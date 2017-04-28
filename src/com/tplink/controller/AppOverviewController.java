/*
 * Copyright (C) 2015, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * AppOverviewController.java
 *
 * Description
 *
 * Author TangWeicheng
 *
 * Ver 1.0, 2017-4-27, TangWeicheng, Create file
 */

package com.tplink.controller;

import com.tplink.annotation.LogRequired;
import com.tplink.annotation.LoginCheck;
import com.tplink.service.StatService;
import com.tplink.utils.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@Scope("prototype")
@RequestMapping("/appOverview")
public class AppOverviewController extends BasicController {

    @Autowired
    StatService statService;

    public static final String COUNT = "indexCounts";

    public static final String URL = "url";

    @RequestMapping("/keyIndex/getStartUpCounts")
    @ResponseBody
    @LoginCheck
    @LogRequired
    public Object getStartUpCounts(int projID, int verID, int appID) {
        setResult(COUNT, statService.getStartupCountOfDate(new Date(), projID, verID, appID));
        return getResult();
    }

    @RequestMapping("/keyIndex/getNewUsersCounts")
    @ResponseBody
    @LoginCheck
    @LogRequired
    public Object getNewUsersCounts(int projID, int verID, int appID) {
        setResult(COUNT, statService.getNewUserCountOfDate(new Date(), projID, verID, appID));
        return getResult();
    }

    @RequestMapping("/keyIndex/getActiveUsersCounts")
    @ResponseBody
    @LoginCheck
    @LogRequired
    public Object getActiveUsersCounts(int projID, int verID, int appID) {
        setResult(COUNT, statService.getActiveUserCountOfDate(new Date(), projID, verID, appID));
        return getResult();
    }

    @RequestMapping("/keyIndex/getUsingTime")
    @ResponseBody
    @LoginCheck
    @LogRequired
    public Object getUsingTime(int projID, int verID, int appID) {
        setResult(COUNT, statService.getAverangeUseTimeOfDate(new Date(), projID, verID, appID));
        return getResult();
    }

    @RequestMapping("/keyIndex/getCustomEventCounts")
    @ResponseBody
    @LoginCheck
    @LogRequired
    public Object getCustomEventCounts(int projID, int verID, int appID) {
        setResult(COUNT, statService.getCustomEventCountOfDate(new Date(), projID, verID, appID));
        return getResult();
    }

    private static final String TOTAL_USER_COUNT = "totalUsers";

    private static final String ACTIVE_USER_COUNT_EVERY_WEEK = "weekActiveUsers";

    private static final String WEEK_RETENTION = "weekRetention";

    private static final String ERROR_RATE = "errorRatio";

    @RequestMapping("keyIndex/getTotalIndexs")
    @ResponseBody
    @LoginCheck
    @LogRequired
    public Object getTotalIndexs(int projID, int verID, int appID) {
        try {
            setResult(TOTAL_USER_COUNT, statService.getTotalUserCount(projID, verID, appID));
            setResult(ACTIVE_USER_COUNT_EVERY_WEEK, statService.getActiveUserCountBetween(
                    DateUtil.getLastWeekBefore(new Date()), new Date(), projID, verID, appID));
            setResult(WEEK_RETENTION, statService.getWeeklyRetention(projID, verID, appID));
            setResult(ERROR_RATE, statService.getErrorRateOfDate(new Date(), projID, verID, appID));
        } catch (Exception e) {
            e.printStackTrace();
            setType(ERROR);
        }

        return getResult();
    }

    private static final String AUTH_STRING = "authorities";

    private static final String AUTH_RATES = "authoritiesAndRate";

    @RequestMapping("/appUsing/getAuthorityAndRate")
    @ResponseBody
    @LoginCheck
    @LogRequired
    public Object getApplicationAuthRate(int projID, int verID, int appID) {
        try {
            setResult(AUTH_STRING, statService.getAuthorityList(projID, verID, appID));
            setResult(AUTH_RATES, statService.getAuthorityRateList(projID, verID, appID));
        } catch (Exception e) {
            e.printStackTrace();
            setType(ERROR);
        }
        return getResult();
    }

    private static final String MEM_USAGE = "memory";

    private static final String DATA_USAGE = "dataflow";

    private static final String ELE_USAGE = "electricity";

    @RequestMapping("/appUsing/getMemoryDataElecIndexs")
    @ResponseBody
    @LoginCheck
    @LogRequired
    public Object getMemDataEleIndexs(int projID, int verID, int appID) {

        try {
            setResult(MEM_USAGE, statService.getMemoryUsage(projID, verID, appID));
            setResult(DATA_USAGE, statService.getDataUsage(projID, verID, appID));
            setResult(ELE_USAGE, statService.getEleUsage(projID, verID, appID));
        } catch (Exception e) {
            e.printStackTrace();
            setType(ERROR);
        }

        return getResult();
    }

}
