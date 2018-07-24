package com.yanlei.model.shehui;

/**
 * @Author: x
 * @Date: Created in 15:37 2018/5/17
 */
public class People {

    private Integer intValue;
    private String streetName;
    private double doubleValue;

    public double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @Override
    public String toString() {
        return "People{" +
                "intValue=" + intValue +
                ", streetName='" + streetName + '\'' +
                '}';
    }
}
