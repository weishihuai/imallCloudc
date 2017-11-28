package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.FirstManageSupplierQualityApprove;
import com.imall.iportal.core.shop.vo.FirstManageSupplierQualityApproveDetailVo;
import com.imall.iportal.core.shop.vo.FirstManageSupplierQualityApprovePageVo;
import com.imall.iportal.core.shop.vo.FirstManageSupplierQualityApproveSaveVo;
import com.imall.iportal.core.shop.vo.FirstManageSupplierQualityApproveSearchParam;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.ParseException;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface FirstManageSupplierQualityApproveService{
    /**
     * 根据ID查询相应的对象
     *
     * @param id 主键ID
     * @return
     */
    FirstManageSupplierQualityApprove findOne(@NotNull Long id);
    /**
     * 根据ID查询相应的对象
     *
     * @param id 主键ID
     * @return
     */
    FirstManageSupplierQualityApproveDetailVo findById(@NotNull Long id,@NotNull Long shopId);
    /**
     * 列表
     *
     * @param pageable
     * @param firstManageSupplierQualityApproveSearchParam
     * @return
     */
    Page<FirstManageSupplierQualityApprovePageVo> queryFirstManageSupplierQualityApprove(@NotNull Pageable pageable, FirstManageSupplierQualityApproveSearchParam firstManageSupplierQualityApproveSearchParam) throws ParseException;


    /**
     * 保存 首营 审核
     * @param firstManageSupplierQualityApprove
     * @return
     */
    Long saveFirstManageSupplierQualityApprove(@NotNull @Valid FirstManageSupplierQualityApproveSaveVo firstManageSupplierQualityApprove);

    /**
     * 状态
     * @param firstManageSupplierQualityApproveId
     * @param approveStateCode
     * @return
     */
    Long updateApproveStateCode(@NotNull Long firstManageSupplierQualityApproveId, @NotBlank String approveStateCode);

    /**
     * 审核通过时间
     * @param firstManageSupplierQualityApproveId
     * @param
     * @return
     */
    Long updateQualityApproveTime(@NotNull Long firstManageSupplierQualityApproveId) throws ParseException;

}
