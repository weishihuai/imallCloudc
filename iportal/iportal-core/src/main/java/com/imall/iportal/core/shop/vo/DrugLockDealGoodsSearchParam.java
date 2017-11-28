package com.imall.iportal.core.shop.vo;

/**
 * Created by wsh on 2017/8/3.
 * 锁定商品列表 搜索参数
 */
public class DrugLockDealGoodsSearchParam {
    /**
     * 商品名称
     */
    private String goodsNm;
    /**
     * 商品编码
     */
    private String goodsCode;
    /**
     * 批号
     */
    private String batch;

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getGoodsNm() {
        return goodsNm;
    }

    public void setGoodsNm(String goodsNm) {
        this.goodsNm = goodsNm;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

}
