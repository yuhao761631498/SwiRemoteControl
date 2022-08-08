package com.swi.drone;

/**
 *
 * @author xx
 * @date 2018/6/1
 * 飞机的基本数据
 */

public class DroneInfo {

    /**
     * 卫星颗数
     */
    private int satelliteNum;

    /**
     * 飞机电量
     */
    private int dronePower;

    /**
     * 遥控器电量
     */
    private int rcPower;

    /**
     * 飞行距离
     */
    private float distance;

    /**
     * 飞行高度
     */
    private float height;

    /**
     * 经度
     */
    private double longitude;

    /**
     * 纬度
     */
    private double latitude;

    /**
     * 飞行状态
     */
    private FlyingState flyingState;

    public int getSatelliteNum() {
        return satelliteNum;
    }

    public void setSatelliteNum(int satelliteNum) {
        this.satelliteNum = satelliteNum;
    }

    public int getDronePower() {
        return dronePower;
    }

    public void setDronePower(int dronePower) {
        this.dronePower = dronePower;
    }

    public int getRcPower() {
        return rcPower;
    }

    public void setRcPower(int rcPower) {
        this.rcPower = rcPower;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public FlyingState getFlyingState() {
        return flyingState;
    }

    public void setFlyingState(FlyingState flyingState) {
        this.flyingState = flyingState;
    }

    public String getString(){
        StringBuilder sb = new StringBuilder();
        sb.append("卫星：" + satelliteNum);
        sb.append("飞机电量: " + dronePower);
        sb.append("遥控器电量: " + rcPower);
        sb.append("飞行距离: " + distance);
        sb.append("飞行高度: " + height);
        sb.append("经度: " + longitude);
        sb.append("纬度: " + latitude);
        sb.append("飞行状态: " + flyingState);
        return sb.toString();
    }


}
