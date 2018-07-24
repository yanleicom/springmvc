package com.yanlei.dao.webdao;

import com.yanlei.model.BusinessData;

import java.util.List;

/**
 * @Author: x
 * @Date: Created in 17:02 2018/3/23
 */
public interface BusinessDataDao {
    List<BusinessData> findBusinessDatas();

    BusinessData getBusinessDataById(Integer id);

    void saveBusinessData(BusinessData businessData);

    Integer saveBusinessDatas(List<BusinessData> businessDatas);

    Integer updateBusinessData(BusinessData businessData);
}
