package com.yanlei.service.impl;

import com.yanlei.dao.ZdpycYearDao;
import com.yanlei.model.ZdpycYear;
import com.yanlei.service.ZdpycYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ZdpycYearServiceImpl implements ZdpycYearService {

    @Autowired
    private ZdpycYearDao zdpycYearDao;

    /**
     * 批量删除这三张表的数据
     */
    private void delAll(){
        zdpycYearDao.delZdpycYearAll();
        zdpycYearDao.delZdpycMonthAll();
        zdpycYearDao.delZdpycDayAll();
    }

    /**
     * 批量将excel中的数据分别添加到这三张表中去
     * @param zdpycYears
     * @param zdpycMonths
     * @param zdpycDays
     */
    @Transactional(rollbackFor = Exception.class)
    public void dealBathAdd(List<ZdpycYear> zdpycYears, List<ZdpycYear> zdpycMonths,
                            List<ZdpycYear> zdpycDays) {
        delAll();
        zdpycYearDao.addZdpycYearList(zdpycYears);
        zdpycYearDao.addZdpycMonthList(zdpycMonths);
        zdpycYearDao.addZdpycDayList(zdpycDays);
    }
}
