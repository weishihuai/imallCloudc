
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.StorageSpaceMove;
import com.imall.iportal.core.shop.vo.StorageSpaceMoveSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface StorageSpaceMoveRepositoryCustom {

    /**
     * 货位移动列表
     *
     * @param pageable                    分页对象
     * @param storageSpaceMoveSearchParam 搜索参数
     * @return
     */
    Page<StorageSpaceMove> query(Pageable pageable, StorageSpaceMoveSearchParam storageSpaceMoveSearchParam);

}

