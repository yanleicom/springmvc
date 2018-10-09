package com.yanlei.controller.showData;

import com.alibaba.fastjson.JSON;
import com.besjon.pojo.JsonRootBean3;
import com.besjon.pojo.JsonRootBean4;
import com.yanlei.service.showData.CultureService;
import com.yanlei.util.PropertyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;



/**
 * 图三数据显示
 * @Author: x
 * @Date: Created in 11:18 2018/4/17
 */

@Controller
public class CultureController {
    private static final Logger logger = LoggerFactory.getLogger(CultureController.class);

    @Autowired
    private CultureService cultureService;

    @RequestMapping(value = "/showCultureLeft" , method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showCultureLeft() throws IOException {
        //Map<String, String> stringStringMap = PropertiesUtil.GetAllProperties(fileURL);
        //String elevenName = stringStringMap.get("elevenName");//总服务事件
        //String twelveName = stringStringMap.get("twelveName");//分区域事件数 年,月,日

        String elevenName = PropertyUtil.getProperty("elevenName");
        String twelveName = PropertyUtil.getProperty("twelveName");
        JsonRootBean3 jsonRootBean = cultureService.findThreeLeft(elevenName,twelveName);

        if (jsonRootBean!=null){

            return JSON.toJSONString(jsonRootBean);
        }

        logger.info("图三左边数据展示失败");
        return "error";

    }



    @RequestMapping(value = "/showCultureRight" , method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showCultureRight() throws Exception {

        try {
           // Map<String, String> stringStringMap = PropertiesUtil.GetAllProperties(fileURL);

            //String thirteenName = stringStringMap.get("thirteenName");//企业服务事件
            //String fourteenName = stringStringMap.get("fourteenName");//分类型事件数
            //String fifteenName = stringStringMap.get("fifteenName");//分部门事件数

            String thirteenName = PropertyUtil.getProperty("thirteenName");
            String fourteenName = PropertyUtil.getProperty("fourteenName");
            String fifteenName = PropertyUtil.getProperty("fifteenName");

            JsonRootBean4 jsonRootBean = cultureService.findThreeRight(thirteenName,fourteenName,fifteenName);

            if (jsonRootBean!=null){

                return JSON.toJSONString(jsonRootBean);
            }

            logger.info("图三右边数据展示失败");
            return "error";
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("读取配置文件失败"+e);
        }
        return "error";
    }
}


