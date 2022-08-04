package com.swzn.drone;

/**
 * Created by xx on 2017-10-11.
 */

public class WifiChannelBean
{
    /***********
     * 是否是飞机的WIFI
     */
    public boolean isDroneWifi;

    /***************
     * wifi信道
     */
    public byte wifiChannel;

    /*******************
     * wifi信道强度
     */
    public int wifiChannelPower = 100;

    public void addWifiChannelPower(int wifiChannelPower)
    {
        this.wifiChannelPower -=wifiChannelPower;
        if(this.wifiChannelPower < 1 )
            this.wifiChannelPower = 1;
    }

    /********************
     *  wifi信道的信燥比
     */
    public int wifiChannelQuality;

    public void clear()
    {
        this.isDroneWifi = false;
        this.wifiChannelPower = 100;
        this.wifiChannelQuality = 0;
    }
}
