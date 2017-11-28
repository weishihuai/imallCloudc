
package com.imall.iportal.core.index.repository;

import com.imall.iportal.core.index.entity.EsIndexManage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * T_PT_ES_INDEX_MANAGE【索引管理】(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface EsIndexManageRepositoryCustom{

    Page<EsIndexManage> query(Pageable pageable);
}

