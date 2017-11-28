
package com.imall.iportal.core.main.repository;

import com.imall.iportal.core.main.entity.SysApp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * T_PT_SYS_APP【应用】(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SysAppRepositoryCustom {

    Page<SysApp> query(Pageable pageable, String appName, String appCname, String hostname, String isAvailable) ;

}

