package com.yanlei.service.showData;

import com.besjon.pojo.JsonRootBean3;
import com.besjon.pojo.JsonRootBean4;

/**
 * @Author: x
 * @Date: Created in 11:21 2018/4/17
 */
public interface CultureService {
    JsonRootBean3 findThreeLeft(String elevenName, String twelveName);

    JsonRootBean4 findThreeRight(String thirteenName, String fourteenName, String fifteenName);
}
