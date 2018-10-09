package com.yanlei.util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Date;
import java.util.Properties;
/**
 * @Author: x
 * @Date: Created in 16:03 2018/4/8
 */
public class PropertyUtil {
    private static final Logger logger = LoggerFactory.getLogger(PropertyUtil.class);

    private static Properties props;

    static{
        loadProps();
    }

    synchronized static private void loadProps(){
        logger.info("开始加载properties文件内容.......");
        props = new Properties();
        InputStream in = null;

        try {
                    //in = PropertyUtil.class.getClassLoader().getResourceAsStream("config.properties");
                    in = PropertyUtil.class.getResourceAsStream("/config/config2.properties");

                    props.load(in);
        } catch (FileNotFoundException e) {
            logger.error("config.properties文件未找到");
        } catch (IOException e) {
            logger.error("出现IOException");
        } finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error("config.properties文件流关闭出现异常");
            }
        }
        logger.info("加载properties文件内容完成...........");
        logger.info("properties文件内容：" + props);
    }

    public static String getProperty(String key){
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key, defaultValue);
    }

    public static void upDateProperties(String key, String defaultValue)  {
        Properties prop = new Properties();
        FileOutputStream fileOutputStream = null;
        try {

            //这儿是获取文件的绝对路径的。　　　　　　　
            fileOutputStream = new FileOutputStream(PropertyUtil.class.getResource("/config/config2.properties").getFile());
            prop.setProperty(key,defaultValue);
            //store方法，第一个参数是输入流，第二个是提示信息。#Sun Jan 07 18:47:58 CST 2018
            prop.store(fileOutputStream, new Date().toString());
            fileOutputStream.flush();
            fileOutputStream.close();

        } catch (IOException e) {
            logger.error("config.properties文件修改异常");
        }
    }
}
