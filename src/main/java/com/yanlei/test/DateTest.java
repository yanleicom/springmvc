package com.yanlei.test;

import com.yanlei.util.DateUtil;
import com.yanlei.util.luxi.BigDecimalUtil;
import org.junit.Test;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: x
 * @Date: Created in 10:41 2018/5/15
 */
public class DateTest {

    @Test
    public void test() throws ParseException {
        //date转成long
        long l = DateUtil.dateToLong(new Date());
        System.out.println(l);
        //date转成String
        String s = DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        System.out.println(s);
        //String转成date
        Date date = DateUtil.stringToDate("2018-05-15 10:44:15", "yyyy-MM-dd HH:mm:ss");
        System.out.println(date);
        //long转成date
        Date date1 = DateUtil.longToDate(1526352387379L, "yyyy-MM-dd HH:mm:ss");
        System.out.println(date1);
        //long转成String
        String s1 = DateUtil.longToString(1526352387379L, "yyyy-MM-dd HH:mm:ss");
        System.out.println(s1);
        //String转成Long
        long l1 = DateUtil.stringToLong("2018-05-15 10:46:27", "yyyy-MM-dd HH:mm:ss");
        System.out.println(l1);

        Date date11 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
        System.out.println(sdf.format(date11));

    }

    @Test
    public void test1(){
        //当前时间戳加一秒测试
        Date date = new Date();
        long time = date.getTime();
        Date date1 = DateUtil.addOneSecond(new Date());
        long time1 = date1.getTime();
        System.out.println(time);
        System.out.println(time1);
    }

    /*@Test
    public void test2(){
        String percent = BigDecimalUtil.getPercent(1, 3);
        System.out.println(percent);
    }*/

}
