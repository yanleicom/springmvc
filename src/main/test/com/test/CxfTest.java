package com.test;

import com.alibaba.fastjson.JSONObject;
import com.yanlei.model.PeopleFile;
import com.yanlei.model.Qjw;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-service.xml")
public class CxfTest {
    private static final Logger log= LoggerFactory.getLogger(CxfTest.class);
    @Test
    public void test1(){
        String s = WebClient.create("http://172.16.10.105:8080/hzxcq_dxm/exchangeTotal")
                .accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).get(String.class);
        System.out.println(s);
    }

    @Test
    public void test2(){
        Qjw qjw =new Qjw();
        qjw.setId(181);
        qjw.setDepartment("测试修改");
        qjw.setNumber(1L);
        qjw.setUpdatatime(new Date());

        Qjw qjw1 =new Qjw();
        qjw1.setId(182);
        qjw1.setDepartment("测试修改");
        qjw1.setNumber(2L);
        qjw1.setUpdatatime(new Date());
        List<Qjw> qjws = new ArrayList<Qjw>();
        qjws.add(qjw);
        qjws.add(qjw1);

        String s = WebClient.create("http://localhost:8081/services/webService/updateQjw")
                .accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).put(JSONObject.toJSONString(qjws),String.class);
        System.out.println(s);
    }

    @Test
    public void test3(){
        String s = WebClient.create("http://localhost:8081/services/webService/peopleFileList")
                .accept(MediaType.APPLICATION_JSON).get(String.class);//查询全部
        log.info("有没有定西啊"+s);
    }

    @Test
    public void test4(){ //查询单个有问题
        PeopleFile peopleFile1 = new PeopleFile();
        peopleFile1.setId(2);
        String s = WebClient.create("http://localhost:8081/services/webService/peopleFileById/" + peopleFile1.getId())
                .accept(MediaType.APPLICATION_JSON).get(String.class);//单个查询
        log.info("啥"+s);
    }

    @Test
    public void test5(){ //单个插入
        PeopleFile peopleFile = new PeopleFile();
        //peopleFile.setId(3);
        peopleFile.setActivenumber(1111L);
        peopleFile.setFilenumber(2222L);
        peopleFile.setFileavg(444L);
        peopleFile.setFiletime("3.6");

        Integer post = WebClient.create("http://localhost:8081/services/webService/savePeopleFile")
                .type(MediaType.APPLICATION_JSON).post(JSONObject.toJSONString(peopleFile), Integer.class);
        log.info("插入条数为"+post);
    }

    @Test
    public void test6(){ //批量插入
        PeopleFile peopleFile = new PeopleFile();
        //peopleFile.setId(3);
        peopleFile.setActivenumber(11111L);
        peopleFile.setFilenumber(22222L);
        peopleFile.setFileavg(4434L);
        peopleFile.setFiletime("3.64");
        PeopleFile peopleFile1 = new PeopleFile();
        //peopleFile.setId(3);
        peopleFile1.setActivenumber(11111L);
        peopleFile1.setFilenumber(22222L);
        peopleFile1.setFileavg(4434L);
        peopleFile1.setFiletime("3.64");
        List<PeopleFile> peopleFileList = new ArrayList<PeopleFile>();
        peopleFileList.add(peopleFile);
        peopleFileList.add(peopleFile1);

        Integer num = WebClient.create("http://localhost:8081/services/webService/savePeopleFileList")
                .type(MediaType.APPLICATION_JSON).post(JSONObject.toJSONString(peopleFileList), Integer.class);
        log.info("插入条数为:"+num);
    }

    @Test
    public void test7(){  //单个修改
        PeopleFile peopleFile = new PeopleFile();
        peopleFile.setId(3);
        peopleFile.setActivenumber(11111L);
        peopleFile.setFilenumber(22222L);
        peopleFile.setFileavg(4434L);
        peopleFile.setColumn1("11");
        peopleFile.setColumn2("22");
        peopleFile.setColumn3("33");
        peopleFile.setColumn4("44");
        peopleFile.setColumn5("55");
        peopleFile.setFiletime("3.64");
        Integer put = WebClient.create("http://localhost:8081/services/webService/updatePeople")
                .accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).put(JSONObject.toJSONString(peopleFile), Integer.class);
        log.info("修改条数为:"+put);
    }

    @Test
    public void test8(){  //批量修改
        List<PeopleFile> peopleFileList = new ArrayList<PeopleFile>();

        PeopleFile peopleFile = new PeopleFile();
        peopleFile.setId(4);
        peopleFile.setActivenumber(11111L);
        peopleFile.setFilenumber(22222L);
        peopleFile.setFileavg(4434L);
        peopleFile.setColumn1("11");
        peopleFile.setColumn2("22");
        peopleFile.setColumn3("33");
        peopleFile.setColumn4("44");
        peopleFile.setColumn5("55");
        peopleFile.setFiletime("3.64");

        PeopleFile peopleFile1 = new PeopleFile();
        peopleFile1.setId(5);
        peopleFile1.setActivenumber(11111L);
        peopleFile1.setFilenumber(22222L);
        peopleFile1.setFileavg(4434L);
        peopleFile1.setColumn1("11");
        peopleFile1.setColumn2("22");
        peopleFile1.setColumn3("33");
        peopleFile1.setColumn4("44");
        peopleFile1.setColumn5("55");
        peopleFile1.setFiletime("3.64");
        peopleFileList.add(peopleFile);
        peopleFileList.add(peopleFile1);
        Integer put = WebClient.create("http://localhost:8081/services/webService/updatePeoples")
                .accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).put(JSONObject.toJSONString(peopleFileList), Integer.class);
        log.info("修改条数为:"+put);
    }

    @Test
    public void test9(){
        Response delete = WebClient.create("http://localhost:8081/services/webService/delpeopleFile" + 3)
                .accept(MediaType.APPLICATION_JSON).delete();//根据ID删除
        log.info("删除条数"+delete);
    }

}
