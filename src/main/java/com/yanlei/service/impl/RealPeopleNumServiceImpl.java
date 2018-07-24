package com.yanlei.service.impl;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
import com.yanlei.dao.RealPeopleNumDao;
import com.yanlei.model.*;
import com.yanlei.service.PeopleService;
import com.yanlei.service.RealPeopleNumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: x
 * @Date: Created in 15:52 2018/3/2
 */
@Service
@Transactional
public class RealPeopleNumServiceImpl implements RealPeopleNumService {

    @Autowired
    private RealPeopleNumDao realPeopleNumDao;
    @Autowired
    private PeopleService peopleService;

    private void delAll(){
        realPeopleNumDao.delPeopleAgeAll();
        realPeopleNumDao.delPeopleNationAll();
        realPeopleNumDao.delPeoplePoliticalAll();
        realPeopleNumDao.delPeopleSexAll();
        realPeopleNumDao.delPeopleWorkAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public void dealBatchAdd(List<PeopleSex> peopleSexes, List<PeopleNation> peopleNations, List<PeopleAge> peopleAges,
                             List<PeoplePolitical> peoplePoliticals, List<PeopleWork> peopleWorks){
        delAll();
        realPeopleNumDao.addPeopleAgeList(peopleAges);
        peopleService.addAge(peopleAges);
        realPeopleNumDao.addPeopleNationList(peopleNations);
        peopleService.addNation(peopleNations);
        realPeopleNumDao.addPeopleSexList(peopleSexes);
        peopleService.addSex(peopleSexes);
        realPeopleNumDao.addPeoplePoliticalList(peoplePoliticals);
        peopleService.addPolitical(peoplePoliticals);
        realPeopleNumDao.addPeopleWorkList(peopleWorks);
        peopleService.addWork(peopleWorks);
    }
}
