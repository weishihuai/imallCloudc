package com.imall.iportal.core.main.service;


import com.imall.commons.base.util.Node;
import com.imall.iportal.core.main.entity.SysResource;
import com.imall.iportal.core.main.valid.SysResourceSaveValid;
import com.imall.iportal.core.main.valid.SysResourceUpdateValid;
import com.imall.iportal.core.main.vo.SysResourceVo;
import com.imall.iportal.core.main.vo.UrlAuthVo;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * T_PT_SYS_RESOURCE【资源】(服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface SysResourceService{

    /**
     * 查找所有资源
     * @param pageable
     * @param parentId 过滤出此节点下的资源
     * @param resourceName 资源名称搜索
     * @param permissionCode 权限编码
     * @param routerKey 路由Key
     * @param isAvailable 是否有效过滤
     */
    Page<SysResourceVo> query(@NotNull Pageable pageable, Long parentId , String resourceName, String permissionCode, String routerKey, String isAvailable);

    /**
     * 获取资源ID下的资源，拼装资源树，异步加载适用
     * @param id 资源ID，选填
     */
    List<Node> buildResourceTree(@NotNull Long id, @NotNull Boolean isIncludeRoot);


    /**
     * 获取资源ID下的资源，拼装资源树，异步加载适用
     * @param parentId  资源ID，选填
     * @param resourceType 资源类型
     * @param isIncludeRoot 是否包含Root节点
     * @return
     */
    List<Node> buildResourceTreeByResourceType(@NotNull Long parentId, String resourceType, @NotNull Boolean isIncludeRoot);

    /**
     * 查找菜单对应资源的子资源 ,资源类型为按钮
     * @param menuId
     * @return
     */
    List<Node> buildResourceTreeCheckByRole(@NotNull Long roleId, @NotNull  Long menuId);

    /**
     * 根据父节点Id，找出所有最后一级子节点
     * @param id
     * @return
     */
    List<Long> findLastChild(@NotNull Long id);

    /**
     * 删除资源
     * @param ids
     */
    void deleteResource(@NotEmpty List<Long> ids);

    /**
     * 返回所有url为.json的权限
     */
    List<UrlAuthVo> findAllUrlAuth();

    /**
     * 新增资源
     * @param sysResourceSaveValid 资源对象
     */
    SysResource save(@NotNull @Valid SysResourceSaveValid sysResourceSaveValid);

    /**
     * 修改资源
     * @param sysResourceUpdateValid 资源对象
     */
    SysResource update(@NotNull @Valid SysResourceUpdateValid sysResourceUpdateValid);


    /**
     * 根据资源ID查找资源
     * @param id 资源ID
     * @result SysResourceMsg 资源对象
     */
    SysResource findOne(@NotNull Long id);

    /**
     * 查询资源名称
     * @param appId 应用ID
     * @return
     */
    List<String> findResourceNameByAppId(@NotNull Long appId);

    /**
     * 以value作用前缀搜索， like value%， 一般用于二叉树，搜索所有子节点
     * @param nodeCode
     * @return
     */
    List<SysResource> findByNodeCodeLike(String nodeCode);


    List<String> findByRouterTemplateJs(@NotBlank String routerTemplateJs);

}
