package com.swi.swiprotocollibrary.baseprotocol;

import com.swi.commonlibrary.utils.ByteUtils;
import com.swi.commonlibrary.utils.CRC16M;

/**
 * Copyright (C), 2020-2030
 * <p>
 * 功能描述:
 * <p>
 * 创建时间: 2022/8/4 15:37
 *
 * @author yuhao
 */
public abstract class BaseSwiFrameBean {

    public BaseSwiFrameBean(byte frameCmdType, byte frameMsgType, short frameMsgID, byte frameDesSysID,
                            byte frameDesComID) {
        this.frameCmdType = frameCmdType;
        this.frameMsgType = frameMsgType;
        this.frameMsgID = frameMsgID;
        this.frameDesSysID = frameDesSysID;
        this.frameDesComID = frameDesComID;
    }

    /**
     * index = 0
     * <p>帧头1--xx</p>
     */
    public static final byte frameHeader1 = (byte) 0x55;

    /**
     * index = 1
     * <p>帧头2--xx</p>
     */
    public static final byte frameHeader2 = (byte) 0xAA;

    /**
     * index = 2
     * 指令类型(可以在消息里面体现)
     * 用来说明payload承载的数据类型
     * <p>
     * XXXXX000	消息指令
     * XXXXX001	组网控制指令
     * XXXXX010	文件传输协议
     */
    public byte frameCmdType;

    /**
     * index = 2
     * 消息类型
     * 1、用来标志数据包方向性；
     * 2、用来指令optional的可用性；
     * 3、部分字节保留；
     * ----0---
     * X0000XXX ：请求帧     X0001XXX ：应答帧    x0010XXX ：上报帧    x0011xxx ：广播帧
     * ----0--- ：optional字段无效，长度为0
     * ----1--- ：optional 字段有效，根据实际定义解析该字段
     */
    public byte frameMsgType;

    /**
     * index = 2
     * 有效标志
     * 0XXXXXXX  optional 字段无效，长度 0
     * 1XXXXXXX  optional 字段有效
     */
    public byte frameOptional = 0;

    /**
     * index = 3
     * 源系统ID
     * 指示源系统ID，0是无效地址
     */
    public byte frameSrcSysID = 4;

    /**
     * index = 4
     * 源组件ID
     * 指示源组件ID，0是无效地址
     */
    public byte frameSrcComID = 8;

    /**
     * index = 5
     * 目的系统ID
     * 指示目的系统ID，0表示广播地址
     */
    public byte frameDesSysID;

    /**
     * index = 6
     * 目的组件ID
     * 指示目的组件ID，0表示广播地址
     */
    public byte frameDesComID;

    /**
     * index = 7-8
     * 消息ID
     * 数据包指令ID，根据该ID，解析payload数据包具体内容
     */
    public short frameMsgID;

    /**
     * index = 9
     * <p>帧的序列号 -- xx</p>
     * 指令集数据包序号，用来进行数据包统计，以及丢包统计，每一个单独组件均包含自己包序号管理系统
     */
    public byte frameSerial;

    /**
     * index = 10-11
     * 指示payload有效数据长度，该字段目前是一个字节，在后续的演变中可根据flag标志位的指示，增加字节长度
     */
    public short frameLength;

    /**
     * index=11+2
     * 有效载荷
     * 有效数据字段
     */
    public byte[] frameContent;

    /**
     * 预留字段
     * 11+2+frameLength+2  (一般长度为0)
     */
    public byte[] frameReserved = new byte[1];

    /**
     * 校验和
     * 11+2+frameLength+2
     * X.25 CRC
     */
    public short frameCRC;


    public byte[] encodeMsg() {
        frameContent = encode();
        frameLength = (short) frameContent.length;
        byte[] msg = new byte[12 + frameLength + 2];
        int index = 0;
        msg[index] = frameHeader1;
        index++;
        msg[index] = frameHeader2;
        index++;
        msg[index] = getMsgType(frameCmdType, frameMsgType);
        index++;
        msg[index] = frameSrcSysID;
        index++;
        msg[index] = frameSrcComID;
        index++;
        msg[index] = frameDesSysID;
        index++;
        msg[index] = frameDesComID;
        index++;
        System.arraycopy(ByteUtils.short2byte(frameMsgID), 0, msg, index, 2);
        index += 2;
        msg[index] = ++frameSerial;
        index++;
        System.arraycopy(ByteUtils.short2byte(frameLength), 0, msg, index, 2);
        index += 2;
        System.arraycopy(frameContent, 0, msg, index, frameLength);
        index += frameLength;
        if (frameOptional != 0) {
            System.arraycopy(frameReserved, 0, msg, index, frameReserved[0]);
            index += frameReserved[0];
        }
        frameCRC = (short) CRC16M.getCrc(msg, 2, 10 + frameLength);
        System.arraycopy(ByteUtils.short2byte(frameCRC), 0, msg, index, 2);
        return msg;
    }

    private byte getMsgType(byte frameCmdType, byte frameMsgType) {
        return (byte) (frameOptional & 0x80 | frameCmdType & 0x07 | frameMsgType & 0x78);
    }

    public abstract byte[] encode();

    public abstract void decode(byte[] data);

}
