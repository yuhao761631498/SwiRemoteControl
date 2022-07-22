package com.swi.maplibrary.mapview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.text.TextPaint;

import androidx.annotation.RequiresApi;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.swi.maplibrary.R;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Copyright (C), 2020-2030,
 * <p>
 * 功能描述:航迹规划基类
 * <p>
 * 创建时间: 2022/7/22 15:13
 *
 * @author yuhao
 */
public abstract class BaseRoutePlane implements AMap.OnMapClickListener, AMap.OnMarkerDragListener {

    private final Context context;

    private final AMap aMap;

    private final List<Marker> markerList = new ArrayList<>();

    private final List<LatLng> latLngList = new ArrayList<>();

    private final List<Polyline> polylineList = new ArrayList<>();

    public BaseRoutePlane(Context context, AMap aMap) {
        this.context = context;
        this.aMap = aMap;
    }

    public void addListener() {
        aMap.addOnMapClickListener(this);
        aMap.addOnMarkerDragListener(this);
    }

    public void removeListener() {
        aMap.removeOnMapClickListener(this);
        aMap.removeOnMarkerDragListener(this);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        addPfMarker(latLng, true);
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onMarkerDragEnd(Marker marker) {

        if (markerList.contains(marker)) {
            int index = markerList.indexOf(marker);
            latLngList.set(index, marker.getPosition());
        }

        polylineList.forEach(new Consumer<Polyline>() {
            @Override
            public void accept(Polyline polyline) {
                polyline.remove();
            }
        });
        polylineList.clear();
        polylineList.add(addPolylineByLatLngList(latLngList));
    }


    public void addPfMarker(LatLng latLng, boolean isDrag) {
        if (markerList.size() < 100) {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.anchor(0.5f, 1.0f);
            markerOptions.position(latLng);
            markerOptions.draggable(isDrag);
            markerOptions.setFlat(false).icon(BitmapDescriptorFactory.fromBitmap(getBitMap(markerList.size() + 1)));
            Marker marker = aMap.addMarker(markerOptions);

            if (markerList.size() > 0) {
                polylineList.add(addPolyline(markerList.get(markerList.size() - 1).getPosition(), latLng));
            }

            latLngList.add(latLng);
            markerList.add(marker);
        }
    }


    public Polyline addPolyline(LatLng startPoint, LatLng endPoint) {
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.add(startPoint, endPoint).width(5.0f).color(Color.rgb(255, 121, 24)).setDottedLine(false).zIndex(3.0f);
        return aMap.addPolyline(polylineOptions);
    }

    public Polyline addPolylineByLatLngList(List<LatLng> latLngList) {
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.addAll(latLngList).width(5.0f).color(Color.rgb(255, 121, 24)).setDottedLine(false).zIndex(3.0f);
        return aMap.addPolyline(polylineOptions);
    }

    public Bitmap getBitMap(int number) {
        Bitmap bitmap =
                BitmapFactory.decodeResource(this.context.getResources(), R.mipmap.icon_route_plan_point).copy(Bitmap.Config.ARGB_8888, true);

        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight());
        Canvas canvas = new Canvas(createBitmap);
        TextPaint textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.rgb(0, 138, 34));
        if (number >= 100) {
            textPaint.setTextSize((2 * 18.0f) - 26.0f);
        } else {
            textPaint.setTextSize((2 * 18.0f) - 20.0f);
        }
        textPaint.setFakeBoldText(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(String.valueOf(number), (float) (canvas.getWidth() / 2), (float) ((canvas.getHeight() / 2) - 3),
                textPaint);
        return createBitmap;
    }
}
