package com.swzn.drone;

/**
 * Created by xx on 2018/6/2.
 * 飞机当前的分辨率
 * SMOOTH_MODE 流畅 240
 * SD_MODE 标准  480
 * HD_MODE 高清 720
 */

public enum PreviewResolution {
    SMOOTH_MODE(0), SD_MODE(1), HD_MODE(2), FHD_MODE(3);

    private int key;
    PreviewResolution(int i) {
        key = i;
    }

    public int getKey() {
        return key;
    }
}
