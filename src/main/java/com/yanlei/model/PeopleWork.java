package com.yanlei.model;

import org.omg.CORBA.PRIVATE_MEMBER;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * 2 * @Author: x
 * 3 * @Date: 2018/1/22 15:48
 * 4
 */
@XmlRootElement
public class PeopleWork {
    private Integer id; //主键
    private String work; //行业
    private Double proportion; //百分比
    private Long number; //数量
    private Date updatetime; //时间

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

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
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
