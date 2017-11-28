package com.imall.iportal.core.shop.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by wsh on 2017/7/27.
 * 其他出库 搜索参数
 */
public class OtherOutStockSearchParam {
    /**
     * 门店ID
     */
    @NotNull
    private Long shopId;
    /**
     * 出库类型代码
     */
    private String typeCode;
    /**
     * 出库人
     */
    private String searchFields;
    /**
     * 出库单号
     */
    private String outStockCode;
    /**
     * 出库时间（开始）
     */
    private String outStockTimeStartString;
    /**
     * 出库时间（结束）
     */
    private String outStockTimeEndString;


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

    public String getOutStockCode() {
        return outStockCode;
    }

    public void setOutStockCode(String outStockCode) {
        this.outStockCode = outStockCode;
    }

    public String getOutStockTimeStartString() {
        return outStockTimeStartString;
    }

    public void setOutStockTimeStartString(String outStockTimeStartString) {
        this.outStockTimeStartString = outStockTimeStartString;
    }

    public String getOutStockTimeEndString() {
        return outStockTimeEndString;
    }

    public void setOutStockTimeEndString(String outStockTimeEndString) {
        this.outStockTimeEndString = outStockTimeEndString;
    }
}
