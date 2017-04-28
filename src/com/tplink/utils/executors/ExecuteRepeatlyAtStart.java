/*
 * Copyright (C) 2015, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * ExecuteRepeatlyAtStart.java
 *
 * Description
 *
 * Author TangWeicheng
 *
 * Ver 1.0, 2016-11-17, TangWeicheng, Create file
 */

package com.tplink.utils.executors;

import java.util.Date;

public class ExecuteRepeatlyAtStart {

    public ExecuteRepeatlyAtStart(Executor executor, Runnable saveUserDataService) {
        Date date = new Date();
        executor.schedulerAfterFinish(saveUserDataService, date, 5);
    }

}
