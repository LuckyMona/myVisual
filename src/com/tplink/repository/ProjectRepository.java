/*
 * Copyright (C) 2015, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * ProjectRepository.java
 *
 * Description
 *
 * Author TangWeicheng
 *
 * Ver 1.0, 2017-4-27, TangWeicheng, Create file
 */

package com.tplink.repository;

import com.tplink.domain.Project;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProjectRepository extends BaseRepository<Project> implements InitializingBean {

    private List<Project> pros;

    public List<Project> getAllProjects() {
        return pros;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        pros = new ArrayList<>();
        pros.add(new Project().setProjID(0).setProjName("TP901"));
        pros.add(new Project().setProjID(2).setProjName("TP902"));
        pros.add(new Project().setProjID(1).setProjName("TP903"));
        pros.add(new Project().setProjID(3).setProjName("TP904"));
        pros.add(new Project().setProjID(4).setProjName("TP905"));

    }
}
