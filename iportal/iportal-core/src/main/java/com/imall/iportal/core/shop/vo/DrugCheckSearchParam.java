package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by frt on 2017/7/15.
 */
public class DrugCheckSearchParam {
    /**
     * 门店 ID
     */
    @NotNull
    private Long shopId;

    /**
     * 检查 类型 代码
     */
    private String checkTypeCode;

    /**
     * 检查 单据 号码
     */
    private String checkDocumentNum;

    /**
     * 计划检查开始时间
     */
    private String fromPlanCheckTimeString;

    /**
     * 计划检查结束时间
     */
    private String toPlanCheckTimeString;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getCheckDocumentNum() {
        return checkDocumentNum;
    }

    public void setCheckDocumentNum(String checkDocumentNum) {
        this.checkDocumentNum = checkDocumentNum;
    }

    public String getCheckTypeCode() {
        return checkTypeCode;
    }

    public void setCheckTypeCode(String checkTypeCode) {
        this.checkTypeCode = checkTypeCode;
    }

    public String getFromPlanCheckTimeString() {
        return fromPlanCheckTimeString;
    }

    public void setFromPlanCheckTimeString(String fromPlanCheckTimeString) {
        this.fromPlanCheckTimeString = fromPlanCheckTimeString;
    }

    public String getToPlanCheckTimeString() {
        return toPlanCheckTimeString;
    }

    public void setToPlanCheckTimeString(String toPlanCheckTimeString) {
        this.toPlanCheckTimeString = toPlanCheckTimeString;
    }

    public Date getFromPlanCheckTime() {
        if(StringUtils.isNotBlank(this.getFromPlanCheckTimeString())){
            return DateTimeUtils.getDayStartTime(this.getFromPlanCheckTimeString());
        }else{
            return null;
        }
    }

    public Date getToPlanCheckTime() {
        if(StringUtils.isNotBlank(this.getToPlanCheckTimeString())){
            return DateTimeUtils.getDayEndTime(this.getToPlanCheckTimeString());
        }else{
            return null;
        }
    }
}
