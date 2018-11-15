package com.yanlei.controller.quartz;

import com.yanlei.util.HttpDataUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @Author: x
 * @Date: Created in 11:11 2018/5/26
 */

@Controller
public class ExchangeController {

    private Logger logger = LoggerFactory.getLogger(ExchangeController.class);
    //交换量统计
    private static  final String exchange_url = "http://172.16.10.105:8080/hzxcq_dxm/exchangeTotal";
    //交换总量统计
    private static  final String exchangeSum_url = "http://172.16.10.105:8080/hzxcq_dxm/exchangeHzCount";
    //今日交换部门统计
    private static  final String exchange_bumeng_url = "http://172.16.10.105:8080/hzxcq_dxm/exchangeActiveTime";
    //数据共享 -->>  and -->> 数据归集
    private static  final String ShareList_url = "http://172.16.10.105:8080/hzxcq_dxm/exchangeTrend";
    //四个平台分类数据
    private static  final String event_url = "http://172.16.10.105:8080/jfinal_demo/eventData";
    //政务平台季度数据统计
    private static final String government_url = "http://172.16.9.140:8080/jfinal_demo/zwtj";


    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    @RequestMapping(value = "/showExchange" , method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showExchange(HttpServletResponse response)  {
        //response.addHeader("Access-Control-Allow-Origin", "*");
        String s = HttpDataUtil.httpGet2(exchange_url, null);
        if (StringUtils.isNotBlank(s)){
            //logger.info("交换量统计 ---->>>>  "+s);
            return s;
        }
        return null;
    }


    @RequestMapping(value = "/showExchangeSum" , method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showExchangeSum(HttpServletResponse response)  {
       // response.addHeader("Access-Control-Allow-Origin", "*");
        String s = HttpDataUtil.httpGet2(exchangeSum_url, null);
        if (StringUtils.isNotBlank(s)){
           // logger.info("交换总量统计 ---->>>>  "+s);
            return s;
        }
        return null;
    }

    @RequestMapping(value = "/showExchangeBuMeng" , method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showExchangeBuMeng(HttpServletResponse response)  {
       // response.addHeader("Access-Control-Allow-Origin", "*");
        String s = HttpDataUtil.httpGet2(exchange_bumeng_url, null);
        if (StringUtils.isNotBlank(s)){
           // logger.info("今日交换部门统计 ---->>>> "+s);
            return s;
        }
        return null;
    }


    //综合指挥共享数据,有任务管理系统和权利事项下发到数据中心的一些事件数量
    @RequestMapping(value = "/showShareList" , method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showShareList(HttpServletResponse response)  {
        //response.addHeader("Access-Control-Allow-Origin", "*");
        String s = HttpDataUtil.httpGet2(ShareList_url, null);
        if (StringUtils.isNotBlank(s)){
            //logger.info("数据共享 -->>  and -->> 数据归集 ---->>>> "+s);
            return s;
        }
        return null;
    }

    //四个平台街道数据展示,分为四类型,展示轮播,按类型分街道展示
    @RequestMapping(value = "/showEventData" , method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showEventData(HttpServletResponse response)  {
        //response.addHeader("Access-Control-Allow-Origin", "*");
        String s = HttpDataUtil.httpGet2(event_url, null);
        if (StringUtils.isNotBlank(s)){
           // logger.info("四个平台数据显示 -->>-->>--->>>>"+s);
            return s;
        }
        return null;
    }

    //不对接小林,全对接数据中心,这个对接谢杰 季度数据更新,三个月一次,显示前五十按百分比倒叙
    //前端指针转动 每次显示一个数据 和每个月政务数据在一个饼图展示, 显示形式相同
   @RequestMapping(value = "showGovernment", method = RequestMethod.GET,
           produces = "text/json;charset=UTF-8")
   @ResponseBody
    public String showGovernment(){
       redisTemplate.opsForValue().set("第二季度政务公开",null);
      /* String s1 = redisTemplate.opsForValue().get("第二季度政务公开");
       if (StringUtils.isNotBlank(s1)){
           return s1;
       }*/
       String s = HttpDataUtil.httpGet2(government_url, null);
        if (StringUtils.isNotBlank(s)){
            //logger.info("政务平台季度数据统计 -->>"+s);
            redisTemplate.opsForValue().set("第二季度政务公开",s,1,TimeUnit.DAYS);
            return s;
        }
        return null;
   }
}
