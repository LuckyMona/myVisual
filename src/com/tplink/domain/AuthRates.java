/*
 * Copyright (C) 2015, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * AuthRates.java
 *
 * Description
 *
 * Author TangWeicheng
 *
 * Ver 1.0, 2017-4-27, TangWeicheng, Create file
 */

package com.tplink.domain;

public class AuthRates {

    private String authority;

    private String rate;

    public String getAuthority() {
        return authority;
    }

    public AuthRates setAuthority(String authority) {
        this.authority = authority;
        return this;
    }

    public String getRate() {
        return rate;
    }

    public AuthRates setRate(String rate) {
        this.rate = rate;
        return this;
    }

}
