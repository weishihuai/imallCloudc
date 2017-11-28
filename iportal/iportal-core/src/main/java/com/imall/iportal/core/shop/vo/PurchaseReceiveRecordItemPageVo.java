package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.PurchaseOrderStateCodeEnum;
import com.imall.iportal.core.shop.entity.PurchaseReceiveRecordItem;

import java.util.Date;

/**
 * Created by lxh on 2017/7/20.
 */
public class PurchaseReceiveRecordItemPageVo extends PurchaseReceiveRecordItem {
    //采购订单编号
    private String purchaseOrderNum;
    //收货单编号
    private String receiveOrderNum;
    //供应商名称
    private String supplierNm;
    //收货人名称
    private String receiver;
    //商品编码
    private String goodsCode;
    //商品名称
    private String goodsNm;
    //通用名称
    private String commonNm;
    //规格
    private String spec;
    //剂型
    private String dosageForm;
    //单位
    private String unit;
    //生产厂家
    private String produceManufacturer;
    //批准文号
    private String approvalNumber;
    //产地
    private String productionPlace;
    //收货日期
    private Date receiveTime;
    //订单状态
    private String purchaseOrderState;

    public String getPurchaseOrderNum() {
        return purchaseOrderNum;
    }

    public void setPurchaseOrderNum(String purchaseOrderNum) {
        this.purchaseOrderNum = purchaseOrderNum;
    }

    public String getReceiveOrderNum() {
        return receiveOrderNum;
    }

    public void setReceiveOrderNum(String receiveOrderNum) {
        this.receiveOrderNum = receiveOrderNum;
    }

    public String getSupplierNm() {
        return supplierNm;
    }

    public void setSupplierNm(String supplierNm) {
        this.supplierNm = supplierNm;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsNm() {
        return goodsNm;
    }

    public void setGoodsNm(String goodsNm) {
        this.goodsNm = goodsNm;
    }

    public String getCommonNm() {
        return commonNm;
    }

    public void setCommonNm(String commonNm) {
        this.commonNm = commonNm;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getProduceManufacturer() {
        return produceManufacturer;
    }

    public void setProduceManufacturer(String produceManufacturer) {
        this.produceManufacturer = produceManufacturer;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public String getProductionPlace() {
        return productionPlace;
    }

    public void setProductionPlace(String productionPlace) {
        this.productionPlace = productionPlace;
    }

    public String getPurchaseOrderState() {
        return purchaseOrderState;
    }

    public void setPurchaseOrderState(String purchaseOrderState) {
        this.purchaseOrderState = purchaseOrderState;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getReceiveTimeString() {
        return DateTimeUtils.convertDateToString(receiveTime);
    }

    public String getPurchaseOrderStateName() {
        return PurchaseOrderStateCodeEnum.fromCode(purchaseOrderState).toName();
    }
}
