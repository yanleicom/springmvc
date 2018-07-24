package com.yanlei.service;

import com.yanlei.model.*;

import java.util.List;

/**
 * @Author: x
 * @Date: Created in 15:51 2018/3/2
 */
public interface RealPeopleNumService {

    void dealBatchAdd(List<PeopleSex> peopleSexes, List<PeopleNation> peopleNations, List<PeopleAge> peopleAges,
                     List<PeoplePolitical> peoplePoliticals, List<PeopleWork> peopleWorks);

}
