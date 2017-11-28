package com.imall.iportal.core.shop.vo;

import java.util.List;

/**
 * Created by lxh on 2017/7/25.
 * 快速收货详情Vo
 */
public class FastReceiveVo {
    //采购人
    private String purchaseMan;
    //供应商编码
    private String supplierCode;
    //供应商名称
    private String supplierNm;
    //制单人
    private String docMaker;
    //订单时间
    private String orderCreateTimeString;
    //收货人
    private String receiver;
    //收货时间
    private String receiveTimeString;
    //验收人
    private String acceptanceMan;
    //验收时间
    private String acceptanceTimeString;
    //验收总金额
    private Double acceptanceTotalAmount;
    //快速收货项
    private List<FastReceiveItemVo> itemList;

    public String getPurchaseMan() {
        return purchaseMan;
    }

    public void setPurchaseMan(String purchaseMan) {
        this.purchaseMan = purchaseMan;
    }

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

    public String getDocMaker() {
        return docMaker;
    }

    public void setDocMaker(String docMaker) {
        this.docMaker = docMaker;
    }

    public String getOrderCreateTimeString() {
        return orderCreateTimeString;
    }

    public void setOrderCreateTimeString(String orderCreateTimeString) {
        this.orderCreateTimeString = orderCreateTimeString;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiveTimeString() {
        return receiveTimeString;
    }

    public void setReceiveTimeString(String receiveTimeString) {
        this.receiveTimeString = receiveTimeString;
    }

    public String getAcceptanceMan() {
        return acceptanceMan;
    }

    public void setAcceptanceMan(String acceptanceMan) {
        this.acceptanceMan = acceptanceMan;
    }

    public String getAcceptanceTimeString() {
        return acceptanceTimeString;
    }

    public void setAcceptanceTimeString(String acceptanceTimeString) {
        this.acceptanceTimeString = acceptanceTimeString;
    }

    public Double getAcceptanceTotalAmount() {
        return acceptanceTotalAmount;
    }

    public void setAcceptanceTotalAmount(Double acceptanceTotalAmount) {
        this.acceptanceTotalAmount = acceptanceTotalAmount;
    }

    public List<FastReceiveItemVo> getItemList() {
        return itemList;
    }

    public void setItemList(List<FastReceiveItemVo> itemList) {
        this.itemList = itemList;
    }
}
