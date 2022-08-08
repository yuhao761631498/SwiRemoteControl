package com.swi.commonlibrary.permissions;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;

import android.view.Gravity;

import androidx.annotation.NonNull;

import com.swi.commonlibrary.GlobalVariable;
import com.swi.commonlibrary.R;
import com.swi.commonlibrary.dialog.GeneralDialog;
import com.swi.permission.AndPermission;
import com.swi.permission.PermissionListener;
import com.swi.permission.SettingService;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by xx on 2017/5/11.
 * <p>该权限管理知识 申请权限，但是用户拒绝或者同意 要自己做判断 --- xx</p>
 */
public class PermissionHelp {

    /****
     * <p>申请 权限列表</p>
     */
    public final int REQUESTPERSSIONLIST = 0x00;

    /**
     * <p>读写sd卡权限Request --- xx</p>
     */
    public final int WRITEREQUEST = 0x01;

    /**
     * <p>手机精准的定位的权限 ---xx</p>
     */
    public final int FINELOCATIONREQUEST = 0x02;

    /**
     * <p>手机粗略定位的权限</p>
     */
    public final int COARSELOCATIONREQUEST = 0x03;

    /**
     * <p>申请网络状态的权限访问</p>
     */
    public final int ACCESS_NETWORKREQUEST = 0x04;

    private static final int REQUEST_CODE_PERMISSION_OTHER = 102;
    public static final int REQUEST_CODE_SETTING = 300;
    private PermissionCallback mCallback;
    private Context mContext;
    private GeneralDialog mSettingDialog;
    private String mAppName = "GDU Mini";

    public PermissionHelp(Context context) {
        mContext = context;
    }

    public void setCallback(PermissionCallback permissionCallback) {
        mCallback = permissionCallback;
    }

    /**
     * 设置app名
     * @param appName
     */
    public void setAppName(String appName){
        mAppName = appName;
    }

    public void applyPermission() {
        String[] needPermissions = GlobalVariable.getAppNeedPermissions();
        AndPermission.with(mContext)
                .requestCode(REQUEST_CODE_PERMISSION_OTHER)
                .permission(needPermissions)
                .callback(listener)
                .start();
    }

    public void applyPermission(String[] needPermissions){
        AndPermission.with(mContext)
                .requestCode(REQUEST_CODE_PERMISSION_OTHER)
                .permission(needPermissions)
                .callback(listener)
                .start();
    }

    private final PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            if (requestCode == REQUEST_CODE_PERMISSION_OTHER) {
                if (AndPermission.hasPermission(mContext, grantedPermissions)) {
                    if (mCallback != null) {
                        mCallback.onSuccessful();
                    }
                } else {
                    getDeniedPermissions(grantedPermissions);
                }
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            if (requestCode == REQUEST_CODE_PERMISSION_OTHER) {
                getDeniedPermissions(deniedPermissions);
            }
        }
    };



    /**
     * 对用户拒绝掉的权限再次申请
     *
     * @param deniedPermissions
     */
    private void getDeniedPermissions(@NonNull final List<String> deniedPermissions) {
        // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
        if (AndPermission.hasAlwaysDeniedPermission(mContext, deniedPermissions)) {
            requestPermissionFromSetting(deniedPermissions);
        } else {
            GeneralDialog generalDialog = new GeneralDialog(mContext, R.style.NormalDialog) {
                @Override
                public void positiveOnClick() {
                    applyPermission();
                }

                @Override
                public void negativeOnClick() {
                    if (!deniedPermissions.contains("android.permission.CAMERA")) {
                        System.exit(0);
                    }
                }
            };
            generalDialog.setCanceledOnTouchOutside(false);
            generalDialog.setCancelable(false);
            generalDialog.setNoTitle();
            generalDialog.setContentText(getPermissionName(deniedPermissions));
            generalDialog.setPositiveButtonText(R.string.Install_authorization);
            generalDialog.setContentGravity(Gravity.CENTER);
            generalDialog.show();
        }
    }

    @SuppressLint("StringFormatInvalid")
    private String getPermissionName(List<String> deniedPermissions){
        if (deniedPermissions.contains(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            return mContext.getString(R.string.Label_Android_GetSD_permission,mAppName);
        } else if (deniedPermissions.contains(Manifest.permission.READ_PHONE_STATE)) {
            return mContext.getString(R.string.Label_Android_GetDevice_permission,mAppName);
        } else if (deniedPermissions.contains(Manifest.permission.ACCESS_FINE_LOCATION)) {
            return mContext.getString(R.string.Label_Android_GetLoction_permission,mAppName);
        }
        return mContext.getString(R.string.Label_Android_Need_permission,mAppName);
    }


    /**
     * Android6.0以下的定位放在GPS界面，所以要单独跳转
     */
    private void checkLocatePermission() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        ((Activity) mContext).startActivityForResult(intent, REQUEST_CODE_SETTING); // 设置完成后返回到原来的界面 //
    }

    /**
     * 在设置界面进行权限设置
     */
    private void requestPermissionFromSetting(List<String> deniedPermissions) {
        final SettingService settingService = AndPermission.defineSettingDialog((Activity) mContext, REQUEST_CODE_SETTING);
        mSettingDialog = new GeneralDialog(mContext, R.style.NormalDialog) {
            @Override
            public void positiveOnClick() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                    checkLocatePermission();
                } else {
                    settingService.execute();
                }
            }

            @Override
            public void negativeOnClick() {
                settingService.cancel();
                mCallback.onFailure();
            }
        };
        mSettingDialog.setContentText(R.string.Label_Android_Need_permission);
        mSettingDialog.setCanceledOnTouchOutside(false);
        mSettingDialog.setCancelable(false);
        mSettingDialog.setTitleText(R.string.Install_Prompt);
        mSettingDialog.setPositiveButtonText(R.string.Install_authorization);
        mSettingDialog.setContentGravity(Gravity.CENTER);
        mSettingDialog.show();
    }

    private void disMissDialog() {
        if (mSettingDialog != null) {
            mSettingDialog.dismiss();
        }
    }

    public boolean hasPermission(String permission) {
        List<String> permissions = new ArrayList<>();
        permissions.add(permission);
        return hasPermission(permissions);
    }


    public boolean hasPermission(List<String> permissions) {
        return AndPermission.hasPermission(mContext, permissions);
    }

    public void onDestroy() {
        disMissDialog();
    }

    public interface PermissionCallback {
        void onSuccessful();

        void onFailure();
    }
}
