package com.yanlei.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * 2 * @Author: x
 * 3 * @Date: 2018/1/22 15:18
 * 4
 */
@XmlRootElement
public class PeopleNation {
    private Integer id; //主键
    private String nation; //民族
    private Long nanumber; //数量
    private Double proportion; //百分比
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

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Long getNanumber() {
        return nanumber;
    }

    public void setNanumber(Long nanumber) {
        this.nanumber = nanumber;
    }

    public Double getProportion() {
        return proportion;
    }

    public void setProportion(Double proportion) {
        this.proportion = proportion;
    }
}
