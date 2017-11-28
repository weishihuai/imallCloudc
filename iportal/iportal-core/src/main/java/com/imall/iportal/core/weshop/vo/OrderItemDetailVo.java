package com.imall.iportal.core.weshop.vo;

import java.util.List;

/**
 * 订单项信息
 */
public class OrderItemDetailVo {
    private Long id;

    /**
     * 商品图片
     */
    private String goodsPicUrl;

    /**
     * 通用 名称
     */
    private String commonNm;

    /**
     * 规格
     */
    private String spec;

    /**
     * 单位
     */
    private String unit;

    /**
     * 生产 厂商
     */
    private String produceManufacturer;

    /**
     * 处方药 类型 代码
     */
    private String prescriptionDrugsTypeCode;

    /**
     * 是否 麻黄碱
     */
    private String isEphedrine;

    /**
     * 商品 单位 价格
     */
    private Double goodsUnitPrice;

    /**
     * 数量
     */
    private Long quantity;

    /**
     * 商品 总 金额
     */
    private Double goodsTotalAmount;

    /**
     * 订单项批次信息
     */
    private List<OrderSendOutBatchDetailVo> orderSendOutBatchDetailVoList;

    //商品名称
    private String goodsNm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodsPicUrl() {
        return goodsPicUrl;
    }

    public void setGoodsPicUrl(String goodsPicUrl) {
        this.goodsPicUrl = goodsPicUrl;
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

    public String getPrescriptionDrugsTypeCode() {
        return prescriptionDrugsTypeCode;
    }

    public void setPrescriptionDrugsTypeCode(String prescriptionDrugsTypeCode) {
        this.prescriptionDrugsTypeCode = prescriptionDrugsTypeCode;
    }

    public String getIsEphedrine() {
        return isEphedrine;
    }

    public void setIsEphedrine(String isEphedrine) {
        this.isEphedrine = isEphedrine;
    }

    public Double getGoodsUnitPrice() {
        return goodsUnitPrice;
    }

    public void setGoodsUnitPrice(Double goodsUnitPrice) {
        this.goodsUnitPrice = goodsUnitPrice;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getGoodsTotalAmount() {
        return goodsTotalAmount;
    }

    public void setGoodsTotalAmount(Double goodsTotalAmount) {
        this.goodsTotalAmount = goodsTotalAmount;
    }

    public List<OrderSendOutBatchDetailVo> getOrderSendOutBatchDetailVoList() {
        return orderSendOutBatchDetailVoList;
    }

    public void setOrderSendOutBatchDetailVoList(List<OrderSendOutBatchDetailVo> orderSendOutBatchDetailVoList) {
        this.orderSendOutBatchDetailVoList = orderSendOutBatchDetailVoList;
    }

    public String getGoodsNm() {
        return goodsNm;
    }

    public void setGoodsNm(String goodsNm) {
        this.goodsNm = goodsNm;
    }
}
