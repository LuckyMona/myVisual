/*
 * Copyright (C) 2015, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * Account.java
 *
 * Description
 *
 * Author TangWeicheng
 *
 * Ver 1.0, 2017-3-10, TangWeicheng, Create file
 */

package com.tplink.domain;

public class Account {

    private String account;

    private String password;

    private String ip;

    private String key;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean checkAccount(Account other) {
        if (other == null) {
            return false;
        }
        if ((other.getPassword() == null) || !other.getAccount().equals(this.account)) {
            return false;
        }
        if ((other.getPassword() == null) || !other.getPassword().equals(this.password)) {
            return false;
        }
        return true;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getKey() {
        return key;
    }

    public Account setKey(String key) {
        this.key = key;
        return this;
    }

}
