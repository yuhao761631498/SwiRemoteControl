package com.swi.swiprotocollibrary.baseprotocol;

import com.swi.commonlibrary.YhLog2File;
import com.swi.commonlibrary.utils.ByteUtils;
import com.swi.commonlibrary.utils.CRC16M;
import com.swi.datalinklibrary.ParsePackageCallback;
import com.swi.datalinklibrary.SwiDataLinkManager;
import com.swi.swiprotocollibrary.CacheBean;
import com.swi.swiprotocollibrary.MsgTypeConfig;
import com.swi.swiprotocollibrary.controlcomponent.AutoTakeOffAck;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.swi.swiprotocollibrary.MsgIdConfig.MSG_ID_AUTO_LAND;
import static com.swi.swiprotocollibrary.MsgIdConfig.MSG_ID_AUTO_TAKEOFF;

/**
 * Copyright (C), 2020-2030
 * <p>
 * 功能描述:数据解析类
 * <p>
 * 创建时间: 2022/8/8 15:39
 *
 * @author yuhao
 */
public class ParseDataPackage implements ParsePackageCallback {

    private final ConcurrentHashMap<Short, CacheBean> cacheHashMap;

    private ThreadPoolExecutor threadPoolExecutor;

    public ParseDataPackage(ConcurrentHashMap<Short, CacheBean> cacheHashMap) {
        this.cacheHashMap = cacheHashMap;
        SwiDataLinkManager.getInstance().setParsePackageCallback(this);
        threadPoolExecutor = new ThreadPoolExecutor(3, 3, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>(128));
    }

    @Override
    public void parseData(byte[] buffer, int length) {
        if (buffer != null && length > 14) {
            int crc = CRC16M.getCrc(buffer, 2, length - 4);
            short crcCheck = ByteUtils.byte2short(buffer, length - 2);
            if (crc == crcCheck) {
                byte desSysId = buffer[5];
                byte desComId = buffer[6];
                if (desSysId == 4 && desComId == 8) {
                    byte packageType = buffer[2];
                    switch (packageType) {
                        case MsgTypeConfig.MSG_CMD_ACK:
                            short msgId = ByteUtils.byte2short(buffer, 7);
                            switch (msgId) {
                                case MSG_ID_AUTO_TAKEOFF:
                                    if (threadPoolExecutor != null && !threadPoolExecutor.isShutdown()) {
                                        threadPoolExecutor.execute(new Runnable() {
                                            @Override
                                            public void run() {
                                                AutoTakeOffAck autoTakeOffAck = new AutoTakeOffAck();
                                                autoTakeOffAck.decodeMsg(buffer, length);
                                                autoTakeOffAck.frameMsgID = MSG_ID_AUTO_TAKEOFF;
                                                CacheBean cacheBean = cacheHashMap.get(autoTakeOffAck.frameMsgID);
                                                if (cacheBean != null && cacheBean.msgCallback != null) {
                                                    cacheBean.msgCallback.callback(autoTakeOffAck.ack, autoTakeOffAck);
                                                }
                                            }
                                        });
                                    }
                                    break;

                                case MSG_ID_AUTO_LAND:
                                    break;
                            }
                            break;

                        case MsgTypeConfig.MSG_CMD_REPORT:
                            break;
                    }
                }
            } else {
                YhLog2File.getSingle().saveData("CRC校验失败");
            }
        }
    }


    public void destroyParseData() {
        if (threadPoolExecutor != null) {
            threadPoolExecutor.shutdownNow();
            threadPoolExecutor = null;
        }
    }
}
