package com.swzn.drone;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xx on 2017/12/31.
 */

public class WifiResult implements Serializable {
    private List<String> wifi;
    private List<String> signal;

    public List<String> getWifi() {
        return wifi;
    }

    public void setWifi(List<String> wifi) {
        this.wifi = wifi;
    }

    public List<String> getSignal() {
        return signal;
    }

    public void setSignal(List<String> signal) {
        this.signal = signal;
    }
}
