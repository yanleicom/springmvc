package com.yanlei.webservice.impl;

import com.yanlei.dao.webdao.BusinessDataDao;
import com.yanlei.model.BusinessData;
import com.yanlei.util.ResponseDTO;
import com.yanlei.webservice.BusinessDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: x
 * @Date: Created in 16:53 2018/3/23
 */
@Service
public class BusinessDataServiceImpl implements BusinessDataService {

    @Autowired
    private BusinessDataDao businessDataDao;

    //查询全部
    public ResponseDTO findBusinessDatas() {
        ResponseDTO responseDTO = new ResponseDTO();
        List<BusinessData> businessDataList = businessDataDao.findBusinessDatas();
        if (businessDataList != null && businessDataList.size() > 0){
            responseDTO.setData(businessDataList);
            return responseDTO;
        }
        return responseDTO.error();
    }

    //根据id查询
    public ResponseDTO getBusinessDataById(Integer id) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (id != null){
            BusinessData businessData = businessDataDao.getBusinessDataById(id);
            if (businessData != null){
                ResponseDTO<BusinessData> responDTO = new ResponseDTO<BusinessData>(businessData);
                return responDTO;
            }
        }
        return responseDTO.error();
    }

    //单个增加
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO saveBusinessData(BusinessData businessData) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (businessData != null){
            businessDataDao.saveBusinessData(businessData);
            if (businessData.getId() != null){
                responseDTO.setData(businessData);
                return responseDTO;
            }
        }
        return responseDTO.error();
    }

    //批量增加
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO saveBusinessDatas(List<BusinessData> BusinessDatas) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (BusinessDatas != null && BusinessDatas.size()>0){
            Integer i = businessDataDao.saveBusinessDatas(BusinessDatas);
            if (i>0)
                return new ResponseDTO(i);
        }
        return responseDTO.error();
    }

    //单个修改
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO updateBusinessData(BusinessData businessData) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (businessData != null && businessData.getId() != null){
            Integer i = businessDataDao.updateBusinessData(businessData);
            if (i>0)
                return new ResponseDTO(i);
        }
        return responseDTO.error();
    }
}
