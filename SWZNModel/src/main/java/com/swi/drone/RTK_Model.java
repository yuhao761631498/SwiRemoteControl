package com.swi.drone;

public class RTK_Model {
     public enum RTK_Status {
        RTK_NA(0, "N/A"),
        RTK_2D(2, "2D"),
        RTK_3D(3, "3D"),
        RTK_Float(4, "Float"),
        RTK_Fixed(5, "Fixed");

        int key;
        String status;

        RTK_Status(int key, String status) {
            this.key = key;
            this.status = status;
        }

        public int getKey() {
            return key;
        }

        public static RTK_Status get(int key) {
            for (RTK_Status e : RTK_Status.values()) {
                if (e.getKey() == key) {
                    return e;
                }
            }
            return null;
        }

        public String getStatus() {
            return status;
        }
    }

    /**
     * rtk模块1 差分状态 0:未定位;1:2D定位;2:3D定位;4:RTK float;5:RTK fixed
     */
    private RTK_Status rtk1_status = RTK_Status.RTK_NA;

    /**
     * rtk模块2 差分状态；
     */
    private RTK_Status rtk2_status = RTK_Status.RTK_NA;

    /**
     * 抗磁干扰模式
     */
    private boolean isAntiMagnetic;

    /**
     * rtk模块数 0：单天线版本；1：双天线版本
     */
    private int rtkAntenna;

    public static RTK_Model parseRTKStatus(byte rtkByte) {
        RTK_Model rtk_model = new RTK_Model();
        rtk_model.rtk1_status = RTK_Status.get(getBits(rtkByte, 0, 3));
        rtk_model.rtk2_status = RTK_Status.get(getBits(rtkByte, 3, 3));
        rtk_model.isAntiMagnetic = (getBits(rtkByte, 6, 1) == 1);
        rtk_model.rtkAntenna = getBits(rtkByte, 7, 1);
        return rtk_model;
    }

    /**
     * rtk模块1
     *
     * @return
     */
    public String getRtk1_status() {
        return rtk1_status.getStatus();
    }

    public void setRtk1_status(String status){
        rtk1_status.status = status;
    }

    /**
     * rtk模块2 差分状态
     *
     * @return
     */
    public String getRtk2_status() {
        return rtk2_status.getStatus();
    }

    /**
     * 是否为抗磁干扰模式
     *
     * @return
     */
    public boolean isAntiMagnetic() {
        return isAntiMagnetic;
    }

    /**
     * @param antiMagnetic
     */
    public void setAntiMagnetic(boolean antiMagnetic) {
        isAntiMagnetic = antiMagnetic;
    }

    /**
     * 获取rtk模块数
     *
     * @return
     */
    public int getRtkAntenna() {
        return rtkAntenna;
    }

    /**
     * 设置rtk模块数
     *
     * @return
     */
    public void setRtkAntenna(int rtkAntenna) {
        this.rtkAntenna = rtkAntenna;
    }

    public static int getBits(byte b, int start, int length) {
        int bit = (int) ((b >> start) & (0xFF >> (8 - length)));
        return bit;
    }
}
