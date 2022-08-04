package com.swzn.permission;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.util.List;

/**
 * Created by xx on 2017/11/13.
 */

public class PermissionUtil {
//    public static boolean checkLocatePermissionsForLOLLIPOP(Context context) {
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//            return true;
//        }
//        try {
//            try {
//                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                        && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    return false;
//                }
//            } catch (Exception e) {
//            }
//            LocationManager locationManager =
//                    (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
//            if (locationManager.getProvider(LocationManager.NETWORK_PROVIDER) != null) {
//                System.out.println("test test 1");
//                return true;
//            } else if (locationManager.getProvider(LocationManager.GPS_PROVIDER) != null) {
//                System.out.println("test test 2");
//                return true;
//            } else {
//                return false;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("test test 3");
//            return false;
//        }
//    }

    public static boolean checkLocatePermissionsForLOLLIPOP(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return true;
        }
        try{
            LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            Location googleLocation;
            if (locationManager.getProvider(LocationManager.NETWORK_PROVIDER) != null) {
                googleLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                //    Log.d("text",googleLocation.toString());
            } else if (locationManager.getProvider(LocationManager.GPS_PROVIDER) != null) {
                googleLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                //    Log.d("text",googleLocation.toString());
            } else {
                return false;
            }
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return false;
            } else {
                return true;
            }
        } catch (SecurityException e){
            e.printStackTrace();
            return false;
        }
    }
}
