package com.yanlei.test;

import com.yanlei.controller.PeopleController;
import com.yanlei.util.PropertiesUtil;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.yanlei.controller.showData.PoliticsController.fileURL;

/**
 * @Author: xiayuanlei
 * @Date: 2018/1/29 18:49
 */
public class RSTest {
    private static final Logger log = LoggerFactory.getLogger(PeopleController.class);
    //private static String fileURL = "C:\\MultipartFile\\config.properties";

    public static void main(String[] args) {
        String s = WebClient.create("http://localhost:8081/services/webService/peopleFile")
                .accept(MediaType.APPLICATION_JSON).get(String.class);//查询操作
        log.info("有没有定西啊"+s);

    }

    @Test
    public void test1(){

        try {
            PropertiesUtil.WriteProperties(fileURL,"cccc","我是中文的");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        try {
            Map<String, String> stringStringMap = PropertiesUtil.GetAllProperties(fileURL);
            for(Map.Entry<String, String> entry : stringStringMap.entrySet()){
            log.info("键 key ："+entry.getKey()+" 值value ："+new String(entry.getValue()));
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3(){

        try {
            Map<String,String> map = new HashMap<String, String>();
            /*map.put("oneName","使用人数");
            map.put("twoName","活跃人数");
            map.put("threeName","公文办件量");
            map.put("fourName","每日平均收件");
            map.put("fiveName","平均办件数时长");*/
           /* map.put("sexName","已办结");
            map.put("sevenName","未办结");*/
            //map.put("eightName","分部门办件数");  //lastTime,name,sum(avg),x月(显示几月数据),(x月,显示几月到几月数据)
            //map.put("threeName","lastTime,公文办件量,sum,1月,3月");
           // map.put("TenName","床位使用情况");
           // map.put("elevenName","总服务事件");
            //map.put("twelveName","分区域事件数");
            //map.put("thirteenName","企业服务事件");
            //map.put("fourteenName","分类型事件数");
            //map.put("fifteenName","分部门事件数");
           // map.put("sixteenName","分区域事件数");
           // map.put("seventeenName","网上办件总数");
            //map.put("eighteenName","分类型事件数");
            //map.put("nineteenName","分部门办件数");

          /*  map.put("jingJiOne","2018年2月主要完成指标情况");
            map.put("jingJiTwo","2018年2月街道经济");
            map.put("jingJiThree","2018年2月财务收入");*/
            map.put("pageOne","2018年第一季度政务公开统计");
            map.put("pageTwo","2018年公文办理数据");
            //map.put("pageThree","各月份办件数");
            PropertiesUtil.WritePropertiesAll(fileURL,map);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
