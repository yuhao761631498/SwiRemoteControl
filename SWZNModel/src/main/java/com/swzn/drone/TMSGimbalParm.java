package com.swzn.drone;

import android.util.Log;

public class TMSGimbalParm {

    private final static String TAG  = "TMSGimbalParm";

    /**
     *拍照录像模式：0：拍照模式；1：录像模式；默认：0：拍照模式；
     */
    public int photoVideoMode;

    /**
     *图像模式：0：红外图像；1：红外画中画；2：可见光图像；3：可见光画中画；默认：0红外
     */
    public int imageMode;

    /**
     *红外相机帧频选择：1：25Hz；2：9Hz；默认25Hz
     */
    public int irFrameRate;

    /**
     *调光算法：0：线性；1：直方图；2：混合；默认：0线性
     */
    public int lightingAlgorithm;

    /**
     *自动打快门开关：0：关闭自动打快门；1：使能自动打快门；默认：1
     */
    public int autoShutter;

    /**
     *图像镜像：0：无；1：X向镜像；2：Y向镜像；3：XY向镜像；默认无
     */
    public int imageMirror;
    /**
     *伪彩色设置：0-9分别代表白热、熔岩、铁红、热铁、医疗、北极、彩虹1、彩虹2、描红、黑热10种伪彩；默认值为2铁红。
     */
    public int pseudoColor;

    /**
     *有无TF卡：0：无:；1：红外；2；可见光；3：红外+可见光；
     */
    public int isInsertTF;

    /**
     *测温叠加开关：0：关闭测温叠加功能；1：打开测温叠加功能；默认打开
     */
    public int TempOverlay;

    /**
     *测温模式：0：关闭测温叠加功能；1：打开测温叠加功能；默认打开
     */
    public int TempMode;

    /**
     *时间经纬度叠加开关：0：关闭；1：打开；默认：打开；
     */
    public int timePositionOverlay;

    /**
     *测温区域选择： 0：全屏测温；1：区域测温；默认全屏测温
     */
    public int TempArea;

    /**
     *热点追踪开关：0：关；1：开；
     */
    public int hotspotTrack;

    /**
     *拍照模式：0：红外单拍；1：连拍；2：持续拍；3：延时拍；4：可见光单拍；0xFF停止拍照（红外）；默认0正常单拍。
     */
    public int photoMode;

    /**
     *拍照存储模式：0：可见光；1：红外；2：可见光和红外；
     */
    public int photoStorageMode;

    /**
     *录像存储模式：0：可见光；1：红外MP4；2：红外Y16；3：红外MP4和Y16；4：可见光+红外MP4；5：可见光+红外Y16；
     */
    public int videoStorageMode;

    /**
     *连拍或持续拍时间间隔（或延时拍延时时间）：3-180s；默认3s
     */
    public int photoInterval;

    /**
     *连拍张数：1-200张；（持续拍时忽略该参数）；默认10张
     */
    public int numContinuousPhoto;

    /**
     *亮度：0-100；默认：50
     */
    public int brightness;

    /**
     *对比度：0-100；默认：50
     */
    public int contrast;

    /**
     *电子放大：8：无放大；16:2倍放大；32:4倍放大；64:8倍放大；默认：8
     */
    public int digitalZoom;

    /**
     *去噪等级：范围0-10默认值：0
     */
    public int denoiseLevel;

    /**
     *去躁开关：0：关闭；1：开；默认：1
     */
    public int denoiseSwitch;

    /**
     *增强细节增益：范围0-64默认值：0
     */
    public int enhanceDetailLevel;

    /**
     *增强开关：0：关闭；1：开；默认：1；
     */
    public int enhanceDetailSwitch;

    /**
     *可见光拍照分辨率 0:8M（3840*2160）；1：5M:2592*1944）；2:2M（1920*1080）；3:4M（2688*1520）；4:14M（4352*3264）；
     */
    public int vlPhotoResolution;

    /**
     *可见光视频分辨率 0:1080P60；1:1080P30；2:720P60；
     */
    public int vlVideoResolution;

    /**
     * 可见光白平衡 0：自动；1：日光（晴天）；3：多云；4：阴天；5白炽灯；6：日光灯；
     */
    public int vlWB;

    /**
     * 可见光 EV值 0：EV2；1：EV1；2：EV0；3：EV-1；4：EV-2；5：EV-3；6：EV3；
     */
    public int vlEV;

    /**
     * 可见光 ISO值
     */
    public int vlISO;

    /**
     * 红外：TF卡总容量高字节
     */
    public int irTFHighByte;

    /**
     * 红外：TF卡总容量低字节；单位10M取整
     */
    public int irTFLowByte;

    /**
     * 可见光：TF卡总容量高字节
     */
    public int vlTFHighByte;

    /**
     *可见光：TF卡总容量低字节；单位10M取整
     */
    public int vlTFLowByte;

    /**
     *红外相机程序版本号（年）
     */
    public int irVersionYear;

    /**
     *红外相机程序版本号（月）
     */
    public int irVersionMonth;

    /**
     *红外相机程序版本号（日）
     */
    public int irVersionDay;

    /**
     *可见光相机版本号高字节
     */
    public int vlVersionHighByte;

    /**
     *可见光相机版本号低字节
     */
    public int vlVersionLowByte;

    public static TMSGimbalParm parseData(byte[] data){
        TMSGimbalParm tmsGimbalParm = new TMSGimbalParm();
        if(data.length >= 26){
            tmsGimbalParm.photoVideoMode      = getBits(data[1], 0, 1);
            tmsGimbalParm.imageMode           = getBits(data[1], 1, 2);
            tmsGimbalParm.irFrameRate         = getBits(data[1], 3, 1);
            tmsGimbalParm.lightingAlgorithm   = getBits(data[1], 4, 2);
            tmsGimbalParm.autoShutter         = getBits(data[1], 6, 1);

            tmsGimbalParm.imageMirror         = getBits(data[2], 0, 2);
            tmsGimbalParm.pseudoColor         = getBits(data[2], 2, 4);
            tmsGimbalParm.isInsertTF          = getBits(data[2], 6, 2);

            tmsGimbalParm.TempOverlay         = getBits(data[3], 0, 1);
            tmsGimbalParm.timePositionOverlay = getBits(data[3], 1, 1);
            tmsGimbalParm.TempArea            = getBits(data[3], 2, 1);
            tmsGimbalParm.hotspotTrack        = getBits(data[3], 3, 1);
            tmsGimbalParm.TempMode            = getBits(data[3], 4, 2);

            tmsGimbalParm.photoMode           = getBits(data[4], 0, 3);
            tmsGimbalParm.photoStorageMode    = getBits(data[4], 3, 2);
            tmsGimbalParm.videoStorageMode    = getBits(data[4], 5, 3);

            tmsGimbalParm.photoInterval       = data[5];
            tmsGimbalParm.numContinuousPhoto  = data[6];
            tmsGimbalParm.brightness          = data[7];
            tmsGimbalParm.contrast            = data[8];
            tmsGimbalParm.digitalZoom         = data[9];

            tmsGimbalParm.denoiseLevel        = getBits(data[10], 0, 4);
            tmsGimbalParm.denoiseSwitch       = getBits(data[10], 4, 1);

            tmsGimbalParm.enhanceDetailLevel  = getBits(data[11], 0, 7);
            tmsGimbalParm.enhanceDetailSwitch = getBits(data[11], 7, 1);

            tmsGimbalParm.vlPhotoResolution   = data[12];
            tmsGimbalParm.vlVideoResolution   = data[13];
            tmsGimbalParm.vlWB                = data[14];
            tmsGimbalParm.vlEV                = data[15];
            tmsGimbalParm.vlISO               = data[16];

            tmsGimbalParm.irTFHighByte        = data[17];
            tmsGimbalParm.irTFLowByte         = data[18];
            tmsGimbalParm.vlTFHighByte        = data[19];
            tmsGimbalParm.vlTFLowByte         = data[20];

            tmsGimbalParm.irVersionYear       = data[21];
            tmsGimbalParm.irVersionMonth      = data[22];
            tmsGimbalParm.irVersionDay        = data[23];

            tmsGimbalParm.vlVersionHighByte   = data[24];
            tmsGimbalParm.vlVersionLowByte    = data[25];

            Log.e(TAG,
                            "photoVideoMode---> " + tmsGimbalParm.photoVideoMode      + "\n" +
                            "imageMode---> " + tmsGimbalParm.imageMode           + "\n" +
                            "irFrameRate---> " + tmsGimbalParm.irFrameRate         + "\n" +
                            "lightingAlgorithm---> " + tmsGimbalParm.lightingAlgorithm   + "\n" +
                            "autoShutter---> " + tmsGimbalParm.autoShutter         + "\n" +
                            "imageMirror---> " + tmsGimbalParm.imageMirror         + "\n" +
                            "pseudoColor---> " + tmsGimbalParm.pseudoColor         + "\n" +
                            "isInsertTF---> " + tmsGimbalParm.isInsertTF          + "\n" +
                            "TempOverlay---> " + tmsGimbalParm.TempOverlay         + "\n" +
                            "timePositionOverlay---> " + tmsGimbalParm.timePositionOverlay + "\n" +
                            "TempArea---> " + tmsGimbalParm.TempArea            + "\n" +
                            "hotspotTrack---> " + tmsGimbalParm.hotspotTrack        + "\n" +
                            "photoMode---> " + tmsGimbalParm.photoMode           + "\n" +
                            "photoStorageMode---> " + tmsGimbalParm.photoStorageMode    + "\n" +
                            "videoStorageMode---> " + tmsGimbalParm.videoStorageMode    + "\n" +
                            "photoInterval---> " + tmsGimbalParm.photoInterval       + "\n" +
                            "numContinuousPhoto---> " + tmsGimbalParm.numContinuousPhoto  + "\n" +
                            "brightness---> " + tmsGimbalParm.brightness          + "\n" +
                            "contrast---> " + tmsGimbalParm.contrast            + "\n" +
                            "digitalZoom---> " + tmsGimbalParm.digitalZoom         + "\n" +
                            "denoiseLevel---> " + tmsGimbalParm.denoiseLevel        + "\n" +
                            "denoiseSwitch---> " + tmsGimbalParm.denoiseSwitch       + "\n" +
                            "enhanceDetailLevel---> " + tmsGimbalParm.enhanceDetailLevel  + "\n" +
                            "enhanceDetailSwitch---> " + tmsGimbalParm.enhanceDetailSwitch + "\n" +
                            "vlPhotoResolution---> " + tmsGimbalParm.vlPhotoResolution   + "\n" +
                            "vlVideoResolution---> " + tmsGimbalParm.vlVideoResolution   + "\n" +
                            "vlWB---> " + tmsGimbalParm.vlWB                + "\n" +
                            "vlEV---> " + tmsGimbalParm.vlEV                + "\n" +
                            "vlISO---> " + tmsGimbalParm.vlISO               + "\n" +
                            "irTFHighByte---> " + tmsGimbalParm.irTFHighByte        + "\n" +
                            "irTFLowByte---> " + tmsGimbalParm.irTFLowByte         + "\n" +
                            "vlTFHighByte---> " + tmsGimbalParm.vlTFHighByte        + "\n" +
                            "vlTFLowByte---> " + tmsGimbalParm.vlTFLowByte         + "\n" +
                            "irVersionYear---> " + tmsGimbalParm.irVersionYear       + "\n" +
                            "irVersionMonth---> " + tmsGimbalParm.irVersionMonth      + "\n" +
                            "irVersionDay---> " + tmsGimbalParm.irVersionDay        + "\n" +
                            "vlVersionHighByte---> " + tmsGimbalParm.vlVersionHighByte   + "\n" +
                            "vlVersionLowByte---> " +   tmsGimbalParm.vlVersionLowByte + "\n");
            return tmsGimbalParm;
        }
        return null;
    }

    public static int getBits(byte b,int start,int length) {
        int bit = (int)((b >> start) & (0xFF >> (8 - length)));
        return bit;
    }
}
