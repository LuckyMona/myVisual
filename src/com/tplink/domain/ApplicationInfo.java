/*
 * Copyright (C) 2015, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * ApplicationInfo.java
 *
 * Description
 *
 * Author TangWeicheng
 *
 * Ver 1.0, 2017-5-6, TangWeicheng, Create file
 */

package com.tplink.domain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ApplicationInfo {

    private String appID;

    private String appName;

    public String getAppID() {
        return appID;
    }

    @Override
    public String toString() {
        return appName;
    }

    public ApplicationInfo setAppID(String appID) {
        this.appID = appID;
        return this;
    }

    public String getAppName() {
        return appName;
    }

    public ApplicationInfo setAppName(String appName) {
        this.appName = appName;
        return this;
    }

    public static ApplicationInfo fromJsonObject(JSONObject o) {
        if (o == null) {
            return null;
        }
        ApplicationInfo in = new ApplicationInfo();
        try {
            in.setAppID(o.getString("app"));
            in.setAppName(o.getString("app"));
        } catch (JSONException e) {
            e.printStackTrace();
            in = null;
        }
        return in;
    }

    public static List<ApplicationInfo> fromJsonArray(JSONArray arr) {
        if (arr == null) {
            return null;
        }
        List<ApplicationInfo> list = new ArrayList<ApplicationInfo>();
        for (int i = 0; i < arr.length(); i++) {
            ApplicationInfo info = null;
            try {
                info = fromJsonObject(arr.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (info != null) {
                list.add(info);
            }
        }
        return list;
    }

}
