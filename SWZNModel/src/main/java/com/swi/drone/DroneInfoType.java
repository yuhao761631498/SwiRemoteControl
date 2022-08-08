package com.swi.drone;

/**
 * Created by xx on 2018/6/22.
 * 通过DroneInfoHelper获取到的数据类型
 */

public enum DroneInfoType {
    DRONE_TYPE(1, "飞机类型");

    private int  key;
    private String value;
    DroneInfoType(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
