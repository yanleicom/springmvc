package com.yanlei.model;

import java.util.Date;

public class DepartmentQwb {
    private Integer id;
    private String department;
    private Long number;
    private Date updatatime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Date getUpdatatime() {
        return updatatime;
    }

    public void setUpdatatime(Date updatatime) {
        this.updatatime = updatatime;
    }
}
