package com.swzn.event;

/**
 * Created by xx on 2017/5/3.
 */
public class DownloadListenerEvent {
    private int type;
    private int progress;
    private int what;
    private String filePath;
    private long fileCount;
    public DownloadListenerEvent(int type,int progress,int what,String filePath,long fileCount){
        this.type=type;
        this.progress=progress;
        this.what=what;
        this.filePath=filePath;
        this.fileCount=fileCount;
    }

    public int getType(){
        return type;
    }
    public int getProgress(){
        return progress;
    }
    public int getWhat(){
        return what;
    }
    public String getFilePath(){
        return filePath;
    }

    public long getFileCount(){
        return fileCount;
    }
}
