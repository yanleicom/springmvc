package com.yanlei.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bamboocloud.util.JsonMethod;

public class NoticeBoardFactory{
    public static void main(String[] args) {
        System.out.println(getNoticeBoard("陈治","2018-04-25"));
    }

    private static String  url = "http://59.202.68.119:8181/completeStatusInfo";

    public static Map<String, List<Map<String, Object>>> getNoticeBoard(String name,String date){
        Map<String, List<Map<String, Object>>> json =  getNoticeBoardHttp(name,date);
		/*if(json.isEmpty()){
			json =  getNoticeBoardHttp("唐锋","2018-04-23");
		}*/
        return json;
    }


    public static Map<String, List<Map<String, Object>>> getNoticeBoardHttp(String name,String date){
        Map<String, List<Map<String, Object>>> jsonData = new  HashMap<String, List<Map<String, Object>>>();
        String Json = "{\"name\":\""+name+"\",\"time\":\""+date+"\"}";
        String data = HttpDataUtil.JsonSMSPost(Json, url);
        Map<String, Object> json = JsonMethod.readValue(data, Map.class);
        List<Map<String, String>> jsonList = (List) json.get("datas");
        if(jsonList!=null){
            for (Map<String, String> map : jsonList) {
                if(map == null || map.get("formTypeName") == null) continue;
                List<Map<String, Object>> addListMap = new ArrayList<Map<String,Object>>();
                Map<String, Object> addMap = new HashMap<String, Object>();
                if(jsonData.containsKey(map.get("formTypeName"))){
                    addListMap = jsonData.get(map.get("formTypeName"));
                    addMap.put("year", map.get("year"));
                    addMap.put("taskDetails", map.get("taskDetails"));
                    addMap.put("status", map.get("status"));
                    addListMap.add(addMap);
                    jsonData.put(map.get("formTypeName"), addListMap);
                    continue;
                }
                addMap.put("year", map.get("year"));
                addMap.put("taskDetails", map.get("taskDetails"));
                addMap.put("status", map.get("status"));
                addListMap.add(addMap);
                jsonData.put(map.get("formTypeName"), addListMap);
            }
        }else{

        }
		/*for (Map<String, String> map : jsonList) {
			System.out.println(map);
		}*/
        //System.out.println("查到了 jsonData ："+jsonData);
        return jsonData;
    }


}
