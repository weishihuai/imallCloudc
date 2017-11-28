package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.PurchaseAcceptanceRecord;
import com.imall.iportal.core.shop.entity.PurchaseAcceptanceRecordItem;
import com.imall.iportal.core.shop.vo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Date;

/**
 * (服务层类)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface PurchaseAcceptanceRecordService {

    PurchaseAcceptanceRecord findOne(@NotNull Long id);

    /**
     * 根据主键和门店ID获取采购验收单的打印模板内容
     *
     * @param id
     * @param shopId
     * @return
     */
    PurchaseAcceptancePrintTemplateVo getPurchaseAcceptancePrintTemplateVoByIdAndShopId(@NotNull Long id, @NotNull Long shopId);

    /**
     * 保存验收记录
     *
     * @param saveVo
     * @return
     */
    Long saveAcceptanceRecord(@Valid @NotNull PurchaseAcceptanceRecordSaveVo saveVo) throws ParseException;

    /**
     * 统计门店的验收记录条数
     *
     * @param start
     * @param end
     * @param shopId
     * @return
     */
    Long countByCreateDate(@NotNull Date start, @NotNull Date end, @NotNull Long shopId);

    /**
     * 根据输入批号、生产日期、有效期检查/新增商品批次信息并保存验收数量进库存,返回批次主键ID
     *
     * @param recordItem
     * @param goodsBatchId
     */
    Long checkGoodsBatch(@NotNull PurchaseAcceptanceRecordItem recordItem, Long goodsBatchId);

    PurchaseAcceptanceRecord save(PurchaseAcceptanceRecord entity);

    /**
     *快速收货，验收列表
     * @param pageable
     * @param searchParams
     * @return
     */
    Page<FastReceivePageVo> queryFastReceive(@NotNull Pageable pageable, @Valid @NotNull PurchaseAcceptanceRecordSearchParams searchParams);

    /**
     * 验收单（快速收货单）详情
     * @param id
     * @param shopId
     * @return
     */
    FastReceiveVo getDetail(@NotNull Long id, @NotNull Long shopId);

    /**
     * 验收记录查询
     * @param pageable
     * @param searchParams
     * @return
     */
    Page<PurchaseAcceptanceRecordPageVo> query(@NotNull Pageable pageable, @Valid @NotNull PurchaseAcceptanceRecordSearchParams searchParams);
}
