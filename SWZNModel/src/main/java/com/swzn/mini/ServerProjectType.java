package com.swzn.mini;

/**
 *
 * @author xx
 * @date 2018/2/5
 * 服务器端判断当前飞机的类型
 */

public enum ServerProjectType {
    MGP03_PROJECT(3, "normal"),
    MGP03_5_8G_PROJECT(4, "5.8G");

    int key;
    String value;
    ServerProjectType(int key, String value) {
        this.key = key;
        this.value = value;
    }
}
