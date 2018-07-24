package com.yanlei.model;

import java.util.Date;

/**
 * @Author: x
 * @Date: Created in 16:58 2018/3/6
 */
public class ZdpycYear {
    private Integer id; //主键
    private String column1; //区域
    private Double column2; //百分比
    private Long column3;  //数量
    private Date updatetime; //时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public Double getColumn2() {
        return column2;
    }

    public void setColumn2(Double column2) {
        this.column2 = column2;
    }

    public Long getColumn3() {
        return column3;
    }

    public void setColumn3(Long column3) {
        this.column3 = column3;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
