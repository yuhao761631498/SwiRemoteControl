package com.swzn.drone;

/**
 *  云台类型
 */

public enum GimbalType {
    O2Plan(0, "O2"),
    ByrdT_4k(1, "ByrdT_4k"),
    ByrdT_GTIR800(2, "ByrdT_GTIR800"),
    ByrdT_10X_Zoom(3, "ByrdT_10X_Zoom"),  //10倍变焦云台
    ByrdT_30X_Zoom(4, "ByrdT_30X_Zoom"),  //30倍变焦云台
    ByrdT_None_Zoom(5, "ByrdT_None_Zoom"),  //没挂云台
    ByrdT_Searchlight(6, "ByrdT_Searchlight"),  //探照灯
    ByrdT_Gas_Detector(7, "ByrdT_Gas_Detector"),  //气体检测器
    ByrdT_Loudspeaker(8, "ByrdT_Loudspeaker"),  //喊话器
    ByrdT_4ka(9, "ByrdT_4ka"),  //可以下视 -120度的4k云台
    ByrdT_TMS(10, "ByrdT_TMS"),  //三光吊舱
    ByrdT_30X_B_Zoom(11, "ByrdT_30X_B_Zoom"),  //新30倍云台
    ByrdT_10X_C_Zoom(12, "ByrdT_10X_C_Zoom"),  //新10倍云台
    ByrdT_35X_Zoom(13, "ByrdT_35X_Zoom"),
    ByrdT_4kc(14, "ByrdT_4kc"), //4KC云台
    ByrT_CJS(15, "ByrT_CJS"), //超景深
    ByrT_HP(16, "ByrT_HP");  //红鹏云台

    int key;
    String value;
    GimbalType(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static GimbalType get(int key){
        for(GimbalType e : GimbalType.values()){
            if (e.getKey() == key) {
                return e;
            }
        }
        return null;
    }
}
