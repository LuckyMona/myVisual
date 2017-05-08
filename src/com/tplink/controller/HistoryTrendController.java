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
import com.tplink.domain.TimePeriod;
import com.tplink.service.StatService;
import com.tplink.utils.DateUtil;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

    private static final String COUNTINT_TIME = "time";

    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormat
            .forPattern("yyyy-MM-dd HH:mm:ss");

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd");

    private static Map<String, Object> getTableDataMap(String date, int startCount,
            int newUserCount, int activeUserCount, int cusEventCount, String usingTime) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ACTIVE_USER_COUNTS, activeUserCount);
        map.put(CUSTOM_EVENT_COUNTS, cusEventCount);
        map.put(NEW_USER_COUNT, newUserCount);
        map.put(STARTUP_COUNTS, startCount);
        map.put(USING_TIME_AVERANGE, usingTime);
        map.put(COUNTINT_TIME, date);
        return map;
    }

    @RequestMapping("/getIndexs")
    @ResponseBody
    @LoginCheck
    @LogRequired
    public Object getHistoryIndexs(TimePeriod timePeriod, String projID, String verID,
            String appID) {

        Date base = null;
        try {
            base = DateUtil.parseDate(timePeriod.getStart());
        } catch (Exception e1) {
            e1.printStackTrace();
            setType(ERROR);
            setErrorMessage("参数错误");
            return getResult();
        }
        Date current = null;
        try {
            current = DateUtil.parseDate(timePeriod.getEnd());
        } catch (Exception e) {
            e.printStackTrace();
            setType(ERROR);
            setErrorMessage("参数错误");
            return getResult();
        }

        boolean sameDay = timePeriod.isSingleDay();

        try {
            if (sameDay) {

                setResult(getTableDataMap("",
                        statService.getStartupCountOfDate(current, projID, verID, appID),
                        statService.getNewUserCountOfDate(current, projID, verID, appID),
                        statService.getActiveUserCountOfDate(current, projID, verID, appID),
                        statService.getCustomEventCountOfDate(current, projID, verID, appID),
                        statService.getAverangeUseTimeOfDate(current, projID, verID, appID)));
            } else {
                setResult(getTableDataMap("",
                        statService.getStartupCountBetween(base, current, projID, verID, appID),
                        statService.getNewUserCountBetween(base, current, projID, verID, appID),
                        statService.getActiveUserCountBetween(base, current, projID, verID, appID),
                        statService.getCustomEventCountBetween(base, current, projID, verID, appID),
                        statService.getAverangeUseTimeBetween(base, current, projID, verID,
                                appID)));
            }

            setType(SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            setType(ERROR);
        }

        return getResult();
    }

    private static final String TABLE_DATA = "tableDatas";

    @RequestMapping("/getTableDetails")
    @ResponseBody
    @LoginCheck
    @LogRequired
    public Object getTableDatas(TimePeriod timePeriod, String projID, String verID, String appID) {
        Date current = null;
        try {
            current = DateUtil.parseDate(timePeriod.getEnd());
        } catch (Exception e1) {
            e1.printStackTrace();
            setType(ERROR);
            setErrorMessage("参数错误");
            return getResult();
        }
        Date contrast = null;
        try {
            contrast = DateUtil.parseDate(timePeriod.getStart());
        } catch (Exception e) {
            e.printStackTrace();
            setType(ERROR);
            setErrorMessage("参数错误");
            return getResult();
        }
        boolean byHour = timePeriod.isSingleDay();

        List<Map<String, Object>> tableDatas = new ArrayList<>();

        try {
            if (byHour) {
                for (int i = 0; i < 24; i++) {
                    Date t = new DateTime(contrast.getTime()).plusHours(i).toDate();
                    tableDatas.add(getTableDataMap(
                            new DateTime(t.getTime()).toString(DATE_TIME_FORMAT),
                            statService.getStartupCountOfDate(t, projID, verID, appID, true),
                            statService.getNewUserCountOfDate(t, projID, verID, appID, true), 0,
                            statService.getCustomEventCountOfDate(t, projID, verID, appID, true),
                            ""));
                }
            } else {
                for (Date t = contrast; t.before(current); t = DateUtil.getThisTimeOfTomorrow(t)) {
                    tableDatas.add(getTableDataMap(new DateTime(t.getTime()).toString(DATE_FORMAT),
                            statService.getStartupCountOfDate(t, projID, verID, appID, byHour),
                            statService.getNewUserCountOfDate(t, projID, verID, appID, byHour),
                            statService.getActiveUserCountOfDate(t, projID, verID, appID),
                            statService.getCustomEventCountOfDate(t, projID, verID, appID, byHour),
                            statService.getAverangeUseTimeOfDate(t, projID, verID, appID)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            setType(ERROR);
        }

        setResult(TABLE_DATA, tableDatas);

        return getResult();
    }

}
