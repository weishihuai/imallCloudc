package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.FirstManageDrugQualityApproveInf;
import com.imall.iportal.core.shop.vo.FirstManageDrugQualityApproveInfSaveVo;
import com.imall.iportal.core.shop.vo.FirstManageDrugQualityApproveInfVo;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.List;

/**
 * (服务层类)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface FirstManageDrugQualityApproveInfService {

    /**
     * 根据首营商品审核信息id 查找对应的审核类型信息
     * @param id
     * @param shopId
     * @return
     */
    FirstManageDrugQualityApproveInfVo findApprovalDetail(@NotNull Long id, @NotNull Long shopId);

    /**
     * 采购审核 质量审核 企业审核
     * @param firstManageDrugQualityApproveInfSaveVo
     * @return
     */
    List<Long> saveFirstManageDrugQualityInf(@NotNull @Valid FirstManageDrugQualityApproveInfSaveVo firstManageDrugQualityApproveInfSaveVo) throws ParseException;

    /**
     * 根据首营商品审核信息id 查找对应的审核类型信息
     * @param id
     * @return
     */
    List<FirstManageDrugQualityApproveInf> findByFirstManageDrugQualityApproveId(@NotNull Long id);
}
