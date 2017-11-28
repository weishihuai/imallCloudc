package com.imall.iportal.core.shop.aop;

import com.imall.commons.dicts.IndexTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.entity.Goods;
import com.imall.iportal.core.shop.entity.GoodsBatch;
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
public class GoodsServiceAop {
    @AfterReturning("execution(public * com.imall.iportal.core.shop.repository.GoodsRepository.save(..)) || execution(public * com.imall.iportal.core.shop.repository.GoodsRepository.saveAndFlush(..))")
    public void afterSave(JoinPoint jp) {
        Goods goods = (Goods) jp.getArgs()[0];
        //version是0，代表是新增，因为新增的时候已经立即提交索引了，所以就没必要再往索引队里里面插数据了
        if (0 != goods.getVersion()){
            this.addIndexQueue(goods.getId());
        }
        updateGoodsBatch(goods);
        updateGoodsSplitZero(goods);
    }

    @Before("execution(public * com.imall.iportal.core.shop.repository.GoodsRepository.delete(..))")
    public void beforeDelete(JoinPoint jp) {
        Object arg = jp.getArgs()[0];
        if (arg instanceof Goods) {
            this.addIndexQueue(((Goods) arg).getId());
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
        ServiceManager.esIndexQueueService.saveQueue(IndexTypeCodeEnum.GOODS.toCode(), objectId, null);
    }

    private void updateGoodsBatch(Goods goods) {
        List<GoodsBatch> goodsBatchList = ServiceManager.goodsBatchService.findByGoodsId(goods.getId());
        for(GoodsBatch goodsBatch : goodsBatchList) {
            if(goods.getGoodsNm().equals(goodsBatch.getGoodsNm()) && goods.getGoodsCode().equals(goodsBatch.getGoodsCode()) && goods.getPinyin().equals(goodsBatch.getPinyin())) {
                continue;
            }
            goodsBatch.setGoodsNm(goods.getGoodsNm());
            goodsBatch.setGoodsCode(goods.getGoodsCode());
            goodsBatch.setPinyin(goods.getPinyin());
            ServiceManager.goodsBatchService.save(goodsBatch);
        }
    }

    private void updateGoodsSplitZero(Goods goods) {
        List<GoodsSplitZero> splitZeroList = ServiceManager.goodsSplitZeroService.findByGoodsId(goods.getId());
        for(GoodsSplitZero splitZero : splitZeroList) {
            if(goods.getGoodsNm().equals(splitZero.getGoodsNm()) && goods.getGoodsCode().equals(splitZero.getGoodsCode()) && goods.getPinyin().equals(splitZero.getPinyin())) {
                continue;
            }
            splitZero.setGoodsNm(goods.getGoodsNm());
            splitZero.setGoodsCode(goods.getGoodsCode());
            splitZero.setPinyin(goods.getPinyin());
            ServiceManager.goodsSplitZeroService.save(splitZero);
        }
    }

}
