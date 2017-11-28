package com.imall.iportal.core.elasticsearch.provider;

import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.commons.dicts.GoodsTypeCodeEnum;
import com.imall.commons.dicts.IndexTypeCodeEnum;
import com.imall.iportal.core.elasticsearch.IIndexProvider;
import com.imall.iportal.core.elasticsearch.entity.GoodsBatchEntity;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class GoodsBatchIndexProvider implements IIndexProvider<GoodsBatchEntity> {

    @Override
    public GoodsBatchEntity getEntity(Long id) {
        GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(id);
        if (goodsBatch == null) {
            return null;
        }
        Goods goods = ServiceManager.goodsService.findOne(goodsBatch.getGoodsId());
        StorageSpace storageSpace = ServiceManager.storageSpaceService.findOne(goodsBatch.getStorageSpaceId());
        Sku sku = ServiceManager.skuService.findByGoodsId(goodsBatch.getGoodsId());

        GoodsBatchEntity entity = new GoodsBatchEntity();

        entity.setId(goodsBatch.getId());
        entity.setShopId(goodsBatch.getShopId());
        entity.setGoodsId(goodsBatch.getGoodsId());
        entity.setGoodsCode(goods.getGoodsCode());
        entity.setGoodsNm(goods.getGoodsNm());
        entity.setCommonNm(goods.getCommonNm());
        entity.setPinyin(goods.getPinyin());
        entity.setGoodsTypeCode(goods.getGoodsTypeCode());
        entity.setProduceManufacturer(goods.getProduceManufacturer());
        entity.setGoodsBatch(goodsBatch.getBatch());
        entity.setStorageSpaceId(goodsBatch.getStorageSpaceId());
        entity.setStorageSpaceType(storageSpace.getStorageSpaceType());
        entity.setHasStock(BoolCodeEnum.fromValue(goodsBatch.getCurrentStock() > 0).toCode());
        entity.setIsSplitZero(sku.getIsSplitZero());
        entity.setBatchState(goodsBatch.getBatchState());
        entity.setCreateDate(goodsBatch.getCreateDate());
        entity.setValidDate(goodsBatch.getValidDate());

        List<OtherInStock> otherInStockList = ServiceManager.otherInStockService.queryByGoodsBatchId(goodsBatch.getShopId(), goodsBatch.getId());
        if(otherInStockList==null || otherInStockList.size()==0){//没有其他入库记录，入库时间为批次创建时间
            entity.setInStockTime(goodsBatch.getCreateDate());
        }else{//有其他入库记录，入库时间为第一次入库的时间
            entity.setInStockTime(otherInStockList.get(0).getInStockTime());
        }

        setPrescriptionDrugsTypeCode(entity);

        setIsEphedrine(entity);

        setIsBuiltIn(entity);

        return entity;
    }


    /**
     * 设置是否内置
     * @param entity
     */
    private void setIsBuiltIn(GoodsBatchEntity entity) {
        StorageSpace storageSpace = ServiceManager.storageSpaceService.findOne(entity.getStorageSpaceId());
        entity.setIsVirtualWarehouse(storageSpace.getIsVirtualWarehouse());
        entity.setIsBuiltIn(storageSpace.getIsBuiltIn());
    }

    /**
     * 设置处方药类型（商品中药饮片）
     *
     * @param entity 索引
     */
    private void setPrescriptionDrugsTypeCode(GoodsBatchEntity entity) {

        if (GoodsTypeCodeEnum.fromCode(entity.getGoodsTypeCode()) == GoodsTypeCodeEnum.DRUG) {
            GoodsDrug goodsDrug = ServiceManager.goodsDrugService.findByGoodsId(entity.getId());
            if (goodsDrug == null) {
                return;
            }
            entity.setPrescriptionDrugsTypeCode(goodsDrug.getPrescriptionDrugsTypeCode());
        }

        if (GoodsTypeCodeEnum.fromCode(entity.getGoodsTypeCode()) == GoodsTypeCodeEnum.CHINESE_MEDICINE_PIECES) {
            GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(entity.getId());
            if (goodsChineseMedicinePieces == null) {
                return;
            }
            entity.setPrescriptionDrugsTypeCode(goodsChineseMedicinePieces.getPrescriptionDrugsTypeCode());
        }

    }

    /**
     * 设置麻黄碱 (药品/中药饮片)
     *
     * @param entity 索引
     */
    private void setIsEphedrine(GoodsBatchEntity entity) {
        if (GoodsTypeCodeEnum.fromCode(entity.getGoodsTypeCode()) == GoodsTypeCodeEnum.DRUG) {
            GoodsDrug goodsDrug = ServiceManager.goodsDrugService.findByGoodsId(entity.getId());
            if (goodsDrug == null) {
                return;
            }
            entity.setIsEphedrine(goodsDrug.getIsEphedrine());
        }

        if (GoodsTypeCodeEnum.fromCode(entity.getGoodsTypeCode()) == GoodsTypeCodeEnum.CHINESE_MEDICINE_PIECES) {
            GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(entity.getId());
            if (goodsChineseMedicinePieces == null) {
                return;
            }
            entity.setIsEphedrine(goodsChineseMedicinePieces.getIsEphedrine());
        }

    }

    @Override
    public IndexTypeCodeEnum actionType() {
        return IndexTypeCodeEnum.GOODS_BATCH;
    }

}
