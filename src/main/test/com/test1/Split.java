package com.test1;

import org.junit.Test;

/**
 * @Author: x
 * @Date: Created in 10:56 2018/4/11
 */
public class Split {

    @Test
    public void test1(){
        String s = "lastTiime,使用人数,12,sum";
        String[] split = s.split(",");
        System.out.println(split.length);
        for (String s1 : split) {
            System.out.println(s1);

        }

    }
}
