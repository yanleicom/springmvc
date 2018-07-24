package com.yanlei.service.impl;

import com.yanlei.dao.DepartmentDao;
import com.yanlei.model.*;
import com.yanlei.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    /**
     * 清除原有的5张表中的所有内容
     */
    private void delAll(){
        departmentDao.delDepartmentQjwAll();
        departmentDao.delDepartmentQwbAll();
        departmentDao.delDepartmentRdbAll();
        departmentDao.delDepartmentZfbAll();
        departmentDao.delDepartmentZxAll();
    }

    /**
     * 批量再将Excel里面的数据全部插入到数据库中去
     * @param departmentQjws
     * @param departmentQwbs
     * @param departmentRdbs
     * @param departmentZfbs
     * @param departmentZxs
     */
    @Transactional(rollbackFor = Exception.class)
    public void dealBathAdd(List<DepartmentQjw> departmentQjws, List<DepartmentQwb> departmentQwbs,
                            List<DepartmentRdb> departmentRdbs, List<DepartmentZfb> departmentZfbs,
                            List<DepartmentZx> departmentZxs) {
        delAll();
        departmentDao.addDepartmentQjwList(departmentQjws);
        departmentDao.addDepartmentQwbList(departmentQwbs);
        departmentDao.addDepartmentRdbList(departmentRdbs);
        departmentDao.addDepartmentZfbList(departmentZfbs);
        departmentDao.addDepartmentZxList(departmentZxs);
    }
}
