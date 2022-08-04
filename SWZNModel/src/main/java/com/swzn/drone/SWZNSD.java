package com.swzn.drone;

/**
 * Created by xx on 2018/6/6.
 * 飞机存储卡
 */

public class SWZNSD {
    /**
     *  剩余容量
     */
    private int remainCardSum;

    /**
     * 总容量
     */
    private int totalCardSum;

    public int getRemainCardSum() {
        return remainCardSum;
    }

    public void setRemainCardSum(int reMainCardSum) {
        this.remainCardSum = reMainCardSum;
    }

    public int getTotalCardSum() {
        return totalCardSum;
    }

    public void setTotalCardSum(int totalCardSum) {
        this.totalCardSum = totalCardSum;
    }
}
