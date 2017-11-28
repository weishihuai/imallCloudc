package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.entity.PurchaseAcceptanceRecord;
import com.imall.iportal.core.shop.entity.PurchaseAcceptanceRecordItem;
import com.imall.iportal.core.shop.entity.ReturnedPurchaseOrderItem;
import com.imall.iportal.core.shop.entity.Supplier;
import com.imall.iportal.core.shop.repository.ReturnedPurchaseOrderItemRepository;
import com.imall.iportal.core.shop.service.ReturnedPurchaseOrderItemService;
import com.imall.iportal.core.shop.vo.PurchaseOrderGoodsVo;
import com.imall.iportal.core.shop.vo.ReturnedPurchaseOrderItemPageVo;
import com.imall.iportal.core.shop.vo.ReturnedPurchaseOrderSearchParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (服务层实现)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class ReturnedPurchaseOrderItemServiceImpl extends AbstractBaseService<ReturnedPurchaseOrderItem, Long> implements ReturnedPurchaseOrderItemService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private ReturnedPurchaseOrderItemRepository getReturnedPurchaseOrderItemRepository() {
        return (ReturnedPurchaseOrderItemRepository) baseRepository;
    }

    @Override
    public List<ReturnedPurchaseOrderItem> findByReturnedPurchaseOrderId(Long returnedPurchaseOrderId) {
        return getReturnedPurchaseOrderItemRepository().findByReturnedPurchaseOrderId(returnedPurchaseOrderId);
    }

    @Override
    public Page<ReturnedPurchaseOrderItemPageVo> query(Pageable pageable, ReturnedPurchaseOrderSearchParams searchParams) {
        Page<ReturnedPurchaseOrderItemPageVo> voPage = getReturnedPurchaseOrderItemRepository().query(pageable, searchParams);
        for (ReturnedPurchaseOrderItemPageVo vo : voPage.getContent()){
            Supplier supplier = ServiceManager.supplierService.findOne(vo.getSupplierId());
            vo.setSupplierNm(supplier.getSupplierNm());
            PurchaseAcceptanceRecordItem recordItem = ServiceManager.purchaseAcceptanceRecordItemService.findOne(vo.getPurchaseAcceptanceRecordItemId());
            PurchaseAcceptanceRecord record = ServiceManager.purchaseAcceptanceRecordService.findOne(recordItem.getPurchaseAcceptanceRecordId());
            vo.setAcceptanceOrderNum(record.getAcceptanceOrderNum());
            PurchaseOrderGoodsVo goodsVo = ServiceManager.goodsService.findPurchaseOrderGoodsVoById(vo.getGoodsId());
            CommonUtil.copyProperties(goodsVo, vo);
        }
        return voPage;
    }
}