package com.yanlei.dao;

import com.yanlei.model.ZdpycYear;

import java.util.List;

public interface ZdpycYearDao {

    void delZdpycYearAll();

    void delZdpycMonthAll();

    void delZdpycDayAll();

    void addZdpycYearList(List<ZdpycYear> zdpycYears);

    void addZdpycMonthList(List<ZdpycYear> zdpycMonths);

    void addZdpycDayList(List<ZdpycYear> zdpycDays);


}
