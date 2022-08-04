package com.swzn.drone;

/**
 * 网络RTK的连接状态
 */
public enum RTKNetConnectStatus {
    DISCONNECT(1, "未连接"),
    SERVER_CONNECTED(2, "服务器连接成功"),
    SERVER_COMMUNICATE(3, "服务器通信成功"),
    SERVER_UNAUTHORIZED(4, "服务器未授权"),
    CONNECTING(5, "连接中"),
    CONNECTED(6, "已连接"),
    CONNECT_FAILED(7, "连接失败"),
    CONNECT_TIMEOUT(8, "连接超时");

    private int key;
    private String value;
    RTKNetConnectStatus(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue(){
        return value;
    }
}
