package com.besjon.pojo;

import java.io.Serializable;

/**
 * 2 * @Author: xiayuanlei
 * 3 * @Date: 2018/1/22 15:58
 * 4
 */
public class Fenlist1 implements Serializable {

    private Integer id;
    private String name;
    private Double percent;
    private Long num;
    private String unit;
    private String source;

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

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
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

    public Fenlist1(Integer id, String name, Double percent, Long num) {
        this.id = id;
        this.name = name;
        this.percent = percent;
        this.num = num;
    }

    public Fenlist1(Integer id, String name, Double percent, Long num, String unit) {
        this.id = id;
        this.name = name;
        this.percent = percent;
        this.num = num;
        this.unit = unit;


    }

    public Fenlist1(Integer id, String name, Double percent, Long num, String unit, String source) {
        this.id = id;
        this.name = name;
        this.percent = percent;
        this.num = num;
        this.unit = unit;
        this.source = source;
    }
}