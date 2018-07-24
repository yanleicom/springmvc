package com.yanlei.test;

import com.yanlei.util.DateUtil;
import com.yanlei.util.PropertiesUtil;
import org.junit.Test;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.yanlei.controller.showData.PoliticsController.fileURL;

/**
 * @Author: x
 * @Date: Created in 11:20 2018/5/10
 */
public class WriteMap {

    @Test
    public void test3(){

        try {
            Map<String,String> map = new HashMap<String, String>();

          /*  map.put("pageOne","2018年第一季度政务公开统计");
            map.put("pageTwo","2018年公文办理数据");*/
           // map.put("pageDuCha","督查数据");
           // map.put("pageGongWen","公文数据");
            map.put("pageOne","2018年第二季度政务公开统计");

            PropertiesUtil.WritePropertiesAll(fileURL,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4() {
        /*double d = 3.1415926;
        String result = String.format("%.2f", d);
        System.out.println(result);
        System.out.println(new DecimalFormat("#.00").format(3.2415926D));

        System.out.println(Math.round(0.52644555 * 10000) * 0.0001d);*/
        String currentYear = DateUtil.getCurrentYear();
        try {
            Date date = DateUtil.stringToDate(currentYear, "yyyy");
            System.out.println(date);
        } catch (ParseException e) {

        }
    }


}
