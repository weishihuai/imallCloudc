package com.imall.iportal.frontend.wechat.order.proxy;

/**
 * 订单项
 */
public class OrderItemProxy {
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

    //商品名称
    private String goodsNm;

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

    public String getGoodsNm() {
        return goodsNm;
    }

    public void setGoodsNm(String goodsNm) {
        this.goodsNm = goodsNm;
    }
}
