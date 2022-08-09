package com.swi.swiprotocollibrary;

/**
 * Copyright (C), 2020-2030
 * <p>
 * 功能描述: 消息类型
 * <p>
 * 创建时间: 2022/8/8 15:07
 *
 * @author yuhao
 */
public class MsgTypeConfig {

    /**
     * 消息指令请求帧
     */
    public static final byte MSG_CMD_REQUEST = 0;

    /**
     * 消息指令应答帧
     */
    public static final byte MSG_CMD_ACK = 8;

    /**
     * 消息指令上报帧
     */
    public static final byte MSG_CMD_REPORT = 16;

    /**
     * 消息指令广播帧
     */
    public static final byte MSG_CMD_BROADCAST = 24;


    /**
     * 组网指令请求帧
     */
    public static final byte MANET_CMD_REQUEST = 1;


    /**
     * 组网指令应答帧
     */
    public static final byte MANET_CMD_ACK = 9;
}
