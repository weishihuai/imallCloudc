package com.imall.iportal.core.shop.vo;

/**
 * Created by frt on 2017/8/1.
 */
public class PrescriptionRegisterItemVo {
    /**
     * 商品 编码
     */
    private String goodsCoding;

    /**
     * 商品 名称
     */
    private String goodsNm;

    /**
     * 通用 名称
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
     * 生产 厂商
     */
    private String produceManufacturer;

    /**
     * 批准文号
     */
    private String approvalNumber;

    /**
     * 产地
     */
    private String productionPlace;

    /**
     * 数量
     */
    private Long quantity;

    public String getGoodsCoding() {
        return goodsCoding;
    }

    public void setGoodsCoding(String goodsCoding) {
        this.goodsCoding = goodsCoding;
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

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public String getProductionPlace() {
        return productionPlace;
    }

    public void setProductionPlace(String productionPlace) {
        this.productionPlace = productionPlace;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
