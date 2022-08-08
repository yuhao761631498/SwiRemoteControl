package com.swi.remotecontrol.hotplugging;

import com.swi.remotecontrol.ZOComment;
import com.swi.remotecontrol.ZOConstants;

/**
 * Created by liangzi on 2016/12/1.
 */

public class WifiConnectImp implements IConnectUav {
  @Override
  public void setConnectInfo() {
    setHttpInfo();
    setUdpInfo();
    setFtpInfo();
  }

  @Override
  public void setHttpInfo() {
    ZOComment.httpHost = ZOConstants.WIFI_HOST_HTTP;
    ZOComment.urlPic = ZOConstants.WIFI_URL_PIC;
    ZOComment.urlPicPage = ZOConstants.WIFI_URL_PIC_PAGE;
    ZOComment.picDelete = ZOConstants.WIFI_PIC_DELETE;
    ZOComment.videoDelete = ZOConstants.WIFI_VIDEO_DELETE;
    ZOComment.urlVideo = ZOConstants.WIFI_URL_VIDEO;
    ZOComment.urlVideoPage = ZOConstants.WIFI_URL_VIDEO_PAGE;
  }

  @Override
  public void setUdpInfo() {
    ZOComment.udpIp = ZOConstants.WIFI_UAV_HOST_IP;
    ZOComment.udpPort = ZOConstants.WIFI_UDP_FLY_CONTROL_PORT;
  }

  @Override
  public void setFtpInfo() {
    ZOComment.ftpIp = ZOConstants.WIFI_UAV_HOST_IP;
    ZOComment.ftpPort = ZOConstants.WIFI_FTP_PORT;
  }
}
