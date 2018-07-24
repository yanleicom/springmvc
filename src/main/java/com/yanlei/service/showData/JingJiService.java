package com.yanlei.service.showData;

import com.besjon.pojo.JsonToJingji;
import com.yanlei.model.GovernmentDepartment;

import java.util.List;

/**
 * @Author: x
 * @Date: Created in 15:25 2018/5/7
 */
public interface JingJiService {
    List<GovernmentDepartment> findPageOne(String pageOne);

    JsonToJingji findPageTwo(String pageTwo);

    JsonToJingji findPageThree(String pageThree);
}
