package com.yanlei.controller.luxiData;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yanlei.model.luxi.Login;
import com.yanlei.service.luXiService.LuXiService;
import com.yanlei.util.JsonMethod;
import com.yanlei.util.Result;
import com.yanlei.util.luxi.httpDataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: x
 * @Date: Created in 18:45 2018/5/10
 */
@Controller
public class LuXiController {

    private static Logger logger = LoggerFactory.getLogger(LuXiController.class);

    @Autowired
    private  LuXiService luXiService;

    private static final String login_url = "http://xcgov.hzxc.gov.cn/Base-Module/CompanyUserLogin/UserValidate";
    private static final String ducha_url = "http://xcgovapi.hzxc.gov.cn/Supervision-Module/Supervision/List";
    private static final String gongwen_url = "http://xcgovapi.hzxc.gov.cn/Document-Module/Document/GetListAll"; //公网
    //private static final String gongwen_url = "http://172.16.10.172:6002/Document-Module/Document/GetListAll"; //测试
    private static String token = null;
    private static String companyCode = "xcgov";
    private static String companyShowID = "b5WJZ1bRLDCb7x2B";
    private static String loginName = "NO6lZyJjYRCAKd9R";
    private static String userName = "boyd";


    @RequestMapping(value = "/PageDucha",produces = "text/json;charset=UTF-8")
    public Result PageDucha() {
        //加载spring配置文件  
       ApplicationContext as = new ClassPathXmlApplicationContext(new String[]{"/spring/applicationContext-service.xml"});
      //调用getBean方法取得被实例化的对象。  
        LuXiService psb = (LuXiService) as.getBean("LuXiService");

        //登入获取tokne
        Map<String, Object> map = getStringObjectMap();
        //解析得到token
        Map<String, Object> DataMap = getStringToken(map);


        Map<String, String> headerMap = new HashMap<String, String>();
        Map<String, Object> BodyMap = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();

        headerMap.put("async", "false");
        headerMap.put("authToken", DataMap.get("token") + "");
        headerMap.put("companyCode", companyCode);
        headerMap.put("companyShowID", companyShowID);
        headerMap.put("language", "zh-cn");
        headerMap.put("loginName", loginName);
        headerMap.put("resourceUri", "/Supervision-Module/Supervision/List");
        headerMap.put("type", "GET");
        headerMap.put("userName", userName);

        int shuji[] = {6, 7, 8};//书记批示件传6，7，8； 区长批示件传1，2，3，4，5
        int quchang[] = {1, 2, 3, 4, 5};

        paramMap.put("types", quchang); //设置试图 区长 书记
        paramMap.put("readStatus", "");//未填/other=所有,0=未阅,1=已阅
        paramMap.put("feedbackStatus", "");//未填/other=所有,1=已反馈，2=未反馈
        paramMap.put("fileStatus", "");//未填/other=所有,0=未归档,1=已归档

        BodyMap.put("param", paramMap);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("header", headerMap);
        jsonObject.put("body", BodyMap);

        try {
            //督查数据
            String s1 = httpDataUtil.doPost(ducha_url, jsonObject.toString());
            //督查数据插入
            Result result = psb.insertPage(s1);
            logger.info("督查数据插入情况===>>>"+result);

        } catch (Exception e) {
            logger.error("督查数据出错" + e);
        }

        return null;

    }

    private Map<String, Object> getStringToken(Map<String, Object> map) {
        //UserAndToken userAndToken = JSON.parseObject(s, UserAndToken.class);
        Map<String, Object> strMap = JsonMethod.readValue(httpDataUtil.doPost(login_url, map), Map.class);
        Map<String, Object> bodyMap = (Map<String, Object>) strMap.get("Body");
        Map<String, Object> responseMap = (Map<String, Object>) bodyMap.get("response");
        Map<String, Object> DataMap = (Map<String, Object>) responseMap.get("Data");

        //测试token postman 注意换resourceUri !! 督查和公文不同
        System.out.println(DataMap.get("token"));
        token = (String) DataMap.get("token");
        return DataMap;
    }

    private Map<String, Object> getStringObjectMap() {
        String s = httpDataUtil.login("xiejie");
        Login login = JSON.parseObject(s, Login.class);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("timestamp", login.getTimestamp().getTime());
        map.put("appId", login.getAppId());
        map.put("echostr", login.getEchostr());
        map.put("nonce", login.getNonce());
        map.put("signature", login.getSignature());
        map.put("loginName", login.getLoginName());
        return map;
    }


    @RequestMapping(value = "/PageGongWen",produces = "text/json;charset=UTF-8")
    public Result PageGongWen() {
        //加载spring配置文件  
        ApplicationContext as = new ClassPathXmlApplicationContext(new String[]{"/spring/applicationContext-service.xml"});
        //调用getBean方法取得被实例化的对象。  
        LuXiService psb = (LuXiService) as.getBean("LuXiService");

        //登入获取tokne
        Map<String, Object> map = getStringObjectMap();
        //将token分装成全局变量
        Map<String, Object> stringToken = getStringToken(map);

        String officialDocument = httpDataUtil.getOfficialDocument2(token,companyCode,companyShowID,loginName,userName);
        try {
            String s1 = httpDataUtil.doPost(gongwen_url, officialDocument);
            //System.out.println(s1);
            //公文数据插入
            Result result = psb.insertPageGongWen(s1);
            logger.info("公文数据插入情况===>>>"+result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("公文数据出错" + e);
        }

        return null;
    }

}
