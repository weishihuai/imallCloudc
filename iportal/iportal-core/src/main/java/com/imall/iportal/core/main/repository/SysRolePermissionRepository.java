
package com.imall.iportal.core.main.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.main.entity.SysRolePermission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * T_PT_SYS_ROLE_PERMISSION【角色资源权限】(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SysRolePermissionRepository extends  IBaseRepository<SysRolePermission, Long> {

    List<SysRolePermission> findByRoleIdAndResourceId(Long roleId, Long resourceId);

    @Query("select resourceId from SysRolePermission where roleId = :roleId")
    List<Long> findResourceIdByRoleId(@Param("roleId") Long roleId);

    @Override
    SysRolePermission save(SysRolePermission permission);

    @Override
    void delete(Long id);

    @Override
    void delete(SysRolePermission permission);

    @Override
    void delete(List<Long> ids);

}

