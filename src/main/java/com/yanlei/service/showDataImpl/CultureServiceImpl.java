package com.yanlei.service.showDataImpl;

import com.besjon.pojo.*;
import com.yanlei.dao.showData.CultureDao;
import com.yanlei.model.GovernmentDepartment;
import com.yanlei.service.showData.CultureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: x
 * @Date: Created in 11:22 2018/4/17
 */
@Service
public class CultureServiceImpl implements CultureService {


    @Autowired
    private CultureDao cultureDao;


    @Override
    public JsonRootBean3 findThreeLeft(String elevenName, String twelveName) {
        //图三总服务事件
        GovernmentDepartment governmentDepartment = cultureDao.findElevenName(elevenName);

        List<String> lists = new ArrayList<String>();

        List<Dlists2> dlistsList = new ArrayList<Dlists2>();

        JsonRootBean3 jsonRootBean = new JsonRootBean3();
        //查询第三类别 年月日
        List<String> levelName = cultureDao.findLevel3(twelveName);
        for (String s : levelName) {
            lists.add(s);
            List<GovernmentDepartment> governmentDepartmentList = cultureDao.findLeft(s);
            List<Fenlist2> fenlists = new ArrayList<Fenlist2>();
            Dlists2 dlists = new Dlists2();
            if (governmentDepartmentList != null && governmentDepartmentList.size() > 0) {
                for (int i = 0; i < governmentDepartmentList.size(); i++) {
                    Fenlist2 fenlist = new Fenlist2(governmentDepartmentList.get(i).getId(), governmentDepartmentList.get(i).getName()
                            , governmentDepartmentList.get(i).getDoubleValue(), governmentDepartmentList.get(i).getIntValue());
                    fenlists.add(fenlist);

                    dlists.setFenlist(fenlists);

                }
            }
            dlistsList.add(dlists);

        }
        jsonRootBean.setLists(lists);
        jsonRootBean.setDlists(dlistsList);
        jsonRootBean.setTotal(governmentDepartment.getIntValue());
        jsonRootBean.setName(governmentDepartment.getName());

        return jsonRootBean;
    }

    @Override
    public JsonRootBean4 findThreeRight(String thirteenName, String fourteenName, String fifteenName) {

        List<GovernmentDepartment> thirteenNameList = cultureDao.findRight(thirteenName);//企业服务事件
        List<GovernmentDepartment> fourteenNameList = cultureDao.findRight(fourteenName);//分类型事件数
        List<GovernmentDepartment> fifteenNameList = cultureDao.findRight(fifteenName);//分部门事件数
        JsonRootBean4 jsonRootBean = new JsonRootBean4();
        List<String> arr = new ArrayList<String>();
        List<Long> array = new ArrayList<Long>();
        List<Dlists4> dlists4s = new ArrayList<Dlists4>();
        Dlists4 dlists = new Dlists4();
        Dlists4 dlists1 = new Dlists4();
        for (int i = 0; i < fifteenNameList.size(); i++) {
            arr.add(fifteenNameList.get(i).getName());
            array.add(fifteenNameList.get(i).getIntValue());
        }
        List<Fenlist4> fenlist = new ArrayList<Fenlist4>();

        for (int i = 0; i < thirteenNameList.size(); i++) {
            Fenlist4 fenlists = new Fenlist4(thirteenNameList.get(i).getId(),
                    thirteenNameList.get(i).getName(), thirteenNameList.get(i).getDoubleValue());
            fenlist.add(fenlists);
            dlists.setFenlist(fenlist);
        }
        List<Fenlist4> fenlist1 = new ArrayList<Fenlist4>();
        for (int i = 0; i < fourteenNameList.size(); i++) {
            Fenlist4 fenlists = new Fenlist4(fourteenNameList.get(i).getId(),
                    fourteenNameList.get(i).getName(), fourteenNameList.get(i).getDoubleValue());
            fenlist1.add(fenlists);
            dlists1.setFenlist(fenlist1);
        }
        dlists4s.add(dlists);
        dlists4s.add(dlists1);
        jsonRootBean.setLists(arr);
        jsonRootBean.setLongList(array);
        jsonRootBean.setDlists(dlists4s);
        jsonRootBean.setThirteenName(thirteenName);
        jsonRootBean.setFourteenName(fourteenName);
        jsonRootBean.setFifteenName(fifteenName);
        return jsonRootBean;
    }
}
