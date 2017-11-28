package com.imall.iportal.core.shop.vo;

/**
 * Created by caidapao on 2017/8/2.
 */
public class FirstManageDrugQualityApproveDetailVo {

    /**
     * 商品编码
     */
    private String goodsCode;
    /**
     * 商品名称
     */
    private String goodsNm;

    /**
     * 通用名称
     */
    private String commonNm;

    /**
     * 规格
     */
    private String spec;
    /**
     * 剂型
     */
    private String dosageForm;
    /**
     * 单位
     */
    private String unit;
    /**
     * 生产厂商
     */
    private String produceManufacturer;
    /**
     * 产地
     */
    private String productionPlace;
    /**
     * 批准文号期限
     */
    private String approvalNumberTermString;
    /**
     * 批准文号
     */
    private String approvalNumber;
    /**
     * 毒理代码 名称
     */
    private String toxicologyCode;
    /**
     * 储存条件代码
     */
    private String storageCondition;
    /**
     * 零售价
     */
    private Double retailPrice;
    /**
     * 会员价
     */
    private Double memberPrice;

    /**
     * 申请人姓名
     */
    private String applyManName;

    /**
     * 申请备注
     */
    private String applyRemark;
    /**
     * 提交意见
     */
    private String submitAdvice;

    /**
     * 采购审核时间
     */
    private String purchaseApproveTimeStr;
    /**
     * 采购审核类型代码
     */
    private String purchaseApproveStateCode;
    /**
     * 采购申请备注
     */
    private String purchaseApproveRemark;
    /**
     * 采购审核人名称
     */
    private String purchaseApproveManName;
    /**
     * 质量审核时间
     */
    private String createDateStr;
    /**
     * 商品类型
     */
    private String goodsTypeCode;


    /**
     * 以下为质量部分,与采购一致
     */
    private String qualityApproveTimeStr;
    private String qualityApproveStateCode;
    private String qualityApproveRemark;
    private String qualityApproveManName;

    /**
     * 以下为企业部分,与采购一致
     */
    private String enterpriseApproveTimeStr;
    private String enterpriseApproveStateCode;
    private String enterpriseApproveRemark;
    private String enterpriseApproveManName;

    public String getGoodsTypeCode() {
        return goodsTypeCode;
    }

    public void setGoodsTypeCode(String goodsTypeCode) {
        this.goodsTypeCode = goodsTypeCode;
    }

    public String getApplyManName() {
        return applyManName;
    }

    public void setApplyManName(String applyManName) {
        this.applyManName = applyManName;
    }

    public String getApplyRemark() {
        return applyRemark;
    }

    public void setApplyRemark(String applyRemark) {
        this.applyRemark = applyRemark;
    }

    public String getSubmitAdvice() {
        return submitAdvice;
    }

    public void setSubmitAdvice(String submitAdvice) {
        this.submitAdvice = submitAdvice;
    }

    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsNm() {
        return goodsNm;
    }

    public void setGoodsNm(String goodsNm) {
        this.goodsNm = goodsNm;
    }

    public String getCommonNm() {
        return commonNm;
    }

    public void setCommonNm(String commonNm) {
        this.commonNm = commonNm;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getProduceManufacturer() {
        return produceManufacturer;
    }

    public void setProduceManufacturer(String produceManufacturer) {
        this.produceManufacturer = produceManufacturer;
    }

    public String getProductionPlace() {
        return productionPlace;
    }

    public void setProductionPlace(String productionPlace) {
        this.productionPlace = productionPlace;
    }

    public String getApprovalNumberTermString() {
        return approvalNumberTermString;
    }

    public void setApprovalNumberTermString(String approvalNumberTermString) {
        this.approvalNumberTermString = approvalNumberTermString;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public String getToxicologyCode() {
        return toxicologyCode;
    }

    public void setToxicologyCode(String toxicologyCode) {
        this.toxicologyCode = toxicologyCode;
    }

    public String getStorageCondition() {
        return storageCondition;
    }

    public void setStorageCondition(String storageCondition) {
        this.storageCondition = storageCondition;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Double getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(Double memberPrice) {
        this.memberPrice = memberPrice;
    }

    public String getPurchaseApproveManName() {
        return purchaseApproveManName;
    }

    public void setPurchaseApproveManName(String purchaseApproveManName) {
        this.purchaseApproveManName = purchaseApproveManName;
    }

    public String getQualityApproveManName() {
        return qualityApproveManName;
    }

    public void setQualityApproveManName(String qualityApproveManName) {
        this.qualityApproveManName = qualityApproveManName;
    }

    public String getEnterpriseApproveManName() {
        return enterpriseApproveManName;
    }

    public void setEnterpriseApproveManName(String enterpriseApproveManName) {
        this.enterpriseApproveManName = enterpriseApproveManName;
    }


    public String getPurchaseApproveTimeStr() {
        return purchaseApproveTimeStr;
    }

    public void setPurchaseApproveTimeStr(String purchaseApproveTimeStr) {
        this.purchaseApproveTimeStr = purchaseApproveTimeStr;
    }

    public String getPurchaseApproveStateCode() {
        return purchaseApproveStateCode;
    }

    public void setPurchaseApproveStateCode(String purchaseApproveStateCode) {
        this.purchaseApproveStateCode = purchaseApproveStateCode;
    }

    public String getPurchaseApproveRemark() {
        return purchaseApproveRemark;
    }

    public void setPurchaseApproveRemark(String purchaseApproveRemark) {
        this.purchaseApproveRemark = purchaseApproveRemark;
    }


    public String getQualityApproveTimeStr() {
        return qualityApproveTimeStr;
    }

    public void setQualityApproveTimeStr(String qualityApproveTimeStr) {
        this.qualityApproveTimeStr = qualityApproveTimeStr;
    }

    public String getQualityApproveStateCode() {
        return qualityApproveStateCode;
    }

    public void setQualityApproveStateCode(String qualityApproveStateCode) {
        this.qualityApproveStateCode = qualityApproveStateCode;
    }

    public String getQualityApproveRemark() {
        return qualityApproveRemark;
    }

    public void setQualityApproveRemark(String qualityApproveRemark) {
        this.qualityApproveRemark = qualityApproveRemark;
    }

    public String getEnterpriseApproveTimeStr() {
        return enterpriseApproveTimeStr;
    }

    public void setEnterpriseApproveTimeStr(String enterpriseApproveTimeStr) {
        this.enterpriseApproveTimeStr = enterpriseApproveTimeStr;
    }

    public String getEnterpriseApproveStateCode() {
        return enterpriseApproveStateCode;
    }

    public void setEnterpriseApproveStateCode(String enterpriseApproveStateCode) {
        this.enterpriseApproveStateCode = enterpriseApproveStateCode;
    }

    public String getEnterpriseApproveRemark() {
        return enterpriseApproveRemark;
    }

    public void setEnterpriseApproveRemark(String enterpriseApproveRemark) {
        this.enterpriseApproveRemark = enterpriseApproveRemark;
    }
}
