package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by frt on 2017/7/15.
 */
public class DrugCuringSearchParam {
    /**
     * 门店 ID
     */
    @NotNull
    private Long shopId;

    /**
     * 养护 类型
     */
    private String curingTypeCode;

    /**
     * 养护 单据 号码
     */
    private String curingDocumentNum;

    /**
     * 计划养护开始时间
     */
    private String fromPlanCuringTimeString;

    /**
     * 计划养护结束时间
     */
    private String toPlanCuringTimeString;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getCuringTypeCode() {
        return curingTypeCode;
    }

    public void setCuringTypeCode(String curingTypeCode) {
        this.curingTypeCode = curingTypeCode;
    }

    public String getCuringDocumentNum() {
        return curingDocumentNum;
    }

    public void setCuringDocumentNum(String curingDocumentNum) {
        this.curingDocumentNum = curingDocumentNum;
    }

    public String getFromPlanCuringTimeString() {
        return fromPlanCuringTimeString;
    }

    public void setFromPlanCuringTimeString(String fromPlanCuringTimeString) {
        this.fromPlanCuringTimeString = fromPlanCuringTimeString;
    }

    public String getToPlanCuringTimeString() {
        return toPlanCuringTimeString;
    }

    public void setToPlanCuringTimeString(String toPlanCuringTimeString) {
        this.toPlanCuringTimeString = toPlanCuringTimeString;
    }

    public Date getFromPlanCuringTime() {
        if(StringUtils.isNotBlank(this.getFromPlanCuringTimeString())){
            return DateTimeUtils.getDayStartTime(this.getFromPlanCuringTimeString());
        }else{
            return null;
        }
    }

    public Date getToPlanCuringTime() {
        if(StringUtils.isNotBlank(this.getToPlanCuringTimeString())){
            return DateTimeUtils.getDayEndTime(this.getToPlanCuringTimeString());
        }else{
            return null;
        }
    }
}
