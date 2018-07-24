package com.yanlei.dao;

import com.yanlei.model.BusinessData;
import com.yanlei.model.GovernmentDepartment;

import java.util.Date;
import java.util.List;

/**
 * @Author: x
 * @Date: Created in 15:50 2018/3/28
 */
public interface QuartzDao {
    List<BusinessData> findBusinessData();

    int saveGovernmentDepartment(BusinessData businessData);

    List<GovernmentDepartment> findDepartmentIdAndAcquisitiontime();

    List<BusinessData> findIdAndTimeList(Integer acquisitiontime, Date departmentid, String dataname);

    int findCount();

    List<BusinessData> findBusinessDataMaxTime();

    void updateGovernmentDepartment(Integer id);

    List<BusinessData> findGroupByName();

    void delAll();

    List<BusinessData> findBusinessDataforOne(Integer value);

    List<GovernmentDepartment> findAreaIDforOne();

    int saveGovernmentDepartments(List<BusinessData> businessDataList);

    void delAllTwo();

    int saveGovernmentSociety(List<BusinessData> businessDataList);

    void delAllThree();

    int saveGovernmentCulture(List<BusinessData> businessDataList);

    void delAllFour();

    int saveGovernmentEconomic(List<BusinessData> businessDataList);
}
