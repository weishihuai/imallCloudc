package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.entity.PurchaseAcceptanceRecordItem;
import com.imall.iportal.core.shop.entity.PurchaseOrder;
import com.imall.iportal.core.shop.entity.Supplier;
import com.imall.iportal.core.shop.repository.PurchaseAcceptanceRecordItemRepository;
import com.imall.iportal.core.shop.service.PurchaseAcceptanceRecordItemService;
import com.imall.iportal.core.shop.vo.PurchaseAcceptanceRecordItemPageVo;
import com.imall.iportal.core.shop.vo.PurchaseAcceptanceRecordItemSearchParams;
import com.imall.iportal.core.shop.vo.PurchaseOrderGoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class PurchaseAcceptanceRecordItemServiceImpl extends AbstractBaseService<PurchaseAcceptanceRecordItem, Long> implements PurchaseAcceptanceRecordItemService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private PurchaseAcceptanceRecordItemRepository getPurchaseAcceptanceRecordItemRepository() {
        return (PurchaseAcceptanceRecordItemRepository) baseRepository;
    }

    @Override
    public List<PurchaseAcceptanceRecordItem> findByPurchaseAcceptanceRecordId(Long purchaseAcceptanceRecordId) {
        return getPurchaseAcceptanceRecordItemRepository().findByPurchaseAcceptanceRecordId(purchaseAcceptanceRecordId);
    }

    @Override
    public Page<PurchaseAcceptanceRecordItemPageVo> queryReturnableItem(Pageable pageable, PurchaseAcceptanceRecordItemSearchParams searchParams) {
        Page<PurchaseAcceptanceRecordItem> recordItemPage = getPurchaseAcceptanceRecordItemRepository().queryReturnableItem(pageable, searchParams);
        List<PurchaseAcceptanceRecordItemPageVo> voList = new ArrayList<>();
        for (PurchaseAcceptanceRecordItem item : recordItemPage.getContent()) {
            PurchaseAcceptanceRecordItemPageVo vo = CommonUtil.copyProperties(item, new PurchaseAcceptanceRecordItemPageVo());
            //商品信息
            Long goodsId = item.getGoodsId();
            PurchaseOrderGoodsVo goods = ServiceManager.goodsService.findPurchaseOrderGoodsVoById(goodsId);
            CommonUtil.copyProperties(goods, vo);
            vo.setGoodsCommonNm(goods.getCommonNm());
            voList.add(vo);
        }
        return new PageImpl<PurchaseAcceptanceRecordItemPageVo>(voList, pageable, recordItemPage.getTotalElements());
    }

    @Override
    public Page<PurchaseAcceptanceRecordItemPageVo> query(Pageable pageable, Long shopId, Long supplierId, String acceptanceOrderNum) {
        Page<PurchaseAcceptanceRecordItemPageVo> voPage = getPurchaseAcceptanceRecordItemRepository().query(pageable, shopId, supplierId, acceptanceOrderNum);
        for (PurchaseAcceptanceRecordItemPageVo vo : voPage.getContent()){
            Supplier supplier = ServiceManager.supplierService.findOne(vo.getSupplierId());
            vo.setSupplierNm(supplier.getSupplierNm());
            PurchaseOrderGoodsVo goodsVo = ServiceManager.goodsService.findPurchaseOrderGoodsVoById(vo.getGoodsId());
            CommonUtil.copyProperties(goodsVo, vo);
            vo.setGoodsCommonNm(goodsVo.getCommonNm());
            PurchaseOrder purchaseOrder = ServiceManager.purchaseOrderService.findOne(vo.getPurchaseOrderId());
            vo.setPurchaseOrderNum(purchaseOrder.getPurchaseOrderNum());
        }
        return voPage;
    }
}