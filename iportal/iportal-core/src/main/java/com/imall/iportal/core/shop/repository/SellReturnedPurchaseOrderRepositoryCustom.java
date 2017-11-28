
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.SellReturnedPurchaseOrder;
import com.imall.iportal.core.shop.vo.SellReturnedPurchaseOrderSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 销售 退货 订单(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SellReturnedPurchaseOrderRepositoryCustom{

    /**
     * 查询销售退货记录
     * @param pageable
     * @param searchParam
     * @return
     */
    Page<SellReturnedPurchaseOrder> query(Pageable pageable, SellReturnedPurchaseOrderSearchParam searchParam);


    /**
     * 查找销售退货订单数据插入到索引队列
     * @return
     */
    Integer queryOrderToQueue();
}

