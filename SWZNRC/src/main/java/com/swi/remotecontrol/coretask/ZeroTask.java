package com.swi.remotecontrol.coretask;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by liangzi on 2017/6/8.
 */

public abstract class ZeroTask implements Runnable, Closeable {
    boolean isClose = false;
    public void close(Closeable obj){
        try {
            if(obj!= null){
                obj.close();
                obj = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
