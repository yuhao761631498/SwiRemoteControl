package com.swi.drone;

/**
 * Created by xx on 2018/6/2.
 *  视频尺寸
 *  P720_30(0),  720视频尺寸，30帧率
 *  P720_60(1),
 *  P1080_30(2),
 *  P1080_60(3),
 *  P2K_25(4),
 *  P2K_30(5)
 *  P4K_25(6)
 *  P4K_30(7);
 */

public enum VideoSize {
    P720_30(0), P720_60(1), P1080_30(2), P1080_60(3),
    P2K_25(4), P2K_30(5), P4K_25(6),P4K_30(7);
    private int key;
    VideoSize(int i) {
        key = i;
    }

    public int getKey() {
        return key;
    }
}
