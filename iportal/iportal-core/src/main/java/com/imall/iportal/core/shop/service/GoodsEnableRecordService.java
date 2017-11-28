package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.GoodsEnableRecord;
import com.imall.iportal.core.shop.vo.GoodsEnableRecordPageVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface GoodsEnableRecordService{

    GoodsEnableRecord save(GoodsEnableRecord goodsEnableRecord);

    /**
     * 修改记录列表
     * @param pageable
     * @param id
     * @param shopId
     * @return
     */
    Page<GoodsEnableRecordPageVo> query(Pageable pageable, @NotNull Long id,@NotNull Long shopId);
}
