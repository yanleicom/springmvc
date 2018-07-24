package com.yanlei.controller.showData;

import com.alibaba.fastjson.JSON;
import com.besjon.pojo.JsonRootBean1;
import com.yanlei.service.showData.SocietyService;
import com.yanlei.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

import static com.yanlei.controller.showData.PoliticsController.fileURL;

/**
 * 图二数据显示
 * @Author: x
 * @Date: Created in 15:53 2018/4/16
 */

@Controller
public class SocietyController {

    private static final Logger logger = LoggerFactory.getLogger(SocietyController.class);


    @Autowired
    private SocietyService societyService;



    @RequestMapping(value = "/showSocietyLeft" , method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showSocietyLeft() throws IOException {
        Map<String, String> stringStringMap = PropertiesUtil.GetAllProperties(fileURL);

        String nineName = stringStringMap.get("nineName");//主要社会指标

        JsonRootBean1 jsonRootBean = societyService.findLevel3(nineName);

        if (jsonRootBean!=null){

            return JSON.toJSONString(jsonRootBean);
        }

        logger.info("图二左边数据展示失败");
        return "error";

    }



    @RequestMapping(value = "/showSocietyRight" , method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showSocietyRight() throws IOException {
        Map<String, String> stringStringMap = PropertiesUtil.GetAllProperties(fileURL);

        String TenName = stringStringMap.get("TenName");//床位使用情况

        JsonRootBean1 jsonRootBean = societyService.findLevel3Right(TenName);

        if (jsonRootBean!=null){

            return JSON.toJSONString(jsonRootBean);
        }

        logger.info("图二右边数据展示失败");
        return "error";

    }
}
