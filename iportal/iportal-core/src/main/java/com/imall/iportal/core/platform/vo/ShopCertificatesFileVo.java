package com.imall.iportal.core.platform.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by ou on 2017/7/24.
 */
public class ShopCertificatesFileVo {

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
     * 门店 ID
     */
    @NotNull
    private Long shopId;

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

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}
