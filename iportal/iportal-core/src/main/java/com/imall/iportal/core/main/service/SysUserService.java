package com.imall.iportal.core.main.service;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.main.valid.SysUserSaveValid;
import com.imall.iportal.core.main.valid.SysUserUpdateValid;
import com.imall.iportal.core.main.vo.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.List;

/**
 * T_SYS_USER【用户】(服务层类)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface SysUserService {

    /**
     * 根据 ID 取用户信息
     * 校验: 限制传入ID不为空
     *
     * @param paramID
     * @return
     * @throws BusinessException
     */
    SysUser findOne(@NotNull Long paramID);


    /**
     * 根据 ID 获取 详情
     *
     * @param id
     * @return
     */
    SysUserDetailVo findById(@NotNull Long id,@NotNull Long orgId);

    /**
     * 根据 ID 取用户信息
     * 校验: 限制传入ID不为空
     *
     * @param paramID
     * @return
     * @throws BusinessException
     */
    SysUser findOneBySafe(@NotNull Long paramID);

    /**
     * 批量删除用户
     * 校验: 限制集合长度最小为1
     *
     * @param userIdList
     * @return
     * @throws BusinessException
     */
    void delete(@NotEmpty List<Long> userIdList);

    /**
     * 查询用户信息
     * @param pageable
     * @param paramsVo
     * @return
     * @throws BusinessException
     */
    Page<SysUserPageVo> query(@NotNull Pageable pageable, UserParamsVo paramsVo) throws BusinessException, ParseException;

    /**
     * 根据 用户登录ID或Email或手机 取用户信息
     *
     * @param loginIdEmailMobile
     * @return
     * @throws BusinessException
     */
    SysUser getUser(@NotBlank String loginIdEmailMobile) throws BusinessException;

    /**
     * 根据 登录ID 取 用户信息
     *
     * @param loginId
     * @return
     * @throws BusinessException
     */
    SysUser getByLoginId(@NotBlank String loginId) throws BusinessException;

    /**
     * 保存用户信息
     * 校验: 限制 sysUserSaveMsg 不能为空，且需要校验 sysUserSaveMsg 对象里面的属性变量
     *
     * @param sysUserSaveValid
     * @throws BusinessException
     */
    SysUser save(@NotNull @Valid SysUserSaveValid sysUserSaveValid);

    /**
     * 更新用户信息
     * 校验: 限制 sysUserSaveMsg 不能为空，且需要校验 sysUserSaveMsg 对象里面的属性变量
     *
     * @param sysUserUpdateValid
     * @throws BusinessException
     */
    SysUser update(@NotNull @Valid SysUserUpdateValid sysUserUpdateValid);

    /**
     * 批量更新用户的状态
     *
     * @param ids
     * @param isEnable
     * @throws BusinessException
     */
    void updateIsEnable(@NotEmpty List<Long> ids, @NotBlank @Length(max = 32) String isEnable) throws BusinessException;


    /**
     * 修改密码
     *
     * @param id
     * @param password
     * @param salt
     * @throws BusinessException
     */
    void modifyPassword(@NotNull Long id, @NotBlank @Length(max = 128) String password, @NotBlank @Length(max = 32) String salt) throws BusinessException;

    /**
     * 查询是否存在用户
     *
     * @param orgId 组织ID
     * @return
     */
    Boolean existsUserByOrgId(@NotNull Long orgId);

    /**
     * 删除用户
     *
     * @param userIdList 用户IDs
     */
    void deleteUser(@NotEmpty List<Long> userIdList);

    /**
     * 查找为某机构服务的所有用户
     *
     * @param serviceOrgId
     * @return
     */
    List<SysUserVo> findVoByServiceOrgId(@NotNull Long serviceOrgId);

    /**
     * 查找为某机构服务的所有没有删除的用户
     *
     * @param serviceOrgId
     * @return
     */
    List<SysUser> findByServiceOrgIdNotDeleted(@NotNull Long serviceOrgId);

    /**
     * 通过用户名查出用户信息
     * @param userName
     * @return
     */
    SysUser findByUserName(@NotBlank String userName);
    /**
     * 通过用户名查出用户是否启用
     * @param userName
     * @return
     */
    Boolean findByName(@NotBlank String userName);

    /**
     * 获取当前登录用户真实姓名以及shopId等信息
     *
     * @param currUser
     * @return
     */
    SysUserLoginVo findCurrentLoginUserMessage(CurrUserVo currUser);

    /**
     * 查出当前用户信息
     * @param userId
     * @param userOrgJobId
     * @return
     */
    CurrUserVo getCurrUserVo(Long userId, Long userOrgJobId);


    /**
     *
     * 检查用户名是否已经存在
     * @return
     */
    Boolean checkUserNameIsExist(@NotBlank @Length(max = 32) String name,Long userId);

    /**
     * 根据门店id，是否是否默认管理员 获取用户信息
     * @param shopId
     * @param IsDefaultAdmin
     * @return
     */
    SysUserShopVo findByShopIdAndIsDefaultAdminAndOrgId(@NotNull Long shopId, String IsDefaultAdmin, @NotNull Long orgId);

    /**
     * 更新用户 手机号以及 Emai
     * @param Email
     * @param Mobile
     * @return
     */
    Long updateMobileAndEmail(@NotNull Long id,String Email, String Mobile);

    /**
     * 查找当前门店所有的员工
     * @param shopId
     * @return
     */
    List<SysUser> findByShopId(@NotNull Long shopId);

    /**
     * 查找当前门店所有的员工
     * @param shopId
     * @return
     */
    List<SysUser> findNormalByShopId(@NotNull Long shopId);


    /**
     * 查找当前登录营业员信息
     * @param shopId 门店ID
     * @param sysUserId 用户ID
     */
    LoginCashierVo findByLoginCashier(@NotNull Long shopId, @NotNull Long sysUserId);
}

