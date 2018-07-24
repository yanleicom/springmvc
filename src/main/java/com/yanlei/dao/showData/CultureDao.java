package com.yanlei.dao.showData;

import com.yanlei.model.GovernmentDepartment;

import java.util.List;

/**
 * @Author: x
 * @Date: Created in 11:42 2018/4/17
 */
public interface CultureDao {
    GovernmentDepartment findElevenName(String elevenName);

    List<String> findLevel3(String twelveName);

    List<GovernmentDepartment> findLeft(String s);

    List<GovernmentDepartment> findRight(String thirteenName);
}
