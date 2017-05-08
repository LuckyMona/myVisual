/*
 * Copyright (C) 2015, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * ApplicationService.java
 *
 * Description
 *
 * Author TangWeicheng
 *
 * Ver 1.0, 2017-4-27, TangWeicheng, Create file
 */

package com.tplink.service;

import com.tplink.domain.ApplicationInfo;
import com.tplink.repository.ApplicationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    ApplicationRepository applicationRepository;

    public List<ApplicationInfo> getAllApplications() {

        return ApplicationInfo.fromJsonArray(applicationRepository.executeSql(
                "select app from filter where app is not null and app<>'' group by app ;"));
    }
}
