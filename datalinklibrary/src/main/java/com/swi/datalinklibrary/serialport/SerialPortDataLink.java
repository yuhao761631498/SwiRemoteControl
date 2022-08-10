package com.swi.datalinklibrary.serialport;

import com.swi.datalinklibrary.DataPackageCallback;

import android.serialport.SerialPort;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2020-2030
 * <p>
 * 功能描述:串口数据链
 * <p>
 * 创建时间: 2022/8/3 10:18
 *
 * @author yuhao
 */
public class SerialPortDataLink {

    private DataPackageCallback dataPackageCallback;

    private SerialPort serialPort = null;
    private InputStream inputStream = null;
    private OutputStream outputStream = null;
    private ReceiveThread mReceiveThread = null;
    private boolean isStart = false;
    private ThreadPoolExecutor threadPoolExecutor;

    /**
     * 打开串口，接收数据
     * 通过串口，接收单片机发送来的数据
     */
    public void openSerialPort() {
        try {
            serialPort = new SerialPort(new File("/dev/ttyS0"), 115200);
            //调用对象SerialPort方法，获取串口中"读和写"的数据流
            inputStream = serialPort.getInputStream();
            outputStream = serialPort.getOutputStream();
            isStart = true;
            threadPoolExecutor = new ThreadPoolExecutor(1, 1, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<>(128));
        } catch (IOException e) {
            e.printStackTrace();
        }
        getSerialPort();
    }

    /**
     * 关闭串口
     * 关闭串口中的输入输出流
     */
    public void closeSerialPort() {
        Log.i("test", "关闭串口");
        try {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (serialPort != null) {
                serialPort.tryClose();
                serialPort = null;
            }
            isStart = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送数据
     * 通过串口，发送数据到单片机
     *
     * @param sendData 要发送的数据
     */
    public void sendSerialPort(byte[] sendData) {
        if (threadPoolExecutor != null) {
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (outputStream != null) {
                            outputStream.write(sendData);
                            outputStream.flush();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void getSerialPort() {
        if (mReceiveThread == null) {
            mReceiveThread = new ReceiveThread();
        }
        mReceiveThread.start();
    }

    /**
     * 接收串口数据的线程
     */

    private class ReceiveThread extends Thread {
        @Override
        public void run() {
            super.run();
            byte[] readData = new byte[1024];
            while (isStart) {
                if (inputStream == null) {
                    return;
                }
                try {
                    int size = inputStream.read(readData);
                    if (size > 0 && dataPackageCallback != null) {
                        byte[] data = new byte[size];
                        System.arraycopy(readData, 0, data, 0, size);
                        dataPackageCallback.getByteArray(data, size);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void setDataPackageCallback(DataPackageCallback dataPackageCallback) {
        this.dataPackageCallback = dataPackageCallback;
    }
}
