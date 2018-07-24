package com.yanlei.dao.showData;

import com.yanlei.model.GovernmentDepartment;

import java.util.List;

/**
 * @Author: x
 * @Date: Created in 15:28 2018/5/7
 */
public interface JingJiDao {

    List<GovernmentDepartment> findPageOne(String pageOne);

    List<GovernmentDepartment> findPageTwo(String pageTwo);

    List<String> findPageLevel(String pageTwo);

    List<GovernmentDepartment> findLevel2Name(String s);

    List<GovernmentDepartment> findNameAndIntValue(String pageThree, String s);
}
