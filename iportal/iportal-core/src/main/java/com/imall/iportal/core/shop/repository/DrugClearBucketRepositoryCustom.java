
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.DrugClearBucket;
import com.imall.iportal.core.shop.vo.DrugClearBucketSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface DrugClearBucketRepositoryCustom {
    /**
     * 药品清斗列表
     *
     * @param pageable                     分页对象
     * @param drugClearBucketSearchParam 搜索参数
     * @return
     */
    Page<DrugClearBucket> query(Pageable pageable, DrugClearBucketSearchParam drugClearBucketSearchParam);

}

