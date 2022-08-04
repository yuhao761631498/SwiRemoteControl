package com.swi.datalinklibrary.aoa;

import android.content.Context;

/**
 * Copyright (C), 2020-2030, 武汉中旗生物医疗电子有限公司
 * <p>
 * 功能描述:
 * <p>
 * 创建时间: 2022/8/3 14:37
 *
 * @author yuhao
 */
public class SwiAccessory extends AoaDataLink{

    public SwiAccessory(Context context) {
        super(context);
    }

    @Override
    public void parseDataLinkData(byte[] buffer, int length) {

    }
}
