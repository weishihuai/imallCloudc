package com.imall.iportal.core.shop.vo;

/**
 * Created by lxh on 2017/7/25.
 * 快速收货列表VO
 */
public class FastReceivePageVo {
    //采购验收记录主键
    private Long purchaseAcceptanceRecordId;
    //验收单编号
    private String acceptanceOrderNum;
    //供应商名称
    private String supplierNm;
    //验收人
    private String acceptanceMan;
    //验收金额
    private Double acceptanceTotalAmount;
    //验收时间
    private String acceptanceTimeString;
    //入库时间
    private String inStorageTimeString;

    public Long getPurchaseAcceptanceRecordId() {
        return purchaseAcceptanceRecordId;
    }

    public void setPurchaseAcceptanceRecordId(Long purchaseAcceptanceRecordId) {
        this.purchaseAcceptanceRecordId = purchaseAcceptanceRecordId;
    }

    public String getAcceptanceOrderNum() {
        return acceptanceOrderNum;
    }

    public void setAcceptanceOrderNum(String acceptanceOrderNum) {
        this.acceptanceOrderNum = acceptanceOrderNum;
    }

    public String getSupplierNm() {
        return supplierNm;
    }

    public void setSupplierNm(String supplierNm) {
        this.supplierNm = supplierNm;
    }

    public String getAcceptanceMan() {
        return acceptanceMan;
    }

    public void setAcceptanceMan(String acceptanceMan) {
        this.acceptanceMan = acceptanceMan;
    }

    public Double getAcceptanceTotalAmount() {
        return acceptanceTotalAmount;
    }

    public void setAcceptanceTotalAmount(Double acceptanceTotalAmount) {
        this.acceptanceTotalAmount = acceptanceTotalAmount;
    }

    public String getAcceptanceTimeString() {
        return acceptanceTimeString;
    }

    public void setAcceptanceTimeString(String acceptanceTimeString) {
        this.acceptanceTimeString = acceptanceTimeString;
    }

    public String getInStorageTimeString() {
        return inStorageTimeString;
    }

    public void setInStorageTimeString(String inStorageTimeString) {
        this.inStorageTimeString = inStorageTimeString;
    }
}
