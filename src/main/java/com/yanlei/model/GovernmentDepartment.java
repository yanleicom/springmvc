package com.yanlei.model;

import java.util.Date;

/**
 * @Author: x
 * @Date: Created in 11:01 2018/3/27
 */
public class GovernmentDepartment {

    private Integer id;
    private String name; //'数据名称',
    private Long intValue; //'数据值',
    private Double doubleValue; // '数据百分比',
    private Date acquisitionTime; // '采集时间',
    private String cycle; //'项目周期(本年/本月/几几年几月几日)',
    private Integer departmentId; //'部门id(对应business_data部门id)',
    private String unit; //计量单位(十万人,百万人,千克,吨,元,万元,件等等)
    private String type; //类型
    private String level; //级别1
    private String level2; //级别2
    private String level3; //级别3
    private String source; //数据来源


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIntValue() {
        return intValue;
    }

    public void setIntValue(Long intValue) {
        this.intValue = intValue;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public Date getAcquisitionTime() {
        return acquisitionTime;
    }

    public void setAcquisitionTime(Date acquisitionTime) {
        this.acquisitionTime = acquisitionTime;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel2() {
        return level2;
    }

    public void setLevel2(String level2) {
        this.level2 = level2;
    }

    public String getLevel3() {
        return level3;
    }

    public void setLevel3(String level3) {
        this.level3 = level3;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "GovernmentDepartment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", intValue=" + intValue +
                ", doubleValue=" + doubleValue +
                ", acquisitionTime=" + acquisitionTime +
                ", cycle='" + cycle + '\'' +
                ", departmentId=" + departmentId +
                ", unit='" + unit + '\'' +
                ", type='" + type + '\'' +
                ", level='" + level + '\'' +
                ", level2='" + level2 + '\'' +
                ", level3='" + level3 + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
