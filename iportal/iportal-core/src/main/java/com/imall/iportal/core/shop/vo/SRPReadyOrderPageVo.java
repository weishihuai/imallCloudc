package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;

import java.util.Date;

/**
 * Created by ygw on 2017/8/7.
 * 销售POS前端--退货：准备退货的订单
 */
public class SRPReadyOrderPageVo extends OrderPageVo{

    private Date returnedTime;

    public Date getReturnedTime() {
        return returnedTime;
    }

    public void setReturnedTime(Date returnedTime) {
        this.returnedTime = returnedTime;
    }

    public String getReturnedTimeString(){
        if(this.getCreateDate()==null){
            return "";
        }else{
            return DateTimeUtils.convertDateTimeToMmString(getReturnedTime());
        }
    }
}
