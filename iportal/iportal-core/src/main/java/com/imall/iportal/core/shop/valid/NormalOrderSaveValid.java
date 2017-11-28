package com.imall.iportal.core.shop.valid;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by ygw on 2017/7/17.
 */
public class NormalOrderSaveValid {

    /** 预约 送达 时间 开始, 如果是null 表示尽快送达*/
    private Date bookDeliveryTimeStart;

    /**  预约 送达 时间 结束, 如果是null 表示尽快送达*/
    private Date bookDeliveryTimeEnd;

    @Length(max = 255)
    private String remark;

    public Date getBookDeliveryTimeStart() {
        return bookDeliveryTimeStart;
    }

    public void setBookDeliveryTimeStart(Date bookDeliveryTimeStart) {
        this.bookDeliveryTimeStart = bookDeliveryTimeStart;
    }

    public Date getBookDeliveryTimeEnd() {
        return bookDeliveryTimeEnd;
    }

    public void setBookDeliveryTimeEnd(Date bookDeliveryTimeEnd) {
        this.bookDeliveryTimeEnd = bookDeliveryTimeEnd;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
