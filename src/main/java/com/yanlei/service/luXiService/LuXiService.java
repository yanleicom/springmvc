package com.yanlei.service.luXiService;

import com.yanlei.util.Result;

import java.util.Date;

/**
 * @Author: x
 * @Date: Created in 16:22 2018/5/11
 */
public interface LuXiService {
    Result insertPage(String s1);

    Result insertPageGongWen(String s1);

    Date findMaxTime(String pageGongWen);

    Integer insertEvent(String s);

    Long findMaxTongId();

}
