package com.imall.commons.base.factory.thread;


import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ygw on 2017/2/21.
 */
public class ThreadFactory {


    private static Map<String,ThreadBus> threadBusMap = new ConcurrentHashMap<>();

    public static ThreadBus createThreadBus(int threadPoolSize){
        String key = UUID.randomUUID().toString();
        ThreadBus threadBus = new ThreadBus(key, threadPoolSize);
        threadBusMap.put(key, threadBus);
        return threadBus;
    }

    public static ThreadBus getThreadBus(String uuid){
        return threadBusMap.get(uuid);
    }

    public static ThreadBus getThreadBus(String uuid, int threadPoolSize){
        ThreadBus threadBus = threadBusMap.get(uuid);
        if(threadBus==null){
            threadBus = createThreadBus(threadPoolSize);
        }
        return threadBus;
    }

    public static Integer getThreadBusCount(){
        return threadBusMap.size();
    }

    public static Integer getThreadCount(){
        int count = 0;
        for (ThreadBus threadBus: threadBusMap.values()){
            count = count + threadBus.getThreadPoolSize();
        }
        return count;
    }

}
