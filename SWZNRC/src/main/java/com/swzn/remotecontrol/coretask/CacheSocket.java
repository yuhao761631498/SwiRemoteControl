package com.swzn.remotecontrol.coretask;

import java.nio.channels.spi.AbstractSelectableChannel;

/**
 * Created by xx on 2018-5-30.
 */

public class CacheSocket
{
    public CacheSocket(AbstractSelectableChannel abstractSelectableChannel,long lastUpdateTime)
    {
        this.abstractSelectableChannel = abstractSelectableChannel;
        this.lastUpdateTime = lastUpdateTime;
        this.canBreak = false;
    }


    public CacheSocket(AbstractSelectableChannel abstractSelectableChannel,long lastUpdateTime,boolean canBreak)
    {
        this.abstractSelectableChannel = abstractSelectableChannel;
        this.lastUpdateTime = lastUpdateTime;
        this.canBreak = canBreak;
    }

    public AbstractSelectableChannel abstractSelectableChannel;

    public long lastUpdateTime;

    public boolean canBreak;

    /***********************
     * 下载的URL  --xx
     */
    public String downLoadURL;
}
