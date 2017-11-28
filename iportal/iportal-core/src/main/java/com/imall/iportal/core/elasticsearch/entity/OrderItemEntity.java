package com.imall.iportal.core.elasticsearch.entity;

/**
 * Created by lxh on 2017/8/2.
 */
public class OrderItemEntity {
    //商品ID
    public static final String GOODS_ID = "itemList.goodsId";
    //订单ID
    public static final String ORDER_ID = "itemList.orderId";
    //skuId
    public static final String SKU_ID = "itemList.skuId";
    //商品编码
    public static final String GOODS_CODING = "itemList.goodsCoding";
    //通用名称
    public static final String COMMON_NM = "itemList.commonNm";
    //商品名称
    public static final String GOODS_NM = "itemList.goodsNm";
    //通用名称首字母
    public static final String GOODS_PINYIN= "itemList.goodsPinyin";
    //生产厂商
    public static final String PRODUCE_MANUFACTURER = "itemList.produceManufacturer";
    //规格
    public static final String SPEC = "itemList.spec";
    //单位
    public static final String UNIT = "itemList.unit";
    //批号
    public static final String BATCH = "itemList.batch";
    //数量
    public static final String QUANTITY = "itemList.quantity";
    //商品单价
    public static final String GOODS_UNIT_PRICE = "itemList.goodsUnitPrice";
    //商品成本总金额
    public static final String GOODS_COST_TOTAL_AMOUNT = "itemList.goodsCostTotalAmount";
    //商品总金额
    public static final String GOODS_TOTAL_AMOUNT = "itemList.goodsTotalAmount";
    //营业员
    public static final String SELLER = "itemList.seller";
    //条形码
    public static final String BAR_CODE = "itemList.barCode";
    private Long orderId;
    private Long goodsId;
    private Long skuId;
    private String goodsCoding;
    private String commonNm;
    private String goodsPinyin;
    private String goodsNm;
    private String produceManufacturer;
    private String spec;
    private String unit;
    private String batch;
    private Long quantity;
    private Double goodsUnitPrice;
    private Double goodsCostTotalAmount;
    private Double goodsTotalAmount;
    private Long seller;
    private String barCode;

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getGoodsCoding() {
        return goodsCoding;
    }

    public void setGoodsCoding(String goodsCoding) {
        this.goodsCoding = goodsCoding;
    }

    public String getCommonNm() {
        return commonNm;
    }

    public void setCommonNm(String commonNm) {
        this.commonNm = commonNm;
    }

    public String getGoodsPinyin() {
        return goodsPinyin;
    }

    public void setGoodsPinyin(String goodsPinyin) {
        this.goodsPinyin = goodsPinyin;
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

    public Double getGoodsUnitPrice() {
        return goodsUnitPrice;
    }

    public void setGoodsUnitPrice(Double goodsUnitPrice) {
        this.goodsUnitPrice = goodsUnitPrice;
    }

    public Double getGoodsCostTotalAmount() {
        return goodsCostTotalAmount;
    }

    public void setGoodsCostTotalAmount(Double goodsCostTotalAmount) {
        this.goodsCostTotalAmount = goodsCostTotalAmount;
    }

    public Double getGoodsTotalAmount() {
        return goodsTotalAmount;
    }

    public void setGoodsTotalAmount(Double goodsTotalAmount) {
        this.goodsTotalAmount = goodsTotalAmount;
    }

    public Long getSeller() {
        return seller;
    }

    public void setSeller(Long seller) {
        this.seller = seller;
    }
}
