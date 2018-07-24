package com.yanlei.webservice;

import com.yanlei.cxfmodel.CXFDepartment;
import com.yanlei.util.ResponseDTO;

import javax.ws.rs.*;
import java.util.List;

/**
 * @Author: x
 * @Date: Created in 10:47 2018/3/26
 */

@Path("/department/fen")
public interface DepartmentService {

    @GET  //查询所有数据
    @Path("/departments")
    @Produces({ "application/xml", "application/json" })
    ResponseDTO getDepartments();

   /* @GET  //根据名称查询
    @Path("/department/{name}")
    @Produces({ "application/xml", "application/json" })
    ResponseDTO getDepartmentByName(@PathParam("name") String name);*/

    @GET  //根据id查询
    @Path("/department/{id}")
    @Produces({ "application/xml", "application/json" })
    ResponseDTO getDepartmentById(@PathParam("id") Integer id);


    @POST //单个增加
    @Path("/department")
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    ResponseDTO saveDepartment(CXFDepartment cxfDepartment);

    @POST //批量增加
    @Path("/departments")
    @Consumes({ "application/xml", "application/json" })
    @Produces({ "application/xml", "application/json" })
    ResponseDTO saveCXFDepartments(List<CXFDepartment> cxfDepartments);


    @PUT //单个修改
    @Path("/department")
    @Consumes({ "application/xml", "application/json" })
    ResponseDTO updateCXFDepartment(CXFDepartment cxfDepartment);
}
