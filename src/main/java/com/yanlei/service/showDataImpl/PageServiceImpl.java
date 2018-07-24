package com.yanlei.service.showDataImpl;

import com.alibaba.fastjson.JSON;
import com.besjon.pojo.JsonToJingji;
import com.yanlei.dao.showData.PageDao;
import com.yanlei.model.GovernmentDepartment;
import com.yanlei.service.showData.PageService;
import com.yanlei.util.DateUtil;
import com.yanlei.util.luxi.BigDecimalUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author: x
 * @Date: Created in 16:56 2018/5/9
 */

@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private PageDao pageDao;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public String pageOne(String pageOne) {
        //redisTemplate.opsForValue().set("第二季度政务公开统计",null);
        String s1 = redisTemplate.opsForValue().get("第二季度政务公开统计");
        if (StringUtils.isNotBlank(s1)){
            return s1;
        }else {
            //分类的名称
            List<String> name = pageDao.findName(pageOne);

            JsonToJingji jsonToJingji = new JsonToJingji();
            List<List<Object>> arr = new ArrayList<List<Object>>();
            if (name.size() > 0 && name != null) {
                jsonToJingji.setName(name);
                for (String s : name) {
                    List<Object> list = new ArrayList<Object>();
                    List<GovernmentDepartment> governmentDepartmentList = pageDao.findNameAndValue(s,pageOne);
                    Long num = pageDao.selectCount(s); //总数
                    //System.out.println(num);
                    if (governmentDepartmentList != null && governmentDepartmentList.size() > 0) {
                        for (int i = 0; i < governmentDepartmentList.size(); i++) {
                            list.add(governmentDepartmentList.get(i));
                            String percent = BigDecimalUtil.getPercent(governmentDepartmentList.get(i).getIntValue(), num);
                            governmentDepartmentList.get(i).setCycle(percent);
                        }
                    }
                    arr.add(list);
                }

                jsonToJingji.setValue(arr);
                jsonToJingji.setHead(pageOne);
                redisTemplate.opsForValue().set("第二季度政务公开统计", JSON.toJSONString(jsonToJingji),1, TimeUnit.DAYS);
            }
            return JSON.toJSONString(jsonToJingji);
        }

    }

    @Override
    public List<GovernmentDepartment> pageGongWen(String pageGongWen) {
        //查询公文八个街道
        String jiedao = "街道";

        //只要2018年数据
        Date date = null;
        try {
            date = DateUtil.stringToDate("2018-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        GovernmentDepartment governmentDepartment =new GovernmentDepartment();
        governmentDepartment.setLevel(pageGongWen);
        governmentDepartment.setName(jiedao);
        governmentDepartment.setAcquisitionTime(date);
        List<GovernmentDepartment> jieDaoName = pageDao.findJieDao(governmentDepartment);

        return  jieDaoName;
    }

    @Override
    public List<List<GovernmentDepartment>> pageDepartment(String pageGongWen) {
        //只要2018年数据
        Date date = null;
        String currentYear = DateUtil.getCurrentYear();
        try {
            date = DateUtil.stringToDate(currentYear, "yyyy");
            //System.out.println(date);
        } catch (ParseException e) {
        }
        //按公文名称 时间大于18年,name分组 排序取前五 后五
        GovernmentDepartment governmentDepartment =new GovernmentDepartment();
        governmentDepartment.setLevel(pageGongWen);
        governmentDepartment.setAcquisitionTime(date);
        List<GovernmentDepartment> firstFive = pageDao.firstFive(governmentDepartment);
        List<GovernmentDepartment> lastFive = pageDao.lastFive(governmentDepartment);
        List<List<GovernmentDepartment>> list = new ArrayList<List<GovernmentDepartment>>();
        if ((firstFive!=null && firstFive.size()>0) && (lastFive!=null && lastFive.size()>0)) {
            list.add(firstFive);
            list.add(lastFive);
        }
        return list;
    }

    @Override
    public String showPageMonthSum(String pageGongWen) {
        Map<String,Object> map = new HashMap<String, Object>();
        //只要2018年数据
        Date date = null;
        String currentYear = DateUtil.getCurrentYear();
        try {
            date = DateUtil.stringToDate(currentYear, "yyyy");
            //System.out.println(date);
        } catch (ParseException e) {
        }
        GovernmentDepartment governmentDepartment =new GovernmentDepartment();
        governmentDepartment.setLevel(pageGongWen);
        governmentDepartment.setAcquisitionTime(date);
        List<GovernmentDepartment> showPageMonthSum = pageDao.showPageMonthSum(governmentDepartment);
        long sum = 0;
        if (showPageMonthSum!=null && showPageMonthSum.size()>0){
            for (int i = 0; i < showPageMonthSum.size(); i++) {
                Long intValue = showPageMonthSum.get(i).getIntValue();
                sum+=intValue;
            }
        }
        map.put("公文总数",sum);
        map.put("各月份公文数",showPageMonthSum);
        return JSON.toJSONString(map);
    }

    @Override
    public String showPageMonth(String pageGongWen) {
        Map<String,Object> map = new HashMap<String, Object>();
        String cycle = DateUtil.dateToString(new Date(), "yyyy年MM月");
        map.put("time",cycle);
        List<GovernmentDepartment> list =  pageDao.showPageMonth(pageGongWen,cycle);
        Long num = 0L;
        if (list.size()>0 && list!=null ){
            for (int i = 0; i < list.size(); i++) {
                num +=list.get(i).getIntValue();
            }
            for (int i = 0; i < list.size(); i++) {
                String percent = BigDecimalUtil.getPercent(list.get(i).getIntValue(), num);
                list.get(i).setCycle(percent);
            }
            map.put(cycle,list);
            return JSON.toJSONString(map);
        }
        return "error";
    }


}
