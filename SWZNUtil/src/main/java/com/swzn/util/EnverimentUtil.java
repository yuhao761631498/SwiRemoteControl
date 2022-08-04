package com.swzn.util;

import java.util.Locale;

/**
 * Created by xx on 2017/11/18.
 */

public class EnverimentUtil {
    /**
     * <p>判断当前环境是否为中文环境</p>
     *
     * @return
     */
    public static boolean isCN() {
        String country = Locale.getDefault().getCountry();
        if (country.equals("CN"))
            return true;
        return false;
    }
}
