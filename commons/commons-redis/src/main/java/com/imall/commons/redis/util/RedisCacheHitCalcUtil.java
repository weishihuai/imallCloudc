package com.imall.commons.redis.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ZJC on 2017/4/14.
 */
public class RedisCacheHitCalcUtil {

    private static final String REQUEST_COUNT = "requestCount";
    private static final String WRITE_COUNT = "writeCount";
    private static final String HIT_COUNT = "hitCount";
    private static final String CLEAR_COUNT = "clearCount";
    private static final String KEY_PRX = "entity_";
    public static Map<String,Map<String,Integer>> calcHitMap = new ConcurrentHashMap<>();


    /**
     * 缓存请求次数统计
     * @param key  排除非实体类的缓存
     */
    public static void  requestCountCalc(String key){
        Map<String, Integer> hit = getHitMap(key);
        if(hit==null){
            return;
        }
        hit.put(REQUEST_COUNT, hit.get(REQUEST_COUNT) + 1);
    }

    /**
     * 缓存命中次数统计
     * @param key 排除非实体类的缓存
     */
    public static void hitCountCalc(String key){
        Map<String, Integer> hit = getHitMap(key);
        if(hit==null){
            return;
        }
        hit.put(HIT_COUNT, hit.get(HIT_COUNT) + 1);
    }

    /**
     * 缓存写次数统计
     * @param key 排除非实体类的缓存
     */
    public static void writeCountCalc(String key){
        Map<String, Integer> hit = getHitMap(key);
        if(hit==null){
            return;
        }
        hit.put(WRITE_COUNT, hit.get(WRITE_COUNT) + 1);
    }

    /**
     * 缓存擦除次数统计
     * @param key 排除非实体类的缓存
     */
    public static void clearCountCalc(String key){
        Map<String, Integer> hit = getHitMap(key);
        if(hit==null){
            return;
        }
        hit.put(CLEAR_COUNT, hit.get(CLEAR_COUNT) + 1);
    }


    private static Map<String,Integer> getHitMap(String key){
        if (StringUtils.isBlank(key) || key.indexOf(KEY_PRX)<=0) {
            return null;
        }
        //key格式例如：iportal:entity_com.imall.iportal.core.entity.SysApp:1-
        //截取key，截取掉参数部分
        key =  key.substring(0,key.lastIndexOf(":"));

        Map<String,Integer> hit = calcHitMap.get(key);
        if(hit == null){
            hit = buildMap();
            calcHitMap.put(key, hit);
        }
        return hit;
    }

    private static Map<String,Integer> buildMap(){
        Map<String,Integer> hitMap = new ConcurrentHashMap<>();
        hitMap.put(REQUEST_COUNT,0);
        hitMap.put(WRITE_COUNT,0);
        hitMap.put(HIT_COUNT,0);
        hitMap.put(CLEAR_COUNT,0);
        return hitMap;
    }
}
