package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by frt on 2017/8/4.
 */
public class DrugCuringRecordSearchParam {
    /**
     *门店 ID
     */
    @NotNull
    private Long shopId;

    private String keyword;

    /**
     * 养护 计划 单号
     */
    private String curingPlanNum;

    /**
     *养护 日期
     */
    private String startCuringDateString;

    /**
     *养护 日期
     */
    private String endCuringDateString;

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

    public String getCuringPlanNum() {
        return curingPlanNum;
    }

    public void setCuringPlanNum(String curingPlanNum) {
        this.curingPlanNum = curingPlanNum;
    }

    public String getStartCuringDateString() {
        return startCuringDateString;
    }

    public Date getStartCuringDate(){
        if(StringUtils.isBlank(this.getStartCuringDateString())){
            return null;
        }else{
            return DateTimeUtils.getDayStartTime(this.getStartCuringDateString());
        }
    }

    public void setStartCuringDateString(String startCuringDateString) {
        this.startCuringDateString = startCuringDateString;
    }

    public String getEndCuringDateString() {
        return endCuringDateString;
    }

    public Date getEndCuringDate(){
        if(StringUtils.isBlank(this.getEndCuringDateString())){
            return null;
        }else{
            return DateTimeUtils.getDayEndTime(this.getEndCuringDateString());
        }
    }

    public void setEndCuringDateString(String endCuringDateString) {
        this.endCuringDateString = endCuringDateString;
    }
}
