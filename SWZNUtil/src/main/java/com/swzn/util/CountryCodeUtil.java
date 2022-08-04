package com.swzn.util;

import android.content.Context;
import android.location.Location;
import android.telephony.TelephonyManager;

/**
 * Created by xx on 2018/1/6.
 * 获取国家码
 */

public class CountryCodeUtil {

    /**
     * 通过设置语言获取国家码
     * @param context
     * @return
     */
    public static String getCountryCodeByLanguage(Context context){
        if (context == null) {
            return null;
        }
        String countryCode = context.getResources().getConfiguration().locale.getCountry();
        return countryCode;
    }

    /**
     * 通过电话卡获取国家码
     * @param context
     * @return
     */
    public static String getCountryCodeByTelephony(Context context){
        if (context == null) {
            return null;
        }
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (manager != null) {
          return  manager.getSimCountryIso();
        }
        return null;
    }

}
