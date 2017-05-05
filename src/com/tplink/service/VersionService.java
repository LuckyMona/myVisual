/*
 * Copyright (C) 2015, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * VersionService.java
 *
 * Description
 *
 * Author TangWeicheng
 *
 * Ver 1.0, 2017-4-27, TangWeicheng, Create file
 */

package com.tplink.service;

import com.tplink.domain.AppVersion;
import com.tplink.repository.VersionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VersionService {
    @Autowired
    VersionRepository versionRepository;

    public List<AppVersion> getAllAppVersions() {
        return AppVersion.fromJsonArray(versionRepository
                .executeSql("select version,app from filter group by version ,app ;"));
    }
}
