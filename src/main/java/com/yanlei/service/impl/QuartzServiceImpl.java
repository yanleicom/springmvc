package com.yanlei.service.impl;

import com.yanlei.dao.QuartzDao;
import com.yanlei.model.BusinessData;
import com.yanlei.model.GovernmentDepartment;
import com.yanlei.service.QuartzService;
import com.yanlei.util.Matching;
import com.yanlei.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.Bus;
import org.apache.xmlbeans.impl.regex.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Author: x
 * @Date: Created in 15:45 2018/3/28
 */

@Service
public class QuartzServiceImpl implements QuartzService {

    @Autowired
    private QuartzDao quartzDao;

   /* //查询采集时间和departmentID
    @Transactional(rollbackFor = Exception.class)
    public Result findDepartmentIdAndAcquisitiontime() {
        //查询小表中的ID 采集时间 数据项名称
        List<GovernmentDepartment> idAndTimeAndName = quartzDao.findDepartmentIdAndAcquisitiontime();
        //查询小表的数据个数
        // int count = quartzDao.findCount();
        //int num = 0;
        //不为空 根据ID分组,查询出每组的最新采集时间
        if (idAndTimeAndName != null && idAndTimeAndName.size() > 0) {
            for (GovernmentDepartment businessData : idAndTimeAndName) {
                if (businessData.getDepartmentid() != null && businessData.getAcquisitiontime() != null && businessData.getDataname() != null) {
                    //根据id和采集时间作为条件去查询大表有没有相同ID并且采集时间大于最新时间的数据
                    // System.out.println(businessData.getAcquisitiontime());
                    //List<BusinessData> IdAndTimeList
                     //       = quartzDao.findIdAndTimeList(businessData.getDepartmentid(), businessData.getAcquisitiontime(), businessData.getDataname());

                    //查询大表最新采集时间根据数据名称分组
                    List<BusinessData> IdAndTimeList = quartzDao.findGroupByName();

                    if (IdAndTimeList != null && IdAndTimeList.size() > 0) {
                        //直接删除小表的内容
                        quartzDao.delAll();
                        //根据iD插入对应的小表中
                        for (BusinessData data : IdAndTimeList) {
                            if (data.getDepartmentid().equals(Matching.DeaprtmentID.XQC_FBMBJS_QWB.getValue())
                                    || data.getDepartmentid().equals(Matching.DeaprtmentID.XQC_FBMBJS_ZFB.getValue())
                                    || data.getDepartmentid().equals(Matching.DeaprtmentID.XQC_FBMBJS_RDB.getValue())
                                    || data.getDepartmentid().equals(Matching.DeaprtmentID.XQC_FBMBJS_QJW.getValue())
                                    || data.getDepartmentid().equals(Matching.DeaprtmentID.XQC_FBMBJS_ZX.getValue())
                                    ) {

                                quartzDao.saveGovernmentDepartment(data);
                            }
                        }
                        return Result.ok();
                    }

                }
            }
            // num = quartzDao.findCount();
        } else {
           *//* //小表没有数据直接查询大表根据ID和采集时间匹配插入小表中
            List<BusinessData> businessDataList = quartzDao.findBusinessData();*//*
            //查询大表采集时间根据数据名称排序
            List<BusinessData> businessDataList = quartzDao.findGroupByName();

            for (BusinessData businessData : businessDataList) {
                if (businessData.getDepartmentid() != null && businessData.getName() != null) {
                    if (businessData.getDepartmentid().equals(Matching.DeaprtmentID.XQC_FBMBJS_QWB.getValue())
                            || businessData.getDepartmentid().equals(Matching.DeaprtmentID.XQC_FBMBJS_ZFB.getValue())
                            || businessData.getDepartmentid().equals(Matching.DeaprtmentID.XQC_FBMBJS_RDB.getValue())
                            || businessData.getDepartmentid().equals(Matching.DeaprtmentID.XQC_FBMBJS_QJW.getValue())
                            || businessData.getDepartmentid().equals(Matching.DeaprtmentID.XQC_FBMBJS_ZX.getValue())
                            ) {
                        quartzDao.saveGovernmentDepartment(businessData);

                    }
                }

            }
            //return Result.ok();
        }
        *//*if (count == num){  //会出现一个问题,根据iD分组完,名称不同,统计类型不同会出现采集时间小于我查询的时间 不做插入操作
            return Result.ok("定时任务执行,但是没有最新采集数据");
        }*//*
        return Result.ok();
    }
}*/
    @Transactional(rollbackFor = Exception.class)
    public Result findDepartmentIdAndAcquisitiontime() {

        //查询business_data内容中 areaid=1 acquisitiontime=max 按name,cycle分组
        List<BusinessData> businessDataList = quartzDao.findBusinessDataforOne(Matching.DeaprtmentID.ZWSJ.getValue());
        if (businessDataList != null && businessDataList.size() > 0){
            //查询对应areaid=1的小表的所有数据
            //List<GovernmentDepartment> governmentDepartmentList = quartzDao.findAreaIDforOne();
                //if (governmentDepartmentList!=null && governmentDepartmentList.size()>0){
                    quartzDao.delAll();
                    int num = quartzDao.saveGovernmentDepartments(businessDataList);
                    if (num>0){
                        return Result.ok("定时任务执行将areaid=1对应的数据插入小表中,插入数据条数:"+num);
                    }
                    return Result.error();
                }
                return Result.error("表中无数据");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result saveGovernmentSociety() {
        //查询business_data内容中 areaid=2 acquisitiontime=max 按name,cycle分组
        List<BusinessData> businessDataList = quartzDao.findBusinessDataforOne(Matching.DeaprtmentID.SHSJ.getValue());
        if (businessDataList != null && businessDataList.size() > 0) {
            //删除小表内容,将所有查询得到的数据插入社会表
            quartzDao.delAllTwo();
            int num = quartzDao.saveGovernmentSociety(businessDataList);
            if (num > 0) {
                return Result.ok("定时任务执行将areaid=2对应的数据插入小表中,插入数据条数:" + num);
            }
            return Result.error();
        }
        return Result.error("表中无数据");
    }

    @Override
    public Result saveGovernmentCulture() {
        //查询business_data内容中 areaid=3 acquisitiontime=max 按name,cycle分组
        List<BusinessData> businessDataList = quartzDao.findBusinessDataforOne(Matching.DeaprtmentID.WHSJ.getValue());
        if (businessDataList != null && businessDataList.size() > 0) {
            //删除小表内容,将所有查询得到的数据插入社会表
            quartzDao.delAllThree();
            int num = quartzDao.saveGovernmentCulture(businessDataList);
            if (num > 0) {
                return Result.ok("定时任务执行将areaid=3对应的数据插入小表中,插入数据条数:" + num);
            }
            return Result.error();
        }
        return Result.error("表中无数据");
    }

    @Override
    public Result saveGovernmentEconomic() {
        //查询business_data内容中 areaid=4 acquisitiontime=max 按name,cycle分组
        List<BusinessData> businessDataList = quartzDao.findBusinessDataforOne(Matching.DeaprtmentID.JJSJ.getValue());
        if (businessDataList != null && businessDataList.size() > 0) {
            //删除小表内容,将所有查询得到的数据插入社会表
            quartzDao.delAllFour();
            int num = quartzDao.saveGovernmentEconomic(businessDataList);
            if (num > 0) {
                return Result.ok("定时任务执行将areaid=4对应的数据插入小表中,插入数据条数:" + num);
            }
            return Result.error();
        }
        return Result.error("表中无数据");
    }
}

