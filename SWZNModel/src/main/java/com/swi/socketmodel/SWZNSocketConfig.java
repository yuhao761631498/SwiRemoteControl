package com.swi.socketmodel;

/**
 * Created by xx on 2016/5/6.
 * Socket一些常理配置
 */
public class SWZNSocketConfig
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
     * 计算棒周期反馈
     */
    public static final short CycleACK_ComputeStickStatus = 0x1d;

    /**
     * 4G模块的反馈
     */
    public static final short CycleACK_4G = 0x1b;
}
