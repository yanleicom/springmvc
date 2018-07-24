package com.yanlei.service;

import com.yanlei.model.*;

import java.util.List; /**
 * @Author: x
 * @Date: Created in 10:42 2018/3/5
 */
public interface LegalPersonService {

    void dealBathAdd(List<SSRegion> ssRegions, List<SSWorkman> ssWorkmens,
                     List<SSIndustry> ssIndustries, List<SSMoney> ssMonies, List<SSRevenue> ssRevenues);




}
