package com.yanlei.service.showData;

import com.besjon.po.JsonRootBean;
import com.yanlei.model.GovernmentDepartment;

import java.util.Collection;
import java.util.List;

/**
 * @Author: x
 * @Date: Created in 11:35 2018/4/4
 */


public interface PoliticsService {

    List<GovernmentDepartment> findOne(Collection<String> values);

    //List<GovernmentDepartment> findRight(List<String> levelNmae);

    JsonRootBean findLevel3(String eightName);
}
