package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.PurchaseReceiveRecord;
import com.imall.iportal.core.shop.vo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * (服务层类)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface PurchaseReceiveRecordService {

    /**
     * 根据主键和门店ID获取采购收货单的打印模板内容
     *
     * @param id
     * @param shopId
     * @return
     */
    PurchaseReceivePrintTemplateVo getPurchaseReceivePrintTemplateVoByIdAndShopId(@NotNull Long id, @NotNull Long shopId);

    /**
     * 保存收货记录
     *
     * @param saveVo
     * @return
     */
    Long saveReceiveRecord(@NotNull @Valid PurchaseReceiveRecordSaveVo saveVo) throws ParseException;

    /**
     * 根据采购订单ID，找出收货记录的ID列表
     *
     * @param purchaseOrderId
     * @return
     */
    List<Long> getIdListByPurchaseOrderId(@NotNull Long purchaseOrderId);

    /**
     * 统计门店的收货记录条数
     *
     * @param start
     * @param end
     * @param shopId
     * @return
     */
    Long countByCreateDate(@NotNull Date start, @NotNull Date end, @NotNull Long shopId);

    PurchaseReceiveRecord save(PurchaseReceiveRecord entity);

    /**
     * 采购验收界面使用，找出可验收的收货记录
     *
     * @param id
     * @return
     */
    PurchaseReceiveRecordVo findEnableAcceptanceReceiveRecord(@NotNull Long id, @NotNull Long shopId);

    /**
     * 采购验收列表查询
     *
     * @param pageable
     * @param searchParam
     * @return
     */
    Page<PurchaseReceiveRecordPageVo> query(@NotNull Pageable pageable, @NotNull @Valid PurchaseReceiveSearchParams searchParam);

    /**
     * 根据主键ID&门店ID查找
     * @param id
     * @param shopId
     * @return
     */
    PurchaseReceiveRecord getByIdAndShopId(@NotNull Long id, @NotNull Long shopId);

    /**
     * 采购收货详细
     * @param id
     * @param shopId
     * @return
     */
    PurchaseReceiveRecordVo findByIdAndShopId(@NotNull Long id, @NotNull Long shopId);

    PurchaseReceiveRecord findOne(@NotNull Long id);
}
