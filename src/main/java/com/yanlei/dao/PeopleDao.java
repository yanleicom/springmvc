package com.yanlei.dao;

import com.yanlei.model.*;

import java.util.List;

/**
 * 2 * @Author: xiayuanlei
 * 3 * @Date: 2018/1/22 10:48
 * 4
 */
public interface PeopleDao {
    List<PeopleSex> findPeopleSex();

    List<PeopleNation> findPeopleNation();

    List<PeopleAge> findPeopleAge();

    List<PeoplePolitical> findPeoplePolitics();

    List<PeopleWork> findPeopleWork();

    List<SSRegion> findRegion();

    List<SSIndustry> findIndustry();

    List<SSRevenue> findRevenue();

    List<SSWorkman> findWorkman();

    List<SSMoney> findMoney();

    Sssj findSssj();

    int updateSssj(Sssj sssj);


    /**
    * @Author: x
    * @Date: Created in 19:04 2018/2/8
     * 三实数据的添加和修改
    */

    int addSex(PeopleSex peopleSex);

    int addNation(PeopleNation peopleNation);

    int addAge(PeopleAge peopleAge);

    int addPolitical(PeoplePolitical peoplePolitical);

    int addWork(PeopleWork peopleWork);



    /**
    * @Author: x
    * @Date: Created in 14:05 2018/2/9
     * 三实数据图二可配置修改
    */

    int addSSRegions(SSRegion ssRegion);

    int addSSIndustries(SSIndustry ssIndustry);

    int addSSRevenues(SSRevenue ssRevenue);

    int addSSWorkmens(SSWorkman ssWorkman);

    int addSSMonies(SSMoney ssMoney);

/**
* @Author: x
* @Date: Created in 10:56 2018/2/23
 * 第三个图可配置页面
*/
    Sgpt findSgpt();

    List<Qysj> findQysjYear();

    List<Qysj> findQysjMonth();

    List<Qysj> findQysjDay();

    List<Flxsj> findFlxsj();



}
