/*
 * Copyright (C) 2015, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * AppAuthRepository.java
 *
 * Description
 *
 * Author TangWeicheng
 *
 * Ver 1.0, 2017-4-27, TangWeicheng, Create file
 */

package com.tplink.repository;

import com.tplink.domain.AuthRates;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AppAuthRepository extends BaseRepository<AuthRates> implements InitializingBean {

    private List<AuthRates> auths;

    public List<String> getAppAuthNames(String pid, String vid, String aid) {
        List<String> names = new ArrayList<>();
        for (AuthRates auth : auths) {
            names.add(auth.getAuthority());
        }
        return names;
    }

    public List<AuthRates> getAuthRates(String pid, String vid, String aid) {
        return auths;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        auths = new ArrayList<>();
        auths.add(new AuthRates().setAuthority("电话").setRate(ran.nextInt(100) + "%"));
        auths.add(new AuthRates().setAuthority("短信").setRate(ran.nextInt(100) + "%"));
        auths.add(new AuthRates().setAuthority("麦克风").setRate(ran.nextInt(100) + "%"));
        auths.add(new AuthRates().setAuthority("日历").setRate(ran.nextInt(100) + "%"));
        auths.add(new AuthRates().setAuthority("通讯录").setRate(ran.nextInt(100) + "%"));
        auths.add(new AuthRates().setAuthority("位置信息").setRate(ran.nextInt(100) + "%"));

    }

}
