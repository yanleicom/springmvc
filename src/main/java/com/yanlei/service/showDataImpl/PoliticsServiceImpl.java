package com.yanlei.service.showDataImpl;

import com.besjon.po.Dlists;
import com.besjon.po.Fenlist;
import com.besjon.po.JsonRootBean;
import com.yanlei.dao.showData.PoliticsDao;
import com.yanlei.model.GovernmentDepartment;
import com.yanlei.model.SumAndAvg;
import com.yanlei.service.showData.PoliticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: x
 * @Date: Created in 11:36 2018/4/4
 */
@Service
public class PoliticsServiceImpl implements PoliticsService {

    @Autowired
    private PoliticsDao politicsDao;

    @Override
    public List<GovernmentDepartment> findOne(Collection<String> values) {
        List<GovernmentDepartment> governmentDepartmentList = new ArrayList<GovernmentDepartment>();
        for (String value : values) {
            String[] split = value.split(",");
            if (split.length == 1) {
                GovernmentDepartment governmentDepartment = politicsDao.findOne(value);

                if (governmentDepartment != null) {
                    // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    //sdf.format(governmentDepartment.getAcquisitiontime());
                    governmentDepartmentList.add(governmentDepartment);
                }
            } else {
                if (split.length == 2){

                } else if (split.length == 3) { //lastTime,公文办件量,?月
                    GovernmentDepartment governmentDepartment = politicsDao.findRule(split[1], split[2]);
                    if (governmentDepartment != null) {
                        governmentDepartmentList.add(governmentDepartment);
                    }
                } else if (split.length == 4 ) {
                    GovernmentDepartment time = politicsDao.findRule(split[1],split[3]);
                    if (split[2].toString().equals("sum")){  //lastTime,公文办件量,sum,?月(全年,半年,一季)
                    //根据split[length-1]和名称(split[1])查询出统计时间
                   // GovernmentDepartment time = politicsDao.findRule(split[1],split[3]);
                    //根据名称查询统计时间小于查询得出的时间求和(sum函数)
                    GovernmentDepartment governmentDepartment = politicsDao.findSum(time);
                    if (governmentDepartment != null) {
                        governmentDepartmentList.add(governmentDepartment);
                    }
                    }else if (split[2].toString().equals("avg")){  //lastTime,公文办件量,avg,?月(全年,半年,一季)
                        //GovernmentDepartment time = politicsDao.findRule(split[1],split[3]);
                        GovernmentDepartment governmentDepartment = politicsDao.findAvg(time);
                        if (governmentDepartment != null) {
                            governmentDepartmentList.add(governmentDepartment);
                        }
                    }
                }else if (split.length == 5){ //lastTime,公文办件量,sum,?月,?月(求和几月到几月)
                    //查询第一个?月开始的时间 cycle like ?月
                    GovernmentDepartment statrTime = politicsDao.findRule(split[1],split[3]);
                    //查询第二个?月结束的时间 cycle like ?月
                    GovernmentDepartment endTime = politicsDao.findRule(split[1],split[4]);

                    SumAndAvg sumAndAvg = new SumAndAvg();
                    sumAndAvg.setName(statrTime.getName());//name split[1]
                    sumAndAvg.setStartTime(statrTime.getAcquisitionTime());//第一个采集时间
                    sumAndAvg.setEndTime(endTime.getAcquisitionTime());//第二个采集时间

                    if (split[2].toString().equals("sum")){
                        //根据名称查询大于等于开始时间小于等于结束时间的sum和
                        GovernmentDepartment governmentDepartment = politicsDao.findMonthSum(sumAndAvg);
                        if (governmentDepartment != null) {
                            governmentDepartmentList.add(governmentDepartment);
                        }
                    }else if (split[2].toString().equals("avg")){  //lastTime,公文办件量,avg,?月,?月(求和几月到几月)
                        GovernmentDepartment governmentDepartment = politicsDao.findMonthAvg(sumAndAvg);
                        if (governmentDepartment != null) {
                            governmentDepartmentList.add(governmentDepartment);
                        }
                    }
                    }
            }

        }
        return governmentDepartmentList;
    }


    @Override
    public JsonRootBean findLevel3(String eightName) {
        List<String> lists = new ArrayList<String>();//分装第三级类别 level3

        List<Dlists> dlistsList = new ArrayList<Dlists>();

        JsonRootBean jsonRootBean = new JsonRootBean();

        List<String> levelName = politicsDao.findLevel3(eightName);
        for (String s : levelName) {
            lists.add(s);//添加
            List<GovernmentDepartment> governmentDepartmentList = politicsDao.findRight(s); //查询三级类别参数 level3
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
        jsonRootBean.setColumn1(eightName);
        return jsonRootBean;
    }
}
