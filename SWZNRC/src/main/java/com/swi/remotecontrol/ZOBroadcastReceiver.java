package com.swi.remotecontrol;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbAccessory;
import android.hardware.usb.UsbManager;
import android.util.Log;

import com.swi.util.logs.SWZNLog;

/**
 * Created by liangzi on 2017/7/6.
 */

public class ZOBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.e("ZOBroadcastReceiver===",action);
        if (ZOAccessorManager.ACTION_USB_PERMISSION.equals(action)) {
          /*  synchronized (this) {
                UsbAccessory accessory = intent.getParcelableExtra(UsbManager.EXTRA_ACCESSORY);
                if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                    ZOUsbAccessor.getInstance().openAccessory(accessory);
                } else
                {
                    Log.d("","permission denied for accessory" + accessory);
                }

                if (UsbManager.ACTION_USB_ACCESSORY_ATTACHED.equals(action)) {
                    ZOUsbAccessor.getInstance().openAccessory(accessory);
                }
                ZOUsbAccessor.getInstance().mPermissionRequestPending = false;
            }*/
        } else if (UsbManager.ACTION_USB_ACCESSORY_DETACHED.equals(action)) {
            SWZNLog.LogE("ACTION_USB_ACCESSORY_DETACHED");
            UsbAccessory accessory = intent.getParcelableExtra(UsbManager.EXTRA_ACCESSORY);
            if (accessory != null && accessory.equals(ZOUsbAccessor.getInstance().mUsbAccessory)) {
                ZOUsbAccessor.getInstance().closeAccessory();
            }
        }else if(UsbManager.ACTION_USB_ACCESSORY_ATTACHED.equals(action))
        {
//            Toast.makeText(context,"有USB接入",Toast.LENGTH_SHORT).show();
        }
    }
}
