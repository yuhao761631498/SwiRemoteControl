package com.swzn.socketmodel;

/**
 * Created by xx on 2016/5/6.
 * Socket一些常理配置
 */
public class SWZNSocketConfig3
{
    /***************
     * socket ip的地址---xx
     * wifi直连
     ***************/

    public static final String IpAdress =  "127.0.0.1";//2.4G
//    public static final String IpAdress =  "172.16.63.239";//2.4G
//    public static final String IpAdress = "192.168.11.124";  //5.8G
//    public static final String IpAdress = "192.168.1.102";

    public static final String APAddress = "192.168.11.123";
    public static final String STAAddress = "192.168.100.1";

    public static final int APPort = 8001;

    public static final int REMOTE_UDP_PORT = 7088;  ////2.4G
//    public static final int REMOTE_UDP_PORT = 7089;  ////5.8G

    /**
     * <p>无效的序列号</p>
     */
    public static final byte InvalidateSerial = -1;

    /**
     * socket的端口---xx
     */
    public static final int IpPort = 3004;

    /**
     * socket的端口，图传的端口--xx
     */
    public static final int IMGIpPort = 7078;

    /**
     * <p>遥控器模块--xx</p>
     */
    public static final byte RCModel = 0x01;

    /**
     * <p>DataLink模块</p>
     */
    public static final byte DataLink = 0x0D;

    /*****
     * <p>飞控模块 --xx</p>
     */
    public static final byte FlyControlModel = 0x02;

    /*****
     * <p>云台模块 --xx</p>
     */
    public static final byte HolderModel = 0x03;


    /**********************************************
     * 图传版的模块---xx
     */
    public static final byte IMGMODEL = (byte) 0x0A;

    /*****
     * <p>相机模块包括CS --xx</p>
     */
        /*拍照录像相关 byrdT*/
    public static final byte CameraModel_byrdT = 0x0C;

    /*拍照录像相关*/
    public static final byte CameraModel = 0x04;

    /*****
            * <p>BirdT相机模块包括CS --xx</p>
        */
    public static final byte CameraModelBirdT = 0x0c;

    /*****
     * <p>综控模块（包含CE） --xx</p>
     */
    public static final byte AllControlModel = 0x05;

    /**
     * @author xx
     * 中继模块 ap12
     */
    public static final byte AP12Model=0x06;

    /******************************
     * Smart功能模块----xx
     */
    public static final byte SmartModel = 0x0B;

    /***
     * <p>固件升级模块</p>
     */
    public static final byte UpdateModel = 0x05;

    /**********************************
     * 智能电池的模块 -----xx
     ***********************************/
    public static final byte BatteryModel = 0x08;

    public static final byte SAGAUpdate = 0x09;

    /**
     * <p>socket链接超时的时间</p>
     */
    public static final int SocketConnTimeOut = 6000;

    /**
     * <p>socket的写线程，等待超时1s后，就发送一个 心跳包</p>
     */
    public static final int WriteWaitTimeOut = 1;

    /**
     * <p>帧的头部</p>
     */
    public static final byte Frame_Head = 0x55;

    /**
     * <p>发送目的地：app</p>
     */
    public static final byte To_App = 0x07;

    /**
     * <p>来源：无人机</p>
     */
    public static final byte To_drone = 0x01;

    /**
     * <ul>
     *     <li>帧头的长度</li>
     *     <li>包含，帧头，长度，</li>
     * </ul>
     */
    public static final byte Frame_Head_length = 0x03;

    /**
     * RTK从千寻服务器获取到的差分数据长度
     */
    public static final int RTK_RTCM_LENGTH = 256;

    /**
     * <ul>
     *     <li>反馈码</li>
     *     <li>ACK成功码</li>
     * </ul>
     */
    public static final byte SUCCESS_CODE = 0x00;

    /**
     * <ul>
     *     <li>反馈码</li>
     *     <li>飞机存储空间已经满了</li>
     * </ul>
     */
    public static final byte MEMERYISFULL_CODE = 0x02;

    /**
     * <ul>
     *     <li>反馈码</li>
     *     <li>没有sd卡</li>
     * </ul>
     */
    public static final byte NO_SD_CODE = 0x09;

    /**
     * <ul>
     *     <li>反馈码</li>
     *     <li>云台禁止发送停止录像指令</li>
     * </ul>
     */
    public static final byte CAN_NOT_STOP_VIDEO = 0x0A;

    /***********
     * <ul>
     *     <li>反馈码</li>
     *     <li>发生失败</li>
     * </ul>
     */
    public static final byte SENDERR_CODE = (byte) 0xFE;

    /***********
     * <ul>
     *     <li>反馈码</li>
     *     <li>发送超时</li>
     * </ul>
     */
    public static final byte SENDTIMEOUT_CODE = (byte)0x01;

    /***********
     * <ul>
     *     <li>反馈码</li>
     *     <li>连接失败</li>
     * </ul>
     */
    public static final byte CONNERR_CODE = (byte)0xFC;


    /*=====================周期反馈的命令字==========================*/

    /**
     * <p>一键起飞，垂直降落，一键返航的回掉 --xx</p>
     */
    public static final byte CycleACK_ControlFly = 0x12;

    /**
     * <p>校次状态的周期反馈 -- xx</p>
     */
    public static final byte CycleACK_checkNorth = 0x11;

    /**
     * 云台校飘的周期反馈  xx
     */
    public static final byte CycleACK_checkClound=0x1c;

    /**
     * <p>拍照的周期反馈 -- xx</p>
     */
    public static final byte CycleACK_takePhoto = 0x24;

    /**
     *  录像的周期反馈
     */
    public static final byte CycleACK_record = 0x26;

    /**
     * 手势识别的周期反馈
     */
    public static final byte CycleAck_guster=0x30;

    /**
     * 视频跟踪和视频环绕的反馈 ---xx
     */
    public static final byte CycleACK_VideoTrack = 0x32;


    /**
     * <p>避障的周期反馈命令字 --xx</p>
     */
    public static final byte CycleACK_obscale = 0x37;

    /**
     * <p>全景拍摄的周期反馈 -- xx</p>
     */
    public static final byte CycleACK_panorama = 0x39;

    /**
     * <p>gps跟踪的周期反馈 --xx</p>
     */
    public static final byte CycleACK_gpsTrack = 0x3b;

    /**
     * <p>gps环绕的周期反馈 --xx</p>
     */
    public static final byte CycleACK_gpsSurround = 0x3d;


    /**
     * <p>云台固件升级的反馈 --xx</p>
     */
    public static final byte CycleACK_HolderFMUpdate = 0x65;


    public static final byte CycleAck_BatteryUpdate=0x67;   //余浩

    /**
     * <p>航迹规划的反馈 --xx</p>
     */
    public static final byte CycleACK_pathPlan = 0x73;

    /**
     * @author 余浩
     * 电子围栏的周期反馈
     */
    public static final byte CycleAck_wall=0x76;

    /**
     * 红外云台当前的拍照状态
     * 于浩
     */
    public static final byte CycleAck_ir_photo_state=(byte) 0xd5;

    /**
     * <p>周期反馈心跳反馈  ---xx</p>
     */
    public static final byte CycleACK_Heart =(byte)0x19;

    /***
     * <p>遥控器的周期反馈 ---xx</p>
     */
    public static final byte CycleACK_RC = 0x50;

    /**
     * <p>反馈当前的连接是否有效 ---xx</p>
     */
    public static final byte CycleACK_ConnState = 0x17;

    /*******************************
     *  清理多媒体的反馈 ---xx
     *******************************/
    public static final byte CycleACK_ClearMedia = 0x2E;

    /**
     * 外挂云台反馈
     */
    public static final byte CycleACK_Gimbal = (byte) 0xA2;

    /*************************************
     * 飞机反馈的详情A --- xx
     *************************************/
    public static final byte CycleACK_DroneInfoA = (byte) 0xBA;

    /****************************************
     * 飞机的反馈详情 --- xx
     ****************************************/
    public static final byte CycleACK_DroneInfoB = (byte) 0xBB;
    /*************************************
     * 飞机反馈的详情_BC --- xx
     *************************************/
    public static final byte CycleACK_DroneInfoBC = (byte) 0xBC;

    /****************************************
     * 飞机的反馈详情_BD --- xx
     ****************************************/
    public static final byte CycleACK_DroneInfoBD = (byte) 0xBD;

    /****************************************
     * 飞机的反馈详情 --- ronD
     ****************************************/
    public static final byte CycleACK_DroneInfoD = (byte) 0x8C;

    /*****************************************
     * 云台信息周期反馈 ---xx
     ******************************************/
    public static final byte CycleACK_HolderInfo = (byte)0x81;


    /************************************
     * 更新飞机固件 ---------xx
     ************************************/
    public static final byte CycleACK_UpdateDroneFM = (byte)0x63;

    /*************************************
     * 更新飞机的OTA ----xx
     *************************************/
    public static final byte CycleACK_UpdateDroneOTA = (byte)0x6B;

    /*************************
     * AGPS升级 ------xx
     *************************/
    public static final byte CycleACK_UpdateDroneAGPS = (byte)0x69;

    public static final byte CycleACK_MatchRC = (byte) 0x80;


    /*********************************
     * 斜飞的周期反馈-----xx
     */
    public static final byte CycleACK_Baisc = (byte)0x41;

    /*********************
     * Ap12主动下发的wifi质量 ----xx
     */
    public static final byte CycleACK_WIFIQuality = (byte)0x91;

    /*******************************
     * RC扫描周围WIFI信道的情况----xx
     */
    public static final byte CycleACK_WIFIChannel = (byte)0x6F;


    /**
     * 获取飞机附近的WIFI信号扫描结果
     */
    public static final byte CycleACK_DroneWifi = (byte)0x46;

    /**
     * <p>机头朝前的反馈 --xx</p>
     */
    public static final byte CycleACK_HeadOrientation  = (byte)0x77;

    /**
     * <p>U盘备份的反馈 --xx</p>
     */
    public static final byte CycleACK_USBDiskBackup  = (byte)0x94;

    /**
     * <p>U盘备份的反馈 --xx</p>
     */
    public static final byte CycleACK_TrackTargetType  = (byte)0x34;

    /**
     * <p>低电返航的反馈 --xx</p>
     */
    public static final byte CycleACK_LowBatteryReturn  = (byte)0x5B;

    /*************************
     * 高级航迹规划----xx
     */
    public static final byte CycleACK_SeniorPlanning = (byte)0x49;

    /**
     * <p>气体检测器反馈 --xx</p>
     */
    public static final byte CycleACK_GasDetector  = (byte)0x5e;

    /**
     * <p>DataLink电量反馈 --xx</p>
     */
    public static final byte CycleACK_DataLinkPower  = (byte)0xEC;
    /*==============================================================*/


    /*================双命令字的脚本===================*/


    /************
     * z4b飞机的 图传信息
     */
    public static final short CycleACK_Z4b_IMG_Info = 0x12;

    public static final short CycleACK_Fly_Control_State = 0x33;

    public static final byte CycleACK_TMS_Gimbal_Status = (byte) 0xA1;

    /**
     * 检测到的目标周期反馈
     */
    public static final byte CycleACK_targetDetect = 0x4c;
    /**
     * 检测到的目标周期反馈
     */
    public static final byte CycleACK_fireDetect = (byte) 0x9b;

    /**
     * 目标纠偏周期反馈
     */
    public static final byte CycleACK_targetTrack = (byte)0xb3;

    /**
     * 目标定位的反馈
     */
    public static final byte CycleACK_TargetLocate = (byte)0x4b;

    /**
     * RTK GGA数据反馈
     */
    public static final byte CycleACK_GGA = (byte) 0xAE;

    /**
     * D6B双电池
     */
    public static final byte CycleACK_D6B_Battery = (byte) 0x7F;

    /**
     * 获取计算棒版本反馈
     */
    public static final short CycleACK_ComputeStickVersion = 0x1c;


    /**
     * 4G模块的反馈
     */
    public static final short CycleACK_4G = 0x1b;


    /**
     * ===============================================
     * =================================================================
     * ===========================================3.0协议
     */

    /**=====================================数据类型=========================================//
    /**
     * 组网控制指令
     */
    public static final byte PROTOCOL_TYPE_CONTROL = 0x00;

    /**
     * 消息指令
     */
    public static final byte PROTOCOL_TYPE_MESSAGE = 0x00;

    /**
     * 文件传输协议
     */
    public static final byte PROTOCOL_TYPE_FILE_TRANSFER = 0x02;

    /**
     * 视频数据(一定要流媒体压缩
     */
    public static final byte PROTOCOL_TYPE_VIDEO = 0x03;

    /**
     * Special – 专用于摇杆指令上报
     */
    public static final byte PROTOCOL_TYPE_RC = 0x04;
    /**=====================================数据类型  END =========================================//


     /**=====================================数据标志=========================================//

     /**
     * 请求帧
     */
    public static final byte PROTOCOL_FLAG_REQUEST = 0x000;

    /**
     * 应答帧
     */
    public static final byte PROTOCOL_FLAG_RESPONSE = 0x01;

    /**
     * 状态帧
     */
    public static final byte PROTOCOL_FLAG_STATUS = 0x02;

    /**
     * 上报帧
     */
    public static final byte PROTOCOL_FLAG_REPORT = 0x03;


     /**=====================================数据标志=========================================//



    /**********************************机载******************************************/
    /***********************************系统*****************************************/
    /**
     * 机载系统ID
     */
    public static final  byte ONBOARD_SYSTEMS_ID = 0x01;

    /**
     * 无人机通信协议-NP
     */
    public static final byte ONBOARD_COMP_NPC = 0x00;

    /**
     *  机载系统  飞控组件
     */
    public static final  byte ONBOARD_COMP_FC_ID = 0x01;
    /**
     *  机载系统  视觉组件
     */
    public static final byte ONBOARD_COMP_VISION_ID = 0x02;
    /**
     *  机载系统  图像处理组件 picture processing
     */
    public static final byte ONBOARD_COMP_PP_ID = 0x03;
    /**
     *  机载系统  综控组件 AllControlModel
     */
    public static final byte ONBOARD_COMP_AC_ID = 0x04;

    /**
     *  机载系统  升级组件 　Upgrade
     */
    public static final byte ONBOARD_COMP_UPGRADE_ID = 0x05;

    /**
     *  机载系统  管理组件 Manager
     */
    public static final byte ONBOARD_COMP_MANAGER_ID = 0x06;

    /**
     *  机载系统  多媒体组件 　 multimedia
     */
    public static final byte ONBOARD_COMP_MULTIMEDIA_ID = 0x07;
    /**
     *  机载系统  电池组件 　 battery
     */
    public static final byte ONBOARD_COMP_BATTERY_ID = 0x08;

    /**
     *  机载系统  Z4C电池组件 　 battery
     */
    public static final byte ONBOARD_COMP_Z4C_BATTERY_ID = (byte) 0xC8;

    /**
     *  机载系统  图传 　 image transmission
     */
    public static final byte ONBOARD_COMP_IT_ID = 0x09;
    /**
     *  机载系统  RTK组件 　 RTK
     */
    public static final byte ONBOARD_COMP_RTK_ID = 0x0A;

    /**
     * 机载系统 MCU组件
     */
    public static final byte ONBOARD_COMP_MCU_ID = 0x0B;

    /**
     * 机载系统 RC组件
     */
    public static final byte ONBOARD_COMP_RC_ID = 0x0C;

    /**
     * 机载系统 授时组件
     */
    public static final byte ONBOARD_COMP_TIME_ID = 0x0D;

    /**
     * 任务管理器组件
     */
    public static final byte ONBOARD_COMP_TASK_ID = 0x0E;

    /**
     * 文件传输组件
     */
    public static final byte ONBOARD_COMP_FILE_TRANSFER_ID = 0x11;

    /**
     * 飞控协处理器组件
     */
    public static final byte ONBOARD_COMP_FC_COPROCESSOR_ID = 0x13;

    /**
     * 飞控的基本状态信息1  频率:10HZ
     */
    public static final int CYCLE_ACK_FC_INFO1 = 0x01010001;

    /**
     * 飞控的基本状态信息2  频率:10HZ
     */
    public static final int CYCLE_ACK_FC_INFO2 = 0x01010002;

    /**
     * 飞控IMU数据信息  频率:25HZ
     */
    public static final int CYCLE_ACK_FC_IMU = 0x0101000F;

    /**
     * GNSS星数
     */
    public static final int CYCLE_ACK_FC_GNSS = 0x0101000B;

    /**
     * 飞控降落信息
     */
    public static final int CYCLE_ACK_FC_LAND = 0x01010020;

    /**
     * GPS环绕反馈
     */
    public static final int CYCLE_ACK_GPS_SURROUND = 0x01010040;

    /**
     * IMU校准信息
     */
    public static final int CYCLE_ACK_IMU_CALIBRATION = 0x010100C2;

    /**
     * 电子围栏信息
     */
    public static final int CYCLE_ACK_FC_ELECTRONIC_FENCE = 0x01010090;

    /**
     * 禁飞区ack监听
     */
    public static final int CYCLE_ACK_NON_FLY_ZONE = 0x010100A0;

    /**
     * 精准返航信息
     */
    public static final int CYCLE_ACK_PRECIS_BACK = 0x01020000;

    /**
     * 计算棒周期反馈
     */
    public static final int CYCLE_ACK_CSS = 0x01020001;

    /**
     *双目状态信息上报
     */
    public static final int CYCLE_ACK_BINOCULAR = 0x01020002;

    /**
     *雷达和TOF状态信息上报
     */
    public static final int CYCLE_ACK_RADAR = 0x01020004;

    /**
     * 视觉跟踪
     */
    public static final int CYCLE_ACK_VISION_TRACK = 0x01020010;

    /**
     * 全向避障信息
     */
    public static final int CYCLE_ACK_VISION_OBSTACLE = 0x01020013;

    /**
     * 目标检测周期反馈
     */
    public static final int CYCLE_ACK_TARGET_DETECT = 0x01020015;

    /**
     * 目标定位周期反馈
     */
    public static final int CYCLE_ACK_TARGET_LOCATE = 0x01020016;

    /**
     * 降落保护周期反馈
     */
    public static final int CYCLE_ACK_LANDING_PROTECT = 0x01020021;


    /**
     * Z4B/C电池1信息
     */
//    public static final int CYCLE_ACK_BATTERY_INFO = 0x01080004;

    /**
     * Z4B/C电池2信息
     */
    public static final int CYCLE_ACK_BATTERY2_INFO = 0x01C80004;

    /**
     * 航迹状态信息
     */
    public static final int CYCLE_ACK_TASK_STATUS = 0x010E000A;

    /**
     * 文件传输请求连接协议
     */
    public static final int CYCLE_ACK_FILE_TRANSFER_CONNECT = 0x01110000;

    /**
     * 文件传输断开连接协议
     */
    public static final int CYCLE_ACK_FILE_TRANSFER_DISCONNECT = 0x01110001;

    /**
     * 文件传输请求上传协议
     */
    public static final int CYCLE_ACK_FILE_TRANSFER_REQUEST_UPLOAD = 0x01110007;

    /**
     * 文件传输文件上传失败协议
     */
    public static final int CYCLE_ACK_FILE_TRANSFER_UPLOAD_FILE = 0x01110008;

    /**
     * 文件传输文件上传完成协议
     */
    public static final int CYCLE_ACK_FILE_TRANSFER_UPLOADED = 0x01110009;

    /**
     * 飞控返航信息
     */
    public static final int CYCLE_ACK_FC_BACK = 0x01010030;

    /**
     * 请求连接
     */
    public static final int CYCLE_ACK_REQUEST_CONNECT = 0X0100000A;

    /**
     * 综控监测log状态信息
     */
    public static final int CYCLE_ACK_AC_LOG_INFO = 0X01040001;

    /**
     * 综控合并云台多挂载共用基本状态信息
     */
    public static final int CYCLE_ACK_AC_PAYLOAD_INFO = 0X01040002;


    /**
     * 综控收集各个模块状态
     */
    public static final int CYCLE_ACK_AC_COMP_INFO = 0X01040004;



    /**
     * 校磁指令状态
     */
    public static final int CYCLE_ACK_FC_CALIBRATION  = 0X010100C0;

    /**
     * rtk的GGA数据
     */
    public static final int CYCLE_ACK_RTK_TYPE_INFO = 0X010A0001;


    /**
     * rtk的GGA数据
     */
    public static final int CYCLE_ACK_RTK_GGA_INFO = 0X010A000F;

    /**
     * 飞控升级周期反馈
     */
    public static final int CYCLE_ACK_FC_UPGRADE = 0x01010205;

    /**
     * 协处理器转发Z4B/C电池信息周期上发
     */
    public static final int CYCLE_ACK_MGP12_BATTERY_INFO1 = 0x01130004;

    /**
     * 协处理器转发Z4B/C电池信息周期上发2
     */
    public static final int CYCLE_ACK_MGP12_BATTERY_INFO2 = 0x01130005;

    /**
     * 智能电池升级状态反馈
     */
    public static final int CYCLE_ACK_MGP12_BATTERY_UPGRADE = 0x0113000D;

    /**
     * 天空端图传基带信息上报
     */
    public static final int CYCLE_ACK_BASEBAND_SKY_INFO = 0x01090003;

    /**
     * 图传丢包率统计
     */
    public static final int CYCLE_ACK_PACKAGE_LOST_INFO = 0X01090004;

    /**
     * 图传连接状态表
     */
    public static final int CYCLE_ACK_IM_CONNECT_INFO = 0X01090006;

    /**
     * 控制手周期反馈
     */
    public static final int CYCLE_ACK_RC_CONTROL_HAND = 0x010C000A;

    /**
     * 图传周围干扰信号强
     */
    public static final int CYCLE_ACK_INTENSITY_INTERFERENCE_SIGNAL = 0x01090007;

    /**
     * ADS-B的命令ID
     */
    public static final int CycleACK_ADS_B = 0x0101000A;

    public static final int ADS_B_CMD = 0x000A;

    /**********************************机载***************************************/
    /***********************************系统**************************************/

    /**********************************吊舱***************************************/
    /***********************************系统**************************************/
    /**
     * 吊舱系统 ID
     */
    public static final byte POD_SYSTEMS_ID = 0x12;

    /**
     * 云台共性
     */
    public static final byte POD_COMP_PUB_ID = 0x00;
    /**
     * 吊舱系统  云台控制
     */
    public static final byte POD_COMP_GC_ID = 0x01;
    /**
     * 吊舱系统  4K云台
     */
    public static final byte POD_COMP_4K_ID = 0x02;
    /**
     * 吊舱系统  10X云台
     */
    public static final byte POD_COMP_10X_ID = 0x03;
    /**
     * 吊舱系统  30X云台
     */
    public static final byte POD_COMP_30X_ID = 0x04;

    /**
     * 吊舱系统  红外云台
     */
    public static final byte POD_COMP_INFRARED_ID = 0x05;

    /**
     * 吊舱系统  三光云台
     */
    public static final byte POD_COMP_TL_ID = 0x06;
    /**
     * 吊舱系统  大吊舱 big pod
     */
    public static final byte POD_COMP_BP_ID = 0x07;

    /**
     * 吊舱系统  喊话器
     */
    public static final byte POD_COMP_MEGAPHONE_ID = 0x08;

    /**
     * 吊舱系统  照明灯
     */
    public static final byte POD_COMP_FLOODLIGHT_ID = 0x09;

    /**
     * 吊舱系统  气体检测
     */
    public static final byte POD_COMP_GAS_DETECTION_ID = 0x0A;

    /**
     * 吊舱系统  35X云台
     */
    public static final byte POD_COMP_35X_ID = 0x0C;

    /**
     * 吊舱系统  电动绞盘
     */
    public static final byte POD_COMP_WINCH_ID = 0x0D;

    /**
     * 吊舱系统  火控
     */
    public static final byte POD_COMP_FIRE_CONTROL_ID = 0x0E;

    /**
     * 吊舱系统  4KC云台
     */
    public static final byte POD_COMP_4KC_ID = 0x0F;

    /**
     * 吊舱系统  喷粉
     */
    public static final byte POD_COMP_DUSTING_ID = 0x10;

    /**
     * 吊舱系统  挂钩
     */
    public static final byte POD_COMP_HOOK_ID = 0x11;

    /**
     * 吊舱系统  .抛投器
     */
    public static final byte POD_COMP_RESQMAX_ID = 0x12;

    /**
     * 吊舱系统  前雷达
     */
    public static final byte POD_COMP_FRONT_RADAR_ID = 0x13;

    /**
     * 吊舱系统  6K云台(
     */
    public static final byte POD_COMP_6K_ID = 0x14;

    /**
     * 吊舱系统  旧10X云台
     */
    public static final byte POD_COMP_OLD_10X = 0x17;

    /**
     * 红外1k云台
     */
    public static final byte POD_COMP_IR_1K_ID = 0x1A;

    /**
     * 小双光云台
     */
    public static final byte SMALL_DOUBLE_LIGHT = 0x1C;

    /**
     * mp130喊话器
     */
    public static final byte MP130_LOUDSPEAKER = 0x1D;

    /**
     * 4光云台
     */
    public static final byte GIMBAL_FOUR_LIGHT = 0x1F;

    /**
     * 云台一键校飘指令周期反馈
     */
    public static final int CYCLE_ACK_CHECK_CLOUD = 0x02000013;

    /**
     * 云台软件升级指令周期反馈
     */
    public static final int CYCLE_ACK_GIMBAL_UPGRADE = 0x02000021;

    /**
     * 拍照周期反馈
     */
    public static final int CYCLE_ACK_PHOTO = 0x02000300;

    /**
     * 录像周期反馈
     */
    public static final int CYCLE_ACK_RECORD = 0x02000301;

    /**
     * TTS目录请求
     */
    public static final int CYCLE_ACK_TMS_CATALOGUE = 0x02000405;

    /**
     * 云台状态消息
     */
    public static final short GIMBAL_INFO_MSG_ID = 0x0001;

    /**
     * 云台姿态消息
     */
    public static final short GIMBAL_POSTURE_MSG_ID = 0x0002;

    /**
     * 相机系统状态消息
     */
    public static final short CAMERA_SYS_STATUS_MSG_ID = 0x0003;

    /**
     * 红外光相机状态反馈
     */
    public static final short INFRARED_CAMERA_INFO_MSG_ID = 0x0004;

    /**
     * 可见光相机状态反馈
     */
    public static final short VISIBLE_CAMERA_INFO_MSG_ID = 0x0005;

    /**
     * 新云台姿态信息反馈
     */
    public static final short GIMBAL_NEW_POSTURE_MSG_ID = 0x0006;

    /**
     * 拍照周期反馈命令字
     */
    public static final short CYCLE_CHECK_CLOUD_MSG_ID = 0x0013;

    /**
     * 请求云台软件升级指令
     */
    public static final short CYCLE_UPGRADE_MSG_ID = 0x0021;

    /**
     * 拍照周期反馈命令字
     */
    public static final short CYCLE_PHOTO_MSG_ID = 0x0300;

    /**
     * 录像周期反馈命令字
     */
    public static final short CYCLE_RECORD_MSG_ID = 0x0301;

    /**
     * TTS播放目录请求
     */
    public static final short CYCLE_TMS_CATALOGUE_MSG_ID = 0x0405;

    /**
     * 飞控-协处理器软件升级指令周期反馈
     */
    public static final int FC_COP_UPGRADE = 0x01130018;

    /**
     * 平台固件升级指令周期反馈
     */
    public static final int PLATFORM_UPGRADE = 0x01050001;

    /**********************************吊舱***************************************/
    /***********************************系统**************************************/


    /**********************************地面站***************************************/
    /***********************************系统**************************************/
    /**
     * 地面站系统 ID
     */
    public static final byte GROUND_STATION_SYSTEMS_ID = 0x03;

    /**
     * 中继
     */
    public static final byte GROUND_STATION_COMP_RELAY_ID = 0x01;

    /**
     *  图传 　 image transmission
     */
    public static final byte GROUND_STATION_COMP_IT_ID = 0x02;

    /**
     * MCU
     */
    public static final byte GROUND_STATION_COMP_MCU_ID = 0x03;

    /**
     * 遥控器状态信息
     */
    public static final int CYCLE_ACK_RC_STATUS_INFO = 0X03030005;

    /**
     * 图传基带信息
     */
    public static final int CYCLE_ACK_BASEBAND_INFO = 0X03010003;




    /**********************************地面站***************************************/
    /***********************************系统**************************************/


    /**********************************移动***************************************/
    /**********************************设备**************************************/
    /**
     * 移动设备 ID
     */
    public static final byte MOBILE_DEVICE_SYSTEMS_ID = 0x04;
    /**
     * APP组件 ID
     */
    public static final byte MOBILE_DEVICE_COMP_APP_ID = 0x01;
    /**********************************移动***************************************/
    /**********************************设备**************************************/


    /**********************************远程服务器***************************************/
    /**********************************设备**************************************/
    /**
     * 远程服务器
     */
    public static final byte REMOTE_SERVER_SYSTEMS_ID = 0x05;
    /**********************************远程服务器***************************************/
    /**********************************设备**************************************/

    /**********************************终端PC系统***************************************/
    /**********************************设备**************************************/
    /**
     * 终端PC系统
     */
    public static final byte PC_SYSTEMS_ID = 0x06;
    /**********************************远程服务器***************************************/
    /**********************************设备**************************************/


    /**********************************工厂调试软件***************************************/
    /**********************************设备**************************************/
    /**
     * 工厂调试软件 系统
     */
    public static final byte FACTORY_DEBUG_SYSTEMS_ID = 0x07;
    /**********************************工厂调试软件***************************************/
    /**********************************设备**************************************/


    /**********************************客户端软件***************************************/
    /**********************************设备**************************************/
    /**
     * 客户端软件 系统
     */
    public static final byte CLIENT_SYSTEMS_ID = 0x08;
    /**********************************客户端软件***************************************/
    /**********************************设备**************************************/

    /**********************************火控系统***************************************/
    /**********************************设备**************************************/
    /**
     * 火控系统 系统
     */
    public static final byte FIRE_CONTROL_SYSTEMS_ID = 0x09;

    /**
     * 火控组件
     */
    public static final byte FIRE_CONTROL_COMP_ID = 0x01;
    /**********************************火控系统***************************************/
    /**********************************设备**************************************/


    /**********************************集群地面站系统***************************************/
    /**********************************设备**************************************/
    /**
     * 集群地面站 系统
     */
    public static final byte CLUSTER_SYSTEMS_ID = 0x0A;
    /**********************************集群地面站系统***************************************/
    /**********************************设备**************************************/


    /**********************************视觉平台***************************************/
    /**********************************设备**************************************/
    /**
     * 视觉平台 系统
     */
    public static final byte VISION_SYSTEMS_ID = 0x0B;
    /**********************************视觉平台***************************************/
    /**********************************设备**************************************/




    /**********************************无人船***************************************/
    /***********************************系统**************************************/

    /**
     * 无人船系统ID
     */
    public static final byte BOAT_SYSTEMS_ID = 0x02;


    /**
     * 控制组件ID
     */
    public static final byte CONTROL_COM_ID = 0x01;


    /**
     * 平台组件ID
     */
    public static final byte PLATFORM_COM_ID = 0x02;

    /**
     * 电调组件ID
     */
    public static final byte ZTW_COM_ID = 0x03;

    /**
     * GNSS组件ID
     */
    public static final byte GNSS_COM_ID = 0x04;


    /**
     * 数据链组件ID
     */
    public static final byte DATA_LINK_COM_ID = 0x05;

    /**
     * 电池组件ID
     */
    public static final byte BATTERY_COM_ID = 0x06;

    /**
     * 遥控组件ID
     */
    public static final byte RC_COM_ID = 0x07;

    /**
     * APP组件ID
     */
    public static final byte APP_COM_ID = 0x08;


    /**
     * GNSS 定位信息
     */
    public static final int CYCLE_ACK_LOCATE_INFO = 0x02010401;

    /**
     * 控制组件  心跳信息
     */
    public static final int CYCLE_ACK_CONTROL_HEART_INFO = 0x02010030;

    /**
     * 控制组件  状态信息
     */
    public static final int CYCLE_ACK_CONTROL_STATUS_INFO = 0x02010031;

    /**
     * 控制组件  传感器信息
     */
    public static final int CYCLE_ACK_CONTROL_SENSOR_INFO = 0x02010032;

    /**
     * 控制组件 导航信息
     */
    public static final int CYCLE_ACK_CONTROL_NAVIGATION_INFO = 0x02010033;

    /**
     * 控制组件 任务信息
     */
    public static final int CYCLE_ACK_TASK_INFO = 0x02010038;


    /**
     * 模拟运行状态信息
     */
    public static final int CYCLE_ACK_SIMULATE_INFO = 0x020100a0;


    /**
     * 电池概括信息
     */
    public static final int CYCLE_ACK_BATTERY_INFO = 0x02010602;



    /**********************************无人船***************************************/
    /***********************************系统**************************************/

    /**
     * ===========================================3.0协议
     */
}
