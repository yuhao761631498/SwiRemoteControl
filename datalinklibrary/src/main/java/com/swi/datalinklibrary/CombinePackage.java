package com.swi.datalinklibrary;

import com.swi.commonlibrary.utils.ByteUtils;
import com.swi.datalinklibrary.aoa.AoaDataLink;
import com.swi.datalinklibrary.serialport.SerialPortDataLink;
import com.swi.datalinklibrary.socket.SocketDataLink;

/**
 * Copyright (C), 2020-2030
 * <p>
 * 功能描述:组包
 * <p>
 * 创建时间: 2022/8/10 9:15
 *
 * @author yuhao
 */
public class CombinePackage implements DataPackageCallback {

    private final CombinePackageCallback combinePackageCallback;

    private final byte[] head = {(byte) 0x55, (byte) 0xAA};

    private byte[] combinationBytes;
    private int offset;
    private int lengthData;
    private byte[] brokenHead;
    private boolean hasBrokenHead;

    /**
     * 包含有效载荷长度的包头长度
     */
    private static final byte PACKAGE_LOAD_HEAD = 12;

    /**
     * 有效载荷的起始index
     */
    private static final byte LOAD_LENGTH_OFFSET = 10;

    /**
     * 除去有效载荷跟预留字段以外的包长度
     */
    private static final short PACKAGE_LENGTH_NO_LOAD = 14;

    public CombinePackage(CombinePackageCallback combinePackageCallback, AoaDataLink aoaDataLink,
                          SerialPortDataLink serialPortDataLink, SocketDataLink socketDataLink) {
        this.combinePackageCallback = combinePackageCallback;
        aoaDataLink.setDataPackageCallback(this);
        serialPortDataLink.setDataPackageCallback(this);
        socketDataLink.setDataPackageCallback(this);
    }

    @Override
    public void getByteArray(byte[] message, int length) {
        if (message.length >= PACKAGE_LOAD_HEAD) {
            if (isEqualHead(message)) {
                offset = 0;
                lengthData = ByteUtils.byte2short(message, LOAD_LENGTH_OFFSET);
                combinationBytes = new byte[lengthData + PACKAGE_LENGTH_NO_LOAD];
                hasBrokenHead = false;
            } else {
                if (hasBrokenHead) {
                    byte[] completeHead = new byte[brokenHead.length + length];
                    System.arraycopy(brokenHead, 0, completeHead, 0, brokenHead.length);
                    System.arraycopy(message, 0, completeHead, brokenHead.length, length);
                    hasBrokenHead = false;
                    getByteArray(completeHead, completeHead.length);
                    return;
                }
            }

            if (offset + length > lengthData + PACKAGE_LENGTH_NO_LOAD) {
                int len1 = lengthData + PACKAGE_LENGTH_NO_LOAD - offset;
                System.arraycopy(message, 0, combinationBytes, offset, len1);
                offset += len1;
                if (offset == lengthData + PACKAGE_LENGTH_NO_LOAD && combinePackageCallback != null) {
                    combinePackageCallback.combinePackage(combinationBytes);
                    hasBrokenHead = false;
                    offset = 0;
                }
                byte[] bytes = new byte[length - len1];
                System.arraycopy(message, len1, bytes, 0, length - len1);
                getByteArray(bytes, bytes.length);
            } else {
                System.arraycopy(message, 0, combinationBytes, offset, length);
                offset += length;
                if (offset == lengthData + PACKAGE_LENGTH_NO_LOAD && combinePackageCallback != null) {
                    combinePackageCallback.combinePackage(combinationBytes);
                    hasBrokenHead = false;
                    offset = 0;
                }
            }
        } else {
            if (offset + length == lengthData + PACKAGE_LENGTH_NO_LOAD && combinePackageCallback != null) {
                System.arraycopy(message, 0, combinationBytes, offset, length);
                combinePackageCallback.combinePackage(combinationBytes);
                hasBrokenHead = false;
                offset = 0;
                return;
            }
            if (!hasBrokenHead) {
                brokenHead = new byte[length];
                System.arraycopy(message, 0, brokenHead, 0, length);
                hasBrokenHead = true;
            } else {
                byte[] completeHead = new byte[brokenHead.length + length];
                System.arraycopy(brokenHead, 0, completeHead, 0, brokenHead.length);
                System.arraycopy(message, 0, completeHead, brokenHead.length, length);
                if (completeHead.length >= PACKAGE_LOAD_HEAD) {
                    getByteArray(completeHead, length);
                    hasBrokenHead = false;
                } else {
                    brokenHead = new byte[completeHead.length];
                    System.arraycopy(completeHead, 0, brokenHead, 0, brokenHead.length);
                }
            }
        }
    }


    /**
     * 功能描述 检查包头是否正确
     *
     * @param message 待检查的数组
     * @return 返回是否匹配
     */
    public boolean isEqualHead(byte[] message) {
        return message[0] == head[0] && message[1] == head[1];
    }


    public void destroyCombine() {

    }
}
