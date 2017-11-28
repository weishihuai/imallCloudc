package com.imall.iportal.core.platform.vo;

/**
 * Created by caidapao on 2017/7/6.
 * 商品档案列表搜索参数
 */
public class GoodsDocListSearchParam {

    /**
     * 是否启用
     */
    private String isEnable;

    /**
     * 审核状态
     */
    private String approveStateCode;

    /**
     * 商品分类id
     */
    private Long goodsCategoryId;


    /**
     * 拼音/商品编码/商品名称/通用名称
     */
    private String keyWords;

    /**
     * 创建时间
     */
    private String fromDateString;
    private String toDateString;


    //以下为门店选择商品档案时的列表搜索参数
    /**
     * 商品名称
     */
    private String docGoodsNm;

    /**
     * 批准文号
     */
    private String docApproveNumber;

    /**
     * 生产厂家
     */
    private String docProduceManufacturer;

    /**
     * 是否是商品新增 选择档案列表
     */
    private String isGoodsAdd;

    public Long getGoodsCategoryId() {
        return goodsCategoryId;
    }

    public void setGoodsCategoryId(Long goodsCategoryId) {
        this.goodsCategoryId = goodsCategoryId;
    }

    public String getIsGoodsAdd() {
        return isGoodsAdd;
    }

    public void setIsGoodsAdd(String isGoodsAdd) {
        this.isGoodsAdd = isGoodsAdd;
    }

    public String getDocGoodsNm() {
        return docGoodsNm;
    }

    public void setDocGoodsNm(String docGoodsNm) {
        this.docGoodsNm = docGoodsNm;
    }

    public String getDocApproveNumber() {
        return docApproveNumber;
    }

    public void setDocApproveNumber(String docApproveNumber) {
        this.docApproveNumber = docApproveNumber;
    }

    public String getDocProduceManufacturer() {
        return docProduceManufacturer;
    }

    public void setDocProduceManufacturer(String docProduceManufacturer) {
        this.docProduceManufacturer = docProduceManufacturer;
    }

    public String getApproveStateCode() {
        return approveStateCode;
    }

    public void setApproveStateCode(String approveStateCode) {
        this.approveStateCode = approveStateCode;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getFromDateString() {
        return fromDateString;
    }

    public void setFromDateString(String fromDateString) {
        this.fromDateString = fromDateString;
    }

    public String getToDateString() {
        return toDateString;
    }

    public void setToDateString(String toDateString) {
        this.toDateString = toDateString;
    }
}
