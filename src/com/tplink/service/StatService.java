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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@SuppressWarnings("deprecation")
public class StatService {

    Random ran = new Random();

    public int getStartupCountOfDate(Date day, int pid, int vid, int aid) {
        return ran.nextInt(day.getYear());
    }

    public int getNewUserCountOfDate(Date day, int pid, int vid, int aid) {
        return ran.nextInt(day.getYear());
    }

    public int getActiveUserCountOfDate(Date day, int pid, int vid, int aid) {
        return ran.nextInt(day.getYear());
    }

    public String getAverangeUseTimeOfDate(Date day, int pid, int vid, int aid) {
        return DateFormat.getTimeInstance().format(ran.nextInt(86400000));
    }

    public static void main(String[] args) {
        System.out.println(DateFormat.getTimeInstance().format(new Date()));
    }

    public int getCustomEventCountOfDate(Date day, int pid, int vid, int aid) {
        return ran.nextInt(86400);
    }

    public int getTotalUserCount(int projID, int verID, int appID) {
        return ran.nextInt(1000);
    }

    public int getActiveUserCountBetween(Date lastWeekBefore, Date date, int projID, int verID,
            int appID) {
        return ran.nextInt(1000);
    }

    public String getWeeklyRetention(int projID, int verID, int appID) {
        return ran.nextInt(100) + "%";
    }

    public String getErrorRateOfDate(Date date, int projID, int verID, int appID) {
        return ran.nextInt(100) + "%";
    }

    @Autowired
    AppAuthRepository appAuthRepository;

    public String getAuthorityList(int projID, int verID, int appID) {
        return appAuthRepository.getAppAuthNames(projID, verID, appID).toString();
    }

    public List<AuthRates> getAuthorityRateList(int projID, int verID, int appID) {
        return appAuthRepository.getAuthRates(projID, verID, appID);
    }

    public ResourceUsage getMemoryUsage(int projID, int verID, int appID) {
        ResourceUsage usage = new ResourceUsage().setAverageUsage(ran.nextInt(75) + "%")
                .setMaxUsage((75 + ran.nextInt(25)) + "%");
        return usage;
    }

    public ResourceUsage getDataUsage(int projID, int verID, int appID) {
        ResourceUsage usage = new ResourceUsage().setAverageUsage(ran.nextInt(75) + "%")
                .setMaxUsage((75 + ran.nextInt(25)) + "%");
        return usage;
    }

    public ResourceUsage getEleUsage(int projID, int verID, int appID) {
        ResourceUsage usage = new ResourceUsage().setAverageUsage(ran.nextInt(75) + "%")
                .setMaxUsage((75 + ran.nextInt(25)) + "%");
        return usage;
    }

}
