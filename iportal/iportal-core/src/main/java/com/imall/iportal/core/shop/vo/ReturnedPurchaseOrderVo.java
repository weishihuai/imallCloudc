package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.shop.entity.ReturnedPurchaseOrder;

import java.util.List;

/**
 * Created by lxh on 2017/7/14.
 * 退货单详情VO
 */
public class ReturnedPurchaseOrderVo extends ReturnedPurchaseOrder {
    //供应商编码
    private String supplierCode;
    //供应商名称
    private String supplierNm;
    //审核人
    private String approveMan;
    //退货项
    private List<ReturnedPurchaseOrderItemVo> itemList;

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

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

    public List<ReturnedPurchaseOrderItemVo> getItemList() {
        return itemList;
    }

    public void setItemList(List<ReturnedPurchaseOrderItemVo> itemList) {
        this.itemList = itemList;
    }
}
