
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.DrugInBucket;
import com.imall.iportal.core.shop.vo.DrugInBucketSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface DrugInBucketRepositoryCustom {
    /**
     * 药品装斗列表
     *
     * @param pageable                分页对象
     * @param drugInBucketSearchParam 搜索参数
     * @return
     */
    Page<DrugInBucket> query(Pageable pageable, DrugInBucketSearchParam drugInBucketSearchParam);

}

