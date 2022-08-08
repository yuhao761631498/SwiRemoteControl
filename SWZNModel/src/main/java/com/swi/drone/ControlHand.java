package com.swi.drone;

/**
 * HAND_AMERICA  美国手
 * HAND_JAPAN 日本手
 * HAND_CHINA 中国手
 */

public enum ControlHand {
    HAND_AMERICA(1), HAND_JAPAN(2), HAND_CHINA(3), HAND_NONE(4);

    int key;
    ControlHand(int i) {
        key = i;
    }

    public int getKey() {
        return key;
    }
}
