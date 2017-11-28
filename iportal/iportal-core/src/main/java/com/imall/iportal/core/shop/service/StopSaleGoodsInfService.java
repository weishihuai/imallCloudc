package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.StopSaleGoodsInf;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface StopSaleGoodsInfService{

    StopSaleGoodsInf save(StopSaleGoodsInf stopSaleGoodsInf);

    /**
     * 通过停售单id  停售批次商品信息
     * @param id
     * @return
     */
    List<StopSaleGoodsInf> findByDrugStopSaleNoticeId(@NotNull Long id);
}
