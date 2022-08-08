package com.swi.util.logs;

import android.util.Log;


/**
 * Created by xx on 2017/4/10.
 */
public class YhLog {

    public static String YUHAO = "<xx>";
//    private static boolean isShowPhone = UavStaticVar.isOpenTextEnvironment ? true : false;
    private static boolean isShowPhone = true;
//
    public static void LogE(String... args) {
        if (args.length >= 2) {
            StringBuffer sb = new StringBuffer();
            for (int i = 1; i < args.length; i++) {
                sb.append(args[i]);
                sb.append(";");
            }
            if (isShowPhone)
            Log.e(args[0], sb.toString());
        } else if (args.length == 1) {
            if (isShowPhone)
            Log.e(YUHAO, args[0]);
        }
    }
}
