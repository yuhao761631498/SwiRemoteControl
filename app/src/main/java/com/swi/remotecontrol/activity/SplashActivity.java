package com.swi.remotecontrol.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.swi.baselibrary.BaseActivity;
import com.swi.commonlibrary.GlobalVariable;
import com.swi.commonlibrary.config.SwiCommonConfig;
import com.swi.commonlibrary.permissions.PermissionHelp;
import com.swi.remotecontrol.MainActivity;
import com.swi.remotecontrol.R;

import java.io.File;
import java.net.DatagramSocket;
import java.net.SocketException;

public class SplashActivity extends BaseActivity {

    private PermissionHelp mPermissionHelp;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        mPermissionHelp = new PermissionHelp(SplashActivity.this);
        mPermissionHelp.setAppName(getString(R.string.app_name));
        applyPermissions();
    }


    private void applyPermissions() {
        mPermissionHelp.setCallback(new PermissionHelp.PermissionCallback() {
            @Override
            public void onSuccessful() {
                init();
            }

            @Override
            public void onFailure() {
                finish();
                System.exit(0);
            }
        });
        mPermissionHelp.applyPermission();
    }

    private void init() {
        File file = new File(SwiCommonConfig.BaseDirectory);
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
        }

        if (GlobalVariable.UDP_SOCKET_PORT == 0) {
            getSocketPort();
        }

        handler.sendEmptyMessageDelayed(0, 2000);
    }


    private final Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
            return false;
        }
    });


    /***************************
     * 获取socket的端口号---xx
     */
    public void getSocketPort() {
        //获取数传端口
        for (int a = 3000; a < 9000; a++) {
            try {
                DatagramSocket socket = new DatagramSocket(a);
                socket.close();
                GlobalVariable.UDP_SOCKET_PORT = a;
                break;
            } catch (SocketException e) {
                e.printStackTrace();
            }
        }
        Log.e("yuhao", "分配的数传端口:" + GlobalVariable.UDP_SOCKET_PORT);
//        GlobalVariable.UDPSocketIMGPort = 6000;
        for (int a = 7078; a < 9000; a++) {
            try {
                DatagramSocket socket = new DatagramSocket(a);
                socket.close();
                GlobalVariable.UDP_SOCKET_IMG_PORT = a;
                break;
            } catch (SocketException e) {
                e.printStackTrace();
            }
        }
        Log.e("yuhao", "分配的图传端口:" + GlobalVariable.UDP_SOCKET_IMG_PORT);
    }
}