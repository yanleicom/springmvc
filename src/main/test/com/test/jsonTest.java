package com.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
/*import com.yanlei.model.Dlists;
import com.yanlei.model.People;
import com.yanlei.model.ResponseDTO;
import com.yanlei.model.ResponseVo;*/
import com.yanlei.dao.LegalPersonDao;
import com.yanlei.dao.PeopleFileDao;
import com.yanlei.dao.RealPeopleNumDao;
import com.yanlei.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author xiayaunlei
 * @date 2018/1/18 15:07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-service.xml")
public class jsonTest {

    @Autowired
    private PeopleFileDao peopleFileDao;
    @Autowired
    private RealPeopleNumDao realPeopleNumDao;
    @Autowired
    private LegalPersonDao legalPersonDao;

    @Test
    public void testdelZFBAll(){
        peopleFileDao.delZFBAll();
    }

    @Test
    public void testAddSSWorkmanList(){
        List<SSWorkman> ssWorkmanList = new ArrayList<SSWorkman>();

        SSWorkman ssWorkman1 = new SSWorkman();
       // ssWorkman1.setWorker("worker1");
        ssWorkman1.setNumber(1111L);
        ssWorkman1.setProportion(23D);
        ssWorkman1.setUpdatetime(new Date());

        SSWorkman ssWorkman2 = new SSWorkman();
        //ssWorkman2.setWorker("worker2");
        ssWorkman2.setNumber(2222L);
        ssWorkman2.setProportion(27D);
        ssWorkman2.setUpdatetime(new Date());

        ssWorkmanList.add(ssWorkman1);
        ssWorkmanList.add(ssWorkman2);

        int i = legalPersonDao.addSSWorkmanList(ssWorkmanList);
        System.out.println(i);
    }


    /**
     * 测试批量添加的操作
     */
    @Test
    public void testAddZFBList(){
        System.out.println(peopleFileDao);
//        PeopleFile peopleFileList = peopleFileDao.findPeopleFileList();
        List<Zfb> zfbList = new ArrayList<Zfb>();
        Zfb zfb = new Zfb();
        zfb.setDepartment("部门1");
        zfb.setNumber(12343L);
        zfb.setUpdatatime(new Date());


        Zfb zfb1 = new Zfb();
        zfb1.setDepartment("部门2");
        zfb1.setNumber(1234L);
        zfb1.setUpdatatime(new Date());


        Zfb zfb2 = new Zfb();
        zfb2.setDepartment("部门3");
        zfb2.setNumber(333333L);
        zfb2.setUpdatatime(new Date());

        zfbList.add(zfb);
        zfbList.add(zfb1);
        zfbList.add(zfb2);

        peopleFileDao.addZFBList(zfbList);
    }


    @Test
    public void testAddPeopleAgeList(){
        List<PeopleAge> peopleAges = new ArrayList<PeopleAge>();
        PeopleAge peopleAge = new PeopleAge();
        peopleAge.setAge("23");
        peopleAge.setNumber(11111L);
        peopleAge.setProportion(23.23);
        PeopleAge peopleAge1 = new PeopleAge();
        peopleAge1.setAge("32");
        peopleAge1.setNumber(222222L);
        peopleAge1.setProportion(22.2222);

        peopleAges.add(peopleAge);
        peopleAges.add(peopleAge1);

        realPeopleNumDao.addPeopleAgeList(peopleAges);
    }

    @Test
    public void testMethod1(){
        List<String> lists = new ArrayList<String>();
        lists.add("区委办");
        lists.add("正委办法");
        lists.add("人委办");
        lists.add("才委办");
        lists.add("检委办");
        Object[] objects = lists.toArray();
        System.out.println(objects[0]);



        List<People> peoples1 = new ArrayList<People>();
        People people1 = new People("lilei",34,22343L);
        People people2 = new People("lilei",34,22343L);
        peoples1.add(people1);
        peoples1.add(people2);

        List<People> peoples2 = new ArrayList<People>();
        People people3 = new People("lilei",34,22343L);
        People people4 = new People("lilei",34,22343L);
        peoples2.add(people3);
        peoples2.add(people4);

        List<ResponseVo> Dlists = new ArrayList<ResponseVo>();
        ResponseVo responseVo1 = new ResponseVo();
        responseVo1.setFenlist(peoples1);
        ResponseVo responseVo2 = new ResponseVo();
        responseVo2.setFenlist(peoples2);
        Dlists.add(responseVo1);
        Dlists.add(responseVo2);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setLists(lists);
        responseDTO.setDlists(Dlists);

        System.out.println(JSON.toJSONString(responseDTO));
    }

    @Test
    public void testReflect(){
        Class<?> aClass = SSWorkman.class;
        try {
            Object obj = aClass.newInstance();
            Field[] fields = aClass.getDeclaredFields();
            Field field = fields[fields.length - 1];
            field.setAccessible(true);
            fields[fields.length -1].set(obj,new Date());
            SSWorkman workman = (SSWorkman)obj;
            System.out.println(fields.length);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testGetSystemInfo(){
        Properties properties = System.getProperties();

        System.out.println("用户的账户名称：" + properties.getProperty("user.name"));
    }
}
