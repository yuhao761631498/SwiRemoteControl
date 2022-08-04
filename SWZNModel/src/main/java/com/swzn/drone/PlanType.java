package com.swzn.drone;

/**
 *
 * @author xx
 * @date 2018/2/5
 */
public enum PlanType {
    O2Plan_Normal(0, "Normal",""),
    O2Plan_Plus(1, "Plus","AccessoryPassthrough"),
    O2Plan_Gek(2, "Gek",""),
    O2Plan_5_8G(3, "5.8G","demo"),
    O2Plan_ByrdT(4,"ByrdT","AR8020"),//ByrT就是Saga
    O2Plan_Other(5, "Other",""),
    O2Plan_Saga(6, "Saga","AR8020"),
    O2Plan_Z4B(7, "Z4B","gdu_z4b"),//gdu_z4b
    O2Plan_SagaPro(8, "SagaPro","AR8020"),//gdu_z4b
    D6B_C201_D(11, "D6B","AR8020");//

    int key;
    String value;
    String modelName;
    PlanType(int key, String value,String modelName)
    {
        this.key = key;
        this.value = value;
        this.modelName = modelName;
    }

    public String getModelName() {
        return modelName;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static PlanType get(int key){
        for(PlanType e : PlanType.values()){
            if (e.getKey() == key) {
                return e;
            }
        }
        return null;
    }
}
