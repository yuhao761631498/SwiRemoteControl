package com.swi.remotecontrol.application;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.swi.commonlibrary.utils.SwiToast;

/**
 * Created by xx on 2016/6/7.
 */

public class SwiApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    private static SwiApplication swiApplication;

    @SuppressLint("StaticFieldLeak")
    public static Context context;

    public Context getGDUContext() {
        return getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        swiApplication = this;

        init();
    }

    public void init() {
        context = getApplicationContext();
    }

    public SwiToast toast;

    public void show(String str) {
        if (toast != null) {
            toast.showMsg(str);
        }
    }

    public static SwiApplication getSingleApp() {
        return swiApplication;
    }
}
