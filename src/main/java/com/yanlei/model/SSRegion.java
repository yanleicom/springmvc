package com.yanlei.model;

import java.util.Date;

/**
 * 2 * @Author: xiayuanlei
 * 3 * @Date: 2018/1/22 18:50
 * 4
 */
public class SSRegion {
    private Integer id;
   // private String region;
    private String name;
    private Double proportion;
    private Long number;
    private Date updatetime;

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

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

    public Double getProportion() {
        return proportion;
    }

    public void setProportion(Double proportion) {
        this.proportion = proportion;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
