package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.DeviceTypeCodeEnum;

import java.util.Date;

/**
 * Created by frt on 2017/8/7.
 */
public class FacilityAndDeviceAccountsPageVo {
    private Long id;

    /**
     *设备 编号
     */
    private String deviceNum;

    /**
     *设备 类型 代码
     */
    private String deviceTypeCode;

    /**
     *设备 名称
     */
    private String deviceNm;

    /**
     *型号
     */
    private String model;

    /**
     *生产厂商
     */
    private String produceManufacturer;

    /**
     *购置 价格
     */
    private Double purchasePrice;

    /**
     *购买 日期
     */
    private String purchaseDateString;

    /**
     *启用 时间
     */
    private String enableTimeString;

    /**
     *购置 地点
     */
    private String purchasePlace;

    /**
     *用途
     */
    private String application;

    /**
     * 使用 年限
     */
    private Integer serviceLife;

    /**
     *负责 人
     */
    private String responseMan;

    /**
     *备注
     */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    public String getDeviceTypeCode() {
        return deviceTypeCode;
    }

    public String getDeviceTypeName(){
        return DeviceTypeCodeEnum.fromCode(this.getDeviceTypeCode()).toName();
    }

    public void setDeviceTypeCode(String deviceTypeCode) {
        this.deviceTypeCode = deviceTypeCode;
    }

    public String getDeviceNm() {
        return deviceNm;
    }

    public void setDeviceNm(String deviceNm) {
        this.deviceNm = deviceNm;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProduceManufacturer() {
        return produceManufacturer;
    }

    public void setProduceManufacturer(String produceManufacturer) {
        this.produceManufacturer = produceManufacturer;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getPurchaseDateString() {
        return purchaseDateString;
    }

    public void setPurchaseDateString(String purchaseDateString) {
        this.purchaseDateString = purchaseDateString;
    }

    public void setPurchaseDate(Date purchaseDate){
        if(purchaseDate==null){
            this.purchaseDateString = "";
        }else{
            this.purchaseDateString = DateTimeUtils.convertDateToString(purchaseDate);
        }
    }

    public String getEnableTimeString() {
        return enableTimeString;
    }

    public void setEnableTimeString(String enableTimeString) {
        this.enableTimeString = enableTimeString;
    }

    public void setEnableTime(Date enableTime){
        if(enableTime==null){
            this.enableTimeString = "";
        }else{
            this.enableTimeString = DateTimeUtils.convertDateToString(enableTime);
        }
    }

    public String getPurchasePlace() {
        return purchasePlace;
    }

    public void setPurchasePlace(String purchasePlace) {
        this.purchasePlace = purchasePlace;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public Integer getServiceLife() {
        return serviceLife;
    }

    public void setServiceLife(Integer serviceLife) {
        this.serviceLife = serviceLife;
    }

    public String getResponseMan() {
        return responseMan;
    }

    public void setResponseMan(String responseMan) {
        this.responseMan = responseMan;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
