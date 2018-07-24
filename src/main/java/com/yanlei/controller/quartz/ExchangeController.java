package com.yanlei.controller.quartz;

import com.yanlei.util.HttpDataUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

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


    @RequestMapping(value = "/showExchange" , method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showExchange(HttpServletResponse response)  {
        //response.addHeader("Access-Control-Allow-Origin", "*");
        String s = HttpDataUtil.httpGet2(exchange_url, null);
        if (StringUtils.isNotBlank(s)){
            logger.info("交换量统计 ---->>>>  "+s);
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
            logger.info("交换总量统计 ---->>>>  "+s);
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
            logger.info("今日交换部门统计 ---->>>> "+s);
            return s;
        }
        return null;
    }


    @RequestMapping(value = "/showShareList" , method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showShareList(HttpServletResponse response)  {
        //response.addHeader("Access-Control-Allow-Origin", "*");
        String s = HttpDataUtil.httpGet2(ShareList_url, null);
        if (StringUtils.isNotBlank(s)){
            logger.info("数据共享 -->>  and -->> 数据归集 ---->>>> "+s);
            return s;
        }
        return null;
    }


    @RequestMapping(value = "/showEventData" , method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showEventData(HttpServletResponse response)  {
        //response.addHeader("Access-Control-Allow-Origin", "*");
        String s = HttpDataUtil.httpGet2(event_url, null);
        if (StringUtils.isNotBlank(s)){
            logger.info("四个平台数据显示 -->>-->>--->>>>"+s);
            return s;
        }
        return null;
    }
}
