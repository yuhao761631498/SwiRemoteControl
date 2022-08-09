package com.swi.commonlibrary.utils;

import android.text.TextUtils;
import android.widget.Toast;

import com.swi.commonlibrary.application.SwiApplication;

/**
 * Copyright (C), 2020-2030
 * <p>
 * 功能描述:
 * <p>
 * 创建时间: 2022/8/9 14:42
 *
 * @author yuhao
 */
public class ToastUtils {

    public static void show(String str) {
        if (!TextUtils.isEmpty(str)) {
            Toast.makeText(SwiApplication.getSingleApp().getContext(), str, Toast.LENGTH_SHORT).show();
        }
    }
}
