package com.swi.commonlibrary;

import android.annotation.SuppressLint;
import android.util.Log;

import com.swi.commonlibrary.config.SwiCommonConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xx on 2018-1-14.
 */

public class YhLog2File {
    private static YhLog2File yhLog2File;
    private FileOutputStream fileOutputStream;

    @SuppressLint("SimpleDateFormat")
    private YhLog2File() {
        String path = SwiCommonConfig.LogDirectory + "/yuhaoLog.txt";
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
            }
        }
    }

    public void saveData(String log) {
        String data = simpleDateFormat.format(new Date()) + ":" + log;
        if (fileOutputStream != null) {
            try {
                Log.e("yuhao", log);
                fileOutputStream.write(data.getBytes());
                fileOutputStream.write("\n".getBytes());
                fileOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void loge(String log) {
        Log.e("yuhao", log);
    }

    public void saveData2(byte[] data) {
        saveData2(data, 0, data.length);
    }

    private SimpleDateFormat simpleDateFormat;

    public void saveData2(byte[] data, int begin, int length) {
        StringBuilder stringBuilder = new StringBuilder(simpleDateFormat.format(new Date()));
        stringBuilder.append(",");
        for (int i = begin; i < length; i++) {
            stringBuilder.append(Integer.toHexString(data[i])).append(",");
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

    public static YhLog2File getSingle() {
        if (yhLog2File == null) {
            yhLog2File = new YhLog2File();
        }
        return yhLog2File;
    }

}
