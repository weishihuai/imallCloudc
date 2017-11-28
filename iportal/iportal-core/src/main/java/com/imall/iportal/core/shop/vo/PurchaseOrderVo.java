package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.shop.entity.PurchaseOrder;

import java.util.List;

/**
 * Created by wsh on 2017/7/7.
 * 采购订单查看 VO对象
 */
public class PurchaseOrderVo extends PurchaseOrder {
    //采购商名称
    private String supplierNm;
    //采购商编码
    private String supplierCode;
    //默认收货时间
    private String receiveTimeString;
    //采购订单项
    private List<PurchaseOrderItemVo> orderItemVoList;

    public String getSupplierNm() {
        return supplierNm;
    }

    public void setSupplierNm(String supplierNm) {
        this.supplierNm = supplierNm;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public List<PurchaseOrderItemVo> getOrderItemVoList() {
        return orderItemVoList;
    }

    public void setOrderItemVoList(List<PurchaseOrderItemVo> orderItemVoList) {
        this.orderItemVoList = orderItemVoList;
    }

    public String getReceiveTimeString() {
        return receiveTimeString;
    }

    public void setReceiveTimeString(String receiveTimeString) {
        this.receiveTimeString = receiveTimeString;
    }
}
