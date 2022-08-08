package com.swi.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by xx on 2017/11/17.
 */

public class SharePreferencesUtil {

    public static SharedPreferences getSharedPreferences(Context context, String sha, int mode){
        return context.getSharedPreferences(sha, mode);
    }
}
