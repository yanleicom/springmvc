package com.besjon.pojo;

import java.io.Serializable;

/**
 * @Author: x
 * @Date: Created in 9:47 2018/5/8
 */
public class JingJiPage implements Serializable {

    private Integer id; //主键
    private String name; //大类别名称
    private Long intValue; //整数值
    private String leve1; // 街道
    private String level2; // 类别名称
    private String unit; //计量单位
    private String source; //来源

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

    public String getLeve1() {
        return leve1;
    }

    public void setLeve1(String leve1) {
        this.leve1 = leve1;
    }

    public String getLevel2() {
        return level2;
    }

    public void setLevel2(String level2) {
        this.level2 = level2;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public JingJiPage(Integer id, String name, Long intValue, String leve1, String level2, String unit, String source) {
        this.id = id;
        this.name = name;
        this.intValue = intValue;
        this.leve1 = leve1;
        this.level2 = level2;
        this.unit = unit;
        this.source = source;
    }

    public JingJiPage() {
    }
}
