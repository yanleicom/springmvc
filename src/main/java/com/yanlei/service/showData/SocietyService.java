package com.yanlei.service.showData;

import com.besjon.pojo.JsonRootBean1;

/**
 * @Author: x
 * @Date: Created in 16:31 2018/4/16
 */
public interface SocietyService {
    com.besjon.pojo.JsonRootBean1 findLevel3(String nineName);

    JsonRootBean1 findLevel3Right(String tenName);
}
