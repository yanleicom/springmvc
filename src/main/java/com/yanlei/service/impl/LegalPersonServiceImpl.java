package com.yanlei.service.impl;

import com.yanlei.dao.LegalPersonDao;
import com.yanlei.dao.PeopleDao;
import com.yanlei.model.*;
import com.yanlei.service.LegalPersonService;
import com.yanlei.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: x
 * @Date: Created in 10:43 2018/3/5
 */
@Service
public class LegalPersonServiceImpl implements LegalPersonService {

    @Autowired
    private LegalPersonDao legalPersonDao;
    @Autowired
    private PeopleService peopleService;

    /**
     * 批量删除数据
     */

    private void  delAll(){
        legalPersonDao.delSSIndustryAll();
        legalPersonDao.delSSMoneyAll();
        legalPersonDao.delSSRegionAll();
        legalPersonDao.delSSRevenueAll();
        legalPersonDao.delSSWorkmanAll();
    }


    /**
     *  批量添加数据包括记录数据
     * @param ssRegions
     * @param ssWorkmens
     * @param ssIndustries
     * @param ssMonies
     * @param ssRevenues
     */
    @Transactional(rollbackFor = Exception.class)
    public void dealBathAdd(List<SSRegion> ssRegions, List<SSWorkman> ssWorkmens,
                            List<SSIndustry> ssIndustries, List<SSMoney> ssMonies, List<SSRevenue> ssRevenues) {
        delAll();
        legalPersonDao.addSSWorkmanList(ssWorkmens);
        peopleService.addSSWorkmens(ssWorkmens);

        legalPersonDao.addSSIndustryList(ssIndustries);
        peopleService.addSSIndustries(ssIndustries);

        legalPersonDao.addSSMoneyList(ssMonies);
        peopleService.addSSMonies(ssMonies);

        legalPersonDao.addSSRegionList(ssRegions);
        peopleService.addSSRegions(ssRegions);

        legalPersonDao.addSSRevenueList(ssRevenues);
        peopleService.addSSRevenues(ssRevenues);
    }
}
