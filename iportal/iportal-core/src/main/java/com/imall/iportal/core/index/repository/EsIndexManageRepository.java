
package com.imall.iportal.core.index.repository;

import com.imall.iportal.core.index.entity.*;
import com.imall.commons.base.dao.IBaseRepository;

/**
 * T_PT_ES_INDEX_MANAGE【索引管理】(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface EsIndexManageRepository extends  IBaseRepository<EsIndexManage, Long>,EsIndexManageRepositoryCustom {

    EsIndexManage getByIndexTypeCode(String indexTypeCode);

}

