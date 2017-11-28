package com.imall.iportal.core.shop.service;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.dicts.OrderSourceCodeEnum;
import com.imall.iportal.core.shop.entity.SkuLockStock;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * SKU_锁_库存(服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface SkuLockStockService{

    /**
     * 订单提交时：锁库存
     * @param shopId
     * @param memberId
     * @param orderNum
     * @param skuLockStocks
     * @return
     * @throws BusinessException
     */
    void lockedStock(@NotNull Long shopId, Long memberId, @NotBlank String orderNum, OrderSourceCodeEnum orderSourceCode, @NotEmpty List<SkuLockStock> skuLockStocks) throws BusinessException;

    /**
     * 订单关闭时：解锁库存
     * @param orderNum
     * @return
     */
    void unLockStock(@NotBlank String orderNum);


    /**
     * 订单发货时：更新锁状态为用锁
     * @param orderNum
     * @return
     */
    void useLock(@NotBlank String orderNum);

}
