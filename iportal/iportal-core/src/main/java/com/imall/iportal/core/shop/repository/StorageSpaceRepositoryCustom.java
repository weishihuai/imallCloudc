
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.StorageSpace;
import com.imall.iportal.core.shop.vo.StorageSpaceSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface StorageSpaceRepositoryCustom {

    /**
     * 分页查询商品货位信息
     *
     * @param pageable                分页对象
     * @param storageSpaceSearchParam 搜索参数
     * @return
     */
    Page<StorageSpace> query(Pageable pageable, StorageSpaceSearchParam storageSpaceSearchParam);

}

