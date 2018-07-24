package com.yanlei.dao.showData;

import com.yanlei.model.GovernmentDepartment;
import com.yanlei.model.SumAndAvg;

import java.util.List;
import java.util.Map;

/**
 * @Author: x
 * @Date: Created in 11:22 2018/4/8
 */
public interface PoliticsDao {
    GovernmentDepartment findOne(String value);

    List<GovernmentDepartment> findRight(String levelNmae);

    List<String> findLevel3(String eightName);

    GovernmentDepartment findRule(String s, String s1);

    GovernmentDepartment findRules(String s);

    GovernmentDepartment findSum(GovernmentDepartment time);

    GovernmentDepartment findAvg(GovernmentDepartment time);

    GovernmentDepartment findMonthSum(SumAndAvg sumAndAvg);

    GovernmentDepartment findMonthAvg(SumAndAvg sumAndAvg);
}
