package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by frt on 2017/7/24.
 */
public class ShiftRecordSearchParam {
    /**
     * 门店 ID
     */
    @NotNull
    private Long shopId;

    /**
     * 收款员
     */
    private String posManName;

    /**
     * 销售开始时间
     */
    private String formCreateDateString;

    /**
     * 销售结束时间
     */
    private String toCreateDateString;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getPosManName() {
        return posManName;
    }

    public void setPosManName(String posManName) {
        this.posManName = posManName;
    }

    public String getFormCreateDateString() {
        return formCreateDateString;
    }

    public void setFormCreateDateString(String formCreateDateString) {
        this.formCreateDateString = formCreateDateString;
    }

    public String getToCreateDateString() {
        return toCreateDateString;
    }

    public void setToCreateDateString(String toCreateDateString) {
        this.toCreateDateString = toCreateDateString;
    }

    public Date getFormCreateDate(){
        if(StringUtils.isBlank(this.getFormCreateDateString())){
            return null;
        }else{
            return DateTimeUtils.getDayStartTime(this.getFormCreateDateString());
        }
    }

    public Date getToCreateDate(){
        if(StringUtils.isBlank(this.getToCreateDateString())){
            return null;
        }else{
            return DateTimeUtils.getDayEndTime(this.getToCreateDateString());
        }
    }
}
