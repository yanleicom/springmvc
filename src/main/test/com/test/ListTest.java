package com.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: x
 * @Date: Created in 15:28 2018/3/1
 */
public class ListTest {
    String s = "[\"部门1\",\"部门2\",\"部门3\",\"部门4\",\"部门5\",\"部门6\",\"部门7\",\"部门8\",\"部门9\",\"部门10\"]";

    public static void main(String[] args) {
        String s = "[\"部门1\",\"部门2\",\"部门3\",\"部门4\",\"部门5\",\"部门6\"" +
                ",\"部门7\",\"部门8\",\"部门9\",\"部门10\"]";
        List<String> fbmsjVo1s = new ArrayList<String>();
        fbmsjVo1s =  JSON.parseArray(s,String.class);
        for (String r: fbmsjVo1s) {
            System.out.println(r);
        }
        System.out.println(fbmsjVo1s);
    }


    @Test
    public void testMethod(){
        long we = 234L;
        Double ddd = new Double(we);
        System.out.println(ddd);
    }

}
