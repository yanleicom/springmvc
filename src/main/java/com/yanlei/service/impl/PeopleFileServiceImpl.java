package com.yanlei.service.impl;

import com.yanlei.dao.PeopleFileDao;
import com.yanlei.model.*;
import com.yanlei.service.PeopleFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PeopleFileServiceImpl implements PeopleFileService {

    @Autowired
    private PeopleFileDao PeopelFileDao;

    public PeopleFile findPeopleFileList() {
        return PeopelFileDao.findPeopleFileList();

    }

    public List<Qjw> findQjwList() {
        return PeopelFileDao.findQjwList();
    }

    public List<Qwb> findQwbList() {
        return PeopelFileDao.findQwbList();
    }

    public List<Rlb> findRlbList() {
        return PeopelFileDao.findRlbList();

    }

    public List<Zfb> findZfbList() {
        return PeopelFileDao.findZfbList();

    }

    public List<Zx> findZxList() {
        return PeopelFileDao.findZxList();
    }

    public int updatePeopleFile(PeopleFile peopleFile) {

        int num = PeopelFileDao.updatePeopleFile(peopleFile);
        if (num > 0) {
            peopleFile.setUpdatetime(new Date());
            PeopelFileDao.insertPeopleFile(peopleFile);
            return num;
        } else {
            return -1;
        }
    }


    public Fbm findFbm() {
        return PeopelFileDao.findFbm();
    }

    public int addQjw(List<Qjw> qjws) {
        int num = 0;
        for (Qjw qjw : qjws) {
            int i = PeopelFileDao.addQjw(qjw);
            num+=i;
        }
        /*if (num>0){
            for (Qjw qjw : qjws) {
                PeopelFileDao.updateQjw(qjw);
            }
        }*/
        return num;
    }

    public int addQwb(List<Qwb> qwbs) {
        int num = 0;
        for (Qwb qwb : qwbs) {
            int i = PeopelFileDao.addQwb(qwb);
            num+=i;
        }
        /*if (num>0){
            for (Qwb qwb : qwbs) {
                PeopelFileDao.updateQwb(qwb);
            }
        }*/
        return num;
    }

    public int addZfb(List<Zfb> zfbs) {
        int num = 0;
        for (Zfb zfb : zfbs) {
            int i = PeopelFileDao.addZfb(zfb);
            num+=i;
        }
        /*if (num>0){
            for (Zfb zfb : zfbs) {
                PeopelFileDao.updateZfb(zfb);
            }
        }*/
        return num;
    }



    public int addRlb(List<Rlb> rlbs) {
        int num = 0;
        for (Rlb rlb : rlbs) {
            int i = PeopelFileDao.addRlb(rlb);
            num+=i;
        }
        /*if (num>0){
            for (Rlb rlb : rlbs) {
                PeopelFileDao.updateRlb(rlb);
            }
        }*/
        return num;
    }

    public int addZx(List<Zx> zxs) {
        int num = 0;
        for (Zx zx : zxs) {
            int i = PeopelFileDao.addZx(zx);
            num+=i;
        }
       /* if (num>0){
            for (Zx zx : zxs) {
                PeopelFileDao.updateZx(zx);
            }
        }*/
        return num;
    }

    public int updateFbm(Fbm fbm1) {
        return PeopelFileDao.updateFbm(fbm1);
    }

    public List<Qjw> findQjwLists() {
        return PeopelFileDao.findQjwLists();
    }


    /**
    * @Author: x
    * @Date: Created in 15:24 2018/2/23
     * 第三个图excel修改
    */
    public int updateSgpt(Sgpt sgpt1) {
        return PeopelFileDao.updateSgpt(sgpt1);
    }

    public int updateQysjYear(List<Qysj> qysjYear) {
        int num = 0;
        for (Qysj qysj : qysjYear) {
            int i =  PeopelFileDao.updateQysjYear(qysj);
            num+=i;
        }
        return num;
    }

    public int updateQysjMonth(List<Qysj> qysjMonth) {
        int num = 0;
        for (Qysj qysj : qysjMonth) {
            int i =  PeopelFileDao.updateQysjMonth(qysj);
            num+=i;
        }
        return num;
    }

    public int updateQysjDay(List<Qysj> qysjDay) {
        int num = 0;
        for (Qysj qysj : qysjDay) {
            int i =  PeopelFileDao.updateQysjDay(qysj);
            num+=i;
        }
        return num;
    }

    public int updateFlxsj(Flxsj flxsj) {
        return PeopelFileDao.updateFlxsj(flxsj);
    }

    public int deleteFlxsj() {
        return PeopelFileDao.deleteFlxsj();
    }

    public List<Fbmsj> findFbmsj() {
        return PeopelFileDao.findFbmsj();

    }

    public int deleteFbmsj() {
        return PeopelFileDao.deleteFbmsj();
    }



    public int addFbmsj(Fbmsj fbmsj) {
        return PeopelFileDao.addFbmsj(fbmsj);
    }

    public void delQJWAll() {
        PeopelFileDao.delQJWAll();
    }

    public void delZFBAll() {
        PeopelFileDao.delZFBAll();
    }

    public void delQWBAll() {
        PeopelFileDao.delQWBAll();
    }

    public void delRLBAll() {
        PeopelFileDao.delRLBAll();
    }

    public void delZXAll() {
        PeopelFileDao.delZXAll();
    }

    /**
     * 批量添加zfb数据
     * @param zfbs
     * @return
     */
    public void addZFBlist(List<Zfb> zfbs) {
        addZfb(zfbs);
        PeopelFileDao.addZFBList(zfbs);
    }

    /**
     * 批量添加qwb数据
     * @param qwbs
     * @return
     */
    public void addQWBlist(List<Qwb> qwbs) {
        addQwb(qwbs);
        PeopelFileDao.addQWBList(qwbs);
    }

    /**
     * 批量添加qjw数据
     * @param qjws
     * @return
     */
    public void addQJWlist(List<Qjw> qjws) {
        addQjw(qjws);
       PeopelFileDao.addQJWList(qjws);
    }

    /**
     * 批量添加zfb数据
     * @param rlbs
     * @return
     */
    public void addRLBlist(List<Rlb> rlbs) {
        addRlb(rlbs);
        PeopelFileDao.addRLBList(rlbs);
    }

    /**
     * 批量添加zx数据
     * @param zxs
     */
    public void addZXlist(List<Zx> zxs){
        addZx(zxs);
        PeopelFileDao.addZXList(zxs);
    }


    public void dealBathAdd(List<Qjw> qjws, List<Zfb> zfbs, List<Rlb> rlbs, List<Zx> zxs, List<Qwb> qwbs) {
        delAll();
        addQJWlist(qjws);
        addZFBlist(zfbs);
        addRLBlist(rlbs);
        addZXlist(zxs);
        addQWBlist(qwbs);
    }

    @Override
    public Integer insertPageList(List<GovernmentDepartment> page) {
        return PeopelFileDao.insertPageList(page);

    }

    /**
     * 删除与excel相关原有的数据
     */
    private void delAll() {
        delQJWAll();
        delZFBAll();
        delQWBAll();
        delZXAll();
        delRLBAll();
    }


}
