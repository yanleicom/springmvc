package com.yanlei.dao;

import com.yanlei.model.Qysj;

import java.util.List;

/**
 * @Author: x
 * @Date: Created in 16:15 2018/3/5
 */
public interface QysjDao {

    int delYearAll();

    int delMonthAll();

    int delDayAll();

    int addQysjYearList(List<Qysj> qysjList);

    int addQysjMonthList(List<Qysj> qysjList);

    int addQysjDayList(List<Qysj> qysjList);

}
