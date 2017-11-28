package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.FirstManageDrugQualityApprove;
import com.imall.iportal.core.shop.vo.FirstManageDrugQualityApproveDetailVo;
import com.imall.iportal.core.shop.vo.FirstManageDrugQualityApprovePageVo;
import com.imall.iportal.core.shop.vo.FirstManageDrugQualityApproveSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.text.ParseException;

/**
 * (服务层类)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface FirstManageDrugQualityApproveService {


    FirstManageDrugQualityApprove save(FirstManageDrugQualityApprove firstManageDrugQualityApprove);

    /**
     * 药品首营审核列表
     *
     * @param pageable
     * @param firstManageDrugQualityApproveSearchParam
     * @param shopId
     * @return
     */
    Page<FirstManageDrugQualityApprovePageVo> query(Pageable pageable, FirstManageDrugQualityApproveSearchParam firstManageDrugQualityApproveSearchParam, @NotNull Long shopId) throws ParseException;

    FirstManageDrugQualityApprove findOne(@NotNull Long id);

    /**
     * 首营商品审核详情
     * @param id
     * @param shopId
     * @return
     */
    FirstManageDrugQualityApproveDetailVo findDetail(@NotNull Long id,@NotNull Long shopId);

    /**
     * 查找商品首营信息
     * @param goodsId 商品ID
     * @return
     */
    FirstManageDrugQualityApprove findByGoodsId(@NotNull Long goodsId);

}
