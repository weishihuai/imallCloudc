package com.imall.iportal.core.main.service;


import com.imall.iportal.core.main.entity.SysRolePermission;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * T_PT_SYS_ROLE_PERMISSION【角色资源权限】(服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface SysRolePermissionService{

    /**
     * 保存用户资源权限
     * @param roleId 角色ID
     * @param id 资源ID
     * @param isChecked  true新增 false 删除
     */
    void saveOrDeleteRolePermission(@NotNull Long roleId, @NotNull  Long id, @NotNull  Boolean isChecked);

    /**
     * 找出角色已分配的资源权限
     * @param roleId
     * @return
     */
    List<Long> findResourceIdByRoleId(@NotNull Long roleId);


    SysRolePermission save(SysRolePermission sysRolePermission);

}
