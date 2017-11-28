package com.imall.iportal.core.shop.vo;

import com.imall.commons.dicts.PurchaseOrderStateCodeEnum;
import com.imall.iportal.core.shop.entity.PurchaseOrder;

/**
 * Created by wsh on 2017/7/7.
 * 采购订单列表 VO对象
 */
public class PurchaseOrderPageVo extends PurchaseOrder {
    //采购商名称
    private String supplierNm;
    //收货单编号--采购验收列表展示
    private String purchaseReceiveOrderNum;

    public String getSupplierNm() {
        return supplierNm;
    }

    public void setSupplierNm(String supplierNm) {
        this.supplierNm = supplierNm;
    }

    public String getPurchaseOrderStateName() {
        return PurchaseOrderStateCodeEnum.fromCode(getPurchaseOrderState()).toName();
    }

    public String getPurchaseReceiveOrderNum() {
        return purchaseReceiveOrderNum;
    }

    public void setPurchaseReceiveOrderNum(String purchaseReceiveOrderNum) {
        this.purchaseReceiveOrderNum = purchaseReceiveOrderNum;
    }
}
