package com.swi.datalinklibrary.serialport;
import com.swi.datalinklibrary.DataPackageCallback;

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

    public void setDataPackageCallback(DataPackageCallback dataPackageCallback) {
        this.dataPackageCallback=dataPackageCallback;
    }
}
