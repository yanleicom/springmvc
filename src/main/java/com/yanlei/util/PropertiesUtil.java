package com.yanlei.util;

import java.io.*;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
/**
 * 键值对文件
 * @ClassName PropertiesUtil.java
 * @Description:x
 * @author QDK
 * @date 2017年12月25日下午3:07:52
 *
 */
public class PropertiesUtil {
	/**
	 * 根据Key读取Value
	 * @Description: x
	 * @param @param filePath 路径
	 * @param @param key 键
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author QDK
	 * @date 2017年12月25日
	 */
	      public static String GetValueByKey(String filePath, String key) {
	          Properties pps = new Properties();
	          try {
	              InputStream in = new BufferedInputStream(new FileInputStream(filePath));  
	              pps.load(in);
	              String value = pps.getProperty(key);
	             return value;
	             
	         }catch (IOException e) {
	             e.printStackTrace();
	             return null;
	         }
	     }
	     
	      /**
	       * 读取Properties的全部信息
	       * @Description: x
	       * @param @param f
	       * @param @throws IOException   ilePath 路径
	       * @return void  
	       * @throws
	       * @author QDK
	       * @date 2017年12月25日
	       */
	     public static Map<String, String> GetAllProperties(String filePath) throws IOException {
	         Properties pps = new Properties();
	         Map<String, String> mapper = new HashMap<String, String>();

	       /*  InputStream in = new BufferedInputStream(new FileInputStream(filePath));
			 pps.load(in);*/

			 BufferedReader br = new BufferedReader( new InputStreamReader(new FileInputStream(filePath),"UTF-8"));
			 pps.load(br);

	         Enumeration en = pps.propertyNames(); //得到配置文件的名字
	         
	         while(en.hasMoreElements()) {
	             String strKey = (String) en.nextElement();
	             String strValue = pps.getProperty(strKey);
	             mapper.put(strKey, strValue);
	         }
	         return mapper;
	     }
	     
	     /**
	      * 写入Properties信息
	      * @Description: x
	      * @param @param filePath 路径
	      * @param @param pKey
	      * @param @param pValue
	      * @param @throws IOException   
	      * @return void  
	      * @throws
	      * @author QDK
	      * @date 2017年12月25日
	      */
	     public static void WriteProperties (String filePath, String pKey, String pValue) throws IOException {
	         Properties pps = new Properties();
	         
	         InputStream in = new FileInputStream(filePath);
	         //从输入流中读取属性列表（键和元素对） 
	         pps.load(in);
	         //调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。  
	         //强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
	         OutputStream out = new FileOutputStream(filePath);
             pps.setProperty(pKey, pValue);
	         //以适合使用 load 方法加载到 Properties 表中的格式，  
	         //将此 Properties 表中的属性列表（键和元素对）写入输出流  
	         pps.store(out, "Update " + pKey + " name");
	     }


    public static void WritePropertiesAll (String filePath, Map<String,String> mapper) throws IOException {
        Properties pps = new Properties();

        InputStream in = new FileInputStream(filePath);
        //从输入流中读取属性列表（键和元素对）
        pps.load(in);
        //调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
        //强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
        OutputStream out = new FileOutputStream(filePath);
        for (String s : mapper.keySet()) {
            pps.setProperty(s, mapper.get(s).toString());

        }
        //添加多个
        //以适合使用 load 方法加载到 Properties 表中的格式，
        //将此 Properties 表中的属性列表（键和元素对）写入输出流 表头
        pps.store(out, "Update ");
    }


    /**
     * 根据value获得key
     * @param map
     * @param value
     * @return
     */
    public static String getKey(Map<String,String> map,String value){
        String key="";
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if(value.equals(entry.getValue())){
                key=entry.getKey();
            }
        }
        return key;
    }

}

