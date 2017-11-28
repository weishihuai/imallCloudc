package com.imall.iportal.core.main.service;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.iportal.core.main.entity.SysApp;
import com.imall.iportal.core.main.valid.SysAppSaveValid;
import com.imall.iportal.core.main.valid.SysAppUpdateValid;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * T_PT_SYS_APP【应用】(服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface SysAppService{

    /**
     * 查询应用列表，可按输入参数模糊匹配并按分页参数输出结果
     * @param pageable 当前页
     * @param pageable 每页条数
     * @param pageable 排序 如 appName desc,isAvailable desc
     * @param appName 应用名称
     * @param appCname 应用中文名称
     * @param hostname IP地址
     * @param isAvailable 是否可用
     * @outparam result  查询结果列表
     * @outparam total   结果总数
     */
    Page<SysApp> query(@NotNull Pageable pageable, String appName, String appCname, String hostname, String isAvailable);

    /**
     * 按appKey查询应用详情
     * @param appKey 应用key
     * @return        返回应用详情，详见SysAppMsg结构
     */
    SysApp findByAppKey(@NotBlank String appKey);

    /**
     * 保存菜单
     * @param sysAppSaveValid
     * @return
     */
    SysApp save(@NotNull @Valid SysAppSaveValid sysAppSaveValid);

    /**
     * 更新菜单
     * @param sysAppUpdateValid
     * @return
     */
    SysApp update(@NotNull @Valid SysAppUpdateValid sysAppUpdateValid);

    /**
     *  根据主键ID查询应用信息
     * @param id 主键ID
     * @return
     */
    SysApp findOne(@NotNull Long id);

    /**
     * 验证是否信任的App
     * @param appKey
     * @param nonceStr
     * @param sign
     * @return
     * @throws BusinessException
     */
    boolean checkIsTrustApp(@NotBlank String appKey, @NotBlank String nonceStr, @NotBlank String sign) throws BusinessException;

    /**
     *  删除应用
     * @param sysAppIds 主键ID集合
     * @return
     */
    void deleteSysApp(@NotEmpty List<Long> sysAppIds);

    /**
     * 查询应用列表，可按输入参数
     * @param isAvailable 是否可用
     */
    List<SysApp> findByIsAvailable(@NotBlank String isAvailable);

    /**
     * 查询所有应用列表
     */
    List<SysApp> findAll();
}
