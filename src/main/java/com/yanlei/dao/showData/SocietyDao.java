package com.yanlei.dao.showData;

import com.yanlei.model.GovernmentDepartment;

import java.util.List;

/**
 * @Author: x
 * @Date: Created in 16:35 2018/4/16
 */
public interface SocietyDao {
    List<String> findLevel3(String nineName);

    List<GovernmentDepartment> findLeft(String s);
}
