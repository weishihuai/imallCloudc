package com.imall.commons.redis;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.List;
import java.util.Map;

public class RedisUtil {

    /**
     * 读取缓存
     */
    public static Object get(Object key){
        Cache.ValueWrapper valueWrapper = getCache().get(key);
        if (valueWrapper == null) {
            return null;
        }
        return valueWrapper.get();
    }

    /**
     * 写入缓存，使用默认失效时间
     */
    public static void put(Object key, Object value){
        getCache().put(key, value);
    }

    /**
     * 写入缓存，不使用失效时间
     */
    public static void putNotEx(Object key, Object value){
        getCache().putNotEx(key, value);
    }

    /**
     * 写入缓存,自定义失效时间
     */
    public static void put(final Object key, final Object value,final Long expireSeconds){
        getCache().put(key, value, expireSeconds);
    }

    /**
     * 删除缓存
     */
    public static void evict(Object key){
        getCache().evict(key);
    }

    /**
     * 写入缓存，将值存储到同一个key中，忽略重复值
     * 没有超时时间
     */
    public static void sAdd(Object key, Object value){
        getCache().sAdd(key, value);
    }

    /**
     * 通过key同时设置 hash的多个field
     */
    public static void hmset(Object key, Map<String,Object> value){
        getCache().hmset(key, value);
    }

    /**
     * 获取Map中多个Field的值
     */
    public static List<Object> hmget(Object key, String... fields){
        return getCache().hmget(key, fields);
    }

    /**
     * 获取某个key的value集合（set）
     */
    public static <T> List<T> sMembers(Object key, Class<T> type){
        return  getCache().sMembers(key, type);
    }

    /**
     *  删除集合中的一个对象
     */
    public static void sRem(Object key, Object value){
        getCache().sRem(key,value);
    }

    /**
     * 判断一个值是否是集合的成员
     */
    public static Boolean sIsMember(Object key, final Object member){
        return getCache().sIsMember(key,member);
    }

    /**
     * 清空缓存
     */
    public static void clear(){
        getCache().clear();
    }


    private static RedisCache cache;
    private static CacheManager manager;

    public void setManager(CacheManager manager) {
        setStaticManager(manager);
    }

    private static synchronized void setStaticManager(CacheManager manager) {
        RedisUtil.manager = manager;
    }


    private static RedisCache getCache(){
        if (cache == null && manager !=null) {
            cache = (RedisCache) manager.getCache("commonCache");
        }
        if(cache==null){
            throw new RuntimeException("RedisCache getCache 失败......");
        }
        return cache;
    }

}
