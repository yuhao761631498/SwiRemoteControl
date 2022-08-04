package com.swzn.drone;

import java.util.ArrayList;
import java.util.List;

public class Z4bBatteryState {

    protected BatteryInfo batteryNo1;
    protected BatteryInfo batteryNo2;

    public BatteryInfo getBatteryNo1(){
        if(batteryNo1 == null){
            batteryNo1 = new BatteryInfo();
        }
        return batteryNo1;
    }

    public BatteryInfo getBatteryNo2(){
        if(batteryNo2 == null){
            batteryNo2 = new BatteryInfo();
        }
        return batteryNo2;
    }

    public class BatteryInfo {
        /*电池温度*/
        private short temp;

        /*总电压*/
        private short totalVoltage;

        /*电量百分比*/
        private short percentage;

        /*剩余容量*/
        private short remainingCapacity;

        /*实际总容量*/
        private short actualTotalCapacity;

        /*充放电次数*/
        private short chargingCycleTimes;

        /*设计总容量*/
        private short designedTotalCapacity;

        /*电芯1 电压*/
        private short cell_1_Voltage;

        /*电芯2 电压*/
        private short cell_2_Voltage;

        /*电芯3 电压*/
        private short cell_3_Voltage;

        /*电芯4 电压*/
        private short cell_4_Voltage;

        /*电芯5 电压*/
        private short cell_5_Voltage;

        /*电芯6 电压*/
        private short cell_6_Voltage;

        /*当前电流*/
        private int current;

        public short getTemp() {
            return temp;
        }

        public void setTemp(short temp) {
            this.temp = temp;
        }

        public short getTotalVoltage() {
            return totalVoltage;
        }

        public void setTotalVoltage(short totalVoltage) {
            this.totalVoltage = totalVoltage;
        }

        public short getPercentage() {
            return percentage;
        }

        public void setPercentage(short percentage) {
            this.percentage = percentage;
        }

        public short getRemainingCapacity() {
            return remainingCapacity;
        }

        public void setRemainingCapacity(short remainingCapacity) {
            this.remainingCapacity = remainingCapacity;
        }

        public short getActualTotalCapacity() {
            return actualTotalCapacity;
        }

        public void setActualTotalCapacity(short actualTotalCapacity) {
            this.actualTotalCapacity = actualTotalCapacity;
        }

        public short getChargingCycleTimes() {
            return chargingCycleTimes;
        }

        public void setChargingCycleTimes(short chargingCycleTimes) {
            this.chargingCycleTimes = chargingCycleTimes;
        }

        public short getDesignedTotalCapacity() {
            return designedTotalCapacity;
        }

        public void setDesignedTotalCapacity(short designedTotalCapacity) {
            this.designedTotalCapacity = designedTotalCapacity;
        }

        public short getCell_1_Voltage() {
            return cell_1_Voltage;
        }

        public void setCell_1_Voltage(short cell_1_Voltage) {
            this.cell_1_Voltage = cell_1_Voltage;
        }

        public short getCell_2_Voltage() {
            return cell_2_Voltage;
        }

        public void setCell_2_Voltage(short cell_2_Voltage) {
            this.cell_2_Voltage = cell_2_Voltage;
        }

        public short getCell_3_Voltage() {
            return cell_3_Voltage;
        }

        public void setCell_3_Voltage(short cell_3_Voltage) {
            this.cell_3_Voltage = cell_3_Voltage;
        }

        public short getCell_4_Voltage() {
            return cell_4_Voltage;
        }

        public void setCell_4_Voltage(short cell_4_Voltage) {
            this.cell_4_Voltage = cell_4_Voltage;
        }

        public short getCell_5_Voltage() {
            return cell_5_Voltage;
        }

        public void setCell_5_Voltage(short cell_5_Voltage) {
            this.cell_5_Voltage = cell_5_Voltage;
        }

        public short getCell_6_Voltage() {
            return cell_6_Voltage;
        }

        public void setCell_6_Voltage(short cell_6_Voltage) {
            this.cell_6_Voltage = cell_6_Voltage;
        }

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public List<Integer> getCellList(){
            List<Integer> cellList = new ArrayList();
            cellList.add((int) cell_1_Voltage);
            cellList.add((int) cell_2_Voltage);
            cellList.add((int) cell_3_Voltage);
            cellList.add((int) cell_4_Voltage);
            cellList.add((int) cell_5_Voltage);
            cellList.add((int) cell_6_Voltage);
            return cellList;
        }
    }


}
