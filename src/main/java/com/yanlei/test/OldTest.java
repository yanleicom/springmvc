package com.yanlei.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: x
 * @Date: Created in 15:21 2018/9/12
 */
public class OldTest {

    //15位和18位身份证信息计算出生日
    public static void main(String[] sg0) {
        String s = "330107194702130323";
        int leh = s.length();
        System.out.println(s.length());
        if (leh != 18 && leh != 15) {
            System.out.println("NO");
        }
        else {
            if (leh == 18) {
                int se = Integer.valueOf(s.substring(leh - 1)) % 2;
                String dates = s.substring(6, 10) + "-" + s.substring(10, 12) + "-" + s.substring(12, 14);
                System.out.println(dates);
                String sex = "";
                if (leh == 0) {
                    sex = "M";
                }
                else {
                    sex = "F";
                }
                System.out.println(sex + "\t" + dates);
            }
            else {
                String dates = "19" + s.substring(6, 8) + "-" + s.substring(8, 10) + "-" + s.substring(10, 12);
                System.out.println(dates);
            }
        }
    }


   // birthDate = idCard.substring(6,10)+"-"+idCard.substring(10,12)+"-"+idCard.substring(12,14)
   /* public static int getAgefromBirthTime(String birthTimeString){
       // String birthDate = birthTimeString.substring(6,10)+"-"+birthTimeString.substring(10,12)+"-"+birthTimeString.substring(12,14);
        // 先截取到字符串中的年、月、日
        if (StringUtils.isNotBlank(birthTimeString)) {
            String strs[] = birthTimeString.trim().split("-");
            int selectYear = Integer.parseInt(strs[0]);
            int selectMonth = Integer.parseInt(strs[1]);
            int selectDay = Integer.parseInt(strs[2]);
            // 得到当前时间的年、月、日
            Calendar cal = Calendar.getInstance();
            int yearNow = cal.get(Calendar.YEAR);
            int monthNow = cal.get(Calendar.MONTH) + 1; //美国时间 +1
            int dayNow = cal.get(Calendar.DATE);
            // 用当前年月日减去生日年月日
            int yearMinus = yearNow - selectYear;
            int monthMinus = monthNow - selectMonth;
            int dayMinus = dayNow - selectDay;
            int age = yearMinus;
            if (yearMinus < 0) {// 选了未来的年份
                age = 0;
            } else if (yearMinus == 0) {// 同年的，要么为1，要么为0
                if (monthMinus < 0) {// 选了未来的月份
                    age = 0;
                } else if (monthMinus == 0) {// 同月份的
                    if (dayMinus < 0) {// 选了未来的日期
                        age = 0;
                    } else if (dayMinus >= 0) {
                        age = 1;
                    }
                } else if (monthMinus > 0) {
                    age = 1;
                }
            } else if (yearMinus > 0) {
                if (monthMinus < 0) {// 当前月>生日月
                } else if (monthMinus == 0) {// 同月份的，再根据日期计算年龄
                    if (dayMinus < 0) {
                    } else if (dayMinus >= 0) {
                        age = age + 1;
                    }
                } else if (monthMinus > 0) {
                    age = age + 1;
                }
            }
            return age;
        }
        return -1;
    }

    @Test  //可用 有字符也可用 计算按年月日计算 比较准确
    public void test1(){
        //String idCard = "33010719450705031X";
       // String birthDate = idCard.substring(6,10)+"-"+idCard.substring(10,12)+"-"+idCard.substring(12,14);
        int agefromBirthTime = OldTest.getAgefromBirthTime("");
        System.out.println(agefromBirthTime);
    }*/

    //这个方法可用 但是根据身份证计算年龄 不通过月份和日期 计算不准确!
    public static int IdNOToAge(String IdNO){
        int leh = IdNO.length();
        String dates="";
        if (leh == 18) {
//            int se = Integer.valueOf(IdNO.substring(leh - 1)) % 2; 注意这里如果有字符的话计算性别会出错
            dates = IdNO.substring(6, 10);
            SimpleDateFormat df = new SimpleDateFormat("yyyy");
            String year=df.format(new Date());
            int u=Integer.parseInt(year)-Integer.parseInt(dates);
            return u;
        }else{
            dates = IdNO.substring(6, 8);
            return Integer.parseInt(dates);
        }
    }

    @Test
    public void Test2(){
        int i = IdNOToAge("33010719450705031X");
        System.out.println(i);
    }
}
