package com.yanlei.service.impl;

import com.yanlei.dao.QysjDao;
import com.yanlei.model.Qysj;
import com.yanlei.service.QysjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: x
 * @Date: Created in 16:31 2018/3/5
 */
@Service
public class QysjServiceImpl implements QysjService {
    @Autowired
    private QysjDao qysjDao;

    private void delQysjAll(){
        qysjDao.delDayAll();
        qysjDao.delMonthAll();
        qysjDao.delYearAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public void dealBathAdd(List<Qysj> qysjDay, List<Qysj> qysjMonth, List<Qysj> qysjYear) {
        delQysjAll();
        qysjDao.addQysjDayList(qysjDay);
        qysjDao.addQysjMonthList(qysjMonth);
        qysjDao.addQysjYearList(qysjYear);
    }
}
