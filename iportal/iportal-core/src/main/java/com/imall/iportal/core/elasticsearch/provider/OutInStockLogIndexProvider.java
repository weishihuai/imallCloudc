package com.imall.iportal.core.elasticsearch.provider;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.IndexTypeCodeEnum;
import com.imall.commons.dicts.OutInStockTypeCodeEnum;
import com.imall.iportal.core.elasticsearch.IIndexProvider;
import com.imall.iportal.core.elasticsearch.entity.OutInStockLogEntity;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.entity.Goods;
import com.imall.iportal.core.shop.entity.GoodsBatch;
import com.imall.iportal.core.shop.entity.OutInStockLog;
import org.springframework.stereotype.Component;


@Component
public class OutInStockLogIndexProvider implements IIndexProvider<OutInStockLogEntity> {

    @Override
    public OutInStockLogEntity getEntity(Long id) {
        OutInStockLog outInStockLog = ServiceManager.outInStockLogService.findOne(id);
        if (outInStockLog == null) {
            return null;
        }

        OutInStockLogEntity entity = CommonUtil.copyProperties(outInStockLog, new OutInStockLogEntity());
        Goods goods = ServiceManager.goodsService.findOne(outInStockLog.getGoodsId());
        GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(outInStockLog.getGoodsBatchId());
        entity.setGoodsNm(goods.getGoodsNm());
        entity.setCommonNm(goods.getCommonNm());
        entity.setGoodsCode(goods.getGoodsCode());
        entity.setBrandNm(goods.getBrandNm());
        entity.setPinyin(goods.getPinyin());
        entity.setBatch(goodsBatch.getBatch());

        switch (OutInStockTypeCodeEnum.fromCode(outInStockLog.getTypeCode())) {
            case OUT_STOCK:
                entity.setOutStockQuantity(outInStockLog.getQuantity());
                entity.setOutStockAmount(outInStockLog.getAmount());
                break;
            case IN_STOCK:
                entity.setInStockQuantity(outInStockLog.getQuantity());
                entity.setInStockAmount(outInStockLog.getAmount());
                break;
        }

        return entity;
    }


    @Override
    public IndexTypeCodeEnum actionType() {
        return IndexTypeCodeEnum.OUT_IN_STOCK_LOG;
    }
}
