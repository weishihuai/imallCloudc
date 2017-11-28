package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.*;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysDictItem;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.*;
import com.imall.iportal.core.shop.repository.OtherInStockRepository;
import com.imall.iportal.core.shop.service.OtherInStockService;
import com.imall.iportal.core.shop.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * (服务层实现)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class OtherInStockServiceImpl extends AbstractBaseService<OtherInStock, Long> implements OtherInStockService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private OtherInStockRepository getOtherInStockRepository() {
        return (OtherInStockRepository) baseRepository;
    }

    @Override
    public Page<OtherInStockPageVo> query(Pageable pageable, OtherInStockSearchParam otherInStockSearchParam) {
        Page<OtherInStock> otherInStockPage = getOtherInStockRepository().query(pageable, otherInStockSearchParam);
        List<OtherInStockPageVo> otherInStockPageVoList = new ArrayList<>();
        for (OtherInStock otherInStock : otherInStockPage.getContent()) {
            otherInStockPageVoList.add(buildOtherInStockPageVo(otherInStock));
        }
        return new PageImpl<>(otherInStockPageVoList, new PageRequest(otherInStockPage.getNumber(),otherInStockPage.getSize()), otherInStockPage.getTotalElements());
    }

    @Override
    public OtherInStockDetailVo findByInStockCode(String inStockCode, Long shopId) {
        List<OtherInStock> otherInStockList = getOtherInStockRepository().listByInStockCode(inStockCode, shopId);
        OtherInStockDetailVo otherInStockDetailVo = new OtherInStockDetailVo();
        OtherInStock otherInStock = otherInStockList.get(0);
        otherInStockDetailVo.setOperatorName(ServiceManager.sysUserService.findOne(otherInStock.getOperationManId()).getRealName());
        otherInStockDetailVo.setInStockTimeString(otherInStock.getInStockTimeString());
        otherInStockDetailVo.setTypeCode(InStockTypeCodeEnum.fromCode(otherInStock.getTypeCode()).toCode());
        otherInStockDetailVo.setApproveManName(ServiceManager.sysUserService.findOne(otherInStock.getApproveManId()).getRealName());
        otherInStockDetailVo.setRemark(otherInStock.getRemark());
        otherInStockDetailVo.setSupplierNm(ServiceManager.supplierService.findOne(otherInStock.getSupplierId()).getSupplierNm());
        List<OtherInStockGoodsVo> otherInStockGoodsVoList = new ArrayList<>();
        for (OtherInStock stock : otherInStockList) {
            otherInStockGoodsVoList.add(buildOtherInStockGoodsVo(stock));
        }
        otherInStockDetailVo.setOtherInStockGoodsVoList(otherInStockGoodsVoList);
        return otherInStockDetailVo;
    }

    @Override
    @Transactional
    public void saveOtherInStock(OtherInStockSaveVo otherInStockSaveVo) throws ParseException {
        if (CollectionUtils.isEmpty(otherInStockSaveVo.getOtherInStockGoodsSaveVoList())) {
            return;
        }
        String inStockCode = DateSerialGenerator.genSerialCode(DateSerialGenerator.OTHER_IN_STOCK_PREFIX);
        for (OtherInStockGoodsSaveVo otherInStockGoodsSaveVo : otherInStockSaveVo.getOtherInStockGoodsSaveVoList()) {
            OtherInStock otherInStock = new OtherInStock();
            otherInStock.setInStockCode(inStockCode);
            otherInStock.setApproveManId(otherInStockSaveVo.getApproveManId());
            if (StringUtils.isNotBlank(otherInStockSaveVo.getRemark())) {
                otherInStock.setRemark(otherInStockSaveVo.getRemark());
            }
            otherInStock.setOperationManId(otherInStockSaveVo.getOperationManId());
            otherInStock.setShopId(otherInStockSaveVo.getShopId());
            otherInStock.setSupplierId(otherInStockSaveVo.getSupplierId());
            otherInStock.setTypeCode(InStockTypeCodeEnum.fromCode(otherInStockSaveVo.getTypeCode()).toCode());
            if (StringUtils.isNotBlank(otherInStockSaveVo.getInStockTimeString())) {
                otherInStock.setInStockTime(DateTimeUtils.convertStringToDate(otherInStockSaveVo.getInStockTimeString()));
            }

            //检查批号并更新库存
            Long goodsBatchId = this.checkGoodsBatch(otherInStockSaveVo,otherInStockGoodsSaveVo, otherInStockGoodsSaveVo.getGoodsBatchId());
            Sku sku = ServiceManager.skuService.findByGoodsId(otherInStockGoodsSaveVo.getGoodsId());
            otherInStock.setGoodsBatchId(goodsBatchId);
            otherInStock.setStorageSpaceId(otherInStockGoodsSaveVo.getStorageSpaceId());
            otherInStock.setQuantity(otherInStockGoodsSaveVo.getQuantity());
            otherInStock.setUnitPrice(otherInStockGoodsSaveVo.getUnitPrice());
            otherInStock.setGoodsId(otherInStockGoodsSaveVo.getGoodsId());
            otherInStock.setSkuId(sku.getId());
            otherInStock.setRealCheckAmount(otherInStockGoodsSaveVo.getUnitPrice() * otherInStockGoodsSaveVo.getQuantity());
            otherInStock.setProduceDate(DateTimeUtils.convertStringToDate(otherInStockGoodsSaveVo.getProductionDateString()));
            otherInStock.setValidDate(DateTimeUtils.convertStringToDate(otherInStockGoodsSaveVo.getValidityString()));
            Long id = this.save(otherInStock).getId();

            //生成入库日志
            OutInStockLog outInStockLog = new OutInStockLog();
            outInStockLog.setShopId(otherInStockSaveVo.getShopId());
            outInStockLog.setGoodsId(otherInStockGoodsSaveVo.getGoodsId());
            outInStockLog.setGoodsBatchId(goodsBatchId);
            outInStockLog.setSkuId(sku.getId());
            outInStockLog.setTypeCode(OutInStockTypeCodeEnum.IN_STOCK.toCode());
            outInStockLog.setStorageSpaceId(otherInStockGoodsSaveVo.getStorageSpaceId());
            outInStockLog.setQuantity(otherInStockGoodsSaveVo.getQuantity());
            outInStockLog.setAmount(otherInStockGoodsSaveVo.getQuantity() * otherInStockGoodsSaveVo.getUnitPrice());
            GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(goodsBatchId);
            outInStockLog.setClearingPrevQuantity(goodsBatch.getCurrentStock());
            outInStockLog.setClearingPrevAmount(goodsBatch.getCurrentStock() * goodsBatch.getPurchasePrice());

            outInStockLog.setLogSourceObjectId(id);
            outInStockLog.setLogSourceTypeCode(OutInStockLogSourceTypeCodeEnum.OTHER_IN_STOCK.toCode());
            outInStockLog.setObjectOrderNum(otherInStock.getInStockCode());
            ServiceManager.outInStockLogService.save(outInStockLog);
        }
    }

    @Override
    @Transactional
    public Long checkGoodsBatch(OtherInStockSaveVo otherInStockSaveVo,OtherInStockGoodsSaveVo otherInStockGoodsSaveVo, Long goodsBatchId) throws ParseException {
        GoodsBatch goodsBatch;
        if (goodsBatchId == null) {
            goodsBatch = buildGoodsBatch(otherInStockSaveVo, otherInStockGoodsSaveVo);
            ServiceManager.goodsBatchService.save(goodsBatch);
        } else {
            GoodsBatchSimpleSearchVo simpleSearchVo = new GoodsBatchSimpleSearchVo();
            simpleSearchVo.setBatch(otherInStockGoodsSaveVo.getGoodsBatch());
            simpleSearchVo.setGoodsId(otherInStockGoodsSaveVo.getGoodsId());
            simpleSearchVo.setPurchasePrice(otherInStockGoodsSaveVo.getUnitPrice());
            simpleSearchVo.setStorageSpaceId(otherInStockGoodsSaveVo.getStorageSpaceId());
            simpleSearchVo.setSupplierId(otherInStockSaveVo.getSupplierId());
            goodsBatch = ServiceManager.goodsBatchService.findByGoodsIdAndBatchAndStorageSpaceIdAndSupplierIdAndPurchasePriceAndBatchState(simpleSearchVo, GoodsBatchStateCodeEnum.NORMAL.toCode());
             if (goodsBatch == null){
                goodsBatch = this.buildGoodsBatch(otherInStockSaveVo, otherInStockGoodsSaveVo);
            } else {
                goodsBatch.setCurrentStock(goodsBatch.getCurrentStock() + otherInStockGoodsSaveVo.getQuantity());
            }
            ServiceManager.goodsBatchService.save(goodsBatch);
        }

        return goodsBatch.getId();
    }

    private GoodsBatch buildGoodsBatch(OtherInStockSaveVo otherInStockSaveVo,OtherInStockGoodsSaveVo otherInStockGoodsSaveVo) throws ParseException {
        GoodsBatch goodsBatch = new GoodsBatch();
        goodsBatch.setBatch(otherInStockGoodsSaveVo.getGoodsBatch());
        goodsBatch.setShopId(otherInStockSaveVo.getShopId());
        goodsBatch.setGoodsId(otherInStockGoodsSaveVo.getGoodsId());
        goodsBatch.setStorageSpaceId(otherInStockGoodsSaveVo.getStorageSpaceId());
        goodsBatch.setProduceDateString(otherInStockGoodsSaveVo.getProductionDateString());
        goodsBatch.setValidDateString(otherInStockGoodsSaveVo.getValidityString());
        Goods goods = ServiceManager.goodsService.findOne(otherInStockGoodsSaveVo.getGoodsId());
        goodsBatch.setGoodsNm(goods.getGoodsNm());
        goodsBatch.setGoodsCommonNm(goods.getCommonNm());
        goodsBatch.setGoodsCode(goods.getGoodsCode());
        goodsBatch.setPinyin(goods.getPinyin());
        goodsBatch.setSkuId(ServiceManager.skuService.findByGoodsId(goods.getId()).getId());
        goodsBatch.setCurrentStock(otherInStockGoodsSaveVo.getQuantity());
        goodsBatch.setPurchasePrice(otherInStockGoodsSaveVo.getUnitPrice());
        goodsBatch.setSupplierId(otherInStockSaveVo.getSupplierId());
        goodsBatch.setBatchState(GoodsBatchStateCodeEnum.NORMAL.toCode());
        return goodsBatch;
    }



    /**
     * 构建其他入库商品信息对象
     *
     * @param stock 其他入库对象
     * @return
     */
    private OtherInStockGoodsVo buildOtherInStockGoodsVo(OtherInStock stock) {
        OtherInStockGoodsVo otherInStockGoodsVo = new OtherInStockGoodsVo();
        Goods goods = ServiceManager.goodsService.findOne(stock.getGoodsId());
        otherInStockGoodsVo.setGoodsCode(goods.getGoodsCode());
        otherInStockGoodsVo.setGoodsNm(goods.getGoodsNm());
        otherInStockGoodsVo.setGoodsCommonNm(goods.getCommonNm());
        otherInStockGoodsVo.setSpec(goods.getSpec());
        otherInStockGoodsVo.setUnit(goods.getUnit());
        otherInStockGoodsVo.setProduceManufacturer(goods.getProduceManufacturer());
        switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())) {
            case CHINESE_MEDICINE_PIECES:       //中药饮片
                GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(stock.getGoodsId());
                if (goodsChineseMedicinePieces == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"中药饮片"}));
                }
                SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
                otherInStockGoodsVo.setDosageForm(sysDictItem.getDictItemNm());
                otherInStockGoodsVo.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
                break;
            case DAILY_NECESSITIES:     //日用品
                GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(goods.getId());
                if (goodsDailyNecessities == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"日用品"}));
                }
                otherInStockGoodsVo.setApprovalNumber(goodsDailyNecessities.getApprovalNumber());
                break;
            case DRUG:        //药品
                GoodsDrug goodDrug = ServiceManager.goodsDrugService.findByGoodsId(goods.getId());
                if (goodDrug == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"药品"}));
                }
                otherInStockGoodsVo.setApprovalNumber(goodDrug.getApprovalNumber());
                SysDictItem sysDictItem2 = ServiceManager.sysDictItemService.findByDictItemCode(goodDrug.getDosageForm());
                otherInStockGoodsVo.setDosageForm(sysDictItem2.getDictItemNm());
                break;
            case COSMETIC:      //化妆品
                GoodsCosmetic goodsCosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(goods.getId());
                if (goodsCosmetic == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"化妆品"}));
                }
                otherInStockGoodsVo.setApprovalNumber(goodsCosmetic.getApprovalNumber());
                break;
            case OTHER:     //其他
                GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(goods.getId());
                if (goodsOther == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"其他药品"}));
                }
                otherInStockGoodsVo.setApprovalNumber(goodsOther.getApprovalNumber());
                break;
        }
        GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(stock.getGoodsBatchId());
        otherInStockGoodsVo.setBatch(goodsBatch.getBatch());
        otherInStockGoodsVo.setProduceDateString(goodsBatch.getProduceDateString());
        otherInStockGoodsVo.setValidDateString(goodsBatch.getValidDateString());
        otherInStockGoodsVo.setCurrentStock(goodsBatch.getCurrentStock());
        StorageSpace storageSpace = ServiceManager.storageSpaceService.findOne(goodsBatch.getStorageSpaceId());
        otherInStockGoodsVo.setStorageSpaceNm(storageSpace.getStorageSpaceNm());
        otherInStockGoodsVo.setInStockPrice(stock.getQuantity() * stock.getUnitPrice());
        otherInStockGoodsVo.setQuantity(stock.getQuantity());
        otherInStockGoodsVo.setUnitPrice(stock.getUnitPrice());
        return otherInStockGoodsVo;
    }

    /**
     * 构建其他入库列表对象
     *
     * @param otherInStock
     * @return
     */
    private OtherInStockPageVo buildOtherInStockPageVo(OtherInStock otherInStock) {
        OtherInStockPageVo otherInStockPageVo = CommonUtil.copyProperties(otherInStock, new OtherInStockPageVo());
        SysUser sysUser = ServiceManager.sysUserService.findOne(otherInStock.getOperationManId());
        otherInStockPageVo.setOperatorName(sysUser.getRealName());
        otherInStockPageVo.setInStockTimeString(otherInStock.getInStockTimeString());
        return otherInStockPageVo;
    }

    @Override
    public List<OtherInStock> queryByGoodsBatchId(Long shopId, Long goodsBatchId) {
        return this.getOtherInStockRepository().queryByGoodsBatchId(shopId, goodsBatchId);
    }
}