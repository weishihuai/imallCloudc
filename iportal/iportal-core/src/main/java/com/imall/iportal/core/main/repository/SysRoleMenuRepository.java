
package com.imall.iportal.core.main.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.main.entity.SysRoleMenu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * T_PT_SYS_ROLE_MENU【角色菜单】(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SysRoleMenuRepository extends  IBaseRepository<SysRoleMenu, Long> {

    List<SysRoleMenu> findByRoleIdAndMenuId(Long roleId,Long menuId);

    @Query("select menuId from SysRoleMenu where roleId = :roleId")
    List<Long> findMenuIdByRoleId(@Param("roleId") Long roleId);

    List<SysRoleMenu> findByMenuIdAndRoleId(Long menuId, Long roleId);
}

