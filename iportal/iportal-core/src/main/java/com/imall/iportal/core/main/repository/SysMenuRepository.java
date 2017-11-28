
package com.imall.iportal.core.main.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.main.entity.SysMenu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * T_PT_SYS_MENU【菜单】(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SysMenuRepository extends  IBaseRepository<SysMenu, Long> ,SysMenuRepositoryCustom{

    @Query("select s from SysMenu s where s.menuType = :menuType and s.isAvailable = :isAvailable and s.parentId=:parentId order by s.orderby asc")
    List<SysMenu> findMenu(@Param("menuType") String menuType,@Param("parentId") Long parentId,@Param("isAvailable") String isAvailable);

    @Query("select s from SysMenu s where s.menuType = :menuType and s.isAvailable = :isAvailable order by s.orderby asc")
    List<SysMenu> findMenu(@Param("menuType") String menuType,@Param("isAvailable") String isAvailable);

    List<SysMenu> findByParentId(Long parentId);

    Long countByParentId(Long parentId);

    SysMenu findByResourceId(Long resourceId);

    @Query("select s.menuName from SysMenu s where s.parentId =?1")
    List<String> findMenuNameByParentId(Long parentId);

    SysMenu findByNodeCode(String nodeCode);
}

