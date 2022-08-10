package com.swi.datalinklibrary.socket;

import com.swi.datalinklibrary.DataPackageCallback;

/**
 * Copyright (C), 2020-2030
 * <p>
 * 功能描述: 基于网络的数据通信
 * <p>
 * 创建时间: 2022/8/10 9:14
 *
 * @author yuhao
 */
public class SocketDataLink {


    private DataPackageCallback dataPackageCallback;

    public void setDataPackageCallback(DataPackageCallback dataPackageCallback) {
        this.dataPackageCallback = dataPackageCallback;
    }
    public void destroySocket() {

    }
}
