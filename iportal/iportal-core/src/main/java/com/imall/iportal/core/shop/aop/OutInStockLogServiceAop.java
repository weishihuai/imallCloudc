package com.imall.iportal.core.shop.aop;

import com.imall.commons.dicts.IndexTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.entity.OutInStockLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by HWJ on 2017/8/4
 */
@Aspect
@Component
public class OutInStockLogServiceAop {
    @AfterReturning("execution(public * com.imall.iportal.core.shop.repository.OutInStockLogRepository.save(..)) || execution(public * com.imall.iportal.core.shop.repository.OutInStockLogRepository.saveAndFlush(..))")
    public void afterSave(JoinPoint jp) {
        OutInStockLog log = (OutInStockLog) jp.getArgs()[0];
        this.addIndexQueue(log.getId());
    }

    @Before("execution(public * com.imall.iportal.core.shop.repository.OutInStockLogRepository.delete(..))")
    public void beforeDelete(JoinPoint jp) {
        Object arg = jp.getArgs()[0];
        if (arg instanceof OutInStockLog) {
            this.addIndexQueue(((OutInStockLog) arg).getId());
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
        ServiceManager.esIndexQueueService.saveQueue(IndexTypeCodeEnum.OUT_IN_STOCK_LOG.toCode(), objectId, null);
    }
}
