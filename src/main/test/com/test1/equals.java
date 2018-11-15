package com.test1;

import com.yanlei.util.DateUtil;
import org.junit.Test;

import java.util.Date;

/**
 * @Author: x
 * @Date: Created in 11:32 2018/4/13
 */
public class equals {


    public static void main(String[] args) {
        String[] arr = {"a","b"};
        String str = "a";
        System.out.println(arr[0].equals(str));

    }

    @Test
    public  void test(){
        String s = DateUtil.dateToString(new Date(), "yyyy年MM月");
        System.out.println(s);
    }
}
