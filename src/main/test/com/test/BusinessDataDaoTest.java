package com.test;

import com.yanlei.model.BusinessData;
import com.yanlei.model.DepartmentQjw;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.junit.Test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: x
 * @Date: Created in 9:33 2018/3/26
 */
public class BusinessDataDaoTest {

    JacksonJsonProvider jsonProvider = new JacksonJsonProvider();
    // String baseAddress = "http://172.16.9.61:8080/yanlei/services/webService/department";
    String baseAddress = "http://localhost:8081/services/webService/businessData";
    //String baseAddress = "http://10.18.58.167:8081/services/webService/dapartment";

    @Test
    public void testGetDepartmentEntityByID(){ //根据id查询
        String s = ClientBuilder.newClient().target(baseAddress).path("/businessData/6").register(jsonProvider)
                .request(MediaType.APPLICATION_JSON).get(String.class);


        System.out.println(s);
    }

    @Test
    public void testGetDepartments() {  //查询全部
        String s = ClientBuilder.newClient().target(baseAddress).path("/businessDatas")
                .register(jsonProvider).request(MediaType.APPLICATION_JSON).get(String.class);

        //for (Object object : list) {
        System.out.println(s);
        //}
    }

    @Test
    public void testInsertDepartmentQjw() throws ParseException { //增加单个
        BusinessData businessData =new BusinessData();
        //businessData.setAppid(1);
        //businessData.setUserid(1);
        businessData.setName("测试数据1");
        businessData.setIntValue(15151351L);
        businessData.setUnit("件");
        //businessData.setDoublevalue(14.43225D);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=sdf.parse("2018-03-12 17:11:54");
        businessData.setAcquisitionTime(date);
        businessData.setCycle("本年");
        businessData.setEncrypted(0);
        businessData.setUpdateTime(new Date());
        businessData.setDepartmentId(111);
        businessData.setStartTime(sdf.parse("2018-01-10 0:0:0"));
        businessData.setEndTime(sdf.parse("2018-03-10 17:11:54"));
        businessData.setStatictType("累计统计好几次");

        String post = ClientBuilder.newClient().target(baseAddress).path("/businessData").register(jsonProvider)
                .request(MediaType.APPLICATION_JSON).post(Entity.entity(businessData, MediaType.APPLICATION_JSON), new GenericType<String>() {
                });
        System.out.println(post);
    }

    @Test
    public void testBatchInsertDepartment() throws ParseException { //批量增加
        List<BusinessData> businessDataList = new ArrayList<BusinessData>();
        BusinessData businessData =new BusinessData();
        businessData.setName("测试数据2");
        //businessData.setIntvalue(14L);
        businessData.setUnit("百分比");
        businessData.setIntValue(3214L);
        businessData.setAppId(1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=sdf.parse("2011-01-12 17:11:54");
        businessData.setAcquisitionTime(date);
        businessData.setCycle("2017年1月");
        businessData.setEncrypted(0);
        businessData.setUpdateTime(new Date());
        businessData.setDepartmentId(1111);
        //businessData.setStartTime(sdf.parse("2018-01-10 0:0:0"));
        //businessData.setEndTime(sdf.parse("2018-03-10 17:11:54"));
        businessData.setStatictType("无累计统计");

        BusinessData businessData1 =new BusinessData();
        businessData1.setName("测试数据3");
        businessData1.setIntValue(2534L);
        //businessData1.setDoubleValue(44.538D);
        businessData1.setUnit("百分比");
        businessData1.setAppId(1);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1=sdf1.parse("2017-02-12 17:11:54");
        businessData1.setAcquisitionTime(date1);
        businessData1.setCycle("2017年2月");
        businessData1.setEncrypted(0);
        businessData1.setUpdateTime(new Date());
        businessData1.setDepartmentId(1111);
        businessData1.setStartTime(sdf1.parse("2018-01-10 0:0:0"));
        businessData1.setEndTime(sdf1.parse("2018-03-10 17:11:54"));
        businessData1.setStatictType("无累计统计");

        businessDataList.add(businessData);
        businessDataList.add(businessData1);

        String post = ClientBuilder.newClient().target(baseAddress).path("/businessDatas").register(jsonProvider).
                request(MediaType.APPLICATION_JSON).post(Entity.entity(businessDataList, MediaType.APPLICATION_JSON),
                new GenericType<String>() {
                });
        System.out.println(post);
    }

    @Test
    public void testUpdateDepartmentQjw() throws ParseException { //单个修改
        BusinessData businessData =new BusinessData();
       /* businessData.setAppid(1);
        businessData.setUserid(1);
        businessData.setName("政协办");
        businessData.setValue(2593534L);
        businessData.setPercentage(22D);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=sdf.parse("2016-07-12 17:11:54");
        businessData.setAcquisitiontime(date);
        businessData.setCycle("本年");
        businessData.setEncrypted(0);
        businessData.setUpdatetime(new Date());*/
        businessData.setSource("小夏");
        businessData.setId(6);
        String s = ClientBuilder.newClient().target(baseAddress).path("/businessData").register(jsonProvider)
                .request(MediaType.APPLICATION_JSON).put(Entity.entity(businessData, MediaType.APPLICATION_JSON),
                        new GenericType<String>() {
                        });
        System.out.println(s);
    }
}
