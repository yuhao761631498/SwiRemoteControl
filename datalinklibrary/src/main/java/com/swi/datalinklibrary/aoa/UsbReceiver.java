package com.swi.datalinklibrary.aoa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbManager;
import android.os.BatteryManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.swi.datalinklibrary.SwiDataLinkManager;


/**
 * Copyright (C), 2020-2030
 * <p>
 * 功能描述:  AOA配件插入广播
 * <p>
 * 创建时间: 2022/8/3 13:54
 *
 * @author yuhao
 */
public class UsbReceiver extends BroadcastReceiver {

    private Thread thread;

    private boolean isChargeIng;

    private Thread notifyThread;

    private final Object object = new Object();

    public UsbReceiver() {
        thread = new Thread(runnable);
        thread.start();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        switch (action) {
            case UsbManager.ACTION_USB_ACCESSORY_ATTACHED:
//            handler.sendEmptyMessage(0);
                break;
            case UsbManager.ACTION_USB_ACCESSORY_DETACHED:
                SwiDataLinkManager.getInstance().switchUsbMode(false);
                break;
            case Intent.ACTION_BATTERY_CHANGED:
                int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
                if (chargePlug == 0) { //USB被拔出了，或者理解为充电被停止了
                    isChargeIng = false;
                    SwiDataLinkManager.getInstance().switchUsbMode(false);
                    Log.e("yuhao", "USB被拔出了: ");
                } else if (chargePlug == BatteryManager.BATTERY_PLUGGED_USB) { //判断是否通过usb充电
                    isChargeIng = true;
                    Log.e("yuhao", "USB被插入: ");
                    if (notifyThread == null || !notifyThread.isAlive()) {
                        notifyThread = new Thread(notifyRun);
                        notifyThread.start();
                    }
                }
                break;
        }
    }


    private final Runnable notifyRun = new Runnable() {
        @Override
        public void run() {
            synchronized (object) {
                try {
                    object.notifyAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };


    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (object) {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                }

                try {
                    Thread.sleep(2000);
                    if (isChargeIng) {
                        handler.sendEmptyMessage(0);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    private final Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            SwiDataLinkManager.getInstance().switchUsbMode(true);
            return false;
        }
    });

    public void onDestroy() {
        if (thread != null) {
            thread.interrupt();
            thread = null;
        }

    }

}
