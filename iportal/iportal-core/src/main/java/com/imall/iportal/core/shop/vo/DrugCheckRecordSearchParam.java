package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by frt on 2017/8/4.
 */
public class DrugCheckRecordSearchParam {
    /**
     *门店 ID
     */
    @NotNull
    private Long shopId;

    private String keyword;

    /**
     * 检查 单码
     */
    private String checkNum;

    /**
     *检查 时间
     */
    private String startCheckTimeString;

    /**
     *检查 时间
     */
    private String endCheckTimeString;

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

    public String getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(String checkNum) {
        this.checkNum = checkNum;
    }

    public String getStartCheckTimeString() {
        return startCheckTimeString;
    }

    public Date getStartCheckTime(){
        if(StringUtils.isBlank(this.getStartCheckTimeString())){
            return null;
        }else{
            return DateTimeUtils.getDayStartTime(this.getStartCheckTimeString());
        }
    }

    public void setStartCheckTimeString(String startCheckTimeString) {
        this.startCheckTimeString = startCheckTimeString;
    }

    public String getEndCheckTimeString() {
        return endCheckTimeString;
    }

    public Date getEndCheckTime(){
        if(StringUtils.isBlank(this.getEndCheckTimeString())){
            return null;
        }else{
            return DateTimeUtils.getDayEndTime(this.getEndCheckTimeString());
        }
    }

    public void setEndCheckTimeString(String endCheckTimeString) {
        this.endCheckTimeString = endCheckTimeString;
    }
}
