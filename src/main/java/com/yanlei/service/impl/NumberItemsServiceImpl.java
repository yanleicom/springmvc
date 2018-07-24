package com.yanlei.service.impl;

import com.yanlei.dao.NumberItemsDao;
import com.yanlei.model.*;
import com.yanlei.service.NumberItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: x
 * @Date: Created in 15:26 2018/3/6
 */

@Service
@Transactional
public class NumberItemsServiceImpl implements NumberItemsService {

    @Autowired
    private NumberItemsDao numberItemsDao;

    public Zdpyc findZdpyc() {
        return numberItemsDao.findZdpyc();

    }


    /**
    * @Author: x
    * @Date: Created in 16:31 2018/3/6
     * 分受理区域办件数年月日数据的展示
    */

    public List<ZdpycYear> findZdpycYear() {
        return numberItemsDao.findZdpycYear();
    }

    public List<ZdpycYear> findZdpycMonth() {
        return numberItemsDao.findZdpycMonth();
    }

    public List<ZdpycYear> findZdpycDay() {
        return numberItemsDao.findZdpycDay();
    }

    public List<Computer> findComputer() {
        return numberItemsDao.findComputer();
    }


    /**
    * @Author: x
    * @Date: Created in 10:03 2018/3/7
     * 第四图分部门办件数
    */
    public List<Qwb> findDepartmentQwb() {
        return numberItemsDao.findDepartmentQwb();
    }

    public List<Zfb> findDepartmentZfb() {
        return numberItemsDao.findDepartmentZfb();
    }

    public List<Rlb> findDepartmentRdb() {
        return numberItemsDao.findDepartmentRdb();
    }

    public List<Qjw> findDepartmentQjw() {
        return numberItemsDao.findDepartmentQjw();
    }

    public List<Zx> findDepartmentZx() {
        return numberItemsDao.findDepartmentZx();
    }

    public int updateZdpyc(Zdpyc zdpyc) {
        return numberItemsDao.updateZdpyc(zdpyc);

    }

    public void deleteAllYear(List<ZdpycYear> zdpycYears) {
        numberItemsDao.deleteAllYear();
        addYear(zdpycYears);
    }

    public void delAllComputers(List<Computer> computers) {
        numberItemsDao.delAllComputers();
        addComputers(computers);
    }

    private void addComputers(List<Computer> computers) {
        numberItemsDao.addComputers(computers);
    }

    private void addYear(List<ZdpycYear> zdpycYears) {
        numberItemsDao.addYear(zdpycYears);
    }

}
