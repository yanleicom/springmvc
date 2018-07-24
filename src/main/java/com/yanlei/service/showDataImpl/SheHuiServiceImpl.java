package com.yanlei.service.showDataImpl;

import com.alibaba.fastjson.JSON;
import com.yanlei.dao.showData.SheHuiDao;
import com.yanlei.model.luxi.EventModel;
import com.yanlei.model.shehui.BuildingArea;
import com.yanlei.model.shehui.Enterprise;
import com.yanlei.model.shehui.People;
import com.yanlei.service.showData.SheHuiService;
import com.yanlei.util.luxi.BigDecimalUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: x
 * @Date: Created in 15:14 2018/5/17
 */

@Service
public class SheHuiServiceImpl implements SheHuiService {

    @Autowired
    private SheHuiDao sheHuiDao;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    @Override
    public String findPeople() {

        String s1 = redisTemplate.opsForValue().get("下城区人口情况");
        if (StringUtils.isNotBlank(s1)){
            return s1;
        }else {
            Map<String, Object> map = new HashMap<String, Object>();
            //人口总数
            Integer sum = sheHuiDao.findPeopleSum();
            double f1 = BigDecimalUtil.BigDecimal(sum);
            //男人口数
            String man = "男";
            Integer manPeople = sheHuiDao.findManPeople(man);
            double f2 = BigDecimalUtil.BigDecimal(manPeople);
            //女人口数
            Integer girlPeople = sum - manPeople;
            double f3 = BigDecimalUtil.BigDecimal(girlPeople);
            //街道人口数
            List<People> list = sheHuiDao.findJieDaoPeople();
            for (People people : list) {
                double f4 = BigDecimalUtil.BigDecimal(people.getIntValue());
                people.setDoubleValue(f4);
                people.setIntValue(null);
            }
            //常驻人口 流动人口 还有 null 默认加为其他
            List<String> zhlx = sheHuiDao.findZhlx();
            if (zhlx != null && zhlx.size() > 0) {
                for (String s : zhlx) {
                    if (s != null && s != "") {
                        Integer people = sheHuiDao.findFenZhlx(s);
                        double f4 = BigDecimalUtil.BigDecimal(people);
                        map.put(s, f4);
                    } else {
                        Integer people = sheHuiDao.findQita();
                        double f4 = BigDecimalUtil.BigDecimal(people);
                        map.put("其他", f4);
                    }
                }
            }

            map.put("人口总数", f1);
            map.put("男人口数", f2);
            map.put("女人口数", f3);
            map.put("街道人口数", list);
            map.put("单位", "万人");
            String s = JSON.toJSONString(map);
            redisTemplate.opsForValue().set("下城区人口情况", s, 15, TimeUnit.DAYS);
            return s;
        }
    }

    @Override
    public String showSheHuiQuYu() {
        String s1 = redisTemplate.opsForValue().get("杭州市各个区域");
        if (StringUtils.isNotBlank(s1)){
            return s1;
        }else {
            Map<String, Object> map = new HashMap<String, Object>();
            //杭州市下面的各个区(县)
            List<String> countyNames = sheHuiDao.countyNames();
            if (countyNames != null && countyNames.size() > 0) {
                for (String countyName : countyNames) {
                    List<People> list = sheHuiDao.findQuYuPeople(countyName);
                    map.put(countyName, list);

                }
            }
            map.put("杭州市各个区域", countyNames);
            map.put("单位", "人");
            String s = JSON.toJSONString(map);
            redisTemplate.opsForValue().set("杭州市各个区域",s);
            return s;
        }

    }

    @Override
    public String findEnterprise(String year) {
        redisTemplate.opsForValue().set("收入于税收",null);
        String s1 = redisTemplate.opsForValue().get("收入于税收");
        if (StringUtils.isNotBlank(s1)) {
            return s1;
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            List<Enterprise> list = sheHuiDao.findEnterprise(year);
            Float xcShhjSum = 0.0F;
            Float xcCzzsrSum = 0.0F;
            if (list != null && list.size() > 0) {
                for (Enterprise enterprise : list) {
                    float v = BigDecimalUtil.BigDecimal(enterprise.getShhjSum());
                    float v1 = BigDecimalUtil.BigDecimal(enterprise.getCzzsrSum());
                    enterprise.setShhjSum(v);
                    enterprise.setCzzsrSum(v1);
                    xcShhjSum += v;
                    xcCzzsrSum += v1;

                }
            }
            map.put("xcShhjSum",xcShhjSum);
            float  b   =  (float)(Math.round(xcCzzsrSum*100))/100; //转成二位小数
            map.put("xcCzzsrSum",b);
            map.put("2017年下城区各个区域财政收入于税收情况", list);
            map.put("单位", "亿元");
            redisTemplate.opsForValue().set("收入于税收", JSON.toJSONString(map), 15, TimeUnit.DAYS);
            return JSON.toJSONString(map);
        }
    }

    @Override
    public String findEvent() {
        Map<String,Object> map = new HashMap<String, Object>();
        List<EventModel> monthsAndCount= sheHuiDao.findMonthsAndCount();
        Integer sum = 0;
        if (monthsAndCount.size()>0 &&monthsAndCount!=null){
            map.put("eventMonthsAndCount",monthsAndCount);
            for (int i = 0; i < monthsAndCount.size(); i++) {
                Integer count = monthsAndCount.get(i).getCount();
                sum+=count;
                String months = monthsAndCount.get(i).getMonths();
                List<EventModel> list = sheHuiDao.findWorkClass(months);
                map.put(months,list);
            }
            String fastMonths = monthsAndCount.get(0).getMonths();
            String lastMonths = monthsAndCount.get(monthsAndCount.size() - 1).getMonths();
            map.put(fastMonths+"-"+lastMonths+"总事件数",sum);

        }

        return JSON.toJSONString(map);
    }

    @Override
    public String findBuilding() {
        Map<String,Object> map = new HashMap<String, Object>();
        Integer sum = 0;
        List<People> list = sheHuiDao.findBuilding();
        if (list.size()>0 &&list !=null){
            for (int i = 0; i < list.size(); i++) {
                if (StringUtils.isNotBlank(list.get(i).getStreetName())){
                    sum += list.get(i).getIntValue();
                    map.put(list.get(i).getStreetName(),list.get(i).getIntValue());
                }
            }
        }
        map.put("sum",sum);
        return JSON.toJSONString(map);
    }


    @Override
    public String showBuildingArea() {
        Map<String,Object> map = new HashMap<String, Object>();
        List<BuildingArea> list =  sheHuiDao.showBuildingArea();
        List<String> arr =new ArrayList<String>();
        List<String> name =new ArrayList<String>();
        double liveArea = 0.0D;//入住面积总数
        double totalArea = 0.0D;//楼宇总面积
        if (list.size()>0 &&list!=null){
            for (BuildingArea buildingArea : list) {
                if (StringUtils.isNotBlank(buildingArea.getStreetName())){
                    double v = buildingArea.getLiveArea().doubleValue();
                    double t = buildingArea.getTotalArea().doubleValue();
                    liveArea+=v; //入住面积总数
                    totalArea+=t;//楼宇总面积
                    double v1 = BigDecimalUtil.BigDecimal(v);//转换单位
                    double t1 = BigDecimalUtil.BigDecimal(t);//转换单位
                    //BigDecimal bd3 = BigDecimal.valueOf(v1);//转换类型
                    //buildingArea.setLiveArea(bd3);
                    //map.put(buildingArea.getStreetName(),buildingArea.getStreetName());
                    String percent = BigDecimalUtil.getPercent(v1, t1);
                    arr.add(percent);
                    name.add(buildingArea.getStreetName());
                }
            }
        }
        map.put("liveArea",BigDecimalUtil.BigDecimal(liveArea)); //入住面积
        map.put("totalArea",BigDecimalUtil.BigDecimal(totalArea)); //总面积
        map.put("unit","万平方米");
        map.put("name",name);
        map.put("arr",arr);
        return JSON.toJSONString(map);
    }
}
