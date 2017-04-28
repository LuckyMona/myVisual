/*
 * Copyright (C) 2015, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * HistoryTrendController.java
 *
 * Description
 *
 * Author TangWeicheng
 *
 * Ver 1.0, 2017-4-26, TangWeicheng, Create file
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

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@Scope("prototype")
@RequestMapping("/historyTrends")
public class HistoryTrendController extends BasicController {

    @Autowired
    private StatService statService;

    private static final String STARTUP_COUNTS = "startUpCounts";

    private static final String NEW_USER_COUNT = "newUsers";

    private static final String ACTIVE_USER_COUNTS = "activeUsers";

    private static final String USING_TIME_AVERANGE = "usingTime";

    private static final String CUSTOM_EVENT_COUNTS = "customEventsCounts";

    private static final String BASE_COUNTS = "baseCounts";

    private static final String CONTRAST_COUNTS = "contrastCounts";

    private static Map<String, Object> getContrastDataMap(Object o1, Object o2) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(BASE_COUNTS, o1);
        map.put(CONTRAST_COUNTS, o2);
        return map;
    }

    @RequestMapping("/getIndexs")
    @ResponseBody
    @LoginCheck
    @LogRequired
    public Object getHistoryIndexs(String baseTime, String contrastTime, int projID, int verID,
            int appID) {
        try {
            Date base = DateUtil.parseDate(baseTime);
            Date current = DateUtil.parseDate(contrastTime);
            setResult(ACTIVE_USER_COUNTS,
                    getContrastDataMap(
                            statService.getActiveUserCountOfDate(base, projID, verID, appID),
                            statService.getActiveUserCountOfDate(current, projID, verID, appID)));
            setResult(STARTUP_COUNTS,
                    getContrastDataMap(
                            statService.getStartupCountOfDate(base, projID, verID, appID),
                            statService.getStartupCountOfDate(current, projID, verID, appID)));
            setResult(NEW_USER_COUNT,
                    getContrastDataMap(
                            statService.getNewUserCountOfDate(base, projID, verID, appID),
                            statService.getNewUserCountOfDate(current, projID, verID, appID)));
            setResult(USING_TIME_AVERANGE,
                    getContrastDataMap(
                            statService.getAverangeUseTimeOfDate(base, projID, verID, appID),
                            statService.getAverangeUseTimeOfDate(current, projID, verID, appID)));
            setResult(CUSTOM_EVENT_COUNTS,
                    getContrastDataMap(
                            statService.getCustomEventCountOfDate(base, projID, verID, appID),
                            statService.getCustomEventCountOfDate(current, projID, verID, appID)));

            setType(SUCCESS);

        } catch (ParseException e) {
            e.printStackTrace();
            setType(ERROR);
            setErrorMessage("检查页面输入参数");
        }

        return getResult();
    }

    @RequestMapping("/getStartupTrends")
    @ResponseBody
    @LoginCheck
    @LogRequired
    public Object getStartupTrends(String baseTime, String contrastTime, int projID, int verID,
            int appID) {

        setType(SUCCESS);
        return getResult();
    }

    @RequestMapping("/getNewUserTrends")
    @ResponseBody
    @LoginCheck
    @LogRequired
    public Object getNewUserTrends(String baseTime, String contrastTime, int projID, int verID,
            int appID) {

        setType(SUCCESS);
        return getResult();
    }

    @RequestMapping("/getActiveUserTrends")
    @ResponseBody
    @LoginCheck
    @LogRequired
    public Object getActiveUserTrends(String baseTime, String contrastTime, int projID, int verID,
            int appID) {

        setType(SUCCESS);
        return getResult();
    }

    @RequestMapping("/getUsingTimeTrends")
    @ResponseBody
    @LoginCheck
    @LogRequired
    public Object getUsingTimeTrends(String baseTime, String contrastTime, int projID, int verID,
            int appID) {

        setType(SUCCESS);
        return getResult();
    }

    @RequestMapping("/getCustomEventsTrends")
    @ResponseBody
    @LoginCheck
    @LogRequired
    public Object getCustomEventsTrends(String baseTime, String contrastTime, int projID, int verID,
            int appID) {
        setType(SUCCESS);
        return getResult();
    }
}
