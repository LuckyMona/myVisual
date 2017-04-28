/*
 * Copyright (C) 2015, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * ExecuteOneTimeAtStart.java
 *
 * Description
 *
 * Author TangWeicheng
 *
 * Ver 1.0, 2017-1-4, TangWeicheng, Create file
 */

package com.tplink.utils.executors;

public class ExecuteOneTimeAtStart {

    public ExecuteOneTimeAtStart(Executor executor, Runnable saveUserDataService) {

        executor.execute(saveUserDataService);
    }

}
