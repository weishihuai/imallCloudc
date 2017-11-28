package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by ou on 2017/7/6.
 */
public class GoodsSplitZeroSearchParam {
    /**
     * 门店 ID
     */
    @NotNull
    private Long shopId;

    /**
    *搜索名称
     */
    private String keyword;

    /**
     *开始时间
     */
    private String startTimeString;

    /**
     * 结束时间
     */
    private String endTimeString;

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

    public String getStartTimeString() {
        return startTimeString;
    }

    public void setStartTimeString(String startTimeString) {
        this.startTimeString = startTimeString;
    }

    public String getEndTimeString() {
        return endTimeString;
    }

    public void setEndTimeString(String endTimeString) {
        this.endTimeString = endTimeString;
    }

    public Date getStartTime(){
        if(StringUtils.isEmpty(this.getStartTimeString())){
            return null;
        }else{
            return DateTimeUtils.getDayStartTime(this.getStartTimeString());
        }
    }

    public Date getEndTime(){
        if(StringUtils.isEmpty(this.getEndTimeString())){
            return null;
        }else{
            return DateTimeUtils.getDayEndTime(this.getEndTimeString());
        }
    }
}
