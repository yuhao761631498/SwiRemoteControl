package com.swzn.drone;

/**
 *
 * @date 2018/6/4
 *
 * 飞行器异常
 */

public class DroneException {

    /**
     * IMU异常
     */
    public  boolean IMU_EXCEPTION = false;

    /**
     * 气压计异常
     */
    public boolean BAROMETER_EXCEPTION = false;

    /**
     * 磁力计异常
     */
    public boolean MAGNETOMETER_EXCEPTION = false;

    /**
     * 视觉避障异常
     */
    public boolean VISION_SYS_EXCEPTION = false;

    /**
     * 智能电池异常
     */
    public boolean SMART_BATTERY_SYS_EXCEPTION = false;

    /**
     * GPS异常
     */
    public boolean GPS_EXCEPTION = false;

    /**
     * 光流系统异常
     */
    public boolean OPTICAL_EXCEPTION = false;

    /**
     * 超声异常
     */
    public boolean ULTRASONIC_EXCEPTION = false;

    /**
     * 云台异常
     */
    public boolean GIMBAL_SYS_EXCEPTION = false;

    /**
     * 禁飞区
     */
    public boolean FORBIDEN_ZONE_EXCEPTION = false;

    public String getString(){
        StringBuilder sb = new StringBuilder();
        sb.append("IMU异常: " + IMU_EXCEPTION);
        sb.append("气压计异常: " + BAROMETER_EXCEPTION);
        sb.append("磁力计异常: " + MAGNETOMETER_EXCEPTION);
        sb.append("视觉避障异常: " + VISION_SYS_EXCEPTION);
        sb.append("智能电池异常: " + SMART_BATTERY_SYS_EXCEPTION);
        sb.append("GPS异常: " + GPS_EXCEPTION);
        sb.append("超声异常: " + ULTRASONIC_EXCEPTION);
        sb.append("云台异常: " + GIMBAL_SYS_EXCEPTION);
        sb.append("禁飞区: " + FORBIDEN_ZONE_EXCEPTION);
        return sb.toString();
    }

}
