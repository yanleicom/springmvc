package com.yanlei.service.showData;

import com.yanlei.model.GovernmentDepartment;

import java.util.List;

/**
 * @Author: x
 * @Date: Created in 16:56 2018/5/9
 */
public interface PageService {
    String pageOne(String pageOne);

    List<GovernmentDepartment> pageGongWen(String pageGongWen);

    List<List<GovernmentDepartment>> pageDepartment(String pageGongWen);

    String showPageMonthSum(String pageGongWen);

    String showPageMonth(String pageGongWen);
}
