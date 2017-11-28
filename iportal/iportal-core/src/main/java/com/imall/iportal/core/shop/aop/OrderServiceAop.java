package com.imall.iportal.core.shop.aop;

import com.imall.commons.dicts.IndexTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.entity.Order;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 订单 AOP
 */
@Aspect
@Component
public class OrderServiceAop {
    @AfterReturning("execution(public * com.imall.iportal.core.shop.repository.OrderRepository.save(..)) " +
            "|| execution(public * com.imall.iportal.core.shop.repository.OrderRepository.saveAndFlush(..))")
    public void afterSave(JoinPoint jp){
        Order order = (Order) jp.getArgs()[0];
        this.addIndexQueue(order.getId());
    }

    private void addIndexQueue(Long objectId) {
        ServiceManager.esIndexQueueService.saveQueue(IndexTypeCodeEnum.ORDER.toCode(), objectId, null);
    }
}
