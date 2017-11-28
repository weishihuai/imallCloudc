package com.imall.iportal.core.elasticsearch.provider;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.GoodsTypeCodeEnum;
import com.imall.commons.dicts.IndexTypeCodeEnum;
import com.imall.iportal.core.elasticsearch.IIndexProvider;
import com.imall.iportal.core.elasticsearch.entity.GoodsEntity;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.entity.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class GoodsIndexProvider implements IIndexProvider<GoodsEntity> {

    @Override
    public GoodsEntity getEntity(Long id) {
        Goods goods = ServiceManager.goodsService.findOne(id);
        if (goods == null) {
            return null;
        }

        GoodsEntity entity = CommonUtil.copyProperties(goods, new GoodsEntity());

        setGoodsBatchList(entity);

        setGoodsSplitZeroIds(entity);

        setPrescriptionDrugsTypeCode(entity);

        setIsEphedrine(entity);

        setSku(entity);

        //销售分类
        this.setSalesCategoryIds(entity, goods);
        return entity;
    }

    private void setSalesCategoryIds(GoodsEntity entity, Goods goods){
        String[] idArr = goods.getSellCategoryIds().split(",");
        List<Long> idList = new ArrayList<>();
        for (String id : idArr){
            idList.add(Long.valueOf(id));
        }
        entity.setSalesCategoryIds(idList.toArray(new Long[idList.size()]));
    }

    /**
     * 设置Sku 表的零售价和销量
     * @param entity
     */
    private void setSku(GoodsEntity entity) {
        Sku sku = ServiceManager.skuService.findByGoodsId(entity.getId());
        if(sku == null){
            return ;
        }
        entity.setSalesVolume(sku.getSalesVolume());
        entity.setRetailPrice(sku.getRetailPrice());
    }

    /**
     * 设置货位ID信息
     *
     * @param entity 索引
     */
    private void setGoodsBatchList(GoodsEntity entity) {
        List<GoodsBatch> batchList = ServiceManager.goodsBatchService.findByGoodsId(entity.getId());
        Set<Long> storageSpaceId = new HashSet<>();
        for (GoodsBatch batch : batchList) {
            storageSpaceId.add(batch.getStorageSpaceId());
        }
        entity.setStorageSpaceId(storageSpaceId.toArray(new Long[storageSpaceId.size()]));
    }

    /**
     * 设置商品拆零id
     *
     * @param entity 索引
     */
    private void setGoodsSplitZeroIds(GoodsEntity entity) {
        List<GoodsSplitZero> splitZeroList = ServiceManager.goodsSplitZeroService.findByGoodsId(entity.getId());
        List<Long> splitZeroIdList = new ArrayList<>();
        for (GoodsSplitZero batch : splitZeroList) {
            splitZeroIdList.add(batch.getId());
        }
        entity.setGoodsSplitZeroIds(splitZeroIdList.toArray(new Long[splitZeroIdList.size()]));
    }

    /**
     * 设置处方药类型（商品中药饮片）
     *
     * @param entity 索引
     */
    private void setPrescriptionDrugsTypeCode(GoodsEntity entity) {

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

    @Override
    public IndexTypeCodeEnum actionType() {
        return IndexTypeCodeEnum.GOODS;
    }

    /**
     * 设置麻黄碱 (药品/中药饮片)
     *
     * @param entity 索引
     */
    private void setIsEphedrine(GoodsEntity entity) {
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


}
