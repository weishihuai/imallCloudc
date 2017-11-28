package com.imall.iportal.core.platform.vo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by ou on 2017/7/8.
 */
public class SupplierDocCertificatesFileSaveVo {


    /**
     * suppplierId
     */
    @NotNull
    private Long supplierDocId;
    /**
     * 分类
     */
    @NotBlank
    private String certificatesType;
    /**
     * 编号
     */

    private String certificatesNum;
    /**
     * 有效期
     */

    private String certificatesValidityString;

    public Long getSupplierDocId() {
        return supplierDocId;
    }

    public void setSupplierDocId(Long supplierDocId) {
        this.supplierDocId = supplierDocId;
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
