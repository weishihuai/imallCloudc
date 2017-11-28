package com.imall.iportal.core.shop.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by frt on 2017/7/7.
 */
public class DestroyRecordSearchParam {
    /**
     * 门店 ID
     */
    @NotNull
    private Long shopId;

    /**
     * 拼音码、商品名称、通用名称、商品编码
     */
    private String keyword;

    /**
     * 批号
     */
    private String batch;

    /**
     * 销毁时间 开始
     */
    private String fromDestroyTimeString;

    /**
     * 销毁时间 结束
     */
    private String toDestroyTimeString;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getFromDestroyTimeString() {
        return fromDestroyTimeString;
    }

    public void setFromDestroyTimeString(String fromDestroyTimeString) {
        this.fromDestroyTimeString = fromDestroyTimeString;
    }

    public String getToDestroyTimeString() {
        return toDestroyTimeString;
    }

    public void setToDestroyTimeString(String toDestroyTimeString) {
        this.toDestroyTimeString = toDestroyTimeString;
    }
}
