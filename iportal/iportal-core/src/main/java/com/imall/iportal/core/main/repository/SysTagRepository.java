
package com.imall.iportal.core.main.repository;

import com.imall.iportal.core.main.entity.*;
import com.imall.commons.base.dao.IBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 标签(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SysTagRepository extends  IBaseRepository<SysTag, Long>,SysTagRepositoryCustom {

    @Query("select a from SysTag a where a.tagTypeCode =:tagTypeCode and a.sysOrgId =:sysOrgId")
    List<SysTag> findByTagTypeCodeAndSysOrgId(@Param("tagTypeCode") String tagTypeCode, @Param("sysOrgId") Long sysOrgId);


    @Query("select a from SysTag a where a.tagTypeCode =:tagTypeCode and a.tagValue =:tagValue and a.sysOrgId =:sysOrgId")
    List<SysTag> findByTagTypeCodeAndTagValueAndSysOrgId(@Param("tagTypeCode") String tagTypeCode, @Param("tagValue") String tagValue, @Param("sysOrgId") Long sysOrgId);
}

