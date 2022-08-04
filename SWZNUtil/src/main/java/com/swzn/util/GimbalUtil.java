package com.swzn.util;


import com.swzn.drone.GimbalType;

/**
 * 云台相关工具类
 */
public class GimbalUtil {

    /**
     * 根据云台定义类似获取App云台类型
     * @param type
     * @return
     */
    public static GimbalType getGimbalType(byte type){
        GimbalType gimbalType;
        switch (type) {
            case 0x09:
                gimbalType = GimbalType.O2Plan;
                break;
            case 0x03:
                gimbalType = GimbalType.ByrdT_4k;
                break;
            case 0x06:
                gimbalType = GimbalType.ByrdT_10X_Zoom;
                break;
            case 0x0A:
                gimbalType = GimbalType.ByrdT_GTIR800;
                break;
            case 0x0B:
                gimbalType = GimbalType.ByrdT_30X_Zoom;
                break;
            case 0x16:
                gimbalType = GimbalType.ByrdT_Searchlight;
                break;
            case 0x17:
                gimbalType = GimbalType.ByrdT_Gas_Detector;
                break;
            case 0x18:
                gimbalType = GimbalType.ByrdT_Loudspeaker;
                break;
            case 0x1A:
                gimbalType = GimbalType.ByrdT_4ka;
                break;
            case 0x12:
                gimbalType = GimbalType.ByrdT_TMS;
                break;
            case 0x13:
                gimbalType = GimbalType.ByrdT_30X_B_Zoom;
                break;
            case 0x14:
                gimbalType = GimbalType.ByrdT_10X_C_Zoom;
                break;
            case 0x1B:
                gimbalType = GimbalType.ByrdT_35X_Zoom;
                break;
            case 0x20:
                gimbalType = GimbalType.ByrdT_4kc;
                break;
            case (byte)0xE0:
                gimbalType = GimbalType.ByrT_HP;
                break;
            case (byte) 0xE1:
                gimbalType = GimbalType.ByrT_CJS;
                break;
            default:
                gimbalType = GimbalType.ByrdT_None_Zoom;
                break;
        }
        return gimbalType;
    }


    /**
     * 根据云台定义类似获取App云台类型名称
     * @param type
     * @return
     */
    public static String getGimbalTypeName(byte type){
        String gimbalType;
        switch (type) {
            case 0x09:
                gimbalType = "O2";
                break;
            case 0x03:
                gimbalType = "4K";
                break;
            case 0x06:
                gimbalType = "10X";
                break;
            case 0x0A:
                gimbalType = "IR800";
                break;
            case 0x0B:
                gimbalType = "30X";
                break;
            case 0x16:
                gimbalType = "Searchlight";
                break;
            case 0x17:
                gimbalType = "Gas_Detector";
                break;
            case 0x18:
                gimbalType = "Loudspeaker";
                break;
            case 0x1A:
                gimbalType = "4KA";
                break;
            case 0x12:
                gimbalType = "TMS";
                break;
            case 0x13:
                gimbalType = "30XB";
                break;
            case 0x14:
                gimbalType = "10XC";
                break;
            case 0x1B:
                gimbalType = "35X";
                break;
            case 0x20:
                gimbalType = "4KC";
                break;
            case (byte)0xE0:
                gimbalType = "HP";
                break;
            case (byte) 0xE1:
                gimbalType = "CJS";
                break;
            default:
                gimbalType = "NoneZoom";
                break;
        }
        return gimbalType;
    }
}
