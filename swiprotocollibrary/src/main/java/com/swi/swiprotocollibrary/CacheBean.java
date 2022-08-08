package com.swi.swiprotocollibrary;

import android.os.SystemClock;

/**
 * Copyright (C), 2020-2030
 * <p>
 * 功能描述:缓冲的消息数据
 * <p>
 * 创建时间: 2022/8/5 16:07
 *
 * @author yuhao
 */
public class CacheBean {

    public CacheBean(MsgCallback msgCallback,byte[] msgArray) {
        this.msgCallback = msgCallback;
        sendTime = SystemClock.elapsedRealtime();
        this.msgArray=msgArray;
    }

    public MsgCallback msgCallback;

    public long sendTime;


    public byte[] msgArray;
}
