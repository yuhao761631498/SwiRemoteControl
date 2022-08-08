package com.swi;

import java.util.List;

public class SWZNFile {

    /**
     * 文件名
     */
    private String name;
    /**
     * 文件路径
     */
    private String path;
    /**
     * 文件大小
     */
    private long size;
    /**
     * 是否为文件夹
     */
    private boolean isDirectory;
    /**
     * 是否为文件
     */
    private boolean isisFile;
    /**
     * 子文件夹列表
     */
    private List<SWZNFile> directorys;
    /**
     * 子文件列表
     */
    private List<SWZNFile> files;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }

    public boolean isIsisFile() {
        return isisFile;
    }

    public void setIsisFile(boolean isisFile) {
        this.isisFile = isisFile;
    }

    public List<SWZNFile> getDirectorys() {
        return directorys;
    }

    public void setDirectorys(List<SWZNFile> directorys) {
        this.directorys = directorys;
    }

    public List<SWZNFile> getFiles() {
        return files;
    }

    public void setFiles(List<SWZNFile> files) {
        this.files = files;
    }
}
