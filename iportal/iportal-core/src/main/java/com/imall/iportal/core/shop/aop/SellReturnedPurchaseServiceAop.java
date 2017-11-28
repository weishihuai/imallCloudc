package com.imall.iportal.core.shop.aop;

import com.imall.commons.dicts.IndexTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.entity.SellReturnedPurchaseOrder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 销售退货订单 AOP
 */
@Aspect
@Component
public class SellReturnedPurchaseServiceAop {
    @AfterReturning("execution(public * com.imall.iportal.core.shop.repository.SellReturnedPurchaseOrderRepository.save(..)) " +
            "|| execution(public * com.imall.iportal.core.shop.repository.SellReturnedPurchaseOrderRepository.saveAndFlush(..))")
    public void afterSave(JoinPoint jp) {
        SellReturnedPurchaseOrder sellReturnedPurchaseOrder = (SellReturnedPurchaseOrder) jp.getArgs()[0];
        this.addIndexQueue(sellReturnedPurchaseOrder.getId());
    }

    private void addIndexQueue(Long objectId) {
        ServiceManager.esIndexQueueService.saveQueue(IndexTypeCodeEnum.SELL_RETURNED_PURCHASE_ORDER.toCode(), objectId, null);
    }
}
