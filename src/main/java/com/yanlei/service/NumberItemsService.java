package com.yanlei.service;

import com.yanlei.model.*;

import java.util.List;

/**
 * @Author: x
 * @Date: Created in 15:25 2018/3/6
 */
public interface NumberItemsService {
    Zdpyc findZdpyc();

    List<ZdpycYear> findZdpycYear();

    List<ZdpycYear> findZdpycMonth();

    List<ZdpycYear> findZdpycDay();

    List<Computer> findComputer();

    List<Qwb> findDepartmentQwb();

    List<Zfb> findDepartmentZfb();

    List<Rlb> findDepartmentRdb();

    List<Qjw> findDepartmentQjw();

    List<Zx> findDepartmentZx();

    int updateZdpyc(Zdpyc zdpyc);

    void deleteAllYear(List<ZdpycYear> zdpycYears);

    void delAllComputers(List<Computer> computers);

}
