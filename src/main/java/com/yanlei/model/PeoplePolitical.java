package com.yanlei.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * 2 * @Author: x
 * 3 * @Date: 2018/1/22 15:47
 * 4
 */
@XmlRootElement
public class PeoplePolitical {
    private Integer id; //主键
    private String political; //政治面貌
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

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
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
