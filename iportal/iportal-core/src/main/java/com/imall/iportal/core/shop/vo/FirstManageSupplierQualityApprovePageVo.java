package com.imall.iportal.core.shop.vo;

/**
 * Created by ou on 2017/7/7.
 */
public class FirstManageSupplierQualityApprovePageVo {

    /**
     * 供应商 质量审核 ID
     */
    private Long id;
    /**
     * 门店 ID
     */
    private Long shopId;
    /**
     * 供应商 ID
     */
    private Long supplierId;
    /**
     * 供应商 编码
     */
    private String supplierCode;
    /**
     * 供应商 名称
     */
    private  String supplierNm;
    /**
     * 法定代表人
     */
    private String legalRepresentative;
    /**
     * 质量 负责 人
     */
    private String qualityResponseManName;
    /**
     * 业务 负责 人
     */
    private String businessResponseManName;
    /**
     * 税务登记证号
     */
    private String taxRegisterCertNum;
    /**
     * 营业执照号
     */
    private String businessLicense;
    /**
     * 营业执照有效期
     */
    private String businessLicenseTimeString;
    /**
     *  审核 状态 代码
     */
    private String approveStateCode;
    /**
     *  审核 状态
     */
    private String approveStateName;
    /**
     *  质量审核时间
     */
    private String qualityApproveTimeString;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
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

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public String getQualityResponseManName() {
        return qualityResponseManName;
    }

    public void setQualityResponseManName(String qualityResponseManName) {
        this.qualityResponseManName = qualityResponseManName;
    }

    public String getBusinessResponseManName() {
        return businessResponseManName;
    }

    public void setBusinessResponseManName(String businessResponseManName) {
        this.businessResponseManName = businessResponseManName;
    }

    public String getTaxRegisterCertNum() {
        return taxRegisterCertNum;
    }

    public void setTaxRegisterCertNum(String taxRegisterCertNum) {
        this.taxRegisterCertNum = taxRegisterCertNum;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getBusinessLicenseTimeString() {
        return businessLicenseTimeString;
    }

    public void setBusinessLicenseTimeString(String businessLicenseTimeString) {
        this.businessLicenseTimeString = businessLicenseTimeString;
    }

    public String getApproveStateCode() {
        return approveStateCode;
    }

    public void setApproveStateCode(String approveStateCode) {
        this.approveStateCode = approveStateCode;
    }

    public String getApproveStateName() {
        return approveStateName;
    }

    public void setApproveStateName(String approveStateName) {
        this.approveStateName = approveStateName;
    }

    public String getQualityApproveTimeString() {
        return qualityApproveTimeString;
    }

    public void setQualityApproveTimeString(String qualityApproveTimeString) {
        this.qualityApproveTimeString = qualityApproveTimeString;
    }
}
