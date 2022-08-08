package com.swi.baselibrary;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Copyright (C), 2020-2030
 * <p>
 * 功能描述: Activity的基类
 * <p>
 * 创建时间: 2022/7/19 16:19
 *
 * @author yuhao
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = getLayoutId();
        setContentView(layoutId);
        initView(savedInstanceState);
        initListener();
        initData();
    }

    public abstract int getLayoutId();

    public abstract void initView(Bundle savedInstanceState);

    public abstract void initListener();

    public abstract void initData();

}
