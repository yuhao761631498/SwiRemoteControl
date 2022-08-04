
package com.swzn.config;
/**
 * Created by shangbeibei on 2017/4/21.
 * <p>全局静态变量</p>
 */

public class UavStaticVar {
    //20180101-V1.4版本-无人机实名登记变量
    public static volatile boolean SN_IS_REGISTER; // SN 飞机是否注册过
    public static volatile String MobileUUid; // 获取用户手机的UUID
    public static volatile String UASID; // 登记标识 UAS 编号
    public static volatile boolean isCN = true; // 是否在国内
    public static volatile boolean isConnNetWork_UAV_Register; // 无人机实名认证【专用】  是否有网络连接
    //V1.3-直播和语音控制功能
    public static volatile boolean isOpenVoiceControl = false; // 是否打开语音功能
    public static volatile int CUR_VOL_VALUE = 0; // 当前音量值（0-100）
    public static volatile boolean isOfflineVoice = true; // 是否是离线语音
    public static volatile boolean isOpenVideoLive; // 是否打开视频直播
    //是否存在 虚拟按键
    public static volatile boolean isHasNavigationBar; // 是否有虚拟按键
    // 是否打开调试环境，分为线上和测试环境
    public static boolean isOpenTextEnvironment; //开发中，true。上线 false
    public static boolean isUploadVDR = false; //必须和测试环境分开,是否上传黑匣子日志
    // 静态变量 参数设置
    public static int screenWidth = 1000; // 屏幕宽
    public static boolean isMetric = true; // 是否公制
    public static boolean SetMapStyle = true;//设置地图类型
    public static volatile boolean isSwitchMap;// 当前是否切换到地图页面
    public static volatile boolean isDisplayFlightTrack = false; // 显示轨迹s
    //    public static volatile LatLng droneCurLatLng;
    public volatile static short Pf_FlightHei = 20; //无人机的飞行高度  ----单位高度是分米，默认是20m---xx
    public static boolean isDownloading = false; // APP最新版本 是否正在下载中，如果APP 退出就不管了
    //APP 版本升级 信息
    public static int RemoteVersionsCode;//最新版本 的版本 Code   【21】
    public static String RemoteVersions;//最新版本 的版本号 【V2.0.0】
    public static String RemoteVersion_Download_url;//当前新版本的url地址(可直接用)
    public static String RemoteVersionName;//当前新版本的的文件名字
    public static String RemoteVersionLog;//当前新版本的日志信息
    //2.0 上传服务器的必要参数
    public static String CountryType;// 当前的国家类型
    public static String LanguageType;// 当前的语言类型
    public static String Android_Version;//设备系统版本  ps:  6.1.1
    public static String Android_Device_Model;//设备型号 ps:  MI 5
    public static String AppVersions;// APP 当前版本号   ps: 2.0.0
    public static String Device_id;//设备ID号
    public static final String MGP03 = "3";// 需要上传的 Progject 类型 是MGP01请求
    public static final String ANDROID = "1";// int类型，1:"android"；2:"iphone"
    public static boolean isOpen3G;
    public static boolean isAmap;
    public static boolean isMapType;
    public static int MaxHeight;
    public static int MaxDistance;

}

