package com.yanlei.dao.showData;

import com.yanlei.model.luxi.EventModel;
import com.yanlei.model.shehui.BuildingArea;
import com.yanlei.model.shehui.Enterprise;
import com.yanlei.model.shehui.People;

import java.util.List;

/**
 * @Author: x
 * @Date: Created in 15:16 2018/5/17
 */
public interface SheHuiDao {
    Integer findPeopleSum();

    Integer findManPeople(String man);

    List<People> findJieDaoPeople();

    List<String> findZhlx();

    Integer findFenZhlx(String s);

    Integer findQita();

    List<String> countyNames();

    List<People> findQuYuPeople(String countyName);

    List<Enterprise> findEnterprise(String year);

    List<EventModel> findMonthsAndCount();

    List<EventModel> findWorkClass(String months);

    List<People> findBuilding();


    List<BuildingArea> showBuildingArea();

}
