package com.swzn.drone;

/**
 * 飞机异常
 */
public enum WarnMode {
    MAGNETIC_ABNORMAL("指南针异常"),
    ENVIRONMENT_ABNORMAL("环境磁干扰大"),
    GLASS_ABNORMAL("气压计异常"),
    LIGHT_STREAM_ABNORMAL("光流异常"),
    ULTRASONIC_ABNORMAL("超声异常"),
    IMU_ABNORMAL("IMU异常"),
    EYES_ABNORMAL("视觉系统异常"),
    CLOUND_ABNORMAL("云台自检异常"),
    BATTERY_ABNORMAL("电池故障"),
    HADINNOFLY("禁飞区");

    WarnMode(String value) {

    }
}
