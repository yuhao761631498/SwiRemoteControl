package com.swzn.drone;

public class ImgVersion_Z4B
{
    /**************************
     * 基带的版本
     */
    private String BaseBandVersion;

    /************
     * 固件版本
     */
    private String firmwareVersion;

    /********************
     * mcu2版本信息
     */
    private String mcu2_version;


    public String getBaseBandVersion() {
        return BaseBandVersion;
    }

    public void setBaseBandVersion(String baseBandVersion) {
        BaseBandVersion = baseBandVersion;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public String getMcu2_version() {
        return mcu2_version;
    }

    public void setMcu2_version(String mcu2_version) {
        this.mcu2_version = mcu2_version;
    }
}
