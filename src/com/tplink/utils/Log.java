/*
 * Copyright (C) 2011, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * Log.java
 *
 * Description default logger
 *
 * Author chenjiuhui@tp-link.net
 *
 * Ver 1.0, Jan 20, 2016, Chen Jiuhui, Create file
 */

package com.tplink.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * default logger
 */
public class Log {

    private static final Logger THREAD = LogManager.getLogger("thread");

    private static final Logger INFO = LogManager.getLogger("info");

    private static final Logger ERROR = LogManager.getLogger("error");

    public static final void thread(String log) {
        // System.out.println(log);
        try {
            THREAD.info(log);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final void i(String log) {
        try {
            INFO.info(log);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void e(StackTraceElement[] log) {
        try {
            ERROR.error(log);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public static void e(String log) {
        try {
            ERROR.error(log);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
