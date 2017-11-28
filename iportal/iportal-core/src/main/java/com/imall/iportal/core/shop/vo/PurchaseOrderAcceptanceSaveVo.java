package com.imall.iportal.core.shop.vo;

/**
 * Created by wsh on 2017/7/7.
 * 采购订单验收 VO对象
 */
public class PurchaseOrderAcceptanceSaveVo {
    /**
     * 采购订单ID
     */
    private Long id;
    /**
     * 供应商编码
     */
    private String supplierCode;
    /**
     * 供应商名称
     */
    private String supplierNm;
    /**
     * 验收人
     */
    private String acceptor;
    /**
     * 验收时间
     */
    private String acceptanceTimeString;

    //todo：wsh 增加验收审核人 制单人

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAcceptor() {
        return acceptor;
    }

    public void setAcceptor(String acceptor) {
        this.acceptor = acceptor;
    }

    public String getAcceptanceTimeString() {
        return acceptanceTimeString;
    }

    public void setAcceptanceTimeString(String acceptanceTimeString) {
        this.acceptanceTimeString = acceptanceTimeString;
    }
}
