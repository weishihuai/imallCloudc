package com.imall.iportal.core.shop.vo;

import com.imall.commons.dicts.OrderSourceCodeEnum;
import com.imall.commons.dicts.OrderStateCodeEnum;
import com.imall.iportal.core.shop.entity.OrderItem;

/**
 * Created by lxh on 2017/8/2.
 * 销售明细
 */
public class SellDetailPageVo extends OrderItem{
    //订单编号
    private String orderNum;
    //订单来源
    private String orderSourceCode;
    //订单状态
    private String orderStateCode;
    //是否处方药
    private String isPrescription;
    //麻黄碱
    private String isEphedrine;
    //剂型
    private String dosageForm;
    //批准文号
    private String approvalNumber;
    //货位
    private String storageSpaceNm;
    //会员卡号
    private String memberCardNum;
    //会员手机
    private String mobile;
    //营业员
    private String sellerNm;
    //销售时间
    private String sellTimeString;

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
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

    public String getIsPrescription() {
        return isPrescription;
    }

    public void setIsPrescription(String isPrescription) {
        this.isPrescription = isPrescription;
    }

    public String getIsEphedrine() {
        return isEphedrine;
    }

    public void setIsEphedrine(String isEphedrine) {
        this.isEphedrine = isEphedrine;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public String getStorageSpaceNm() {
        return storageSpaceNm;
    }

    public void setStorageSpaceNm(String storageSpaceNm) {
        this.storageSpaceNm = storageSpaceNm;
    }

    public String getMemberCardNum() {
        return memberCardNum;
    }

    public void setMemberCardNum(String memberCardNum) {
        this.memberCardNum = memberCardNum;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSellerNm() {
        return sellerNm;
    }

    public void setSellerNm(String sellerNm) {
        this.sellerNm = sellerNm;
    }

    public String getSellTimeString() {
        return sellTimeString;
    }

    public void setSellTimeString(String sellTimeString) {
        this.sellTimeString = sellTimeString;
    }

    public String getOrderSourceName(){
        return OrderSourceCodeEnum.fromCode(orderSourceCode).toName();
    }

    public String getOrderStatName(){
        return OrderStateCodeEnum.fromCode(orderStateCode).toName();
    }
}
