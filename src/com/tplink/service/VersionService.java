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

import com.tplink.domain.RomVersion;
import com.tplink.repository.VersionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VersionService {
    @Autowired
    VersionRepository versionRepository;

    public List<RomVersion> getAllRomVersions() {
        return versionRepository.getAllRomVerisons();
    }
}
