package com.yanlei.service.showDataImpl;

import com.besjon.pojo.*;
import com.yanlei.dao.showData.SocietyDao;
import com.yanlei.model.GovernmentDepartment;
import com.yanlei.service.showData.SocietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: x
 * @Date: Created in 16:31 2018/4/16
 */
@Service
public class SocietyServiceImpl implements SocietyService {

    @Autowired
    private SocietyDao societyDao;

    @Override
    public JsonRootBean1 findLevel3(String nineName) {
        List<String> lists = new ArrayList<String>();

        List<Dlists1> dlistsList =new ArrayList<Dlists1>();

        List<Colnum> colnumList =new ArrayList<Colnum>();

        JsonRootBean1 jsonRootBean = new JsonRootBean1();

        List<String> levelName = societyDao.findLevel3(nineName);
        for (String s : levelName) {
            lists.add(s);
            List<GovernmentDepartment> governmentDepartmentList = societyDao.findLeft(s);
            List<Fenlist1> fenlists= new ArrayList<Fenlist1>();
            Dlists1 dlists = new Dlists1();
            List<FenData> fenDatas =new ArrayList<FenData>();
            Colnum colnum = new Colnum();
            if (governmentDepartmentList != null && governmentDepartmentList.size() > 0) {
                for (int i = 0; i < governmentDepartmentList.size(); i++) {
                    Fenlist1 fenlist1 = new Fenlist1(governmentDepartmentList.get(i).getId(),
                            governmentDepartmentList.get(i).getName(),governmentDepartmentList.get(i).getDoubleValue(),
                            governmentDepartmentList.get(i).getIntValue(),governmentDepartmentList.get(i).getUnit(),
                            governmentDepartmentList.get(i).getSource());
                    fenlists.add(fenlist1);

                    dlists.setFenlist(fenlists);

                    FenData fenData = new FenData(governmentDepartmentList.get(i).getName(),governmentDepartmentList.get(i).getIntValue());
                    fenDatas.add(fenData);
                    colnum.setFenData(fenDatas);

                }
            }
            dlistsList.add(dlists);
            colnumList.add(colnum);
        }

        jsonRootBean.setDlists(dlistsList);
        jsonRootBean.setLists(lists);
        jsonRootBean.setColnums(colnumList);
        jsonRootBean.setLists1(nineName);
        return jsonRootBean;

    }

    @Override
    public JsonRootBean1 findLevel3Right(String tenName) {
        List<String> lists = new ArrayList<String>();

        List<Dlists1> dlistsList =new ArrayList<Dlists1>();

        JsonRootBean1 jsonRootBean = new JsonRootBean1();

        List<String> levelName = societyDao.findLevel3(tenName);
        for (String s : levelName) {
            lists.add(s);
            List<GovernmentDepartment> governmentDepartmentList = societyDao.findLeft(s);
            List<Fenlist1> fenlists= new ArrayList<Fenlist1>();
            Dlists1 dlists = new Dlists1();
            if (governmentDepartmentList != null && governmentDepartmentList.size() > 0) {
                for (int i = 0; i < governmentDepartmentList.size(); i++) {
                    Fenlist1 fenlist1 = new Fenlist1(governmentDepartmentList.get(i).getId(),
                            governmentDepartmentList.get(i).getName(),governmentDepartmentList.get(i).getDoubleValue(),
                            governmentDepartmentList.get(i).getIntValue(),governmentDepartmentList.get(i).getUnit(),
                            governmentDepartmentList.get(i).getSource());
                    fenlists.add(fenlist1);

                    dlists.setFenlist(fenlists);

                }
            }
            dlistsList.add(dlists);
        }

        jsonRootBean.setDlists(dlistsList);
        jsonRootBean.setLists(lists);
        return jsonRootBean;
    }
}
