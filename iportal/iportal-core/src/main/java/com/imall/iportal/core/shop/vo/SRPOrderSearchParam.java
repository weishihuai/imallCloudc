package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by ygw on 2017/8/1.
 * 销售退货订单搜索
 * SellReturnedPurchaseOrderSearchParam
 */
public class SRPOrderSearchParam {

    @NotNull
    private Long shopId;

    //收款员
    private Long cashierId;

    private String fromReturnedPurchaseTimeString;

    private String toReturnedPurchaseTimeString;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getCashierId() {
        return cashierId;
    }

    public void setCashierId(Long cashierId) {
        this.cashierId = cashierId;
    }

    public String getFromReturnedPurchaseTimeString() {
        return fromReturnedPurchaseTimeString;
    }

    public void setFromReturnedPurchaseTimeString(String fromReturnedPurchaseTimeString) {
        this.fromReturnedPurchaseTimeString = fromReturnedPurchaseTimeString;
    }

    public String getToReturnedPurchaseTimeString() {
        return toReturnedPurchaseTimeString;
    }

    public void setToReturnedPurchaseTimeString(String toReturnedPurchaseTimeString) {
        this.toReturnedPurchaseTimeString = toReturnedPurchaseTimeString;
    }

    public Date getFormReturnedPurchaseTime(){
        if(StringUtils.isBlank(this.getFromReturnedPurchaseTimeString())){
            return null;
        }else{
            return DateTimeUtils.getDayStartTime(this.getFromReturnedPurchaseTimeString());
        }
    }

    public Date getToReturnedPurchaseTime(){
        if(StringUtils.isBlank(this.getToReturnedPurchaseTimeString())){
            return null;
        }else{
            return DateTimeUtils.getDayEndTime(this.getToReturnedPurchaseTimeString());
        }
    }
}
