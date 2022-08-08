package com.swi.drone;

public class BatteryInfo_Z4B
{
    /*******************
     * 电池的温度
     */
    private short temp;

    /***************
     * 总电压---xx
     * 单位mV
     */
    private int totalVoltage;

    /************
     * 电量百分比---xx
     */
    private int power;

    /**************
     * 剩余电容---xx
     * 单位 mAH
     */
    private int   battery_capacity_left;

    /*****************
     * 电池总容量---实际
     * 单位 mAH
     */
    private int   battery_capacity_all;

    /******
     * 充放次数
     */
    private int   inflationNumber;

    /*****************
     * 电池总容量---设计
     * 单位 mAH
     */
    private int   battery_capacity_set;

    /***************
     * 电芯电压--1
     * 单位mV
     */
    private int batteris_One;

    /***************
     * 电芯电压--2
     * 单位mV
     */
    private int batteris_two;


    /***************
     * 电芯电压--3
     * 单位mV
     */
    private int batteris_three;


    /***************
     * 电芯电压--4
     * 单位mV
     */
    private int batteris_four;

    /***************
     * 电芯电压--5
     * 单位mV
     */
    private int batteris_five;

    /***************
     * 电芯电压--6
     * 单位mV
     */
    private int batteris_six;

    /**************
     * 当前电流
     */
    private int currentElectricity;


    public short getTemp() {
        return temp;
    }

    public void setTemp(short temp) {
        this.temp = temp;
    }

    public int getTotalVoltage() {
        return totalVoltage;
    }

    public void setTotalVoltage(int totalVoltage) {
        this.totalVoltage = totalVoltage;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getBattery_capacity_left() {
        return battery_capacity_left;
    }

    public void setBattery_capacity_left(int battery_capacity_left) {
        this.battery_capacity_left = battery_capacity_left;
    }

    public int getBattery_capacity_all() {
        return battery_capacity_all;
    }

    public void setBattery_capacity_all(int battery_capacity_all) {
        this.battery_capacity_all = battery_capacity_all;
    }

    public int getInflationNumber() {
        return inflationNumber;
    }

    public void setInflationNumber(int inflationNumber) {
        this.inflationNumber = inflationNumber;
    }

    public int getBattery_capacity_set() {
        return battery_capacity_set;
    }

    public void setBattery_capacity_set(int battery_capacity_set) {
        this.battery_capacity_set = battery_capacity_set;
    }

    public int getBatteris_One() {
        return batteris_One;
    }

    public void setBatteris_One(int batteris_One) {
        this.batteris_One = batteris_One;
    }

    public int getBatteris_two() {
        return batteris_two;
    }

    public void setBatteris_two(int batteris_two) {
        this.batteris_two = batteris_two;
    }

    public int getBatteris_three() {
        return batteris_three;
    }

    public void setBatteris_three(int batteris_three) {
        this.batteris_three = batteris_three;
    }

    public int getBatteris_four() {
        return batteris_four;
    }

    public void setBatteris_four(int batteris_four) {
        this.batteris_four = batteris_four;
    }

    public int getBatteris_five() {
        return batteris_five;
    }

    public void setBatteris_five(int batteris_five) {
        this.batteris_five = batteris_five;
    }

    public int getBatteris_six() {
        return batteris_six;
    }

    public void setBatteris_six(int batteris_six) {
        this.batteris_six = batteris_six;
    }

    public int getCurrentElectricity() {
        return currentElectricity;
    }

    public void setCurrentElectricity(int currentElectricity) {
        this.currentElectricity = currentElectricity;
    }


    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("");
        stringBuffer.append("temp:").append(temp).append(",totalVoltage:").append(totalVoltage);
        stringBuffer.append(",power:").append(power);
        stringBuffer.append(",battery_capacity_left:").append(battery_capacity_left);
        stringBuffer.append(",battery_capacity_all:").append(battery_capacity_all);
        stringBuffer.append(",inflationNumber:").append(inflationNumber);
        stringBuffer.append(",battery_capacity_set:").append(battery_capacity_set);
        stringBuffer.append(",batteris_One:").append(batteris_One);
        stringBuffer.append(",batteris_two:").append(batteris_two);
        stringBuffer.append(",batteris_three:").append(batteris_three);
        stringBuffer.append(",batteris_four:").append(batteris_four);
        stringBuffer.append(",batteris_five:").append(batteris_five);
        stringBuffer.append(",batteris_six:").append(batteris_six);
        stringBuffer.append(",currentElectricity:").append(currentElectricity);
        return stringBuffer.toString();
    }
}
