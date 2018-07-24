package com.yanlei.dao.luXiDao;

import com.yanlei.model.GovernmentDepartment;
import com.yanlei.model.luxi.Event;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List; /**
 * @Author: x
 * @Date: Created in 17:19 2018/5/11
 */

@Repository("LuXiDao")
public interface LuXiDao {

    int insertPage(List<GovernmentDepartment> list);

    int insertPageGongWen(List<GovernmentDepartment> list);

    Date findMaxTime(String pageGongWen);

    Integer insertEvent(List<Event> event);

    Long findMaxTongId();
}
