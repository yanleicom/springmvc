package com.yanlei.service;

import com.yanlei.model.*;

import java.util.List;

public interface PeopleFileService {
    PeopleFile findPeopleFileList();

    List<Qjw> findQjwList();

    List<Qwb> findQwbList();

    List<Rlb> findRlbList();

    List<Zfb> findZfbList();

    List<Zx> findZxList();

    int updatePeopleFile(PeopleFile peopleFile);

    Fbm findFbm();

    int addQjw(List<Qjw> qjws);

    int addQwb(List<Qwb> qwbs);

    int addZfb(List<Zfb> zfbs);

    int addRlb(List<Rlb> rlbs);

    int addZx(List<Zx> zxs);

    int updateFbm(Fbm fbm1);

    List<Qjw> findQjwLists();

    int updateSgpt(Sgpt sgpt1);

    int updateQysjYear(List<Qysj> qysjYear);

    int updateQysjMonth(List<Qysj> qysjMonth);

    int updateQysjDay(List<Qysj> qysjDay);

    int updateFlxsj(Flxsj flxsj);

    int deleteFlxsj();

    List<Fbmsj> findFbmsj();

    int deleteFbmsj();

    int addFbmsj(Fbmsj fbmsj);

    void delQJWAll();

    void delZFBAll();

    void delQWBAll();

    void delRLBAll();

    void delZXAll();

    void addZFBlist(List<Zfb> zfbs);

    void addQWBlist(List<Qwb> qwbs);

    void addQJWlist(List<Qjw> qjws);

    void addRLBlist(List<Rlb> rlbs);

    void addZXlist(List<Zx> zxs);

    void dealBathAdd(List<Qjw> qjws, List<Zfb> zfbs, List<Rlb> rlbs, List<Zx> zxs, List<Qwb> qwbs);

    Integer insertPageList(List<GovernmentDepartment> page);

}
