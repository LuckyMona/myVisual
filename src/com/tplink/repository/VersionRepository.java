/*
 * Copyright (C) 2015, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * VersionRepository.java
 *
 * Description
 *
 * Author TangWeicheng
 *
 * Ver 1.0, 2017-4-27, TangWeicheng, Create file
 */

package com.tplink.repository;

import com.tplink.domain.RomVersion;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VersionRepository extends BaseRepository<RomVersion> implements InitializingBean {

    private List<RomVersion> vers;

    public List<RomVersion> getAllRomVerisons() {
        return vers;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        vers = new ArrayList<>();
        vers.add(new RomVersion().setProjID(0).setVerID(0)
                .setVerName("ROMVER" + ran.nextInt(Integer.MAX_VALUE)));
        vers.add(new RomVersion().setProjID(0).setVerID(1)
                .setVerName("ROMVER" + ran.nextInt(Integer.MAX_VALUE)));
        vers.add(new RomVersion().setProjID(1).setVerID(2)
                .setVerName("ROMVER" + ran.nextInt(Integer.MAX_VALUE)));
        vers.add(new RomVersion().setProjID(2).setVerID(3)
                .setVerName("ROMVER" + ran.nextInt(Integer.MAX_VALUE)));
        vers.add(new RomVersion().setProjID(2).setVerID(4)
                .setVerName("ROMVER" + ran.nextInt(Integer.MAX_VALUE)));
        vers.add(new RomVersion().setProjID(2).setVerID(5)
                .setVerName("ROMVER" + ran.nextInt(Integer.MAX_VALUE)));
        vers.add(new RomVersion().setProjID(2).setVerID(6)
                .setVerName("ROMVER" + ran.nextInt(Integer.MAX_VALUE)));
        vers.add(new RomVersion().setProjID(3).setVerID(7)
                .setVerName("ROMVER" + ran.nextInt(Integer.MAX_VALUE)));
        vers.add(new RomVersion().setProjID(3).setVerID(8)
                .setVerName("ROMVER" + ran.nextInt(Integer.MAX_VALUE)));
        vers.add(new RomVersion().setProjID(5).setVerID(9)
                .setVerName("ROMVER" + ran.nextInt(Integer.MAX_VALUE)));
    }

}
