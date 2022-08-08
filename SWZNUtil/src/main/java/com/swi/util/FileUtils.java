package com.swi.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by xx on 2017/12/22.
 */

public class FileUtils {
    private static boolean flag;

    /**
     * 文件是否存在
     * @param fileName
     * @return
     */
    public static boolean isFileExist(String fileName) {
        if (fileName != null) {
            File file = new File(fileName);
            return file.exists();
        } else {
            return false;
        }
    }

    /**
     * 删除文件或者文件夹
     * @param fileName
     */
    public static void deleteFile(String fileName) {
        if (fileName != null) {
            File file = new File(fileName);
            if (file != null && file.exists()) {
                if (file.isFile()) {
                    deleteFiles(fileName);
                } else {
                    deleteDirectory(fileName);
                }
            }
        }
    }

    /**
     * 删除文件
     * @param sPath
     * @return
     */
    public static boolean deleteFiles(String sPath) {
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 删除文件夹
     * @param sPath
     * @return
     */
    public static boolean deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        if (files != null) for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFiles(files[i].getAbsolutePath());
                if (!flag) break;
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 获取文件，遇见换行结束
     * @param path
     * @return
     */
    public static String getFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String         s      = reader.readLine();
                reader.close();
                return s;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取文件中所有内容
     * @param path
     * @return
     */
    public static String getAllFile(String path){
        File file = new File(path);
        String allS = "";
        if (file.exists() && file.isFile()) {
            try {
//                BufferedReader reader = new BufferedReader(new FileReader(file));
                FileInputStream in = new FileInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
                String         s      = null;
                while((s = reader.readLine()) != null){
                    allS += s + "\n";
                }
                reader.close();
                return allS;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 存储文件
     * @param strPath
     * @param data
     * @return
     */
    public static long setRawDataToFile(String strPath, String data) {
        long wrSize = 0;
        if (data != null && data.length() > 0 && strPath != null) {
            if (strPath.endsWith(File.separator)) {
                return wrSize;
            }
            File file = new File(strPath);
            if (!file.getParentFile().exists()) {
                if (!file.getParentFile().mkdirs()) {
                    return wrSize;
                }
            }

            try {
                boolean bFileWritable = true;
                if (file.exists() && file.isFile()) {
                    bFileWritable = file.delete();
                }
                if (bFileWritable) {
                    PrintWriter pw = new PrintWriter(new FileOutputStream(file));
                    pw.println(data);
                    pw.close();
                    wrSize = file.length();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return wrSize;
    }


    /**
     * 删除文件夹中的内容
     * @param sPath
     * @return
     */
    public static boolean deleteDirectoryContent(String sPath){
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        if (files != null) for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFiles(files[i].getAbsolutePath());
                if (!flag) break;
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        return true;
    }

    /**
     * 创建文件夹
     * @param filePath
     */
    public static void createFolder(String filePath){
        if (filePath != null) {
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                if (!file.getParentFile().mkdirs()) {
                    return ;
                }
            }
            if (!file.exists()) {
                file.mkdir();
            }
        }
    }

    /**
     * 复制单个文件
     * @param oldPathName
     * @param newPathName
     * @return
     */
    public static boolean copyFile(String oldPathName, String newPathName) {
        try {
            File oldFile = new File(oldPathName);
            if (!oldFile.exists()) {
                return false;
            } else if (!oldFile.isFile()) {
                return false;
            } else if (!oldFile.canRead()) {
                return false;
            }

            /* 如果不需要打log，可以使用下面的语句
            if (!oldFile.exists() || !oldFile.isFile() || !oldFile.canRead()) {
                return false;
            }
            */

            FileInputStream fileInputStream = new FileInputStream(oldPathName);
            FileOutputStream fileOutputStream = new FileOutputStream(newPathName);
            byte[] buffer = new byte[1024];
            int byteRead;
            while (-1 != (byteRead = fileInputStream.read(buffer))) {
                fileOutputStream.write(buffer, 0, byteRead);
            }
            fileInputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static  FileOutputStream mFileOutputStream1;
    public static void saveData2(String newPathName, byte[] data){
        if (mFileOutputStream1 == null) {
            try {
                mFileOutputStream1 = new FileOutputStream(newPathName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(mFileOutputStream1 != null )
        {
            try {
                mFileOutputStream1.write(data);
                mFileOutputStream1.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static  FileOutputStream mFileOutputStream2;
    public static void saveData3(String newPathName, byte[] data){
        if (mFileOutputStream2 == null) {
            try {
                mFileOutputStream2 = new FileOutputStream(newPathName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(mFileOutputStream2 != null )
        {
            try {
                mFileOutputStream2.write(data);
                mFileOutputStream2.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static byte[] readFileToByteArray(String path) {
        File file = new File(path);
        if(!file.exists()) {
            Log.e("TAG","File doesn't exist!");
            return null;
        }
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            long inSize = in.getChannel().size();//判断FileInputStream中是否有内容
            if (inSize == 0) {
                Log.d("TAG","The FileInputStream has no content!");
                return null;
            }

            byte[] buffer = new byte[in.available()];//in.available() 表示要读取的文件中的数据长度
            in.read(buffer);  //将文件中的数据读到buffer中
            return buffer;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                return null;
            }
            //或IoUtils.closeQuietly(in);
        }
    }
}

