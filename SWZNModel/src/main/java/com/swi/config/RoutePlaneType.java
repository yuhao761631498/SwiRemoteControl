package com.swi.config;
public enum RoutePlaneType {
    FLIGHT_LINE(3),
    FLIGHT_SIDE(2),
    FLIGHT_CUBE(1),
    ELEC_POLE_COLLECT(4),
    ELEC_CHANNEL_ROUTE(5),
    ELEC_TREE_ROUTE(6),
    ELEC_POINT_COLLECT(7),
    ELEC_ROUND_ROUTE(8),
    ELEC_PRECISE_ROUTE(9),
    FLIGHT_NONE(10),
    DEVICE_RECOGNIZE(11);

    int value;
    RoutePlaneType(int value){
        this.value=value;
    }

    public int getValue(){
        return value;
    }
}
