package com.swzn.multimedia;

import java.io.Serializable;

/**
 * Created by xx on 2016/6/15.
 */
public class MultiMediaItemBean implements Serializable
{

    public static final int IMAGE = 0x01;

    public static final int MEDIA = 0x02;

    public static final int MEDIA_NoNeedMuxer = 0x03;

    /**
     * <p>是否是选择的状态</p>
     */
    public boolean isSelect;

    /**
     * <p>是什么类型的文件 图片、视频</p>
     */
    public int type;

    /**
     * <p>文件名字</p>
     */
    public String name;//文件的名字

    /**
     * <p>图片/视频封面路径</p>
     */
    public String coverPath;

    /**
     * <p>图片/视频原文件路径</p>
     */
    public String path; //文件的路径

    /**
     * <p>最后一次修改的时间</p>
     */
    public  long lastModified; //最后一次修改的时间

    /*********
     * 转化好的，时间Str (YYYY-MM)
     */
    public String lastModified_Str;

    /**
     * <p>播放时间长</p>
     * <p>毫秒级别。要除以1000</p>
     */
    public int duration;

    /**
     * 文件位置
     */
    public int position;


    /**
     * <p>如果为true需要显示 对应的 icon</p>
     */
    public boolean showHadDowned = false;

    /**
     *  <p>是否存有本地副本</p>
     */
    public boolean isTransprintExit=false;

    /**
     * 本地媒体库显示是否是下载的原图
     */
    public boolean HadDownSource=false;

    /**
     * 文件大小
     */
    public String size;

    /**
     * 视频高度
     */
    public int videoHeight;

}
