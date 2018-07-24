package com.yanlei.dao;

import com.yanlei.model.*;

import java.util.List;

public interface DepartmentDao {

    void  delDepartmentQjwAll();

    void  delDepartmentQwbAll();

    void  delDepartmentRdbAll();

    void  delDepartmentZfbAll();

    void  delDepartmentZxAll();


    void addDepartmentQjwList(List<DepartmentQjw> departmentQjws);

    void addDepartmentQwbList(List<DepartmentQwb> departmentQwbs);

    void addDepartmentRdbList(List<DepartmentRdb> departmentRdbs);

    void addDepartmentZfbList(List<DepartmentZfb> departmentZfbs);

    void addDepartmentZxList(List<DepartmentZx> departmentZxs);


}
