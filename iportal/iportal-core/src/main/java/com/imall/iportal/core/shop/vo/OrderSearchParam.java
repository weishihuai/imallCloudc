package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by frt on 2017/7/24.
 */
public class OrderSearchParam {
    /**
     * 门店 ID
     */
    @NotNull
    private Long shopId;

    /**
     * 开单人
     */
    private Long openOrderManId;

    /**
     * 订单 来源 代码
     */
    private String orderSourceCode;

    /**
     * 订单 编号
     */
    private String orderNum;

    /**
     * 销售开始时间
     */
    private Date formCreateDate;

    private String formCreateDateString;

    /**
     * 销售结束时间
     */
    private Date toCreateDate;

    private String toCreateDateString;

    /**
     * 是否 麻黄碱 订单
     */
    private String isEphedrineOrder;

    /**
     * 是否 处方药 订单
     */
    private String isPrescriptionOrder;

    /**
     * 商品编码、名称、拼音
     */
    private String goodsSearchFields;

    /**
     * 会员手机、名称、卡号
     */
    private String memberSearchFields;

    /**
     *
     * 是否 已退货
     */
    private String isHasReturnedPurchase;

    /**
     * 支付方式
     */
    private String payWayType;

    /**
     * 订单状态
     */
    private String orderStateCode;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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

    public Date getFormCreateDate() {
        return formCreateDate;
    }

    public void setFormCreateDate(Date formCreateDate) {
        this.formCreateDate = formCreateDate;
    }

    public Date getToCreateDate() {
        return toCreateDate;
    }

    public void setToCreateDate(Date toCreateDate) {
        this.toCreateDate = toCreateDate;
    }

    public String getFormCreateDateString() {
        return formCreateDateString;
    }

    public String getToCreateDateString() {
        return toCreateDateString;
    }

    public void setFormCreateDateString(String value){
        try {
            setFormCreateDate(org.apache.commons.lang3.StringUtils.isBlank(value)?null:DateTimeUtils.convertStringToDate(value));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setToCreateDateString(String value){
        try {
            setToCreateDate(org.apache.commons.lang3.StringUtils.isBlank(value)?null:DateTimeUtils.getDayEndTime(DateTimeUtils.convertStringToDate(value)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getGoodsSearchFields() {
        return goodsSearchFields;
    }

    public void setGoodsSearchFields(String goodsSearchFields) {
        this.goodsSearchFields = goodsSearchFields;
    }

    public String getMemberSearchFields() {
        return memberSearchFields;
    }

    public void setMemberSearchFields(String memberSearchFields) {
        this.memberSearchFields = memberSearchFields;
    }

    public String getIsHasReturnedPurchase() {
        return isHasReturnedPurchase;
    }

    public void setIsHasReturnedPurchase(String isHasReturnedPurchase) {
        this.isHasReturnedPurchase = isHasReturnedPurchase;
    }

    public Long getOpenOrderManId() {
        return openOrderManId;
    }

    public void setOpenOrderManId(Long openOrderManId) {
        this.openOrderManId = openOrderManId;
    }

    public String getPayWayType() {
        return payWayType;
    }

    public void setPayWayType(String payWayType) {
        this.payWayType = payWayType;
    }

    public String getOrderStateCode() {
        return orderStateCode;
    }

    public void setOrderStateCode(String orderStateCode) {
        this.orderStateCode = orderStateCode;
    }
}
