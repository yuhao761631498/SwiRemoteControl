package com.swzn.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * SharedPrefrences的工具类
 *
 * @author Administrator
 */
public class SPUtils {

    /**
     * <P>shang</P>
     * <P>已经注册过的SN飞机</P>
     * <P>完成实名登记</P>
     */
    public static final String REGISTER_UAV_SN = "register_uav_sn";
    /**
     * <P>shang</P>
     * <P>最后一次使用的飞机类型</P>
     */
    public static final String USER_LAST_PLANTYPE = "user_last_plantype";
    /**
     * <P>最后一次使用的云台类型</P>
     */
    public static final String USER_LAST_GIMBAL = "user_last_gimbal";
    /**
     * <P>shang</P>
     * <P>高德地图初始化定位坐标</P>
     */
    public static final String AMAP_LATLNG = "amap_latlng";
    /**
     * <P>shang</P>
     * <P>gooogle地图初始化定位坐标</P>
     */
    public static final String GOOGLE_LATLNG = "google_latlng";
    /**
     * <P>shang</P>
     * <P>绑定邮箱</P>
     */
    public static final String BIND_EMAILE = "bingEmail";
    /**
     * <P>shang</P>
     * <P>更换手机号或者绑定</P>
     */
    public static final String REPLACE2BIND_MOBILE = "replace2bingMobile";

    /**
     * <p>是否第一次启动app</p>
     */
    public static final String IS_NOT_FIRST_BOOT = "is_not_first_boot";

    /**
     * <P>shang</P>
     * <P>主页面欢迎界面，是否是第一次连接O2飞机</P>
     */
    public static final String IS_FIRST_CONN_PLAN = "isfirstconnplan";
    /**
     * <P>shang</P>
     * <P>引导图层，是否是第一次进入</P>
     * <P>APP直连-经典模式</P>
     */
    public static final String IS_FIRST_IN_WIFI_CLASSICS_MODE = "isfirstinwificlassics";
    /**
     * /**
     * <P>shang</P>
     * <P>引导图层，是否是第一次进入</P>
     * <P>APP直连-智能模式</P>
     */
    public static final String IS_FIRST_IN_WIFI_SMART_MODE = "isfirstinwifismart";
    /**
     * /**
     * <P>shang</P>
     * <P>引导图层，是否是第一次进入</P>
     * <P>遥控器连接-经典模式</P>
     */
    public static final String IS_FIRST_IN_REMOTE_CLASSICS_MODE = "isfirstinremoteclassics";
    /**
     * /**
     * <P>shang</P>
     * <P>引导图层，是否是第一次进入</P>
     * <P>遥控器连接-智能模式</P>
     */
    public static final String IS_FIRST_IN_REMOTE_SMART_MODE = "isfirstinremotesmart";
    /**
     * /**
     * <P>shang</P>
     * <P>引导图层，是否是第一次进入</P>
     * <P>地图页面</P>
     */
    public static final String IS_FIRST_IN_MAP_PAGE = "isfirstinmappage";
    /**
     * <P>shang</P>
     * <P>引导图层，是否是第一次进入Smart 倒飞自拍中</P>
     */
    public static final String IS_FIRST_IN_SMART_INVERTED = "isfirstInverted";

    /**
     * 保存 版本信息的2 版本号和 code
     */
    public static final String SF_VERSION = "sf_Version";
    public static final String SF_VERSIONCODE = "sf_VersionCode";
    // 航迹规划
    public static String MAPTYPE = "maptype";
    public static String ZORRO_DELAY = "zorro_delay";
    /**
     * <P>shang</P>
     * <P>增加测试模式 的状态保存</P>
     */
    public static String TEXT_MODE = "text_mode";

    /********************************************--xx
     * 测试服务器MODE,是否启动测试服务器的模式
     */
    public static String TestServerMode = "TestServerMode";

    /********************************************--xx
     * 测试服务器MODE,是否启动测试服务器的模式
     */
    public static String ScreenOrientationIsOpen = "ScreenOrientationIsOpen";

    /********xx***********
     * 控制模式----xx
     **********************/
    public static String CONTROLMODEL = "CONTROLMODEL_RON";

    /**********************
     * 记得当前的模式-----xx
     */
    public static String REMANDMODEL = "REMANDMODEL";


    /*斜飞，垂飞 结束后动作*/

    /**************
     * 垂飞结束后的动作 ----xx
     */
    public static final String EXIT_ACTION_Vertical = "EXIT_ACTION_VERTICAL";

    /******************************
     * 斜飞结束后的动作 ----xx
     */
    public static final String EXIT_ACTION_Inverted = "EXIT_ACTION_Inverted";

    public static final int ACTION_HOVER = 0;

    public static final int ACTION_RETURN = 1;

    /*斜飞，垂飞 相机动作zhaijiang*/
    /********************
     * 垂飞的时候 相机的动作 ---xx
     */
    public static final String CAMERA_ACTION_VERTAICAL = "CAMERA_ACTION_VERTAICAL";

    /**************************
     * 斜飞的时候，相机的动作----xx
     */
    public static final String CAMERA_ACTION_INVERTED = "CAMERA_ACTION_INVERTED";

    /**************************
     * 垂直的距离-------xx
     */
    public static final String DISTENSE_VERTAICAL = "DISTENSE_VERTAICAL";

    /******************************
     * 斜飞的距离-------xx
     */
    public static final String DISTENSE_INVERTED = "DISTENSE_INVERTED";

    /************************
     * 是否显示避障信息----xx
     */
    public static final String SHOWOBSTACLEVIEW = "SHOWOBSTACLEVIEW";

    /**
     * 限飞高度
     */
    public static final String HEIGHT_LIMIT = "height_limit";

    /**
     * 限飞距离
     */
    public static final String DISTANCE_LIMIT = "distance_limit";


    /************************
     * 最后飞机经纬度，用于找飞机
     */
    public static final String LAST_DRONE_POSITION = "LAST_DRONE_POSITION";
    public static final String LAST_DRONE_TIME = "LAST_DRONE_TIME";


    public static final int ACTION_RECORD = 0;

    public static final int ACTION_PHOTO = 1;

    public static final int ACTION_MANUAL = 2;


    //2.0 飞行记录常量保存

    public static final String Timestamp = "fr_timestamp";
    public static final String Startplace = "fr_startplace";
    public static final String Maxhight = "fr_Maxhight";
    public static final String Maxdistance = "fr_Maxdistance";
    public static final String Flighttime = "fr_flighttime";
    public static final String Weather = "fr_weather";
    public static final String Json = "fr_json";// 航迹点json
    public static final String START_PLACE_INFO = "fr_statrplace";
    public static final String STARTTIME = "StartTime2minute2second";

    public static final String MD5String = "md5String";
    public static final String FILEPATH = "filePath";
    public static final String FIRWARETYPE = "firmwreType";

    /***************************flightMs账号******************************/
//    public static final String flightMsCustomerId="customerId";   //客户id
    public static final String flightMsTeamId="teamId";   //团队ID
    public static final String flightMsParentTeamId="parentTeamId";   //父团队ID
    public static final String flightMsTeamName="teamName";   //团队名称
//    public static final String flightMsSignKey="signKey";  //密钥
    public static final String flightMsId = "id";   //用户ID
    //    public static final String flightMsTaskId="taskId";  //任务ID
    public static final String flightMsMobile = "mobile";  //电话
    public static final String flightMsEmail = "email";  //电话
    public static final String flightMsCountry = "country";  //国籍
    public static final String flightMsHeadUrl = "headUrl";  //头像地址
    public static final String flightMsUserName = "userName";//用户名
    public static final String flightTaskJson = "taskJson";  //团队任务列表的json
    public static final String RightMenuElec = "rightMenuElec";

    public static final String RTK_MODULE_STATUS = "rtkModuleStatus";  //RTK模块是否开启
    public static final String RTK_IP = "rtkIp";  //RTK IP
    public static final String RTK_PORT = "rtkPort";  //RTK Port
    public static final String RTK_ACCOUNT = "rtkAccount";  //RTK 端口
    public static final String RTK_PASSWORD = "rtkPassword";  //RTK 密码
    public static final String RTK_MOUNT_POINT = "rtkMountPoint";  //RTK 挂载点

    public static final String RTK_AK = "rtkAK";  //RTK AppKey
    public static final String RTK_AS = "rtkAS";  //RTK AppSecret
    public static final String RTK_DEVICE_ID = "rtkDeviceID";  //RTK DeviceID

    public static final String MS_ADDRESS = "ms_address";
    public static final String MS_PORT = "ms_port";
    public static final String PUSH_PORT = "push_port";
    public static final String ORGANIZATION_PORT = "organization_port";

    public static final String GB28181_DEVICE_ID = "gb28181_device_id";

    public static final String LIVE_TYPE = "live_type";

    /**
     * 万能的put方法
     *
     * @param context
     * @param key
     * @param value
     */
    public static void put(Context context, String key, Object value) {
        if (context == null) {
            return;
        }
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        Editor edit = sp.edit();
        //instanceof 用于判断数据类型的
        if (value instanceof String) {
            edit.putString(key, (String) value);
        } else if (value instanceof Integer) {
            edit.putInt(key, (int) value);
        } else if (value instanceof Byte) {
            edit.putInt(key, (int) value);
        } else if (value instanceof Double) {
            edit.putFloat(key, (Float) value);
        } else if (value instanceof Boolean) {
            edit.putBoolean(key, (boolean) value);
        } else if (value instanceof Long) {
            edit.putLong(key, (Long) value);
        }
        edit.commit();
    }

    /**
     * 获取字符串
     *
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        if (context == null) {
            return null;
        }
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }

    public static String getString(Context context, String key, String defaultValue) {
        if (context == null) {
            return null;
        }
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getString(key, defaultValue);
    }

    /**
     * 获取long
     *
     * @param context
     * @param key
     * @return
     */
    public static Long getLong(Context context, String key) {
        if (context == null) {
            return null;
        }
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getLong(key, 0);
    }

    /**
     * 获取整数
     *
     * @param context
     * @param key
     * @return
     */
    public static Float getFloat(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getFloat(key, 0);
    }

    /**
     * 获取Int
     *
     * @param context
     * @param key
     * @return
     */
    public static int getInt(Context context, String key) {
        if (context == null) {
            return 0;
        }
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getInt(key, 0);
    }

    /**
     * 获取Int
     *
     * @param context
     * @param key
     * @return
     */
    public static int getCustomInt(Context context, String key, int custom) {
        if (context == null) {
            return 0;
        }
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getInt(key, custom);
    }

    /**
     * 获取Boolean
     * <P>默认为 false </P>
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key) {
        if (context == null) {
            return false;
        }
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

    /**
     * 获取Boolean
     * <P>设置自定义 Boolean </P>
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean getCustomBoolean(Context context, String key, boolean isOpen) {
        if (context == null) {
            return false;
        }
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getBoolean(key, isOpen);
    }

    /**
     * 获取Boolean
     * <P>默认为 false </P>
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean getFalseBoolean(Context context, String key) {
        if (context == null) {
            return false;
        }
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

    /**
     * 获取Boolean
     * <P>默认为 true </P>
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean getTrueBoolean(Context context, String key) {
        if (context == null) {
            return false;
        }
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getBoolean(key, true);
    }

    /**
     * <p>shang</p>
     * <p>获取有默认值的字符串</p>
     * <p>是固件版本用来对比的</p>
     *
     * @param context
     * @param key
     * @return
     */
    public static String getDefaultString(Context context, String key) {
        if (context == null) {
            return null;
        }
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getString(key, "0");
    }
}















