package com.imall.iportal.core.shop.vo;

/**
 * Created by caidapao on 2017/8/18.
 */
public class GoodsEnableRecordPageVo {
    /**
     * 商品名称
     */
    private String goodsNm;
    /**
     * 商品编码
     */
    private String goodsCode;
    /**
     * 操作窗台
     */
    private String operationState;
    /**
     * 修改原因
     */
    private String reason;
    /**
     * 操作人
     */
    private String lastModifiedBy;
    /**
     * 操作时间
     */
    private String lastModifiedDateString;

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

    public String getOperationState() {
        return operationState;
    }

    public void setOperationState(String operationState) {
        this.operationState = operationState;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getLastModifiedDateString() {
        return lastModifiedDateString;
    }

    public void setLastModifiedDateString(String lastModifiedDateString) {
        this.lastModifiedDateString = lastModifiedDateString;
    }
}
