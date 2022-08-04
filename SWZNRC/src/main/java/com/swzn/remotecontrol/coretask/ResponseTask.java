package com.swzn.remotecontrol.coretask;


import com.swzn.util.logs.SWZNLog;
import com.swzn.util.logs.SWZNLog2File;
import com.swzn.util.SaveFileUtils;
import com.swzn.remotecontrol.ZOConstants;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/** 接收USB传输来的数据，并通过代理client 发送给代理服务器
 * Created by liangzi on 2016/11/15.
 */
public class ResponseTask extends ZeroTask {
    private byte[]          mBuffer,mBuffer2;
    private FileInputStream mFileInputStream;
    private Socket mSocketUsb;
    private OutputStream mProxyOutputStream;

    private DisposeTask disposeTask;
    private SaveFileUtils saveFileUtils;

    public void setDisposeTask( DisposeTask disposeTask )
    {
        this.disposeTask = disposeTask;
    }

    /****************************************
     *  -1 初始化状态 ----------xx
     *   1 遥控器发的每包512个字节
     *   2 遥控器发的每包是4104字节
     */
    public static byte isSendLenthIsState = -1;


    public ResponseTask(FileInputStream inputStream, Socket socketUsb) {
        mFileInputStream = inputStream;
        mSocketUsb = socketUsb;
        mBuffer = new byte[ZOConstants.BUFFER_SIZE_4104*2];
        TWO_511 = ZOConstants.BUFFER_SIZE_512 * 2;
        THREE_511 = ZOConstants.BUFFER_SIZE_512 * 3;
    }
    private  int  TWO_511;
    private  int THREE_511;
    @Override
    public void run() {
//        RonLog2File.getSingle().saveData("RonLog2File==========================begin start");
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_AUDIO);
        int len = -1;
        int offset = 0;
        int packageRealLen = 0;
        if (null != mFileInputStream)
        {
            try {
                mProxyOutputStream = mSocketUsb.getOutputStream();
                while ( !isClose )
                {
                    mBuffer2 = new byte[ZOConstants.BUFFER_SIZE_4104];
                    len = mFileInputStream.read(mBuffer);
                    int destPort = getDestPort(mBuffer);
                    int length = getDataLength(mBuffer);
//                    if (saveFileUtils == null) {
//                        saveFileUtils = new SaveFileUtils();
//                    }
//                    saveFileUtils.saveFile(mBuffer, 0, len);

                /*    if(len > 0 )
                    {
                        if( disposeTask != null )
                        {
                            disposeTask.addData(mBuffer);
                        }
                    }*/
                     ;
                  if (len == ZOConstants.BUFFER_SIZE_512) // 适配note 5的情况
                   {
                       packageRealLen = getDataLength(mBuffer);
                       if(packageRealLen > ZOConstants.BUFFER_SIZE_4104  || mBuffer[0] > 1)
                           continue;
                       System.arraycopy(mBuffer,0,mBuffer2,0,ZOConstants.BUFFER_SIZE_512);
                       SWZNLog.LogE("xx===========packageRealLen:"+ packageRealLen + ", "+ mBuffer[0]);
                       if(packageRealLen < ZOConstants.BUFFER_SIZE_512)
                        {

                        } else if (packageRealLen < TWO_511 )
                       {
                           len = mFileInputStream.read(mBuffer);
                           System.arraycopy(mBuffer,0,mBuffer2,ZOConstants.BUFFER_SIZE_512,ZOConstants.BUFFER_SIZE_512);
                       }else if (packageRealLen < THREE_511 )
                        {
                            len = mFileInputStream.read(mBuffer);
                            System.arraycopy(mBuffer,0,mBuffer2,ZOConstants.BUFFER_SIZE_512,ZOConstants.BUFFER_SIZE_512);
                            len = mFileInputStream.read(mBuffer);
                            System.arraycopy(mBuffer,0,mBuffer2,TWO_511,ZOConstants.BUFFER_SIZE_512);
                        }else
                       {
                            int size = (packageRealLen +8)/ZOConstants.BUFFER_SIZE_512;
                           if((packageRealLen +8)% ZOConstants.BUFFER_SIZE_512 !=  0)
                               size += 1;
                           for (int i = 1; i < size ; i ++ )
                           {
                               len = mFileInputStream.read(mBuffer);
                               if(ZOConstants.BUFFER_SIZE_512*i + len > ZOConstants.BUFFER_SIZE_4104 )
                               {
                                   System.arraycopy(mBuffer,0,mBuffer2,ZOConstants.BUFFER_SIZE_512*i,ZOConstants.BUFFER_SIZE_4104 - ZOConstants.BUFFER_SIZE_512*i);
                               }else
                               System.arraycopy(mBuffer,0,mBuffer2,ZOConstants.BUFFER_SIZE_512*i,len);
                           }
                       }
                        if(disposeTask != null )
                       {
                           disposeTask.addData(mBuffer2);
                       }
                    }else if(len == -1)break;
                    else
                   {
//                       if( destPort != 7078  )
//                       {
                           System.arraycopy(mBuffer,0,mBuffer2,0,ZOConstants.BUFFER_SIZE_4104);
                           if(disposeTask != null )
                           {
                               disposeTask.addData(mBuffer2);
                           }
//                       }
                       /*else
                       {
                           if(length <1400)
                           RonLog.LogE("ResponseTask===========:" + destPort + "," + length);
                       }*/

                   }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (mProxyOutputStream!=null) {
                    mProxyOutputStream.close();
                }
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        SWZNLog2File.getSingle().saveData("RonLog2File========================== is Over");
    }


    /**获取数据长度*/
    private int getDataLength(byte[] data){
        return  get2ByteToInt(data[6], data[7]);
    }

    /**获取远程端口*/
    private int getDestPort(byte[] data){
        return get2ByteToInt(data[4], data[5]);
    }

    public static int getUnsignedByte(byte var0) {
        int var1 = var0;
        if (var0 < 0) {
            var1 = var0 + 256;
        }
        return var1;
    }

    public static int get2ByteToInt(byte var0, byte var1) {
        int var = var1 << 8 | getUnsignedByte(var0);
        if(var < 0){
            var += 65536;
        }
        return var;
    }

    @Override
    public void close() throws IOException
    {
        isClose = true;
        close(mProxyOutputStream);
        close(mFileInputStream);
        isSendLenthIsState = -1;
    }
}