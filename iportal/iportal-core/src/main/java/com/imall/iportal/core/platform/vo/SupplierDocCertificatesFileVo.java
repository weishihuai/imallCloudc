package com.imall.iportal.core.platform.vo;

/**
 * Created by ou on 2017/7/8.
 */
public class SupplierDocCertificatesFileVo {

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
    private Long supplierDocId;

    public Long getSupplierDocId() {
        return supplierDocId;
    }

    public void setSupplierDocId(Long supplierDocId) {
        this.supplierDocId = supplierDocId;
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
