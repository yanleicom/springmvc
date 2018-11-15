package cxfTest;

import com.yanlei.model.DepartmentQjw;
import com.yanlei.model.PeopleFile;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-service.xml")
public class JARXSClient {
    //ip地址可配置
//    String baseAddress = "http://172.16.9.61:8080/yanlei/services/webService";
    String baseAddress = "http://localhost:8080/services/webService";

    JacksonJsonProvider jsonProvider = new JacksonJsonProvider();

    @Test
    public void testBatchInsertDepartment() {
        List<DepartmentQjw> departmentQjws = new ArrayList<DepartmentQjw>();
        DepartmentQjw departmentQjw1 = new DepartmentQjw();
        departmentQjw1.setDepartment("department1");
        departmentQjw1.setNumber(2222222L);
        departmentQjw1.setUpdatatime(new Date());

        DepartmentQjw departmentQjw2 = new DepartmentQjw();
        departmentQjw2.setDepartment("department2");
        departmentQjw2.setNumber(3333333L);
        departmentQjw2.setUpdatatime(new Date());

        departmentQjws.add(departmentQjw1);
        departmentQjws.add(departmentQjw2);

        String post = ClientBuilder.newClient().target(baseAddress).path("departmentQjws").register(jsonProvider).
                request(MediaType.APPLICATION_JSON).post(Entity.entity(departmentQjws, MediaType.APPLICATION_JSON),
                new GenericType<String>() {
                });
        System.out.println(post);


    }


    @Test
    public void testInsertDepartmentQjw() {
        DepartmentQjw departmentQjw = new DepartmentQjw();
        departmentQjw.setDepartment("bumeng");
        departmentQjw.setNumber(1232342L);
        departmentQjw.setUpdatatime(new Date());
        String s = ClientBuilder.newClient().target(baseAddress).path("departmentQjw").register(jsonProvider)
                .request(MediaType.APPLICATION_JSON).post(Entity.entity(departmentQjw, MediaType.APPLICATION_JSON),
                        new GenericType<String>() {
                        });
        System.out.println(s);
    }

    @Test
    public void testUpdateDepartmentQjw() {
        DepartmentQjw departmentQjw = new DepartmentQjw();
        departmentQjw.setDepartment("bumeng");
        departmentQjw.setNumber(1232342L);
        departmentQjw.setId(153);
        departmentQjw.setUpdatatime(new Date());
        String s = ClientBuilder.newClient().target(baseAddress).path("departmentQjw").register(jsonProvider)
                .request(MediaType.APPLICATION_JSON).put(Entity.entity(departmentQjw, MediaType.APPLICATION_JSON),
                        new GenericType<String>() {
                        });
        System.out.println(s);
    }

    @Test
    public void testGetDepartments() {
        List list = ClientBuilder.newClient().target(baseAddress).register(jsonProvider).
                path("departmentQjws").request(MediaType.APPLICATION_JSON).get(List.class);

        for (Object object : list) {
            System.out.println(object);
        }
    }

    @Test
    public void testGetQjwList() {
        JacksonJsonProvider jsonProvider = new JacksonJsonProvider();
        String productList = ClientBuilder.newClient().register(jsonProvider)
                .target(baseAddress).path("/departmentQjws")
                .request(MediaType.APPLICATION_JSON).get(String.class);
        System.out.println(productList);
        /*for (Object product : productList) {
            System.out.println(product);
        }*/
    }


    @Test
    public void testPeopleFile() {
        String s = ClientBuilder.newClient().target(baseAddress).path("/peopleFile").
                request(MediaType.APPLICATION_JSON).get(String.class);
        //显示peoplefile的数据    todo "updatetime":null--------------------------------
        System.out.println(s);
    }

    public List<PeopleFile> getPeopleFileList() {
        List<PeopleFile> peopleFileList = new ArrayList<PeopleFile>();
        PeopleFile peopleFile1 = new PeopleFile();
        peopleFile1.setColumn3("column13");
        peopleFile1.setColumn4("column14");
        peopleFile1.setColumn5("column15");
        peopleFile1.setColumn6("column16");
        peopleFile1.setColumn7("column17");
        peopleFile1.setUpdatetime(new Date());

        PeopleFile peopleFile2 = new PeopleFile();
        peopleFile2.setColumn3("column23");
        peopleFile2.setColumn4("column24");
        peopleFile2.setColumn5("column25");
        peopleFile2.setColumn6("column26");
        peopleFile2.setColumn7("column27");
        peopleFile2.setUpdatetime(new Date());


        peopleFileList.add(peopleFile1);
        peopleFileList.add(peopleFile2);

        return peopleFileList;
    }

    @Test
    public void testsavePeopleFile() {
        List<PeopleFile> peopleFileList = getPeopleFileList();
        Integer post = ClientBuilder.newClient().target(baseAddress).register(jsonProvider).
                path("/savePeopleFile").request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(peopleFileList, MediaType.APPLICATION_JSON), new GenericType<Integer>() {
                });
        System.out.println(post);
    }

    @Test
    public void testupdatePeopleFile() {

        List<PeopleFile> peopleFileList = getPeopleFileList();
        Integer put = ClientBuilder.newClient().target(baseAddress).register(jsonProvider).
                path("/updatepeoples").request(MediaType.APPLICATION_JSON).
                put(Entity.entity(peopleFileList, MediaType.APPLICATION_JSON), new GenericType<Integer>() {
                });
        System.out.println(put);

    }

}
