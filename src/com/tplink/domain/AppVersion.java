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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AppVersion {

    private String verID;

    private String verName;

    private String appID;

    public String getVerID() {
        return verID;
    }

    public AppVersion setVerID(String verID) {
        this.verID = verID;
        return this;
    }

    public String getVerName() {
        return verName;
    }

    public AppVersion setVerName(String verName) {
        this.verName = verName;
        return this;
    }

    public String getAppID() {
        return appID;
    }

    public AppVersion setAppID(String appID) {
        this.appID = appID;
        return this;
    }

    public static AppVersion fromJsonObject(JSONObject o) {
        if (o == null) {
            return null;
        }
        AppVersion in = new AppVersion();
        try {
            in.setVerID(o.getString("version"));
            in.setAppID(o.getString("app"));
            in.setVerName(o.getString("version"));
        } catch (JSONException e) {
            e.printStackTrace();
            in = null;
        }
        return in;
    }

    public static List<AppVersion> fromJsonArray(JSONArray arr) {
        if (arr == null) {
            return null;
        }
        List<AppVersion> list = new ArrayList<AppVersion>();
        for (int i = 0; i < arr.length(); i++) {
            AppVersion info = null;
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
