package com.yanlei.dao.showData;

import com.yanlei.model.GovernmentDepartment;

import java.util.List;

/**
 * @Author: x
 * @Date: Created in 17:25 2018/5/9
 */
public interface PageDao {
    List<String> findName(String pageOne);

    List<GovernmentDepartment> findNameAndValue(String s, String pageOne);

    List<GovernmentDepartment> findJieDao(GovernmentDepartment governmentDepartment);

    List<GovernmentDepartment> firstFive(GovernmentDepartment governmentDepartment);

    List<GovernmentDepartment> lastFive(GovernmentDepartment governmentDepartment);

    List<GovernmentDepartment> showPageMonthSum(GovernmentDepartment governmentDepartment);

    List<GovernmentDepartment> showPageMonth(String pageGongWen, String cycle);

    Long selectCount(String s);
}
