package com.swzn.mount;

import java.util.List;


/**
 * 一次完整检测记录数据结构
 */
public class SingleGasRecordInfo {
    /**
     * 一次完整检测到气体列表
     */
    private List<SingleGasRecord> gasBeans;

    /**
     * 采样数量
     */
    private int samplingNum;

    /**
     * 检测面积
     */
    private double area;

    /**
     * 最大浓度
     */
    private float maxConcentration;

    /**
     * 最大浓度纬度
     */
    private double maxConcentrationLat;

    /**
     * 最大浓度经度
     */
    private double maxConcentrationLng;


    /**
     * 最小浓度
     */
    private float minConcentration;

    /**
     * 最小浓度纬度
     */
    private double minConcentrationLat;

    /**
     * 最小浓度经度
     */
    private double minConcentrationLng;

    /**
     * 平均浓度
     */
    private float  averageConcentration;

    /**
     * 单位
     */
    private String gasUnit;

    /**
     * 气体名称
     */
    private String gasName;

    /**
     * 生成报告时图片
     */
    private String imagePath;


    public List<SingleGasRecord> getGasBeans() {
        return gasBeans;
    }

    public void setGasBeans(List<SingleGasRecord> gasBeans) {
        this.gasBeans = gasBeans;
    }

    public float getMaxConcentration() {
        return maxConcentration;
    }

    public void setMaxConcentration(float maxConcentration) {
        this.maxConcentration = maxConcentration;
    }

    public String getGasUnit() {
        return gasUnit;
    }

    public void setGasUnit(String gasUnit) {
        this.gasUnit = gasUnit;
    }

    public String getGasName() {
        return gasName;
    }

    public void setGasName(String gasName) {
        this.gasName = gasName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getSamplingNum() {
        return samplingNum;
    }

    public void setSamplingNum(int samplingNum) {
        this.samplingNum = samplingNum;
    }

    public float getMinConcentration() {
        return minConcentration;
    }

    public void setMinConcentration(float minConcentration) {
        this.minConcentration = minConcentration;
    }

    public float getAverageConcentration() {
        return averageConcentration;
    }

    public void setAverageConcentration(float averageConcentration) {
        this.averageConcentration = averageConcentration;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getMaxConcentrationLat() {
        return maxConcentrationLat;
    }

    public void setMaxConcentrationLat(double maxConcentrationLat) {
        this.maxConcentrationLat = maxConcentrationLat;
    }

    public double getMaxConcentrationLng() {
        return maxConcentrationLng;
    }

    public void setMaxConcentrationLng(double maxConcentrationLng) {
        this.maxConcentrationLng = maxConcentrationLng;
    }

    public double getMinConcentrationLat() {
        return minConcentrationLat;
    }

    public void setMinConcentrationLat(double minConcentrationLat) {
        this.minConcentrationLat = minConcentrationLat;
    }

    public double getMinConcentrationLng() {
        return minConcentrationLng;
    }

    public void setMinConcentrationLng(double minConcentrationLng) {
        this.minConcentrationLng = minConcentrationLng;
    }
}
