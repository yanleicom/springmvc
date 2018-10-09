package com.yanlei.controller.quartz;

import com.alibaba.fastjson.JSON;
import com.yanlei.model.luxi.Login;
import com.yanlei.service.luXiService.LuXiService;
import com.yanlei.util.*;
import com.yanlei.util.luxi.httpDataUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//import static com.yanlei.controller.showData.PoliticsController.fileURL;

/**
 * @Author: x
 * @Date: Created in 11:11 2018/5/26
 */
@Controller
public class GongWenController {

    private static final Logger logger = LoggerFactory.getLogger(GongWenController.class);
    //登入获取tokne和userId
    private static final String login_url = "http://xcgov.hzxc.gov.cn/Base-Module/CompanyUserLogin/UserValidate";
    //实时督查数据
    private static final String ducha_url = "http://xcgovapi.hzxc.gov.cn/Supervision-Module/Supervision/List";
    //实时公文数据
    private static final String gongwen_url = "http://xcgovapi.hzxc.gov.cn/Document-Module/Document/GetListAll"; //公文
    //业务十日交换量测试地址 //81761
    private static  final String TenDays_exchange_url = "http://172.16.10.105:8080/jfinal_demo/event";

    private static String token = null;
    private static String companyCode = "xcgov";
    private static String companyShowID = "b5WJZ1bRLDCb7x2B";
    private static String loginName = "NO6lZyJjYRCAKd9R";
    private static String userName = "boyd";

    /*public static final String fileURL = "C:\\MultipartFile\\config.properties";
    Map<String, String> stringStringMap;

    {
        try {
            stringStringMap = PropertiesUtil.GetAllProperties(fileURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    @Autowired
    private LuXiService luXiService;


    //公文数据 page 每十五分钟定时一次
    public void insertGongWen() {
        String s = httpDataUtil.login("xiejie");
        Login login = JSON.parseObject(s, Login.class);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("timestamp", login.getTimestamp().getTime());
        map.put("appId", login.getAppId());
        map.put("echostr", login.getEchostr());
        map.put("nonce", login.getNonce());
        map.put("signature", login.getSignature());
        map.put("loginName", login.getLoginName());
        Map<String, Object> strMap = JsonMethod.readValue(httpDataUtil.doPost(login_url, map), Map.class);
        Map<String, Object> bodyMap = (Map<String, Object>) strMap.get("Body");
        Map<String, Object> responseMap = (Map<String, Object>) bodyMap.get("response");
        Map<String, Object> DataMap = (Map<String, Object>) responseMap.get("Data");

        //测试token postman 注意换resourceUri !! 督查和公文不同
//        System.out.println(DataMap.get("token"));
        token = (String) DataMap.get("token");
        String pageGongWen = PropertyUtil.getProperty("pageGongWen");
//        String pageGongWen = stringStringMap.get("pageGongWen");
//        System.out.println(pageGongWen);
        //查询最大的时间
        Date date = luXiService.findMaxTime(pageGongWen);
        //System.out.println("查询时间"+date.getTime());
        Date date1 = DateUtil.addOneSecond(date); //最大时间加1秒 对接设计大于等于所以加1秒
        long startTime = DateUtil.dateToLong(date1); //大于数据库最大时间 传long值
       // System.out.println("加一秒时间"+startTime);
        long endTime = new Date().getTime(); // 小于当前时间
       // System.out.println(endTime);
        String officialDocument = httpDataUtil.getOfficialDocument(token,companyCode,companyShowID,loginName,userName,startTime,endTime);
        try {
            String s1 = httpDataUtil.doPost(gongwen_url, officialDocument);
            //System.out.println(s1);
            //公文数据插入
            Result result = luXiService.insertPageGongWen(s1);
            logger.info("公文数据插入情况===>>>"+result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("公文数据出错" + e);
        }
    }


    //四个平台事件数 event 每二小时定时一次
    public String showTenDaysExchange()  {
        Long tongId = luXiService.findMaxTongId(); //查询最大tongid id大于表示新数据加入数据库 数据动态
        String s = HttpDataUtil.httpGet2(TenDays_exchange_url+"?tongId="+tongId, null);
        if (StringUtils.isNotBlank(s)){
            Integer result = luXiService.insertEvent(s);
            if (result!=null && result>0){
                logger.info("业务十日交换量成功,成功条数 ---->>>> "+result);
                return null;
            }else {
                logger.info("业务十日交换量为空 ~~~");
            return null;
            }

        }
        logger.error("业务十日交换量获取失败!!!");
        return null;
    }
}
