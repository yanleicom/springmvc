package com.yanlei.dao;

import com.yanlei.model.*;

import java.util.List;

/**
 * @Author: x
 * @Date: Created in 9:12 2018/3/5
 */
public interface LegalPersonDao {

    int delSSRegionAll();

    int delSSIndustryAll();

    int delSSMoneyAll();

    int delSSWorkmanAll();

    int delSSRevenueAll();

    int addSSRegionList(List<SSRegion> ssRegionList);

    int addSSIndustryList(List<SSIndustry> ssIndustryList);

    int addSSMoneyList(List<SSMoney> ssMoneyList);

    int addSSWorkmanList(List<SSWorkman> ssWorkmanList);

    int addSSRevenueList(List<SSRevenue> ssRegionList);




}
