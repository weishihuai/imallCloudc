
package com.imall.iportal.core.main.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.main.entity.SysZone;

import java.util.List;

/**
 * T_PT_SYS_ZONE【地区表】(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SysZoneRepository extends  IBaseRepository<SysZone, Long> {

    List<SysZone> findByParentId(Long parentId);

    SysZone findByNodeCode(String nodeCode);

}

