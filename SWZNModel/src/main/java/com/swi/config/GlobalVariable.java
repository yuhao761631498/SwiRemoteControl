package com.swi.config;


import android.Manifest;


import com.swi.drone.PlanType;
import com.swi.AlgorithmMark;
import com.swi.drone.BatteryType;
import com.swi.drone.ControlHand;
import com.swi.drone.FourthGStatus;
import com.swi.drone.GimbalType;
import com.swi.drone.LightType;
import com.swi.drone.RTKNetConnectStatus;
import com.swi.drone.RTK_Model;
import com.swi.drone.WifiChannelBean;
import com.swi.drone.Z4bBatteryState;
import com.swi.drone.Z4bRfState;
import com.swi.mount.GasBean;
import com.swi.mount.GasRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xx on 2017/2/21.
 * <p>保存一些全局变量的信息</p>
 */
public class GlobalVariable {

    public static final int QR_CODE_SCAN = 100;
    /*****************
     * 是否正在校磁中
     */
    public static boolean rectifyMagnetometerIng;

    /*****************
     * 上一次的刷新状态---xx
     * 本来是放到ZorroRealControlActivity里面的。结果有反馈说，重复进去该页面，会重复提示信息
     */
    public static byte lastHomeState;

    /******************************
     * 是否已经对比特率做了切换----xx
     * 0：未做码流切换
     * 1.码流缩小了
     */
    public static boolean hadChangeByte;

    /****************
     * RC扫描的信道信息
     */
    public static List<WifiChannelBean> channelBeenData;

    /***************************
     * 接受图传的端口号-----xx
     */
    public static int UDPSocketIMGPort = 7078;

    public static int UDPSocketIMGPort2 = 7075;


    /**********************************
     * 接受数传的端口号-----xx
     */
    public static int UDPSOCKETPORT = 0;

    /**
     * 是否已经插入SD卡   余浩
     */
    public static boolean insertSD = false;

    /**
     * 云台是否已经安装  没收到反馈就是没安装 余浩
     */
    public static boolean ExitHolder = false;

    /**
     * 由于性能较差的手机在进入九宫格页面的时候都会有较大延迟，此时点击返回键都会崩溃  ，所以延时执行退出的动作  余浩
     */
    public static boolean CanDelayExit;


    public static boolean isFocus = false;
    public static boolean isIrKeepingPhotoChosen = false;
    public static boolean isIrKeepingPhotoTaking = false;
    public static boolean isIrVisibleLight = false;
    /*判断三光云台是否为带测温版本*/
    public static boolean isTmsWithThermometry = true;
    public static boolean isFirmwareUpdateShowing = false;

    public static LightType mCurrentLightType = LightType.VISIBLE_LIGHT;   //当前主屏显示的光类型 默认可见光
    /**
     * 是否为延时拍照
     */
    public static boolean isIrDelayPhoto = false;


    /**
     * 靠近电子围栏边界
     */
    public static boolean nearElectronicRail;

    /**
     * 像素密度
     */
    public static float density;

    /**
     * 是否需要返航
     */
    public static boolean isReturn;

    /**
     * 是否是智图汇连图传z4b;
     */
    public static boolean isZ4b_zt;

    /**
     * 当前推流ID
     */
    public static int sCurrentPushStreamId = 0;

    /***************
     * <p>初始化一些状态</p>
     */
    public static void init() {
        isShowRemoteControl = false;
    }


    /******************************
     * 已经靠近警示区了-----xx
     *****************************/
    public static boolean HadNearWarnZone;

    /**
     * 禁飞区域内   余浩
     */
    public static boolean HadInNoFly;

    /*****************
     * Sd的总容量
     * 单位:10M
     */
    public static float SdCardSum = 0;

    /**
     * SD卡的已用容量
     * 单位：10M
     */
    public static float reMainCardSum = 0;

    /*****************
     * 三光云台Sd2的总容量
     * 单位:10M
     */
    public static float Sd2CardSum = 0;

    /**
     * 三光云台SD卡2的已用容量
     * 单位：10M
     */
    public static float reMainSD2Sum = 0;

    /**********************
     * <p>是否显示避障雷达</p>
     */
    public static boolean hadShowObstacle = false;

    /*******************************
     * <p>屏幕宽度</p>
     */
    public static int screenWidth;

    /*********************
     * 每次App启动后，都要检查是否需要校磁
     * 0x01：需要校磁
     * 0x02：已经提示用户了。
     */
    public static byte magnetometerNeedCalibration;

    /********************
     * <p>屏幕的高度</p>
     */
    public static int screenHeight;

    /*********************
     * 屏幕的真正高度---xx
     */
    public static int screenRealHeight;

    /**********************
     * <p>视频流的分辨率</p>
     * <ul>
     *     <li>1:640*480</li>
     *     <li>2:1280*720</li>
     *     <li>3:1920*1080</li>
     * </ul>
     */
    public static byte FPVType = 3;

    /************
     * 云台版本号（飞机端实时）
     */
    public static double gimbelVersion = 0;

    /**
     * 电池版本号
     */
    public static double batteryVersion;


    /**
     * @author xx
     * 变倍连拍是否开启
     */
    public static boolean changePhotoSize = false;


    /**
     * @author xx
     * <p>
     * 连拍张数
     */
    public static byte numPhoto = (byte) 0x03;

    /**
     * @ahthor xx
     * 连拍开关
     */
    public static boolean continuePhoto = false;

    /**
     * @author xx
     * 定时连拍开关
     */
    public static boolean timerContinuePhoto = false;


    /**
     * @author xx
     * 定时连拍的张数
     */
    public static int timerPhotoNum = 5;

    /**
     * @author xx
     * 间隔时间
     */
    public static int time = 2;

    /**
     * @author xx
     * <P>慢动作Mode</P>
     */
    public static boolean SlowVideoMode = false;

    /**
     * @author xx
     * <P>延时摄影Mode</P>
     */
    public static boolean DelayVideoMode = false;

    /**
     * @author 余浩
     * 慢动作录像时间比例
     */
    public static byte slowFold = (byte) 0x10;

    /**
     * @author xx
     * <p>
     * 快动作录像的时间比例
     */
    public static byte qulickFold = (byte) 0x01;

    /**
     * 飞机的返航高度  xx
     */
    public static short backHeight = -1;


    /**
     * 全景拍照的开关  余浩
     */
    public static boolean fullViewPhoto;

    /**
     * s是否正在录像 余浩
     */
    public static boolean isRecording;

    /**
     * 飞机的版本号  余浩
     */
    public static String planeVersion;

    /*******************
     * 当前是录像模式，还是拍照模式 ----xx 20180503 补上说明
     */
    public static boolean isPhoto;

    /**
     * 飞机小版本号  余浩
     */
    public static byte bigVersion;

    /**
     * 飞机大版本号 余浩
     */
    public static byte smallVersion;

    /**
     * 飞机固件新版本md5
     */
    public static String flyFirmwareMd5;

    /**
     * 云台固件新版本md5
     */
    public static String gimbalFirmwareMd5;

    /**
     * 电池固件新版本md5
     */
    public static String batterFirmwareMd5;

    /**
     * RCa新版本md5
     */
    public static String RCaFirmwareMd5;

    /**
     * 系统固件新版本md5
     */
    public static String OTAFirmwareMd5;

    /**
     * 图传中继固件新版本md5
     */
    public static String AP12FirmwareMd5;

    /**
     * 图传中继固件新版本md5
     */
    public static String ComputeStickFirmwareMd5;

    /**
     * OTA版本号  余浩
     */
    public static long OTAVersion;

    /**
     * 计算棒版本
     */
    public static String ComputeStickVersion;

    /**
     * 电池厂家
     */
    public static BatteryType factoryCode;
//    /**
//     * ap12的版本号  余浩
//     */
//    public static double AP12Version;

    /**
     * 是否开启航迹规划  余浩
     */
    public static boolean isOpenFlightRoutePlan = false;

    /**
     * 是否开启航电子围栏  余浩
     */
    public static boolean isOpenElectronicRail = false;

    /**
     * <P>飞行器SN码</P>
     */
    public static String SN;

    /**
     *  电池SN
     */
    public static String sBatterySN;

    /**
     * 云台SN
     */
    public static String sGimbalSN;

    /**************
     * 当前的控制手
     */
    public static ControlHand controlHand = ControlHand.HAND_NONE;

    /************
     * 直连
     * 的时候，手机可以操作飞机的开关
     */
    public static boolean isConnDrone = true;


    /********xx*********
     * 飞机的飞行模式
     *  0x00运动模式
     *  0x01普通模式
     ********************/
    public static byte DroneFlyMode;

    /*******RON**************
     * 延迟拍照的时间
     * 0：none
     * 1:3
     * 2:5
     * 3:10
     */
    public static int DelayPhotoPosition = 0;

    /************xx******************
     * 连接类型
     * MGP03_RC_USB 遥控器USB连接
     * MGP03_WIFI   直连
     * MGP03_NONE   未连接
     *********************************/
    public enum ConnType {
        MGP03_WIFI,
        MGP03_RC_USB,
        MGP03_NONE
    }

    /**********xx***********
     * 飞机连接的状态
     ************************/
    public static ConnType connType = ConnType.MGP03_RC_USB;

    /*********xx**********
     * 遥控器是否已经连接 0：未连接 1：连接
     **********************/
    public static byte RC_usb_hadConn = 0;

    /***************xx***********
     * 是否开启雷达避障
     *****************************/
    public static boolean Open_Obstacle = false;

    /**
     * <P>shang</P>
     * <P>是否开启保护罩模式</P>
     */
    public static boolean isOpen_Protection_Mode = false;

    /***********xx*********
     * 当前的算法标识
     ***********************/
    public static AlgorithmMark.AlgorithmType algorithmType = AlgorithmMark.AlgorithmType.NONE;

    /******************************
     * 避障模式是开启----xx
     */
    public static boolean obstacleIsOpen;

    /***********************
     * 返航避障
     ***********************/
    public static boolean obstacleReturnIsOpen;

    /***********************
     * 后退避障
     ***********************/
    public static boolean obstacleBackIsOpen;

    /*****************xx******************
     *  显示为英尺
     **************************************/
    public static boolean showAsInch;

    /**************xx***********
     * 是否是智能模式
     ****************************/
    public static boolean isSmartModel;

    /********************************
     * 标记是否选择了smart模式或者经典模式---xx
     */
    public static boolean remandSelectSmartOrClassModel;

    /*****************xx*****************
     * 飞机的当前飞行状态
     *  <ul>
     *      <li>0:飞行中</li>
     *      <li>1:地面上</li>
     *      <li>2:起飞中</li>
     *      <li>3:降落中</li>
     *      <li>4:悬停中</li>
     *      <li>5.光流迫降</li>
     *  </ul>
     *************************************/
    public static byte droneFlyState = 1;

    /*******************
     * 返航状态-----xx
     * 2 返航中
     ******************/
    public static byte backState = 0;

    /**
     * 模拟飞控还是真实飞控
     */
    public static boolean flyControlSimulation=false;

    /*******************cardview
     * 定位方式
     ******************/
    public static byte locationed_type = 0;

    /*****************xx****************
     * 飞机是否在室内 ---true室内模式，false室外模式
     ************************************/
    public static boolean droneIsIndoor;

    /****************
     * 飞机当前的纬度 --xx
     *******************/
    public static double latitude;

    /************************
     * 飞机当前的经度 --xx
     ************************/
    public static double longitude;

    //************************xx---警告标示********************

    /**
     * <p>警告信息 -- xx</p>
     * <p>飞行电量低</p>
     */
    public static boolean isShowAircraft;//状态栏告警提示  飞行电量低

    /**
     * <p>警告信息 -- xx</p>
     * <p>遥控器电量低</p>
     */
    public static boolean isShowRemoteControl;//状态栏告警提示 遥控器电量低，

    /**
     * <p>警告信息 -- xx</p>
     * <p>GPS信号弱</p>
     */
    public static boolean isShowGPS;//状态栏告警提示  GPS信号弱

    /**
     * 环境磁干扰大   余浩
     */
    public static boolean isEnvironmentAbnormal;   //环境磁干扰大

    /**
     * <p>警告信息 -- xx</p>
     * <p>内存是否已满</p>
     */
    public static boolean isShowMemeryIsFull;//内存是否已满
    //*********************************************************

    /**
     * 是否在预览中
     */
    public static boolean isPreviewing;


    /**
     * 是否飞机自检项存在异常
     */
    public static boolean isShowInspection;   //自检状态异常


    /*=====================================begin=====================================*/

    /**********************
     * 当前飞行器的高度-- xx
     * 单位cm
     */
    public static int height_drone;


    /**********************
     * 当前飞行器的海拔高度-- xx
     * 单位m
     */
    public static float altitude_drone;

    /**
     * 地面的海拔高度
     */
    public static float altitude_drone_land;

    /******************
     * 当前飞机的电量---xx
     */
    public static byte power_drone = -1;

    /**
     * 电压
     */
    public static short powerV;

    /**
     * 当前遥控器的电量
     */
    public static int power_rc = -1;

    public static String SN_CODE_RN;

    /*************
     * 飞行器的卫星颗数 --xx
     */
    public static byte satellite_drone;

    /*********************************
     * 飞行器的异常状态 -- xx
     *  <p>zero版本的飞机的自检的结果是int类型 ----xx</p>
     */
    public static int CheckSelfResult;

    /***************************
     * 飞机自检异常状态-----xx
     */
    public static byte CheckSelfResult_C;

    /****************************
     * 算法开启状态---xx
     */
    public static byte AlgOpenType;

    /*************
     * 飞行器的航向角 ---xx
     */
    public static short planeAngle;

    /**
     * 飞机的地理偏角  余浩
     */
    public static float mapAngle;

    public static float mapRotate;

    /************
     * 飞行器是否已经上锁 --- xx
     */
    public static boolean planeHadLock = true;

    /****************
     * 飞机是否正处在禁飞区内
     */
    public static boolean planeHadInNoFly;

    /***********
     * 飞机是否处在围栏之外
     */
    public static boolean planeHadOutRail;

    /*=====================================end=====================================*/

    /**
     * <p>当前飞机的连接状态 --- xx</p>
     */
    public static ConnStateEnum connStateEnum = ConnStateEnum.Conn_None;

    /**
     * <p>当前飞机的类型 ---shang</p>
     */
    public static PlanType planType = PlanType.O2Plan_SagaPro;

    /**
     * <p>当前飞机的云台类型 -Byrd——t</p>
     */
    public static GimbalType gimbalType = GimbalType.ByrdT_None_Zoom;

    /*******************************
     *  飞机的飞行距离,单位M ---xx
     */
    public static short flyDistance;

    /**
     * 距离限制  余浩   单位 ：M
     */
    public static short limitDiatsnce;

    /****************************
     * 限制高度----xx,单位M
     */
    public static short limitHeight;

    public static boolean stopPreViewByUser;

    /************************************8
     * 达到限制距离的提示是否已经达到了----xx
     */
    public static boolean hadTipLimitDistanseHadGet;

    /************************************88
     * 到达限制高度的提示音已经达到了-----xx
     */
    public static boolean hadTipLimitHeightHadGet;

    /**********************************************
     * 目前飞机能否起飞，这个结果由飞控告诉App ---xx
     */
    public static boolean droneCanFly;

    /***********************************调试信息*************************************/
    public static short xekf_VelX;
    public static int xekf_VelY;
    public static short xekf_VelD;
    public static double GPS_Lat = -1;
    public static double GPS_Lon = -1;
    public static double GPS_Lat_valid = -1;
    public static double GPS_Lon_valid = -1;
    public static short GPS_VELX;
    public static short GPS_VELY;

    public static byte DroneTemp;

    /**************************
     * 飞机的俯仰角
     */
    public static int pitchAngle;

    /************************
     * 飞机的Roll轴的角度
     */
    public static int rollAngle;

    /***********************
     * <p>飞机的飞行状态---xx</p>
     * 0为姿态模式
     * 1为GPS模式
     */
    public static byte flyMode;


    public static float FlyControlV;//飞控电压

    //震动系数
    public static byte Shock;

    /******************
     * 晃动系数 -----xx
     */
    public static byte Shaking;

    //飞控版本
    public static int flyVersion;

    //内核版本
    public static int insertVersion;

    public static int CSVersion;

    public static int CEVersion;

    //超声波版本
    public static int UltrasonicVersion;

    //光流版本
    public static int OpticalFlowVersion;

    public static int faceVersion;

    public static int trackVersion;

    public static int EISVersion;

    /****
     * <p>gps质量</p>
     */
    public static short GPSQuality;

    /***************************
     * 云台的yaw角度----xx
     */
    public static short HolderYAW;

    /**********************
     * 云台的Roll轴 ---xx
     */
    public static short HolderRoll;

    /******************************
     * 云台的Pitch的轴 ---xx
     ******************************/
    public static short HolderPitch;

    /**********************************
     * 云台的横滚校准值----xx
     ************************************/
    public static byte HolderSmallRoll = 30;

    /**
     * 俯仰最大波轮速度值
     */
    public static byte thumbWheelSpeed = 40;

    /***********************
     * 云台出现云台阻塞
     ***********************************/
    public static byte HolderIsErr;

    /**
     * 外挂云台变倍倍数
     */
    public static byte HolderZoom;

    /*********************************
     *  gps跟踪已经收到反馈了
     *  -2 未接受的反馈数据
     *  -1 失败
     *  0 成功
     */
    public static byte GPSTrackHadBack = -2;

    /**************************
     * 手机端的经度-----xx
     **************************/
    public static int LonPhone;

    /**************************
     * 手机端的维度-----xx
     **************************/
    public static int LatPhone;

    /**************************
     * 档位信息
     **************************/
    public static byte gearMode;

    public static short version_code;

    /*****************************
     * 获取的PPS和sps的索引号----xx
     */
    public static byte ppsspsIndex = -1;

    /***************************
     *  协议版本号---------xx
     */
    public static byte protocalVersion;

    /*******************************
     * 心跳发送的PPS和 sps,发现视频流的分辨率和 发送来的分辨率 不一样，所以
     * 副本保存的 PPS都直接用心跳的
     */
    public static byte heartPpsSps = 0;

    /********************
     * wifi的延迟是否过大----xx
     */
    public static boolean wifiDelay;

    /**
     * AGPS是否有效的标志位 余浩
     * <p>
     * 0:发送升级AGPS指令失败   1：AGPS有效  2：下载AGPS失败   3：上传AGPS失败  4：其它  5：指令发送成功但飞机返回失败
     */
    public static int testAgps = 4;

    /**
     * 单电芯报警标志位 余浩
     */
    public static boolean isBatteryCellWaring;

    public static boolean ISEXIT = false;

    public static boolean isExitPic = false;

    public static boolean is_4K_Video = false;
    /**
     * 标记是否是正常启动，非正常启动方式目前有两种：
     * 1.长时间挂在后台，导致系统回收资源而杀掉进程
     * 2.app运行时，去改变app的权限而导致体统杀掉进程
     * 系统会保持app的最后一个activity及部分资源，
     */
    public static boolean isNormalBoot = false;

    /**
     * 新建AOA的标志位  余浩
     */
    public static boolean isRestartAoa = false;
    /**
     * 是否支持超清解码  余浩
     */
    public static boolean isSupprotFD = true;

    /**
     * 判断是否有新的系统版本
     */
    public static boolean isNewOTAVersion = false;

    /**
     * 是否处于格式化中
     */
    public static boolean isFormating = false;

    /**
     * 获取位置权限的request code
     */
    public static final int MY_PERMISSIONS_REQUEST_GETLOCATION = 909;

    public static final String UPGRADE_TYPE = "upgrade_type";

    public static final String UPDATE_FIRMWARE = "update_firmware";

    public static final String MAIN_JUMP_TYPE = "main_jump_type";

    public static final String LIVE_TYPE = "live_type";

    public static final int JUMP_TO_EXPLORE = 0x03;  //跳转到探索页面

    public static final int JUMP_TO_UPGRADE = 0x04;  //跳转到升级页面

    public static final int RESULT_TO_LIVE = 0x05;   //跳转到实时控制页的直播

    public static final int RESULT_TO_CHECKCLOUD = 0x1026;//校漂的

    public static final int JUMP_TO_WEIBO_AUTH = 32973; //从微博授权页面跳回

    public static final int JUMP_TO_FACEBOOK_AUTH = 64206; //从facebook授权页面跳回

    public static final int MAIN_TO_REALCONTROL = 1;

    public static int zoom30x_RecordResolutionIndex = 3;
    public static int zoom30x_PhotoResolutionIndex = 1;
    public static int zoom30x_VideoCompressIndex;
    public static int zoom30x_AntiFlickerIndex;
    public static boolean zoom30x_AntiFog;
    public static int zoom30x_AntiFog_Index;
    public static int zoom30x_ColorIndex;
    public static int zoom30x_ExposureIndex;
    public static int zoom30x_ExposureIntensity;
    public static boolean zoom30x_AntiShake;
    public static boolean IR_isFullMeasure = true;


    public static int arlink_dataValid;
    public static int arlink_linkStatus;
    public static int arlink_imgStatus;
    public static int arlink_grdSignalQuality;//信号强度
    public static int arlink_skySignalQuality;//信号强度
    public static int arlink_grdAEnergy;
    public static int arlink_grdBEnergy;
    public static int arlink_skyAEnergy;
    public static int arlink_skyBEnergy;

//    public static int

    /***
     * 当前所在国家的国家码（大写）
     */
    public static String countryCode = "CN";

    public static boolean isKuXin = false;

    public static final String[] getAppNeedPermissions() {
        String[] permissions = new String[4];
        permissions[0] = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        permissions[1] = Manifest.permission.READ_EXTERNAL_STORAGE;
        permissions[2] = Manifest.permission.ACCESS_FINE_LOCATION;
        permissions[3] = Manifest.permission.ACCESS_NETWORK_STATE;
//        permissions[4] = Manifest.permission.RECORD_AUDIO;//saga 不需要录音权限
        return permissions;
    }

    /**
     * 用于黑匣子飞行记录
     */
    public static byte[] frame_contentBC;

    public static byte[] frame_contentBD;

    /**
     * 全景拍照的开关  余浩
     * 0 关闭
     * 1 竖拍
     * 2 横排180
     */
    public static int AllViewPhoto = 0;

    public static byte[] matchID = null;


    /*无人机动力饱和 等于7时*/
    public static int droneFullPower = 0;

    /**
     * Z4B：RTK状态(
     * 0:信号弱；
     * 1:1D
     * 2:2D
     * 3:3D
     * 4:float
     * 5:fix
     */
    public static int Z4bRTK_Status = 0;

    /**
     * RTK 类型
     * 0：无效值
     * 1: 飞机板网络RTK
     * 2: 手机版网络RTK
     * 3: 基站版RTK
     * 4: 千寻SDK RTK
     */
    public static int sRTKType = 0;

    /**
     * 是否为千寻SDK RTK
     */
    public static boolean isQXSdkRtk = false;

    /**
     * 手机版RTK状态 0:不在线，1在线
     */
    public static int sPhoneRTKStatus;
    /**
     * 基站版RTK状态 0:不在线，1在线
     */
    public static int sBSRTKStatus;
    /**
     * 飞机板RTK状态  0:不在线，1在线
     */
    public static int sDroneRTKStatus;

    /********************
     * 只有带有RTK的设备才有效
     */
    public static RTK_Model rtk_model = new RTK_Model();

    /**
     * rtk 模块2的卫星颗数
     */
    public static int Satellite_rtk2;

    public static boolean addLATLON2Pic = false;

    public static Z4bRfState z4bRfState = new Z4bRfState();

    public static Z4bBatteryState z4bBatteryState = new Z4bBatteryState();

    public static FourthGStatus sFourthGStatus = new FourthGStatus();

    /**
     * 计算棒加载状态
     */
    public static boolean isComputerStickConnected = false;

    /**
     * 当前的航迹飞行模式
     */
    public static RoutePlaneType flightRutePlaneMode = RoutePlaneType.FLIGHT_NONE;

    /**
     * 是否需要启动快速拼图
     */
    public static boolean isFastMappingByWeb = false;

    /**
     * 算法检测的开关是否已经打开
     */
    public static boolean discernIsOpen = false;

    public static String routePathSave;

    /**
     * 接受到千寻差分数据的个数
     */
    public static int sReceiveRTCMNum;

    /**
     * 接受到GGS数据的个数
     */
    public static int sReceiveGGANum;

    /**
     * gga 数据
     */
    public static String sReceiveGGAData;

    /**
     * 请求RTK握手指令
     *  0:无效值 1：开启 2：关闭
     */
    public static int  sRequestGGAData = 0;

    /**
     * GGA的最新长度
     */
    public static final int sMinGGANum = 50;

    /**
     * RTK连接状态
     */
    public static RTKNetConnectStatus sRTKConnectStatus = RTKNetConnectStatus.DISCONNECT;


    public static int power_drone_1 = 0;
    public static int power_drone_2 = 0;

    public static String x_speed_str = "0";
    public static String y_speed_str = "0";
    public static String height_str = "0";

    /**
     *  气体检测
     */
    public static List<GasRecord> sGasRecord;
    public static List<GasBean> MountedGas = new ArrayList<>();

    public static boolean isAI;
    public static boolean isG4;
    public static boolean isModleEdge;
    /**
     * 是否连接RTK
     */
    public static boolean isConnectedRTK = false;

    /**
     * 801温度
     */
    public static int sDrone801Temperature;


    /**
     * 当前选择的航迹类型
     */
    public static RoutePlaneType sRoutePlaneType;

    /**
     * GB28181注册状态
     */
    public static boolean sGB28181Register;

    /**
     * GB28181推流状态
     */
    public static boolean sGB28181Stream;

}
