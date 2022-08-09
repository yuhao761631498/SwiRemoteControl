package com.swi.datalinklibrary;

/**
 * Copyright (C), 2020-2030
 * <p>
 * 功能描述: 数据包的回调
 * <p>
 * 创建时间: 2022/8/8 15:31
 *
 * @author yuhao
 */
public interface DataPackageCallback {

    void getByteArray(byte[] buffer,int length);
}
