package com.imall.iportal.core.main.service;


import com.imall.iportal.core.main.entity.SysRoleMenu;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * T_PT_SYS_ROLE_MENU【角色菜单】(服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface SysRoleMenuService{

    /**
     * 保存或删除角色所分配的菜单
     * @param roleId 角色ID
     * @param id 菜单ID
     * @param isChecked true分配菜单 false：取消菜单
     */
    void saveOrDeleteRoleMenu(@NotNull Long roleId , @NotNull Long id, @NotNull  Boolean isChecked);

    /**
     * 用户保存或删除角色所分配的菜单
     * @param roleId 被操作者角色ID
     * @param id 菜单ID
     * @param isChecked true分配菜单 false：取消菜单
     */
    void saveAllotRoleMenu(@NotNull Long roleId , @NotNull Long id, @NotNull Boolean isChecked);

    /**
     * 根据角色找菜单
     * @param roleId
     * @return
     */
    List<Long> findMenuIdByRoleId(@NotNull Long roleId);

    SysRoleMenu save(@NotNull SysRoleMenu sysRoleMenu);
}
