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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Project {

    private String projID;

    private String projName;

    public String getProjID() {
        return projID;
    }

    public Project setProjID(String projID) {
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

    public static Project fromJsonObject(JSONObject o) {
        if (o == null) {
            return null;
        }
        Project in = new Project();
        try {
            in.setProjID(o.getString("project"));
            in.setProjName(o.getString("project"));
        } catch (JSONException e) {
            e.printStackTrace();
            in = null;
        }
        return in;
    }

    public static List<Project> fromJsonArray(JSONArray arr) {
        if (arr == null) {
            return null;
        }
        List<Project> list = new ArrayList<Project>();
        for (int i = 0; i < arr.length(); i++) {
            Project info = null;
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
