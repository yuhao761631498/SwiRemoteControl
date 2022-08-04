package com.swzn.util;


import com.swzn.mount.GasBean;
import com.swzn.mount.GasType;

/**
 * 气体检测工具类
 */
public class GasDetectorUtil {

    /**
     *  根据气体名称获取类型
     * @param gasBean
     * @return
     */
    public static byte getGasTypeByName(GasBean gasBean){
        switch (gasBean.getGasName()){
            case "SO2":
                return GasType.SO2.getKey();
            case "NO2":
                return GasType.NO2.getKey();
            case "CO":
                return GasType.CO.getKey();
            case "O3":
                return GasType.O3.getKey();
            case "AQI":
                return GasType.AQI.getKey();
            case "PM2.5":
                return GasType.PM2_5.getKey();
            case "PM10":
                return GasType.PM10.getKey();
        }
        return 0;
    }

}
