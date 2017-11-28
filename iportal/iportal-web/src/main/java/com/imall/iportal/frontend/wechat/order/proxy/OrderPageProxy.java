package com.imall.iportal.frontend.wechat.order.proxy;

import com.imall.commons.dicts.OrderStateCodeEnum;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * 订单
 */
public class OrderPageProxy {
    private Long id;

    /**
     * 微门店 ID
     */
    private Long weShopId;

    /**
     * 门店 名称
     */
    private String shopNm;

    /**
     * 订单 状态 代码
     */
    private String orderStateCode;

    /**
     * 关闭 原因
     */
    private String closeReasonName;

    /**
     * 支付 方式
     */
    private String paymentWayName;

    /**
     * 订单 总 金额
     */
    private Double orderTotalAmount;

    /**
     * 订单商品总数量
     */
    private Long totalQuantity;

    /**
     * 订单项
     */
    private List<OrderItemProxy> itemList;

    //下单时间
    private String createDateString;

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

    public String getShopNm() {
        return shopNm;
    }

    public void setShopNm(String shopNm) {
        this.shopNm = shopNm;
    }

    public String getOrderStateCode() {
        return orderStateCode;
    }

    public String getOrderStateName(){
        if(StringUtils.isBlank(this.orderStateCode)){
            return "";
        }else{
            return OrderStateCodeEnum.fromCode(this.orderStateCode).toName();
        }
    }

    public void setOrderStateCode(String orderStateCode) {
        this.orderStateCode = orderStateCode;
    }

    public Double getOrderTotalAmount() {
        return orderTotalAmount;
    }

    public void setOrderTotalAmount(Double orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }

    public List<OrderItemProxy> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItemProxy> itemList) {
        this.itemList = itemList;
    }

    public Long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getPaymentWayName() {
        return paymentWayName;
    }

    public void setPaymentWayName(String paymentWayName) {
        this.paymentWayName = paymentWayName;
    }

    public String getCloseReasonName() {
        return closeReasonName;
    }

    public void setCloseReasonName(String closeReasonName) {
        this.closeReasonName = closeReasonName;
    }

    public String getCreateDateString() {
        return createDateString;
    }

    public void setCreateDateString(String createDateString) {
        this.createDateString = createDateString;
    }
}
