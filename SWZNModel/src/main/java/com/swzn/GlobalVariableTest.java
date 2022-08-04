package com.swzn;

/**
 * Created by xx on 2017-6-22.
 */
public class GlobalVariableTest {

    /*********************************
     * 飞机固件版本号
     * eg.飞机的固件版本号1.06，此值为106---xx
     */
    public static int DroneFirmwareVersion;

    /**
     * 设备的国标ID
     */
    public static String LabelSNID;

    /**
     * 设备的国标的密码
     */
    public static String LabelPWD;

    /***************************************
     * 是否需要旋转180°------xx
     */
    public static boolean isRotate180 = true;//旋转180°

    /*************************
     * 添加队列书记失败---xx
     * 添加队列失败是有数字的
     * 添加队列成功是-1
     */
    public static int addDataQueueErrAndSize;

    /******************
     * 图传收的包个数---xx
     */
    public static int num_GetImgChannel;

    /************************
     * 总共收到的图传数---xx
     */
    public static long length_GetImgChannel;


    /**********************
     * 目标的经度 ---xx
     *************************/
    public static float targetLan;

    /**********************
     * 目标的纬度 --xx
     ***********************/
    public static float targetLon;

    /**************************
     * 上传经纬的数量 --xx
     **************************/
    public static byte updatePlanPoints;

    /********************
     * 当前航迹规划的点-----xx
     *********************/
    public static byte currentPlanPoint;

    /*****************************
     * 控制方向舵 ---xx
     ****************************/
    public static byte controlOrientation;

    /****************************
     * 控制俯仰Roll舵--xx
     ****************************/
    public static byte controlRoll;

    /***************************
     * 控制横滚舵 ----xx
     ***************************/
    public static byte controlPitch;

    /**************************
     * 控制油门舵-----xx
     **************************/
    public static byte controlThrottle;

    /*****************************
     * 真实方向舵 ---xx
     ****************************/
    public static byte realOrientation;

    /****************************
     * 控制俯仰Roll舵--xx
     ****************************/
    public static byte realRoll;

    /***************************
     * 控制横滚舵 ----xx
     ***************************/
    public static byte realPitch;

    /**************************
     * 控制油门舵-----xx
     **************************/
    public static byte realThrottle;

    /***********************
     * 悬停的油门大小----xx
     ***********************/
    public static byte hoverThrottle;

    /**********************
     * GPS的地向速度 ----xx
     **********************/
    public static short GPS_velD;

    /*********************************
     * 高度中是否是气压计 ----xx
     * 0:超声波
     * 1：气压计
     * 无效数据
     *********************************/
    public static byte isBarometer = -1;

    /*********************************
     * 右向加速度 ----xx
     **********************************/
    public static short AccelerationRight;

    /*********************************
     * 向后加速度 ----xx
     **********************************/
    public static short AccelerationBack;

    /************************
     * 向下加速度 ---xx
     **************************/
    public static short AccelerationDown;

    /***************************
     * 总飞机的时间 (单位MIN)---xx
     ***************************/
    public static short AllFlyTime;

    /**************************
     * 设置目标高度 ----xx
     *************************/
    public static short SetTargetHight;

    /************************
     * 飞行时间在空中-----xx
     ************************/
    public static short FlyTimeOnSky;

    /*********************
     * 定位方式：0气压计，1.超声
     */
    public static byte locationUltrasonic = -1;

    /********************************
     * 返航状态：0：默认； 1：返航失败；2：返航中；3：低电返航失败；
     */
    public static byte backHomeState;

    /*********************
     * 返航的经度---xx
     *************************/
    public static float backHomeLan;

    /*********************
     * 返航的纬度---xx
     *************************/
    public static float backHomeLon;

    /**********************
     * 光流置信度 ----xx
     **********************/
    public static int OpticalFlowQuality;

    /***************************
     * 飞机的最大速度-----xx
     ***************************/
    public static int DroneMaxSpeed;

    /**********************
     * 稳像开关 ---xx
     *********************/
    public static byte HolderImgOff;

    /*******************
     * 飞机降落的原因----xx
     */
    public static byte DroneLandWhy;

    /*******************
     * 飞机返航的原因----xx
     */
    public static byte DroneBackWhy;

    /********************************
     * 最近距离 距离的 禁飞区的 -----xx
     ********************************/
    public static short NearestDistanceOfNoFly;

    /************************
     * 融合速度 ---xx
     *************************/
    public static byte fuseSpeed;

    /************************
     * 飞控请求AGPS ---xx
     **************************/
    public static byte requestAGPS;


    /********************************
     * 滤波后_Y向速度
     ********************************/
    public static short x_MAG;

    public static short y_velocity;

    public static byte versionIndex_low;

    /************************
     * 飞机时间
     **************************/
    public static byte year;
    public static byte month;
    public static byte day;
    public static byte hour;
    public static byte minute;
    public static byte second;
    public static byte distanceHeight_high;
    public static byte distanceHeight_low;
    public static byte z_MAG_low;

    /********************************
     * 遥控器报警标志
     ********************************/
    public static byte alarmFlag;

    /********************************
     * 滤波后_D向速度（高位）
     ********************************/
    public static byte d_velocity_high;

    /********************************
     * 地面站YMAG显示低位
     ********************************/
    public static byte y_MAG_low;

    /********************************
     * 地面站YMAG显示高位
     ********************************/
    public static byte y_MAG_high;

    /********************************
     * 地面站ZMAG显示高位
     ********************************/
    public static byte z_MAG_high;

    /********************************
     * 滤波后_X向速度（高位）
     ********************************/
    public static byte x_velocity_high;
    public static byte versionIndex_high;
    public static short magnetic_heading;

    /********************************
     * 滤波后_D向速度（低位）
     ********************************/
    public static byte d_velocity_low;

    /********************************
     * 云台状态---这个状态一直是无效的，
     * 5月31号，把该状态作为HOME点刷新的标识位----xx
     * 详细说明如下：
     * 0 无返航点，开机及降落后默认值
     *1 在飞行中更新了返航点，比如光流起飞，飞到室外，有gps时更新返航点
     *2 起飞时更新返航点成功
     *3 起飞时更新返航点失败
     *4 触发返航时，无返航点不执行返航
     *5 触发返航时，gps信号差不执行返航
     ********************************/
    public static byte gimbal_status;

    /********************************
     * 滤波后_X向速度（低位）
     ********************************/
    public static byte x_velocity_low;

    /********************************
     * 虚拟云台俯仰角(网罩模式的开关状态   余浩)
     ********************************/
    public static short visual_gimabl_pitch;

    /********************************
     * X,Y,Z轴晃动系数
     ********************************/
    public static byte sloshing_coefficient_x;
    public static byte sloshing_coefficient_y;
    public static byte sloshing_coefficient_z;

    /********************************
     * 气压高度(高，低字节)
     ********************************/
    public static byte barometric_height_high;
    public static byte barometric_height_low;

    /********************************
     * 超声高度（高，低字节）
     ********************************/
    public static byte ultrasonic_height_high;
    public static byte ultrasonic_height_low;

    /********************************
     * 光流velx，vely
     ********************************/
    public static short optical_velocity_x;
    public static short optical_velocity_y;

    /********************************
     * 光流置信度
     ********************************/
    public static byte optical_confidence_level_0_1;

    /********************************
     * GPS置信度
     ********************************/
    public static byte gps_confidence;

    /********************************
     * 融合东向速度
     ********************************/
    public static short mix_velocity_east;

    /********************************
     * 融合北向速度
     ********************************/
    public static short mix_velocity_north;

    /********************************
     * Z，X方向震动系数  是否收敛
     ********************************/
    public static byte vibration_coefficient_z;
    public static byte vibration_coefficient_x;

    /********************************
     * 融合东向速度
     ********************************/
    public static byte vibration_range_y;
    public static byte vibration_range_z;

    /********************************
     * 目标环绕中测距距离
     ********************************/
    public static byte target_surround_distance;

    /********************************
     * GPS航向   RTK用作地面的海拔高度
     ********************************/
    public static float gps_heading;

    /********************************
     * 方向舵，副翼，升降舵 ，油门原始数据
     ********************************/
    public static short raw_rudder;
    public static short raw_ailerons;
    public static short raw_elevator;
    public static short raw_throttle;

    /********************************
     * 飞机锁电机原因数据
     ********************************/
    public static byte flight_reason_lock;

    /********************************
     * 卡尔曼融合次数
     ********************************/
    public static byte calman_fusion_frequency;

    /********************************
     * 竖向acc动态偏差
     ********************************/
    public static float ertical_acceleration_deviation;

    /********************************
     * 滤波后的垂直方向位移
     ********************************/
    public static float EKF2;

    /********************************
     * 光流纹理
     ********************************/
    public static int optical_texture;

    /********************************
     * 最大磁原始数据
     ********************************/
    public static byte magnetic_raw;

    /********************************
     * 最大磁校准数据
     ********************************/
    public static short magnetic_calibration;

    /********************************
     * 警示区中距离禁飞区的最近距离
     ********************************/
    public static byte alert_to_no_takeOff_distance;

    /********************************
     * 其他
     ********************************/
    public static byte falg_001;
    public static byte falg_002;

    /********************************
     * 双目的异常状态----xx
     */
    public static byte BinocularErr;

    /*******************************
     * 是否开启本地副本保存----xx
     */
    public static boolean isOpenLocalCacheMedia = true;

    /**************************************
     * 信号强度-----xx
     */
    public static byte wifiQuality;

    /**********************
     * 噪音质量---xx
     */
    public static byte noiseQuality;

    /***************
     * RC是否已经获取到PreView----xx
     */
    public static byte rcHadGetPreView;

    /***************************
     * 10s钟收到的数据长度
     */
    public static int receiverDataLength;

    /********************************
     * 自动切换比特率的值----xx
     * F6:0.6M
     * F8:0.8M
     * 1:1M
     */
    public static byte autoChangeBite = 1;

    /***************************
     * cs是否是最新的版本---xx
     */
    public static byte csIsLastVersion;

    /**
     * 是否重新开流
     */
    public static boolean isRestartStream = false;

    /**
     * 是否初始化解码器
     */
    public static boolean isInitDecoder = false;

    /**
     * 解码器状态
     */
    public static String decoderStatus ;

}
