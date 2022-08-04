package com.swzn.util.helper;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;

import com.swzn.util.logs.SWZNLog;
import com.swzn.config.GlobalVariable;


/**
 * Created by xx on 2017-7-5.
 */

public class LocationHelper {

    private LocationManager locationManager;
    private boolean isGpsEnabled;
    private String locateType;
    private Activity activity;

    private boolean HadGetPerssion;

    public LocationHelper(Activity activity) {
        this.activity = activity;
    }

    /**
     * 方法描述：初始化定位相关数据
     */
    public void initData() {
        //获取定位服务
        locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        //判断是否开启GPS定位功能
        isGpsEnabled = locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER);
        //定位类型：GPS
        locateType = locationManager.GPS_PROVIDER;
//        getProvider(locationManager);
        requestGPSPermission();
    }


    /**
     *
     * @param manager 位置管理服务
     * @return 最好的位置提供者    // gps //wifi //
     */
    private String getProvider(LocationManager manager){
        //一组查询条件
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE); //获取精准位置、
        criteria.setAltitudeRequired(false);//对海拔不敏感
        criteria.setPowerRequirement(Criteria.POWER_HIGH);//耗电量高
        criteria.setSpeedRequired(true);//速度变化敏感
        criteria.setCostAllowed(true);//产生开销  通信费用
        //返回最好的位置提供者   true 表示只返回当前已经打开的定位设备
        return  manager.getBestProvider(criteria, true);
    }


    private void requestGPSPermission() {
        SWZNLog.LogE("requestGPSPermission ===================:" + isGpsEnabled + ",");
//        if (ContextCompat.checkSelfPermission(activity,
//                Manifest.permission.ACCESS_COARSE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(activity,
//                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
//                    GlobalVariable.MY_PERMISSIONS_REQUEST_GETLOCATION);
//        } else {
//            HadGetPerssion = true;
//            RonLog.LogE("已经有权限 ===================");
//            hadGetPerssion();
//        }
    }
    private Location location;

    public void getLocation() {
        if( !HadGetPerssion || locationManager == null) return;
        // 设置监听*器，自动更新的最小时间为间隔N秒(1秒为1*1000，这样写主要为了方便)或最小位移变化超过N米
//        locationManager.requestLocationUpdates(locateType, 10,0.01f,
//                locationListener);
//
//         location = locationManager.getLastKnownLocation(locateType);
        if (location != null) {
            GlobalVariable.LonPhone = (int) (location.getLongitude() * 10000000);
            GlobalVariable.LatPhone = (int) (location.getLatitude() * 10000000);
        }
    }

    public void hadGetPerssion()
    {
        HadGetPerssion = true;
        try {
//        locationManager.requestLocationUpdates(locateType, 10,0.01f,
//                locationListener);
//
//        location = locationManager.getLastKnownLocation(locateType);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        if (location!=null){
            GlobalVariable.LonPhone = (int) (location.getLongitude()*10000000);
            GlobalVariable.LatPhone = (int) (location.getLatitude()*10000000 );
        }
    }


    public void onDestory()
    {
//        locationManager.removeUpdates(locationListener);
        locationManager = null;
        if (locationListener != null) {
            locationListener = null;
        }
        GlobalVariable.LatPhone  = 0;
        GlobalVariable.LonPhone  = 0;
    }

    private LocationListener  locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location)
        { //位置信息变化时触发:当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
            GlobalVariable.LonPhone = (int) (location.getLongitude()*10000000);
            GlobalVariable.LatPhone = (int) (location.getLatitude()*10000000 );
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
            //GPS状态变化时触发:Provider被disable时触发此函数，比如GPS被关闭
        }

        @Override
        public void onProviderEnabled(String s) {
            //方法描述：GPS开启时触发
            getLocation();
        }

        @Override
        public void onProviderDisabled(String s) {
            SWZNLog.LogE("locationListener:onProviderDisabled:" + s);
        }
    };
}
