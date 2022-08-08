package com.swi.swiprotocollibrary.baseprotocol;

import com.swi.datalinklibrary.SwiDataLinkManager;
import com.swi.swiprotocollibrary.CacheMsgTimeoutCheck;
import com.swi.swiprotocollibrary.MsgCallback;

/**
 * Copyright (C), 2020-2030
 * <p>
 * 功能描述: 数据发送
 * <p>
 * 创建时间: 2022/8/4 15:26
 *
 * @author yuhao
 */
public class SendMsgArrayData {

    private CacheMsgTimeoutCheck cacheMsgTimeoutCheck;

    public SendMsgArrayData(CacheMsgTimeoutCheck cacheMsgTimeoutCheck) {
        this.cacheMsgTimeoutCheck = cacheMsgTimeoutCheck;
    }

    public void sendCmd(short msgId, byte[] msg, MsgCallback msgCallback) {
        SwiDataLinkManager.getInstance().sendDataByDataLink(msg);
        if (cacheMsgTimeoutCheck != null) {
            cacheMsgTimeoutCheck.putCacheMsg(msgId, msgCallback, msg);
        }
    }


}
