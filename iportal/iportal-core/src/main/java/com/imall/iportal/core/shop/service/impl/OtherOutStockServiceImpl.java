package com.imall.iportal.core.shop.service.impl;

import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.BigDecimalUtil;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.GoodsTypeCodeEnum;
import com.imall.commons.dicts.OutInStockLogSourceTypeCodeEnum;
import com.imall.commons.dicts.OutInStockTypeCodeEnum;
import com.imall.commons.dicts.OutStockTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysDictItem;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.*;
import com.imall.iportal.core.shop.repository.OtherOutStockRepository;
import com.imall.iportal.core.shop.service.OtherOutStockService;
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
public class OtherOutStockServiceImpl extends AbstractBaseService<OtherOutStock, Long> implements OtherOutStockService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private OtherOutStockRepository getOtherOutStockRepository() {
        return (OtherOutStockRepository) baseRepository;
    }

    @Override
    public Page<OtherOutStockPageVo> query(Pageable pageable, OtherOutStockSearchParam otherOutStockSearchParam) {
        Page<OtherOutStock> stockCheckPage = getOtherOutStockRepository().query(pageable, otherOutStockSearchParam);
        List<OtherOutStockPageVo> otherOutStockPageVoList = new ArrayList<>();
        for (OtherOutStock otherOutStock : stockCheckPage.getContent()) {
            otherOutStockPageVoList.add(buildOtherOutStockPageVo(otherOutStock));
        }
        return new PageImpl<>(otherOutStockPageVoList, new PageRequest(stockCheckPage.getNumber(),stockCheckPage.getSize()), stockCheckPage.getTotalElements());
    }

    @Override
    public OtherOutStockDetailVo findByOutStockCode(String outStockCode, Long shopId) {
        List<OtherOutStock> otherOutStockList = getOtherOutStockRepository().listByOutStockCode(outStockCode, shopId);
        OtherOutStockDetailVo otherOutStockDetailVo = new OtherOutStockDetailVo();
        OtherOutStock otherStock = otherOutStockList.get(0);
        otherOutStockDetailVo.setOperatorName(ServiceManager.sysUserService.findOne(otherStock.getOperationManId()).getRealName());
        otherOutStockDetailVo.setOutStockTimeTimeString(otherStock.getOutStockTimeString());
        otherOutStockDetailVo.setTypeCode(OutStockTypeCodeEnum.fromCode(otherStock.getTypeCode()).toCode());
        otherOutStockDetailVo.setApproveManName(ServiceManager.sysUserService.findOne(otherStock.getApproveManId()).getRealName());
        otherOutStockDetailVo.setRemark(otherStock.getRemark());
        List<OtherOutStockGoodsVo> otherOutStockGoodsVoList = new ArrayList<>();
        for (OtherOutStock otherOutStock : otherOutStockList) {
            otherOutStockGoodsVoList.add(buildOtherOutStockGoodsVo(otherOutStock));
        }
        otherOutStockDetailVo.setOtherOutStockGoodsVoList(otherOutStockGoodsVoList);
        return otherOutStockDetailVo;
    }

    @Override
    @Transactional
    public void saveOtherOutStock(OtherOutStockSaveVo otherOutStockSaveVo) throws ParseException {
        String outStockCode = DateSerialGenerator.genSerialCode(DateSerialGenerator.OTHER_OUT_STOCK_PREFIX);
        for (OtherOutStockGoodsSaveVo otherOutStockGoodsSaveVo : otherOutStockSaveVo.getOtherOutStockGoodsSaveVoList()) {
            //检查出库数量是否大于当前库存
            GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(otherOutStockGoodsSaveVo.getBatchId());
            if (otherOutStockGoodsSaveVo.getQuantity() > goodsBatch.getCurrentStock()) {
                String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.GOODS_BATCH_QUANTITY_NOT_ENOUGH);
                throw new BusinessException(ResGlobalExt.GOODS_BATCH_QUANTITY_NOT_ENOUGH, String.format(message, new Object[]{goodsBatch.getBatch()}));
            }

            OtherOutStock otherOutStock = new OtherOutStock();
            otherOutStock.setOutStockCode(outStockCode);
            otherOutStock.setApproveManId(otherOutStockSaveVo.getApproveManId());
            if (StringUtils.isNotBlank(otherOutStockSaveVo.getRemark())) {
                otherOutStock.setRemark(otherOutStockSaveVo.getRemark());
            }
            otherOutStock.setOperationManId(otherOutStockSaveVo.getOperationManId());
            otherOutStock.setShopId(otherOutStockSaveVo.getShopId());
            otherOutStock.setTypeCode(OutStockTypeCodeEnum.fromCode(otherOutStockSaveVo.getTypeCode()).toCode());
            if (StringUtils.isNotBlank(otherOutStockSaveVo.getOutStockTimeString())) {
                otherOutStock.setOutStockTime(DateTimeUtils.convertStringToDate(otherOutStockSaveVo.getOutStockTimeString()));
            }
            otherOutStock.setGoodsBatchId(otherOutStockGoodsSaveVo.getBatchId());
            otherOutStock.setStorageSpaceId(otherOutStockGoodsSaveVo.getStorageSpaceId());
            otherOutStock.setQuantity(otherOutStockGoodsSaveVo.getQuantity());
            otherOutStock.setUnitPrice(otherOutStockGoodsSaveVo.getUnitPrice());
            otherOutStock.setGoodsId(otherOutStockGoodsSaveVo.getGoodsId());
            otherOutStock.setSkuId(otherOutStockGoodsSaveVo.getSkuId());
            otherOutStock.setRealCheckAmount(otherOutStockGoodsSaveVo.getUnitPrice() * otherOutStockGoodsSaveVo.getQuantity());
            Long id = this.save(otherOutStock).getId();

            //出库日志
            OutInStockLog outInStockLog = new OutInStockLog();
            outInStockLog.setShopId(otherOutStockSaveVo.getShopId());
            outInStockLog.setGoodsId(otherOutStockGoodsSaveVo.getGoodsId());
            outInStockLog.setGoodsBatchId(otherOutStockGoodsSaveVo.getBatchId());
            outInStockLog.setSkuId(otherOutStockGoodsSaveVo.getSkuId());
            outInStockLog.setTypeCode(OutInStockTypeCodeEnum.OUT_STOCK.toCode());
            outInStockLog.setStorageSpaceId(otherOutStockGoodsSaveVo.getStorageSpaceId());
            outInStockLog.setQuantity(0 - otherOutStockGoodsSaveVo.getQuantity());
            outInStockLog.setAmount(BigDecimalUtil.subtract(0, BigDecimalUtil.multiply(otherOutStockGoodsSaveVo.getQuantity(), goodsBatch.getPurchasePrice())));
            outInStockLog.setClearingPrevQuantity(goodsBatch.getCurrentStock());
            outInStockLog.setClearingPrevAmount(BigDecimalUtil.multiply(goodsBatch.getCurrentStock(), goodsBatch.getPurchasePrice()));
            outInStockLog.setLogSourceObjectId(id);
            outInStockLog.setLogSourceTypeCode(OutInStockLogSourceTypeCodeEnum.OTHER_OUT_STOCK.toCode());
            outInStockLog.setObjectOrderNum(otherOutStock.getOutStockCode());
            ServiceManager.outInStockLogService.save(outInStockLog);

            //更新库存
            GoodsBatch batch = ServiceManager.goodsBatchService.findOne(otherOutStockGoodsSaveVo.getBatchId());
            batch.setCurrentStock(batch.getCurrentStock() - otherOutStockGoodsSaveVo.getQuantity());
            ServiceManager.goodsBatchService.update(batch);
        }
    }

    private OtherOutStockGoodsVo buildOtherOutStockGoodsVo(OtherOutStock otherOutStock) {
        OtherOutStockGoodsVo otherOutStockGoodsVo = new OtherOutStockGoodsVo();
        Goods goods = ServiceManager.goodsService.findOne(otherOutStock.getGoodsId());
        otherOutStockGoodsVo.setGoodsCode(goods.getGoodsCode());
        otherOutStockGoodsVo.setGoodsNm(goods.getGoodsNm());
        otherOutStockGoodsVo.setGoodsCommonNm(goods.getCommonNm());
        otherOutStockGoodsVo.setSpec(goods.getSpec());
        otherOutStockGoodsVo.setUnit(goods.getUnit());
        otherOutStockGoodsVo.setProduceManufacturer(goods.getProduceManufacturer());
        switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())) {
            case CHINESE_MEDICINE_PIECES:       //中药饮片
                GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(otherOutStock.getGoodsId());
                if (goodsChineseMedicinePieces == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"中药饮片"}));
                }
                SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
                otherOutStockGoodsVo.setDosageForm(sysDictItem.getDictItemNm());
                otherOutStockGoodsVo.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
                break;
            case DAILY_NECESSITIES:     //日用品
                GoodsDailyNecessities goodsDailyNecessities = ServiceManager.goodsDailyNecessitiesService.findByGoodsId(goods.getId());
                if (goodsDailyNecessities == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"日用品"}));
                }
                otherOutStockGoodsVo.setApprovalNumber(goodsDailyNecessities.getApprovalNumber());
                break;
            case COSMETIC:      //化妆品
                GoodsCosmetic goodsCosmetic = ServiceManager.goodsCosmeticService.findByGoodsId(goods.getId());
                if (goodsCosmetic == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"化妆品"}));
                }
                otherOutStockGoodsVo.setApprovalNumber(goodsCosmetic.getApprovalNumber());
                break;
            case DRUG:        //药品
                GoodsDrug goodDrug = ServiceManager.goodsDrugService.findByGoodsId(goods.getId());
                if (goodDrug == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"药品"}));
                }
                otherOutStockGoodsVo.setApprovalNumber(goodDrug.getApprovalNumber());
                SysDictItem sysDictItem2 = ServiceManager.sysDictItemService.findByDictItemCode(goodDrug.getDosageForm());
                otherOutStockGoodsVo.setDosageForm(sysDictItem2.getDictItemNm());
                break;
            case OTHER:     //其他
                GoodsOther goodsOther = ServiceManager.goodsOtherService.findByGoodsId(goods.getId());
                if (goodsOther == null) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                    throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"其他药品"}));
                }
                otherOutStockGoodsVo.setApprovalNumber(goodsOther.getApprovalNumber());
                break;
        }
        GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(otherOutStock.getGoodsBatchId());
        otherOutStockGoodsVo.setBatch(goodsBatch.getBatch());
        otherOutStockGoodsVo.setProduceDateString(goodsBatch.getProduceDateString());
        otherOutStockGoodsVo.setValidDateString(goodsBatch.getValidDateString());
        otherOutStockGoodsVo.setCurrentStock(goodsBatch.getCurrentStock());
        StorageSpace storageSpace = ServiceManager.storageSpaceService.findOne(goodsBatch.getStorageSpaceId());
        otherOutStockGoodsVo.setStorageSpaceNm(storageSpace.getStorageSpaceNm());
        otherOutStockGoodsVo.setOutStockPrice(otherOutStock.getQuantity() * otherOutStock.getUnitPrice());
        otherOutStockGoodsVo.setQuantity(otherOutStock.getQuantity());
        otherOutStockGoodsVo.setUnitPrice(otherOutStock.getUnitPrice());
        return otherOutStockGoodsVo;
    }

    /**
     * 构建其他出库列表对象
     *
     * @param otherOutStock
     * @return
     */
    private OtherOutStockPageVo buildOtherOutStockPageVo(OtherOutStock otherOutStock) {
        OtherOutStockPageVo otherOutStockPageVo = CommonUtil.copyProperties(otherOutStock, new OtherOutStockPageVo());
        SysUser sysUser = ServiceManager.sysUserService.findOne(otherOutStock.getOperationManId());
        otherOutStockPageVo.setOperatorName(sysUser.getRealName());
        otherOutStockPageVo.setOutStockTimeString(otherOutStock.getOutStockTimeString());
        return otherOutStockPageVo;
    }
}