package com.swzn.drone;

/**
 *
 * @author xx
 * @date 2018/2/5
 * 固件的版本
 */

public enum FirmwareType {
    FLY_FIRMWARE(4, "飞控", "fly"),
    CLOUD_FIRMWARE(5, "云台", "cloud"),
    BATTER_FIRMWARE(6, "智能电池", "battery"),
    OTA_FIRMWARE(7, "OTA", "OTA"),
    AP12_FIRMWARE(8, "图传中继", "AP12"),
    GIMBAL_4K_FIRMWARE(12, "saga4K云台", "gimbal4k"),
    GIMBAL_10X_FIRMWARE(13, "saga10倍云台", "gimbal10x"),
    GIMBAL_30X_FIRMWARE(14, "saga30倍云台", "gimbal30x"),//该云台不再维护
    GIMBAL_GTIR800_FIRMWARE(15, "sagaGTIR800红外云台", "gimbalGtir800"),
    GIMBAL_4Ka_FIRMWARE(20, "saga4Ka云台", "gimbal4ka"),
    GIMBAL_30X_B_FIRMWARE(21, "新30倍云台", "gimbal30x_B"),
    GIMBAL_35X_FIRMWARE(22, "35倍云台", "gimbal35x"),
    GIMBAL_10X_C_FIRMWARE(24, "新10倍云台", "gimbal10x_c"),
    GIMBAL_TMC_FIRMWARE(100, "三光云台", "gimbalTMC"),//三光目前么有升级程序，在服务器
    GIMBAL_Loudspeaker_FIRMWARE(25, "喊话器", "gimbalLoudspeaker"),
    GIMBAL_Searchlight_FIRMWARE(26, "探照灯", "gimbalSearchlight"),
    GIMBAL_GasDetector_FIRMWARE(27, "气体检测器", "gimbalGasDetector"),
    SAGA_RCA_FIRMWARE(28, "GR111", "GR111"),
    COMPUTE_STICK_FIRMWARE(30, "计算棒", "computeStick"), //视觉计算棒
    GIMBAL_4KC_FIRMWARE(31, "4KC云台", "gimbal4kC"); //4kc云台

    int key;
    String value;
    String enValue;
    FirmwareType(int key, String value, String enValue){
        this.key = key;
        this.value = value;
        this.enValue = enValue;
    }

    public int getKey(){
        return key;
    }

    public String getValue(){
        return value;
    }

    public String getEnValue(){
        return enValue;
    }

    public static FirmwareType get(int key){
        for(FirmwareType e : FirmwareType.values()){
            if (e.getKey() == key) {
                return e;
            }
        }
        return null;
    }
}
