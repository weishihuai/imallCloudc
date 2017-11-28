
package com.imall.iportal.core.main.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.main.entity.SysJob;

import java.util.List;

/**
 * T_PT_SYS_JOB【岗位/职务】(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SysJobRepository extends  IBaseRepository<SysJob, Long>,SysJobRepositoryCustom {

    List<SysJob> findByOrgIdAndIsDefaultAdmin(Long orgId, String isDefaultAdmin);

    SysJob findByJobCode(String jobCode);
}

