package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by HWJ on 2017/8/5
 */
public class ComplainRecordSearchParam {
    /**
     * 门店ID
     */
    @NotNull
    private Long shopId;

    /**
     * 商品拼音/商品名称/通用名称/编码（拼音、名称支持模糊搜索）
     */
    private String searchFields;

    /**
     * 投诉人，顾客名称
     */
    private String customerName;

    /**
     * 创建开始时间
     */
    private String fromDateString;

    /**
     * 创建结束时间
     */
    private String toDateString;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getSearchFields() {
        return searchFields;
    }

    public void setSearchFields(String searchFields) {
        this.searchFields = searchFields;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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


    public Date getFromDate() {
        if(StringUtils.isNotBlank(getFromDateString())) {
            try {
                return DateTimeUtils.convertStringToDate(getFromDateString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public Date getToDate() {
        if(StringUtils.isNotBlank(getToDateString())) {
            try {
                Date toDate = DateTimeUtils.convertStringToDate(getToDateString());
                return DateTimeUtils.getDayEndTime(toDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
