package com.imall.iportal.core.main.service;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.iportal.core.main.entity.SysParamInf;
import com.imall.iportal.core.main.valid.SysParamInfBatchUpdateValid;
import com.imall.iportal.core.main.valid.SysParamInfSaveValid;
import com.imall.iportal.core.main.valid.SysParamInfUpdateValid;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * T_PT_SYS_PARAM_INF【系统参数】(服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface SysParamInfService{

    /**
     * 根据 ID 取系统参数
     * @param paramID
     * @return
     */
    SysParamInf findOne(@NotNull Long paramID);

    /**
     * 保存
     * @param sysParamInfSaveValid
     */
    SysParamInf save(@NotNull @Valid SysParamInfSaveValid sysParamInfSaveValid);

    /**
     * 修改
     * @param sysParamInfUpdateValid
     */
    SysParamInf update(@NotNull @Valid SysParamInfUpdateValid sysParamInfUpdateValid);

    /**
     * 查询系统参数，可按输入参数模糊匹配并按分页参数输出结果
     * @param pageable 分页
     * @param innerCode  内部 编码
     * @param paramNm 参数 名称
     * @outparam result  查询结果列表
     * @outparam total   结果总数
     */
    Page<SysParamInf> query(@NotNull Pageable pageable,Long sysOrgId, String innerCode, String paramNm);

    /**
     * 根据内部编码innerCode去更新参数值paramValue
     * @param paramInfs
     * @return
     */
    void updateByBatch(@NotNull Long sysOrgId,@NotEmpty List<SysParamInfBatchUpdateValid> paramInfs);

    /**
     * 更新系统参数
     * @param sysOrgId 机构号
     * @param innerCode 内部编码
     * @param paramValueStr 参数值
     */
    void updateParamInf(@NotNull Long sysOrgId, @NotBlank String innerCode, @NotBlank String paramValueStr);

    /**
     * 根据sysOrgId列出某分组的所有参数
     * @param sysOrgId
     * @param groupTypeCode
     * @return
     */
    List<SysParamInf> listBySysOrgIdAndGroupTypeCode(@NotNull Long sysOrgId,@NotBlank  String groupTypeCode);


    /**
     * 批量删除
     * @param paramInfIdList
     * @return
     */
    void delete(@NotEmpty List<Long> paramInfIdList);

    /**
     * 拷贝默认组织机构的系统参数给新添的机构
     * @param fromOrgId 从此机构拷贝
     * @param toOrgId 拷贝到此机构
     */
    void createOrgParamInfByDefault(@NotNull Long fromOrgId,@NotNull Long toOrgId);

    /**
     * 根据 机构ID和InnerCore获取系统参数，
     * @param innerCode
     * @return
     * @throws BusinessException
     */
    SysParamInf findOneByOrgAndInnerCode(@NotNull Long sysOrgId,@NotBlank  String innerCode) throws BusinessException;

}
