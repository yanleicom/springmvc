package com.yanlei.service;

import com.yanlei.model.BusinessData;
import com.yanlei.util.Result;

import java.util.List;

/**
 * @Author: x
 * @Date: Created in 15:45 2018/3/28
 */
public interface QuartzService {


   // Result findBusinessData();

    Result findDepartmentIdAndAcquisitiontime();

    Result saveGovernmentSociety();

    Result saveGovernmentCulture();

    Result saveGovernmentEconomic();

}
