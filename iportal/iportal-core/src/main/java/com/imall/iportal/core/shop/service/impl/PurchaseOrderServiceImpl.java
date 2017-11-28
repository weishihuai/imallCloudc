package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.BigDecimalUtil;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.base.util.GeneratePurchaseNumUtil;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.commons.dicts.PurchaseOrderStateCodeEnum;
import com.imall.commons.dicts.PurchaseOrderTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysDictItem;
import com.imall.iportal.core.shop.entity.*;
import com.imall.iportal.core.shop.repository.PurchaseOrderRepository;
import com.imall.iportal.core.shop.service.PurchaseOrderService;
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
public class PurchaseOrderServiceImpl extends AbstractBaseService<PurchaseOrder, Long> implements PurchaseOrderService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private PurchaseOrderRepository getPurchaseOrderRepository() {
        return (PurchaseOrderRepository) baseRepository;
    }

    @Override
    public Page<PurchaseOrderPageVo> query(Pageable pageable, PurchaseOrderSearchParams searchParam) {
        Page<PurchaseOrder> purchaseOrderPage = getPurchaseOrderRepository().query(pageable, searchParam);
        List<PurchaseOrderPageVo> voList = new ArrayList<>();
        for (PurchaseOrder order : purchaseOrderPage.getContent()) {
            PurchaseOrderPageVo vo = CommonUtil.copyProperties(order, new PurchaseOrderPageVo());
            Supplier supplier = ServiceManager.supplierService.findOne(order.getSupplierId());
            vo.setSupplierNm(supplier.getSupplierNm());
            voList.add(vo);
        }
        return new PageImpl<PurchaseOrderPageVo>(voList, new PageRequest(purchaseOrderPage.getNumber(),purchaseOrderPage.getSize()), purchaseOrderPage.getTotalElements());
    }

    @Override
    public PurchaseOrderPrintTemplateVo getPurchaseOrderPrintTemplateVoByIdAndShopId(Long id, Long shopId) {
        PurchaseOrder purchaseOrder = getPurchaseOrderRepository().findByIdAndShopId(id, shopId);
        PurchaseOrderPrintTemplateVo templateVo = new PurchaseOrderPrintTemplateVo();
        if (purchaseOrder == null) {
            return templateVo;
        }
        CommonUtil.copyProperties(purchaseOrder, templateVo);
        templateVo.setDayOfWeek(DateTimeUtils.getDayOfWeekStr(purchaseOrder.getCreateDate()));
        Supplier supplier = ServiceManager.supplierService.findOne(purchaseOrder.getSupplierId());
        templateVo.setSupplierNm(supplier.getSupplierNm());
        List<PurchaseOrderItem> orderItemList = ServiceManager.purchaseOrderItemService.findByPurchaseOrderId(id);
        List<PurchaseOrderPrintTemplateVo.PurchaseOrderItemPrintVo> itemPrintVos = new ArrayList<>();
        for (PurchaseOrderItem orderItem : orderItemList) {
            itemPrintVos.add(this.buildOrderItemPrintVo(orderItem));
        }
        templateVo.setItemPrintVos(itemPrintVos);
        return templateVo;
    }

    @Override
    public PurchaseOrderVo findByIdAndShopId(Long id, Long shopId) {
        PurchaseOrder purchaseOrder = getPurchaseOrderRepository().findByIdAndShopId(id, shopId);
        if (purchaseOrder == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobal.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobal.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"采购订单"}));
        }
        PurchaseOrderVo orderVo = CommonUtil.copyProperties(purchaseOrder, new PurchaseOrderVo());
        Supplier supplier = ServiceManager.supplierService.findOne(purchaseOrder.getSupplierId());
        orderVo.setSupplierNm(supplier.getSupplierNm());
        orderVo.setSupplierCode(supplier.getSupplierCode());
        List<PurchaseOrderItem> orderItemList = ServiceManager.purchaseOrderItemService.findByPurchaseOrderId(id);
        List<PurchaseOrderItemVo> orderItemVoList = new ArrayList<>();
        for (PurchaseOrderItem orderItem : orderItemList) {
            orderItemVoList.add(this.buildOrderItemVo(orderItem));
        }
        orderVo.setOrderItemVoList(orderItemVoList);
        return orderVo;
    }

    @Override
    @Transactional
    public Long updateStatToCancel(Long id, Long shopId) {
        PurchaseOrder purchaseOrder = getPurchaseOrderRepository().findByIdAndShopId(id, shopId);
        if (purchaseOrder == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobal.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobal.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"采购订单"}));
        }
        //只有待收货的订单才能取消
        if (PurchaseOrderStateCodeEnum.WAIT_RECEIVE != PurchaseOrderStateCodeEnum.fromCode(purchaseOrder.getPurchaseOrderState())) {
            String name = PurchaseOrderStateCodeEnum.fromCode(purchaseOrder.getPurchaseOrderState()).toName();
            throw new BusinessException(ResGlobal.COMMON_ERROR, name + "的采购订单不能取消");
        }
        purchaseOrder.setPurchaseOrderState(PurchaseOrderStateCodeEnum.CANCEL.toCode());
        this.save(purchaseOrder);
        return id;
    }

    @Override
    @Transactional
    public Long savePurchaseOrder(PurchaseOrderSaveVo saveVo) {
        PurchaseOrder purchaseOrder = CommonUtil.copyProperties(saveVo, new PurchaseOrder());
        Date now = new Date();
        Long count = getPurchaseOrderRepository().countByCreateDate(DateTimeUtils.getDayStartTime(now), DateTimeUtils.getDayEndTime(now), saveVo.getShopId());
        purchaseOrder.setPurchaseOrderNum(GeneratePurchaseNumUtil.generateNum(GeneratePurchaseNumUtil.PURCHASE_ORDER_PREFIX, saveVo.getShopId(), count + 1));
        purchaseOrder.setPurchaseOrderState(PurchaseOrderStateCodeEnum.WAIT_RECEIVE.toCode());
        purchaseOrder.setPurchaseOrderType(PurchaseOrderTypeCodeEnum.OFF_LINE_ORDER.toCode());
        Long id = this.save(purchaseOrder).getId();
        Long shopId = saveVo.getShopId();
        Long supplierId = saveVo.getSupplierId();
        for (PurchaseOrderItemSaveVo itemSaveVo : saveVo.getItemList()) {
            PurchaseOrderItem orderItem = CommonUtil.copyProperties(itemSaveVo, new PurchaseOrderItem());
            orderItem.setPurchaseOrderId(id);
            orderItem.setIsReceive(BoolCodeEnum.NO.toCode());
            orderItem.setShopId(shopId);
            orderItem.setSupplierId(supplierId);
            ServiceManager.purchaseOrderItemService.save(orderItem);
        }
        return id;
    }

    @Override
    @Transactional
    public Long saveFastReceive(FastReceiveSaveVo saveVo) throws ParseException {
        Date now = new Date();
        Date start = DateTimeUtils.getDayStartTime(now);
        Date end = DateTimeUtils.getDayEndTime(now);
        //保存采购订单
        PurchaseOrder purchaseOrder = this.savePurchaseOrder(saveVo, now, start, end);
        Long id = purchaseOrder.getId();
        //保存收货记录
        Long purchaseReceiveRecordId = this.saveReceiveRecord(saveVo, id, start, end);
        //保存验收记录
        this.saveAcceptanceRecord(saveVo, id, purchaseReceiveRecordId, start, end);
        return id;
    }

    @Override
    public PurchaseOrderVo findPurchaseReceiveInfo(Long id, Long shopId) {
        PurchaseOrder purchaseOrder = getPurchaseOrderRepository().findByIdAndShopId(id, shopId);
        if (purchaseOrder == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobal.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobal.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"采购订单"}));
        }
        PurchaseOrderVo orderVo = CommonUtil.copyProperties(purchaseOrder, new PurchaseOrderVo());
        List<PurchaseOrderItem> orderItemList = ServiceManager.purchaseOrderItemService.findByPurchaseOrderIdAndIsReceive(id, BoolCodeEnum.NO.toCode());
        List<PurchaseOrderItemVo> orderItemVoList = new ArrayList<>();
        for (PurchaseOrderItem orderItem : orderItemList) {
            orderItemVoList.add(this.buildOrderItemVo(orderItem));
        }
        orderVo.setOrderItemVoList(orderItemVoList);
        orderVo.setReceiveTimeString(DateTimeUtils.convertDateToString(new Date()));
        return orderVo;
    }

    @Override
    public PurchaseOrder findPurchaseOrder(Long id, Long shopId) {
        return getPurchaseOrderRepository().findByIdAndShopId(id, shopId);
    }

    //保存采购订单
    private PurchaseOrder savePurchaseOrder(FastReceiveSaveVo saveVo, Date now, Date start, Date end) throws ParseException {
        Long shopId = saveVo.getShopId();
        PurchaseOrder purchaseOrder = CommonUtil.copyProperties(saveVo, new PurchaseOrder());
        Long count = getPurchaseOrderRepository().countByCreateDate(start, end, shopId);
        purchaseOrder.setPurchaseOrderNum(GeneratePurchaseNumUtil.generateNum(GeneratePurchaseNumUtil.PURCHASE_ORDER_PREFIX, saveVo.getShopId(), count + 1));
        purchaseOrder.setPurchaseOrderState(PurchaseOrderStateCodeEnum.CLEAR.toCode());
        purchaseOrder.setPurchaseOrderType(PurchaseOrderTypeCodeEnum.OFF_LINE_ORDER.toCode());
        //预计到货时间：如果输入的收货时间为空，则设置为当前系统时间，反之为收货时间
        if (StringUtils.isBlank(saveVo.getReceiveTimeString())) {
            purchaseOrder.setExpectedArrivalTime(now);
        } else {
            purchaseOrder.setExpectedArrivalTimeString(saveVo.getReceiveTimeString());
        }
        purchaseOrder.setClearingTime(now);
        Long id = this.save(purchaseOrder).getId();
        Long supplierId = saveVo.getSupplierId();
        for (FastReceiveItemSaveVo itemSaveVo : saveVo.getItemList()) {
            PurchaseOrderItem orderItem = CommonUtil.copyProperties(itemSaveVo, new PurchaseOrderItem());
            orderItem.setPurchaseOrderId(id);
            orderItem.setIsReceive(BoolCodeEnum.YES.toCode());
            orderItem.setShopId(shopId);
            orderItem.setSupplierId(supplierId);
            orderItem.setPurchaseQuantity(itemSaveVo.getGoodsArrivalQuantity());
            ServiceManager.purchaseOrderItemService.save(orderItem);
        }
        return purchaseOrder;
    }

    //保存收货记录
    private Long saveReceiveRecord(FastReceiveSaveVo saveVo, Long purchaseOrderId, Date start, Date end) throws ParseException {
        Long shopId = saveVo.getShopId();
        Long supplierId = saveVo.getSupplierId();
        //保存收货记录
        PurchaseReceiveRecord purchaseReceiveRecord = new PurchaseReceiveRecord();
        purchaseReceiveRecord.setShopId(shopId);
        purchaseReceiveRecord.setSupplierId(supplierId);
        purchaseReceiveRecord.setPurchaseOrderId(purchaseOrderId);
        purchaseReceiveRecord.setReceiver(saveVo.getReceiver());
        purchaseReceiveRecord.setReceiveTimeString(saveVo.getReceiveTimeString());
        purchaseReceiveRecord.setRemark(saveVo.getRemark());
        purchaseReceiveRecord.setIsAllAcceptance(BoolCodeEnum.YES.toCode());
        //生成收货编号
        Long count = ServiceManager.purchaseReceiveRecordService.countByCreateDate(start, end, shopId);
        purchaseReceiveRecord.setReceiveOrderNum(GeneratePurchaseNumUtil.generateNum("CGSH", shopId, count + 1));
        Long purchaseReceiveRecordId = ServiceManager.purchaseReceiveRecordService.save(purchaseReceiveRecord).getId();
        //保存收货记录项
        for (FastReceiveItemSaveVo itemSaveVo : saveVo.getItemList()) {
            Long goodsId = itemSaveVo.getGoodsId();
            PurchaseReceiveRecordItem recordItem = new PurchaseReceiveRecordItem();
            recordItem.setPurchaseReceiveRecordId(purchaseReceiveRecordId);
            recordItem.setPurchaseOrderId(purchaseOrderId);
            recordItem.setShopId(shopId);
            recordItem.setSupplierId(supplierId);
            recordItem.setGoodsId(goodsId);
            recordItem.setPurchaseUnitPrice(itemSaveVo.getPurchaseUnitPrice());
            //购买数量即为到货数量
            recordItem.setPurchaseQuantity(itemSaveVo.getGoodsArrivalQuantity());
            recordItem.setReceiveQuantity(itemSaveVo.getGoodsArrivalQuantity());
            recordItem.setIsAcceptance(BoolCodeEnum.YES.toCode());
            recordItem.setRejectionReason(itemSaveVo.getRejectionReason());
            ServiceManager.purchaseReceiveRecordItemService.save(recordItem);
        }
        return purchaseReceiveRecordId;
    }

    //保存验收记录
    private void saveAcceptanceRecord(FastReceiveSaveVo saveVo, Long purchaseOrderId, Long purchaseReceiveRecordId, Date start, Date end) {
        Long shopId = saveVo.getShopId();
        //保存收货记录
        Long supplierId = saveVo.getSupplierId();
        PurchaseAcceptanceRecord acceptanceRecord = CommonUtil.copyProperties(saveVo, new PurchaseAcceptanceRecord());
        acceptanceRecord.setPurchaseOrderId(purchaseOrderId);
        acceptanceRecord.setPurchaseReceiveRecordId(purchaseReceiveRecordId);
        acceptanceRecord.setIsPayed(BoolCodeEnum.NO.toCode());
        acceptanceRecord.setSupplierId(supplierId);
        //生成验收单号
        Long count = ServiceManager.purchaseAcceptanceRecordService.countByCreateDate(start, end, shopId);
        acceptanceRecord.setAcceptanceOrderNum(GeneratePurchaseNumUtil.generateNum(GeneratePurchaseNumUtil.ACCEPTANCE_ORDER_PREFIX, shopId, count + 1));
        acceptanceRecord.setAcceptanceTotalAmount(saveVo.getPurchaseTotalAmount());//快速收货：验收总金额==采购总金额
        Long purchaseAcceptanceRecordId = ServiceManager.purchaseAcceptanceRecordService.save(acceptanceRecord).getId();
        //保存验收项
        for (FastReceiveItemSaveVo itemSaveVo : saveVo.getItemList()) {
            this.saveAcceptanceRecordItem(itemSaveVo, purchaseAcceptanceRecordId, shopId, supplierId);
        }
    }

    private void saveAcceptanceRecordItem(FastReceiveItemSaveVo itemSaveVo, Long purchaseAcceptanceRecordId, Long shopId, Long supplierId) {
        Long goodsId = itemSaveVo.getGoodsId();
        //保存验收项
        PurchaseAcceptanceRecordItem acceptanceRecordItem = CommonUtil.copyProperties(itemSaveVo, new PurchaseAcceptanceRecordItem());
        Double purchaseUnitPrice = itemSaveVo.getPurchaseUnitPrice();//采购单价
        Long goodsArrivalQuantity = itemSaveVo.getGoodsArrivalQuantity();//到货数量
        if (goodsArrivalQuantity < itemSaveVo.getQualifiedQuantity()){
            throw new BusinessException(ResGlobal.COMMON_ERROR, "合格数量不能大于到货数量");
        }
        if (goodsArrivalQuantity < itemSaveVo.getSampleQuantity()){
            throw new BusinessException(ResGlobal.COMMON_ERROR, "抽样数量不能大于到货数量");
        }
        acceptanceRecordItem.setInStorageQuantity(goodsArrivalQuantity);//入库数量 = 到货数量
        acceptanceRecordItem.setRejectionQuantity(goodsArrivalQuantity - itemSaveVo.getQualifiedQuantity());//拒收数量 = 到货数量 - 合格数量
        acceptanceRecordItem.setAcceptanceQualifiedAmount(BigDecimalUtil.multiply(itemSaveVo.getQualifiedQuantity(), purchaseUnitPrice));//验收合格金额 = 验收合格数量*采购单价
        acceptanceRecordItem.setTotalAmount(BigDecimalUtil.multiply(goodsArrivalQuantity, purchaseUnitPrice));//总金额
        acceptanceRecordItem.setShopId(shopId);
        acceptanceRecordItem.setSupplierId(supplierId);
        acceptanceRecordItem.setGoodsId(goodsId);
        acceptanceRecordItem.setPurchaseAcceptanceRecordId(purchaseAcceptanceRecordId);
        acceptanceRecordItem.setPurchaseUnitPrice(purchaseUnitPrice);
        acceptanceRecordItem.setReturnableQuantity(itemSaveVo.getQualifiedQuantity());//可退数量=验收合格数量
        //商品信息
        Goods goods = ServiceManager.goodsService.findOne(goodsId);
        acceptanceRecordItem.setGoodsCode(goods.getGoodsCode());
        acceptanceRecordItem.setGoodsNm(goods.getGoodsNm());
        acceptanceRecordItem.setProduceManufacturer(goods.getProduceManufacturer());
        //检查批号
        Long goodsBatchId = ServiceManager.purchaseAcceptanceRecordService.checkGoodsBatch(acceptanceRecordItem, itemSaveVo.getGoodsBatchId());
        acceptanceRecordItem.setGoodsBatchId(goodsBatchId);
        ServiceManager.purchaseAcceptanceRecordItemService.save(acceptanceRecordItem);
    }

    private PurchaseOrderItemVo buildOrderItemVo(PurchaseOrderItem orderItem) {
        PurchaseOrderItemVo orderItemVo = CommonUtil.copyProperties(orderItem, new PurchaseOrderItemVo());
        Long goodsId = orderItem.getGoodsId();
        PurchaseOrderGoodsVo goodsVo = ServiceManager.goodsService.findPurchaseOrderGoodsVoById(goodsId);
        CommonUtil.copyProperties(goodsVo, orderItemVo);
        Sku sku = ServiceManager.skuService.findByGoodsId(goodsId);
        orderItemVo.setRetailPrice(sku.getRetailPrice());
        return orderItemVo;
    }

    private PurchaseOrderPrintTemplateVo.PurchaseOrderItemPrintVo buildOrderItemPrintVo(PurchaseOrderItem orderItem) {
        PurchaseOrderPrintTemplateVo.PurchaseOrderItemPrintVo printVo = new PurchaseOrderPrintTemplateVo.PurchaseOrderItemPrintVo();
        printVo.setPurchaseQuantity(orderItem.getPurchaseQuantity());
        printVo.setPurchaseUnitPrice(orderItem.getPurchaseUnitPrice());
        printVo.setPurchaseTotalAmount(BigDecimalUtil.multiply(orderItem.getPurchaseQuantity(), orderItem.getPurchaseUnitPrice()));
        //商品信息
        Long goodsId = orderItem.getGoodsId();
        Goods goods = ServiceManager.goodsService.findOne(goodsId);
        printVo.setGoodsCode(goods.getGoodsCode());
        printVo.setCommonNm(goods.getCommonNm());
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