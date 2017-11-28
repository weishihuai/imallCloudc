package com.imall.commons.redis;

import com.imall.commons.redis.annotation.RedisCacheable;
import com.imall.commons.redis.util.EntityCacheInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;


public class RedisCacheInitBean implements ApplicationContextAware, InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisCacheInitBean.class);
    private static final String DATE_PATTERN = "yyMMddHHmmssSSS";
    private static  final String REDIS_CACHEABLE_ENTITY_KEYS = "redisCacheableEntityKeys";

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {

        Map<String, Object> serviceBeanMap = ctx.getBeansWithAnnotation(RedisCacheable.class);

        if (!serviceBeanMap.isEmpty()) {
            for (Object bean : serviceBeanMap.values()) {
                RedisCacheable redisCacheable = bean.getClass().getAnnotation(RedisCacheable.class);
                System.out.println("使用到缓存的类=" + redisCacheable.value().getName());

                //entityKey格式例如：iportal:com.imall.iportal.core.entity.SysApp
                String entityKey = redisCacheable.appName() + ":" + redisCacheable.value().getName();
                if (RedisUtil.sIsMember(REDIS_CACHEABLE_ENTITY_KEYS, entityKey)) {
                    continue;
                }

                EntityCacheInfo entityCacheInfo  = new EntityCacheInfo();
                entityCacheInfo.setCacheEntityName(redisCacheable.value().getSimpleName());
                entityCacheInfo.setCacheEntityPath(redisCacheable.value().getName());
                entityCacheInfo.setAppName(redisCacheable.appName());
                entityCacheInfo.setCacheEntityExpireSeconds(86400);
                entityCacheInfo.setRequestCount(0);
                entityCacheInfo.setWriteCount(0);
                entityCacheInfo.setHitCount(0);
                entityCacheInfo.setClearCount(0);
                entityCacheInfo.setKey(entityKey);

                RedisUtil.sAdd(REDIS_CACHEABLE_ENTITY_KEYS, entityKey);
                RedisUtil.putNotEx(entityKey, entityCacheInfo);
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

}
