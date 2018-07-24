package com.yanlei.controller.showData;

import com.alibaba.fastjson.JSON;
import com.besjon.po.JsonRootBean;
import com.yanlei.model.GovernmentDepartment;
import com.yanlei.service.showData.PoliticsService;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Author: x
 * @Date: Created in 11:25 2018/4/4
 * 图一数据显示
 * fileURL 读取配置文件的绝对路径 k-v 记载配置文件 读取value 根据value显示参数
 * lastTime,使用人数,2月 查询最新采集时间为二月的使用人数的数据
 * lastTime,公文办件量,sum(avg),3月 查询公文办量的三个月总和(平均)
 * lastTime,使用人数,sum(avg),1月,3月 查询公文办量一月到三月的总和(平均)
 */

@Controller
public class PoliticsController {

    private static final Logger log = LoggerFactory.getLogger(PoliticsController.class);

    public static final String fileURL = "C:\\MultipartFile\\config.properties";

    @Autowired
    private PoliticsService politicsService;


    /**
     * 图一左边数据显示
     * 读取配置文件key 获得 value 根据value查询显示数据
     * 匹配规则
     * fileURL 读取配置文件的绝对路径 k-v 记载配置文件 读取value 根据value显示参数
     * lastTime,使用人数,2月 查询最新采集时间为二月的使用人数的数据
     * lastTime,公文办件量,sum(avg),3月 查询公文办量的三个月总和(平均)
     * lastTime,使用人数,sum(avg),1月,3月 查询公文办量一月到三月的总和(平均)
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/showPoliticsLeft" , method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showPoliticsLeft(HttpServletResponse response) throws IOException {

        try {
            //读取配置文件去查询数据
            Map<String, String> stringStringMap = PropertiesUtil.GetAllProperties(fileURL);
        /*for(Map.Entry<String, String> entry : stringStringMap.entrySet()){
            log.info("键 key ："+entry.getKey()+" 值value ："+entry.getValue());

        }*/
            String oneName = stringStringMap.get("oneName");//使用人数 图一左边开始显示数据
            String twoName = stringStringMap.get("twoName");//活跃人数
            String threeName = stringStringMap.get("threeName");//公文办件量
            String fourName = stringStringMap.get("fourName");//每日平均收件
            String fiveName = stringStringMap.get("fiveName");//平均办件数时长
            String sexName = stringStringMap.get("sexName");//已办结
            String sevenName = stringStringMap.get("sevenName");//未办结

            Collection<String> values = new ArrayList<String>();
            values.add(oneName);
            values.add(twoName);
            values.add(threeName);
            values.add(fourName);
            values.add(fiveName);
            values.add(sexName);
            values.add(sevenName);
            //Collection<String> values = stringStringMap.values();
            List<GovernmentDepartment> governmentDepartmentList = politicsService.findOne(values);

            return JSON.toJSONString(governmentDepartmentList);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("图一左边数据显示数据显示出错了=======>>>>>"+e);
            return "error";
        }

    }



    /**
     *图一右边数据显示修改配置文件
     * 读取配置文件二级类别根据二级类别查询三级类别(分组)
     *JsonRootBean 对获得的参数封装格式返回json
     * @return
     */
    @RequestMapping(value = "/showPoliticsRight" , method = RequestMethod.GET,
            produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String showPoliticsRight() throws IOException {

        Map<String, String> stringStringMap = PropertiesUtil.GetAllProperties(fileURL);
        String eightName = stringStringMap.get("eightName");//分部门办件数
        //查询出三级类别的名称(分组)
        JsonRootBean jsonRootBean = politicsService.findLevel3(eightName);

        if (jsonRootBean!=null){

            return JSON.toJSONString(jsonRootBean);
        }
        //List<GovernmentDepartment> governmentDepartmentList = politicsService.findRight(levelNmae);

        log.info("图一右边数据展示失败");
        return "error";

    }


}
