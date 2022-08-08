package com.swi.drone;

import java.io.Serializable;
import java.util.List;


public class FirmwareUpdateBean implements Serializable{
    /**
     * version_code": "8", //版本编号
     "version": "1.2.3", //版本号
     "md5": "dce802104f3589a2fa778b8652e22a66 ", //当文件的类型type=4(飞控)或8（图传中继版本）时必需
     "flag": "1", //强制升级标志，1：强制升级；默认为0
     "download_file":"http://qiniu_domain/cms/gdu_tech/1500350066211.hex",
     "version_log": "版本升级日志" //含中英文，即把version_log和version_log_en字段拼接
     "create_time": "1503304374",
     "update_time": "1503304374",
     "update_array" {}  针对飞控升级，服务器端会返回大于当前版本的所有版本信息

     */
    private int version_code;

    /**
     * <p>版本号</p>
     */
    private String version;

    /**
     * <p>保存原始版本号,为了解决OTA转换为时间后的精度损失</p>
     */
    private String rawVersion;

    /**
     * <p>md5</p>
     */
    private String md5;

    /**
     * <p>强制升级标志</p>
     */
    private String flag;

    /**
     * <p>文件地址</p>
     */
    private String download_file;

    /**
     * <p>版本升级日志</p>
     */
    private String version_log;


    private String create_time;


    private String update_time;

    /**
     * 固件大小
     */
    private int size;

    /**
     * 文件类型 3:禁飞区文件，4:飞控，5:云台, 6:智能电池,7:系统,8:图传中继版本
     */
    private int type;

    /**
     * 供智能电池使用 仅当type=6时有效 1：精锐电池，2：赛能电池, 3: 迪比科电池
     */
    private int sub_type;

    private String curVersion;

    private String typeName;


    /*该固件对应的飞机类型，用于上传时核对飞机类型*/
    private int planType;

    /**
     * 针对飞控升级，服务器端会返回大于当前版本的所有版本信息
     */
    private List<FirmwareUpdateBean> update_array;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }



    public String getCurVersion() {
        return curVersion;
    }

    public void setCurVersion(String curVersion) {
        this.curVersion = curVersion;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getVersion_code() {
        return version_code;
    }

    public void setVersion_code(int version_code) {
        this.version_code = version_code;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getDownload_file() {
        return download_file;
    }

    public void setDownload_file(String download_file) {
        this.download_file = download_file;
    }

    public String getVersion_log() {
        return version_log;
    }

    public void setVersion_log(String version_log) {
        this.version_log = version_log;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }


    public int getSub_type() {
        return sub_type;
    }

    public void setSub_type(int sub_type) {
        this.sub_type = sub_type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getRawVersion() {
        return rawVersion;
    }

    public void setRawVersion(String rawVersion) {
        this.rawVersion = rawVersion;
    }

    public List<FirmwareUpdateBean> getUpdate_array() {
        return update_array;
    }

    public void setUpdate_array(List<FirmwareUpdateBean> update_array) {
        this.update_array = update_array;
    }


    public int getPlanType() {
        return planType;
    }

    public void setPlanType(int planType) {
        this.planType = planType;
    }
}
