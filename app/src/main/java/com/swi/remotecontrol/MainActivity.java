package com.swi.remotecontrol;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import com.amap.api.maps.AMap;
import com.swi.baselibrary.BaseActivity;
import com.swi.baselibrary.DisplayUtils;
import com.swi.baselibrary.GlobalVariable;
import com.swi.baselibrary.SwitchMapAndSurface;
import com.swi.maplibrary.callback.MapWindowClickCallback;
import com.swi.maplibrary.mapview.GuideMapFragment;

public class MainActivity extends BaseActivity implements MapWindowClickCallback, View.OnClickListener {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private GuideMapFragment guideMapFragment;
    private FrameLayout fr_map_content;
    private TextureView texture_View;
    private ConstraintLayout cs_video_content;


    @Override
    public int getLayoutId() {
        //去除标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去除状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
//        ImmersionBar.with(this).fitsSystemWindows(true).autoDarkModeEnable(true).statusBarColor(
//                R.color.color_224CD0).init();

        fr_map_content = findViewById(R.id.fr_map_content);
        texture_View = findViewById(R.id.texture_View);
        cs_video_content = findViewById(R.id.cs_video_content);


        guideMapFragment = new GuideMapFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fr_map_content, guideMapFragment).commit();
    }

    @Override
    public void initListener() {
        guideMapFragment.setMapWindowClickCallback(this);
        cs_video_content.setOnClickListener(this);
    }

    @Override
    public void initData() {

        initGlobal();

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission_group.LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

    }

    private void initGlobal() {
        int[] screenRelatedInformation = DisplayUtils.getScreenRelatedInformation(MainActivity.this);
        if (screenRelatedInformation != null && screenRelatedInformation.length > 1) {
            GlobalVariable.screenWidth = screenRelatedInformation[0];
            GlobalVariable.screenHeight = screenRelatedInformation[1];
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                for (int result : grantResults) {
                    if (result != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(MainActivity.this, "请求位置权限权限被拒绝", Toast.LENGTH_LONG).show();
                        break;
                    }
                }
                break;

            case 2:
                for (int result : grantResults) {
                    if (result != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(MainActivity.this, "请求存储权限被拒绝", Toast.LENGTH_LONG).show();
                        break;
                    }
                }
                break;
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    @Override
    public void mapWindowClick(AMap aMap) {
        ViewGroup.LayoutParams layoutParams = fr_map_content.getLayoutParams();
        if (layoutParams.width < GlobalVariable.screenWidth * 0.8) {  //当前为窗口模式
            SwitchMapAndSurface.switchWindow(MainActivity.this, fr_map_content, cs_video_content,
                    () -> guideMapFragment.removeTouchListener());
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cs_video_content:
                ConstraintLayout.LayoutParams layoutParams =
                        (ConstraintLayout.LayoutParams) cs_video_content.getLayoutParams();
                if (layoutParams.width < GlobalVariable.screenWidth * 0.8) {
                    SwitchMapAndSurface.switchWindow(MainActivity.this, cs_video_content, fr_map_content,
                            () -> guideMapFragment.addTouchListener());
                }
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        guideMapFragment = null;
    }
}