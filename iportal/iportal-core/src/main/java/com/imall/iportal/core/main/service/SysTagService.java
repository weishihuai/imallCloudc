package com.imall.iportal.core.main.service;


import com.imall.iportal.core.main.entity.SysTag;
import com.imall.iportal.core.main.valid.SysTagSaveValid;
import com.imall.iportal.core.main.valid.SysTagUpdateValid;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 标签(服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface SysTagService{

    /**
     * 标签类型代码查找标签集合
     * @param tagTypeCode
     * @return
     */
    List<SysTag> findByTagTypeCodeSysOrgId(@NotBlank String tagTypeCode ,@NotNull Long sysOrgId);

    /**
     * 标签类型代码 和 标签值 查找标签
     * @param tagTypeCode
     * @param tagValue
     * @param sysOrgId
     * @return
     */
    SysTag findByTagTypeCodeTagValueSysOrgId(@NotBlank String tagTypeCode, @NotBlank String tagValue, @NotNull  Long sysOrgId);

    /**
     * 保存
     * @param sysTagSaveValid
     * @return
     */
    SysTag save(@NotNull @Valid SysTagSaveValid sysTagSaveValid);

    /**
     * 更新
     * @param sysTagUpdateValid
     * @return
     */
    SysTag update(@NotNull @Valid SysTagUpdateValid sysTagUpdateValid);
}
