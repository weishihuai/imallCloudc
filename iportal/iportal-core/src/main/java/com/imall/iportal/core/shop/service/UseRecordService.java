package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.vo.UseRecordDetailVo;
import com.imall.iportal.core.shop.vo.UseRecordPageVo;
import com.imall.iportal.core.shop.vo.UseRecordSaveVo;
import com.imall.iportal.core.shop.vo.UseRecordSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface UseRecordService{

    /**
     * 保存
     * @param saveVo
     */
    void save(@NotNull @Valid UseRecordSaveVo saveVo);

    /**
     * 分页查询设备设施使用记录
     * @param pageable
     * @param useRecordSearchParam
     * @return
     */
    Page<UseRecordPageVo> query(@NotNull Pageable pageable, @NotNull @Valid UseRecordSearchParam useRecordSearchParam);

    /**
     * 获取设备设施使用记录详情
     * @param shopId
     * @param id
     * @return
     */
    UseRecordDetailVo findById(@NotNull Long shopId, @NotNull Long id);
}
