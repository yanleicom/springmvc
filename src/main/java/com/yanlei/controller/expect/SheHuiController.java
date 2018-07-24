package com.yanlei.controller.expect;

import com.yanlei.service.showData.SheHuiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: x
 * @Date: Created in 15:09 2018/5/17
 */

@Controller
public class SheHuiController {
    private Logger logger = LoggerFactory.getLogger(SheHuiController.class);

    @Autowired
    private SheHuiService sheHuiService;

    /**
     * 人口数据  mzj_rkxxjl
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/showSheHuiPeople" , method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showPeople(HttpServletResponse response)  {
        //response.addHeader("Access-Control-Allow-Origin", "*");

        String s = sheHuiService.findPeople();
        //logger.info(s);
        return s;
    }


    /**
     * 浙江省 - 杭州市 - 各个区 - 街道(县)
     * address 杭州市地址数据展示
     * @param response
     * @return json
     */
    /*@RequestMapping(value = "/showSheHuiQuYu" , method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showSheHuiQuYu(HttpServletResponse response)  {
        //response.addHeader("Access-Control-Allow-Origin", "*");
        //显示杭州市下面各个区域(县) 区域下各个街道(县)和人数
        String s = sheHuiService.showSheHuiQuYu();
        return s;
    }*/



    /**
     * 重点企业收税信息  收入和税收
     * 重点企业信息表 -- czj_zdqyxxb   shhj -- 税收合计  czzsr -- 财政总收入
     * @param response
     * @param year 默认显示2017年信息 暂未统计2018年信息
     * @return json
     */
    @RequestMapping(value = "/showEnterprise" , method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showEnterprise(HttpServletResponse response,@RequestParam(defaultValue = "2017") String year)  {
       // response.addHeader("Access-Control-Allow-Origin", "*");

        String s = sheHuiService.findEnterprise(year);
        //logger.info(s);
        return s;
    }

    /**
     * 事件数的统计 event
     * @return
     */
    @RequestMapping(value = "/showEvent" , method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showEvent() {
        String s = sheHuiService.findEvent();
        //logger.info(s);
        return s;
    }


    /**
     *  楼宇信息 八个街道楼宇数(幢) 总数
     * @return
     */
/*    @RequestMapping(value = "/showBuilding" , method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showBuilding() {
        String s =  sheHuiService.findBuilding();
        return s;
    }*/

    /**
     *  楼宇信息 分街道按面积算 总面积,空余面积
     * @return
     */
    @RequestMapping(value = "/showBuildingArea" , method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showBuildingArea(HttpServletResponse response) {
        //response.addHeader("Access-Control-Allow-Origin", "*");
        String s =  sheHuiService.showBuildingArea();
        //logger.info(s);
        return s;
    }
}
