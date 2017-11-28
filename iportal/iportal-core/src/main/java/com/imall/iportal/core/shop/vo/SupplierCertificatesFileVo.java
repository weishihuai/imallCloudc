package com.imall.iportal.core.shop.vo;

/**
 * Created by ou on 2017/7/8.
 */
public class SupplierCertificatesFileVo {

    /**
     * id
     */
    private Long id;
    /**
     * 分类
     */
    private String certificatesType;
    /**
     * 编号
     */
    private String certificatesNum;
    /**
     * 有效期
     */
    private String certificatesValidityString;
    /**
     * 供应商id
     */
    private Long supplierId;

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCertificatesType() {
        return certificatesType;
    }

    public void setCertificatesType(String certificatesType) {
        this.certificatesType = certificatesType;
    }

    public String getCertificatesNum() {
        return certificatesNum;
    }

    public void setCertificatesNum(String certificatesNum) {
        this.certificatesNum = certificatesNum;
    }

    public String getCertificatesValidityString() {
        return certificatesValidityString;
    }

    public void setCertificatesValidityString(String certificatesValidityString) {
        this.certificatesValidityString = certificatesValidityString;
    }
}
