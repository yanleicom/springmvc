/*
package com.yanlei.controller.quartz;

import com.alibaba.fastjson.JSON;
import com.yanlei.model.BusinessData;
import com.yanlei.model.luxi.Login;
import com.yanlei.service.QuartzService;
import com.yanlei.service.luXiService.LuXiService;
import com.yanlei.util.DateUtil;
import com.yanlei.util.JsonMethod;
import com.yanlei.util.PropertiesUtil;
import com.yanlei.util.Result;
import com.yanlei.util.luxi.httpDataUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yanlei.controller.showData.PoliticsController.fileURL;

*/
/**
 * @Author: x
 * @Date: Created in 16:09 2018/3/26
 *//*

@Controller
public class QuartzController implements Job{

   private static final Logger logger = LoggerFactory.getLogger(QuartzController.class);

    private static final String login_url = "http://xcgov.hzxc.gov.cn/Base-Module/CompanyUserLogin/UserValidate";
    private static final String ducha_url = "http://xcgovapi.hzxc.gov.cn/Supervision-Module/Supervision/List";
    private static final String gongwen_url = "http://xcgovapi.hzxc.gov.cn/Document-Module/Document/GetListAll"; //公网
    private static String token = null;
    private static String companyCode = "xcgov";
    private static String companyShowID = "b5WJZ1bRLDCb7x2B";
    private static String loginName = "NO6lZyJjYRCAKd9R";
    private static String userName = "boyd";

    @Autowired
    private QuartzService quartzService;

    @Autowired
    private LuXiService luXiService;


    Map<String, String> stringStringMap;

    {
        try {
            stringStringMap = PropertiesUtil.GetAllProperties(fileURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void execute(JobExecutionContext context) throws JobExecutionException {

            */
/*System.out.println("myJob 执行了 ......"+ context.getTrigger().getKey().getName());
            ApplicationContext applicationContext = (ApplicationContext) context.getJobDetail().getJobDataMap().get("applicationContext");
            System.out.println("获取到的Spring的容器是: " + applicationContext);*//*


            //查询restful中所有的部门id和采集时间
       */
/* Result idAndTime = quartzService.findDepartmentIdAndAcquisitiontime();//政治
        //Result businessData = quartzService.findBusinessData();
        logger.info("定时任务One执行   =========>   任务执行结果  ==========>"+idAndTime);

        Result num = quartzService.saveGovernmentSociety();//社会
        logger.info("定时任务Two执行   =========>   任务执行结果  ==========>"+num);

        Result number = quartzService.saveGovernmentCulture();//文化
        logger.info("定时任务Three执行   =========>   任务执行结果  ==========>"+number);*//*


        */
/*Result numbers = quartzService.saveGovernmentEconomic();//经济
        logger.info("定时任务Four执行   =========>   任务执行结果  ==========>"+numbers);*//*


        String s = HttpDataUtil.login("xiejie");
        Login login = JSON.parseObject(s, Login.class);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("timestamp", login.getTimestamp().getTime());
        map.put("appId", login.getAppId());
        map.put("echostr", login.getEchostr());
        map.put("nonce", login.getNonce());
        map.put("signature", login.getSignature());
        map.put("loginName", login.getLoginName());
        Map<String, Object> strMap = JsonMethod.readValue(HttpDataUtil.doPost(login_url, map), Map.class);
        Map<String, Object> bodyMap = (Map<String, Object>) strMap.get("Body");
        Map<String, Object> responseMap = (Map<String, Object>) bodyMap.get("response");
        Map<String, Object> DataMap = (Map<String, Object>) responseMap.get("Data");

        //测试token postman 注意换resourceUri !! 督查和公文不同
        //System.out.println(DataMap.get("token"));
        token = (String) DataMap.get("token");

        String pageGongWen = stringStringMap.get("pageGongWen");

        //查询最大的时间
        Date date = luXiService.findMaxTime(pageGongWen);
        //System.out.println("查询时间"+date.getTime());
        Date date1 = DateUtil.addOneSecond(date); //最大时间加1秒 对接设计大于等于所以加1秒
        long startTime = DateUtil.dateToLong(date1); //大于数据库最大时间 传long值
        //System.out.println("加一秒时间"+startTime);
        long endTime = new Date().getTime(); // 小于当前时间
        System.out.println(endTime);
        String officialDocument = HttpDataUtil.getOfficialDocument(token,companyCode,companyShowID,loginName,userName,startTime,endTime);
        try {
            String s1 = HttpDataUtil.doPost(gongwen_url, officialDocument);
            System.out.println(s1);
            //公文数据插入
            Result result = luXiService.insertPageGongWen(s1);
            logger.info("公文数据插入情况===>>>"+result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("公文数据出错" + e);
        }
    }



}
*/
