package com.swi.config;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;

import java.util.Locale;

/**
 * Created by xx on 2016/6/12.
 */
public class SWZNConfig {

    //个人中心/升级 更新 上传关键 标示 字串
    public static final int MGP03_PROJECT = 3;//  类型 是MGP03  的 2.4G
    public static final int MGP03_5_8G_PROJECT = 4;//  是MGP03 的5.8G
    public static final int MGP03_PLUS_PROJECT = 5;//  是MGP03 的PLUS
    public static final int BIRDT = 6;
    public static final int SAGA = 6;
    public static final int SAGA_PRO = 13;   //Saga Pro类型
    public static final String ANDROIND = "1";// 需要上传的 Progject 类型 是MGP01请求
    public static final String BATTERY_CODE = "battery_code";

    //（1）存储版本信息时，分为网络端 和 飞机端
    public static final String NET_TYPE = "net_type";
    public static final String FLY_TYPE = "fly_type";

    // 当前是什么模式（用于判断不同模式下的Marker触发事件）
    public static final int NoMode = 0;
    public static final int FpMode = 1;
    public static final int ErMode = 2;
    // 当前是什么状态（用于航迹规划判断不同的状态）
    public static final int FpClose = 0;
    public static final int FpGo = 1;
    public static final int FpGoOn = 2;
    // 当前是什么状态（用于电子围栏判断不同的状态）
    public static final int ErClose = 0;
    public static final int ErStart = 1;

    //账号绑定
    public static final String BIND_TYPE = "bind_type";
    public static final String PHONE_NUM = "phone_num";

    //分享类型
    public static final int QQ_SHARE = 0x0;
    public static final int WECHAT_SHARE = 0x1;
    public static final int WECHAT_MOMENT_SHARE = 0x2;
    public static final int SINA_WEIBO_SHARE = 0x3;
    public static final int FACEBOOK_SHARE = 0x4;
    public static final int TWITTER_SHARE = 0x5;
    public static final int MORE_SHARE = 0x4;

    /**
     * <p>0x01:室内模式， 0x02:室外模式</p>
     */
    public static int doorState = 0x02;

    /**
     * <p>大缩略图的宽，高是等比例压缩后的</p>
     */
    public static final int ThumbnailWidth = 480;

    /**
     * <p>小缩略图的宽，高是等比例压缩后的高</p>
     */
    public static final int SmallThumbnailWidth = 100;

    /**
     * <p>文件的基本目录</p>
     */
    public static String BaseDirectory = Environment
            .getExternalStorageDirectory() + "/swzn/usv";
    /**
     * <p>Text文件的基本目录</p>
     */
    public static String TextDirectory = Environment
            .getExternalStorageDirectory() + "/swzn/usv";

    public static String TargetDirectory = Environment
            .getExternalStorageDirectory() + "/swzn/target";

    /**
     * <p>图片类型</p>
     */
    public static int Type_IMAGE = 0x01;

    /**
     * <p>多媒体类型</p>
     */
    public static int Type_MEDIA = 0x02;

    /**
     * <p>图片的文件名字</p>
     */
    public static String ImageFileName = "Image";

    /**
     * <p>APK下载到本地的文件夹地址</p>
     */
    public static final String Directory_APK = "Apk";

    /**
     * 存放下载图片的文件夹
     */
    public static String ImageDownlaod = "ImageDowload";

    /**
     * <p>视频的文件名字</p>
     */
    public static String MediaFileName = "Media";


    /**
     * 自定义音乐存放文件夹 余浩
     */
    public static String MusicDirectiry = "Music";


    /**
     * 航迹规划 余浩
     */
    public static String FlightRouteDirectiry = "RoutePlan";

    public static String ElecDirectiry = "Elec";

    /**
     * 拼图结果目录  余浩
     */
    public static String TifDirectiry = "tipDir";

    /**
     * 团队任务 余浩
     */
    public static String FlightRouteTeamDirectiry = "RoutePlanTeam";

    public static String FlightGalleryZipDirectiry = "GalleryZip";

    public static String FlightGallery = "FlightGallery";

    public static String Fligth1D = "FlightGallery/Flight-1D";

    public static String Fligth2D = "FlightGallery/Flight-2D";

    public static String Fligth3D = "FlightGallery/Flight-3D";

    /**
     * <p>缓存路径</p>
     */
    public static String CacheFileName = "cache";
    /**
     * <p>黑匣子 信息 文件 </p>
     */
    public static String DroneFlogFile = "VDR_LOG";

    /**
     * <p>黑匣子excel 信息 文件 </p>
     */
    public static String DroneFlogExcelFile = "VDR_EXCEL";

    /**
     * <p>三方sdk 百度资源 </p>
     */
    public static String baiduASR = "baiduASR";
    /**
     * <p>开发测试 信息 </p>
     */
    public static String DevelopTextInfo = "DevelopInfo";

    /**
     * <p>临时文件路径</p>
     */
    public static String TempFileName = "Temp";
    /**
     * <p>升级包文件夹</p>
     */
    public static String UpgradePackage = "UpgradePackage";
    /**
     * <p>升级包文件夹2.4G</p>
     */
    public static String Upgrade_2Dot4 = "upgrade_2Dot4";
    /**
     * <p>升级包文件夹2.4G</p>
     */
    public static String Upgrade_O2Plus = "upgrade_o2plus";
    /**
     * <p>升级包文件夹2.4G</p>
     */
    public static String Upgrade_O2X = "upgrade_o2x";
    /**
     * <p>升级文件夹5.8G</p>
     */
    public static String Upgrade_5Dot8 = "upgrade_5Dot8";
    /**
     * <p>升级文件夹byrd T</p>
     */
    public static String Upgrade_ByrdT = "upgrade_byrdT";
    /**
     * <p>升级包文件夹2.4G</p>
     */
    public static String Upgrade_OTher = "upgrade_other";
    /**
     * <p>科大讯飞文件路径</p>
     */
    public static String IflytekFileName = "Iflytek";

    /**
     * <p>临时文件之音乐</p>
     */
    public static String MusicFileName = "Temp/music";

    /**
     * <p>临时文件之视频</p>
     */
    public static String VideoTempFileName = "Temp/video";


    public static String VideoLocalCache = "video/LocalCache";

    /***********************
     * 合并后的视频存放地
     */
    public static String ViedoMerge = "MergeVideo";

    /**
     * 临时图片文件存放  余浩
     */
    public static String ImageTempFileName = "Temp/image";

    /**
     * 目标检测保存图片的文件目录
     */
    public static String sTargetPicFolder = "target/";

    /**
     * 告警图片保存目录
     */
    public static String AlarmPicFolder = "Alarm/";

    /**
     *  气体检测文件目录
     */
    public static String sGasDetectorFolder = "GasDetector/";

    public static final String PLAN_XML_DIR = SWZNConfig.BaseDirectory + "/RoutePlan/";

    /**
     * 定位相关
     */
    public static String GOOGLE_COUNTRY = "GoogleCountry";
    public static String GOOGLE_CITY = "GoogleCity";
    public static String GD_COUNTRY = "GDcountry";
    public static String GD_CITY = "GDcity";

    public static final String KEY_SENDCPS_FLAG = "flag";
    public static final String KEY_SENDGPS_CLOSE = "close";
    public static final String KEY_SENDGPS_OPEN_SURROUND = "open_surrround";
    public static final String KEY_SENDCPS_LAT = "latitude";
    public static final String KEY_SENDCPS_LON = "longitude";
    public static final String KEY_SENDGPS_OPEN_VIDEO_TRACK = "open_video_track";
    public static final String KEY_VIDEO_TRACKING = "video_tracking";
    public static final String KEY_IMAGE_SURROUND = "image_surround";
    public static final String KEY_GESTURE = "gesture";
    public static final String LOCATION_CITY = "locationCity";
    public static final String CITY = "city";
    public static final String PINYIN_CITY = "pinyinCity";

    //国家选择
    public static String COUNTRY_NAME = "name";
    //用户手册的地址
    public static String MANUAL_PATH = "manualPath";
    public static String REFRESH = "refresh";
    public static final String FIND_PASSWORD_TYPE = "find_password_type";

    /**********************ACK反馈*********余浩******************/
    public static final byte OK = 0x00;    //成功
    public static final byte TIME_OUT = 0x01;    //连接超时
    public static final byte STOROAGE_FULL = 0x02;    //存储不足
    public static final byte REC_ING = 0x03;    //正在录像
    public static final byte NOT_OPENED_CAMERA = 0x04;    //摄像头打开失败
    public static final byte NOT_REC = 0x05;    //未录像
    public static final byte DOING = 0x06;   //正在进行中
    public static final short UNKNOWN_ERROR = 0xff;   //未知错误


    /**
     * 飞控版本
     */
    public static String FLY_VERSION = "fly_version";
    public static String FLY_VERSION_CODE = "fly_versionCode";

    /**
     * 云台版本
     */
    public static String CLOUND_VERSION = "gimbal_version";
    public static String CLOUND_VERSION_CODE = "gimbal_versionCode";

    /**
     * 智能电池版本
     */
    public static String BATTERY_VERSION = "battery_version";
    public static String BATTERY_VERSION_CODE = "battery_versionCode";

    /**
     * OTA版本
     */
    public static String OTA_VERSION = "ota_version";
    public static String OTA_VERSION_CODE = "ota_versionCode";

    /**
     * AP12版本
     */
    public static String AP12_VERSION = "ap12_version";
    public static String AP12_VERSION_CODE = "ap12_versionCode";

    /**
     * 禁飞区版本
     */
    public static String NFZ_VERSION = "nfz_version";
    public static String NFZ_VERSION_CODE = "nfz_versionCode";

    /**
     * 飞控地址
     */
    public static String FLY_URL = "fly_url";

    /**
     * 云台地址
     */
    public static String CLOUND_URL = "clound_url";

    /**
     * 智能电池地址
     */
    public static String BATTERY_URL = "battery_url";

    /**
     * ota地址
     */
    public static String OTA_URL = "ota_url";

    /**
     * 禁飞区地址
     */
    public static String NFZ_URL = "nfz_url";

    public static String CUR_FLY_VERSION = "cur_fly_version";

    public static String CUR_CLOUND_VERSION = "cur_clound_version";

    /**
     * 音效提示开关
     */
    public static final String VOICE = "voice";
    public static final String Live_Compress = "Live_Compress";

    /**
     * 限高限距第一次进入链接飞机默认开启
     */
    public static final String LIMIT_HEIGHT_DISTANCE = "limitHeightDistance";

    /**
     * 重新定位
     */
    public static final String LOCATEADAIN = "locateAgain";
    /**
     * 定位失败
     */
    public static String LOCATED_FAILED = "locatedFailed";

    /**
     * 注册成功广播
     */
    public static final String REGIST = "regist";

    /**
     * 带国家代码的国家名称
     */
    public static String CODE_COUNTRY = "codeCountry";

    /**
     * 账号已经在其它设备上登录
     */
    public static final String HAS_LOGIN = "hasLogin";

    /**
     * 机上媒体库下载是否下载视频
     */
    public static final String IS_VIDEO = "isVideo";

    /**
     * 登录成功后发送广播刷新数据
     */
    public static final String LOGIN = "login";


    /**
     * <P>shang 媒体库 分享完成后 点击完成 后传递的常量</P>
     */
    public static final String SHAREDID = "shardeID";

    /**
     * 机上没图库下载图片后回到本地媒体库重新刷新文件
     */
    public static final String HASDOWNPIC = "hasDownloadPic";

    /**
     * 判断手机是否装载sd卡n
     *
     * @return
     */
    public static boolean hasSdcard() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    /**
     * <p>判断当前环境是否为中文环境</p>
     *
     * @return
     */
    public static boolean isCN() {
        String country = Locale.getDefault().getCountry();
        if (country.equals("CN"))
            return true;
        return false;
    }

    /**
     * <p>获取版本信息</p>
     *
     * @param context
     * @return
     */
    public static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pi;
    }

    public static final String SINA_WEIBO_APP_KEY = "69544732";
    public static final String SINA_WEIBO_REDIRECT_URL = "http://www.sharesdk.cn";
    public static final String SCOPE =
            "email,direct_messages_read,direct_messages_write,"
                    + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                    + "follow_app_official_microblog," + "invitation_write";

}
