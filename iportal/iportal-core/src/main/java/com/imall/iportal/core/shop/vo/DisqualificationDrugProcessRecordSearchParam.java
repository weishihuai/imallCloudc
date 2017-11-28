package com.imall.iportal.core.shop.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by wsh on 2017/8/10.
 * 不合格药品处理记录  搜索参数
 */
public class DisqualificationDrugProcessRecordSearchParam {
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
     * 日期 (开始时间)
     */
    private String recordDateStartTimeString;
    /**
     * 日期 (结束时间)
     */
    private String recordDateEndTimeString;

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

    public String getRecordDateStartTimeString() {
        return recordDateStartTimeString;
    }

    public void setRecordDateStartTimeString(String recordDateStartTimeString) {
        this.recordDateStartTimeString = recordDateStartTimeString;
    }

    public String getRecordDateEndTimeString() {
        return recordDateEndTimeString;
    }

    public void setRecordDateEndTimeString(String recordDateEndTimeString) {
        this.recordDateEndTimeString = recordDateEndTimeString;
    }
}
