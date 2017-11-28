
package com.imall.iportal.core.main.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.main.entity.JsonObjectEngine;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface JsonObjectEngineRepository extends IBaseRepository<JsonObjectEngine, Long>,JsonObjectEngineRepositoryCustom {

    @Query("select j from JsonObjectEngine j where j.jsonObjectTypeCode=?1 and j.jsonObjectId = ?2")
    JsonObjectEngine getDataAsObject(String jsonObjectTypeCode, Long jsonObjectId);

    @Query("select j from JsonObjectEngine j where j.jsonObjectTypeCode=?1 and j.jsonObjectId = ?2")
    List<JsonObjectEngine> getDataAsList(String jsonObjectTypeCode, Long jsonObjectId);

    @Modifying
    @Query("delete from JsonObjectEngine j where j.jsonObjectTypeCode=?1 and j.jsonObjectId = ?2")
    void delByObjectIdObjectTypeCode(String jsonObjectTypeCode, Long jsonObjectId);

}

