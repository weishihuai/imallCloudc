package com.imall.iportal.core.main.service;

import com.imall.commons.redis.util.EntityCacheInfo;
import com.imall.iportal.core.main.valid.EntityCacheInfoValid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;


@Validated
public interface SysCacheToolService{

    /**
     * 根据key清除缓存，key支持使用模糊,例如：*app*
     * @param pattern  key
     * @return Boolean
     */
    Boolean evictCache(@NotNull String pattern);

    /**
     *  获取实体类缓存列表
     * @param pageable 分页
     * @param cacheEntityName 类名
     */
    Page<EntityCacheInfo> query(@NotNull Pageable pageable, String cacheEntityName);

    /**
     * 更新缓存记录
      * @param entityCacheInfo
     * @return
     */
    Boolean updateCache(@NotNull EntityCacheInfoValid entityCacheInfo);
}
