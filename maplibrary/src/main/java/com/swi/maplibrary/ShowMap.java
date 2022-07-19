package com.swi.maplibrary;

import android.content.Context;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.model.MyLocationStyle;

/**
 * Copyright (C), 2020-2030, 武汉中旗生物医疗电子有限公司
 * <p>
 * 功能描述:显示地图
 * <p>
 * 创建时间: 2022/7/19 16:34
 *
 * @author yuhao
 */
public class ShowMap {

    private final MapView mapView;

    private final AMap aMap;

    private final Context context;

    public ShowMap(MapView mapView, Context context) {
        this.mapView = mapView;
        this.context = context;
        MapsInitializer.updatePrivacyShow(context, true, true);
        MapsInitializer.updatePrivacyAgree(context, true);

        aMap = mapView.getMap();

        showBluePoint();
    }


    /**
     * 显示定位小蓝点
     */
    private void showBluePoint() {
        MyLocationStyle myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
        //连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）默认执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
    }
}
