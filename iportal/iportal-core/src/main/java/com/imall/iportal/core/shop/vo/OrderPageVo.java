package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.commons.dicts.OrderSourceCodeEnum;

import java.util.Date;

/**
 * Created by frt on 2017/7/24.
 */
public class OrderPageVo {
    private java.lang.Long id;

    /**
     * 订单 来源 代码
     */
    private java.lang.String orderSourceCode;
    /**
     * 订单 编号
     */
    private java.lang.String orderNum;

    /**
     * 商品 总 金额
     */
    private java.lang.Double goodsTotalAmount;

    /**
     * 订单 总 金额
     */
    private java.lang.Double orderTotalAmount;

    /**
     * 开单人
     */
    private java.lang.String openOrderMan;

    /**
     * 销售时间
     */
    private Date createDate;


    /**
     * 是否 麻黄碱 订单
     */
    private String isEphedrineOrder;

    /**
     * 是否 处方药 订单
     */
    private String isPrescriptionOrder;

    /**
     * 会员名称
     */
    private String memberName;

    /**
     * 会员卡号
     */
    private String memberCardNum;

    /** 实收 现金 金额 */
    private Double realGeveCashAmount;

    /** 找零 */
    private Double returnSmall;

    private String isHasReturnedPurchase;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderSourceCode() {
        return orderSourceCode;
    }

    public void setOrderSourceCode(String orderSourceCode) {
        this.orderSourceCode = orderSourceCode;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Double getOrderTotalAmount() {
        return orderTotalAmount;
    }

    public void setOrderTotalAmount(Double orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }

    public String getOpenOrderMan() {
        return openOrderMan;
    }

    public void setOpenOrderMan(String openOrderMan) {
        this.openOrderMan = openOrderMan;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getIsEphedrineOrder() {
        return isEphedrineOrder;
    }

    public void setIsEphedrineOrder(String isEphedrineOrder) {
        this.isEphedrineOrder = isEphedrineOrder;
    }

    public String getIsPrescriptionOrder() {
        return isPrescriptionOrder;
    }

    public void setIsPrescriptionOrder(String isPrescriptionOrder) {
        this.isPrescriptionOrder = isPrescriptionOrder;
    }

    public String getOrderSourceName(){
        return OrderSourceCodeEnum.fromCode(this.getOrderSourceCode()).toName();
    }

    public String getCreateDateString(){
        if(this.getCreateDate()==null){
            return "";
        }else{
            return DateTimeUtils.convertDateTimeToString(getCreateDate());
        }
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Double getGoodsTotalAmount() {
        return goodsTotalAmount;
    }

    public void setGoodsTotalAmount(Double goodsTotalAmount) {
        this.goodsTotalAmount = goodsTotalAmount;
    }

    public String getMemberCardNum() {
        return memberCardNum;
    }

    public void setMemberCardNum(String memberCardNum) {
        this.memberCardNum = memberCardNum;
    }

    public Double getRealGeveCashAmount() {
        return realGeveCashAmount;
    }

    public void setRealGeveCashAmount(Double realGeveCashAmount) {
        this.realGeveCashAmount = realGeveCashAmount;
    }

    public Double getReturnSmall() {
        return returnSmall;
    }

    public void setReturnSmall(Double returnSmall) {
        this.returnSmall = returnSmall;
    }

    public String getIsHasReturnedPurchase() {
        return isHasReturnedPurchase;
    }

    public void setIsHasReturnedPurchase(String isHasReturnedPurchase) {
        this.isHasReturnedPurchase = isHasReturnedPurchase;
    }

    public String getOrderType(){
        if(BoolCodeEnum.fromCode(isHasReturnedPurchase).boolValue()){
            return "退货订单";
        }
        return "销售订单";
    }
}
