package com.imall.iportal.core.shop.vo;

import java.util.List;

/**
 * Created by ou on 2017/7/7.
 */
public class SupplierComponentPageVo {
    /**
     * 供应商 id
     */
    private String id;

    /**
     * 供应商 名称
     */
    private  String supplierNm;
    /**
     * 供应商 编码
     */
    private  String supplierCode;
    /**
     * 注册 地址
     */
    private  String regAddr;
    /**
     * 经营 许可证号
     */
    private  String commodityLicense;
    /**
     * 营业执照号
     */
    private String businessLicense;
    /**
     * 法定代表人
     */
    private String legalRepresentative;
    /**
     * 资质
     */
    List<SupplierCertificatesFileVo> supplierCertificatesFileVos;

    public List<SupplierCertificatesFileVo> getSupplierCertificatesFileVos() {
        return supplierCertificatesFileVos;
    }

    public void setSupplierCertificatesFileVos(List<SupplierCertificatesFileVo> supplierCertificatesFileVos) {
        this.supplierCertificatesFileVos = supplierCertificatesFileVos;
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getRegAddr() {
        return regAddr;
    }

    public void setRegAddr(String regAddr) {
        this.regAddr = regAddr;
    }

    public String getCommodityLicense() {
        return commodityLicense;
    }

    public void setCommodityLicense(String commodityLicense) {
        this.commodityLicense = commodityLicense;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }
}
