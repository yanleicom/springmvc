package com.yanlei.model;

import java.util.Date;

/**
 * @Author: x
 * @Date: Created in 16:44 2018/4/13
 */
public class SumAndAvg {

    private String name;
    private Date startTime;
    private Date endTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
