package com.swzn.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by xx on 2017/12/26.
 */

public class NetWorkUtils {
    /**
     * 判断wifi是否为2.4G
     * @param freq wifi频率
     * @return
     */
    public static boolean is24GHz(int freq) {
        return freq > 2400 && freq < 2500;
    }

    /**
     * 判断wifi是否为5G
     * @param freq wifi频率
     * @return
     */
    public static boolean is5GHz(int freq) {
        return freq > 4900 && freq < 5900;
    }

    /**
     * 获取wifi的ip地址
     * @return
     */
    public static String getWifiIp(Context context){
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifi.getConnectionInfo();
        return null;
//        if (wifiInfo != null) {
//            return wifiInfo.getIpAddress();
//        }
    }

//    private String intToIp(int i)  {
//        return (i & 0xFF) + "." + ((i >> 8 ) & 0xFF)? + "." + ((i >> 16 ) & 0xFF) +"."+((i >> 24 ) & 0xFF );
//    }

    /**
     * 检查当前网络是否可用
     */
    public static boolean checkNetwork(Context context) {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        } else {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        if (networkInfo[i].getType() == ConnectivityManager.TYPE_MOBILE) {
                            return networkInfo[i].isAvailable();
                        } else if (networkInfo[i].getType() == ConnectivityManager.TYPE_WIFI) {
                            return networkInfo[i].isAvailable();
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * 判断是否有网络连接
     * <p>shang</p>
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * <P>shang</P>
     * <P>获取当前网络类型</P>
     *
     * @param context
     * @return
     */
    public static int getNetworkType(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
        if (networkInfo != null && networkInfo.length > 0) {
            for (int i = 0; i < networkInfo.length; i++) {
                if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                    if (networkInfo[i].getType() == ConnectivityManager.TYPE_MOBILE) {
                        return ConnectivityManager.TYPE_MOBILE;
                    } else if (networkInfo[i].getType() == ConnectivityManager.TYPE_WIFI) {
                        return ConnectivityManager.TYPE_WIFI;
                    }
                }
            }
        }
        return -1;
    }

    /**
     * 判断MOBILE网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isMobileConnected(Context context) {
        if (context != null) {
            //获取手机所有连接管理对象(包括对wi-fi,net等连接的管理)
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            //判断NetworkInfo对象是否为空 并且类型是否为MOBILE
            NetworkInfo[] networkInfo = manager.getAllNetworkInfo();
            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    if (networkInfo[i].getType() == ConnectivityManager.TYPE_MOBILE) {
                        return networkInfo[i].isAvailable();
                    }
                }
            }
        }
        return false;
    }

    /**
     * @author xx
     * @category 判断是否有外网连接（普通方法不能判断外网的网络是否连接，比如连接上局域网）
     * @return
     */
    public static final boolean ping() {

        String result = null;
        try {
            String ip = "www.baidu.com";// ping 的地址，可以换成任何一种可靠的外网
            Process p = Runtime.getRuntime().exec("ping -c 3 -w 100 " + ip);// ping网址3次
            // 读取ping的内容，可以不加
            InputStream input = p.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            StringBuffer stringBuffer = new StringBuffer();
            String content = "";
            while ((content = in.readLine()) != null) {
                stringBuffer.append(content);
            }
            Log.d("------ping-----", "result content : " + stringBuffer.toString());
            // ping的状态
            int status = p.waitFor();
            if (status == 0) {
                result = "success";
                return true;
            } else {
                result = "failed";
            }
        } catch (IOException e) {
            result = "IOException";
        } catch (InterruptedException e) {
            result = "InterruptedException";
        } finally {
            Log.d("----result---", "result = " + result);
        }
        return false;
    }

    public static String getLocalIP(Context context) {
        String ip = getLocalIpAddress(context);
        if (TextUtils.isEmpty(ip)) {
            ip = getLocalIpAddress();
        }

        return ip;
    }

    public static String getLocalIpAddress(Context context) {
        try {
            WifiManager wifiManager = (WifiManager)context.getSystemService("wifi");
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int i = wifiInfo.getIpAddress();
            if (i == 0) {
                return null;
            } else {
                String ip = int2ip(i);
                return ip;
            }
        } catch (Exception var5) {
            return null;
        }
    }

    public static String getLocalIpAddress() {
        try {
            Enumeration en = NetworkInterface.getNetworkInterfaces();

            while(en.hasMoreElements()) {
                NetworkInterface intf = (NetworkInterface)en.nextElement();
                Enumeration enumIpAddr = intf.getInetAddresses();

                while(enumIpAddr.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress)enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        String ip = inetAddress.getHostAddress().toString();
                        System.out.println("test 3333 ip" + ip);
                        return ip;
                    }
                }
            }
        } catch (SocketException var5) {
            Log.e("WifiPreference IpAddress", var5.toString());
        }

        return null;
    }

    public static String int2ip(int ipInt) {
        StringBuilder sb = new StringBuilder();
        sb.append(ipInt & 255).append(".");
        sb.append(ipInt >> 8 & 255).append(".");
        sb.append(ipInt >> 16 & 255).append(".");
        sb.append(ipInt >> 24 & 255);
        return sb.toString();
    }
}
