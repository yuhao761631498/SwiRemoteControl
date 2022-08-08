package com.swi.util.logs;


import com.swi.config.UavStaticVar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xx on 2018-1-14.
 */

public class SWZNLog2File {
    private static SWZNLog2File ronLog2File;
    private FileOutputStream fileOutputStream;

    private SWZNLog2File() {
        String path = "/mnt/sdcard/swzn/LOG/ronlog_001.txt";
        simpleDateFormat = new SimpleDateFormat("HH:mm:ss:SSS");
        if (fileOutputStream == null) {
            File tempFile = new File(path);
            if (!tempFile.getParentFile().exists()) {
                tempFile.getParentFile().mkdirs();
            }
            try {
                fileOutputStream = new FileOutputStream(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return;
            }
        }
    }


    public void saveData(String log) {
        if(!UavStaticVar.isOpenTextEnvironment)  //!UavStaticVar.isOpenTextEnvironment )
        {
            return;
        }
        String  data = simpleDateFormat.format(new Date()) + ":"+ log;
        if(fileOutputStream != null )
        {
            try {
                fileOutputStream.write(data.getBytes());
                fileOutputStream.write("\n".getBytes());
                fileOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveData4(byte[] data, int begin, int length) {
        StringBuilder stringBuilder = new StringBuilder(simpleDateFormat.format(new Date()));
        stringBuilder.append(",");
        for (int i = begin; i < length; i++) {
            stringBuilder.append(Integer.toHexString((data[i] & 0xff))).append(",");
        }
        stringBuilder.append("\n");
        if (fileOutputStream != null) {
            try {
                fileOutputStream.write(stringBuilder.toString().getBytes());
                fileOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveData3(String log) {
       /* if( !UavStaticVar.isOpenTextEnvironment )
        {
            return;
        }*/
        String data = simpleDateFormat.format(new Date()) + ":" + log;
        if (fileOutputStream != null)
        {
            try {
                fileOutputStream.write(data.getBytes());
                fileOutputStream.write("\n".getBytes());
                fileOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveData2(byte[] data) {
        saveData2(data, 0, data.length);
    }

    private SimpleDateFormat simpleDateFormat;

    public void saveData2(byte[] data, int begin, int length) {
        if( true )  //!UavStaticVar.isOpenTextEnvironment )
        {
            return;
        }
        StringBuilder stringBuilder = new StringBuilder(simpleDateFormat.format(new Date()));
        stringBuilder.append(",");
        for (int i = begin; i < length; i++) {
            stringBuilder.append(Integer.toHexString((data[i] & 0xff))).append(",");
        }
        stringBuilder.append("\n");
        if (fileOutputStream != null) {
            try {
                fileOutputStream.write(stringBuilder.toString().getBytes());
                fileOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveData3(byte[] data, int begin, int length) {
        if(true)
            return;
        if (fileOutputStream != null) {
            try {
                fileOutputStream.write(data, begin, length);
                fileOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopSave() {
        if (fileOutputStream != null)
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public static SWZNLog2File getSingle() {
        if (ronLog2File == null) {
            ronLog2File = new SWZNLog2File();
        }
        return ronLog2File;
    }
}
