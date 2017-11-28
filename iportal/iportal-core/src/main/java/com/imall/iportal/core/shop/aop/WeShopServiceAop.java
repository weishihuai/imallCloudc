package com.imall.iportal.core.shop.aop;

import com.imall.commons.dicts.IndexTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.entity.WeShop;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by HWJ on 2017/7/6
 */
@Aspect
@Component
public class WeShopServiceAop {
    @AfterReturning("execution(public * com.imall.iportal.core.shop.repository.WeShopRepository.save(..)) || execution(public * com.imall.iportal.core.shop.repository.WeShopRepository.saveAndFlush(..))")
    public void afterSave(JoinPoint jp) {
        WeShop weShop = (WeShop) jp.getArgs()[0];
        this.addIndexQueue(weShop.getId());
    }

    @Before("execution(public * com.imall.iportal.core.shop.repository.WeShopRepository.delete(..))")
    public void beforeDelete(JoinPoint jp) {
        Object arg = jp.getArgs()[0];
        if (arg instanceof WeShop) {
            this.addIndexQueue(((WeShop) arg).getId());
        } else if (arg instanceof Long) {
            this.addIndexQueue((Long) arg);
        } else if (arg instanceof List) {
            List<Long> ids = (List<Long>) arg;
            for (Long id : ids) {
                this.addIndexQueue(id);
            }
        }
    }

    private void addIndexQueue(Long objectId) {
        ServiceManager.esIndexQueueService.saveQueue(IndexTypeCodeEnum.WE_SHOP.toCode(), objectId, null);
    }
}
