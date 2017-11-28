
package com.imall.iportal.core.main.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.main.entity.SysRole;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * T_PT_SYS_ROLE【角色】(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SysRoleRepository extends  IBaseRepository<SysRole, Long>, SysRoleRepositoryCustom {

    @Query("select roleName from SysRole where id in(select roleId from SysAuth where jobId =?1)")
    List<String> findRoleNameByJobId(Long jobId);

    List<SysRole> findByOrgIdAndIsAvailableAndIsDefaultAdmin(Long orgId, String isAvailable, String isDefaultAdmin);

    SysRole findByRoleCode(String roleCode);
}

