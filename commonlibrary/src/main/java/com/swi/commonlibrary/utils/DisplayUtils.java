package com.swi.commonlibrary.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

/**
 * Copyright (C), 2020-2030, 武汉中旗生物医疗电子有限公司
 * <p>
 * 功能描述:
 * <p>
 * 创建时间: 2022/7/21 10:02
 *
 * @author yuhao
 */
public class DisplayUtils {

    /**
     * 显示器的绝对显示宽高
     *
     * @param context
     */
    public static int[] getScreenRelatedInformation(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int[] screenInfo = new int[2];
        if (windowManager != null) {

            DisplayMetrics outMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(outMetrics);
            int widthPixels = outMetrics.widthPixels;
            int heightPixels = outMetrics.heightPixels;
            int densityDpi = outMetrics.densityDpi;
            float density = outMetrics.density;
            float scaledDensity = outMetrics.scaledDensity;
            //可用显示大小的绝对宽度（以像素为单位）。
            //可用显示大小的绝对高度（以像素为单位）。
            //屏幕密度表示为每英寸点数。
            //显示器的逻辑密度。
            //显示屏上显示的字体缩放系数。
            Log.d("display", "widthPixels = " + widthPixels + ",heightPixels = " + heightPixels + "\n" +
                    ",densityDpi = " + densityDpi + "\n" +
                    ",density = " + density + ",scaledDensity = " + scaledDensity);
            screenInfo[0] = widthPixels;
            screenInfo[1] = heightPixels;
        }
        return screenInfo;
    }

    public static int[] getRealScreenRelatedInformation(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int[] screenInfo = new int[2];
        if (windowManager != null) {
            DisplayMetrics outMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getRealMetrics(outMetrics);
            int widthPixels = outMetrics.widthPixels;
            int heightPixels = outMetrics.heightPixels;
            int densityDpi = outMetrics.densityDpi;
            float density = outMetrics.density;
            float scaledDensity = outMetrics.scaledDensity;
            //可用显示大小的绝对宽度（以像素为单位）。
            //可用显示大小的绝对高度（以像素为单位）。
            //屏幕密度表示为每英寸点数。
            //显示器的逻辑密度。
            //显示屏上显示的字体缩放系数。
            Log.d("display", "widthPixels = " + widthPixels + ",heightPixels = " + heightPixels + "\n" +
                    ",densityDpi = " + densityDpi + "\n" +
                    ",density = " + density + ",scaledDensity = " + scaledDensity);
            screenInfo[0] = widthPixels;
            screenInfo[1] = heightPixels;
        }
        return screenInfo;
    }
}
