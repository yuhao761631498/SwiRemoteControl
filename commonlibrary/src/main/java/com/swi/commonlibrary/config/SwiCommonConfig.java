package com.swi.commonlibrary.config;

import android.os.Environment;

/**
 * Copyright (C), 2020-2030
 * <p>
 * 功能描述:
 * <p>
 * 创建时间: 2022/8/2 14:06
 *
 * @author yuhao
 */
public class SwiCommonConfig {

    /**
     * <p>文件的基本目录</p>
     */
    public static String BaseDirectory = Environment
            .getExternalStorageDirectory() + "/swi";

    /**
     * 日志的存放文件夹
     */
    public static String LogDirectory = BaseDirectory + "/" + "log";
}
