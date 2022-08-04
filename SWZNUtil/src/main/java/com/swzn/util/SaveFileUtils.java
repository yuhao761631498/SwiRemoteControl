package com.swzn.util;

import com.swzn.config.SWZNConfig;
import com.swzn.util.logs.YhLog;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xx on 2017-10-5.
 */

public class SaveFileUtils {
    private String path = "/sdcard/gdu/test.txt";

    public SaveFileUtils() {
        path = SWZNConfig.TextDirectory + "/" + SWZNConfig.DevelopTextInfo + "/" + "Text_" + DateUtil.SwitchTimestamp_ymdhm(new Date().getTime() / 1000)+".txt";
    }

    private boolean isCSV;

    public SaveFileUtils(String name) {
        path = SWZNConfig.TextDirectory + "/" + SWZNConfig.DevelopTextInfo + "/" + "XUEJIAN_" + DateUtil.SwitchTimestamp_ymdhm(new Date().getTime() / 1000) + ".csv";
        isCSV = true;
    }

    public void setPath(String outpath, String name) {
        path = outpath + name;
    }

    String end = "\n\n\n\n";
    private FileOutputStream fileOutputStream;

    SimpleDateFormat simpleDateFormat;

    public void saveLogData(int height, int distense, int speed, int lost, int quality) {
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(simpleDateFormat.format(new Date())).append(",");
        stringBuilder.append(height).append(",");
        stringBuilder.append(distense).append(",");
        stringBuilder.append(speed).append(",");
        stringBuilder.append(lost).append(",");
        stringBuilder.append(quality).append("\n");
        saveFile(stringBuilder.toString().getBytes());
    }

    public void saveFile(byte[] data, int offset, int length) {
        if (fileOutputStream == null) {
            File tempFile = new File(path);
            if (!tempFile.getParentFile().exists()) {
                tempFile.mkdirs();
            }
            try {
                fileOutputStream = new FileOutputStream(path);
                YhLog.LogE("++++++++++++++++++++++");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return;
            }
        }
        try {

            YhLog.LogE("intOffest="+offset+"      length="+length);

            fileOutputStream.write(data, offset, length);

            fileOutputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            if (fileOutputStream != null) {
//                try {
//                    fileOutputStream.close();
//                    YhLog.LogE("===================");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
        }
    }

    public void saveFile(byte[] data) {
        if (fileOutputStream == null) {
            File tempFile = new File(path);
            if (!tempFile.getParentFile().exists()) {
                tempFile.mkdirs();
            }
            try {
                fileOutputStream = new FileOutputStream(path);
                if (isCSV)
                    try {
                        String head = "时间,高度,距离,码流,丢包率,信号强度\n";
                        fileOutputStream.write(head.getBytes(), 0, head.getBytes().length);
                        fileOutputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return;
            }
        }

        try {
            fileOutputStream.write(data);
//            fileOutputStream.write(end.getBytes());
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 根据byte数组生成文件
     *
     * @param bytes 生成文件用到的byte数组
     */
    public void createFileWithByte(byte[] bytes, int offest, int length) {
        // TODO Auto-generated method stub
        /**
         * 创建File对象，其中包含文件所在的目录以及文件的命名
         */
        // 创建FileOutputStream对象
        FileOutputStream outputStream = null;

        // 创建BufferedOutputStream对象
        BufferedOutputStream bufferedOutputStream = null;
        if (bufferedOutputStream == null) {
            File file = new File(SWZNConfig.TextDirectory + "/" + System.currentTimeMillis() + ".txt");
            // 如果文件存在则删除
            if (file.exists()) {
                file.delete();
            }
            YhLog.LogE("begin save:" + offest + ",length:" + length);

            // 在文件系统中根据路径创建一个新的空文件
            try {
                file.createNewFile();
                outputStream = new FileOutputStream(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {

            // 获取FileOutputStream对象
            // 获取BufferedOutputStream对象
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            // 往文件所在的缓冲输出流中写byte数据
            bufferedOutputStream.write(bytes, offest, length);
            // 刷出缓冲输出流，该步很关键，要是不执行flush()方法，那么文件的内容是空的。
            bufferedOutputStream.flush();
        } catch (Exception e) {
            // 打印异常信息
            e.printStackTrace();
        } finally {
            // 关闭创建的流对象
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }


    public void sendLocalTestData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String PATH = "/sdcard";//GduConfig.BaseDirectory + "/" + GduConfig.TempFileName;
                DatagramSocket datagramSocket = null;
                InetAddress inetAdd = null;
                try {
                    inetAdd = InetAddress.getByName("127.0.0.1");
                    datagramSocket = new DatagramSocket(7078);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (SocketException e) {
                    e.printStackTrace();
                }
                File file = new File(PATH + "/rtppkt.txt");
                InputStream in = null;
                byte[] tempbytes = new byte[4 * 1024 + 8];
                int byteread = 0;
                try {
                    in = new FileInputStream(file);
                    while ((byteread = in.read(tempbytes)) != -1) {
//                        Log.e("xx", "------------------>"+byteread);
                        DatagramPacket pck = new DatagramPacket(tempbytes, 0,
                                byteread, inetAdd, 7078);
                        datagramSocket.send(pck);
                        Thread.sleep(10);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
