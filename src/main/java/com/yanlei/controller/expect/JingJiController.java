package com.yanlei.controller.expect;

import com.alibaba.fastjson.JSON;
import com.besjon.pojo.JsonToJingji;
import com.yanlei.model.GovernmentDepartment;
import com.yanlei.service.showData.JingJiService;
import com.yanlei.util.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.yanlei.controller.showData.PoliticsController.fileURL;

/**
 * @Author: x 经济左边数据展示
 * @Date: Created in 14:32 2018/5/7
 */

@Controller
public class JingJiController {

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
    private JingJiService jingJiService;


    /**
     * jingji 经济页面 展示左边饼图数据
     * @param response
     * @return
     * @throws IOException
     * 2018年2月主要完成指标情况（计量单位：亿元）
     */
    @RequestMapping(value = "/showJingJiLeft" , method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showPageLeft(HttpServletResponse response) throws IOException {
        //response.addHeader("Access-Control-Allow-Origin", "*");
        //Map<String, String> stringStringMap = PropertiesUtil.GetAllProperties(fileURL);
        String pageOne = stringStringMap.get("jingJiOne");///2018年2月主要完成指标情况

        List<GovernmentDepartment> PageLists = jingJiService.findPageOne(pageOne);

        return JSON.toJSONString(PageLists);
    }




    /**
     * page1 经济页面 展示右边柱状图数据
     * @param response
     * @return
     * @throws IOException
     * 经济数据 八个街道数据展示 2018年2月街道经济
     */
    @RequestMapping(value = "/showJingJiRightOne" , method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showPageRightOne(HttpServletResponse response) throws IOException {
        //response.addHeader("Access-Control-Allow-Origin", "*");

        String pageTwo = stringStringMap.get("jingJiTwo");//2018年2月街道经济

        //List<GovernmentDepartment> PageLists = jingJiService.findPageTwo(pageTwo);

        JsonToJingji jsonToJingji = jingJiService.findPageTwo(pageTwo);
        return JSON.toJSONString(jsonToJingji);
    }



    @RequestMapping(value = "/showJingJiRightTwo" , method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showPageRightTwo(HttpServletResponse response) throws IOException {
        //response.addHeader("Access-Control-Allow-Origin", "*");

        String pageThree = stringStringMap.get("jingJiThree");//2018年2月财务收入

        JsonToJingji jsonToJingji = jingJiService.findPageThree(pageThree);
        return JSON.toJSONString(jsonToJingji);
    }


    /**
     * 修改配置文件 根据Vlaue 查询 key 修改 value为 updateValue
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/updateProperties" , method = RequestMethod.POST,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String updateProperties(HttpServletResponse response, HttpServletRequest request) throws IOException {
       // response.addHeader("Access-Control-Allow-Origin", "*");
        String valeu = request.getParameter("value");
        String updateValeu = request.getParameter("updateValue");
        if (StringUtils.isNotBlank(valeu) && StringUtils.isNotBlank(updateValeu)){
            Map<String, String> stringStringMap = PropertiesUtil.GetAllProperties(fileURL);
            String key = PropertiesUtil.getKey(stringStringMap,valeu);
            if (StringUtils.isNotBlank(key)){
                stringStringMap.put(key,updateValeu);
                PropertiesUtil.WritePropertiesAll(fileURL,stringStringMap);
                return "success";
            }else {
                return "error";
            }
        }
        return "error";
    }
}

