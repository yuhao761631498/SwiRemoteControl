package com.swzn.drone;

public class Z4bRfState {

    private final static String[] Z4B_RF_BANDWIDTH = {"2.31", "3.08", "", "3.86",  "4.63", "6.17", "",
        "7.71", "9.25", "", "", "", "13.88", "", "", "23.13"};

    /*天空端信号强度*/
    private byte skySignalQuality;

    /*天空端发射功率*/
    private int skyTX_POWER;

    /*天空端信噪比*/
    private int skySNR;

    /*地面端信号强度*/
    private byte grdSignalQuality;

    /*地面端发射功率*/
    private int grdTX_POWER;

    /*地面端信噪比*/
    private int grdSNR;

    /*下行带宽模式*/
    private byte rfBandwidth;

    /*当前频段*/
    private int rfFrequency;

    private boolean isFreqSelfAdaptive;

    public byte getSkySignalQuality() {
        return skySignalQuality;
    }

    public void setSkySignalQuality(byte skySignalQuality) {
        this.skySignalQuality = skySignalQuality;
    }

    public int getSkyTX_POWER() {
        return skyTX_POWER;
    }

    public void setSkyTX_POWER(int skyTX_POWER) {
        this.skyTX_POWER = skyTX_POWER;
    }

    public int getSkySNR() {
        return skySNR;
    }

    public void setSkySNR(int skySNR) {
        this.skySNR = skySNR;
    }

    public byte getGrdSignalQuality() {
        return grdSignalQuality;
    }

    public void setGrdSignalQuality(byte grdSignalQuality) {
        this.grdSignalQuality = grdSignalQuality;
    }

    public int getGrdTX_POWER() {
        return grdTX_POWER;
    }

    public void setGrdTX_POWER(int grdTX_POWER) {
        this.grdTX_POWER = grdTX_POWER;
    }

    public int getGrdSNR() {
        return grdSNR;
    }

    public void setGrdSNR(int grdSNR) {
        this.grdSNR = grdSNR;
    }

    public byte getRfBandwidth() {
        return rfBandwidth;
    }

    public void setRfBandwidth(byte rfBandwidth) {
        this.rfBandwidth = rfBandwidth;
    }

    public int getRfFrequency() {
        return rfFrequency;
    }

    public void setRfFrequency(int rfFrequency) {
        this.rfFrequency = rfFrequency;
    }

    public String getRfBandwidthString(){
        try {
            return Z4B_RF_BANDWIDTH[this.rfBandwidth];
        } catch (ArrayIndexOutOfBoundsException e){
            return "";
        }
    }

    public boolean isFreqSelfAdaptive() {
        return isFreqSelfAdaptive;
    }

    public void setFreqSelfAdaptive(boolean freqSelfAdaptive) {
        isFreqSelfAdaptive = freqSelfAdaptive;
    }

}
