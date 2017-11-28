package com.imall.iportal.core.main.service;


import com.imall.iportal.core.main.entity.SysAuth;
import com.imall.iportal.core.main.vo.AuthParamsVo;
import com.imall.iportal.core.main.vo.SysRoleVo;
import com.imall.iportal.core.main.vo.TagAuthVo;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * T_PT_SYS_AUTH【授权】(服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface SysAuthService{

    /**
     * 分页查询
     * @param page 当前页
     * @param size 每页条数
     * @param orders 排序
     * @outparam result  查询结果列表
     * @outparam total   结果总数
     * @return
     * @throws BusinessException
     */
    Page<SysAuth> findAll(@NotNull Pageable pageable);
    /**
     * 根据 ID 取授权信息
     * @param paramID
     * @return
     * @throws BusinessException
     */
    SysAuth findOne(@NotNull Long paramID);

    /**
     * 保存授权
     * @param sysAuth
     * @throws BusinessException
     */
    SysAuth save(@NotNull SysAuth sysAuth);

    /**
     * 批量删除授权
     * @param sysAuthIdList
     * @return
     * @throws BusinessException
     */
    void delete(@NotEmpty List<Long> sysAuthIdList);

    /**
     * 查询角色ids
     * @param jobId 岗位id
     * @return
     */
    List<Long> findRoleIdsByJobId(@NotNull Long jobId);

    /**
     * 分页查询
     * @param pageable
     * @param paramsVo
     * @return
     */
    Page<SysRoleVo> findRolesByJobId(@NotNull Pageable pageable, AuthParamsVo paramsVo);

    /**
     * 保存岗位授权信息
     * @param jobId 岗位id
     * @param roleIds 角色ids
     */
    void saveJobRole(@NotNull Long jobId, @NotEmpty  Long[] roleIds);

    /**
     * 删除岗位授权信息
     * @param jobId 岗位id
     * @param roleIds 角色ids
     */
    void deleteJobRole(@NotNull Long jobId, @NotEmpty Long[] roleIds);

    /**
     * 获取授权信息
     * @param jobId
     * @return
     */
    TagAuthVo getTagAuthVoByJobId(@NotNull Long jobId);


}
