package com.imall.commons.redis.processor;

import com.imall.commons.redis.RedisUtil;
import com.imall.commons.redis.util.EntityCacheInfo;
import com.imall.commons.redis.util.RedisCacheHitCalcUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class RedisCacheHitCalcProcessor {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private static final String REQUEST_COUNT = "requestCount";
    private static final String WRITE_COUNT = "writeCount";
    private static final String HIT_COUNT = "hitCount";
    private static final String CLEAR_COUNT = "clearCount";
    private static  final String REDIS_CACHEABLE_ENTITY_KEYS = "redisCacheableEntityKeys";
    private static final String KEY_PRX = ":entity_";


    private boolean disabled;
    private boolean running;
    public static final String DISABLED_SCHEDULED_CRON = "1 1 1 1 JAN *";

    @Value("${app.redisCacheHitCalc.frequency:1 1 1 1 JAN *}")
    public void setCronValue(String value) {
        disabled = (DISABLED_SCHEDULED_CRON.equals(value));

    }

    @Scheduled(cron="${app.redisCacheHitCalc.frequency:0 0/1 * * * ?}")
    public void process() {
        if (disabled || running) {
            return;
        }
        try{
            running = true;
            Map<String,Map<String,Integer>> hitMap =  RedisCacheHitCalcUtil.calcHitMap;
            List<String> entityKeys = RedisUtil.sMembers(REDIS_CACHEABLE_ENTITY_KEYS, String.class);
            if (!hitMap.isEmpty()) {
                for (String key : entityKeys) {
                    //key格式例如：iportal:com.imall.iportal.core.entity.SysApp
                    EntityCacheInfo entityCacheInfo = (EntityCacheInfo)RedisUtil.get(key);
                    if (entityCacheInfo != null) {
                        // hitMapKey格式：iportal:entity_com.imall.iportal.core.entity.SysApp
                        String hitMapKey = entityCacheInfo.getAppName() + KEY_PRX + entityCacheInfo.getCacheEntityPath();
                        Map<String, Integer> calcResult = hitMap.get(hitMapKey);
                        if (calcResult != null) {
                            entityCacheInfo.setRequestCount(calcResult.get(REQUEST_COUNT));
                            entityCacheInfo.setWriteCount(calcResult.get(WRITE_COUNT));
                            entityCacheInfo.setHitCount(calcResult.get(HIT_COUNT));
                            entityCacheInfo.setClearCount(calcResult.get(CLEAR_COUNT));
                        }
                        RedisUtil.putNotEx(key, entityCacheInfo);//直接替换即可
                    }
                }
            }
        }catch (Throwable tr) {
            logger.error(tr.getMessage(), tr);
        }finally{
            running = false;
        }
    }
}

