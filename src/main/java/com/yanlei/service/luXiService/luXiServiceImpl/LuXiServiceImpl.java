package com.yanlei.service.luXiService.luXiServiceImpl;

import com.yanlei.dao.luXiDao.LuXiDao;
import com.yanlei.model.GovernmentDepartment;
import com.yanlei.model.luxi.Event;
import com.yanlei.service.luXiService.LuXiService;
import com.yanlei.util.DateUtil;
import com.yanlei.util.JsonMethod;
import com.yanlei.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: x
 * @Date: Created in 16:23 2018/5/11
 */

@Service("LuXiService")
public class LuXiServiceImpl implements LuXiService {

    @Autowired
    private LuXiDao luXiDao;

    @Override
    public Result insertPage(String s1) {

        //加载spring配置文件  
        ApplicationContext as = new ClassPathXmlApplicationContext(new String[]{"/spring/applicationContext-service.xml"});
        //调用getBean方法取得被实例化的对象。  
        LuXiDao psb = (LuXiDao) as.getBean("LuXiDao");
        List<GovernmentDepartment> list = new ArrayList<GovernmentDepartment>();
         Map<String,Object> duChaList = JsonMethod.readValue(s1,Map.class);
            Map<String,Object> body = ( Map<String,Object>)duChaList.get("Body");
            Map<String,Object> response = ( Map<String,Object>)body.get("response");
            List<Map<String,Object>> data = (List<Map<String,Object>>) response.get("Data");

            for (Map<String, Object> datum : data) {
                GovernmentDepartment governmentDepartment =new GovernmentDepartment();
                for (String s2 : datum.keySet()) {
                    governmentDepartment.setLevel("督查数据");
                    governmentDepartment.setLevel2(datum.get("title").toString());
                    governmentDepartment.setIntValue(1L);
                    governmentDepartment.setDoubleValue(0D);
                    governmentDepartment.setAcquisitionTime(new Date((Long) datum.get("instructionDate")));
                    governmentDepartment.setName((String) datum.get("originalWay"));//来文单位
                    //1=区长批示，2=区长批示，3=区长批示，123=区长批示，4=区长批示，5=通知公告，6=抄高单，7=告知单，8=督办件
                    governmentDepartment.setType(datum.get("type")+"");
                    governmentDepartment.setCycle(datum.get("isView")+""); //是否已读 0 = 否 ,1 = 是
                    governmentDepartment.setSource("鹭栖大于");
                }
                list.add(governmentDepartment);
            }
            //System.out.println(list);
            int num = psb.insertPage(list);

            if (num>0){
                return Result.ok("督察数据插入成功,条数====>>>"+num);
            }
            return Result.error("督察数据插入失败");
    }

    @Override
    public Result insertPageGongWen(String s1) {
        //加载spring配置文件  
        //ApplicationContext as = new ClassPathXmlApplicationContext(new String[]{"/spring/applicationContext-service.xml"});
        //调用getBean方法取得被实例化的对象。  
        //LuXiDao psb = (LuXiDao) as.getBean("LuXiDao");
        List<GovernmentDepartment> list = new ArrayList<GovernmentDepartment>();
        Map<String,Object> gongWenList = JsonMethod.readValue(s1,Map.class);
        Map<String,Object> body = ( Map<String,Object>)gongWenList.get("Body");
        Map<String,Object> response = ( Map<String,Object>)body.get("response");
        List<Map<String,Object>> data = null;
        try {
            data = (List<Map<String,Object>>) response.get("Data");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (data.size()>0 && data!=null) {
            for (Map<String, Object> datum : data) {
                GovernmentDepartment governmentDepartment = new GovernmentDepartment();
                for (String s2 : datum.keySet()) {
                    governmentDepartment.setName(datum.get("deptName") + "");//公文所属部门名称
                    governmentDepartment.setLevel("公文数据");
                    governmentDepartment.setLevel2(datum.get("title") + ""); //标题
                    governmentDepartment.setLevel3(datum.get("createUser") + ""); //创建用户
                    governmentDepartment.setType(datum.get("createUserName") + ""); //创建用户名
                    governmentDepartment.setSource("鹭栖大于");
                    if (datum.get("createTime") != null) {
                        governmentDepartment.setCycle(DateUtil.dateToString(new Date((Long) datum.get("createTime")), "yyyy年MM月"));
                        governmentDepartment.setAcquisitionTime(new Date((Long) datum.get("createTime")));//印发日期

                    }
                }
                list.add(governmentDepartment);
            }
            int num = luXiDao.insertPageGongWen(list);
            if (num > 0) {
                return Result.ok("公文数据插入成功,条数====>>>" + num);
            }
            return Result.error("公文数据插入失败");
        }else {
            return Result.error("公文数据无更新~~~~~~");
        }
    }

    @Override
    public Date findMaxTime(String pageGongWen) {

        return luXiDao.findMaxTime(pageGongWen);
    }

    @Override
    public Integer insertEvent(String s) {
        Map<String,Object> map = JsonMethod.readValue(s,Map.class);
        List<Map<String,Object>> data = null;
        try {
            List<Event> event =(List<Event>) map.get("event");
            //System.out.println(event);
            if (event.size()>0 &&event!=null){

                Integer num = luXiDao.insertEvent(event);
                if (num == event.size() && num != null){
                    return num;
                }else {
                    return -1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Long findMaxTongId() {
        return luXiDao.findMaxTongId();
    }

}
