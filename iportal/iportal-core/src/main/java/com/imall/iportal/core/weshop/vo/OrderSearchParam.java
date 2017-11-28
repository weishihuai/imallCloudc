package com.imall.iportal.core.weshop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by frt on 2017/8/14.
 */
public class OrderSearchParam {
    /**
     * 微门店 ID
     */
    @NotNull
    private java.lang.Long weShopId;

    /**
     * 订单 来源 代码
     */
    @NotBlank
    private java.lang.String orderSourceCode;

    /**
     * 订单 状态 代码
     */
    private java.lang.String orderStateCode;

    /**
     * 商品编码/通用名称/通用名称首字母/商品名称
     */
    private String keyword;

    /**
     * 收货人 姓名
     */
    private java.lang.String receiverName;

    /**
     * 联系 电话
     */
    private java.lang.String contactTel;

    /**
     * 订单 编号
     */
    private java.lang.String orderNum;

    /**
     * 下单开始时间
     */
    private String startCreateDateString;

    /**
     * 下单结束时间
     */
    private String endCreateDateString;

    /**
     * 预约 送达 时间 开始
     */
    private String bookDeliveryTimeStartString;

    /**
     * 预约 送达 时间 结束
     */
    private String bookDeliveryTimeEndString;

    /**
     * 支付 方式 代码
     */
    private java.lang.String paymentWayCode;

    /**
     *微信 用户 ID
     */
    private java.lang.Long wechatUserId;

    public Long getWeShopId() {
        return weShopId;
    }

    public void setWeShopId(Long weShopId) {
        this.weShopId = weShopId;
    }

    public String getOrderSourceCode() {
        return orderSourceCode;
    }

    public void setOrderSourceCode(String orderSourceCode) {
        this.orderSourceCode = orderSourceCode;
    }

    public String getOrderStateCode() {
        return orderStateCode;
    }

    public void setOrderStateCode(String orderStateCode) {
        this.orderStateCode = orderStateCode;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getStartCreateDateString() {
        return startCreateDateString;
    }

    public Date getStartCreateDate() throws ParseException {
        if(StringUtils.isBlank(this.getStartCreateDateString())){
            return null;
        }else{
            return DateTimeUtils.getDayStartTime(this.getStartCreateDateString());
        }
    }

    public void setStartCreateDateString(String startCreateDateString) {
        this.startCreateDateString = startCreateDateString;
    }

    public String getEndCreateDateString() {
        return endCreateDateString;
    }

    public Date getEndCreateDate() throws ParseException {
        if(StringUtils.isBlank(this.getEndCreateDateString())){
            return null;
        }else{
            return DateTimeUtils.getDayEndTime(this.getEndCreateDateString());
        }
    }

    public void setEndCreateDateString(String endCreateDateString) {
        this.endCreateDateString = endCreateDateString;
    }

    public String getBookDeliveryTimeStartString() {
        return bookDeliveryTimeStartString;
    }

    public Date getBookDeliveryTimeStart() throws ParseException {
        if(StringUtils.isBlank(this.getBookDeliveryTimeStartString())){
            return null;
        }else{
            return DateTimeUtils.getDayStartTime(this.getBookDeliveryTimeStartString());
        }
    }

    public void setBookDeliveryTimeStartString(String bookDeliveryTimeStartString) {
        this.bookDeliveryTimeStartString = bookDeliveryTimeStartString;
    }

    public String getBookDeliveryTimeEndString() {
        return bookDeliveryTimeEndString;
    }

    public Date getBookDeliveryTimeEnd() throws ParseException {
        if(StringUtils.isBlank(this.getBookDeliveryTimeEndString())){
            return null;
        }else{
            return DateTimeUtils.getDayEndTime(this.getBookDeliveryTimeEndString());
        }
    }

    public void setBookDeliveryTimeEndString(String bookDeliveryTimeEndString) {
        this.bookDeliveryTimeEndString = bookDeliveryTimeEndString;
    }

    public String getPaymentWayCode() {
        return paymentWayCode;
    }

    public void setPaymentWayCode(String paymentWayCode) {
        this.paymentWayCode = paymentWayCode;
    }

    public Long getWechatUserId() {
        return wechatUserId;
    }

    public void setWechatUserId(Long wechatUserId) {
        this.wechatUserId = wechatUserId;
    }
}
