package com.yanlei.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;

/**
 * @Author: x
 * @Date: Created in 16:49 2018/9/12
 */
public class BirthdayToAge {

    public static int getAgefromBirthTime(String birthTimeString){
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
}
