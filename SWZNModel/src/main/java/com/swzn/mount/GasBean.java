package com.swzn.mount;

public class GasBean {
    private String gasName;

    private float gasConcentration;

    private String gasUnit;

    /**
     * 是否超标
     */
    private boolean isConcentration;

    public String getGasName() {
        return gasName;
    }

    public void setGasName(String gasName) {
        this.gasName = gasName;
    }

    public float getGasConcentration() {
        return gasConcentration;
    }

    public void setGasConcentration(float gasConcentration) {
//        switch (gasName){
//            case "O2":
//            case "NO2":
//            case "SO2":
//            case "CL2":
//            case "O3":
//                this.gasConcentration = gasConcentration / 10.0f;
//                break;
//            default:
//                this.gasConcentration = gasConcentration;
//                break;
//
//        }
        this.gasConcentration = gasConcentration;
    }

    public String getGasUnit() {
        return gasUnit;
    }

    public void setGasUnit(String gasUnit) {
        this.gasUnit = gasUnit;
    }

    public boolean isConcentration() {
        return isConcentration;
    }

    public void setConcentration(boolean concentration) {
        isConcentration = concentration;
    }
}
