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

import java.text.ParseException;
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

    private static final String BASE_COUNTS = "baseCounts";

    private static final String CONTRAST_COUNTS = "contrastCounts";

    private static final String COUNTINT_TIME = "time";

    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormat
            .forPattern("yyyy-MM-dd HH:mm:ss");

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd");

    private static Map<String, Object> getContrastDataMap(Object o1, Object o2) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(BASE_COUNTS, o1);
        map.put(CONTRAST_COUNTS, o2);
        return map;
    }

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
        }
        Date current = null;
        try {
            current = DateUtil.parseDate(timePeriod.getEnd());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setResult(ACTIVE_USER_COUNTS,
                getContrastDataMap(statService.getActiveUserCountOfDate(base, projID, verID, appID),
                        statService.getActiveUserCountOfDate(current, projID, verID, appID)));
        setResult(STARTUP_COUNTS,
                getContrastDataMap(statService.getStartupCountOfDate(base, projID, verID, appID),
                        statService.getStartupCountOfDate(current, projID, verID, appID)));
        setResult(NEW_USER_COUNT,
                getContrastDataMap(statService.getNewUserCountOfDate(base, projID, verID, appID),
                        statService.getNewUserCountOfDate(current, projID, verID, appID)));
        setResult(USING_TIME_AVERANGE,
                getContrastDataMap(statService.getAverangeUseTimeOfDate(base, projID, verID, appID),
                        statService.getAverangeUseTimeOfDate(current, projID, verID, appID)));
        setResult(CUSTOM_EVENT_COUNTS,
                getContrastDataMap(
                        statService.getCustomEventCountOfDate(base, projID, verID, appID),
                        statService.getCustomEventCountOfDate(current, projID, verID, appID)));

        setType(SUCCESS);

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
        } catch (ParseException e1) {
            e1.printStackTrace();
            current = new Date();
        }
        Date contrast = null;
        try {
            contrast = DateUtil.parseDate(timePeriod.getStart());
        } catch (Exception e) {
            e.printStackTrace();
            contrast = DateUtil.getLastWeekBefore(current);
        }
        boolean byHour = timePeriod.isSingleDay();

        List<Map<String, Object>> tableDatas = new ArrayList<>();

        if (byHour) {
            for (int i = 0; i < 24; i++) {
                Date t = new DateTime(contrast.getTime()).plusHours(i).toDate();
                tableDatas.add(getTableDataMap(new DateTime(t.getTime()).toString(DATE_TIME_FORMAT),
                        statService.getStartupCountOfDate(t, projID, verID, appID, true),
                        statService.getNewUserCountOfDate(t, projID, verID, appID, true), 0,
                        statService.getCustomEventCountOfDate(t, projID, verID, appID, true), ""));
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

        setResult(TABLE_DATA, tableDatas);

        return getResult();
    }

}
