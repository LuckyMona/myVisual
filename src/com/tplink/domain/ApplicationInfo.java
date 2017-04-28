
package com.tplink.domain;

public class ApplicationInfo {

    private int appID;

    private String appName;

    public int getAppID() {
        return appID;
    }

    public ApplicationInfo setAppID(int appID) {
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

}
