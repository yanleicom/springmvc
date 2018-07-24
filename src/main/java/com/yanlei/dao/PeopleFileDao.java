package com.yanlei.dao;

import com.yanlei.model.*;

import java.util.List;

public interface PeopleFileDao {
    PeopleFile findPeopleFileList();

    List<Qjw> findQjwList();

    List<Qwb> findQwbList();

    List<Rlb> findRlbList();

    List<Zfb> findZfbList();

    List<Zx> findZxList();

    int updatePeopleFile(PeopleFile peopleFile);

    int insertPeopleFile(PeopleFile peopleFile);

    int updateQjw(List<Qjw> qjw);

    //webservice测试
    int savePeopleFile(PeopleFile peopleFile);

    int deletePeopleFile(Integer id);

    int updatePeople(PeopleFile peopleFile);


    /**
     * @return
     */
    Fbm findFbm();

    int addQjw(Qjw qjw);

    void updateQjw(Qjw qjw);

    int addQwb(Qwb qwb);

    void updateQwb(Qwb qwb);

    int addZfb(Zfb zfb);

    void updateZfb(Zfb zfb);

    int addRlb(Rlb rlb);

    void updateRlb(Rlb rlb);

    int addZx(Zx zx);

    void updateZx(Zx zx);

    int updateFbm(Fbm fbm1);

    List<Qjw> findQjwLists();

    List<Fbmsj> findFbmsj();



    int updateSgpt(Sgpt sgpt1);

    int updateQysjYear(Qysj qysj);

    int updateQysjMonth(Qysj qysj);

    int updateQysjDay(Qysj qysj);

    int updateFlxsj(Flxsj flxsj);

    int deleteFlxsj();

/**
* @Author: x
* @Date: Created in 10:36 2018/3/1
 * 第三图分部门事件数
*/
    int deleteFbmsj();

    int addFbmsj(Fbmsj fbmsj);

    void delZFBAll();

    void addZFBList(List<Zfb> zfbs);

    void delQJWAll();

    void addQJWList(List<Qjw> qjws);

    void delQWBAll();

    void addQWBList(List<Qwb> qwbs);

    void delZXAll();

    void addZXList(List<Zx> zxs);

    void delRLBAll();

    void addRLBList(List<Rlb> rlbs);


    int updatePeoples(List<PeopleFile> peopleFileList);

    Integer insertPageList(List<GovernmentDepartment> page);
}
