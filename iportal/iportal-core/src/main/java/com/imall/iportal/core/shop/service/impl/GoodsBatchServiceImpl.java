package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.BigDecimalUtil;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.*;
import com.imall.iportal.core.elasticsearch.ESUtils;
import com.imall.iportal.core.elasticsearch.entity.GoodsBatchEntity;
import com.imall.iportal.core.elasticsearch.utils.SearchUtils;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysDictItem;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.*;
import com.imall.iportal.core.shop.repository.GoodsBatchRepository;
import com.imall.iportal.core.shop.service.GoodsBatchService;
import com.imall.iportal.core.shop.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * (服务层实现)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class GoodsBatchServiceImpl extends AbstractBaseService<GoodsBatch, Long> implements GoodsBatchService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Integer expireDate;

    @Value("${app.shop.expireDateGoods.expireDate}")
    public void setExpireDate(Integer expireDate) {
        this.expireDate = expireDate;
    }

    @SuppressWarnings("unused")
    private GoodsBatchRepository getGoodsBatchRepository() {
        return (GoodsBatchRepository) baseRepository;
    }

    @Override
    public Page<GoodsBatchVo> query(Pageable pageable, String searchFields, String batch, Long shopId) {
        Page<GoodsBatch> goodsBatchPage = getGoodsBatchRepository().query(pageable, searchFields, batch, shopId);
        List<GoodsBatchVo> goodsBatchVoArrayList = new ArrayList<>();
        for (GoodsBatch goodsBatch : goodsBatchPage.getContent()) {
            goodsBatchVoArrayList.add(buildGoodsBatchVo(goodsBatch));
        }
        return new PageImpl<>(goodsBatchVoArrayList, new PageRequest(goodsBatchPage.getNumber(), goodsBatchPage.getSize()), goodsBatchPage.getTotalElements());
    }

    @Override
    public Page<ExpireDrugWarningGoodsVo> queryExpireDrugWarning(Pageable pageable, StockWarningSearchParam stockWarningSearchParam) {
        Page<GoodsBatch> expireDrugWarningPage = getGoodsBatchRepository().queryExpireDrugWarningPage(pageable, stockWarningSearchParam);
        List<ExpireDrugWarningGoodsVo> expireDrugWarningGoodsVoList = new ArrayList<>();
        for (GoodsBatch goodsBatch : expireDrugWarningPage.getContent()) {
            expireDrugWarningGoodsVoList.add(buildExpireDrugWarningGoodsVo(goodsBatch));
        }
        return new PageImpl<>(expireDrugWarningGoodsVoList, new PageRequest(expireDrugWarningPage.getNumber(), expireDrugWarningPage.getSize()), expireDrugWarningPage.getTotalElements());
    }

    @Override
    public Page<NearExpireDateGoodsVo> queryNearExpireDatePage(Pageable pageable, StockWarningSearchParam stockWarningSearchParam) {
        Page<GoodsBatch> nearExpireDatePage = getGoodsBatchRepository().queryNearExpireDatePage(pageable, stockWarningSearchParam, expireDate);
        List<NearExpireDateGoodsVo> nearExpireDateGoodsVoList = new ArrayList<>();
        for (GoodsBatch goodsBatch : nearExpireDatePage.getContent()) {
            nearExpireDateGoodsVoList.add(buildNearExpireDateGoodsVo(goodsBatch));
        }
        return new PageImpl<>(nearExpireDateGoodsVoList, new PageRequest(nearExpireDatePage.getNumber(), nearExpireDatePage.getSize()), nearExpireDatePage.getTotalElements());
    }

    /**
     * 构建近效期催销对象
     *
     * @param goodsBatch
     * @return
     */
    private NearExpireDateGoodsVo buildNearExpireDateGoodsVo(GoodsBatch goodsBatch) {
        NearExpireDateGoodsVo nearExpireDateGoodsVo = CommonUtil.copyProperties(goodsBatch, new NearExpireDateGoodsVo());
        Goods goods = ServiceManager.goodsService.findOne(goodsBatch.getGoodsId());
        nearExpireDateGoodsVo.setSpec(goods.getSpec());
        nearExpireDateGoodsVo.setUnit(goods.getUnit());
        nearExpireDateGoodsVo.setProduceManufacturer(goods.getProduceManufacturer());
        switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())) {
            case CHINESE_MEDICINE_PIECES:  //中药饮片
                GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goodsBatch.getGoodsId());
                checkGoodsChineseMedicinePiecesIsNull(goodsChineseMedicinePieces);
                nearExpireDateGoodsVo.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
                SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
                nearExpireDateGoodsVo.setDosageForm(sysDictItem.getDictItemNm());
                nearExpireDateGoodsVo.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
                break;
            case DRUG:     //药品
                GoodsDrug goodDrug = ServiceManager.goodsDrugService.findByGoodsId(goods.getId());
                checkGoodsDrugIsNull(goodDrug);
                nearExpireDateGoodsVo.setApprovalNumber(goodDrug.getApprovalNumber());
                SysDictItem sysDictItem2 = ServiceManager.sysDictItemService.findByDictItemCode(goodDrug.getDosageForm());
                nearExpireDateGoodsVo.setDosageForm(sysDictItem2.getDictItemNm());
                break;
            case DAILY_NECESSITIES:  //日用品
                GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(goods.getId());
                checkGoodsDailyNecessitiesIsNull(goodsDailyNecessities);
                nearExpireDateGoodsVo.setApprovalNumber(goodsDailyNecessities.getApprovalNumber());
                break;
            case OTHER:  //其他
                GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(goods.getId());
                checkGoodsOtherIsNull(goodsOther);
                nearExpireDateGoodsVo.setApprovalNumber(goodsOther.getApprovalNumber());
                break;
            case COSMETIC:   //化妆品
                GoodsCosmetic goodsCosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(goods.getId());
                checkGoodsCosmeticIsNull(goodsCosmetic);
                nearExpireDateGoodsVo.setApprovalNumber(goodsCosmetic.getApprovalNumber());
                break;
        }
        Supplier supplier = ServiceManager.supplierService.findOne(goodsBatch.getSupplierId());
        nearExpireDateGoodsVo.setSupplierNm(supplier.getSupplierNm());

        List<GoodsBatch> batchList = getGoodsBatchRepository().findByGoodsIdAndBatch(goodsBatch.getGoodsId(), goodsBatch.getBatch());
        Long totalQuantity = 0L;
        Double totalAmount = 0D;
        for (GoodsBatch batch : batchList) {
            if(batch.getCurrentStock() <= 0 || GoodsBatchStateCodeEnum.DELETE == GoodsBatchStateCodeEnum.fromCode(batch.getBatchState())) {
                continue;
            }
            totalQuantity += batch.getCurrentStock();
            totalAmount = BigDecimalUtil.add(totalAmount, BigDecimalUtil.multiply(batch.getCurrentStock(), batch.getPurchasePrice()));
        }
        nearExpireDateGoodsVo.setCurrentStock(totalQuantity);
        //库存金额
        nearExpireDateGoodsVo.setStockMoney(totalAmount);
        nearExpireDateGoodsVo.setProduceDateString(goodsBatch.getProduceDateString());
        nearExpireDateGoodsVo.setValidDateString(goodsBatch.getValidDateString());
        return nearExpireDateGoodsVo;
    }

    private void checkGoodsOtherIsNull(GoodsOther goodsOther) {
        if (goodsOther == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"其他药品"}));
        }
    }

    private void checkGoodsCosmeticIsNull(GoodsCosmetic goodsCosmetic) {
        if (goodsCosmetic == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"商品化妆品"}));
        }
    }

    private void checkGoodsDrugIsNull(GoodsDrug goodDrug) {
        if (goodDrug == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"药品"}));
        }
    }

    private void checkGoodsChineseMedicinePiecesIsNull(GoodsChineseMedicinePieces goodsChineseMedicinePieces) {
        if (goodsChineseMedicinePieces == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"中药饮片"}));
        }
    }

    /**
     * 构建过期药品对象
     *
     * @param goodsBatch
     * @return
     */
    private ExpireDrugWarningGoodsVo buildExpireDrugWarningGoodsVo(GoodsBatch goodsBatch) {
        ExpireDrugWarningGoodsVo expireDrugWarningGoodsVo = CommonUtil.copyProperties(goodsBatch, new ExpireDrugWarningGoodsVo());
        Goods goods = ServiceManager.goodsService.findOne(goodsBatch.getGoodsId());
        expireDrugWarningGoodsVo.setSpec(goods.getSpec());
        expireDrugWarningGoodsVo.setUnit(goods.getUnit());
        expireDrugWarningGoodsVo.setProduceManufacturer(goods.getProduceManufacturer());
        switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())) {
            case CHINESE_MEDICINE_PIECES:  //中药饮片
                GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goodsBatch.getGoodsId());
                checkGoodsChineseMedicinePiecesIsNull(goodsChineseMedicinePieces);
                expireDrugWarningGoodsVo.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
                SysDictItem sysDictItem2 = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
                expireDrugWarningGoodsVo.setDosageForm(sysDictItem2.getDictItemNm());
                expireDrugWarningGoodsVo.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
                break;
            case DRUG:     //药品
                GoodsDrug goodDrug = ServiceManager.goodsDrugService.findByGoodsId(goods.getId());
                checkGoodsDrugIsNull(goodDrug);
                GoodsDrug goodsDrug = ServiceManager.goodsDrugService.findByGoodsId(goodsBatch.getGoodsId());
                SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsDrug.getDosageForm());
                expireDrugWarningGoodsVo.setDosageForm(sysDictItem.getDictItemNm());
                expireDrugWarningGoodsVo.setApprovalNumber(goodDrug.getApprovalNumber());
                break;
            case COSMETIC:   //化妆品
                GoodsCosmetic goodsCosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(goods.getId());
                checkGoodsCosmeticIsNull(goodsCosmetic);
                expireDrugWarningGoodsVo.setApprovalNumber(goodsCosmetic.getApprovalNumber());
                break;
            case DAILY_NECESSITIES:  //日用品
                GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(goods.getId());
                checkGoodsDailyNecessitiesIsNull(goodsDailyNecessities);
                expireDrugWarningGoodsVo.setApprovalNumber(goodsDailyNecessities.getApprovalNumber());
                break;
            case OTHER:  //其他
                GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(goods.getId());
                checkGoodsOtherIsNull(goodsOther);
                expireDrugWarningGoodsVo.setApprovalNumber(goodsOther.getApprovalNumber());
                break;
        }
        Supplier supplier = ServiceManager.supplierService.findOne(goodsBatch.getSupplierId());
        expireDrugWarningGoodsVo.setSupplierNm(supplier.getSupplierNm());

        List<GoodsBatch> batchList = getGoodsBatchRepository().findByGoodsIdAndBatch(goodsBatch.getGoodsId(), goodsBatch.getBatch());
        Long totalQuantity = 0L;
        Double totalAmount = 0D;
        for (GoodsBatch batch : batchList) {
            if(batch.getCurrentStock() <= 0 || GoodsBatchStateCodeEnum.DELETE == GoodsBatchStateCodeEnum.fromCode(batch.getBatchState())) {
                continue;
            }
            totalQuantity += batch.getCurrentStock();
            totalAmount = BigDecimalUtil.add(totalAmount, BigDecimalUtil.multiply(batch.getCurrentStock(), batch.getPurchasePrice()));
        }
        expireDrugWarningGoodsVo.setCurrentStock(totalQuantity);
        expireDrugWarningGoodsVo.setStockMoney(totalAmount);
        expireDrugWarningGoodsVo.setProduceDateString(goodsBatch.getProduceDateString());
        expireDrugWarningGoodsVo.setValidDateString(goodsBatch.getValidDateString());
        return expireDrugWarningGoodsVo;
    }

    /**
     * 构建GoodsBatchVo对象
     *
     * @param goodsBatch 商品批次信息
     * @return
     */
    private GoodsBatchVo buildGoodsBatchVo(GoodsBatch goodsBatch) {
        GoodsBatchVo goodsBatchVo = CommonUtil.copyProperties(goodsBatch, new GoodsBatchVo());
        Goods goods = ServiceManager.goodsService.findOne(goodsBatch.getGoodsId());
        goodsBatchVo.setSpec(goods.getSpec());
        goodsBatchVo.setUnit(goods.getUnit());
        goodsBatchVo.setProduceManufacturer(goods.getProduceManufacturer());
        StorageSpace storageSpace = ServiceManager.storageSpaceService.findOne(goodsBatch.getStorageSpaceId());
        goodsBatchVo.setStorageSpaceNm(storageSpace.getStorageSpaceNm());
        switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())) {
            case CHINESE_MEDICINE_PIECES:  //中药饮片
                GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goodsBatch.getGoodsId());
                checkGoodsChineseMedicinePiecesIsNull(goodsChineseMedicinePieces);
                goodsBatchVo.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
                SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
                goodsBatchVo.setDosageForm(sysDictItem.getDictItemNm());
                goodsBatchVo.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
                break;
            case DRUG:     //药品
                GoodsDrug goodDrug = ServiceManager.goodsDrugService.findByGoodsId(goods.getId());
                checkGoodsDrugIsNull(goodDrug);
                goodsBatchVo.setApprovalNumber(goodDrug.getApprovalNumber());
                SysDictItem sysDictItem2 = ServiceManager.sysDictItemService.findByDictItemCode(goodDrug.getDosageForm());
                goodsBatchVo.setDosageForm(sysDictItem2.getDictItemNm());
                break;
            case COSMETIC:   //化妆品
                GoodsCosmetic goodsCosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(goods.getId());
                checkGoodsCosmeticIsNull(goodsCosmetic);
                goodsBatchVo.setApprovalNumber(goodsCosmetic.getApprovalNumber());
                break;
            case OTHER:  //其他
                GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(goods.getId());
                checkGoodsOtherIsNull(goodsOther);
                goodsBatchVo.setApprovalNumber(goodsOther.getApprovalNumber());
                break;
            case DAILY_NECESSITIES:  //日用品
                GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(goods.getId());
                checkGoodsDailyNecessitiesIsNull(goodsDailyNecessities);
                goodsBatchVo.setApprovalNumber(goodsDailyNecessities.getApprovalNumber());
                break;
        }
        Supplier supplier = ServiceManager.supplierService.findOne(goodsBatch.getSupplierId());
        goodsBatchVo.setSupplierNm(supplier.getSupplierNm());
        return goodsBatchVo;
    }

    private void checkGoodsDailyNecessitiesIsNull(GoodsDailyNecessities goodsDailyNecessities) {
        if (goodsDailyNecessities == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"日用品"}));
        }
    }

    @Override
    public List<GoodsBatch> findByGoodsId(Long goodsId) {
        return getGoodsBatchRepository().findByGoodsId(goodsId);
    }

    @Override
    @Transactional
    public void updateBatch(GoodsBatchUpdateVo goodsBatchUpdateVo, Long shopId) throws ParseException {
        GoodsBatch goodsBatch = this.findOne(goodsBatchUpdateVo.getId());
        if (goodsBatch == null || !shopId.equals(goodsBatch.getShopId())) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"批号"}));
        }
        goodsBatch.setProduceDate(DateTimeUtils.convertStringToDate(goodsBatchUpdateVo.getNewProduceDateString()));
        goodsBatch.setValidDate(DateTimeUtils.convertStringToDate(goodsBatchUpdateVo.getNewValidDateString()));
        goodsBatch.setBatch(goodsBatchUpdateVo.getNewBatch());
        update(goodsBatch);
        //生成批次修改记录
        GoodsBatchModRecord goodsBatchModRecord = new GoodsBatchModRecord();
        goodsBatchModRecord.setApproveManId(goodsBatchUpdateVo.getApproveManId());
        goodsBatchModRecord.setGoodsBatchId(goodsBatch.getId());
        goodsBatchModRecord.setNewBatch(goodsBatchUpdateVo.getNewBatch());
        goodsBatchModRecord.setOldBatch(goodsBatch.getBatch());
        goodsBatchModRecord.setOldProduceDate(DateTimeUtils.convertStringToDate(goodsBatch.getProduceDateString()));
        goodsBatchModRecord.setNewProduceDate(DateTimeUtils.convertStringToDate(goodsBatchUpdateVo.getNewProduceDateString()));
        goodsBatchModRecord.setOldValidDate(DateTimeUtils.convertStringToDate(goodsBatch.getValidDateString()));
        goodsBatchModRecord.setNewValidDate(DateTimeUtils.convertStringToDate(goodsBatchUpdateVo.getNewValidDateString()));
        ServiceManager.goodsBatchModRecordService.save(goodsBatchModRecord);
    }


    public GoodsBatchStockInfo findCurStockInfo(Long goodsId) {
        List<GoodsBatch> goodsBatchList = getGoodsBatchRepository().findByGoodsId(goodsId);
        Long curStock = 0L;
        Double amount = 0D;
        for (GoodsBatch goodsBatch : goodsBatchList) {
            if (goodsBatch.getCurrentStock() < 1) {
                continue;
            }
            curStock += goodsBatch.getCurrentStock();
            amount += goodsBatch.getCurrentStock() * goodsBatch.getPurchasePrice();
        }

        GoodsBatchStockInfo stockInfo = new GoodsBatchStockInfo();
        stockInfo.setCurrentStock(curStock);
        stockInfo.setTotalAmount(amount);

        return stockInfo;
    }

    @Override
    public Page<GoodsBatchCommonComponentPageVo> queryCommonGoodsBatchList(Pageable pageable, GoodsCommonComponentSearchParam goodsCommonComponentSearchParam, Long shopId) {

        Page<GoodsBatch> goodsBatchPage = queryByEs(pageable, goodsCommonComponentSearchParam, shopId);
        List<GoodsBatchCommonComponentPageVo> goodsBatchCommonComponentPageVos = new ArrayList<>();
        for (GoodsBatch goodsBatch : goodsBatchPage.getContent()) {
            goodsBatchCommonComponentPageVos.add(buildCommonGoodsBatchPageVo(goodsBatch));
        }

        return new PageImpl<>(goodsBatchCommonComponentPageVos, pageable, goodsBatchPage.getTotalElements());
    }

    private Page<GoodsBatch> queryByEs(Pageable pageable, GoodsCommonComponentSearchParam goodsCommonComponentSearchParam, Long shopId) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        queryBuilder.must(QueryBuilders.termQuery(GoodsBatchEntity.SHOP_ID, shopId));

        //拼音/商品编码/商品名称
        if (StringUtils.isNotBlank(goodsCommonComponentSearchParam.getKeyWords())) {
            BoolQueryBuilder keywordBuilder = QueryBuilders.boolQuery()
                    .should(QueryBuilders.termQuery(GoodsBatchEntity.PINYIN, goodsCommonComponentSearchParam.getKeyWords()))
                    .should(QueryBuilders.termQuery(GoodsBatchEntity.GOODS_NM, goodsCommonComponentSearchParam.getKeyWords()))
                    .should(QueryBuilders.termQuery(GoodsBatchEntity.COMMON_NM, goodsCommonComponentSearchParam.getKeyWords()));
            queryBuilder.must(keywordBuilder);
        }

        //商品编码
        if (StringUtils.isNotBlank(goodsCommonComponentSearchParam.getGoodsCode())) {
            queryBuilder.must(QueryBuilders.termQuery(GoodsBatchEntity.GOODS_CODE, goodsCommonComponentSearchParam.getGoodsCode()));
        }

        //批次状态
        if (StringUtils.isNotBlank(goodsCommonComponentSearchParam.getBatchState())) {
            queryBuilder.must(QueryBuilders.termQuery(GoodsBatchEntity.BATCH_STATE, goodsCommonComponentSearchParam.getBatchState()));
        }

        //批号
        if (StringUtils.isNotBlank(goodsCommonComponentSearchParam.getBatch())) {
            queryBuilder.must(QueryBuilders.termQuery(GoodsBatchEntity.GOODS_BATCH, goodsCommonComponentSearchParam.getBatch()));
        }

        //生产厂家
        if (StringUtils.isNotBlank(goodsCommonComponentSearchParam.getProduceManufacturer())) {
            queryBuilder.must(QueryBuilders.termQuery(GoodsBatchEntity.PRODUCE_MANUFACTURER, goodsCommonComponentSearchParam.getProduceManufacturer()));
        }

        //处方药 类型
        if (StringUtils.isNotBlank(goodsCommonComponentSearchParam.getPrescriptionDrugsTypeCode())) {
            queryBuilder.must(QueryBuilders.termQuery(GoodsBatchEntity.PRESCRIPTION_DRUGS_TYPE_CODE, PrescriptionDrugsTypeCodeEnum.fromCode(goodsCommonComponentSearchParam.getPrescriptionDrugsTypeCode()).toCode()));
        }

        //麻黄碱
        if (StringUtils.isNotBlank(goodsCommonComponentSearchParam.getIsEphedrine())) {
            queryBuilder.must(QueryBuilders.termQuery(GoodsBatchEntity.IS_EPHEDRINE, goodsCommonComponentSearchParam.getIsEphedrine()));
        }
        //是否内置
        if (StringUtils.isNotBlank(goodsCommonComponentSearchParam.getIsBuiltIn())) {
            queryBuilder.must(QueryBuilders.termQuery(GoodsBatchEntity.IS_BUILT_IN, goodsCommonComponentSearchParam.getIsBuiltIn()));
        }
        //是否 虚拟货位
        if (StringUtils.isNotBlank(goodsCommonComponentSearchParam.getIsVirtualWarehouse())) {
            queryBuilder.must(QueryBuilders.termQuery(GoodsBatchEntity.IS_VIRTUAL_WAREHOUSE, goodsCommonComponentSearchParam.getIsVirtualWarehouse()));
        }
        //是否 库存大于〇
        if (StringUtils.isNotBlank(goodsCommonComponentSearchParam.getIsBiggerThanCurrentStock())) {
            queryBuilder.must(QueryBuilders.termQuery(GoodsBatchEntity.HAS_STOCK, goodsCommonComponentSearchParam.getIsBiggerThanCurrentStock()));
        }

        //是否中药饮片
        if (StringUtils.isNotBlank(goodsCommonComponentSearchParam.getIsChineseMedicinePieces()) && BoolCodeEnum.fromCode(goodsCommonComponentSearchParam.getIsChineseMedicinePieces()) == BoolCodeEnum.YES) {
            queryBuilder.must(QueryBuilders.termQuery(GoodsBatchEntity.GOODS_TYPE_CODE, GoodsTypeCodeEnum.CHINESE_MEDICINE_PIECES.toCode()));
        }

        // 是否 拆零
        if (StringUtils.isNotBlank(goodsCommonComponentSearchParam.getIsSplitZero())) {
            queryBuilder.must(QueryBuilders.termQuery(GoodsBatchEntity.IS_SPLIT_ZERO, goodsCommonComponentSearchParam.getIsSplitZero()));
        }

        //入库时间
        if (goodsCommonComponentSearchParam.getGtInStockDays() > 0) {
            Date date = DateTimeUtils.addDays(new Date(), (-1 * goodsCommonComponentSearchParam.getGtInStockDays()));
            queryBuilder.must(QueryBuilders.rangeQuery(GoodsBatchEntity.IN_STOCK_TIME).lte(date.getTime()));
        }

        //药品是否有效
        if (BoolCodeEnum.YES.toCode().equals(goodsCommonComponentSearchParam.getIsInEffective())) {
            queryBuilder.must(QueryBuilders.rangeQuery(GoodsBatchEntity.VALID_DATE).gt((new Date()).getTime()));
        }

        //是否允许出库
        if (StringUtils.isNotBlank(goodsCommonComponentSearchParam.getIsAllowOutStock()) && BoolCodeEnum.YES == BoolCodeEnum.fromCode(goodsCommonComponentSearchParam.getIsAllowOutStock())) {
            BoolQueryBuilder subQueryBuilder = QueryBuilders.boolQuery().should(QueryBuilders.termQuery(GoodsBatchEntity.BATCH_STATE, GoodsBatchStateCodeEnum.NORMAL.toCode()))
                    .should(QueryBuilders.termQuery(GoodsBatchEntity.BATCH_STATE, GoodsBatchStateCodeEnum.OVERDUE.toCode()));
            queryBuilder.must(subQueryBuilder);
        }

        SearchRequestBuilder searchRequestBuilder = ESUtils.getClient()
                .prepareSearch(IndexTypeCodeEnum.GOODS_BATCH.toCode())
                .setTypes(IndexTypeCodeEnum.GOODS_BATCH.toCode())
                .setSearchType(SearchType.DEFAULT)
                .setQuery(queryBuilder)
                .addSort(GoodsBatchEntity.GOODS_ID, SortOrder.DESC)
                .addStoredField(GoodsBatchEntity.ID)
                .setFrom(pageable.getPageNumber() * pageable.getPageSize())
                .setSize(pageable.getPageSize())
                .setExplain(false);
        SearchResponse sr = searchRequestBuilder.get();
        List<Map<String, Object>> list = SearchUtils.getSearchResult(sr);
        List<GoodsBatch> result = new ArrayList<>();
        for (Map<String, Object> map : list) {
            Long id = (Long) map.get(GoodsBatchEntity.ID);
            result.add((findOne(id)));
        }
        return new PageImpl<>(result, pageable, sr.getHits().getTotalHits());

    }

    private GoodsBatchCommonComponentPageVo buildCommonGoodsBatchPageVo(GoodsBatch goodsBatch) {
        GoodsBatchCommonComponentPageVo goodsBatchCommonComponentPageVo = CommonUtil.copyProperties(goodsBatch, new GoodsBatchCommonComponentPageVo());

        Goods goods = ServiceManager.goodsService.findOne(goodsBatch.getGoodsId());
        goodsBatchCommonComponentPageVo.setGoodsTypeCode(GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()).toCode());
        goodsBatchCommonComponentPageVo.setGoodsCode(goods.getGoodsCode());
        goodsBatchCommonComponentPageVo.setGoodsNm(goods.getGoodsNm());
        goodsBatchCommonComponentPageVo.setProduceManufacturer(goods.getProduceManufacturer());
        goodsBatchCommonComponentPageVo.setCommonNm(goods.getCommonNm());
        goodsBatchCommonComponentPageVo.setSpec(goods.getSpec());
        goodsBatchCommonComponentPageVo.setUnit(goods.getUnit());
        goodsBatchCommonComponentPageVo.setToxicologyCode(goods.getToxicologyCode());
        goodsBatchCommonComponentPageVo.setStorageCondition(goods.getStorageCondition());
        goodsBatchCommonComponentPageVo.setDisplayPosition(goods.getDisplayPosition());
        goodsBatchCommonComponentPageVo.setInstructionsStr(goods.getInstructionsStr());
        goodsBatchCommonComponentPageVo.setMedicationGuideStr(goods.getMedicationGuideStr());
        goodsBatchCommonComponentPageVo.setIsEnable(goods.getIsEnable());
        goodsBatchCommonComponentPageVo.setPinyin(goods.getPinyin());
        if (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()) == GoodsTypeCodeEnum.DRUG) {
            GoodsDrug goodDrug = ServiceManager.goodsDrugService.findByGoodsId(goods.getId());
            SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodDrug.getDosageForm());
            goodsBatchCommonComponentPageVo.setDosageFormName(sysDictItem.getDictItemNm());
            goodsBatchCommonComponentPageVo.setDosageForm(goodDrug.getDosageForm());
            goodsBatchCommonComponentPageVo.setApprovalNumber(goodDrug.getApprovalNumber());
            //中药饮片
        } else if (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()) == GoodsTypeCodeEnum.CHINESE_MEDICINE_PIECES) {
            GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goods.getId());
            SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
            goodsBatchCommonComponentPageVo.setDosageFormName(sysDictItem.getDictItemNm());
            goodsBatchCommonComponentPageVo.setDosageForm(goodsChineseMedicinePieces.getDosageForm());
            goodsBatchCommonComponentPageVo.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
            goodsBatchCommonComponentPageVo.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
        } else if (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()) == GoodsTypeCodeEnum.OTHER) {
            GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(goods.getId());
            goodsBatchCommonComponentPageVo.setApprovalNumber(goodsOther.getApprovalNumber());
        } else if (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()) == GoodsTypeCodeEnum.COSMETIC) {
            GoodsCosmetic goodsCosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(goods.getId());
            goodsBatchCommonComponentPageVo.setApprovalNumber(goodsCosmetic.getApprovalNumber());
        } else if (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()) == GoodsTypeCodeEnum.DAILY_NECESSITIES) {
            GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(goods.getId());
            goodsBatchCommonComponentPageVo.setApprovalNumber(goodsDailyNecessities.getApprovalNumber());
        }

        StorageSpace storageSpace = ServiceManager.storageSpaceService.findOne(goodsBatch.getStorageSpaceId());
        goodsBatchCommonComponentPageVo.setStorageSpaceNm(storageSpace.getStorageSpaceNm());

        Sku sku = ServiceManager.skuService.findByGoodsId(goods.getId());
        goodsBatchCommonComponentPageVo.setRetailPrice(sku.getRetailPrice());
        goodsBatchCommonComponentPageVo.setMemberPrice(sku.getMemberPrice());
        goodsBatchCommonComponentPageVo.setSplitZeroUnit(sku.getSplitZeroUnit());
        goodsBatchCommonComponentPageVo.setSplitZeroSpec(sku.getSplitZeroSpec());
        goodsBatchCommonComponentPageVo.setSplitZeroRetailPrice(sku.getSplitZeroRetailPrice());
        goodsBatchCommonComponentPageVo.setSplitZeroMemberPrice(sku.getSplitZeroMemberPrice());
        goodsBatchCommonComponentPageVo.setCurrentStock(goodsBatch.getCurrentStock());
        goodsBatchCommonComponentPageVo.setSplitZeroQuantity(sku.getSplitZeroQuantity());

        Supplier supplier = ServiceManager.supplierService.findOne(goodsBatch.getSupplierId());
        goodsBatchCommonComponentPageVo.setSupplierNm(supplier.getSupplierNm());
        return goodsBatchCommonComponentPageVo;
    }

    @Override
    public List<GoodsBatch> findHasStockGoodsByGoodsId(Long goodsId) {
        return getGoodsBatchRepository().findHasStockGoodsByGoodsId(goodsId);
    }

    @Override
    public List<GoodsBatch> findHasStockGoodsByGoodsIdAndBatch(Long goodsId, String batch) {
        return getGoodsBatchRepository().findHasStockGoodsByGoodsIdAndBatch(goodsId, batch);
    }


    @Override
    public List<GoodsBatchSimpleVo> findGoodsBatchByGoodsId(Long goodsId) {
        List<GoodsBatch> batchList = getGoodsBatchRepository().findByGoodsId(goodsId);
        List<GoodsBatchSimpleVo> voList = new ArrayList<>();
        for (GoodsBatch batch : batchList) {
            voList.add(this.buildSimpleVo(batch));
        }
        return voList;
    }

    @Override
    public List<GoodsBatchSimpleVo> findByGoodsIdAndShopId(Long goodsId, Long shopId) {
        List<Object> result = getGoodsBatchRepository().findDistinctBatch(shopId, goodsId);
        List<GoodsBatchSimpleVo> voList = new ArrayList<>();
        for (Object object : result) {
            Object[] arr = (Object[]) object;
            voList.add(this.buildSimpleVo((GoodsBatch) arr[0]));
        }
        return voList;
    }

    private GoodsBatchSimpleVo buildSimpleVo(GoodsBatch batch) {
        GoodsBatchSimpleVo vo = CommonUtil.copyProperties(batch, new GoodsBatchSimpleVo());
        StorageSpace storageSpace = ServiceManager.storageSpaceService.findOne(batch.getStorageSpaceId());
        vo.setStorageSpaceNm(storageSpace.getStorageSpaceNm());
        return vo;
    }

    @Override
    public Page<BatchActualStockPageVo> queryBatchActualStock(Pageable pageable, Long shopId, String searchFields, String batch) {
        Page<GoodsBatch> goodsBatchPage = getGoodsBatchRepository().query(pageable, searchFields, batch, shopId);
        List<BatchActualStockPageVo> result = new ArrayList<>();
        for (GoodsBatch goodsBatch : goodsBatchPage.getContent()) {

            BatchActualStockPageVo stockPageVo = new BatchActualStockPageVo();
            Goods goods = ServiceManager.goodsService.findOne(goodsBatch.getGoodsId());
            StorageSpace storageSpace = ServiceManager.storageSpaceService.findOne(goodsBatch.getStorageSpaceId());
            Sku sku = ServiceManager.skuService.findOne(goodsBatch.getSkuId());
            Supplier supplier = ServiceManager.supplierService.findOne(goodsBatch.getSupplierId());
            stockPageVo.setGoodsCode(goods.getGoodsCode());
            stockPageVo.setGoodsNm(goods.getGoodsNm());
            stockPageVo.setCommonNm(goods.getCommonNm());
            stockPageVo.setSpec(goods.getSpec());
            stockPageVo.setUnit(goods.getUnit());
            stockPageVo.setCurrentStock(goodsBatch.getCurrentStock());
            stockPageVo.setRetailPrice(sku.getRetailPrice());
            stockPageVo.setProduceManufacturer(goods.getProduceManufacturer());
            stockPageVo.setBatch(goodsBatch.getBatch());
            stockPageVo.setProduceDateString(goodsBatch.getProduceDateString());
            stockPageVo.setValidDateString(goodsBatch.getValidDateString());
            stockPageVo.setStorageSpaceNm(storageSpace.getStorageSpaceNm());
            stockPageVo.setSupplierNm(supplier.getSupplierNm());
            stockPageVo.setPurchasePrice(goodsBatch.getPurchasePrice());
            stockPageVo.setBatchState(GoodsBatchStateCodeEnum.fromCode(goodsBatch.getBatchState()).toCode());
            if (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()) == GoodsTypeCodeEnum.DRUG) {
                GoodsDrug goodsDrug = ServiceManager.goodsDrugService.findByGoodsId(goods.getId());
                if (goodsDrug != null) {
                    SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsDrug.getDosageForm());
                    stockPageVo.setDosageForm(sysDictItem.getDictItemNm());
                    stockPageVo.setApprovalNumber(goodsDrug.getApprovalNumber());
                }
            } else if (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()) == GoodsTypeCodeEnum.CHINESE_MEDICINE_PIECES) {
                GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goods.getId());
                if (goodsChineseMedicinePieces != null) {
                    SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
                    stockPageVo.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
                    stockPageVo.setDosageForm(sysDictItem.getDictItemNm());
                    stockPageVo.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
                }
            } else if (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()) == GoodsTypeCodeEnum.OTHER) {
                GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(goods.getId());
                stockPageVo.setApprovalNumber(goodsOther.getApprovalNumber());
            } else if (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()) == GoodsTypeCodeEnum.DAILY_NECESSITIES) {
                GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(goods.getId());
                stockPageVo.setApprovalNumber(goodsDailyNecessities.getApprovalNumber());
            } else if (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()) == GoodsTypeCodeEnum.COSMETIC) {
                GoodsCosmetic goodsCosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(goods.getId());
                stockPageVo.setApprovalNumber(goodsCosmetic.getApprovalNumber());
            }
            result.add(stockPageVo);
        }
        return new PageImpl<>(result, pageable, goodsBatchPage.getTotalElements());
    }


    @Override
    public Integer queryGoodsBatchToQueue() {
        return getGoodsBatchRepository().queryGoodsBatchToQueue();
    }

    @Override
    public List<GoodsBatch> findByGoodsIdAndShopIdAndBatchState(Long goodsId, Long shopId, String batchState) {
        return getGoodsBatchRepository().findByGoodsIdAndShopIdAndBatchState(goodsId, shopId, batchState);
    }

    @Override
    public Long findCurrentStockByGoodsId(Long goodsId) {
        return getGoodsBatchRepository().findCurrentStockByGoodsId(goodsId);
    }

    @Override
    public Long findCurrentStockByGoodsIdAndBatch(Long goodsId, String batch) {
        return getGoodsBatchRepository().findCurrentStockByGoodsIdAndBatch(goodsId, batch);
    }

    @Override
    public void updateSubtractCurrentStock(Long goodsBatchId, Long quantity) {
        GoodsBatch goodsBatch = findOne(goodsBatchId);
        if (goodsBatch == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"批号"}));
        }
        if (goodsBatch.getCurrentStock() < quantity) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.GOODS_BATCH_QUANTITY_NOT_ENOUGH);
            throw new BusinessException(ResGlobalExt.GOODS_BATCH_QUANTITY_NOT_ENOUGH, String.format(message, new Object[]{goodsBatch.getBatch()}));
        }

        goodsBatch.setCurrentStock(goodsBatch.getCurrentStock() - quantity);
        save(goodsBatch);
    }


    @Override
    public Double findTotalGoodsAmountByGoodsId(Long goodsId) {
        List<GoodsBatch> skuList = getGoodsBatchRepository().findHasStockGoodsByGoodsId(goodsId);
        Double totalAmount = 0D;
        for (GoodsBatch goodsBatch : skuList) {
            totalAmount = BigDecimalUtil.add(totalAmount, BigDecimalUtil.multiply(goodsBatch.getCurrentStock(), goodsBatch.getPurchasePrice()));
        }

        return totalAmount;
    }

    @Override
    public GoodsBatch findOneByCustom(Long id) {
        return getGoodsBatchRepository().findOneByCustom(id);
    }

    @Override
    public GoodsBatch fingByGoodsBatch(GoodsBatchSimpleSearchVo goodsBatchSimpleSearchVo) {
        return getGoodsBatchRepository().findByGoodsIdAndBatchAndStorageSpaceIdAndSupplierIdAndPurchasePrice(goodsBatchSimpleSearchVo.getGoodsId(), goodsBatchSimpleSearchVo.getBatch(), goodsBatchSimpleSearchVo.getStorageSpaceId(), goodsBatchSimpleSearchVo.getSupplierId(), goodsBatchSimpleSearchVo.getPurchasePrice());

    }

    @Override
    public GoodsBatch findByGoodsIdAndBatchAndStorageSpaceIdAndSupplierIdAndPurchasePriceAndBatchState(GoodsBatchSimpleSearchVo goodsBatchSimpleSearchVo, String batchState) {
        return getGoodsBatchRepository().findByGoodsIdAndBatchAndStorageSpaceIdAndSupplierIdAndPurchasePriceAndBatchState(goodsBatchSimpleSearchVo.getGoodsId(), goodsBatchSimpleSearchVo.getBatch(), goodsBatchSimpleSearchVo.getStorageSpaceId(), goodsBatchSimpleSearchVo.getSupplierId(), goodsBatchSimpleSearchVo.getPurchasePrice(), batchState);
    }

    @Override
    public void checkGoodsBatchState(Long batchId) {
        //批次
        GoodsBatch goodsBatch = findOne(batchId);
        if (GoodsBatchStateCodeEnum.NORMAL != GoodsBatchStateCodeEnum.fromCode(goodsBatch.getBatchState())) {
            throw new BusinessException(ResGlobal.COMMON_ERROR, "商品批次[" + goodsBatch.getBatch() + "]已停售");
        }

        //商品
        Goods goods = ServiceManager.goodsService.findOne(goodsBatch.getGoodsId());
        if (BoolCodeEnum.YES == BoolCodeEnum.fromCode(goods.getIsDelete())
                || GoodsApproveStateCodeEnum.PASS_APPROVE != GoodsApproveStateCodeEnum.fromCode(goods.getApproveStateCode())
                || BoolCodeEnum.NO == BoolCodeEnum.fromCode(goods.getIsEnable())) {
            throw new BusinessException(ResGlobal.COMMON_ERROR, "商品[" + goods.getGoodsNm() + "]已下架");
        }
    }

    @Override
    public void checkGoodsBatchState(Long goodsId, String batch) {

        //商品
        Goods goods = ServiceManager.goodsService.findOne(goodsId);
        if (BoolCodeEnum.YES == BoolCodeEnum.fromCode(goods.getIsDelete())
                || GoodsApproveStateCodeEnum.PASS_APPROVE != GoodsApproveStateCodeEnum.fromCode(goods.getApproveStateCode())
                || BoolCodeEnum.NO == BoolCodeEnum.fromCode(goods.getIsEnable())) {
            throw new BusinessException(ResGlobal.COMMON_ERROR, "商品[" + goods.getGoodsNm() + "]已下架");
        }

        //批次
        List<GoodsBatch> goodsBatchList = getGoodsBatchRepository().findHasStockGoodsByGoodsIdAndBatch(goodsId, batch);
        if (goodsBatchList.isEmpty()) {
            throw new BusinessException(ResGlobal.COMMON_ERROR, "商品批次[" + batch + "]库存不足");
        }

    }

    @Override
    @Transactional
    public void updateOverdueBatch() {
        getGoodsBatchRepository().updateOverdueBatch();
    }

    @Override
    @Transactional
    public void deleteBatch(GoodsBatch goodsBatch) {
        goodsBatch.setBatchState(GoodsBatchStateCodeEnum.DELETE.toCode());
        this.save(goodsBatch);
    }
}