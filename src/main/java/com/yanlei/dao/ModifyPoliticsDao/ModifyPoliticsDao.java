package com.yanlei.dao.ModifyPoliticsDao;

import com.yanlei.model.GovernmentDepartment;

import java.util.List; /**
 * @Author: x
 * @Date: Created in 10:34 2018/4/18
 */
public interface ModifyPoliticsDao {

    int updateDepartment(List<GovernmentDepartment> departments);

    int delDepartmentOne(Integer id);
}
