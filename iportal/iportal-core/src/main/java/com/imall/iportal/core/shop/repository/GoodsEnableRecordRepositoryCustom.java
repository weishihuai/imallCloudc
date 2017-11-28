
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.GoodsEnableRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface GoodsEnableRecordRepositoryCustom{


    /**
     * 修改记录列表
     * @param pageable
     * @param id
     * @param shopId
     * @return
     */
    Page<GoodsEnableRecord> query(Pageable pageable, Long id, Long shopId);

}

