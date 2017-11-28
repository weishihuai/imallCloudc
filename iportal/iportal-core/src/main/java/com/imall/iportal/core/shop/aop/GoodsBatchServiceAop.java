package com.imall.iportal.core.shop.aop;

import com.imall.commons.dicts.GoodsBatchStateCodeEnum;
import com.imall.commons.dicts.IndexTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.entity.GoodsBatch;
import com.imall.iportal.core.shop.entity.Sku;
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
public class GoodsBatchServiceAop {

    @Before("execution(public * com.imall.iportal.core.shop.repository.GoodsBatchRepository.save(..)) || execution(public * com.imall.iportal.core.shop.repository.GoodsBatchRepository.saveAndFlush(..))")
    public void beforeSave(JoinPoint jp) {
        GoodsBatch goodsBatch = (GoodsBatch) jp.getArgs()[0];
        if(GoodsBatchStateCodeEnum.fromCode(goodsBatch.getBatchState()) == GoodsBatchStateCodeEnum.DELETE) {
            Sku sku = ServiceManager.skuService.findOne(goodsBatch.getSkuId());
            sku.setCurrentStock(sku.getCurrentStock() - goodsBatch.getCurrentStock());
            ServiceManager.skuService.update(sku);
            return;
        }

        if(goodsBatch.getId() == null) {
            Sku sku = ServiceManager.skuService.findOne(goodsBatch.getSkuId());
            sku.setCurrentStock(sku.getCurrentStock() + goodsBatch.getCurrentStock());
            ServiceManager.skuService.update(sku);
        }else {
            GoodsBatch oldGoodsBatch = ServiceManager.goodsBatchService.findOneByCustom(goodsBatch.getId());
            Sku sku = ServiceManager.skuService.findOne(goodsBatch.getSkuId());
            Long updateQuantity = goodsBatch.getCurrentStock() - oldGoodsBatch.getCurrentStock();
            if(updateQuantity != 0) {
                sku.setCurrentStock(sku.getCurrentStock() + updateQuantity);
                ServiceManager.skuService.update(sku);
            }
            if(!goodsBatch.getStorageSpaceId().equals(oldGoodsBatch.getStorageSpaceId())) {
                addGoodsIndexQueue(goodsBatch.getGoodsId());
            }
        }
    }

    @AfterReturning("execution(public * com.imall.iportal.core.shop.repository.GoodsBatchRepository.save(..)) || execution(public * com.imall.iportal.core.shop.repository.GoodsBatchRepository.saveAndFlush(..))")
    public void afterSave(JoinPoint jp) {
        GoodsBatch goodsBatch = (GoodsBatch) jp.getArgs()[0];
        this.addIndexQueue(goodsBatch.getId());
    }

    @Before("execution(public * com.imall.iportal.core.shop.repository.GoodsBatchRepository.delete(..))")
    public void beforeDelete(JoinPoint jp) {
        Object arg = jp.getArgs()[0];
        if (arg instanceof GoodsBatch) {
            this.addIndexQueue(((GoodsBatch) arg).getId());
            this.addGoodsIndexQueue(((GoodsBatch) arg).getGoodsId());
            this.deleteUpdateStock((GoodsBatch) arg);
        } else if (arg instanceof Long) {
            GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne((Long) arg);
            this.addIndexQueue(goodsBatch.getId());
            this.addGoodsIndexQueue(goodsBatch.getGoodsId());
            this.deleteUpdateStock(goodsBatch);
        } else if (arg instanceof List) {
            List<Long> ids = (List<Long>) arg;
            for (Long id : ids) {
                GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(id);
                this.addIndexQueue(id);
                this.addGoodsIndexQueue(goodsBatch.getGoodsId());
                this.deleteUpdateStock(goodsBatch);
            }
        }
    }

    private void deleteUpdateStock(GoodsBatch goodsBatch) {
        Sku sku = ServiceManager.skuService.findOne(goodsBatch.getSkuId());
        sku.setCurrentStock(sku.getCurrentStock() - goodsBatch.getCurrentStock());
        ServiceManager.skuService.update(sku);
    }

    private void addIndexQueue(Long objectId) {
        ServiceManager.esIndexQueueService.saveQueue(IndexTypeCodeEnum.GOODS_BATCH.toCode(), objectId, null);
    }

    private void addGoodsIndexQueue(Long objectId) {
        ServiceManager.esIndexQueueService.saveQueue(IndexTypeCodeEnum.GOODS.toCode(), objectId, null);
    }
}
