package com.swzn.map;

/**
 * Created by xx on 2017/5/26.
 */
public class PathPlanBean
{
    /**
     * <p>航迹规划的经度  --xx</p>
     */
    public int longitude;

    /**
     * <p>航迹规划的维度 --xx</p>
     */
    public int latitude;

    /**
     * <p>当前的索引号 --xx</p>
     */
    public byte currentIndex;

    /**
     * 水平速度  cm/s
     */
    public short hs = 300;

    /**
     * 0 自动对准下一个点
     * 1 使用当前航向
     * 2 自定义航向
     */
    public byte headingType = 0;

    /**
     * 朝向类型为 2 时有效：
     * 角度，(-PI,PI )*100
     */
    public short headingAngle;

    /**
     * 0 先水平再高度
     * 1 先高度再水平
     * 2 高度水平同时
     */
    public byte verticalType;

    /**
     * 高度值
     * cm
     */
    public int height;

    /**
     * 上升速度值
     * 如果垂直类型为 2,此字段无效
     */
    public short vSpeed;

    public byte countPoint;
}
