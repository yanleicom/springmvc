package com.yanlei.service.showDataImpl;

import com.besjon.pojo.JingJiPage;
import com.besjon.pojo.JingJiPageDlists;
import com.besjon.pojo.JsonToJingji;
import com.yanlei.dao.showData.JingJiDao;
import com.yanlei.model.GovernmentDepartment;
import com.yanlei.service.showData.JingJiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: x
 * @Date: Created in 15:25 2018/5/7
 */
@Service
public class JingJiServiceImpl implements JingJiService {
    @Autowired
    private JingJiDao jingJiDao;

    @Override
    public List<GovernmentDepartment> findPageOne(String pageOne) {
       return jingJiDao.findPageOne(pageOne);

    }

    @Override
    public JsonToJingji findPageTwo(String pageTwo) {
        //根据pageTwo查询 level2分组 查询出八个街道名称
        List<String> list = new ArrayList<String>();
        //根据名称查询出街道信息
        List<String> level2Name = jingJiDao.findPageLevel(pageTwo);

        List<JingJiPageDlists> jingJiPageDlists =new ArrayList<JingJiPageDlists>();

        JsonToJingji jsonToJingji = new JsonToJingji();
        String name = null;

        if (level2Name!=null && level2Name.size()>0) {
            for (String s : level2Name) {
                List<JingJiPage> jingJiPages =new ArrayList<JingJiPage>();
                list.add(s);
                List<GovernmentDepartment> governmentDepartmentList = jingJiDao.findLevel2Name(s);
                //System.out.println("开始打印=====>>>>" + governmentDepartmentList);
                name = governmentDepartmentList.get(0).getLevel();
                JingJiPageDlists jiPageDlists = new JingJiPageDlists();
                for (int i = 0; i < governmentDepartmentList.size(); i++) {
                    JingJiPage jiPage = new JingJiPage(governmentDepartmentList.get(i).getId(),
                            governmentDepartmentList.get(i).getName(),governmentDepartmentList.get(i).getIntValue(),
                            governmentDepartmentList.get(i).getLevel(),governmentDepartmentList.get(i).getLevel2(),
                            governmentDepartmentList.get(i).getUnit(),governmentDepartmentList.get(i).getSource());
                    jingJiPages.add(jiPage);
                    jiPageDlists.setJingJiPages(jingJiPages);
                }
                jingJiPageDlists.add(jiPageDlists);
            }
            jsonToJingji.setLists(list);
            jsonToJingji.setDlists(jingJiPageDlists);
            jsonToJingji.setHead(name);

        }
        //return jingJiDao.findPageTwo(pageTwo);
        return jsonToJingji;
    }

    @Override
    public JsonToJingji findPageThree(String pageThree) {
        //根据pageThree查询 level2分组 查询出level2类别名称
        List<String> level2Name = jingJiDao.findPageLevel(pageThree);
        //level2类名存放
        //List<String> list = new ArrayList<String>();

        JsonToJingji jsonToJingji = new JsonToJingji();
        List<List<Long>> arr =new ArrayList<List<Long>>();

        if (level2Name!= null && level2Name.size()>0){

            jsonToJingji.setList(level2Name);

            for (String s : level2Name) {
                List<Long> intValue = new ArrayList<Long>();
                //list.add(s);
                //数据库name的存放
                List<String> name = new ArrayList<String>();
                //根据pageThree,level2查询
                List<GovernmentDepartment> governmentDepartmentList = jingJiDao.findNameAndIntValue(pageThree,s);

                if (governmentDepartmentList!=null && governmentDepartmentList.size()>0){
                    for (int i = 0; i < governmentDepartmentList.size(); i++) {
                        name.add(governmentDepartmentList.get(i).getName());
                        intValue.add(governmentDepartmentList.get(i).getIntValue());
                    }
                }
                jsonToJingji.setName(name);
                arr.add(intValue);
            }
            jsonToJingji.setIntValue(arr);
        }


        jsonToJingji.setHead(pageThree);

        return jsonToJingji;
    }
}
