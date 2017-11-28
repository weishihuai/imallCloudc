package com.imall.commons.redis.keygenerator;

import com.google.common.hash.Hashing;
import com.imall.commons.redis.annotation.RedisCacheable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

/**
 * 实现spring cache的默认缓存实现策略
 */
public class DefaultKeyGenerator implements KeyGenerator {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // CUSTOM CACHE KEY
    private static final int NO_PARAM_KEY = 0;
    private static final int NULL_PARAM_KEY = 53;
    private static final String DEFAULT_METHOD_NAME = "findOne";
    private static final String KEY_PRX = "entity_";

    @Override
    public Object generate(Object target, Method method, Object... params) {

        Class persistentClass = getSuperClassGenericType(target.getClass(), 0);
        RedisCacheable redisCacheable = (RedisCacheable) persistentClass.getAnnotation(RedisCacheable.class);

        StringBuilder key = new StringBuilder();
        key.append(redisCacheable.appName()).append(":");

        if(method.getName().equals(DEFAULT_METHOD_NAME)){
            //方法是findOne的生成不同的key，以标识是实体对象缓存
            // key格式例如：iportal:entity_com.imall.iportal.core.entity.SysApp:1-
            key.append(KEY_PRX).append(redisCacheable.value().getName()).append(":");
        }else{
            // key格式例如：iportal:com.imall.iportal.core.service.impl.SysAppServiceImpl.findByIsAvailable:Y-
            key.append(target.getClass().getName()).append(".").append(method.getName()).append(":");
        }


        if (params.length == 0) {
            return key.append(NO_PARAM_KEY).toString();
        }

        for (Object param : params) {
            if (param == null) {
                logger.warn("input null param for Spring cache, use default key={}", NULL_PARAM_KEY);
                key.append(NULL_PARAM_KEY);
            } else if (ClassUtils.isPrimitiveArray(param.getClass())) {
                int length = Array.getLength(param);
                for (int i = 0; i < length; i++) {
                    key.append(Array.get(param, i));
                    key.append(',');
                }
            } else if (ClassUtils.isPrimitiveOrWrapper(param.getClass()) || param instanceof String) {
                key.append(param);
            } else {
                logger.warn("Using an object as a cache key may lead to unexpected results. " +
                        "Either use @Cacheable(key=..) or implement CacheKey. Method is " + target.getClass() + "#" + method.getName());
                key.append(param.hashCode());
            }
            key.append('-');
        }

        String finalKey = key.toString();
        long cacheKeyHash = Hashing.murmur3_128().hashString(finalKey, Charset.defaultCharset()).asLong();
        logger.debug("using cache key={} hashCode={}", finalKey, cacheKeyHash);
        return key.toString();
    }

    @SuppressWarnings("unchecked")
    public static Class<Object> getSuperClassGenericType(final Class clazz, final int index) {

        //返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        //返回表示此类型实际类型参数的 Type 对象的数组。
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }

        return (Class) params[index];
    }

}