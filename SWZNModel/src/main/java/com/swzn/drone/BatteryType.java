package com.swzn.drone;

public enum BatteryType
{
    Factory_None((byte)0,(byte)0),//未知电池
    Factory_JINRUI((byte)1,(byte)1),//精锐的电池
    Factory_SAINENG((byte)2,(byte)2),//赛能的电池
    Factory_DIBIKE((byte)3,(byte)3),//迪比科的电池
    Factory_JIUSEN((byte)5,(byte)6);//久森的电池

   private byte type_InDrone;

   private byte type_InWebServer;

    BatteryType(byte type_InDrone, byte type_InWebServer)
   {
       this.type_InDrone = type_InDrone;
       this.type_InWebServer = type_InWebServer;
   }

    public byte getType_InDrone() {
        return type_InDrone;
    }

    public byte getType_InWebServer() {
        return type_InWebServer;
    }

    public static BatteryType get(byte key){
        for(BatteryType e : BatteryType.values()){
            if (e.getType_InDrone() == key)
            {
                return e;
            }
        }
        return Factory_None;
    }
}
