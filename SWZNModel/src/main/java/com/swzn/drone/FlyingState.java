package com.swzn.drone;

/**
 * Created by xx on 2018/6/1.
 */

public enum FlyingState {
    Fling(0), Ground(1), Takeoff(2), Landing(3), Hovering(4), OpticalFlowForceLand(5);

    int key;
    FlyingState(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static FlyingState get(int key){
        for(FlyingState e : FlyingState.values()){
            if (e.getKey() == key) {
                return e;
            }
        }
        return null;
    }
}
