package com.yanlei.dao.webdao;

import com.yanlei.cxfmodel.CXFDepartment;

import java.util.List;

/**
 * @Author: x
 * @Date: Created in 10:57 2018/3/26
 */
public interface CXFDepartmentDao {
    List<CXFDepartment> getDepartments();

    CXFDepartment getDepartmentByName(String name);

    CXFDepartment getDepartmentById(Integer id);

    void saveDepartment(CXFDepartment cxfDepartment);

    Integer saveCXFDepartments(List<CXFDepartment> cxfDepartments);

    Integer updateCXFDepartment(CXFDepartment cxfDepartment);
}
