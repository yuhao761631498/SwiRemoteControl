package com.swzn.util.logs;

import android.util.Log;


/**
 * Created by xx on 2016/5/23.
 */
public class SWZNLog {

//    private static boolean isShow = UavStaticVar.isOpenTextEnvironment ? true : false;
    private static boolean isShow = true;

    public static String RON = "<xx>";


    public static void LogE(int args){
        if (!isShow)
            return;
        LogE(args + "");
    }

    public static void LogE(String... args) {
        if (!isShow)
            return;
        if (args.length >= 2) {
            StringBuffer sb = new StringBuffer();
            for (int i = 1; i < args.length; i++) {
                sb.append(args[i]);
                sb.append(";");
            }
            Log.e(args[0], sb.toString());
        } else if (args.length == 1) {
            Log.e(RON, args[0]);
        }
    }

    public static void LogData(byte[] data, int length) {
        StringBuilder sb = new StringBuilder("UDPSocket:");
        for (int i = 0; i < length; i++) {
            sb.append(",").append(Integer.toHexString(data[i]));
        }
        SWZNLog.LogI(sb.toString());
    }

    public static void LogDataE(byte[] data, int length) {
        StringBuilder sb = new StringBuilder("UDPSocket:");
        for (int i = 0; i < length; i++) {
            sb.append(",").append(Integer.toHexString(data[i]));
        }
        SWZNLog.LogE(sb.toString());
    }

    public static void LogD(String... args) {
        if (!isShow)
            return;
        if (args.length >= 2) {
            StringBuffer sb = new StringBuffer();
            for (int i = 1; i < args.length; i++) {
                sb.append(args[i]);
                sb.append(";");
            }
            Log.d(args[0], sb.toString());
        } else if (args.length == 1) {

            Log.d(RON, args[0]);
        }
    }


    public static void LogI(String msg) {
        if (!isShow)
            return;
        Log.i(RON, msg);
    }

    public static void LogW(String msg) {
        if (!isShow)
            return;
        Log.w(RON, msg);
    }
}
