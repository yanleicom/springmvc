package com.yanlei.service.showData;

import com.besjon.pojo.JsonRootBean;
import com.besjon.pojo.JsonRootBean3;
import com.besjon.pojo.JsonRootBean5;

/**
 * @Author: x
 * @Date: Created in 17:36 2018/4/17
 */
public interface EconomicService {
    JsonRootBean3 findFourLeft(String sixteenName);

    JsonRootBean5 findFourMiddle(String seventeenName, String eighteenName);

    JsonRootBean findFourRight(String nineteenName);

}
