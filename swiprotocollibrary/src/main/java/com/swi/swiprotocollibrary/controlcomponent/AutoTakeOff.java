package com.swi.swiprotocollibrary.controlcomponent;

import com.swi.commonlibrary.utils.ByteUtils;
import com.swi.swiprotocollibrary.baseprotocol.BaseSwiFrameBean;

/**
 * Copyright (C), 2020-2030
 * <p>
 * 功能描述:自主启航
 * <p>
 * 创建时间: 2022/8/4 16:29
 *
 * @author yuhao
 */
public class AutoTakeOff extends BaseSwiFrameBean {

    public AutoTakeOff() {
        super((byte)0, (byte)0, (short) 0x0040, (byte) 1, (byte) 1);
    }

    /**
     * 启航高度 （起航高度值 100-5000cm
     * 相对起航点的高度）
     */
    public short autoTakeOff_height;

    /**
     * 启航速度  （上升速度值 50-500cm/s）
     */
    public short autoTakeOff_speed;

    @Override
    public byte[] encode() {
        byte[] bytes = new byte[4];
        int index = 0;
        System.arraycopy(ByteUtils.short2byte(autoTakeOff_height), 0, bytes, index, 2);
        index += 2;
        System.arraycopy(ByteUtils.short2byte(autoTakeOff_speed), 0, bytes, index, 2);
        return bytes;
    }

    @Override
    public void decode(byte[] data) {
        int index = 0;
        autoTakeOff_height = ByteUtils.byte2short(data, index);
        index += 2;
        autoTakeOff_speed = ByteUtils.byte2short(data, index);
    }
}
