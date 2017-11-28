package com.imall.commons.redis;

import org.springframework.cache.Cache;

import java.util.List;
import java.util.Map;


public interface IRedisCache extends Cache {

    /**
     * 通过key同时设置 hash的多个field
     */
    void hmset(Object key, Map<String, Object> value);

    /**
     * 获取Map中多个Field的值
     */
    List<Object> hmget(Object key, String... fields);

    /**
     * 写入缓存，使用默认失效时间
     */
    void put(Object key, Object value);

    /**
     * 写入缓存，不使用失效时间
     */
    void putNotEx(Object key, Object value);

    /**
     * 写入缓存，自定义超时时间
     * @param expireSeconds 超时秒数
     */
    void put(Object key, Object value, Long expireSeconds);

    /**
     * 写入缓存，将值存储到同一个key中，忽略重复值
     * 没有超时时间
     */
    void sAdd(Object key, Object value);

    /**
     * 获取某个key的value集合（set）
     */
    <T> List<T> sMembers(Object key, Class<T> type);

    /**
     *  删除集合中的一个对象
     */
    void sRem(Object key, Object value);

    /**
     * 判断一个值是否是集合的成员
     */
    Boolean sIsMember(Object key, Object member);
}
