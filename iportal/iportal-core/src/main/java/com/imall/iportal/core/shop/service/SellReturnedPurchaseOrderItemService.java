package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.SellReturnedPurchaseOrderItem;
import com.imall.iportal.core.shop.vo.SRPReadyOrderItemVo;
import com.imall.iportal.core.shop.vo.SellReturnedPurchaseOrderItemPageVo;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 销售 退货 订单 项(服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SellReturnedPurchaseOrderItemService{


    SellReturnedPurchaseOrderItem save(SellReturnedPurchaseOrderItem orderItem);

    //计算已退数量
    Long queryCalcReturnedQuantity(Long orderItemId);

    /**
     * 根据订单ID查找订单项
     * @param orderId 订单ID
     * @return
     */
    List<SRPReadyOrderItemVo> findVoByOrderId(@NotNull Long orderId);

    /**
     * 根据退货订单ID查找
     * @param sellReturnedPurchaseOrderId 退货订单ID
     * @return
     */
    List<SellReturnedPurchaseOrderItemPageVo> findItemPageVoByReturnedPurchaseOrderId(Long sellReturnedPurchaseOrderId);

    /**
     * 通过退货订单ID查找退货订单项List
     * @param id
     * @return
     */
    List<SellReturnedPurchaseOrderItem> findByReturnedPurchaseOrderId(Long id);

    /**
     * 计算改销售退货订单商品总数量
     * @param sellReturnPurchaseOrderId
     * @return
     */
    Long findSellReturnPurchaseGoodsQuantity(@NotNull Long sellReturnPurchaseOrderId);
}
