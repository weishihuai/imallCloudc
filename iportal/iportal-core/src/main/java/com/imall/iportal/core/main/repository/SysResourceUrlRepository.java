
package com.imall.iportal.core.main.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.main.entity.SysResourceUrl;

import java.util.List;

/**
 * T_PT_SYS_RESOUCE_URL【资源URL】(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SysResourceUrlRepository extends  IBaseRepository<SysResourceUrl, Long>, SysResourceUrlRepositoryCustom {

    List<SysResourceUrl> findByResourceId(Long resourceId);

}

