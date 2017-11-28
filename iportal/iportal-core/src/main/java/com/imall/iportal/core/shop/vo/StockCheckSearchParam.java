package com.imall.iportal.core.shop.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by wsh on 2017/7/20.
 * 库存盘点 搜索参数
 */
public class StockCheckSearchParam {
    /**
     * 门店ID
     */
    @NotNull
    private Long shopId;
    /**
     * 盘点单号
     */
    private String checkOrderNum;
    /**
     * 盘点状态代码
     */
    private String checkedStateCode;
    /**
     * 创建时间(开始)
     */
    private String createDateBeginString;
    /**
     * 创建时间(结束)
     */
    private String createDateEndString;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getCheckOrderNum() {
        return checkOrderNum;
    }

    public void setCheckOrderNum(String checkOrderNum) {
        this.checkOrderNum = checkOrderNum;
    }

    public String getCheckedStateCode() {
        return checkedStateCode;
    }

    public void setCheckedStateCode(String checkedStateCode) {
        this.checkedStateCode = checkedStateCode;
    }

    public String getCreateDateBeginString() {
        return createDateBeginString;
    }

    public void setCreateDateBeginString(String createDateBeginString) {
        this.createDateBeginString = createDateBeginString;
    }

    public String getCreateDateEndString() {
        return createDateEndString;
    }

    public void setCreateDateEndString(String createDateEndString) {
        this.createDateEndString = createDateEndString;
    }
}
