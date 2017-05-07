/*
 * Copyright (C) 2015, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * TestStatService.java
 *
 * Description
 *
 * Author TangWeicheng
 *
 * Ver 1.0, 2017-5-6, TangWeicheng, Create file
 */

package com.tplink.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration({
        "/root-context.xml"
})
public class TestStatService {

    @Autowired
    StatService statService;

    @Test
    public void testgetActiveUserCountBetween() {
        String aid = "com.cyanogenmod.trebuchet";
        String pid = "TP904A";
        String vid = "7.0";
        Date day = new Date();

        statService.getCustomEventCountOfDate(day, pid, vid, aid);
        statService.getStartupCountOfDate(day, pid, vid, aid);
        statService.getWeeklyRetention(pid, vid, aid);
    }

}
