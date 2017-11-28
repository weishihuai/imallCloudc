package com.imall.iportal.core.shop.aop;

import com.imall.commons.dicts.IndexTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.entity.GoodsSplitZero;
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
public class GoodsSplitZeroServiceAop {
    @AfterReturning("execution(public * com.imall.iportal.core.shop.repository.GoodsSplitZeroRepository.save(..)) || execution(public * com.imall.iportal.core.shop.repository.GoodsSplitZeroRepository.saveAndFlush(..))")
    public void afterSave(JoinPoint jp) {
        GoodsSplitZero goodsSplitZero = (GoodsSplitZero) jp.getArgs()[0];
        this.addIndexQueue(goodsSplitZero.getGoodsId());
    }

    @Before("execution(public * com.imall.iportal.core.shop.repository.GoodsSplitZeroRepository.delete(..))")
    public void beforeDelete(JoinPoint jp) {
        Object arg = jp.getArgs()[0];
        if (arg instanceof GoodsSplitZero) {
            this.addIndexQueue(((GoodsSplitZero) arg).getGoodsId());
        } else if (arg instanceof Long) {
            GoodsSplitZero goodsSplitZero = ServiceManager.goodsSplitZeroService.findOne((Long) arg);
            this.addIndexQueue(goodsSplitZero.getGoodsId());
        } else if (arg instanceof List) {
            List<Long> ids = (List<Long>) arg;
            for (Long id : ids) {
                GoodsSplitZero goodsSplitZero = ServiceManager.goodsSplitZeroService.findOne(id);
                this.addIndexQueue(goodsSplitZero.getGoodsId());
            }
        }
    }

    private void addIndexQueue(Long objectId) {
        ServiceManager.esIndexQueueService.saveQueue(IndexTypeCodeEnum.GOODS.toCode(), objectId, null);
    }
}
