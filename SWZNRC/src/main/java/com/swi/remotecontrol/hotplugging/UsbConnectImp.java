package com.swi.remotecontrol.hotplugging;

import com.swi.remotecontrol.ZOComment;
import com.swi.remotecontrol.ZOConstants;

/**
 * Created by liangzi on 2016/12/1.
 */

public class UsbConnectImp implements IConnectUav {
  @Override
  public void setConnectInfo() {
    setHttpInfo();
    setUdpInfo();
    setFtpInfo();
  }

  @Override
  public void setHttpInfo() {
    ZOComment.httpHost = ZOConstants.USB_HOST_HTTP;
    ZOComment.urlPic = ZOConstants.URL_PIC;
    ZOComment.urlPicPage = ZOConstants.URL_PIC_PAGE;
    ZOComment.picDelete = ZOConstants.PIC_DELETE;
    ZOComment.videoDelete = ZOConstants.VIDEO_DELETE;
    ZOComment.urlVideo = ZOConstants.URL_VIDEO;
    ZOComment.urlVideoPage = ZOConstants.URL_VIDEO_PAGE;
  }

  @Override
  public void setUdpInfo() {
    ZOComment.udpIp = ZOConstants.LOCAL_IP;
    ZOComment.udpPort = ZOConstants.LOCAL_PORT_UDP;
  }

  @Override
  public void setFtpInfo() {
    ZOComment.ftpIp = ZOConstants.LOCAL_IP;
    ZOComment.ftpPort = ZOConstants.LOCAL_PORT_FTP_CMD;
  }
}
