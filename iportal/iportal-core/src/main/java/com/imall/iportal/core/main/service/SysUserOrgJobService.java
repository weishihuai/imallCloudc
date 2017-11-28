package com.imall.iportal.core.main.service;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.iportal.core.main.entity.SysUserOrgJob;
import com.imall.iportal.core.main.valid.SysUserOrgJobSaveValid;
import com.imall.iportal.core.main.valid.SysUserOrgJobUpdateValid;
import com.imall.iportal.core.main.vo.SysUserOrgJobVo;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * T_PT_SYS_USER_ORG_JOB【组织岗位】(服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface SysUserOrgJobService{

    /**
     * 根据 ID 取用户岗位信息
     * @param paramID
     * @return
     * @throws BusinessException
     */
    SysUserOrgJob findOne(@NotNull Long paramID);

    /**
     * 根据 ID 取用户岗位信息
     * @param paramID
     * @return
     * @throws BusinessException
     */
    SysUserOrgJobVo findOneVo(@NotNull Long paramID);

    /**
     * 保存用户岗位关系
     * @param sysUserOrgJobUpdateValid
     * @throws BusinessException
     */
    SysUserOrgJob update(@NotNull @Valid SysUserOrgJobUpdateValid sysUserOrgJobUpdateValid);

    /**
     * 保存用户岗位关系
     * @param sysUserOrgJobSaveValid
     * @throws BusinessException
     */
    SysUserOrgJob save(@NotNull @Valid SysUserOrgJobSaveValid sysUserOrgJobSaveValid);

    /**
     * 批量删除用户岗位关系
     * @param userOrgJobIdList
     * @return
     * @throws BusinessException
     */
    void delete(@NotEmpty List<Long> userOrgJobIdList);

    /**
     * 通过用户UserId 批量删除用户岗位关系
     * @param userId
     * @return
     * @throws BusinessException
     */
    void deleteByUserId(@NotNull Long userId);

    /**
     * 查找某用户的所有岗位
     * @param userId
     * @return
     * @throws BusinessException
     */
    List<SysUserOrgJobVo> findVoByUserId(@NotNull Long userId);

    /**
     * 查找某用户的所有岗位
     * @param userId
     * @return
     * @throws BusinessException
     */
    List<SysUserOrgJob> findByUserId(@NotNull Long userId);

    /**
     * 根据某机构ID查找所有用户岗位
     * @param orgId
     * @return
     */
    List<SysUserOrgJob> findByOrgId(@NotNull Long orgId);

    /**
     * 根据某机构ID查找所有用户主岗位
     * @param orgId
     * @return
     */
    List<SysUserOrgJob> findByOrgIdAndIsmain(@NotNull Long orgId);

    /**
     * 判断某用户是否拥有某岗位的权限
     * @param userId
     * @param orgId
     * @param jobId
     * @return
     * @throws BusinessException
     */
    boolean isExist(@NotNull Long userId,@NotNull  Long orgId,@NotNull  Long jobId) throws BusinessException;

    /**
     * 获取岗位
     * @param userId
     * @param orgId
     * @param jobId
     * @return
     * @throws BusinessException
     */
    SysUserOrgJob getByUserIdOrgIdJobId(@NotNull Long userId,@NotNull  Long orgId,@NotNull  Long jobId) throws BusinessException;

    /**
     * 根据 某用户ID 取得主岗位
     * @param userId
     * @return
     * @throws BusinessException
     */
    SysUserOrgJob getIsmainByUserId(@NotNull Long userId) throws BusinessException;

    /**
     * 更新某用户的某岗位为主岗位
     * @param userId
     * @param jobId
     * @throws BusinessException
     */
    void updateJobIsmain(@NotNull Long userId,@NotNull  Long jobId) throws BusinessException;

    /**
     * 保存或更新
     * @param userId
     * @param orgId
     * @param jobId
     * @param ismain
     * @throws BusinessException
     */
    SysUserOrgJob saveOrUpdate(@NotNull Long userId,@NotNull  Long orgId,@NotNull  Long jobId,@NotBlank String ismain) throws BusinessException;

    /**
     * 查询是否存在用户
     * @param orgId 组织ID
     * @return
     */
    Boolean existsUserByOrgId(@NotNull Long orgId);

    /**
     * 查询是否存在用户
     * @param userId 用户ID
     * @return
     */
    Boolean existsUserByUserId(@NotNull Long userId);

}
