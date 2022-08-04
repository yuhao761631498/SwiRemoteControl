package com.swi.datalinklibrary;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.swi.datalinklibrary.aoa.SwiAccessory;
import com.swi.datalinklibrary.aoa.UsbReceiver;
import com.swi.datalinklibrary.serialport.SwiSerialPort;

/**
 * Copyright (C), 2020-2030, 武汉中旗生物医疗电子有限公司
 * <p>
 * 功能描述:
 * <p>
 * 创建时间: 2022/8/3 15:37
 *
 * @author yuhao
 */
public class SwiDataLinkManager {

    private static SwiDataLinkManager swiDataLinkManager;

    private Context context;
    private SwiAccessory swiAccessory;
    private SwiSerialPort swiSerialPort;
    private UsbReceiver usbReceiver;

    public static SwiDataLinkManager getInstance() {
        if (swiDataLinkManager == null) {
            swiDataLinkManager = new SwiDataLinkManager();
        }
        return swiDataLinkManager;
    }

    public void init(Context context) {
        this.context = context;
        usbReceiver = new UsbReceiver();
        IntentFilter batteryFilter = new IntentFilter();
        batteryFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        context.registerReceiver(usbReceiver, batteryFilter);
    }

    public void switchUsbMode(boolean isCharge) {
        if (swiAccessory == null) {
            swiAccessory = new SwiAccessory(context);
        }
        swiAccessory.isChargeByUsb(isCharge);
    }

    public void SwitchSerialPortMode(boolean isConnect) {
        swiSerialPort = new SwiSerialPort();
    }

    public void onDestroy() {
        context.unregisterReceiver(usbReceiver);
        if (swiAccessory != null) {
            swiAccessory.onDestroy();
        }
        if (usbReceiver != null) {
            usbReceiver.onDestroy();
            usbReceiver = null;
        }
    }

}
