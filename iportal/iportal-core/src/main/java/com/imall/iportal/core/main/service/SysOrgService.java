package com.imall.iportal.core.main.service;


import com.imall.commons.base.util.Node;
import com.imall.iportal.core.main.entity.SysOrg;
import com.imall.iportal.core.main.valid.OrgUserParamsVoSaveValid;
import com.imall.iportal.core.main.valid.SysOrgSaveValid;
import com.imall.iportal.core.main.valid.SysOrgUpdateValid;
import com.imall.iportal.core.main.vo.OrgAdminUserAuthInfoVo;
import com.imall.iportal.core.main.vo.OrgUserParamsVo;
import com.imall.iportal.core.main.vo.SysOrgVo;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * T_PT_SYS_ORG【组织】(服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface SysOrgService{

    /**
     * 查询组织列表，可按输入参数模糊匹配并按分页参数输出结果
     * @param pageable
     * @param parentId
     * @param orgName
     * @param orgCode
     * @param phone
     * @return
     */
    Page<SysOrgVo> query(@NotNull Pageable pageable, Long parentId, String orgName, String orgCode, String phone);

    /**
     * 保存组织
     * @param sysOrgSaveValid
     * @return
     */
    SysOrg save(@NotNull @Valid SysOrgSaveValid sysOrgSaveValid);

    /**
     * 更新组织
     * @param sysOrgUpdateValid
     * @return
     */
    SysOrg update(@NotNull @Valid SysOrgUpdateValid sysOrgUpdateValid);


    /**
     *  根据主键ID查询组织信息
     * @param id 主键ID
     * @return
     */
    SysOrg findOne(@NotNull Long id);

    /**
     * 创建组织树
     * @param loginOrgId 当前登录的机构id
     * @param parentId 组织ID
     * @param isIncludeRoot 是否包含Root
     * @return 查询结果列表
     */
    List<Node> buildOrgTree(@NotNull Long loginOrgId,@NotNull  Long parentId,@NotNull Boolean isIncludeRoot);

    /**
     * 删除组织
     * @param sysOrgIds 组织IDs
     */
    void deleteSysOrg(@NotEmpty List<Long> sysOrgIds);

    /**
     * 更新组织、角色、岗位、管理员信息
     * @param authInfoVo
     */
    void updateOrgAdminUserAuthInfo(OrgAdminUserAuthInfoVo authInfoVo);

    /**
     * 创建一个组织，并创建一个默认岗位、角色、管理员，并且授权
     */
    void createOrgAdminUserAuth(@NotNull @Valid OrgUserParamsVoSaveValid orgUserParamsVoSaveValid);


    /**
     *创建组织 机构
     * @param orgUserParamsVo
     * @return
     */
    Long saveOrgAdminUserAuth(OrgUserParamsVo orgUserParamsVo);

}
