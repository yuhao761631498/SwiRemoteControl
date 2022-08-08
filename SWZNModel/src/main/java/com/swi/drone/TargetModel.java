package com.swi.drone;

import android.graphics.Point;

import com.swi.map.LatLng;


/**
 * Created by xx on 2018/8/18.
 */

public class TargetModel {
    private int id;
    private int type;
    private Point point;
    private LatLng latLng;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

}
