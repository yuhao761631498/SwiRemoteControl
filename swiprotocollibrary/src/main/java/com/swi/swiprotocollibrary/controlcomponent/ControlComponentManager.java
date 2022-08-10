package com.swi.swiprotocollibrary.controlcomponent;

import com.swi.swiprotocollibrary.CacheMsgTimeoutCheck;
import com.swi.swiprotocollibrary.MsgCallback;
import com.swi.swiprotocollibrary.baseprotocol.SendMsgArrayData;

/**
 * Copyright (C), 2020-2030
 * <p>
 * 功能描述:  控制组件
 * <p>
 * 创建时间: 2022/8/4 14:41
 *
 * @author yuhao
 */
public class ControlComponentManager extends SendMsgArrayData {

    public ControlComponentManager(CacheMsgTimeoutCheck cacheMsgTimeoutCheck) {
        super(cacheMsgTimeoutCheck);
    }

    /**
     * 自主启航
     */
    public void sendMsgAutoTakeOff(MsgCallback msgCallback) {
        AutoTakeOff autoTakeOff = new AutoTakeOff();
        autoTakeOff.autoTakeOff_height = 150;
        autoTakeOff.autoTakeOff_speed = 50;
        byte[] encode = autoTakeOff.encodeMsg();
        sendCmd(autoTakeOff.frameMsgID, encode, msgCallback);
    }

    /**
     * 添加控制组件的周期回调
     */
    public void setControlCycleCallback(short msgId, MsgCallback msgCallback) {
        addCycleCallback(msgId, msgCallback);
    }

    /**
     * 移除控制组件的周期回调
     *
     * @param msgId  消息id
     */
    public void removeControlCycleCallback(short msgId) {
        removeCycleCallback(msgId);
    }
}
