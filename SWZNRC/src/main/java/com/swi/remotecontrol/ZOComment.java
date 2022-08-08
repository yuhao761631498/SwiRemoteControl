package com.swi.remotecontrol;

/**
 * Created by liangzi on 2016/12/1.
 */

public class ZOComment {
  //默认wifi连接
  public static volatile String udpIp = ZOConstants.WIFI_UAV_HOST_IP;
  public static volatile int udpPort = ZOConstants.WIFI_UDP_FLY_CONTROL_PORT;

  public static volatile String ftpIp = ZOConstants.WIFI_UAV_HOST_IP;
  public static volatile int ftpPort = ZOConstants.WIFI_FTP_PORT;

  /**
   * httpHost
   */
  public static volatile String httpHost = ZOConstants.WIFI_HOST_HTTP;
  /**
   * 照片的请求URL
   */
  public static volatile String urlPic = ZOConstants.WIFI_URL_PIC;
  /**
   * 照片请求的拼接URl
   */
  public static volatile String urlPicPage = ZOConstants.WIFI_URL_PIC_PAGE;
  /**
   * 照片删除
   */
  public static volatile String picDelete = ZOConstants.WIFI_PIC_DELETE;
  /**
   * 视频删除
   */
  public static volatile String videoDelete = ZOConstants.WIFI_VIDEO_DELETE;
  /**
   * 视频的请求URL
   */
  public static volatile String urlVideo = ZOConstants.WIFI_URL_VIDEO;
  /**
   * 视频请求的拼接URl
   */
  public static volatile String urlVideoPage = ZOConstants.WIFI_URL_VIDEO_PAGE;
}
