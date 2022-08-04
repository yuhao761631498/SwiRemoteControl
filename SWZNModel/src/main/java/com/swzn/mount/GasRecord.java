package com.swzn.mount;



import java.util.List;

/**
 * 每个点所有气体检测记录数据结构
 */
public class GasRecord {
    /**
     * 每次检测到气体列表
     */
    private List<GasBean> gasBeans;
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
     * 气体超标类型
     */
    private byte gasExceedingType;


    public List<GasBean> getGasBeans() {
        return gasBeans;
    }

    public void setGasBeans(List<GasBean> gasBeans) {
        this.gasBeans = gasBeans;
    }

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

    public byte getGasExceedingType() {
        return gasExceedingType;
    }

    public void setGasExceedingType(byte gasExceedingType) {
        this.gasExceedingType = gasExceedingType;
    }
}
