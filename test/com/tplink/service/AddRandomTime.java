/*
 * Copyright (C) 2015, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * AddRandomTime.java
 *
 * Description
 *
 * Author TangWeicheng
 *
 * Ver 1.0, 2017-5-8, TangWeicheng, Create file
 */

package com.tplink.service;

import static org.junit.Assert.assertEquals;

import com.tplink.repository.StatRepository;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration({
        "/root-context.xml"
})
public class AddRandomTime {

    @Autowired
    StatRepository statRepository;

    @Autowired
    StatService statService;

    private Random ran = new Random();

    private static final int MINUTES_OF_A_MONTH = 30 * 24 * 60;

    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormat
            .forPattern("yyyy-MM-dd HH:mm:ss");

    private static final long imeiStart = 100000000000000l;

    private String getRandomImei() {
        String imei = (ran.nextInt(Integer.MAX_VALUE) + imeiStart) + "";
        return imei;
    }

    @Test
    public void addRandomDateForActiveUser() {

        JSONArray a = statRepository.executeSql("select count(*) as count from active_user;");

        int count = 0;
        if (a == null) {
            count = 0;
        }
        try {
            JSONObject o = a.getJSONObject(0);
            count = o.getInt("count");
        } catch (JSONException e) {
            e.printStackTrace();
            count = 0;
        }

        assertEquals(count > 0, true);

        for (int i = 1; i < (count + 1); i++) {
            statRepository.executeUpdate("update active_user set day_time='"
                    + new DateTime().minusMonths(1).plusMinutes(ran.nextInt(MINUTES_OF_A_MONTH))
                            .toString(DATE_TIME_FORMAT)
                    + "', imei='" + getRandomImei() + "' where id=" + i + ";");
        }

    }

    @Test
    public void addRandomDateForAppDataUse() {

        JSONArray a = statRepository.executeSql("select count(*) as count from app_data_use;");

        int count = 0;
        if (a == null) {
            count = 0;
        }
        try {
            JSONObject o = a.getJSONObject(0);
            count = o.getInt("count");
        } catch (JSONException e) {
            e.printStackTrace();
            count = 0;
        }

        assertEquals(count > 0, true);

        for (int i = 1; i < (count + 1); i++) {
            statRepository.executeUpdate("update app_data_use set day_time='"
                    + new DateTime().minusMonths(1).plusMinutes(ran.nextInt(MINUTES_OF_A_MONTH))
                            .toString(DATE_TIME_FORMAT)
                    + "' where id=" + i + ";");
        }

    }

    @Test
    public void addRandomDateForAppProc() {

        JSONArray a = statRepository.executeSql("select count(*) as count from app_proc;");

        int count = 0;
        if (a == null) {
            count = 0;
        }
        try {
            JSONObject o = a.getJSONObject(0);
            count = o.getInt("count");
        } catch (JSONException e) {
            e.printStackTrace();
            count = 0;
        }

        assertEquals(count > 0, true);

        for (int i = 1; i < (count + 1); i++) {
            statRepository.executeUpdate("update app_proc set day_time='"
                    + new DateTime().minusMonths(1).plusMinutes(ran.nextInt(MINUTES_OF_A_MONTH))
                            .toString(DATE_TIME_FORMAT)
                    + "' where id=" + i + ";");
        }

    }

    @Test
    public void addRandomDateForAppStart() {

        JSONArray a = statRepository.executeSql("select count(*) as count from app_start;");

        int count = 0;
        if (a == null) {
            count = 0;
        }
        try {
            JSONObject o = a.getJSONObject(0);
            count = o.getInt("count");
        } catch (JSONException e) {
            e.printStackTrace();
            count = 0;
        }

        assertEquals(count > 0, true);

        for (int i = 1; i < (count + 1); i++) {
            statRepository.executeUpdate("update app_start set hour_time='"
                    + new DateTime().minusMonths(1).plusMinutes(ran.nextInt(MINUTES_OF_A_MONTH))
                            .toString(DATE_TIME_FORMAT)
                    + "' where id=" + i + ";");
        }

    }

    @Test
    public void addRandomDateForAvgUseTime() {

        JSONArray a = statRepository.executeSql("select count(*) as count from avg_use_time;");

        int count = 0;
        if (a == null) {
            count = 0;
        }
        try {
            JSONObject o = a.getJSONObject(0);
            count = o.getInt("count");
        } catch (JSONException e) {
            e.printStackTrace();
            count = 0;
        }

        assertEquals(count > 0, true);

        for (int i = 1; i < (count + 1); i++) {
            statRepository.executeUpdate("update avg_use_time set day_time='"
                    + new DateTime().minusMonths(1).plusMinutes(ran.nextInt(MINUTES_OF_A_MONTH))
                            .toString(DATE_TIME_FORMAT)
                    + "' where id=" + i + ";");
        }

    }

    @Test
    public void addRandomDateForeventCOunt() {

        JSONArray a = statRepository.executeSql("select count(*) as count from event_count;");

        int count = 0;
        if (a == null) {
            count = 0;
        }
        try {
            JSONObject o = a.getJSONObject(0);
            count = o.getInt("count");
        } catch (JSONException e) {
            e.printStackTrace();
            count = 0;
        }

        assertEquals(count > 0, true);

        for (int i = 1; i < (count + 1); i++) {
            statRepository.executeUpdate("update event_count set hour_time='"
                    + new DateTime().minusMonths(1).plusMinutes(ran.nextInt(MINUTES_OF_A_MONTH))
                            .toString(DATE_TIME_FORMAT)
                    + "' where id=" + i + ";");
        }

    }

    @Test
    public void addRandomDateForNewUser() {

        JSONArray a = statRepository.executeSql("select count(*) as count from new_user;");

        int count = 0;
        if (a == null) {
            count = 0;
        }
        try {
            JSONObject o = a.getJSONObject(0);
            count = o.getInt("count");
        } catch (JSONException e) {
            e.printStackTrace();
            count = 0;
        }

        assertEquals(count > 0, true);

        for (int i = 1; i < (count + 1); i++) {
            statRepository.executeUpdate("update new_user set time='"
                    + new DateTime().minusMonths(1).plusMinutes(ran.nextInt(MINUTES_OF_A_MONTH))
                            .toString(DATE_TIME_FORMAT)
                    + "', imei='" + getRandomImei() + "' where id=" + i + ";");
        }

    }
}
