
package com.imall.iportal.core.main.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.main.entity.SysResource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * T_PT_SYS_RESOURCE【资源】(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SysResourceRepository extends  IBaseRepository<SysResource, Long>, SysResourceRepositoryCustom {


    @Query("select u from SysResource u where u.parentId = :parentId and u.resourceType = :resourceType")
    List<SysResource> findByParentIdAndResourceType(@Param("parentId") Long parentId, @Param("resourceType") String resourceType);

    List<SysResource> findByParentId(Long parentId);

    @Query("select r.resourceName from SysResource r where r.appId =?1")
    List<String> findResourceNameByAppId(Long appId);

    @Query("select r.permissionCode from SysResource r where r.routerTemplateJs =?1")
    List<String> findByRouterTemplateJs(String routerTemplateJs);

    Long countByParentId(Long parentId);

    // 以value作用前缀搜索， like value%， 一般用于二叉树，搜索所有子节点
    @Query("select r from SysResource r where r.nodeCode like ?1%")
    List<SysResource> findByNodeCodeLike(String nodeCode);

    @Query("select u from SysResource u where u.isAvailable = 'Y' and u.resourceType = 'BTN'")
    List<SysResource> findByIsAvailableAndResourceType();
}

