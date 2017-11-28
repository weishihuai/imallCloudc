package com.imall.iportal.core.shop.service.impl;

import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.BigDecimalUtil;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.commons.dicts.GoodsTypeCodeEnum;
import com.imall.commons.dicts.StockCheckStateCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysDictItem;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.*;
import com.imall.iportal.core.shop.repository.StockCheckRepository;
import com.imall.iportal.core.shop.service.StockCheckService;
import com.imall.iportal.core.shop.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * (服务层实现)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class StockCheckServiceImpl extends AbstractBaseService<StockCheck, Long> implements StockCheckService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private StockCheckRepository getStockCheckRepository() {
        return (StockCheckRepository) baseRepository;
    }

    @Override
    public Page<StockCheckPageVo> query(Pageable pageable, StockCheckSearchParam stockCheckSearchParam) {
        Page<StockCheck> stockCheckPage = getStockCheckRepository().query(pageable, stockCheckSearchParam);
        List<StockCheckPageVo> stockCheckPageVoList = new ArrayList<>();
        for (StockCheck stockCheck : stockCheckPage.getContent()) {
            StockCheckPageVo stockCheckPageVo = buildStockCheckPageVo(stockCheck);
            buildCheckStockState(stockCheck, stockCheckPageVo);
            stockCheckPageVoList.add(stockCheckPageVo);
        }
        return new PageImpl<>(stockCheckPageVoList, new PageRequest(stockCheckPage.getNumber(), stockCheckPage.getSize()), stockCheckPage.getTotalElements());
    }

    /**
     * 组装盘点状态
     *
     * @param stockCheck       库存盘点对象
     * @param stockCheckPageVo
     */
    private void buildCheckStockState(StockCheck stockCheck, StockCheckPageVo stockCheckPageVo) {
        List<StockCheck> stockCheckList = getStockCheckRepository().listByCheckOrderNum(stockCheck.getCheckOrderNum(), stockCheck.getShopId());
        List<String> checkStateList = new ArrayList<>();
        for (StockCheck stockCheck1 : stockCheckList) {
            checkStateList.add(stockCheck1.getCheckedStateCode());
        }
        if (checkStateList.contains(StockCheckStateCodeEnum.UNCHECKED.toCode())) {
            stockCheckPageVo.setCheckedStateCode(StockCheckStateCodeEnum.UNCHECKED.toCode());
        } else if (checkStateList.contains(StockCheckStateCodeEnum.CANCEL.toCode())) {
            stockCheckPageVo.setCheckedStateCode(StockCheckStateCodeEnum.CANCEL.toCode());
        } else if (checkStateList.contains(StockCheckStateCodeEnum.UN_POST.toCode())) {
            stockCheckPageVo.setCheckedStateCode(StockCheckStateCodeEnum.UN_POST.toCode());
        } else {
            stockCheckPageVo.setCheckedStateCode(StockCheckStateCodeEnum.CHECKED.toCode());
        }
    }

    @Override
    public Page<StockPostingPageVo> queryStockPosting(Pageable pageable, StockPostingSearchParam stockPostingSearchParam) {
        Page<StockCheck> stockPostingPage = getStockCheckRepository().queryStockPosting(pageable, stockPostingSearchParam);
        List<StockPostingPageVo> stockPostingPageVoList = new ArrayList<>();
        for (StockCheck stockCheck : stockPostingPage.getContent()) {
            stockPostingPageVoList.add(buildStockPostingPageVo(stockCheck));
        }
        return new PageImpl<>(stockPostingPageVoList, new PageRequest(stockPostingPage.getNumber(), stockPostingPage.getSize()), stockPostingPage.getTotalElements());
    }

    /**
     * 构建盘点过账对象
     *
     * @param stockCheck
     * @return
     */
    private StockPostingPageVo buildStockPostingPageVo(StockCheck stockCheck) {
        StockPostingPageVo stockPostingPageVo = CommonUtil.copyProperties(stockCheck, new StockPostingPageVo());
        Goods goods = ServiceManager.goodsService.findOne(stockCheck.getGoodsId());
        stockPostingPageVo.setGoodsCode(goods.getGoodsCode());
        stockPostingPageVo.setGoodsNm(goods.getGoodsNm());
        stockPostingPageVo.setGoodsCommonNm(goods.getCommonNm());
        stockPostingPageVo.setSpec(goods.getSpec());
        stockPostingPageVo.setUnit(goods.getUnit());
        stockPostingPageVo.setProduceManufacturer(goods.getProduceManufacturer());
        switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())) {
            case CHINESE_MEDICINE_PIECES:       //中药饮片
                GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(stockCheck.getGoodsId());
                if (goodsChineseMedicinePieces == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"中药饮片"}));
                }
                stockPostingPageVo.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
                SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
                stockPostingPageVo.setDosageForm(sysDictItem.getDictItemNm());
                stockPostingPageVo.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
                break;
            case DRUG:        //药品
                GoodsDrug goodDrug = ServiceManager.goodsDrugService.findByGoodsId(goods.getId());
                if (goodDrug == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"药品"}));
                }
                stockPostingPageVo.setApprovalNumber(goodDrug.getApprovalNumber());
                SysDictItem sysDictItem2 = ServiceManager.sysDictItemService.findByDictItemCode(goodDrug.getDosageForm());
                stockPostingPageVo.setDosageForm(sysDictItem2.getDictItemNm());
                break;
            case OTHER:     //其他
                GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(goods.getId());
                if (goodsOther == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"其他药品"}));
                }
                stockPostingPageVo.setApprovalNumber(goodsOther.getApprovalNumber());
                break;
            case DAILY_NECESSITIES:     //日用品
                GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(goods.getId());
                if (goodsDailyNecessities == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"日用品"}));
                }
                stockPostingPageVo.setApprovalNumber(goodsDailyNecessities.getApprovalNumber());
                break;
            case COSMETIC:      //化妆品
                GoodsCosmetic goodsCosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(goods.getId());
                if (goodsCosmetic == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"化妆品"}));
                }
                stockPostingPageVo.setApprovalNumber(goodsCosmetic.getApprovalNumber());
                break;
        }
        GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(stockCheck.getGoodsBatchId());
        stockPostingPageVo.setBatch(goodsBatch.getBatch());
        stockPostingPageVo.setProduceDateString(goodsBatch.getProduceDateString());
        stockPostingPageVo.setValidDateString(goodsBatch.getValidDateString());
        stockPostingPageVo.setCurrentStock(goodsBatch.getCurrentStock());
        StorageSpace storageSpace = ServiceManager.storageSpaceService.findOne(goodsBatch.getStorageSpaceId());
        stockPostingPageVo.setStorageSpaceNm(storageSpace.getStorageSpaceNm());
        stockPostingPageVo.setStockPrice(BigDecimalUtil.multiply(goodsBatch.getCurrentStock(), goodsBatch.getPurchasePrice()));
        return stockPostingPageVo;
    }

    @Override
    public StockCheckDetailVo findByCheckOrderNum(String checkOrderNum, Long shopId) {
        List<StockCheck> stockCheckList = getStockCheckRepository().listByCheckOrderNum(checkOrderNum, shopId);
        StockCheckDetailVo stockCheckDetailVo = new StockCheckDetailVo();
        //操作人姓名
        stockCheckDetailVo.setOperatorName(ServiceManager.sysUserService.findOne(stockCheckList.get(0).getOperatorId()).getRealName());
        stockCheckDetailVo.setOperationTimeString(stockCheckList.get(0).getOperationTimeString());
        List<String> checkStateList = new ArrayList<>();
        for (StockCheck stockCheck1 : stockCheckList) {
            checkStateList.add(stockCheck1.getCheckedStateCode());
        }
        if (checkStateList.contains(StockCheckStateCodeEnum.UNCHECKED.toCode())) {
            stockCheckDetailVo.setCheckedStateCode(StockCheckStateCodeEnum.UNCHECKED.toCode());
        } else if (checkStateList.contains(StockCheckStateCodeEnum.CANCEL.toCode())) {
            stockCheckDetailVo.setCheckedStateCode(StockCheckStateCodeEnum.CANCEL.toCode());
        } else if (checkStateList.contains(StockCheckStateCodeEnum.UN_POST.toCode())) {
            stockCheckDetailVo.setCheckedStateCode(StockCheckStateCodeEnum.UN_POST.toCode());
        } else {
            stockCheckDetailVo.setCheckedStateCode(StockCheckStateCodeEnum.CHECKED.toCode());
        }
        List<StockCheckGoodsVo> stockCheckGoodsVoList = new ArrayList<>();
        for (StockCheck stockCheck : stockCheckList) {
            stockCheckGoodsVoList.add(buildStockCheckGoodsVo(stockCheck));
        }
        stockCheckDetailVo.setStockCheckGoodsVoList(stockCheckGoodsVoList);
        return stockCheckDetailVo;
    }

    @Override
    @Transactional
    public void updateCheckedStateByCheckOrderNum(String checkOrderNum, Long shopId) {
        getStockCheckRepository().updateCheckedStateAndOperationTimeByCheckOrderNum(StockCheckStateCodeEnum.CANCEL.toCode(), new Date(), checkOrderNum, shopId);
    }

    @Override
    @Transactional
    public void saveStockCheck(Long shopId, Long operatorId, StockCheckSaveVo stockCheckSaveVo) throws ParseException {
        String checkOrderNm = DateSerialGenerator.genSerialCode(DateSerialGenerator.STOCK_CHECK_PREFIX);
        for (StockCheckGoodsSaveVo stockCheckGoodsSaveVo : stockCheckSaveVo.getStockCheckGoodsSaveVoList()) {
            StockCheck stockCheck = new StockCheck();
            stockCheck.setOperatorId(operatorId);
            stockCheck.setShopId(shopId);
            stockCheck.setGoodsId(stockCheckGoodsSaveVo.getGoodsId());
            stockCheck.setSkuId(stockCheckGoodsSaveVo.getSkuId());
            stockCheck.setGoodsBatchId(stockCheckGoodsSaveVo.getGoodsBatchId());
            Goods goods = ServiceManager.goodsService.findOne(stockCheckGoodsSaveVo.getGoodsId());
            stockCheck.setGoodsCode(goods.getGoodsCode());
            stockCheck.setGoodsNm(goods.getGoodsNm());
            stockCheck.setGoodsCommonNm(goods.getCommonNm());
            stockCheck.setPinyin(goods.getPinyin());
            GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(stockCheckGoodsSaveVo.getGoodsBatchId());
            if (goodsBatch == null) {
                String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"批号"}));
            }
            stockCheck.setCurrentStock(goodsBatch.getCurrentStock());
            stockCheck.setCheckOrderNum(checkOrderNm);
            stockCheck.setCheckedStateCode(StockCheckStateCodeEnum.UNCHECKED.toCode());
            stockCheck.setIsPosting(BoolCodeEnum.NO.toCode());
            stockCheck.setOperationTime(new Date());
            this.save(stockCheck);
        }
    }

    @Override
    @Transactional
    public void updateStockCheckRealCheckQuantity(StockCheckCheckedVo stockCheckCheckedVo, Long shopId) throws ParseException {
        List<String> checkStateList = new ArrayList<>();
        for (StockCheckRealCheckQuantityUpdateVo stockCheckRealCheckQuantityUpdateVo : stockCheckCheckedVo.getStockCheckRealCheckQuantityUpdateVoList()) {
            StockCheck stockCheck = ServiceManager.stockCheckService.findOne(stockCheckRealCheckQuantityUpdateVo.getId());
            if (stockCheck == null || !shopId.equals(stockCheck.getShopId())) {
                String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"库存盘点"}));
            }
            //库存有差异 则需要进行过账处理
            checkStateList.add(stockCheckRealCheckQuantityUpdateVo.getRealCheckQuantity().equals(stockCheck.getCurrentStock()) ? StockCheckStateCodeEnum.CHECKED.toCode() : StockCheckStateCodeEnum.UN_POST.toCode());
            stockCheck.setRealCheckQuantity(stockCheckRealCheckQuantityUpdateVo.getRealCheckQuantity());
            GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(stockCheck.getGoodsBatchId());
            stockCheck.setRealCheckAmount(BigDecimalUtil.multiply(goodsBatch.getPurchasePrice(), stockCheckRealCheckQuantityUpdateVo.getRealCheckQuantity()));
            //损益数量
            Long lossQuantity = stockCheckRealCheckQuantityUpdateVo.getRealCheckQuantity() - stockCheck.getCurrentStock();
            stockCheck.setLossQuantity(lossQuantity);
            stockCheck.setLossAmount(BigDecimalUtil.multiply(goodsBatch.getPurchasePrice(), lossQuantity));
            this.update(stockCheck);
        }

        for (StockCheckRealCheckQuantityUpdateVo stockCheckRealCheckQuantityUpdateVo : stockCheckCheckedVo.getStockCheckRealCheckQuantityUpdateVoList()) {
            StockCheck stockCheck = ServiceManager.stockCheckService.findOne(stockCheckRealCheckQuantityUpdateVo.getId());
            if (stockCheck == null || !shopId.equals(stockCheck.getShopId())) {
                String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"库存盘点"}));
            }
            stockCheck.setCheckedStateCode(checkStateList.contains(StockCheckStateCodeEnum.UN_POST.toCode()) ? StockCheckStateCodeEnum.UN_POST.toCode() : StockCheckStateCodeEnum.CHECKED.toCode());
            this.update(stockCheck);
        }
    }

    @Override
    @Transactional
    public void updateStockCheckStateToPosting(Long id, Long shopId) {
        StockCheck stockCheck = this.findOne(id);
        if (stockCheck == null || !stockCheck.getShopId().equals(shopId)) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"库存盘点"}));
        }
        stockCheck.setIsPosting(BoolCodeEnum.YES.toCode());
        stockCheck.setCheckedStateCode(StockCheckStateCodeEnum.CHECKED.toCode());
        //更新GoodsBatch库存
        GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(stockCheck.getGoodsBatchId());
        goodsBatch.setCurrentStock(stockCheck.getRealCheckQuantity());
        ServiceManager.goodsBatchService.update(goodsBatch);
    }

    /**
     * 构建StockCheckGoodsVo对象
     *
     * @param stockCheck
     * @return
     */
    private StockCheckGoodsVo buildStockCheckGoodsVo(StockCheck stockCheck) {
        StockCheckGoodsVo stockCheckGoodsVo = new StockCheckGoodsVo();
        stockCheckGoodsVo.setId(stockCheck.getId());
        Goods goods = ServiceManager.goodsService.findOne(stockCheck.getGoodsId());
        stockCheckGoodsVo.setGoodsCode(goods.getGoodsCode());
        stockCheckGoodsVo.setGoodsNm(goods.getGoodsNm());
        stockCheckGoodsVo.setGoodsCommonNm(goods.getCommonNm());
        stockCheckGoodsVo.setSpec(goods.getSpec());
        stockCheckGoodsVo.setUnit(goods.getUnit());
        stockCheckGoodsVo.setProduceManufacturer(goods.getProduceManufacturer());
        switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())) {
            case CHINESE_MEDICINE_PIECES:       //中药饮片
                GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(stockCheck.getGoodsId());
                if (goodsChineseMedicinePieces == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"中药饮片"}));
                }
                stockCheckGoodsVo.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
                SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
                stockCheckGoodsVo.setDosageForm(sysDictItem.getDictItemNm());
                stockCheckGoodsVo.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
                break;
            case DRUG:        //药品
                GoodsDrug goodDrug = ServiceManager.goodsDrugService.findByGoodsId(goods.getId());
                if (goodDrug == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"药品"}));
                }
                stockCheckGoodsVo.setApprovalNumber(goodDrug.getApprovalNumber());
                SysDictItem sysDictItem2 = ServiceManager.sysDictItemService.findByDictItemCode(goodDrug.getDosageForm());
                stockCheckGoodsVo.setDosageForm(sysDictItem2.getDictItemNm());
                break;
            case DAILY_NECESSITIES:     //日用品
                GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(goods.getId());
                if (goodsDailyNecessities == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"日用品"}));
                }
                stockCheckGoodsVo.setApprovalNumber(goodsDailyNecessities.getApprovalNumber());
                break;
            case COSMETIC:      //化妆品
                GoodsCosmetic goodsCosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(goods.getId());
                if (goodsCosmetic == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"化妆品"}));
                }
                stockCheckGoodsVo.setApprovalNumber(goodsCosmetic.getApprovalNumber());
                break;
            case OTHER:     //其他
                GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(goods.getId());
                if (goodsOther == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"其他药品"}));
                }
                stockCheckGoodsVo.setApprovalNumber(goodsOther.getApprovalNumber());
                break;
        }
        GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(stockCheck.getGoodsBatchId());
        stockCheckGoodsVo.setBatch(goodsBatch.getBatch());
        stockCheckGoodsVo.setProduceDateString(goodsBatch.getProduceDateString());
        stockCheckGoodsVo.setValidDateString(goodsBatch.getValidDateString());
        stockCheckGoodsVo.setCurrentStock(goodsBatch.getCurrentStock());
        StorageSpace storageSpace = ServiceManager.storageSpaceService.findOne(goodsBatch.getStorageSpaceId());
        stockCheckGoodsVo.setStorageSpaceNm(storageSpace.getStorageSpaceNm());
        stockCheckGoodsVo.setRealCheckQuantity(stockCheck.getRealCheckQuantity());
        return stockCheckGoodsVo;
    }

    /**
     * 构建StockCheckPageVo对象
     *
     * @param stockCheck 盘点信息
     * @return
     */
    private StockCheckPageVo buildStockCheckPageVo(StockCheck stockCheck) {
        StockCheckPageVo stockCheckPageVo = CommonUtil.copyProperties(stockCheck, new StockCheckPageVo());
        stockCheckPageVo.setOperationTimeString(stockCheck.getOperationTimeString());
        SysUser sysUser = ServiceManager.sysUserService.findOne(stockCheck.getOperatorId());
        if (sysUser == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"用户"}));
        }
        stockCheckPageVo.setOperatorName(sysUser.getRealName());
        return stockCheckPageVo;
    }

}