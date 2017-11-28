package com.imall.iportal.core.main.service;


import com.imall.iportal.core.main.entity.SysDeveloperAuth;
import com.imall.iportal.core.main.valid.SysDeveloperAuthSaveValid;
import com.imall.iportal.core.main.valid.SysDeveloperAuthUpdateValid;
import com.imall.iportal.core.main.vo.SysDeveloperAuthVo;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * T_PT_SYS_DEVELOPER_AUTH【开发者授权】实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface SysDeveloperAuthService{

    /**
     * 查询某开发者的授权应用
     * @param userId 用户id
     * @return
     */
    List<SysDeveloperAuth> findByUserIdAndIsAvailable(@NotNull Long userId);

    /**
     * 查询开发者的授权列表，可按输入参数模糊匹配并按分页参数输出结果
     * @param pageable 当前页
     * @param pageable 每页条数
     * @param pageable 排序 如 appName desc,isAvailable desc
     * @param userName 用户名称
     * @param isAvailable 是否可用
     * @outparam result  查询结果列表
     * @outparam total   结果总数
     */
    Page<SysDeveloperAuthVo> query(@NotNull Pageable pageable, String userName, String isAvailable);

    /**
     * 保存
     * @param sysDeveloperAuthSaveValid
     * @return
     */
    SysDeveloperAuth save(@NotNull @Valid SysDeveloperAuthSaveValid sysDeveloperAuthSaveValid);

    /**
     * 查找开发者授权信息
     * @param id
     * @return
     */
    SysDeveloperAuthVo findByDeveloperAuthId(@NotNull Long id);

    /**
     * 更新开发者授权信息
     * @param sysDeveloperAuthUpdateValid
     * @return
     */
    SysDeveloperAuth update(@NotNull @Valid SysDeveloperAuthUpdateValid sysDeveloperAuthUpdateValid);

    /**
     *  根据主键ID查询开发者授权信息
     * @param id 主键ID
     * @return
     */
    SysDeveloperAuth findOne(@NotNull Long id);

    /**
     *  删除开发者授权
     * @param sysDeveloperAuthIds 主键ID集合
     * @return
     */
    void delete(@NotEmpty List<Long> sysDeveloperAuthIds);

}
