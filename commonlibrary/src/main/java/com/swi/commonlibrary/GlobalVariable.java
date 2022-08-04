package com.swi.commonlibrary;

import android.Manifest;

/**
 * Copyright (C), 2020-2030, 武汉中旗生物医疗电子有限公司
 * <p>
 * 功能描述:
 * <p>
 * 创建时间: 2022/7/21 11:12
 *
 * @author yuhao
 */
public class GlobalVariable {
    /**
     * 接受数传的端口号
     */
    public static int UDP_SOCKET_PORT = 0;

    /**
     * 接受图传的端口号
     */
    public static int UDP_SOCKET_IMG_PORT = 7078;


    public static final String ACCESSORY_MODE1 = "AR8020";

    public static final String ACCESSORY_MODE2 = "gdu_z4b";


    public static int screenWidth;


    public static int screenHeight;


    public static String[] getAppNeedPermissions() {
        String[] permissions = new String[5];
        permissions[0] = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        permissions[1] = Manifest.permission.READ_EXTERNAL_STORAGE;
        permissions[2] = Manifest.permission.ACCESS_FINE_LOCATION;
        permissions[3] = Manifest.permission.ACCESS_NETWORK_STATE;
        permissions[4] = Manifest.permission.ACCESS_COARSE_LOCATION;
        return permissions;
    }
}
