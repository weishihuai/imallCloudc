package com.imall.iportal.core.shop.vo;

/**
 * Created by ygw on 2017/3/1.
 */
public abstract class CartItem {
    /**
     * 物品ID
     */
    protected Long objectId;

    /**
     * 商品ID
     */
    protected Long goodsId;

    /**
     * SkuID
     */
    protected Long skuId;

    /**
     * 商品 编码
     */
    protected String goodsCode;

    /**
     * 商品图片路径
     */
    private String goodsImgUrl;

    /**
     * 通用 名称
     */
    protected String commonNm;

    /**
     * 商品 名称
     */
    protected String goodsNm;

    /**
     * 生产厂商
     */
    protected String produceManufacturer;

    /**
     * 规格
     */
    protected String spec;

    /**
     * 单位
     */
    protected String unit;

    /**
     * 批号
     */
    protected String batch;

    /**
     * 数量
     */
    protected Long quantity = 0L;

    /**
     * 单价
     */
    protected Double unitPrice = 0D;

    /**
     * 单品优惠价格
     */
    protected Double discountUnitPrice = 0D; //todo:营销策略、营销规则 未开始

    /**
     * 这个物品总优惠金额
     */
    protected Double discountTotalAmount = 0D; //todo:营销策略、营销规则 未开始

    /**
     * 物品总金额（小计）= 单价 * 数量 - 这个物品总优惠金额
     */
    protected Double totalAmount = 0D;

    /**
     * 是否勾选
     */
    protected Boolean isItemSelected = true;


    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
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

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getDiscountUnitPrice() {
        return discountUnitPrice;
    }

    public void setDiscountUnitPrice(Double discountUnitPrice) {
        this.discountUnitPrice = discountUnitPrice;
    }

    public Double getDiscountTotalAmount() {
        return discountTotalAmount;
    }

    public void setDiscountTotalAmount(Double discountTotalAmount) {
        this.discountTotalAmount = discountTotalAmount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Boolean getIsItemSelected() {
        return this.isItemSelected;
    }

    public void setIsItemSelected(Boolean isItemSelected) {
        this.isItemSelected = isItemSelected;
    }

    public String getGoodsImgUrl() {
        return goodsImgUrl;
    }

    public void setGoodsImgUrl(String goodsImgUrl) {
        this.goodsImgUrl = goodsImgUrl;
    }
}
