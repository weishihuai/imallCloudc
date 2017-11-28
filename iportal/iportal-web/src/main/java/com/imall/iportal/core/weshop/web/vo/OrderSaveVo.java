package com.imall.iportal.core.weshop.web.vo;

/**
 * Created by ygw on 2017/8/23.
 */
public class OrderSaveVo {

    private Boolean isToday;
    private String deliveryTime;
    private String remark;

    public Boolean getIsToday() {
        return this.isToday;
    }

    public void setIsToday(Boolean isToday) {
        this.isToday = isToday;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
