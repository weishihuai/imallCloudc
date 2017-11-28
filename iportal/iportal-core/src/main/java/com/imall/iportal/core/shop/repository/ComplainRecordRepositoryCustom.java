
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.ComplainRecord;
import com.imall.iportal.core.shop.vo.ComplainRecordSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface ComplainRecordRepositoryCustom{

    /**
     * 列表查询
     * @param pageable
     * @param searchParam 查询条件
     */
    Page<ComplainRecord> query(Pageable pageable, ComplainRecordSearchParam searchParam);
}

