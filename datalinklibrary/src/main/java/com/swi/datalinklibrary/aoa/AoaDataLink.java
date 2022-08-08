package com.swi.datalinklibrary.aoa;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbAccessory;
import android.hardware.usb.UsbManager;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import com.swi.commonlibrary.YhLog2File;
import com.swi.commonlibrary.utils.DataUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static com.swi.commonlibrary.GlobalVariable.ACCESSORY_MODE1;
import static com.swi.commonlibrary.GlobalVariable.ACCESSORY_MODE2;

/**
 * Copyright (C), 2020-2030
 * <p>
 * 功能描述: AOA形式的数据链
 * <p>
 * 创建时间: 2022/8/3 10:17
 *
 * @author yuhao
 */
public abstract class AoaDataLink {

    public final String ACTION_USB_PERMISSION = "com.android.gdu.saga.USB_PERMISSION";

    private final Context mContext;

    private UsbManager mUsbManager;

    /**
     * 正在申请权限中，避免多次弹框
     */
    private boolean requestPermissionIng;

    private UsbAccessory usbAccessory;

    private ParcelFileDescriptor mFileDescriptor;

    private Thread writeThread;

    private FileInputStream mFileInputStream;

    private FileOutputStream mFileOutputStream;

    private final Object objectOut = new Object();

    private final Object objectIn = new Object();

    private boolean connAble;

    /**
     * 写数据的队列，用来收集所有需要往usb写的数据
     */
    protected LinkedBlockingQueue<byte[]> sendQueue;

    /**
     * 每次从usb读数据的缓存
     */
    private final byte[] readCacheBuffer;

    private Thread readThread;

    public AoaDataLink(Context context) {
        this.mContext = context;
        mUsbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
        sendQueue = new LinkedBlockingQueue<>(128);
        readCacheBuffer = new byte[1024 * 4];

        IntentFilter intentFilter = new IntentFilter(ACTION_USB_PERMISSION);
        context.registerReceiver(permissionReceiver, intentFilter);
    }

    public void isChargeByUsb(boolean isCharge) {
        Log.e("yuhao", "isCharge: " + isCharge);
        if (isCharge && !connAble) {
            UsbManager usbManager = (UsbManager) mContext.getSystemService(Context.USB_SERVICE);
            UsbAccessory[] usbAccessories = usbManager.getAccessoryList();
            if (usbAccessories != null) {
                Log.e("yuhao", "usbAccessories.length: " + usbAccessories.length);
            } else {
                Log.e("yuhao", "未获取到任何设备");
            }
            if (usbAccessories != null && usbAccessories.length > 0) {
                Log.e("yuhao", "usbAccessories[0].getModel(): " + usbAccessories[0].getModel());
                if (usbAccessories[0].getModel().equals(ACCESSORY_MODE1) || usbAccessories[0].getModel().equals(ACCESSORY_MODE2)) {
                    connAccessory(usbAccessories[0]);
                } else {
                    YhLog2File.getSingle().saveData("窝草，获取不到USB的设备: ");
                }
            }
        } else if (!isCharge) {
            onAoaDestroy();
        }
    }


    /************
     * 连接设备
     * @param usbAccessory  Usb配件
     */
    private synchronized void connAccessory(UsbAccessory usbAccessory) {
        if (usbAccessory == null || mFileDescriptor != null || requestPermissionIng) {
            return;
        }
        if (usbAccessory.getModel().equals(ACCESSORY_MODE1) || usbAccessory.getModel().equals(ACCESSORY_MODE2)) {
            Log.e("yuhao", "connAccessory========连接设备正确");
        } else {
            //连接的设备，不对
            Log.e("yuhao", "connAccessory====连接设备不正确:" + usbAccessory.getModel());
            return;
        }

        this.usbAccessory = usbAccessory;
        //判断是否已经获取了权限
        boolean hadGetPermission = mUsbManager.hasPermission(usbAccessory);

        //未获取到权限
        if (!hadGetPermission) {
            PendingIntent pi = PendingIntent.getBroadcast(mContext, 0, new Intent(ACTION_USB_PERMISSION), 0);
            mUsbManager.requestPermission(usbAccessory, pi);
            requestPermissionIng = true;
            return;
        }

        //开始工作
        if (writeThread == null || !writeThread.isAlive()) {
            writeThread = new Thread(writeRun);
            writeThread.start();
        }
    }


    /**
     * 打开USB设备，并往usb设备写数据的线程---xx
     */
    private final Runnable writeRun = new Runnable() {
        @Override
        public void run() {
            if (usbAccessory == null) {
                return;
            }

            mFileDescriptor = mUsbManager.openAccessory(usbAccessory);
            if (mFileDescriptor == null) {
                //TODO 打开失败
                YhLog2File.getSingle().saveData("打开openAccessory usbAccessory 失败");
                return;
            }
            mFileInputStream = new FileInputStream(mFileDescriptor.getFileDescriptor());
            mFileOutputStream = new FileOutputStream(mFileDescriptor.getFileDescriptor());
            if (mFileOutputStream == null || mFileInputStream == null) {
                //TODO 打开设备失败
                YhLog2File.getSingle().saveData("mFileOutputStream mFileInputStream 失败");
                try {
                    mFileDescriptor.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mFileDescriptor = null;
                return;
            }
            //准备尝试一下 是否 连接的设备不是假设备
            byte[] dataTest = new byte[3];
            try {
                mFileOutputStream.write(dataTest, 0, 1);
            } catch (Exception e) {
                onAoaDestroy();
                YhLog2File.getSingle().saveData("遇到假的设备=====");
                return;
            }
            connAble = true;
            YhLog2File.getSingle().saveData("开始工作==002：" + connAble + "," + this.hashCode());

            //启动read 线程
            if (readThread != null && readThread.isAlive()) {
                readThread.interrupt();
                readThread = null;
            }

            if (readThread == null || !readThread.isAlive()) {
                readThread = new Thread(readRun);
                readThread.start();
            }

            sendQueue.clear();
            //下面是正式的写数据了
            while (connAble) {
                byte[] data = null;
                try {
                    data = sendQueue.poll(2000, TimeUnit.MILLISECONDS);
                    if (data == null || data.length == 12) {
                        continue;
                    }
//                    writeLog(data);
                    synchronized (objectOut) {
                        if (mFileOutputStream != null && mFileOutputStream.getFD().valid()) {
                            mFileOutputStream.write(data);
                            mFileOutputStream.flush();
                        } else {
                            YhLog2File.getSingle().saveData("mFileOutputStream is Err");
                            Thread.sleep(100);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    YhLog2File.getSingle().saveData("write thread 被打断");
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                    YhLog2File.getSingle().saveData("write thread 写数据出现异常:" + this.hashCode() + "," + e.toString());
                    break;
                }
            }
            //此处需要释放资源
            onAoaDestroy();
            YhLog2File.getSingle().saveData("此处需要释放资源=====");
        }
    };


    /**
     * 从USB读取数据的线程
     */
    private final Runnable readRun = new Runnable() {
        @Override
        public void run() {
            int readLength = 0;
            while (connAble) {
                try {
                    synchronized (objectIn) {
                        if (mFileInputStream == null || !mFileInputStream.getFD().valid()) {
                            readLength = -2;
                        } else {
                            readLength = mFileInputStream.read(readCacheBuffer);
                        }
                    }
                    if (readLength == -2) {
                        YhLog2File.getSingle().saveData("readLength == -2");
                        Thread.sleep(100);
                        continue;
                    }
                    parseDataLinkData(readCacheBuffer, readLength);
                } catch (IOException e) {
                    e.printStackTrace();
                    YhLog2File.getSingle().saveData("接收数据异常————IOException:" + e.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    YhLog2File.getSingle().saveData("接收数据异常————InterruptedException:" + e.toString());
                }
            }
        }
    };

    public abstract void parseDataLinkData(byte[] buffer, int length);

    /***
     * 把数据存放到发送的队列中，等待发送
     * @param data 待发送的数据
     */
    public boolean sendDataLinkData(byte[] data) {
        if (connAble && sendQueue != null) {
            return sendQueue.offer(data);
        }
        return false;
    }

    private void writeLog(byte[] data) {
        if (data != null) {
            StringBuilder sb = new StringBuilder();
            for (byte datum : data) {
                sb.append(DataUtil.byte2Hex(datum)).append(",");
            }
            System.out.println("test readLength writeLog " + sb.toString());
        }
    }

    public void onAoaDestroy() {
        mFileInputStream = null;
        mFileOutputStream = null;
        connAble = false;

        //关闭一下线程
        if (readThread != null && readThread.isAlive()) {
            readThread.interrupt();
            readThread = null;
        }

        synchronized (objectIn) {
            try {
                if (mFileInputStream != null)
                    mFileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mFileInputStream = null;
        }

        synchronized (objectOut) {
            if (mFileOutputStream != null) {
                try {
                    mFileOutputStream.notify();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    mFileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mFileOutputStream = null;
            }
        }

        if (mFileDescriptor != null) {
            try {
                mFileDescriptor.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mFileDescriptor = null;
        }
        usbAccessory = null;
    }


    private final BroadcastReceiver permissionReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ACTION_USB_PERMISSION)) {
                boolean granted = intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false);
                if (granted) {
                    requestPermissionIng = false;
                }
            }
        }
    };


    /**
     * 回收资源,是界面倍回收的时候调用
     */
    public void onDestroy() {
        if (writeThread != null && writeThread.isAlive()) {//如果是writeThread 存在，只需要终止writeThread,其他的一些资源，在writeThread里面回收
            writeThread.interrupt();
            writeThread = null;
        }

        mContext.unregisterReceiver(permissionReceiver);
    }
}
