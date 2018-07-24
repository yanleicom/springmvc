package com.yanlei.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * @Author: x
 * @Date: Created in 16:03 2018/3/23
 */
@XmlRootElement
public class BusinessData {

    private Integer id;
    private Integer appId; //'应用id',
    private Integer userId;//'用户id',
    private String name; //'数据名称',
    private Long intValue; //'数据值(整数)',
    private Double doubleValue; //'数据百分比(小数)',
    private Date acquisitionTime; //'采集时间',
    private String cycle; //'统计期间名称',
    private Integer encrypted; //'是否加密 0没有 1加密',
    private Date updateTime; // '更新时间',
    private Integer departmentId; //'部门id',
    private Date startTime; //'起始时间(精确的统计时间 起点)',
    private Date endTime; //'统计期间终点时间(终点)',
    private Integer areaId; //'外键',
    private String statictType; //'是否发生数,是否累计数',
    private String unit; //'计量单位(十万人,百万人,顿,元,万元,件等等)',
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

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getEncrypted() {
        return encrypted;
    }

    public void setEncrypted(Integer encrypted) {
        this.encrypted = encrypted;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getStatictType() {
        return statictType;
    }

    public void setStatictType(String statictType) {
        this.statictType = statictType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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


}
