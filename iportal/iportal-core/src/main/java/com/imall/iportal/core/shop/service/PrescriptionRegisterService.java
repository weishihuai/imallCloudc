package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.PrescriptionRegister;
import com.imall.iportal.core.shop.vo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.List;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface PrescriptionRegisterService{
    /**
     * 新增
     * @param prescriptionRegisterSaveVo
     * @return
     */
    PrescriptionRegister save(@NotNull @Valid PrescriptionRegisterSaveVo prescriptionRegisterSaveVo);

    /**
     * 根据 ID 获取处方登记详情
     * @param shopId
     * @param id
     * @return
     */
    PrescriptionRegisterDetailVo findById(@NotNull Long shopId, @NotNull Long id);

    /**
     * 更新处方登记
     * @param prescriptionRegisterUpdateVo
     * @return
     */
    PrescriptionRegister update(@NotNull @Valid PrescriptionRegisterUpdateVo prescriptionRegisterUpdateVo) throws ParseException;

    /**
     * 分页查询处方登记
     * @param pageable
     * @param prescriptionRegisterSearchParam
     * @return
     */
    Page<PrescriptionRegisterPageVo> query(@NotNull Pageable pageable, @NotNull @Valid PrescriptionRegisterSearchParam prescriptionRegisterSearchParam);

    /**
     * 调剂
     * @param prescriptionDispensingVo
     * @return
     */
    PrescriptionRegister dispensingPrescription(@NotNull @Valid PrescriptionDispensingVo prescriptionDispensingVo);

    /**
     * 获取处方药订单项
     * @param shopId
     * @param orderId
     * @return
     */
    List<PrescriptionRegisterItemVo> listItemByOrderId(@NotNull Long shopId, @NotNull Long orderId);
}
