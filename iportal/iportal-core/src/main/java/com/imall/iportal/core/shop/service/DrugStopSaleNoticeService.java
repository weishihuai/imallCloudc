package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.vo.DrugStopSaleNoticeDetailVo;
import com.imall.iportal.core.shop.vo.DrugStopSaleNoticePageVo;
import com.imall.iportal.core.shop.vo.DrugStopSaleNoticeSaveVo;
import com.imall.iportal.core.shop.vo.DrugStopSaleNoticeSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.ParseException;

/**
 * (服务层类)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface DrugStopSaleNoticeService {

    /**
     * 停售单列表
     *
     * @param pageable
     * @param drugStopSaleNoticeSearchParam
     * @param shopId
     * @return
     */
    Page<DrugStopSaleNoticePageVo> query(Pageable pageable, DrugStopSaleNoticeSearchParam drugStopSaleNoticeSearchParam, @NotNull Long shopId) throws ParseException;

    Long saveDrugStopSaleNotice(@NotNull @Valid DrugStopSaleNoticeSaveVo drugStopSaleNoticeSaveVo);

    /**
     * 查找详细
     * @param id
     * @param shopId
     * @return
     */
    DrugStopSaleNoticeDetailVo findDetail(@NotNull Long id,@NotNull Long shopId);

}
