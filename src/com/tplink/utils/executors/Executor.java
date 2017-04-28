/*
 * Copyright (C) 2011, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * ExecutorUtil.java
 *
 * Description thread pool executor
 *
 * Author chenjiuhui@tp-link.net
 *
 * Ver 1.0, Feb 2, 2016, Chen Jiuhui, Create file
 */

package com.tplink.utils.executors;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * thread pool executor
 */
@Component("executor")
public class Executor {// NO_UCD (use private)

    private static final int POOL_SIZE = 4;

    private int poolSize;

    private static Executor exe;

    private ThreadPoolTaskScheduler executor;

    private Executor() {
        if (poolSize <= 0) {
            poolSize = POOL_SIZE;
        }
        executor = new ThreadPoolTaskScheduler();
        executor.setPoolSize(poolSize);
        executor.initialize();
    }

    public static final Executor getInstance() {
        if (exe == null) {
            exe = new Executor();
        }

        return exe;
    }

    /**
     * @param task
     * @param date
     */
    public void scheduler(Runnable task, Date date) {
        executor.schedule(task, date);
    }

    /**
     * @param task
     * @param date
     */
    public void schedulerAfterFinish(Runnable task, Date date, long period) {
        executor.scheduleWithFixedDelay(task, date, period);
    }

    /**
     * @param task
     * @param trigger
     */
    public void scheduler(Runnable task, Trigger trigger) {
        executor.schedule(task, trigger);
    }

    /**
     * @param task
     * @param period the interval between successive executions of the task (in
     *            milliseconds)
     */
    public void scheduler(Runnable task, int period) {
        executor.scheduleAtFixedRate(task, period);
    }

    /**
     * @param task
     * @param date
     * @param millisecondsPerDay the interval between successive executions of
     *            the task (in milliseconds)
     */
    public void scheduler(Runnable task, Date date, long millisecondsPerDay) {
        executor.scheduleAtFixedRate(task, date, millisecondsPerDay);
    }

    public void execute(Runnable task) {
        executor.execute(task);
    }

    public void destory() {
        executor.destroy();
    }

    public int getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }
}
