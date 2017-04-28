/*
 * Copyright (C) 2015, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * RomVersion.java
 *
 * Description
 *
 * Author TangWeicheng
 *
 * Ver 1.0, 2017-4-26, TangWeicheng, Create file
 */

package com.tplink.domain;

public class RomVersion {

    private int verID;

    private String verName;

    private int projID;

    public int getVerID() {
        return verID;
    }

    public RomVersion setVerID(int verID) {
        this.verID = verID;
        return this;
    }

    public String getVerName() {
        return verName;
    }

    public RomVersion setVerName(String verName) {
        this.verName = verName;
        return this;
    }

    public int getProjID() {
        return projID;
    }

    public RomVersion setProjID(int projID) {
        this.projID = projID;
        return this;
    }

}
