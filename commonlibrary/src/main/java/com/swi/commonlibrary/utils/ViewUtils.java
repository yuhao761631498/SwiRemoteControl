package com.swi.commonlibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;


/**
 * Created by xx on 2016/5/23.
 */
public class ViewUtils {

    private static final float ROUND_CEIL = 0.5f;
    private static DisplayMetrics sDisplayMetrics;

    /**
     * 初始化操作
     *
     * @param context context
     */
    public static void init(Context context) {
        sDisplayMetrics = context.getResources().getDisplayMetrics();
    }
    /**
     * <p>判断 point 是否 属于View的 区域</p>
     *
     * @param v
     * @param point
     * @return
     */
    public static boolean viewIsContainPoint(View v, Point point) {
        if (point.x > v.getLeft() && point.x < v.getLeft() + v.getWidth()) {
            if (point.y > v.getTop() && point.y < v.getTop() + v.getHeight()) {
                return true;
            }
        }

        return false;
    }

    /**
     * <p>获取状态栏的高度</p>
     *
     * @param activity
     * @return
     */
    public static int getWindowTitleHeight(Activity activity) {
        Display disp = activity.getWindowManager().getDefaultDisplay();
        Point outP = new Point();
        disp.getSize(outP);
        int maxHeight = outP.y;

        Rect outRect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);

        int h = maxHeight - outRect.height();
        return h;
    }

    /**
     * <p>获取屏幕的宽度</p>
     *
     * @param context
     * @return
     */
    public static int getWindowWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        return width;
    }

    /**
     * <p>获取屏幕的宽度</p>
     *
     * @param context
     * @return
     */
    public static int getWindowHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }

    public static int[] getWindowWidthAndHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        return new int[]{width, height};
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static void setViewHeight(View v, int height) {
        ViewGroup.LayoutParams lp = v.getLayoutParams();
        lp.height = height;
        v.setLayoutParams(lp);
    }


    /**
     * 判断横竖屏
     *
     * @param activity
     * @return 1：竖 | 0：横
     */
    public static int ScreenOrient(Activity activity) {
        int orient = activity.getRequestedOrientation();
        if (orient != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE && orient != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            WindowManager windowManager = activity.getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            int screenWidth = display.getWidth();
            int screenHeight = display.getHeight();
            orient = screenWidth < screenHeight ? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT : ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        }
        return orient;
    }

    /**
     * dp 转 px
     *
     * @param dp dp值
     * @return 转换后的像素值
     */
    public static int dp2px(int dp) {


        Log.i("result", "dp2px: 3"+ROUND_CEIL);
        Log.i("result", "dp2px: 1"+dp);
        Log.i("result", "dp2px: 2 "+sDisplayMetrics);
        return (int) (dp * sDisplayMetrics.density + ROUND_CEIL);
    }

    /**
     * dp 转 px
     *
     * @param dp dp值
     * @return 转换后的像素值
     */
    public static float dp2px(float dp) {
        return dp * sDisplayMetrics.density + ROUND_CEIL;
    }

    /**
     * 根据经纬度，计算两点间距离
     */

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    public static double distanceOfTwoPoints(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(
                Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        double EARTH_RADIUS = 6378137.0;
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

}
