package com.swi.swiprotocollibrary.baseprotocol;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.swi.commonlibrary.application.SwiApplication;
import com.swi.commonlibrary.config.CodeErrorConfig;
import com.swi.commonlibrary.utils.ByteUtils;
import com.swi.commonlibrary.utils.ToastUtils;
import com.swi.swiprotocollibrary.MsgIdConfig;

/**
 * Copyright (C), 2020-2030
 * <p>
 * 功能描述: 应答基类
 * <p>
 * 创建时间: 2022/8/9 14:11
 *
 * @author yuhao
 */
public abstract class BaseAck extends BaseSwiFrameBean {

    public short ack;

    public BaseAck(short frameMsgID) {
        super(frameMsgID);
    }

    public void decodeMsg(byte[] data, int length) {
        frameMsgID = ByteUtils.byte2short(data, 7);
        if (frameMsgID == MsgIdConfig.MSG_ID_AUTO_TAKEOFF) {
            short ack = ByteUtils.byte2short(data, 12);
            handler.sendEmptyMessage(ack);
            byte[] load = new byte[length];
            System.arraycopy(data, 12, load, 0, length);
            decode(load);
        }
    }

    public abstract void decode(byte[] data);

    private final Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            switch (message.what) {
                case CodeErrorConfig.CODE_EXECUTE_SUCCESS:
                    ToastUtils.show("指令执行成功");
                    break;

                case CodeErrorConfig.CODE_EXECUTE_FAILED:
                    ToastUtils.show("指令执行失败");
                    break;
            }
            return false;
        }
    });

    @Override
    public byte[] encode() {
        return new byte[0];
    }

}
