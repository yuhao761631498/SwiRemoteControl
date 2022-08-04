package com.swzn.drone;

/**
 * Created by xx on 2018/6/26.
 * 相机属性类型 EV, ISO, WB
 */

public enum CameraParamType {
    EV(1), ISO(2), WB(3);

    int key;
    CameraParamType(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
