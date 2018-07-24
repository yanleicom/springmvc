package com.yanlei.util.luxi;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * @Author: x
 * @Date: Created in 10:15 2018/5/22
 */
public class BigDecimalUtil {

    //将人数转成万人,带二位小数点
    public static double BigDecimal(Integer i){
        double f1 = new BigDecimal(i * 0.0001).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }

    //将元转换成亿元
    public static float BigDecimal(float i){
        float f1 = new BigDecimal(i * 0.00000001).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        return f1;
    }

    //将平方米转成万平方米
    public static double BigDecimal(double i){
        double f1 = new BigDecimal(i * 0.0001).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }

    public static  String getPercent(Long num,Long totalPeople ){
        String percent ;
        Double p3 = 0.0;
        if(totalPeople == 0){
            p3 = 0.0;
        }else{
            p3 = num*1.0/totalPeople;
        }
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(2);//控制保留小数点后几位，2：表示保留2位小数点
        percent = nf.format(p3);
        return percent;
    }

    public static  String getPercent(double num,double totalPeople ){
        String percent ;

        double p3 = num/totalPeople;

        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(2);//控制保留小数点后几位，2：表示保留2位小数点
        percent = nf.format(p3);
        return percent;
    }
}
