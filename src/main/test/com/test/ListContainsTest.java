package com.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: x
 * @Date: Created in 11:36 2018/3/30
 */
public class ListContainsTest {

    @Test
    public void Test(){
        List<String> Str =new ArrayList<String>();
        Str.add("1");
        Str.add("2");
        Str.add("3");
        Str.add("4");
        List<String> Str1 =new ArrayList<String>();
        Str1.add("1");
        Str1.add("2");
        Str1.add("3");
        System.out.println(Str.contains(Str1));


    }



}
