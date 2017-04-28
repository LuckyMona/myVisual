/*
 * Copyright (C) 2015, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * Project.java
 *
 * Description
 *
 * Author TangWeicheng
 *
 * Ver 1.0, 2017-4-26, TangWeicheng, Create file
 */

package com.tplink.domain;

public class Project {

    private int projID;

    private String projName;

    public int getProjID() {
        return projID;
    }

    public Project setProjID(int projID) {
        this.projID = projID;
        return this;
    }

    public String getProjName() {
        return projName;
    }

    public Project setProjName(String projName) {
        this.projName = projName;
        return this;
    }

}
