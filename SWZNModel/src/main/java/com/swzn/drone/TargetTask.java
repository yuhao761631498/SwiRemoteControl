package com.swzn.drone;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xx on 2018/9/5.
 * 目标检测任务，包含多个目标
 */

public class TargetTask implements Serializable{

    private int id;        //目标id
    private String name;   //目标任务名
    private long time;    //目标任务时间
    private List<TargetMode> targetModes;  //目标任务中包含的多个目标
    private boolean isSelect;
    private boolean isFolder; //标记目录

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public List<TargetMode> getTargetModes() {
        return targetModes;
    }

    public void setTargetModes(List<TargetMode> targetModes) {
        this.targetModes = targetModes;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public boolean isFolder() {
        return isFolder;
    }

    public void setFolder(boolean folder) {
        isFolder = folder;
    }
}
