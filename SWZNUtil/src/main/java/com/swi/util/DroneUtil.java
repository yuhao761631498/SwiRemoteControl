package com.swi.util;

/**
 * Created by xx on 2018/6/22.
 */

public class DroneUtil {
    /**
     * 输入真实的距离返回放大后的距离
     * 单位：CM
     *
     * @param real
     * @return
     */
    public static int PDRealDidstance2FakeDistance(int real) {
//        if (real <= 30000) {
//            return real;
//        } else if (real <= 40000) {
//            return (int) (1.8 * real - 24000);
//        } else {
//            return (int) (1.2 * real);
//        }
        return real;
    }

    /**
     * 输入放大后的距离返回真实的距离
     * 单位：CM
     *
     * @param fake
     * @return
     */
    public static int PDFakeDistance2RealDistance(int fake) {
//        if (fake <= 30000) {
//            return fake;
//        } else if (fake <= 48000) {
//            return (int) ((fake * 1.0 + 24000) / 1.8);
//        } else {
//            return (int) (fake * 1.0 / 1.2);
//        }
        return fake;
    }
}
