package com.imall.iportal.core.shop.vo;

/**
 * Created by caidapao on 2017/8/2.
 */
public class FirstManageDrugQualityApprovePageVo {

    private Long id;

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
     * 剂型名称
     */
    private String dosageFormName;
    /**
     * 生产厂商
     */
    private String produceManufacturer;
    /**
     * 单位
     */
    private String unit;
    /**
     * 批准文号
     */
    private String approvalNumber;
    /**
     * 审核 状态 代码
     */
    private String approveStateCode;
    /**
     * 质量 审核 时间
     */
    private String qualityApproveTimeStr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDosageFormName() {
        return dosageFormName;
    }

    public void setDosageFormName(String dosageFormName) {
        this.dosageFormName = dosageFormName;
    }

    public String getProduceManufacturer() {
        return produceManufacturer;
    }

    public void setProduceManufacturer(String produceManufacturer) {
        this.produceManufacturer = produceManufacturer;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public String getApproveStateCode() {
        return approveStateCode;
    }

    public void setApproveStateCode(String approveStateCode) {
        this.approveStateCode = approveStateCode;
    }

    public String getQualityApproveTimeStr() {
        return qualityApproveTimeStr;
    }

    public void setQualityApproveTimeStr(String qualityApproveTimeStr) {
        this.qualityApproveTimeStr = qualityApproveTimeStr;
    }
}
