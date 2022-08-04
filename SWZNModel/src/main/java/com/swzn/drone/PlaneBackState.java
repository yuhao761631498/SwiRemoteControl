package com.swzn.drone;

/**
 * Created by xx on 2018/1/4.
 * 飞机返航状态
 */

public enum PlaneBackState {
    BACK_DEFAULT(0, "默认"),
    BACK_FAILED(1, "返航失败"),
    BACKING(2, "返航中"),
    BACK_LOW_POWER(3,"低电返航失败"),
    CONFIRM_CIRCLE_CENTER(16,"已确定圆心" ),
    MOVE_TO_SET_CENTER(32, "移动至设定位置中"),
    SURROUNDING_CENTER(64, "环绕中"),
    SURROUND_ARRIVED(128,"环绕视频到达指定位置，可以开始环绕");

    int key;
    String value;
    PlaneBackState(int key, String value) {
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
