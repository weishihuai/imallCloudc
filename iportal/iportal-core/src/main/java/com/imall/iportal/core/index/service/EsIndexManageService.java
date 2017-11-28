package com.imall.iportal.core.index.service;


import com.imall.commons.dicts.IndexTypeCodeEnum;
import com.imall.iportal.core.index.entity.EsIndexManage;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * T_PT_ES_INDEX_MANAGE【索引管理】(服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface EsIndexManageService{

    EsIndexManage getByIndexTypeCode(@NotNull IndexTypeCodeEnum typeCodeEnum);

    Page<EsIndexManage> query(@NotNull Pageable pageable);

    void rebuildIndex(@NotNull Long indexManageId);

    void updateManageRemainAndState(@NotBlank String indexTypeCode, int remainNum);
}
