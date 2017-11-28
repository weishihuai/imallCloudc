package com.imall.commons.base.factory.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ygw on 2017/2/21.
 */
public class ThreadBus {

    private String uuid;
    private Integer threadPoolSize;
    private ExecutorService executorService;

    public ThreadBus(String uuid, Integer threadPoolSize){
        this.uuid = uuid;
        this.threadPoolSize = threadPoolSize;
        executorService = Executors.newFixedThreadPool(threadPoolSize);
    }

    public void submitTask(Runnable runnable){
        executorService.submit(runnable);
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getThreadPoolSize() {
        return threadPoolSize;
    }

    public void setThreadPoolSize(Integer threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }
}
