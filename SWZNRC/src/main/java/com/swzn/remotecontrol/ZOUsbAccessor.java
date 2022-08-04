package com.swzn.remotecontrol;

import android.content.Context;

/**
 * Created by liangzi on 2017/6/2.
 */
public class ZOUsbAccessor extends ZOAccessorManager {
    private ZOOnUsbListener mListener;
    private static ZOUsbAccessor mInstance;

    public static ZOUsbAccessor getInstance(){
        synchronized (ZOUsbAccessor.class){
            if(mInstance == null){
                mInstance = new ZOUsbAccessor();
            }
        }
        return mInstance;
    }
    public ZOUsbAccessor() {
        super();
    }

    public void initAccessor(Context activity){
        super.init(activity);
    }

    @Override
    void openUsbModel() {
        if(mListener != null){
            mListener.openUsbModel();
        }
    }
    @Override
    void closeUsbModel() {
        close();
        if(mListener != null){
            mListener.closeUsbModel();
        }
    }

    public void onResume() {
        super.onResume();
    }

    public void closeAccessory(){
        super.closeAccessory();
//        mListener = null;
    }

    public String getVersion(){
        return  super.getVersion();
    }

    public void setOnUsbListener(ZOOnUsbListener listener) {
        mListener = listener;
    }
}
