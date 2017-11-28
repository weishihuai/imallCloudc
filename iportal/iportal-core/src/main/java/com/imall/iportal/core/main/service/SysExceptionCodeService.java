package com.imall.iportal.core.main.service;


import com.imall.iportal.core.main.entity.SysExceptionCode;
import com.imall.iportal.core.main.valid.SysExceptionCodeSaveValid;
import com.imall.iportal.core.main.valid.SysExceptionCodeUpdateValid;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface SysExceptionCodeService{

    /**
     * 查询异常编码列表，可按输入参数模糊匹配并按分页参数输出结果
     * @param code 异常编码
     * @param exceptionMsg 异常提示
     * @outparam result  查询结果列表
     * @outparam total   结果总数
     */
    Page<SysExceptionCode> query(@NotNull Pageable pageable, String code, String exceptionMsg);

    /**
     * 通过异常编码或提示消息
     * @param code
     * @return
     */
    String getMessageByCode(@NotBlank String code);

    /**
     * 删除异常码表记录
     * @param ids 异常码表Id主键集合
     */
    void delete(@NotEmpty List<Long> ids);

    /**
     * 新增异常码表
     * @param sysExceptionCodeSaveValid 异常码表对象
     */
    SysExceptionCode save(@NotNull @Valid SysExceptionCodeSaveValid sysExceptionCodeSaveValid);

    /**
     * 修改异常码表
     * @param sysExceptionCodeUpdateValid 异常码表对象
     */
    SysExceptionCode update(@NotNull @Valid SysExceptionCodeUpdateValid sysExceptionCodeUpdateValid);

    /**
     * 根据角色ID查找异常码表
     * @param id 角色ID
     * @result SysExceptionCodeMsg 异常码表对象
     */
    SysExceptionCode findOne(@NotNull Long id);
}
