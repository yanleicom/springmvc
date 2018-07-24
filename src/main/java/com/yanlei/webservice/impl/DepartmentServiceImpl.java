package com.yanlei.webservice.impl;

import com.yanlei.cxfmodel.CXFDepartment;
import com.yanlei.dao.webdao.CXFDepartmentDao;
import com.yanlei.util.ResponseDTO;
import com.yanlei.webservice.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: x
 * @Date: Created in 10:57 2018/3/26
 */

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private CXFDepartmentDao cxfDepartmentDao;

    //查询全部
    public ResponseDTO getDepartments() {
        ResponseDTO responseDTO = new ResponseDTO();
        List<CXFDepartment> cxfDepartments = cxfDepartmentDao.getDepartments();
        if (cxfDepartments != null && cxfDepartments.size() > 0){
            responseDTO.setData(cxfDepartments);
            return responseDTO;
        }
        return responseDTO.error();
    }

    //根据名称查询
    public ResponseDTO getDepartmentByName(String name) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (name != null){
            CXFDepartment cxfDepartment = cxfDepartmentDao.getDepartmentByName(name);
            if (cxfDepartment != null){
                ResponseDTO<CXFDepartment> responDTO = new ResponseDTO<CXFDepartment>(cxfDepartment);
                return responDTO;
            }
        }
        return responseDTO.error();
    }

    //根据id查询
    public ResponseDTO getDepartmentById(Integer id) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (id != null){
            CXFDepartment cxfDepartment = cxfDepartmentDao.getDepartmentById(id);
            if (cxfDepartment != null){
                ResponseDTO<CXFDepartment> responDTO = new ResponseDTO<CXFDepartment>(cxfDepartment);
                return responDTO;
            }
        }
        return responseDTO.error();
    }

    //单个增加
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO saveDepartment(CXFDepartment cxfDepartment) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (cxfDepartment != null){
            cxfDepartmentDao.saveDepartment(cxfDepartment);
            if (cxfDepartment.getId() != null){
                responseDTO.setData(cxfDepartment);
                return responseDTO;
            }
        }
        return responseDTO.error();
    }

    //批量增加
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO saveCXFDepartments(List<CXFDepartment> cxfDepartments) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (cxfDepartments != null && cxfDepartments.size()>0){
            Integer i = cxfDepartmentDao.saveCXFDepartments(cxfDepartments);
            if (i>0)
                return new ResponseDTO(i);
        }
        return responseDTO.error();
    }

    //单个修改
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO updateCXFDepartment(CXFDepartment cxfDepartment) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (cxfDepartment != null && cxfDepartment.getId() != null){
            Integer i = cxfDepartmentDao.updateCXFDepartment(cxfDepartment);
            if (i>0)
                return new ResponseDTO(i);
        }
        return responseDTO.error();
    }


}
