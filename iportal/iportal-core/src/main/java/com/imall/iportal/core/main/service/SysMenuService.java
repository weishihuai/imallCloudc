package com.imall.iportal.core.main.service;


import com.imall.commons.base.util.Node;
import com.imall.iportal.core.main.entity.SysMenu;
import com.imall.iportal.core.main.valid.SysMenuSaveValid;
import com.imall.iportal.core.main.valid.SysMenuUpdateValid;
import com.imall.iportal.core.main.vo.PortalMenuVo;
import com.imall.iportal.core.main.vo.RouterConfigVo;
import com.imall.iportal.core.main.vo.SysMenuVo;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * T_PT_SYS_MENU【菜单】(服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface SysMenuService{

    /**
     * 查询菜单列表，可按输入参数模糊匹配并按分页参数输出结果
     * @param pageable 当前页
     * @param pageable 每页条数
     * @param pageable 排序 如 menuName desc
     * @param parentId 父节点ID
     * @param menuName　菜单名称
     * @outparam result  查询结果列表
     * @outparam total   结果总数
     */
    Page<SysMenuVo> query(@NotNull Pageable pageable, Long parentId, String menuName);

    /**
     * 保存菜单
     * @param sysMenuSaveValid
     * @return
     */
    SysMenu save(@NotNull @Valid SysMenuSaveValid sysMenuSaveValid);

    /**
     * 更新菜单
     * @param sysMenuUpdateValid
     * @return
     */
    SysMenu update(@NotNull @Valid SysMenuUpdateValid sysMenuUpdateValid);

    /**
     *  根据主键ID查询菜单信息
     * @param id 主键ID
     * @return
     */
    SysMenu findOne(@NotNull Long id);

    /**
     *  删除菜单
     * @param sysMenuIds 主键ID集合
     * @return
     */
    void  deleteMenu(@NotEmpty List<Long> sysMenuIds);


    /**
     * 查询菜单信息 包含资源名称
     * @param id  ID
     * @return
     */
    SysMenuVo findById(@NotNull Long id);

    /**
     * 根据父节点Id，找出所有最后一级子节点
     * @param id
     * @return
     */
    List<Long> findLastChild(@NotNull Long id);

    /**
     * 根据父节点Id，找出所有最后一级子节点
     * @param id
     * @return
     */
    List<SysMenu> findLastChildObject(Long id);

    /**
     * 创建菜单树
     * @param parentId 父节点
     * @param isIncludeRoot 是否包含Root
     * @return
     */
    List<Node> buildMenuTree(@NotNull Long parentId, @NotNull Boolean isIncludeRoot);

    /**
     * 过滤出当前角色所拥有的菜单
     * @param roleId
     * @param parentId
     * @param jobId  岗位ID
     * @return
     */
    List<Node> buildMenuTreeFilterByRole(@NotNull Long roleId, @NotNull Long parentId, @NotNull Long jobId);

    /**
     * 异步获取子节点，并根据角色分配的菜单选中节点
     * @param roleId
     * @param parentId
     * @param isIncludeRoot
     * @param jobId
     * @return
     */
    List<Node> buildMenuTreeCheckByRole(@NotNull Long roleId, @NotNull Long parentId, @NotNull Boolean isIncludeRoot, @NotNull Long jobId);

    /**
     * 获取平台菜单
     * @return
     */
    List<PortalMenuVo> getPortalMenus();

    /**
     * 路由配置
     * @return
     */
    List<RouterConfigVo> getPortalRouterConfig();

    /**
     * 查询菜单信息
     * @param resourceId 资源ID
     * @return
     */
    SysMenu findByResourceId(@NotNull Long resourceId);

    /**
     * 权限nodeCode取得菜单
     * @param nodeCode
     * @return
     */
    SysMenu getByNodeCode(@NotBlank String nodeCode);


    SysMenu saveMenu(@NotNull SysMenu sysMenu);
}
