package com.swzn.drone;

/**
 * Created by xx on 2018/6/2.
 * P1920_1080
 * P3840_2160
 * P4208_3120
 */

public enum PhotoResolution {
    P4208_3120(0), P3840_2160(1), P1920_1080(2);

    private int key;
    PhotoResolution(int i) {
        key = i;
    }

    public int getKey() {
        return key;
    }
}
