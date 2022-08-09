package com.swi.datalinklibrary;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.swi.datalinklibrary.aoa.AoaDataLink;
import com.swi.datalinklibrary.aoa.UsbReceiver;
import com.swi.datalinklibrary.serialport.SerialPortDataLink;

/**
 * Copyright (C), 2020-2030
 * <p>
 * 功能描述: 通信管理类
 * <p>
 * 创建时间: 2022/8/3 15:37
 *
 * @author yuhao
 */
public class SwiDataLinkManager implements DataPackageCallback {

    private static SwiDataLinkManager swiDataLinkManager;

    private Context context;
    private UsbReceiver usbReceiver;
    private AoaDataLink aoaDataLink;
    private SerialPortDataLink serialPortDataLink;
    private ParsePackageCallback parsePackageCallback;

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

    /**
     * 启动USB通信
     *
     * @param isCharge 是否正在充电
     */
    public void switchUsbMode(boolean isCharge) {
        if (aoaDataLink == null) {
            aoaDataLink = new AoaDataLink(context);
            aoaDataLink.setDataPackageCallback(this);
        }
        aoaDataLink.isChargeByUsb(isCharge);
    }


    /**
     * 通过数据链发送数据
     *
     * @param data 需要发送的数据
     */
    public boolean sendDataByDataLink(byte[] data) {
        if (aoaDataLink != null) {
            return aoaDataLink.sendDataLinkData(data);
        } else if (serialPortDataLink != null) {
            //TODO
        }
        return false;
    }


    public void switchSerialPortMode() {
        if (serialPortDataLink != null) {
            serialPortDataLink = new SerialPortDataLink();
            serialPortDataLink.setDataPackageCallback(this);
        }
    }

    @Override
    public void getByteArray(byte[] buffer, int length) {
        if (parsePackageCallback != null) {
            parsePackageCallback.parseData(buffer, length);
        }
    }


    public void setParsePackageCallback(ParsePackageCallback parsePackageCallback) {
        this.parsePackageCallback = parsePackageCallback;
    }


    public void onDestroy() {
        context.unregisterReceiver(usbReceiver);
        if (aoaDataLink != null) {
            aoaDataLink.onDestroy();
        }
        if (usbReceiver != null) {
            usbReceiver.onDestroy();
            usbReceiver = null;
        }
    }


}
