package com.swi.swiprotocollibrary;

import android.os.Build;
import android.os.SystemClock;

import androidx.annotation.RequiresApi;

import com.swi.commonlibrary.config.CodeErrorConfig;
import com.swi.datalinklibrary.SwiDataLinkManager;
import com.swi.swiprotocollibrary.baseprotocol.ParseDataPackage;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Copyright (C), 2020-2030
 * <p>
 * 功能描述:消息重传与超时检测处理
 * <p>
 * 创建时间: 2022/8/5 16:01
 *
 * @author yuhao
 */
public class CacheMsgTimeoutCheck extends Thread {

    private ParseDataPackage parseDataPackage;

    private ConcurrentHashMap<Short, CacheBean> cacheHashMap;

    public CacheMsgTimeoutCheck() {
        cacheHashMap = new ConcurrentHashMap<>(256);

        parseDataPackage = new ParseDataPackage(cacheHashMap);
    }

    public void putCacheMsg(short msgId, MsgCallback msgCallback, byte[] msg) {
        if (msgCallback != null && msg != null) {
            CacheBean cacheBean = new CacheBean(msgCallback, msg);
            cacheHashMap.put(msgId, cacheBean);
        }
    }

    public CacheBean getCacheMsg(short msgId) {
        CacheBean cacheBean = cacheHashMap.get(msgId);
        if (cacheBean != null) {
            cacheHashMap.remove(msgId);
        }
        return cacheBean;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void run() {
        super.run();
        try {
            while (!currentThread().isInterrupted()) {
                Iterator<Map.Entry<Short, CacheBean>> iterator = cacheHashMap.entrySet().iterator();
                long currentTime = SystemClock.elapsedRealtime();
                if (iterator.hasNext()) {
                    Map.Entry<Short, CacheBean> next = iterator.next();
                    CacheBean value = next.getValue();
                    MsgCallback msgCallback = value.msgCallback;
                    if (value != null && currentTime - value.sendTime > 3000) {
                        iterator.remove();
                        if (msgCallback != null) {
                            msgCallback.callback(CodeErrorConfig.CODE_TIMEOUT, null);
                        }
                    } else if (value != null && currentTime - value.sendTime > 1000) {
                        value.sendTime = currentTime;
                        boolean isSend = SwiDataLinkManager.getInstance().sendDataByDataLink(value.msgArray);
                        if (!isSend) {
                            if (msgCallback != null) {
                                iterator.remove();
                                msgCallback.callback(CodeErrorConfig.CODE_SEND_FAILED, null);
                            }
                        }
                    }
                }
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            interrupt();
        }
    }


    public void destroyCache() {
        if (cacheHashMap != null) {
            cacheHashMap.clear();
            cacheHashMap = null;
            interrupt();
        }

        if (parseDataPackage != null) {
            parseDataPackage.destroyParseData();
            parseDataPackage = null;
        }
    }
}
