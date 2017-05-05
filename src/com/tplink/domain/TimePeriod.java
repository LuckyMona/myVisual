/*
 * Copyright (C) 2015, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * TimePeriod.java
 *
 * Description
 *
 * Author TangWeicheng
 *
 * Ver 1.0, 2017-5-5, TangWeicheng, Create file
 */

package com.tplink.domain;

public class TimePeriod {

    private String start;

    private String end;

    public String getStart() {
        return start;
    }

    public TimePeriod setStart(String start) {
        this.start = start;
        return this;
    }

    public String getEnd() {
        return end;
    }

    public TimePeriod setEnd(String end) {
        this.end = end;
        return this;
    }

    public boolean isSingleDay() {
        return start.equals(end);
    }

}
