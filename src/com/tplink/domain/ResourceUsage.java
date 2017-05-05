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

import org.json.JSONException;
import org.json.JSONObject;

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

    public static ResourceUsage fromJsonObject(JSONObject o) {
        if (o == null) {
            return null;
        }
        ResourceUsage in = new ResourceUsage();
        try {
            in.setAverageUsage(o.getString("avg_usg"));
            in.setMaxUsage(o.getString("max_usg"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return in;
    }

}
