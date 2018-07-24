package com.yanlei.webservice;

import com.yanlei.model.BusinessData;
import com.yanlei.util.ResponseDTO;

import javax.ws.rs.*;
import java.util.List;

/**
 * @Author: x
 * @Date: Created in 16:36 2018/3/23
 */

@Path("/businessData")
public interface BusinessDataService {

    @GET  //查询所有数据
    @Path("/businessDatas")
    @Produces({ "application/xml", "application/json" })
    ResponseDTO findBusinessDatas();

    @GET  //根据id查询
    @Path("/businessData/{id}")
    @Produces({ "application/xml", "application/json" })
    ResponseDTO getBusinessDataById(@PathParam("id") Integer id);

    @POST //单个增加
    @Path("/businessData")
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    ResponseDTO saveBusinessData(BusinessData businessData);

    @POST //批量增加
    @Path("/businessDatas")
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    ResponseDTO saveBusinessDatas(List<BusinessData> BusinessDatas);


    @PUT //单个修改
    @Path("/businessData")
    @Consumes({ "application/xml", "application/json" })
    ResponseDTO updateBusinessData(BusinessData businessData);


}
