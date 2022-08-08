package com.swi.remotecontrol.hotplugging;

/**
 * Created by liangzi on 2016/12/1.
 */

public interface IConnectUav {
    void setConnectInfo();
    void setHttpInfo();
    void setUdpInfo();
    void setFtpInfo();
}
