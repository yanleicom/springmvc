package com.yanlei.dao.showData;

import com.yanlei.model.GovernmentDepartment;

import java.util.List;

/**
 * @Author: x
 * @Date: Created in 17:40 2018/4/17
 */
public interface EconomicDao {

    List<String> findLevel3(String sixteenName);

    List<GovernmentDepartment> findLeft(String s);

    GovernmentDepartment findElevenName(String seventeenName);

    List<GovernmentDepartment> findEighteenName(String eighteenName);

    List<GovernmentDepartment> findFourRight(String s);
}
