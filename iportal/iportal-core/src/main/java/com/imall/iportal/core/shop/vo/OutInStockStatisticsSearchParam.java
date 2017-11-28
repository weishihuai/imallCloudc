package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by HWJ on 2017/7/12
 */
public class OutInStockStatisticsSearchParam {

    /**
     * 门店ID
     */
    @NotNull
    private Long shopId;

    /**
     * 开始时间
     */
    private String fromDateString;

    /**
     * 结束时间
     */
    private String toDateString;

    /**
     * 商品拼音/名称/编码（拼音、名称支持模糊搜索）
     */
    private String searchFields;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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


    public String getSearchFields() {
        return searchFields;
    }

    public void setSearchFields(String searchFields) {
        this.searchFields = searchFields;
    }
}
