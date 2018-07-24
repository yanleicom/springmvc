package com.yanlei.service.modifyConfigService;

import com.yanlei.model.GovernmentDepartment;
import com.yanlei.util.Result;

import java.util.List; /**
 * @Author: x
 * @Date: Created in 9:42 2018/4/18
 */
public interface ModifyPoliticsService {
    Result updateDepartment(List<GovernmentDepartment> departments);

    Result delDepartmentOne(Integer id);
}
