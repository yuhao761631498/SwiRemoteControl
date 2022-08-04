package com.swzn.mount;

/**
 * 气体类型
 */
public enum GasType {
    SO2((byte)1, "SO2"),
    NO2((byte)2, "NO2"),
    CO((byte)3, "CO"),
    O3((byte)4, "O3"),
    AQI((byte)5, "AQI"),
    PM2_5((byte)6, "PM2.5"),
    PM10((byte)7, "PM10");

    byte key;
    String value;
    GasType(byte key, String value) {
        this.key = key;
        this.value = value;
    }

    public byte getKey() {
        return key;
    }

    public void setKey(byte key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }



}
