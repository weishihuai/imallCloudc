package com.imall.iportal.core.index.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.index.entity.EsIndexQueue;
import org.springframework.data.jpa.repository.Query;

public interface EsIndexQueueRepository extends IBaseRepository<EsIndexQueue, Long>, EsIndexQueueRepositoryCustom {

    @Query("select count(e) from EsIndexQueue e where e.indexTypeCode = ?1")
    int countByIndexTypeCode(String indexTypeCode);
}
