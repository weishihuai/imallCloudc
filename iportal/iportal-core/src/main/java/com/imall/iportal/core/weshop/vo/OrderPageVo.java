package com.imall.iportal.core.weshop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by frt on 2017/8/14.
 */
public class OrderPageVo {
    private Long id;

    /**
     * 微门店 ID
     */
    private Long weShopId;

    /**
     * 订单 编号
     */
    private java.lang.String orderNum;

    /**
     * 下单时间
     */
    private Date createDate;

    /**
     * 预约 送达 时间 开始
     */
    private java.util.Date bookDeliveryTimeStart;

    /**
     * 预约 送达 时间 结束
     */
    private java.util.Date bookDeliveryTimeEnd;

    /**
     * 订单 状态 代码
     */
    private java.lang.String orderStateCode;

    /**
     * 关闭 原因
     */
    private java.lang.String cancelOrderReason;

    /**
     * 收货人 姓名
     */
    private java.lang.String receiverName;

    /**
     * 联系 电话
     */
    private java.lang.String contactTel;

    /**
     * 订单 总 金额
     */
    private java.lang.Double orderTotalAmount;

    /**
     * 运费
     */
    private java.lang.Double freightAmount;

    /**
     * 备注
     */
    private java.lang.String remark;

    /**
     * 管理员 备注
     */
    private java.lang.String adminRemark;

    /**
     * 订单项
     */
    private List<OrderItemDetailVo> orderItemDetailVoList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getCreateDateString(){
        if(this.getCreateDate()==null){
            return "";
        }else{
            return DateTimeUtils.convertDateTimeToString(this.getCreateDate());
        }
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getBookDeliveryTimeStart() {
        return bookDeliveryTimeStart;
    }

    public String getBookDeliveryDateString(){
        if(this.getBookDeliveryTimeStart()==null){
            return "";
        }else{
            return DateTimeUtils.convertDateToString(this.getBookDeliveryTimeStart());
        }
    }

    public String getBookDeliveryTimeStartString(){
        if(this.getBookDeliveryTimeStart()==null){
            return "";
        }else{
            return DateTimeUtils.convertMmTimeToString(this.getBookDeliveryTimeStart());
        }
    }

    public void setBookDeliveryTimeStart(Date bookDeliveryTimeStart) {
        this.bookDeliveryTimeStart = bookDeliveryTimeStart;
    }

    public Date getBookDeliveryTimeEnd() {
        return bookDeliveryTimeEnd;
    }

    public String getBookDeliveryTimeEndString(){
        if(this.getBookDeliveryTimeEnd()==null){
            return "";
        }else{
            return DateTimeUtils.convertMmTimeToString(this.getBookDeliveryTimeEnd());
        }
    }

    public void setBookDeliveryTimeEnd(Date bookDeliveryTimeEnd) {
        this.bookDeliveryTimeEnd = bookDeliveryTimeEnd;
    }

    public String getOrderStateCode() {
        return orderStateCode;
    }

    public void setOrderStateCode(String orderStateCode) {
        this.orderStateCode = orderStateCode;
    }

    public String getCancelOrderReason() {
        return cancelOrderReason;
    }

    public void setCancelOrderReason(String cancelOrderReason) {
        this.cancelOrderReason = cancelOrderReason;
    }

    public Double getOrderTotalAmount() {
        return orderTotalAmount;
    }

    public void setOrderTotalAmount(Double orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }

    public Double getFreightAmount() {
        return freightAmount;
    }

    public void setFreightAmount(Double freightAmount) {
        this.freightAmount = freightAmount;
    }

    public String getRemark() {
        if(StringUtils.isNotBlank(remark) && remark.length()>130){
            return remark.substring(0, 125) + "...";
        }else{
            return remark;
        }
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<OrderItemDetailVo> getOrderItemDetailVoList() {
        return orderItemDetailVoList;
    }

    public void setOrderItemDetailVoList(List<OrderItemDetailVo> orderItemDetailVoList) {
        this.orderItemDetailVoList = orderItemDetailVoList;
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

    public Long getWeShopId() {
        return weShopId;
    }

    public void setWeShopId(Long weShopId) {
        this.weShopId = weShopId;
    }

    public String getAdminRemark() {
        return adminRemark;
    }

    public void setAdminRemark(String adminRemark) {
        this.adminRemark = adminRemark;
    }
}
