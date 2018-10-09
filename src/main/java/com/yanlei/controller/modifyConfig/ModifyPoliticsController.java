package com.yanlei.controller.modifyConfig;

import com.alibaba.fastjson.JSONArray;
import com.yanlei.model.GovernmentDepartment;
import com.yanlei.service.modifyConfigService.ModifyPoliticsService;
import com.yanlei.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

//import static com.yanlei.controller.showData.PoliticsController.fileURL;

/**
 * @Author: x
 * @Date: Created in 11:31 2018/4/16
 */
@Controller
public class ModifyPoliticsController {

    private static final Logger log = LoggerFactory.getLogger(ModifyPoliticsController.class);

    @Autowired
    private ModifyPoliticsService modifyPoliticsService;


    /**
     * 图一左边数据显示修改(规则配置) 修改配置文件 中的value 根据V 查k 根据K修改V
     * 接受修改规则 写入配置文件
     * 读取配置文件 按写入规则匹配显示数据回显
     *
     */

    /*@RequestMapping(value = "/updataConfig" , method = RequestMethod.POST,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showData(String value,String upValue) {
        try {
            //读取配置文件参数
            Map<String, String> stringStringMap = PropertiesUtil.GetAllProperties(fileURL);
            //根据value获得key
            String key = PropertiesUtil.getKey(stringStringMap, value);
            //根据key修改value参数,将用户修改的value修改到配置文件中
            PropertiesUtil.WriteProperties(fileURL, key, upValue);
            //再次读取配置文件内容
            Map<String, String> stringMap = PropertiesUtil.GetAllProperties(fileURL);
            log.info("修改后的配置文件" + stringMap);
            return "success";

        } catch (Exception e) {
            e.printStackTrace();
            log.error("图一配置文件修改失败=======>>>>>" + e);
            return "error";
        }

    }*/


    /**
     * 传对象 根据ID修改字段
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateDepartmentOne", method = RequestMethod.POST,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public Result updateDepartment(HttpServletResponse response, HttpServletRequest request) {

        String department = request.getParameter("department");
        List<GovernmentDepartment> departments = JSONArray.parseArray(department, GovernmentDepartment.class);
        /*List<GovernmentDepartment> departments =new ArrayList<GovernmentDepartment>();
        GovernmentDepartment governmentDepartment = new GovernmentDepartment();
        governmentDepartment.setId(777);
        governmentDepartment.setIntValue(46L);

        GovernmentDepartment governmentDepartment1 = new GovernmentDepartment();
        governmentDepartment1.setId(789);
        governmentDepartment1.setIntValue(2222L);

        departments.add(governmentDepartment);
        departments.add(governmentDepartment1);*/

        Result result = modifyPoliticsService.updateDepartment(departments);

        return result;

    }


    /**
     *
     * @param response
     * @param request
     * @param id 根据ID删除
     * @return
     */
    @RequestMapping(value = "/delDepartmentOne", method = RequestMethod.POST,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public Result delDepartmentOne(HttpServletResponse response, HttpServletRequest request,Integer id) {

        Result result = modifyPoliticsService.delDepartmentOne(id);
        return result;
    }
}
