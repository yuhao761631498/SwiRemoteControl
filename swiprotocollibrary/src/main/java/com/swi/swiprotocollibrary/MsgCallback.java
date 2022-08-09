package com.swi.swiprotocollibrary;

import com.swi.swiprotocollibrary.baseprotocol.BaseAck;

/**
 * Copyright (C), 2020-2030
 * <p>
 * 功能描述: 发送消息的回调
 * <p>
 * 创建时间: 2022/8/5 15:32
 *
 * @author yuhao
 */
public interface MsgCallback {

    void callback(short codeError, BaseAck baseAck);

}
