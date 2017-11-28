package com.imall.iportal.core.shop.vo;


import java.util.List;

/**
 * Created by HWJ on 2017/7/20
 */
public class GoodsPostPageVo {

    /**
     * 商品ID
     */
    private Long id;

    /**
     * skuId
     */
    private Long skuId;

    /**
     * 商品编码
     */
    private String goodsCode;

    /**
     * 通用 名称
     */
    private String commonNm;

    /**
     * 商品名称
     */
    private String goodsNm;

    /**
     * 生产 厂商
     */
    private String produceManufacturer;

    /**
     * 规格
     */
    private String spec;

    /**
     * 单位
     */
    private String unit;

    /**
     * 零售 价格
     */
    private Double retailPrice;

    /**
     * 会员价格
     */
    private Double memberPrice;

    /**
     * 库存
     */
    private Long stock;

    /**
     * 是否 麻黄碱
     */
    private String isEphedrine;

    /**
     * 是否 处方药
     */
    private String prescriptionDrugs;

    /**
     * 商品批次
     */
    private List<GoodsBatchPosPageVo> goodsBatchList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getCommonNm() {
        return commonNm;
    }

    public void setCommonNm(String commonNm) {
        this.commonNm = commonNm;
    }

    public String getGoodsNm() {
        return goodsNm;
    }

    public void setGoodsNm(String goodsNm) {
        this.goodsNm = goodsNm;
    }

    public String getProduceManufacturer() {
        return produceManufacturer;
    }

    public void setProduceManufacturer(String produceManufacturer) {
        this.produceManufacturer = produceManufacturer;
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

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public String getIsEphedrine() {
        return isEphedrine;
    }

    public void setIsEphedrine(String isEphedrine) {
        this.isEphedrine = isEphedrine;
    }

    public String getPrescriptionDrugs() {
        return prescriptionDrugs;
    }

    public void setPrescriptionDrugs(String prescriptionDrugs) {
        this.prescriptionDrugs = prescriptionDrugs;
    }

    public List<GoodsBatchPosPageVo> getGoodsBatchList() {
        return goodsBatchList;
    }

    public void setGoodsBatchList(List<GoodsBatchPosPageVo> goodsBatchList) {
        this.goodsBatchList = goodsBatchList;
    }
}
