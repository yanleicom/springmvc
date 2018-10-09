package com.yanlei.test;

import com.yanlei.util.PropertiesUtil;
import com.yanlei.util.PropertyUtil;
import org.apache.cxf.common.util.PropertyUtils;
import org.junit.Test;

import java.io.*;
import java.util.Iterator;
import java.util.Properties;
/**
 * @Author: x
 * @Date: Created in 14:49 2018/9/17
 */
public class PropertyTest {
    public static void main(String[] args) {
        Properties prop = new Properties();
            try{
                Properties properties = new Properties();
                // 使用ClassLoader加载properties配置文件生成对应的输入流
                InputStream in = PropertyTest.class.getClassLoader().getResourceAsStream("config/config2.properties");
                // 使用properties对象加载输入流
                properties.load(in);
                //获取key对应的value值
                String oneName = properties.getProperty("fourteenName");
                System.out.println(oneName);
            }
               catch(Exception e){
                     System.out.println(e);
                  }
           }



           @Test
            public void getValue(){
               String fourteenName = PropertyUtil.getProperty("pageGongWen");
               System.out.println(fourteenName);

           }


        @Test
    public void writeproperties() throws IOException {
//        PropertyUtil.upDateProperties("pageOne","2018年第二季度政务公开统计");
        PropertyUtil.upDateProperties("pageGongWen","公文数据");
        }

}
