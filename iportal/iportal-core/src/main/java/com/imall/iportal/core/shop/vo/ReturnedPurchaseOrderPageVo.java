package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.shop.entity.ReturnedPurchaseOrder;

/**
 * Created by lxh on 2017/7/14.
 */
public class ReturnedPurchaseOrderPageVo extends ReturnedPurchaseOrder {
    //供应商名称
    private String supplierNm;
    //审核人
    private String approveMan;

    public String getSupplierNm() {
        return supplierNm;
    }

    public void setSupplierNm(String supplierNm) {
        this.supplierNm = supplierNm;
    }

    public String getApproveMan() {
        return approveMan;
    }

    public void setApproveMan(String approveMan) {
        this.approveMan = approveMan;
    }
}
