package com.imall.iportal.core.shop.vo;

import com.imall.commons.dicts.OrderSourceCodeEnum;
import com.imall.commons.dicts.OrderStateCodeEnum;

/**
 * Created by lxh on 2017/8/2.
 * 销售汇总VO
 */
public class SellSummaryPageVo {
    //订单ID
    private Long orderId;
    //订单编号
    private String orderNum;
    //销售时间
    private String sellTimeString;
    //订单来源
    private String orderSourceCode;
    //订单状态
    private String orderStateCode;
    //应收金额
    private Double amountReceivable;
    //实收金额
    private Double amountReceived;
    //商品总数量
    private Long goodsTotalNum;
    //成本总金额
    private Double costTotalAmount;
    //开单人
    private String openOrderMan;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getSellTimeString() {
        return sellTimeString;
    }

    public void setSellTimeString(String sellTimeString) {
        this.sellTimeString = sellTimeString;
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

    public Double getAmountReceivable() {
        return amountReceivable;
    }

    public void setAmountReceivable(Double amountReceivable) {
        this.amountReceivable = amountReceivable;
    }

    public Double getAmountReceived() {
        return amountReceived;
    }

    public void setAmountReceived(Double amountReceived) {
        this.amountReceived = amountReceived;
    }

    public Long getGoodsTotalNum() {
        return goodsTotalNum;
    }

    public void setGoodsTotalNum(Long goodsTotalNum) {
        this.goodsTotalNum = goodsTotalNum;
    }

    public Double getCostTotalAmount() {
        return costTotalAmount;
    }

    public void setCostTotalAmount(Double costTotalAmount) {
        this.costTotalAmount = costTotalAmount;
    }

    public String getOpenOrderMan() {
        return openOrderMan;
    }

    public void setOpenOrderMan(String openOrderMan) {
        this.openOrderMan = openOrderMan;
    }

    public String getOrderSourceName(){
        return OrderSourceCodeEnum.fromCode(orderSourceCode).toName();
    }

    public String getOrderStatName(){
        return OrderStateCodeEnum.fromCode(orderStateCode).toName();
    }
}
