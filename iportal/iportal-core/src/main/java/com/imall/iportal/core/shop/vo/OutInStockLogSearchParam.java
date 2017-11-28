package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by HWJ on 2017/7/12
 */
public class OutInStockLogSearchParam {

    /**
     * 门店ID
     */
    @NotNull
    private Long shopId;

    /**
     * 商品拼音/名称/编码（拼音、名称支持模糊搜索）
     */
    private String searchFields;

    /**
     * 批号
     */
    private String batch;

    /**
     * 创建开始时间
     */
    private String fromDateString;

    /**
     * 创建结束时间
     */
    private String toDateString;

    /**
     * 库存类型
     */
    private String logSourceTypeCode;

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

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
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

    public String getLogSourceTypeCode() {
        return logSourceTypeCode;
    }

    public void setLogSourceTypeCode(String logSourceTypeCode) {
        this.logSourceTypeCode = logSourceTypeCode;
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
