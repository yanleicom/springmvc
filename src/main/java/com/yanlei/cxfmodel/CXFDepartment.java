package com.yanlei.cxfmodel;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * @Author: x
 * @Date: Created in 10:52 2018/3/26
 */

@XmlRootElement
public class CXFDepartment {
    private Integer id;
    private String name;
    private Integer departmentId;
    private Date createTime;
    private Integer areaId;

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

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }
}

