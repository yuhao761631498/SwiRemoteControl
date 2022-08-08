package com.swi.drone;

/**
 * RTK登录需要的参数
 */
public class RTKLoginParam {
    /**
     * ip地址
     */
    private String ip;
    /**
     * 端口
     */
    private int port;
    /**
     * 登录名
     */
    private String id;
    /**
     * 密码
     */
    private String pw;

    /**
     * 挂载点
     */
    private String mp;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getMp() {
        return mp;
    }

    public void setMp(String mp) {
        this.mp = mp;
    }
}
