package com.swzn;

/**
 * Created by xx on 2017/2/24.
 * 算法标识结合
 */
public class AlgorithmMark
{

    private static AlgorithmMark algorithmMark;

    public static AlgorithmMark getSingleton()
    {
        if(algorithmMark == null )
        {
            algorithmMark = new AlgorithmMark();
            algorithmMark.currentALG = AlgorithmType.NONE;
        }
        return algorithmMark;
    }

    /****
     * 算法的类型
     * Track_GPS：GPS跟踪；
     * Track_VIDEO：视频跟踪；
     * Gesture：手势；
     * Surrond_GPS：GPS环绕；
     * Surrond_IMG：视觉环绕；
     * ObStacle：避障
     * Panorrama：全景
     * Vertical_pull：垂直拉升，
     * Inverted_Photo:渐远自拍
     * DEVICE_RECOGNISE  器件识别
     */
    public enum AlgorithmType
    {
        Track_GPS,Track_VIDEO,Gesture,Surrond_GPS,Surrond_IMG,Panorrama,Vertical_pull,Inverted_Photo,NONE,DEVICE_RECOGNISE
    }

    public AlgorithmType currentALG;

    public boolean Track_GPS;

    public boolean Track_VIDEO;

    public boolean Gesture;

    public boolean Surrond_GPS;

    public boolean Surrond_IMG;

    public boolean ObStacle;

    public boolean Track_VIDEO_03;

    public boolean Panorrama;

}
