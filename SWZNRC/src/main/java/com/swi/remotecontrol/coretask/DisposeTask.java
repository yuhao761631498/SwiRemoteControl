package com.swi.remotecontrol.coretask;

import com.swi.util.logs.SWZNLog2File;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by xx on 2018-1-13.
 */

public class DisposeTask extends ZeroTask
{
    private LinkedBlockingQueue<byte[]> usbData;
    private OnGetUsbDataCallBack onGetUsbDataCallBack;
    public  DisposeTask()
    {
        usbData  = new LinkedBlockingQueue(4096);
        isClose = false;
    }

    public void addData( byte [] data  )
    {
       /* if( usbData != null )
        {
           boolean isAdd =  usbData.offer(data);
            if(!isAdd)
            {
                RonLog2File.getSingle().saveData("从USB的数据添加到队列失败");
                YhLog.LogE("从USB的数据添加到队列失败");
            }
        }*/

        //发现有些手机，处理的比较慢，如果使用队列来保存的话，队列会溢出,故直接回调----xx
        if(this.onGetUsbDataCallBack != null )
        {
            this.onGetUsbDataCallBack.onCallBack(data);
        }
    }

    /**************************************************
     * 获取
     * @param onGetUsbDataCallBack
     */
    public void setOnGetUsbDataCallBack(OnGetUsbDataCallBack onGetUsbDataCallBack)
    {
        this.onGetUsbDataCallBack = onGetUsbDataCallBack;
    }

    @Override
    public void close() throws IOException
    {
        isClose = true;
    }

    @Override
    public void run()
    {
        SWZNLog2File.getSingle().saveData("DisposeTask ==============begin Start");
        try {
            while ( !isClose )
            {
               byte[] data = usbData.poll(300, TimeUnit.MICROSECONDS);
                if( data != null )
                {
                   if(this.onGetUsbDataCallBack != null )
                   {
                       this.onGetUsbDataCallBack.onCallBack( data );
                   }
                }
            }
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        SWZNLog2File.getSingle().saveData("DisposeTask ==============Run is Over");
    }

    public interface  OnGetUsbDataCallBack
    {
        public void onCallBack(byte[] data);
    }
}
