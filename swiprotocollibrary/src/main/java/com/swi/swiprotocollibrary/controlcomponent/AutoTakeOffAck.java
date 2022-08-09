package com.swi.swiprotocollibrary.controlcomponent;

import com.swi.swiprotocollibrary.baseprotocol.BaseAck;

/**
 * Copyright (C), 2020-2030
 * <p>
 * 功能描述:
 * <p>
 * 创建时间: 2022/8/9 14:11
 *
 * @author yuhao
 */
public class AutoTakeOffAck extends BaseAck {

    public AutoTakeOffAck() {
        super((short) 0x0040);
    }

    @Override
    public void decode(byte[] data) {

    }
}
