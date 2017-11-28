package com.imall.iportal.core.main.service.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.redis.RedisUtil;
import com.imall.commons.redis.util.EntityCacheInfo;
import com.imall.iportal.core.main.service.SysCacheToolService;
import com.imall.iportal.core.main.valid.EntityCacheInfoValid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.Collator;
import java.util.*;


@Service
public class SysCacheToolServiceImpl implements SysCacheToolService {

    private static  final String REDIS_CACHEABLE_ENTITY_KEYS = "redisCacheableEntityKeys";
    private static Collator collator = Collator.getInstance(Locale.ENGLISH);

    @Override
    public Boolean evictCache(String pattern) {
        RedisUtil.evict(pattern);
        return true;
    }

    @Override
    public Page<EntityCacheInfo> query(Pageable pageable, String cacheEntityName) {

        List<String> keys = RedisUtil.sMembers(REDIS_CACHEABLE_ENTITY_KEYS, String.class);

        List<EntityCacheInfo> result = new ArrayList<EntityCacheInfo>();
        if(StringUtils.isNotBlank(cacheEntityName)){
            for (String key : keys) {
                EntityCacheInfo entityCacheInfo = (EntityCacheInfo)RedisUtil.get(key);
                if (entityCacheInfo != null && entityCacheInfo.getCacheEntityPath().indexOf(cacheEntityName) > 0) {
                    result.add(entityCacheInfo);
                }
            }
        }else{
            for (String key : keys) {
                EntityCacheInfo entityCacheInfo = (EntityCacheInfo)RedisUtil.get(key);
                result.add(entityCacheInfo);
            }
        }

        Collections.sort(result, new Comparator<EntityCacheInfo>() {
            @Override
            public int compare(EntityCacheInfo o1,  EntityCacheInfo o2) {
                return collator.compare(o1.getCacheEntityName(),  o2.getCacheEntityName());
            }
        });

        int total = result.size();
        int page = pageable.getPageNumber()+1;
        List<EntityCacheInfo> newList = result.subList(pageable.getPageSize() * pageable.getPageNumber(), ((pageable.getPageSize() * page) > total ? total : (pageable.getPageSize()  * page)));

        return new PageImpl<EntityCacheInfo>(newList, pageable, result.size());
    }

    @Override
    public Boolean updateCache(EntityCacheInfoValid entityCacheInfoValid) {
        EntityCacheInfo entityCacheInfo = (EntityCacheInfo)RedisUtil.get(entityCacheInfoValid.getKey());
        if (entityCacheInfo == null ) {
            entityCacheInfo = CommonUtil.copyProperties(entityCacheInfoValid,new EntityCacheInfo());
        }
        entityCacheInfo.setCacheEntityExpireSeconds(entityCacheInfoValid.getCacheEntityExpireSeconds());//目前只支持修改失效时间
        RedisUtil.putNotEx(entityCacheInfoValid.getKey(), entityCacheInfo);//直接替换即可
        return true;
    }
}
