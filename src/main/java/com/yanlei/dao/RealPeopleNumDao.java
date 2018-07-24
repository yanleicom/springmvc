package com.yanlei.dao;

import com.yanlei.model.*;

import java.util.List;

/**
 * @Author: x
 * @Date: Created in 15:24 2018/3/2
 */
public interface RealPeopleNumDao {
    int delPeopleAgeAll();

    int delPeopleNationAll();

    int delPeoplePoliticalAll();

    int delPeopleSexAll();

    int delPeopleWorkAll();

    int addPeopleAgeList(List<PeopleAge> peopleAges);

    int addPeopleNationList(List<PeopleNation> peopleNations);

    int addPeoplePoliticalList(List<PeoplePolitical> peoplePoliticals);

    int addPeopleSexList(List<PeopleSex> peopleSexes);

    int addPeopleWorkList(List<PeopleWork> peopleWorks);

}
