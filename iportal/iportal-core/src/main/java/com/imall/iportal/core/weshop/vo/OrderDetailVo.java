package com.imall.iportal.core.weshop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.DeliveryTypeCodeEnum;
import com.imall.commons.dicts.OrderStateCodeEnum;
import com.imall.commons.dicts.PayWayTypeCodeEnum;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * 订单详情
 */
public class OrderDetailVo {
    private Long id;

    /**
     * 门店 ID
     */
    private Long shopId;

    /**
     * 微门店 ID
     */
    private Long weShopId;

    /**
     * 订单 编号
     */
    private String orderNum;

    /**
     * 商品 总 金额
     */
    private Double goodsTotalAmount;

    /**
     * 订单 总 金额
     */
    private Double orderTotalAmount;

    /**
     * 备注
     */
    private String remark;

    /**
     *  管理员 备注
     */
    private String adminRemark;

    /**
     * 运费
     */
    private Double freightAmount;

    /**
     * 支付 方式
     */
    private String paymentWayName;

    /**
     * 订单 状态 代码
     */
    private String orderStateCode;

    /**
     * 是否 已支付
     */
    private String isPayed;

    /**
     * 是否 货到 付款
     */
    private String isCod;

    /**
     * 发货 时间
     */
    private String orderConfirmTimeString;

    /**
     * 订单 完成 时间
     */
    private String finishTimeString;

    /**
     * 预约 送达 时间 开始
     */
    private String bookDeliveryTimeStartString;

    private Date bookDeliveryTimeStart;

    /**
     * 预约 送达 时间 结束
     */
    private String bookDeliveryTimeEndString;

    private Date bookDeliveryTimeEnd;

    /**
     * 关闭 原因
     */
    private String cancelOrderReason;

    /**
     * 订单 关闭 时间
     */
    private String orderCloseTimeString;

    /**
     * 收货人 姓名
     */
    private String receiverName;

    /**
     * 联系 电话
     */
    private String contactTel;

    /**
     * 配送 地址
     */
    private String deliveryAddr;

    /**
     * 详细 地址
     */
    private String detailAddr;

    /**
     * 配送 类型
     */
    private String deliveryTypeName;

    /**
     * 创建时间
     */
    private String createDateString;

    /**
     * 订单项信息
     */
    private List<OrderItemDetailVo> orderItemDetailVoList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getWeShopId() {
        return weShopId;
    }

    public void setWeShopId(Long weShopId) {
        this.weShopId = weShopId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Double getGoodsTotalAmount() {
        return goodsTotalAmount;
    }

    public void setGoodsTotalAmount(Double goodsTotalAmount) {
        this.goodsTotalAmount = goodsTotalAmount;
    }

    public Double getOrderTotalAmount() {
        return orderTotalAmount;
    }

    public void setOrderTotalAmount(Double orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getFreightAmount() {
        return freightAmount;
    }

    public void setFreightAmount(Double freightAmount) {
        this.freightAmount = freightAmount;
    }

    public String getPaymentWayName() {
        return paymentWayName;
    }

    public void setPaymentWayName(String paymentWayName) {
        this.paymentWayName = paymentWayName;
    }

    public void setPaymentWayCode(String paymentWayCode){
        if(StringUtils.isBlank(paymentWayCode)){
            this.setPaymentWayName("");
        }else{
            this.setPaymentWayName(PayWayTypeCodeEnum.fromCode(paymentWayCode).toName());
        }
    }

    public String getOrderStateCode() {
        return orderStateCode;
    }

    public String getOrderStateName() {
        if(StringUtils.isBlank(this.getOrderStateCode())){
            return "";
        }else{
            return OrderStateCodeEnum.fromCode(this.getOrderStateCode()).toName();
        }
    }

    public void setOrderStateCode(String orderStateCode) {
        this.orderStateCode = orderStateCode;
    }

    public String getIsPayed() {
        return isPayed;
    }

    public void setIsPayed(String isPayed) {
        this.isPayed = isPayed;
    }

    public String getIsCod() {
        return isCod;
    }

    public void setIsCod(String isCod) {
        this.isCod = isCod;
    }

    public String getOrderConfirmTimeString() {
        return orderConfirmTimeString;
    }

    public void setOrderConfirmTime(Date orderConfirmTime){
        if(orderConfirmTime==null){
            this.orderConfirmTimeString = "";
        }else{
            this.orderConfirmTimeString = DateTimeUtils.convertDateTimeToMmString(orderConfirmTime);
        }
    }

    public String getFinishTimeString() {
        return finishTimeString;
    }

    public void setFinishTime(Date finishTime){
        if(finishTime==null){
            this.finishTimeString = "";
        }else{
            this.finishTimeString = DateTimeUtils.convertDateTimeToMmString(finishTime);
        }
    }

    public String getBookDeliveryTimeStartString() {
        return bookDeliveryTimeStartString;
    }

    public void setBookDeliveryTimeStart(Date bookDeliveryTimeStart){
        this.bookDeliveryTimeStart = bookDeliveryTimeStart;
        if(bookDeliveryTimeStart==null){
            this.bookDeliveryTimeStartString = "";
        }else{
            this.bookDeliveryTimeStartString = DateTimeUtils.convertDateTimeToMmString(bookDeliveryTimeStart);
        }
    }

    public Date getBookDeliveryTimeStart() {
        return bookDeliveryTimeStart;
    }

    public String getBookDeliveryTimeEndString() {
        return bookDeliveryTimeEndString;
    }

    public void setBookDeliveryTimeEnd(Date bookDeliveryTimeEnd){
        this.bookDeliveryTimeEnd = bookDeliveryTimeEnd;
        if(bookDeliveryTimeEnd==null){
            this.bookDeliveryTimeEndString = "";
        }else{
            this.bookDeliveryTimeEndString = DateTimeUtils.convertMmTimeToString(bookDeliveryTimeEnd);
        }
    }

    public Date getBookDeliveryTimeEnd() {
        return bookDeliveryTimeEnd;
    }

    public String getCancelOrderReason() {
        return cancelOrderReason;
    }

    public void setCancelOrderReason(String cancelOrderReason) {
        this.cancelOrderReason = cancelOrderReason;
    }

    public String getOrderCloseTimeString() {
        return orderCloseTimeString;
    }

    public void setOrderCloseTime(Date orderCloseTime){
        if(orderCloseTime==null){
            this.orderCloseTimeString = "";
        }else{
            this.orderCloseTimeString = DateTimeUtils.convertDateTimeToMmString(orderCloseTime);
        }
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

    public String getDeliveryAddr() {
        return deliveryAddr;
    }

    public void setDeliveryAddr(String deliveryAddr) {
        this.deliveryAddr = deliveryAddr;
    }

    public String getDetailAddr() {
        return detailAddr;
    }

    public void setDetailAddr(String detailAddr) {
        this.detailAddr = detailAddr;
    }

    public String getDeliveryTypeName() {
        return deliveryTypeName;
    }

    public void setDeliveryTypeName(String deliveryTypeName) {
        this.deliveryTypeName = deliveryTypeName;
    }

    public void setDeliveryTypeCode(String deliveryTypeCode) {
        if(StringUtils.isBlank(deliveryTypeCode)){
            this.setDeliveryTypeName("");
        }else{
            this.setDeliveryTypeName(DeliveryTypeCodeEnum.fromCode(deliveryTypeCode).toName());
        }
    }

    public String getCreateDateString() {
        return createDateString;
    }

    public void setCreateDate(Date createDate){
        if(createDate==null){
            this.createDateString = "";
        }else{
            this.createDateString = DateTimeUtils.convertDateTimeToMmString(createDate);
        }
    }

    public List<OrderItemDetailVo> getOrderItemDetailVoList() {
        return orderItemDetailVoList;
    }

    public void setOrderItemDetailVoList(List<OrderItemDetailVo> orderItemDetailVoList) {
        this.orderItemDetailVoList = orderItemDetailVoList;
    }

    public String getAdminRemark() {
        return adminRemark;
    }

    public void setAdminRemark(String adminRemark) {
        this.adminRemark = adminRemark;
    }
}
