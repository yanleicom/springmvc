package com.yanlei.model.shehui;

/**
 * @Author: x
 * @Date: Created in 15:37 2018/5/17
 */
public class People {

    private Integer intValue;
    private String streetName;
    private double doubleValue;
    private String birthday;
    private double underAge;
    private double labour;
    private double oldAge;


    @Override
    public String toString() {
        return "People{" +
                "intValue=" + intValue +
                ", streetName='" + streetName + '\'' +
                ", doubleValue=" + doubleValue +
                ", birthday='" + birthday + '\'' +
                ", underAge=" + underAge +
                ", labour=" + labour +
                ", oldAge=" + oldAge +
                '}';
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

    public double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public double getUnderAge() {
        return underAge;
    }

    public void setUnderAge(double underAge) {
        this.underAge = underAge;
    }

    public double getLabour() {
        return labour;
    }

    public void setLabour(double labour) {
        this.labour = labour;
    }

    public double getOldAge() {
        return oldAge;
    }

    public void setOldAge(double oldAge) {
        this.oldAge = oldAge;
    }
}
