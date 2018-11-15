package com.quartz;

import com.yanlei.controller.DeparmentController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/**
 * @Author: x
 * @Date: Created in 15:08 2018/3/26
 */

@ContextConfiguration(locations = "classpath:spring/applicationContext-service.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class QuartzTest {

    @Test
    public void test(){
       while (true){}
    }



}
