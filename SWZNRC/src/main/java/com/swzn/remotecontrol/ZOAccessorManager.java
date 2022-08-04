package com.swzn.remotecontrol;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.hardware.usb.UsbAccessory;
import android.hardware.usb.UsbManager;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import com.swzn.util.logs.SWZNLog;
import com.swzn.util.logs.SWZNLog2File;
import com.swzn.remotecontrol.coretask.DisposeTask;
import com.swzn.remotecontrol.coretask.ProxyServerTask;
import com.swzn.remotecontrol.coretask.RequestTask;
import com.swzn.remotecontrol.hotplugging.IConnectUav;
import com.swzn.remotecontrol.hotplugging.UsbConnectImp;
import com.swzn.remotecontrol.hotplugging.WifiConnectImp;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

abstract class ZOAccessorManager {
    private static final String TAG = ZOAccessorManager.class.getName();
    protected static final String ACTION_USB_PERMISSION = "com.gdutech.action.USB_PERMISSION";
    protected boolean             mPermissionRequestPending;
    private PendingIntent mPermissionIntent;
    private UsbManager mUsbManager;
    protected UsbAccessory mUsbAccessory;
    private ParcelFileDescriptor mParceFileDescriptor;
    private FileInputStream mInputStream;
    private FileOutputStream mOutputStream;
    private RequestTask mRequestTask;
    private ProxyServerTask mProxyServerAppToUav;
    private Context mContext;
    private IConnectUav mConnectUav;


    public  ZOAccessorManager() {
    }

    protected void init(Context context) {
        mContext = context;
        mUsbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
        mPermissionIntent = PendingIntent.getBroadcast(context, 0, new Intent(ACTION_USB_PERMISSION), 0);
    }

    private long lastConnAccessoryTime = 0;

    public void onResume() {
//        print("-----onResume-----");
        if(System.currentTimeMillis() - lastConnAccessoryTime < 5000)return;
        try {
            if (mInputStream != null && mOutputStream != null)
            {
                return;
            }
            lastConnAccessoryTime = System.currentTimeMillis();
            UsbAccessory[] accessories = mUsbManager.getAccessoryList();
            UsbAccessory accessory = accessories == null ? null : accessories[0];
            if (accessory != null) {
                if (mUsbManager.hasPermission(accessory)) {
                    openAccessory(accessory);
                } else {
                    synchronized (ZOAccessorManager.class) {
                        if (!mPermissionRequestPending) {
                            mUsbManager.requestPermission(accessory, mPermissionIntent);
                            mPermissionRequestPending = true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    protected void openAccessory(UsbAccessory accessory) {
        try {
            mParceFileDescriptor = mUsbManager.openAccessory(accessory);
            if (mParceFileDescriptor != null) {
                setUsbConnect();//初始化一些参数--xx
                mUsbAccessory = accessory;
                FileDescriptor fd = mParceFileDescriptor.getFileDescriptor();
                mInputStream = new FileInputStream(fd);
                mOutputStream = new FileOutputStream(fd);
                Log.d(TAG, "createTask");
                SWZNLog2File.getSingle().saveData("zero____openAccessory" + accessory.getModel());
                //关键部分---xx
                createTask();
    //            EventBus.getDefault().post(new EventCenter(ZOConstants.RESTARTRTP));
                //回调监听函数----xx
                openUsbModel();
                print("accessory opened");
            } else {
                Log.d(TAG, "accessory open fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**设置USB连接*/
    private void setUsbConnect()
    {
        //初始化状态---xx
        mConnectUav = new UsbConnectImp();
        mConnectUav.setConnectInfo();
    }

    private void createCacheTask()
    {

    }

    private DisposeTask disposeTask;
    /**创建任务*/
    private void createTask() {
        if(mProxyServerAppToUav == null)
        {
            SWZNLog.LogE("createTask===== 创建CreateTask");
            //添加Selecter
            mProxyServerAppToUav = new ProxyServerTask();
            ZOThreadManager.getInstance().addWorkStealingPool(mProxyServerAppToUav);
        }
        if(disposeTask == null )
        {
            disposeTask = new DisposeTask();
            disposeTask.setOnGetUsbDataCallBack(mProxyServerAppToUav.onGetUsbDataCallBack);
            ZOThreadManager.getInstance().addWorkStealingPool(disposeTask);
        }
        //添加发送数据到USB的请求任务
        mRequestTask = new RequestTask(mInputStream, mOutputStream,disposeTask);
        ZOThreadManager.getInstance().addWorkStealingPool(mRequestTask);
        // thread start
    }

    private void print(String str){
        Log.e(TAG, str);
    }

    protected void closeAccessory() {
        SWZNLog.LogE("closeAccessory =========================");
        setWifiConnect();
        close();
        removeThread();
        closeUsbModel();
        mInputStream = null;
        mOutputStream = null;
        mParceFileDescriptor = null;
        mUsbAccessory = null;
    }

    /**设置wifi连接*/
    private void setWifiConnect() {
        mConnectUav = new WifiConnectImp();
        mConnectUav.setConnectInfo();
    }

    /**关闭各种流，清除数据*/
    public void close() {
        try {
            if (mInputStream != null)
            {
                mInputStream.close();
            }
            if (mOutputStream != null) {
                mOutputStream.close();
            }
            if (mParceFileDescriptor != null) {
                mParceFileDescriptor.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**清除任务*/
    private void removeThread()
    {

        try {

            if(mRequestTask != null){
                mRequestTask.close();
            }
            ZOThreadManager.getInstance().removeWorkStealingPool(mRequestTask);

            if(mProxyServerAppToUav != null){
                mProxyServerAppToUav.close();
            }
            ZOThreadManager.getInstance().removeWorkStealingPool(mProxyServerAppToUav);

            if(disposeTask != null )
            {
                disposeTask.close();
            }
            ZOThreadManager.getInstance().removeWorkStealingPool(disposeTask);
            mRequestTask = null;
            mProxyServerAppToUav = null;
            disposeTask = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getVersion(){
        if(mContext != null){
            try {
                PackageManager pm = mContext.getPackageManager();
                PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(),
                        PackageManager.GET_CONFIGURATIONS);
                return pi.versionName;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    abstract void openUsbModel();
    abstract void closeUsbModel();


}
