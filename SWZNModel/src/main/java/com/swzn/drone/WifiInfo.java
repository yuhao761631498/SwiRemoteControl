package com.swzn.drone;

import java.io.Serializable;

/**
 * Created by xx on 2017/12/28.
 */

public class WifiInfo implements Serializable {
    private String wifiSSID;
    private String wifiPassword;
    private int wifiRSSI;

    public String getWifiSSID() {
        return wifiSSID;
    }

    public void setWifiSSID(String wifiSSID) {
        this.wifiSSID = wifiSSID;
    }

    public String getWifiPassword() {
        return wifiPassword;
    }

    public void setWifiPassword(String wifiPassword) {
        this.wifiPassword = wifiPassword;
    }

    public int getWifiRSSI() {
        return wifiRSSI;
    }

    public void setWifiRSSI(int wifiRSSI) {
        this.wifiRSSI = wifiRSSI;
    }
}
