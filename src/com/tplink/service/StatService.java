/*
 * Copyright (C) 2015, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * StatisticsService.java
 *
 * Description
 *
 * Author TangWeicheng
 *
 * Ver 1.0, 2017-4-26, TangWeicheng, Create file
 */

package com.tplink.service;

import com.tplink.domain.AuthRates;
import com.tplink.domain.ResourceUsage;
import com.tplink.repository.AppAuthRepository;
import com.tplink.repository.StatRepository;
import com.tplink.utils.DateUtil;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class StatService {

    @Autowired
    StatRepository statRepository;

    private static final String DATE_FORMAT_STRING_SQL = "%Y-%m-%d";

    private static final String HOUR_FORMAT_STRING_SQL = "%Y-%m-%d %H";

    private static final DateTimeFormatter HOUR_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd HH");

    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormat
            .forPattern("yyyy-MM-dd HH:mm:ss");

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd");

    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormat.forPattern("HH:mm:ss");

    Random ran = new Random();

    public int getStartupCountOfDate(Date date, String projID, String verID, String appID) {
        return getStartupCountOfDate(date, projID, verID, appID, false);
    }

    public int getStartupCountOfDate(Date day, String pid, String vid, String aid, boolean byhour) {
        JSONArray a = statRepository
                .executeSql("select sum(app_start_count) as count from app_start where project='"
                        + pid + "' and version='" + vid + "' and app='" + aid
                        + "' and date_format(hour_time,\""
                        + (byhour ? HOUR_FORMAT_STRING_SQL : DATE_FORMAT_STRING_SQL) + "\")='"
                        + new DateTime(day.getTime()).toString(byhour ? HOUR_FORMAT : DATE_FORMAT)
                        + "';");

        if (a == null) {
            return 0;
        }
        try {
            JSONObject o = a.getJSONObject(0);
            return o.getInt("count");
        } catch (JSONException e) {
            // e.printStackTrace();
            return 0;
        }
    }

    public int getStartupCountBetween(Date start, Date end, String pid, String vid, String aid) {
        JSONArray a = statRepository
                .executeSql("select sum(app_start_count) as count from app_start where project='"
                        + pid + "' and version='" + vid + "' and app='" + aid + "' and hour_time>'"
                        + new DateTime(start.getTime()).toString(DATE_TIME_FORMAT)
                        + "' and hour_time<'"
                        + new DateTime(end.getTime()).toString(DATE_TIME_FORMAT) + "' ;");

        if (a == null) {
            return 0;
        }
        try {
            JSONObject o = a.getJSONObject(0);
            return o.getInt("count");
        } catch (JSONException e) {
            // e.printStackTrace();
            return 0;
        }
    }

    public int getNewUserCountOfDate(Date day, String pid, String vid, String aid) {
        return getNewUserCountOfDate(day, pid, vid, aid, false);
    }

    public int getNewUserCountOfDate(Date day, String pid, String vid, String aid, boolean byhour) {
        JSONArray a = statRepository.executeSql(
                "select count(*) as count from new_user where project='" + pid + "' and app='" + aid
                        + "' and version='" + vid + "' and date_format(time,\""
                        + (byhour ? HOUR_FORMAT_STRING_SQL : DATE_FORMAT_STRING_SQL) + "\")='"
                        + new DateTime(day.getTime()).toString(byhour ? HOUR_FORMAT : DATE_FORMAT)
                        + "';");

        if (a == null) {
            return 0;
        }
        try {
            JSONObject o = a.getJSONObject(0);
            return o.getInt("count");
        } catch (JSONException e) {
            // e.printStackTrace();
            return 0;
        }
    }

    public int getNewUserCountBetween(Date start, Date end, String pid, String vid, String aid) {
        JSONArray a = statRepository
                .executeSql("select count(*) as count from new_user where project='" + pid
                        + "' and app='" + aid + "' and version='" + vid + "' time>'"
                        + new DateTime(start.getTime()).toString(DATE_TIME_FORMAT) + "' and time<'"
                        + new DateTime(end.getTime()).toString(DATE_TIME_FORMAT) + "' ;");

        if (a == null) {
            return 0;
        }
        try {
            JSONObject o = a.getJSONObject(0);
            return o.getInt("count");
        } catch (JSONException e) {
            // e.printStackTrace();
            return 0;
        }
    }

    public int getActiveUserCountOfDate(Date day, String pid, String vid, String aid) {
        JSONArray a = statRepository
                .executeSql("select count(*) as count from active_user where model='" + pid
                        + "' and app='" + aid + "' and version='" + vid
                        + "' and date_format(day_time,\"" + DATE_FORMAT_STRING_SQL + "\")='"
                        + new DateTime(day.getTime()).toString(DATE_FORMAT) + "';");

        if (a == null) {
            return 0;
        }
        try {
            JSONObject o = a.getJSONObject(0);
            return o.getInt("count");
        } catch (JSONException e) {
            // e.printStackTrace();
            return 0;
        }
    }

    public String getAverangeUseTimeOfDate(Date day, String pid, String vid, String aid) {
        JSONArray a = statRepository.executeSql(
                "select avg(avg_use_time) as avg_time from avg_use_time  where project='" + pid
                        + "' and app='" + aid + "' and version='" + vid
                        + "' and date_format(day_time,\"" + DATE_FORMAT_STRING_SQL + "\")='"
                        + new DateTime(day.getTime()).toString(DATE_FORMAT) + "';");

        int millisecond = 0;
        if ((a != null) && (a.length() > 0)) {
            try {
                JSONObject o = a.getJSONObject(0);
                millisecond = (int)o.getDouble("avg_use_time");
            } catch (JSONException e) {
                // e.printStackTrace();
                millisecond = 0;
            }
        }

        return new DateTime(0).plusMillis(millisecond).toString(TIME_FORMAT);
    }

    public String getAverangeUseTimeBetween(Date start, Date end, String pid, String vid,
            String aid) {
        JSONArray a = statRepository.executeSql(
                "select avg(avg_use_time) as avg_time from avg_use_time  where project='" + pid
                        + "' and app='" + aid + "' and version='" + vid + "' and day_time>'"
                        + new DateTime(start.getTime()).toString(DATE_TIME_FORMAT)
                        + "' and day_time<'"
                        + new DateTime(end.getTime()).toString(DATE_TIME_FORMAT) + "' ;");

        int millisecond = 0;
        if ((a != null) && (a.length() > 0)) {
            try {
                JSONObject o = a.getJSONObject(0);
                millisecond = (int)o.getDouble("avg_time");
            } catch (JSONException e) {
                // e.printStackTrace();
                millisecond = 0;
            }
        }

        return new DateTime(0).plusMillis(millisecond).toString(TIME_FORMAT);
    }

    public int getCustomEventCountOfDate(Date date, String projID, String verID, String appID) {
        return getCustomEventCountOfDate(date, projID, verID, appID, false);
    }

    public int getCustomEventCountOfDate(Date day, String pid, String vid, String aid,
            boolean byhour) {
        JSONArray a = statRepository.executeSql(
                "select sum(count) as count from event_count where  project='" + pid + "' and app='"
                        + aid + "' and version='" + vid + "' and date_format(hour_time,\""
                        + (byhour ? HOUR_FORMAT_STRING_SQL : DATE_FORMAT_STRING_SQL) + "\")='"
                        + new DateTime(day.getTime()).toString(byhour ? HOUR_FORMAT : DATE_FORMAT)
                        + "';");

        if (a == null) {
            return 0;
        }
        try {
            JSONObject o = a.getJSONObject(0);
            return o.getInt("count");
        } catch (JSONException e) {
            // e.printStackTrace();
            return 0;
        }
    }

    public int getCustomEventCountBetween(Date start, Date end, String pid, String vid,
            String aid) {
        JSONArray a = statRepository
                .executeSql("select sum(count) as count from event_count where  project='" + pid
                        + "' and app='" + aid + "' and version='" + vid + "' and hour_time>'"
                        + new DateTime(start.getTime()).toString(DATE_TIME_FORMAT)
                        + "' and hour_time<'"
                        + new DateTime(end.getTime()).toString(DATE_TIME_FORMAT) + "';");

        if (a == null) {
            return 0;
        }
        try {
            JSONObject o = a.getJSONObject(0);
            return o.getInt("count");
        } catch (JSONException e) {
            // e.printStackTrace();
            return 0;
        }
    }

    public int getTotalUserCount(String projID, String verID, String appID) {
        JSONArray a = statRepository.executeSql("select count(*) as count from new_user ;");

        if (a == null) {
            return 0;
        }
        try {
            JSONObject o = a.getJSONObject(0);
            return o.getInt("count");
        } catch (JSONException e) {
            // e.printStackTrace();
            return 0;
        }
    }

    public int getActiveUserCountBetween(Date lastWeekBefore, Date date, String projID,
            String verID, String appID) {
        JSONArray a = statRepository
                .executeSql("select count(*) as count from new_user where time>'"
                        + new DateTime(lastWeekBefore.getTime()).toString(DATE_TIME_FORMAT)
                        + "' and time< '"
                        + new DateTime(lastWeekBefore.getTime()).toString(DATE_TIME_FORMAT)
                        + "' ;");

        if (a == null) {
            return 0;
        }
        try {
            JSONObject o = a.getJSONObject(0);
            return o.getInt("count");
        } catch (JSONException e) {
            // e.printStackTrace();
            return 0;
        }
    }

    public String getWeeklyRetention(String projID, String verID, String appID) {

        String lastDayLastMonth = new DateTime().minusDays(1).minusMonths(1).toString(DATE_FORMAT);

        JSONArray a = statRepository
                .executeSql("select count(*) as count from new_user where date_format(time,\""
                        + DATE_FORMAT_STRING_SQL + "\")='" + lastDayLastMonth + "' ;");

        int newUserNum = 0;
        if (a != null) {

            try {
                JSONObject o = a.getJSONObject(0);
                newUserNum = o.getInt("count");
            } catch (JSONException e) {
                // e.printStackTrace();
                newUserNum = 0;
            }
        }

        String lastDay = new DateTime().toString(DATE_TIME_FORMAT);
        String weekAgo = new DateTime().minusDays(DateUtil.DAY_OF_WEEK).toString(DATE_TIME_FORMAT);

        a = statRepository.executeSql("select count(*) as count from new_user where time>'"
                + weekAgo + "' and time<'" + lastDay
                + "' and imei in (select imei from new_user where date_format(time,\""
                + DATE_FORMAT_STRING_SQL + "\")='" + lastDayLastMonth + "' ) ;");

        int activeNum = 0;
        if (a != null) {

            try {
                JSONObject o = a.getJSONObject(0);
                activeNum = o.getInt("count");
            } catch (JSONException e) {
                // e.printStackTrace();
                activeNum = 0;
            }
        }
        if (newUserNum == 0) {
            return 0 + "%";
        } else {
            return ((activeNum * 100) / newUserNum) + "%";
        }
    }

    public String getErrorRateOfDate(Date date, String projID, String verID, String appID) {
        return ran.nextInt(100) + "%";
    }

    @Autowired
    AppAuthRepository appAuthRepository;

    public String getAuthorityList(String projID, String verID, String appID) {
        return appAuthRepository.getAppAuthNames(projID, verID, appID).toString();
    }

    public List<AuthRates> getAuthorityRateList(String projID, String verID, String appID) {
        return appAuthRepository.getAuthRates(projID, verID, appID);
    }

    public ResourceUsage getMemoryUsage(String pid, String vid, String aid) {
        return getMemoryUsage(new Date(), pid, vid, aid);
    }

    public ResourceUsage getMemoryUsage(Date day, String pid, String vid, String aid) {

        JSONArray a = statRepository.executeSql(
                "select avg_proc as avg_usg , max_proc as max_usg from app_proc where project='"
                        + pid + "' and app='" + aid + "' and version='" + vid
                        + "' and date_format(day_time,\"" + (DATE_FORMAT_STRING_SQL) + "\")='"
                        + new DateTime(day.getTime()).toString(DATE_FORMAT) + "';");

        if ((a != null) && (a.length() > 0)) {
            try {
                JSONObject o = a.getJSONObject(0);
                return ResourceUsage.fromJsonObject(o);
            } catch (JSONException e) {
                // e.printStackTrace();
            }
        }
        return new ResourceUsage();
    }

    public ResourceUsage getDataUsage(String pid, String vid, String aid) {
        return getDataUsage(new Date(), pid, vid, aid);
    }

    public ResourceUsage getDataUsage(Date day, String pid, String vid, String aid) {

        JSONArray a = statRepository.executeSql(
                "select avg_data_use as avg_usg , max_data_use as max_usg from app_data_use where project='"
                        + pid + "' and app='" + aid + "' and version='" + vid
                        + "' and date_format(day_time,\"" + (DATE_FORMAT_STRING_SQL) + "\")='"
                        + new DateTime(day.getTime()).toString(DATE_FORMAT) + "';");

        if ((a != null) && (a.length() > 0)) {
            try {
                JSONObject o = a.getJSONObject(0);
                return ResourceUsage.fromJsonObject(o);
            } catch (JSONException e) {
                // e.printStackTrace();
            }
        }
        return new ResourceUsage();
    }

    public ResourceUsage getEleUsage(String projID, String verID, String appID) {
        ResourceUsage usage = new ResourceUsage().setAverageUsage(ran.nextInt(75) + "%")
                .setMaxUsage((75 + ran.nextInt(25)) + "%");
        return usage;
    }

}
