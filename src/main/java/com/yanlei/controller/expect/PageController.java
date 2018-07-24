package com.yanlei.controller.expect;

import com.alibaba.fastjson.JSON;
import com.besjon.pojo.JsonToJingji;
import com.yanlei.model.GovernmentDepartment;
import com.yanlei.service.showData.PageService;
import com.yanlei.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.yanlei.controller.showData.PoliticsController.fileURL;

/**
 * 政务数据的展示
 *
 * @Author: x
 * @Date: Created in 16:44 2018/5/9
 */
@Controller
public class PageController {

    private Logger logger = LoggerFactory.getLogger(PageController.class);

    //加载配置文件 读取K - V
    Map<String, String> stringStringMap;

    {
        try {
            stringStringMap = PropertiesUtil.GetAllProperties(fileURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private PageService pageService;

    /**
     * 2018年第二季度政务公开统计 数据展示
     *
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/showPageOne", method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showPageOne(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");

        String pageOne = stringStringMap.get("pageOne");//2018年第一季度政务公开统计
        String jsonToJingji = pageService.pageOne(pageOne);
        //logger.info(jsonToJingji);
        return jsonToJingji;
    }


    //督查数据先不写 雷老师之后想改成 1call
    @RequestMapping(value = "/showPageDuCha", method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showPageDuCha() {
        String pageDuCha = stringStringMap.get("pageDuCha");
        return null;
    }


    /**
     * 公文数据 按下城区八个街道分
     *
     * @param response
     * @return
     */
    @RequestMapping(value = "/showPageGongWen", method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showPageGongWen(HttpServletResponse response) {
        // response.addHeader("Access-Control-Allow-Origin", "*");
        String pageGongWen = stringStringMap.get("pageGongWen");
        List<GovernmentDepartment> list = pageService.pageGongWen(pageGongWen);
        return JSON.toJSONString(list);
    }


    /**
     * 公文数据 按部门名称分类 值排序显示前五和后五
     * @param
     * @return
     */
    @RequestMapping(value = "/showPageGongWenTwo" , method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showPageGongWenTwo()  {
        String pageGongWen = stringStringMap.get("pageGongWen");

        List<List<GovernmentDepartment>> list = pageService.pageDepartment(pageGongWen);
        return JSON.toJSONString(list);
    }


    /**
     * 公文数据 按月份显示统计数
     * @param response  测试跨域
     * @return
     */
    @RequestMapping(value = "/showPageMonthSum" , method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showPageMonthSum(HttpServletResponse response)  {
        String pageGongWen = stringStringMap.get("pageGongWen");

        String s = pageService.showPageMonthSum(pageGongWen);
        return s;
    }


    /**
     * 公文数据 显示最新一个月的公文数据
     *
     * @return
     */
    @RequestMapping(value = "/showPageMonth", method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showPageMonth(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        String pageGongWen = stringStringMap.get("pageGongWen");
        String s = pageService.showPageMonth(pageGongWen);
        //logger.info(s);
        return s;
    }

}
