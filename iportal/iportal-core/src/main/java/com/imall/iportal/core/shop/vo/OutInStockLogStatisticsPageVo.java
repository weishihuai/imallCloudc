package com.imall.iportal.core.shop.vo;

/**
 * 销售明细统计Vo,进销存台帐
 * Created by HWJ on 2017/7/13.
 */
public class OutInStockLogStatisticsPageVo {
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
     * 生产厂家
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
     * 结算 前 数量，初期结存数量
     */
    private Long clearingPrevQuantity;

    /**
     * 结算 前 金额，初期结存金额
     */
    private Double clearingPrevAmount;

    /**
     * 本期入库数量
     */
    private Long curInStockQuantity;

    /**
     * 本期入库金额
     */
    private Double curInStockAmount;

    /**
     * 本期出库数量
     */
    private Long curOutStockQuantity;

    /**
     * 本期出库金额
     */
    private Double curOutStockAmount;

    /**
     * 期末结存数量
     */
    private Long curStockQuantity;

    /**
     * 期末结存金额
     */
    private Double curStockAmount;

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

    public Long getClearingPrevQuantity() {
        return clearingPrevQuantity;
    }

    public void setClearingPrevQuantity(Long clearingPrevQuantity) {
        this.clearingPrevQuantity = clearingPrevQuantity;
    }

    public Double getClearingPrevAmount() {
        return clearingPrevAmount;
    }

    public void setClearingPrevAmount(Double clearingPrevAmount) {
        this.clearingPrevAmount = clearingPrevAmount;
    }

    public Long getCurInStockQuantity() {
        return curInStockQuantity;
    }

    public void setCurInStockQuantity(Long curInStockQuantity) {
        this.curInStockQuantity = curInStockQuantity;
    }

    public Double getCurInStockAmount() {
        return curInStockAmount;
    }

    public void setCurInStockAmount(Double curInStockAmount) {
        this.curInStockAmount = curInStockAmount;
    }

    public Long getCurOutStockQuantity() {
        return curOutStockQuantity;
    }

    public void setCurOutStockQuantity(Long curOutStockQuantity) {
        this.curOutStockQuantity = curOutStockQuantity;
    }

    public Double getCurOutStockAmount() {
        return curOutStockAmount;
    }

    public void setCurOutStockAmount(Double curOutStockAmount) {
        this.curOutStockAmount = curOutStockAmount;
    }

    public Long getCurStockQuantity() {
        return curStockQuantity;
    }

    public void setCurStockQuantity(Long curStockQuantity) {
        this.curStockQuantity = curStockQuantity;
    }

    public Double getCurStockAmount() {
        return curStockAmount;
    }

    public void setCurStockAmount(Double curStockAmount) {
        this.curStockAmount = curStockAmount;
    }
}
