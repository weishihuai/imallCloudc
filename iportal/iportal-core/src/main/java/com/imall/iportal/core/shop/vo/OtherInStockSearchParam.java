package com.imall.iportal.core.shop.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by wsh on 2017/7/29.
 * 其他入库 搜索参数
 */
public class OtherInStockSearchParam {
    /**
     * 门店ID
     */
    @NotNull
    private Long shopId;
    /**
     * 入库类型代码
     */
    private String typeCode;
    /**
     * 入库人
     */
    private String searchFields;
    /**
     * 入库单号
     */
    private String inStockCode;
    /**
     * 入库时间（开始）
     */
    private String inStockTimeStartString;
    /**
     * 入库时间（结束）
     */
    private String inStockTimeEndString;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getSearchFields() {
        return searchFields;
    }

    public void setSearchFields(String searchFields) {
        this.searchFields = searchFields;
    }

    public String getInStockCode() {
        return inStockCode;
    }

    public void setInStockCode(String inStockCode) {
        this.inStockCode = inStockCode;
    }

    public String getInStockTimeStartString() {
        return inStockTimeStartString;
    }

    public void setInStockTimeStartString(String inStockTimeStartString) {
        this.inStockTimeStartString = inStockTimeStartString;
    }

    public String getInStockTimeEndString() {
        return inStockTimeEndString;
    }

    public void setInStockTimeEndString(String inStockTimeEndString) {
        this.inStockTimeEndString = inStockTimeEndString;
    }
}
