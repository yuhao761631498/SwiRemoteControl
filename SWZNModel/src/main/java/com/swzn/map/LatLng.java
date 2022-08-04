package com.swzn.map;

import java.io.Serializable;

/**
 * Created by xx on 2018/8/9.
 */

public class LatLng implements Serializable {
    public final double latitude;
    public final double longitude;

    public LatLng(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
