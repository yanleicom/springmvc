package com.yanlei.service.showDataImpl;

import com.besjon.pojo.*;
import com.yanlei.dao.showData.EconomicDao;
import com.yanlei.model.GovernmentDepartment;
import com.yanlei.service.showData.EconomicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: x
 * @Date: Created in 17:36 2018/4/17
 */
@Service
public class EconomicServiceImpl implements EconomicService {

    @Autowired
    private EconomicDao economicDao;

    @Override
    public JsonRootBean3 findFourLeft(String sixteenName) {
        List<String> lists = new ArrayList<String>();

        List<Dlists2> dlistsList = new ArrayList<Dlists2>();

        JsonRootBean3 jsonRootBean = new JsonRootBean3();

        List<String> levelName = economicDao.findLevel3(sixteenName);
        for (String s : levelName) {
            lists.add(s);
            List<GovernmentDepartment> governmentDepartmentList = economicDao.findLeft(s);
            List<Fenlist2> fenlists = new ArrayList<Fenlist2>();
            Dlists2 dlists = new Dlists2();
            if (governmentDepartmentList != null && governmentDepartmentList.size() > 0) {
                for (int i = 0; i < governmentDepartmentList.size(); i++) {
                    Fenlist2 fenlist = new Fenlist2(governmentDepartmentList.get(i).getId()
                            ,governmentDepartmentList.get(i).getName(), governmentDepartmentList.get(i).getDoubleValue()
                            ,governmentDepartmentList.get(i).getIntValue());
                    fenlists.add(fenlist);
                    dlists.setFenlist(fenlists);

                }
            }
            dlistsList.add(dlists);
        }

        jsonRootBean.setLists(lists);
        jsonRootBean.setDlists(dlistsList);
        jsonRootBean.setName(sixteenName);
        return jsonRootBean;
    }

    @Override
    public JsonRootBean5 findFourMiddle(String seventeenName, String eighteenName) {

        GovernmentDepartment governmentDepartment = economicDao.findElevenName(seventeenName);

        List<GovernmentDepartment> governmentDepartmentList = economicDao.findEighteenName(eighteenName);

        List<Fenlist5> fenlist = new ArrayList<Fenlist5>();

        JsonRootBean5 jsonRootBean = new JsonRootBean5();
        if (governmentDepartmentList != null && governmentDepartmentList.size() > 0) {
            for (int i = 0; i < governmentDepartmentList.size(); i++) {
                Fenlist5 fenlist5 = new Fenlist5(governmentDepartmentList.get(i).getId()
                        , governmentDepartmentList.get(i).getName(), governmentDepartmentList.get(i).getIntValue());
                fenlist.add(fenlist5);
            }
        }
        jsonRootBean.setFenlist5s(fenlist);
        jsonRootBean.setColumn5(seventeenName);
        jsonRootBean.setColumn6(governmentDepartment.getIntValue());
        jsonRootBean.setColumn7(eighteenName);
        return jsonRootBean;
    }

    @Override
    public JsonRootBean findFourRight(String nineteenName) {
        List<String> lists = new ArrayList<String>();//分装第三级类别 level3

        List<Dlists> dlistsList = new ArrayList<Dlists>();

        JsonRootBean jsonRootBean = new JsonRootBean();

        List<String> levelName = economicDao.findLevel3(nineteenName);
        for (String s : levelName) {
            lists.add(s);//添加
            List<GovernmentDepartment> governmentDepartmentList = economicDao.findFourRight(s); //查询三级类别参数 level3
            List<Fenlist> fenlists = new ArrayList<Fenlist>();
            Dlists dlists = new Dlists();
            if (governmentDepartmentList != null && governmentDepartmentList.size() > 0) {
                for (int i = 0; i < governmentDepartmentList.size(); i++) {
                    Fenlist fenlist = new Fenlist(governmentDepartmentList.get(i).getId(), governmentDepartmentList.get(i).getName(), governmentDepartmentList.get(i).getIntValue());
                    fenlists.add(fenlist);

                    dlists.setFenlist(fenlists);
                }
            }
            dlistsList.add(dlists);
        }

        jsonRootBean.setDlists(dlistsList);
        jsonRootBean.setLists(lists);
        jsonRootBean.setColumn1(nineteenName);
        return jsonRootBean;
    }
}
