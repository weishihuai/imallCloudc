package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.vo.MaintainingRecordDetailVo;
import com.imall.iportal.core.shop.vo.MaintainingRecordPageVo;
import com.imall.iportal.core.shop.vo.MaintainingRecordSaveVo;
import com.imall.iportal.core.shop.vo.MaintainingRecordSearchParam;
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
public interface MaintainingRecordService{

    /**
     * 保存
     * @param saveVo
     */
    void save(@NotNull @Valid MaintainingRecordSaveVo saveVo);

    /**
     * 分页查询设备设施维护记录
     * @param pageable
     * @param maintainingRecordSearchParam
     * @return
     */
    Page<MaintainingRecordPageVo> query(@NotNull Pageable pageable, @NotNull @Valid MaintainingRecordSearchParam maintainingRecordSearchParam);

    /**
     * 详情
     * @param shopId
     * @param id
     * @return
     */
    MaintainingRecordDetailVo findById(@NotNull Long shopId, @NotNull Long id);
}
