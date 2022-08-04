package com.swzn.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by xx on 2016/6/20.
 * <ul>
 * <li>http访问缓存工具类</li>
 * </ul>
 */
public class HttpCacheDao {
    private final String sp_Name = "sp_Name";
    SharedPreferences sharedPreferences;

    public HttpCacheDao(Context context) {
        sharedPreferences = SharePreferencesUtil.getSharedPreferences(context, sp_Name, 0);
    }


    /**
     * <p>把url和 args拼接起来</p>
     *
     * @param url
     * @param args
     * @return
     */
    private String getCacheKey(String url, Map<String, String> args) {
        StringBuffer stringBuffer = new StringBuffer(url);
        stringBuffer.append("?");
        if (args != null && args.size() > 0) {
            Iterator<Map.Entry<String, String>> iter = args.entrySet().iterator();
            StringBuilder sb = new StringBuilder();
            while (iter.hasNext()) {
                Map.Entry<String, String> entry = iter.next();
                sb.append(entry.getKey());
                sb.append("=").append(entry.getValue());
                sb.append("&");
            }
            sb.deleteCharAt(sb.lastIndexOf("&"));
        }
        return stringBuffer.toString();
    }

    /**
     * <p>获取本地缓存的Http</p>
     *
     * @return
     */
    public String getHttpCache(String url, Map<String, String> args) {
        String key = getCacheKey(url, args);
        return sharedPreferences.getString(key, "");
    }

    /**
     * <p>保持 缓存 到本地</p>
     *
     * @param url
     * @param args
     */
    public void saveHttpCache(String url, Map<String, String> args, String result) {
        String key = getCacheKey(url, args);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, result);
        editor.commit();
    }

    /**
     * <p>清除所有的缓存</p>
     */
    public void clearHttpCache() {
        sharedPreferences.edit().clear().commit();
    }
}
