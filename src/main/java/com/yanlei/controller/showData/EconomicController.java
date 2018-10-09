package com.yanlei.controller.showData;

import com.alibaba.fastjson.JSON;
import com.besjon.pojo.JsonRootBean;
import com.besjon.pojo.JsonRootBean3;
import com.besjon.pojo.JsonRootBean5;
import com.yanlei.service.showData.EconomicService;
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
 * 图四 经济数据展示
 * @Author: x
 * @Date: Created in 17:27 2018/4/17
 */

@Controller
public class EconomicController {

    private static final Logger logger = LoggerFactory.getLogger(EconomicController.class);

    @Autowired
    private EconomicService economicService;

    @RequestMapping(value = "/showEconomicLeft" , method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showEconomicLeft() throws IOException {
        //Map<String, String> stringStringMap = PropertiesUtil.GetAllProperties(fileURL);

        //String sixteenName = stringStringMap.get("sixteenName");//分区域事件数 年,月,日
        String sixteenName = PropertyUtil.getProperty("sixteenName");
        JsonRootBean3 jsonRootBean = economicService.findFourLeft(sixteenName);

        if (jsonRootBean!=null){

            return JSON.toJSONString(jsonRootBean);
        }

        logger.info("图四左边数据展示失败");
        return "error";

    }


    @RequestMapping(value = "/showEconomicMiddle" , method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showEconomicMiddle() throws IOException {
        //Map<String, String> stringStringMap = PropertiesUtil.GetAllProperties(fileURL);
        //String seventeenName = stringStringMap.get("seventeenName");//网上办件总数
        //String eighteenName = stringStringMap.get("eighteenName");//分类型事件数

        String seventeenName = PropertyUtil.getProperty("seventeenName");
        String eighteenName = PropertyUtil.getProperty("eighteenName");

        JsonRootBean5 jsonRootBean = economicService.findFourMiddle(seventeenName,eighteenName);

        if (jsonRootBean!=null){

            return JSON.toJSONString(jsonRootBean);
        }

        logger.info("图四中间数据展示失败");
        return "error";

    }


    @RequestMapping(value = "/showEconomicRight" , method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showEconomicRight() throws IOException {
        //Map<String, String> stringStringMap = PropertiesUtil.GetAllProperties(fileURL);

       // String nineteenName = stringStringMap.get("nineteenName");//分部门办件数
        String nineteenName = PropertyUtil.getProperty("nineteenName");

        JsonRootBean jsonRootBean = economicService.findFourRight(nineteenName);

        if (jsonRootBean!=null){

            return JSON.toJSONString(jsonRootBean);
        }

        logger.info("图四中间数据展示失败");
        return "error";

    }
}
