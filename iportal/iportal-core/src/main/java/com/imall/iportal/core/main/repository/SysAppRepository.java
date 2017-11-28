
package com.imall.iportal.core.main.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.main.entity.SysApp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * T_PT_SYS_APP【应用】(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SysAppRepository extends  IBaseRepository<SysApp, Long>,SysAppRepositoryCustom {

    List<SysApp> findByIsAvailable(String isAvailable);

    @Query("select a from SysApp a where a.appName = ?1")
    Page<SysApp> findByAppName(String appName, Pageable pageable);

    SysApp findByAppKey(String appKey);
}

