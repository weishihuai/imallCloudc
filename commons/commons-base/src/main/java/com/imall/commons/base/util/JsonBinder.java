package com.imall.commons.base.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * json与对象的序列化
 * User: yang
 * Date: 2016-9-2
 *
 */

public class JsonBinder {
    private static Logger logger = LoggerFactory.getLogger(JsonBinder.class);
    private static JsonBinder normalBinder;

    private JsonBinder(JsonSerialize.Inclusion always) {

    }

    /**
     * 创建输出全部属性到Json字符串的Binder.
     */
    public static synchronized  JsonBinder buildNormalBinder() {
        if(normalBinder==null){
            normalBinder = new JsonBinder(JsonSerialize.Inclusion.ALWAYS);
        }
        return normalBinder;
    }

    /**
     * 如果JSON字符串为Null或"null"字符串,返回Null.
     * 如果JSON字符串为"[]",返回空集合.
     * 如果需要返回数组，使用数据类型如Product[].class
     * <p/>
     */
    public static <T> T toBeanObject(String jsonString, Class<T> clazz) {
        if(StringUtils.isBlank(jsonString)){
            return null;
        }
        return JSON.parseObject(jsonString, clazz);
    }

    public static <T> List<T> toBeanList(String jsonString, Class<T> clazz) {
        if(StringUtils.isBlank(jsonString)){
            return null;
        }
        return JSON.parseArray(jsonString, clazz);
    }

    //List<Map<String, Object>> listMap = JSON.parseObject(jsonString, new TypeReference<List<Map<String,Object>>>(){});
    public static <T> T toBeanMap(String jsonString) {
        if(StringUtils.isBlank(jsonString)){
            return null;
        }
        return JSON.parseObject(jsonString, new TypeReference<T>(){});
    }

    //不能用
  /*  @Deprecated
    public static <T> T toBean(String jsonString) {
        if(StringUtils.isBlank(jsonString)){
            return null;
        }
        return JSON.parseObject(jsonString, new TypeReference<T>(){});
    }*/

    /**
     * 如果对象为Null,返回"null".
     * 如果集合为空集合,返回"[]".
     */
    public static String toJson(Object object) {
        if(object==null){
            return null;
        }
        return JSON.toJSONString(object);
    }

}
