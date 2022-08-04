package com.swzn.mount;


/**
 * 每个点单一气体检测记录数据结构
 */
public class SingleGasRecord {
    /**
     * 每次检测到气体列表
     */
    private GasBean gasBean;
    /**
     * 检测点纬度
     */
    private double lat;

    /**
     * 检测点经度
     */
    private double lng;

    /**
     * 检测点高度
     */
    private float height;

    /**
     *  图片路径
     */
    private String imagePath;

    /**
     * 气体异常类型
     */
    private byte gasExceedType;


    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public GasBean getGasBean() {
        return gasBean;
    }

    public void setGasBean(GasBean gasBean) {
        this.gasBean = gasBean;
    }

    public byte getGasExceedType() {
        return gasExceedType;
    }

    public void setGasExceedType(byte gasExceedType) {
        this.gasExceedType = gasExceedType;
    }
}
