package com.swzn.remotecontrol.coretask;



import com.swzn.util.logs.SWZNLog2File;
import com.swzn.remotecontrol.ZOConstants;
import com.swzn.remotecontrol.ZOThreadManager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;


/**
 * Created by liangzi on 2016/11/15.
 */
public class RequestTask extends ZeroTask {
    private byte[]           mBuffer;
    private FileOutputStream mFileOutputStream;
    private FileInputStream mFileInputStream;
    private InputStream mInputStream;
    private Socket mSocketUsb;
    private ResponseTask mCommandTask = null;

    private DisposeTask disposeTask;

    public RequestTask(FileInputStream inputStream, FileOutputStream outputStream,DisposeTask disposeTask) {
        mFileInputStream = inputStream;
        this.disposeTask = disposeTask;
        mFileOutputStream = outputStream;
        mBuffer = new byte[ZOConstants.BUFFER_SIZE_1024 + 8];
    }

    @Override
    public void run() {
//        RonLog2File.getSingle().saveData("RequestTask ==============begin Start");
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_AUDIO);
       /* try {

            while (!isClose) {
                try {
                    mCommandTask = getResponseTask();
                    //捕捉IO异常说明USB断开，跳出最外层while循环，以便线程可被回收。
                    operateData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }*/

        try {
            while (!isClose) {
                try {
                    if( mCommandTask == null)
                        mCommandTask = getResponseTask();
                    //捕捉IO异常说明USB断开，跳出最外层while循环，以便线程可被回收。
                    if(mCommandTask != null)
                        operateData();
                    else
                        Thread.sleep(300);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        SWZNLog2File.getSingle().saveData("RequestTask ==============isOver");
    }

    /**发送数据*/
    private void operateData() throws IOException {
        while (mInputStream.read(mBuffer) != -1 && !isClose) {
            try {
                if (mFileOutputStream != null ) {
    //                Log.d("ZOAccessorManager", " RequestTask = " + bytesToHexString(mBuffer));
                    mFileOutputStream.write(mBuffer);
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    private ResponseTask getResponseTask() throws IOException {
        mSocketUsb = new Socket(InetAddress.getLocalHost(), ZOConstants.LOCAL_PORT_PROXY_SERVER);
        mInputStream = mSocketUsb.getInputStream();
        //先确保代理Socket 已经连接上，然后再创建响应USB任务类
        ResponseTask commandTask = new ResponseTask(mFileInputStream, mSocketUsb);
        commandTask.setDisposeTask(disposeTask);
        ZOThreadManager.getInstance().addWorkStealingPool(commandTask);
        return commandTask;
    }

    /**关闭流并清除任务*/
    @Override
    public void close() throws IOException {
        isClose = true;
        mBuffer = null;
        close(mInputStream);
        close(mSocketUsb);
        close(mCommandTask);
        ZOThreadManager.getInstance().removeWorkStealingPool(mCommandTask);
    }

    public void close(Socket obj){
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
