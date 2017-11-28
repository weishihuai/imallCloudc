package com.imall.iportal.core.main.service;


import com.imall.iportal.core.main.entity.SysRole;
import com.imall.iportal.core.main.valid.SysRoleSaveValid;
import com.imall.iportal.core.main.valid.SysRoleUpdateValid;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * T_PT_SYS_ROLE【角色】(服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface SysRoleService{

    /**
     * 查询角色列表，可按输入参数模糊匹配并按分页参数输出结果
     * @param pageable 分页参数
     * @param orgId  机构ID
     * @return
     */
    Page<SysRole> query(@NotNull Pageable pageable, Long orgId);

    /**
     * 查询角色列表，（仅可用角色）
     * @param orgId  机构ID
     * @return
     */
    List<SysRole> findByOrgId(Long orgId);

    /**
     * 查询角色名称
     * @param jobId 岗位ID
     * @return
     */
    List<String> findRoleNameByJobId(@NotNull Long jobId);

    /**
     * 删除角色
     * @param ids 角色Id集合
     */
    void delete(@NotEmpty List<Long> ids);

    /**
     * 新增角色
     * @param sysRoleSaveValid 角色对象
     */
    SysRole save(@NotNull @Valid SysRoleSaveValid sysRoleSaveValid);

    /**
     * 修改角色
     * @param sysRoleUpdateValid 角色对象
     */
    SysRole update(@NotNull @Valid SysRoleUpdateValid sysRoleUpdateValid);

    /**
     * 根据角色ID查找角色
     * @param id 角色ID
     * @result SysRoleMsg 角色对象
     */
    SysRole findOne(@NotNull Long id);

    /**
     *
     * 检查编码是否已经存在
     * @return
     */
    Boolean checkRoleCodeIsExist(@NotBlank @Length(max = 32) String roleCode, Long roleId);

}
