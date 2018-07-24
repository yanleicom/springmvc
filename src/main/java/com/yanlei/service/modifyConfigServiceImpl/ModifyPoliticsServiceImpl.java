package com.yanlei.service.modifyConfigServiceImpl;

import com.yanlei.dao.ModifyPoliticsDao.ModifyPoliticsDao;
import com.yanlei.model.GovernmentDepartment;
import com.yanlei.service.modifyConfigService.ModifyPoliticsService;
import com.yanlei.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: x
 * @Date: Created in 9:42 2018/4/18
 */

@Service
public class ModifyPoliticsServiceImpl implements ModifyPoliticsService {

    @Autowired
    private ModifyPoliticsDao modifyPoliticsDao;

    @Override
    public Result updateDepartment(List<GovernmentDepartment> departments) {

        if (departments.size()>0 && departments!= null){

            int num = modifyPoliticsDao.updateDepartment(departments);
            if (num > 0){
                return Result.ok("修改成功,修改条数 ===>>>"+num);
            }
            return Result.error("updateDepartment修改失败");
        }
        return Result.error("接受参数为空");
    }

    @Override
    public Result delDepartmentOne(Integer id) {
        if (id != null){
            int num = modifyPoliticsDao.delDepartmentOne(id);
            if (num > 0){
                return Result.ok("删除成功id为===>>>"+id);
            }
            return Result.error();
        }
        return Result.error();
    }
}
