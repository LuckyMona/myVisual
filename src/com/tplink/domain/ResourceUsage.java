/*
 * Copyright (C) 2015, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * ResourceUsage.java
 *
 * Description
 *
 * Author TangWeicheng
 *
 * Ver 1.0, 2017-4-27, TangWeicheng, Create file
 */

package com.tplink.domain;

public class ResourceUsage {

    private String averageUsage;

    private String maxUsage;

    public String getAverageUsage() {
        return averageUsage;
    }

    public ResourceUsage setAverageUsage(String averageUsage) {
        this.averageUsage = averageUsage;
        return this;
    }

    public String getMaxUsage() {
        return maxUsage;
    }

    public ResourceUsage setMaxUsage(String maxUsage) {
        this.maxUsage = maxUsage;
        return this;
    }

}
