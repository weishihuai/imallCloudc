package com.imall.iportal.frontend.wechat.order.proxy;

import java.util.List;

/**
 * 订单详情
 */
public class OrderDetailProxy {
    private Long id;

    /**
     * 微门店 ID
     */
    private Long weShopId;

    /**
     * 订单 编号
     */
    private String orderNum;

    /**
     * 支付 方式
     */
    private String paymentWayName;

    /**
     * 订单 状态 代码
     */
    private String orderStateCode;

    /**
     * 门店 名称
     */
    private String shopNm;

    /**
     * 运费
     */
    private Double freightAmount;

    /**
     * 订单 总 金额
     */
    private Double orderTotalAmount;

    /**
     * 送达日期
     */
    private String arrivedDay;

    /**
     * 送达时间
     */
    private String arrivedTime;

    /**
     * 配送 地址
     */
    private String deliveryAddr;

    /**
     * 详细 地址
     */
    private String detailAddr;

    /**
     * 收货人 姓名
     */
    private String receiverName;

    /**
     * 联系 电话
     */
    private String contactTel;

    /**
     * 创建时间
     */
    private String createDateString;

    /**
     * 关闭 原因
     */
    private String closeReasonName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 订单项
     */
    private List<OrderItemProxy> itemList;

    //门店联系电话
    private String shopContactTel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPaymentWayName() {
        return paymentWayName;
    }

    public void setPaymentWayName(String paymentWayName) {
        this.paymentWayName = paymentWayName;
    }


    public String getOrderStateCode() {
        return orderStateCode;
    }

    public void setOrderStateCode(String orderStateCode) {
        this.orderStateCode = orderStateCode;
    }

    public String getShopNm() {
        return shopNm;
    }

    public void setShopNm(String shopNm) {
        this.shopNm = shopNm;
    }

    public Double getFreightAmount() {
        return freightAmount;
    }

    public void setFreightAmount(Double freightAmount) {
        this.freightAmount = freightAmount;
    }

    public Double getOrderTotalAmount() {
        return orderTotalAmount;
    }

    public void setOrderTotalAmount(Double orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }

    public String getArrivedDay() {
        return arrivedDay;
    }

    public void setArrivedDay(String arrivedDay) {
        this.arrivedDay = arrivedDay;
    }

    public String getArrivedTime() {
        return arrivedTime;
    }

    public void setArrivedTime(String arrivedTime) {
        this.arrivedTime = arrivedTime;
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

    public String getCreateDateString() {
        return createDateString;
    }

    public void setCreateDateString(String createDateString) {
        this.createDateString = createDateString;
    }

    public List<OrderItemProxy> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItemProxy> itemList) {
        this.itemList = itemList;
    }

    public String getCloseReasonName() {
        return closeReasonName;
    }

    public void setCloseReasonName(String closeReasonName) {
        this.closeReasonName = closeReasonName;
    }

    public String getShopContactTel() {
        return shopContactTel;
    }

    public void setShopContactTel(String shopContactTel) {
        this.shopContactTel = shopContactTel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
