package com.swi.drone;

public class TMSGimbalState {

    /*最高温温度值*/
    private short highestTemp;

    /*最低温温度值*/
    private short lowestTemp;

    /*光标温温度值*/
    private short cursorPointTemp;

    /*区域平均温度值*/
    private short areaAvgTemp;

    /*最高温坐标X*/
    private short highestTempPoint_X;

    /*最高温坐标Y*/
    private short highestTempPoint_Y;

    /*最低温坐标X*/
    private short lowestTempPoint_X;

    /*最低温坐标Y*/
    private short lowestTempPoint_Y;

    /*光标温坐标X*/
    private short cursorPoint_X;

    /*光标温坐标Y*/
    private short cursorPoint_Y;

    /*区域平均温坐标X*/
    private short areaAvg_X;

    /*区域平均温坐标Y*/
    private short areaAvg_Y;

    /*红外：TF卡剩余容量*/
    private short irFreeCapacity;

    /*可见光：TF卡剩余容量*/
    private short visibleLightFreeCapacity;

    /*当前相机拍照模式*/
    private byte curPhotoMode;

    /*当前相机录像模式*/
    private byte curVideoMode;

    public short getHighestTemp() {
        return highestTemp;
    }

    public void setHighestTemp(short highestTemp) {
        this.highestTemp = highestTemp;
    }

    public short getLowestTemp() {
        return lowestTemp;
    }

    public void setLowestTemp(short lowestTemp) {
        this.lowestTemp = lowestTemp;
    }

    public short getCursorPointTemp() {
        return cursorPointTemp;
    }

    public void setCursorPointTemp(short cursorPointTemp) {
        this.cursorPointTemp = cursorPointTemp;
    }

    public short getAreaAvgTemp() {
        return areaAvgTemp;
    }

    public void setAreaAvgTemp(short areaAvgTemp) {
        this.areaAvgTemp = areaAvgTemp;
    }

    public short getHighestTempPoint_X() {
        return highestTempPoint_X;
    }

    public void setHighestTempPoint_X(short highestTempPoint_X) {
        this.highestTempPoint_X = highestTempPoint_X;
    }

    public short getHighestTempPoint_Y() {
        return highestTempPoint_Y;
    }

    public void setHighestTempPoint_Y(short highestTempPoint_Y) {
        this.highestTempPoint_Y = highestTempPoint_Y;
    }

    public short getLowestTempPoint_X() {
        return lowestTempPoint_X;
    }

    public void setLowestTempPoint_X(short lowestTempPoint_X) {
        this.lowestTempPoint_X = lowestTempPoint_X;
    }

    public short getLowestTempPoint_Y() {
        return lowestTempPoint_Y;
    }

    public void setLowestTempPoint_Y(short lowestTempPoint_Y) {
        this.lowestTempPoint_Y = lowestTempPoint_Y;
    }

    public short getCursorPoint_X() {
        return cursorPoint_X;
    }

    public void setCursorPoint_X(short cursorPoint_X) {
        this.cursorPoint_X = cursorPoint_X;
    }

    public short getCursorPoint_Y() {
        return cursorPoint_Y;
    }

    public void setCursorPoint_Y(short cursorPoint_Y) {
        this.cursorPoint_Y = cursorPoint_Y;
    }

    public short getAreaAvg_X() {
        return areaAvg_X;
    }

    public void setAreaAvg_X(short areaAvg_X) {
        this.areaAvg_X = areaAvg_X;
    }

    public short getAreaAvg_Y() {
        return areaAvg_Y;
    }

    public void setAreaAvg_Y(short areaAvg_Y) {
        this.areaAvg_Y = areaAvg_Y;
    }

    public byte getCurPhotoMode() {
        return curPhotoMode;
    }

    public void setCurPhotoMode(byte curPhotoMode) {
        this.curPhotoMode = curPhotoMode;
    }

}
