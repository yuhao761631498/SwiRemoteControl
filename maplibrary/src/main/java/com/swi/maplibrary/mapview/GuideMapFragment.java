package com.swi.maplibrary.mapview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.swi.maplibrary.R;
import com.swi.maplibrary.callback.MapWindowClickCallback;
import com.swi.maplibrary.routeplane.RouteWayPoint;

public class GuideMapFragment extends Fragment implements AMap.OnMyLocationChangeListener,
        AMap.OnCameraChangeListener, AMap.OnMapTouchListener, View.OnClickListener {

    private TextureMapView mapView;
    private AMap aMap;
    private MyLocationStyle myLocationStyle;
    private double latitude;
    private double longitude;
    private MapWindowClickCallback mapWindowClickCallback;
    private TextView normal_map;
    private TextView satellite_map;
    private RouteWayPoint routeWayPoint;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_guide_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MapsInitializer.updatePrivacyShow(getContext(), true, true);
        MapsInitializer.updatePrivacyAgree(getContext(), true);

        mapView = view.findViewById(R.id.map);

        if (aMap == null) {
            aMap = mapView.getMap();
        }

        mapView.onCreate(savedInstanceState);

        initView(view);

        showBluePoint();

        initListener();

        initMapUISetting();

        routeWayPoint = new RouteWayPoint(getContext(), aMap);
    }

    private void initView(View view) {
        normal_map = view.findViewById(R.id.tv_normal_map);
        satellite_map = view.findViewById(R.id.tv_satellite_map);
    }

    private void initListener() {
        aMap.addOnMyLocationChangeListener(this);
        aMap.addOnCameraChangeListener(this);
        aMap.addOnMapTouchListener(this);
        normal_map.setOnClickListener(this);
        satellite_map.setOnClickListener(this);
    }

    /**
     * 显示定位小蓝点
     */
    private void showBluePoint() {
        //初始化定位蓝点样式类myLocationStyle.myLocationType
        myLocationStyle = new MyLocationStyle();
        //连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）默认执行此种模式。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_current_position_point));
        myLocationStyle.showMyLocation(true);
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
    }

    private void initMapUISetting() {
        UiSettings uiSettings = aMap.getUiSettings();
        uiSettings.setCompassEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        uiSettings.setScaleControlsEnabled(true);
        aMap.moveCamera(CameraUpdateFactory.zoomTo(16));
    }


    @Override
    public void onMyLocationChange(Location location) {
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            Log.e("yuhao", "latitude: " + latitude);
            Log.e("yuhao", "longitude: " + longitude);
        }
        Bundle bundle = location.getExtras();
        if (bundle != null) {
            int errorCode = bundle.getInt(MyLocationStyle.ERROR_CODE);
            String errorInfo = bundle.getString(MyLocationStyle.ERROR_INFO);
            // 定位类型，可能为GPS WIFI等，具体可以参考官网的定位SDK介绍
            int locationType = bundle.getInt(MyLocationStyle.LOCATION_TYPE);
                /*
                errorCode
                errorInfo
                locationType
                */
            Log.e("amap", "定位信息， code: " + errorCode + " errorInfo: " + errorInfo + " locationType: " + locationType);
        } else {
            Log.e("amap", "定位信息， bundle is null ");
        }
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        if (Math.abs(cameraPosition.target.latitude - latitude) < 0.01 && Math.abs(cameraPosition.target.longitude - longitude) < 0.01) {
            myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);
            aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        }
    }


    @Override
    public void onTouch(MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            if (mapWindowClickCallback != null) {
                mapWindowClickCallback.mapWindowClick(aMap);
            }
        }
    }

    /**
     * 移除地图的触摸回调监听(地图全屏)
     */
    public void removeTouchListener() {
        aMap.removeOnMapTouchListener(this);
        normal_map.setVisibility(View.VISIBLE);
        satellite_map.setVisibility(View.VISIBLE);
        routeWayPoint.addListener();
    }

    /**
     * 设置地图触摸监听（地图窗口）
     */
    public void addTouchListener() {
        aMap.addOnMapTouchListener(this);
        normal_map.setVisibility(View.GONE);
        satellite_map.setVisibility(View.GONE);
        routeWayPoint.removeListener();
    }

    public void setMapWindowClickCallback(MapWindowClickCallback mapWindowClickCallback) {
        this.mapWindowClickCallback = mapWindowClickCallback;
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_normal_map) {
            aMap.setMapType(AMap.MAP_TYPE_NORMAL);
        } else if (id == R.id.tv_satellite_map) {
            aMap.setMapType(AMap.MAP_TYPE_SATELLITE);
        }
    }
}