package com.swi.drone;

import java.io.Serializable;

/**
 * Created by xx on 2018/9/5.
 * 目标检测对象
 */

public class TargetMode implements Serializable{

    private int id;        //目标id
    private byte state;    //目标状态
    private short leftX;   //目标左上角X
    private short leftY;   //目标左上角Y
    private short width;   //目标宽
    private short height;  //目标高
    private short totalNum;//检测目标总数
    private long lat;       //目标纬度
    private long lng;       //目标经度
    private long elevation;       //目标海拔高度
    private String imgPath; //目标检测时一帧图片的路径
    private String imgName; //目标检测时图片的名称,时间戳形式
    private long createTime;      //目标检测时间
    private boolean isConfirmed;  //目标是否确认
    private byte lightType;       //光类型  0x00可见光/  0x01红外
    private byte locateType;      //目标定位类型  0x01循环检  0x02单点定位 0x03跟踪
    private short targetType;     //目标类别
    private short targetConfidence; //目标置信度
    private byte flawType;          //缺陷标志位
    private byte project;           //项目类型  1：电网项目  2：消防项目

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public short getLeftX() {
        return leftX;
    }

    public void setLeftX(short leftX) {
        this.leftX = leftX;
    }

    public short getLeftY() {
        return leftY;
    }

    public void setLeftY(short leftY) {
        this.leftY = leftY;
    }

    public short getWidth() {
        return width;
    }

    public void setWidth(short width) {
        this.width = width;
    }

    public short getHeight() {
        return height;
    }

    public void setHeight(short height) {
        this.height = height;
    }

    public short getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(short totalNum) {
        this.totalNum = totalNum;
    }

    public long getLat() {
        return lat;
    }

    public void setLat(long lat) {
        this.lat = lat;
    }

    public long getLng() {
        return lng;
    }

    public void setLng(long lng) {
        this.lng = lng;
    }

    public long getElevation() {
        return elevation;
    }

    public void setElevation(long elevation) {
        this.elevation = elevation;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public byte getLightType() {
        return lightType;
    }

    public void setLightType(byte lightType) {
        this.lightType = lightType;
    }

    public byte getLocateType() {
        return locateType;
    }

    public void setLocateType(byte locateType) {
        this.locateType = locateType;
    }

    public short getTargetType() {
        return targetType;
    }

    public void setTargetType(short targetType) {
        this.targetType = targetType;
    }

    public short getTargetConfidence() {
        return targetConfidence;
    }

    public void setTargetConfidence(short targetConfidence) {
        this.targetConfidence = targetConfidence;
    }

    public byte getFlawType() {
        return flawType;
    }

    public void setFlawType(byte flawType) {
        this.flawType = flawType;
    }

    public byte getProject() {
        return project;
    }

    public void setProject(byte project) {
        this.project = project;
    }
}
