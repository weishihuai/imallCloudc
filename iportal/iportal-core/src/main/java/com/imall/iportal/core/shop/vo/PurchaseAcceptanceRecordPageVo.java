package com.imall.iportal.core.shop.vo;

import com.imall.commons.dicts.PurchaseOrderStateCodeEnum;
import com.imall.iportal.core.shop.entity.PurchaseAcceptanceRecord;

/**
 * Created by lxh on 2017/7/31.
 */
public class PurchaseAcceptanceRecordPageVo extends PurchaseAcceptanceRecord {
    //供应商名称
    private String supplierNm;
    //订单状态
    private String purchaseOrderState;
    //入库时间
    private String inStorageTimeString;

    public String getSupplierNm() {
        return supplierNm;
    }

    public void setSupplierNm(String supplierNm) {
        this.supplierNm = supplierNm;
    }

    public String getPurchaseOrderState() {
        return purchaseOrderState;
    }

    public void setPurchaseOrderState(String purchaseOrderState) {
        this.purchaseOrderState = purchaseOrderState;
    }

    public String getInStorageTimeString() {
        return inStorageTimeString;
    }

    public void setInStorageTimeString(String inStorageTimeString) {
        this.inStorageTimeString = inStorageTimeString;
    }

    public String getPurchaseOrderStateName() {
        return PurchaseOrderStateCodeEnum.fromCode(purchaseOrderState).toName();
    }
}
