package com.swzn.drone;

/**
 * Created by xx on 2018/10/19.
 * 光类型  可见光/红外光
 */

public enum LightType {
    VISIBLE_LIGHT(0, "visible light"),
    INFRARED_LIGHT(1, "infrared light");

    int key;
    String value;
    LightType(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
