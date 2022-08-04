package com.swzn.drone;

/**
 * Created by xx on 2018/9/5.
 * 目标检测对象
 */

public class FireTargetMode extends TargetMode{

    private double GPS_Lat = -1;
    private double GPS_Lon = -1;
    private int fireArea;

    public double getGPS_Lat() {
        return GPS_Lat;
    }

    public void setGPS_Lat(double GPS_Lat) {
        this.GPS_Lat = GPS_Lat;
    }

    public double getGPS_Lon() {
        return GPS_Lon;
    }

    public void setGPS_Lon(double GPS_Lon) {
        this.GPS_Lon = GPS_Lon;
    }

    public int getFireArea() {
        return fireArea;
    }

    public void setFireArea(int fireArea) {
        this.fireArea = fireArea;
    }
}
