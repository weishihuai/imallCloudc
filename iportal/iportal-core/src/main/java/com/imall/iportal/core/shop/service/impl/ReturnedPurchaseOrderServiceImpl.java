package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.BigDecimalUtil;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.base.util.GeneratePurchaseNumUtil;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.commons.dicts.OutInStockLogSourceTypeCodeEnum;
import com.imall.commons.dicts.OutInStockTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysDictItem;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.shop.entity.*;
import com.imall.iportal.core.shop.repository.ReturnedPurchaseOrderRepository;
import com.imall.iportal.core.shop.service.ReturnedPurchaseOrderService;
import com.imall.iportal.core.shop.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class ReturnedPurchaseOrderServiceImpl extends AbstractBaseService<ReturnedPurchaseOrder, Long> implements ReturnedPurchaseOrderService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private ReturnedPurchaseOrderRepository getReturnedPurchaseOrderRepository() {
        return (ReturnedPurchaseOrderRepository) baseRepository;
    }

    @Override
    public ReturnedPurchasePrintTemplateVo getReturnedPurchasePrintTemplateVoByIdAndShopId(Long id, Long shopId) {
        ReturnedPurchaseOrder returnedPurchaseOrder = getReturnedPurchaseOrderRepository().findByIdAndShopId(id, shopId);
        ReturnedPurchasePrintTemplateVo templateVo = new ReturnedPurchasePrintTemplateVo();
        if (returnedPurchaseOrder == null) {
            return templateVo;
        }
        //退货单信息
        CommonUtil.copyProperties(returnedPurchaseOrder, templateVo);
        templateVo.setDayOfWeek(DateTimeUtils.getDayOfWeekStr(returnedPurchaseOrder.getCreateDate()));
        //采购商信息
        Supplier supplier = ServiceManager.supplierService.findOne(returnedPurchaseOrder.getSupplierId());
        templateVo.setSupplierNm(supplier.getSupplierNm());
        //审核人信息
        SysUser sysUser = ServiceManager.sysUserService.findOne(returnedPurchaseOrder.getApproveManId());
        templateVo.setApproveMan(sysUser.getRealName());
        //退货订单项
        List<ReturnedPurchaseOrderItem> orderItemList = ServiceManager.returnedPurchaseOrderItemService.findByReturnedPurchaseOrderId(id);
        List<ReturnedPurchasePrintTemplateVo.ReturnedPurchaseOrderItemPrintVo> itemPrintVos = new ArrayList<>();
        for (ReturnedPurchaseOrderItem orderItem : orderItemList) {
            itemPrintVos.add(this.buildPrintVo(orderItem));
        }
        templateVo.setItemPrintVos(itemPrintVos);
        return templateVo;
    }

    @Override
    public Page<ReturnedPurchaseOrderPageVo> query(Pageable pageable, ReturnedPurchaseOrderSearchParams params) {
        Page<ReturnedPurchaseOrder> orderPage = getReturnedPurchaseOrderRepository().query(pageable, params);
        List<ReturnedPurchaseOrderPageVo> voList = new ArrayList<>();
        for (ReturnedPurchaseOrder order : orderPage.getContent()) {
            ReturnedPurchaseOrderPageVo vo = CommonUtil.copyProperties(order, new ReturnedPurchaseOrderPageVo());
            //供应商信息
            Supplier supplier = ServiceManager.supplierService.findOne(order.getSupplierId());
            vo.setSupplierNm(supplier.getSupplierNm());
            //审核人
            SysUser sysUser = ServiceManager.sysUserService.findOne(order.getApproveManId());
            vo.setApproveMan(sysUser.getRealName());
            voList.add(vo);
        }
        return new PageImpl<ReturnedPurchaseOrderPageVo>(voList, pageable, orderPage.getTotalElements());
    }

    @Override
    public ReturnedPurchaseOrderVo findByIdAndShopId(Long id, Long shopId) {
        ReturnedPurchaseOrder returnedPurchaseOrder = getReturnedPurchaseOrderRepository().findByIdAndShopId(id, shopId);
        if (returnedPurchaseOrder == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobal.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobal.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"退货单"}));
        }
        ReturnedPurchaseOrderVo orderVo = CommonUtil.copyProperties(returnedPurchaseOrder, new ReturnedPurchaseOrderVo());
        Supplier supplier = ServiceManager.supplierService.findOne(returnedPurchaseOrder.getSupplierId());
        orderVo.setSupplierNm(supplier.getSupplierNm());
        orderVo.setSupplierCode(supplier.getSupplierCode());
        SysUser sysUser = ServiceManager.sysUserService.findOne(returnedPurchaseOrder.getApproveManId());
        orderVo.setApproveMan(sysUser.getRealName());
        //退货项
        List<ReturnedPurchaseOrderItem> itemList = ServiceManager.returnedPurchaseOrderItemService.findByReturnedPurchaseOrderId(id);
        List<ReturnedPurchaseOrderItemVo> itemVoList = new ArrayList<>();
        for (ReturnedPurchaseOrderItem item : itemList) {
            ReturnedPurchaseOrderItemVo itemVo = CommonUtil.copyProperties(item, new ReturnedPurchaseOrderItemVo());
            PurchaseOrderGoodsVo goodsVo = ServiceManager.goodsService.findPurchaseOrderGoodsVoById(item.getGoodsId());
            CommonUtil.copyProperties(goodsVo, itemVo);
            itemVoList.add(itemVo);
        }
        orderVo.setItemList(itemVoList);
        return orderVo;
    }

    @Override
    @Transactional
    public Long saveReturnedPurchaseOrder(ReturnedPurchaseOrderSaveVo saveVo) {
        ReturnedPurchaseOrder order = CommonUtil.copyProperties(saveVo, new ReturnedPurchaseOrder());
        order.setIsPayed(BoolCodeEnum.NO.toCode());
        Long shopId = saveVo.getShopId();
        //生成退货单号
        Date now = new Date();
        Long count = getReturnedPurchaseOrderRepository().countByCreateDate(DateTimeUtils.getDayStartTime(now), DateTimeUtils.getDayEndTime(now), shopId);
        order.setReturnedPurchaseOrderNum(GeneratePurchaseNumUtil.generateNum(GeneratePurchaseNumUtil.RETURNED_PURCHASE_ORDER_PREFIX, shopId, count + 1));
        order.setReturnedPurchaseTotalAmount(0D);
        Long returnedPurchaseOrderId = this.save(order).getId();
        Long supplierId = saveVo.getSupplierId();
        Double returnedPurchaseTotalAmount = 0D;
        for (ReturnedPurchaseOrderItemSaveVo itemSaveVo : saveVo.getItemList()) {
            PurchaseAcceptanceRecordItem acceptanceRecordItem = ServiceManager.purchaseAcceptanceRecordItemService.findOne(itemSaveVo.getPurchaseAcceptanceRecordItemId());
            if (acceptanceRecordItem == null){
                String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobal.COMMON_OBJECT_NO_FOUND);
                throw new BusinessException(ResGlobal.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"采购验收记录项"}));
            }

            GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(acceptanceRecordItem.getGoodsBatchId());
            if (goodsBatch.getCurrentStock() < itemSaveVo.getReturnedPurchaseQuantity()){
                throw new BusinessException(ResGlobal.COMMON_ERROR,"退货数量不能大于可退数量");
            }
            ReturnedPurchaseOrderItem item = new ReturnedPurchaseOrderItem();
            item.setReturnedPurchaseQuantity(itemSaveVo.getReturnedPurchaseQuantity());
            item.setPurchaseAcceptanceRecordItemId(itemSaveVo.getPurchaseAcceptanceRecordItemId());
            item.setReturnedPurchaseOrderId(returnedPurchaseOrderId);
            item.setShopId(shopId);
            item.setSupplierId(supplierId);
            Double amount = BigDecimalUtil.multiply(itemSaveVo.getReturnedPurchaseQuantity(), acceptanceRecordItem.getPurchaseUnitPrice());
            returnedPurchaseTotalAmount = BigDecimalUtil.add(returnedPurchaseTotalAmount, amount);
            item.setAmount(amount);
            item.setGoodsBatchId(acceptanceRecordItem.getGoodsBatchId());
            item.setGoodsBatch(acceptanceRecordItem.getGoodsBatch());
            item.setProductionDate(acceptanceRecordItem.getProductionDate());
            item.setValidity(acceptanceRecordItem.getValidity());
            item.setPurchaseUnitPrice(acceptanceRecordItem.getPurchaseUnitPrice());
            item.setGoodsId(acceptanceRecordItem.getGoodsId());
            item.setReturnableQuantity(acceptanceRecordItem.getReturnableQuantity());
            ServiceManager.returnedPurchaseOrderItemService.save(item);

            //保存出入库日志
            saveOutInStockLog(order, item);

            //更新批次SKU库存
            Long remainStockQuantity = goodsBatch.getCurrentStock() - item.getReturnedPurchaseQuantity();
            goodsBatch.setCurrentStock(remainStockQuantity);
            ServiceManager.goodsBatchService.save(goodsBatch);

            //更新可退数量
            acceptanceRecordItem.setReturnableQuantity(remainStockQuantity);
            ServiceManager.purchaseAcceptanceRecordItemService.save(acceptanceRecordItem);
        }
        order.setReturnedPurchaseTotalAmount(returnedPurchaseTotalAmount);
        this.save(order);
        return returnedPurchaseOrderId;
    }

    private void saveOutInStockLog(ReturnedPurchaseOrder order, ReturnedPurchaseOrderItem item) {
        OutInStockLog log = new OutInStockLog();
        GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(item.getGoodsBatchId());
        log.setShopId(item.getShopId());
        log.setGoodsId(item.getGoodsId());
        log.setSkuId(goodsBatch.getSkuId());
        log.setTypeCode(OutInStockTypeCodeEnum.OUT_STOCK.toCode());
        log.setGoodsBatchId(item.getGoodsBatchId());
        log.setStorageSpaceId(goodsBatch.getStorageSpaceId());
        log.setQuantity(0 - item.getReturnedPurchaseQuantity());
        log.setAmount(0 - item.getAmount());
        log.setClearingPrevQuantity(goodsBatch.getCurrentStock());
        log.setClearingPrevAmount(BigDecimalUtil.multiply(goodsBatch.getCurrentStock(), goodsBatch.getPurchasePrice()));
        log.setLogSourceTypeCode(OutInStockLogSourceTypeCodeEnum.RETURNED_PURCHASE.toCode());
        log.setLogSourceObjectId(item.getReturnedPurchaseOrderId());
        log.setObjectOrderNum(order.getReturnedPurchaseOrderNum());
        ServiceManager.outInStockLogService.save(log);
    }

    private ReturnedPurchasePrintTemplateVo.ReturnedPurchaseOrderItemPrintVo buildPrintVo(ReturnedPurchaseOrderItem orderItem) {
        ReturnedPurchasePrintTemplateVo.ReturnedPurchaseOrderItemPrintVo printVo = CommonUtil.copyProperties(orderItem, new ReturnedPurchasePrintTemplateVo.ReturnedPurchaseOrderItemPrintVo());
        Long goodsId = orderItem.getGoodsId();
        Goods goods = ServiceManager.goodsService.findOne(goodsId);
        printVo.setGoodsCode(goods.getGoodsCode());
        printVo.setGoodsNm(goods.getGoodsNm());
        printVo.setSpec(goods.getSpec());
        printVo.setUnit(goods.getUnit());
        printVo.setProduceManufacturer(goods.getProduceManufacturer());
        String formCode = ServiceManager.goodsService.getDosageFormByGoodsIdAndGoodsTypeCode(goodsId, goods.getGoodsTypeCode());
        if (StringUtils.isNotBlank(formCode)){
            SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(formCode);
            printVo.setDosageForm(sysDictItem == null ? "" : sysDictItem.getDictItemNm());
        }
        return printVo;
    }
}