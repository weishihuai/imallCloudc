package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by frt on 2017/8/7.
 */
public class FacilityAndDeviceAccountsSaveVo {
    /**
     *门店 ID
     */
    @NotNull
    private Long shopId;

    /**
     *设备 类型 代码
     */
    @NotEmpty
    @Length(max = 32)
    private String deviceTypeCode;

    /**
     *设备 编号
     */
    @NotEmpty
    @Length(max = 32)
    private String deviceNum;

    /**
     *设备 名称
     */
    @NotEmpty
    @Length(max = 128)
    private String deviceNm;

    /**
     *型号
     */
    @Length(max = 32)
    private String model;

    /**
     *生产厂商
     */
    @Length(max = 64)
    private String produceManufacturer;

    /**
     *负责 人
     */
    @NotEmpty
    @Length(max = 32)
    private String responseMan;

    /**
     *购置 价格
     */
    private Double purchasePrice;

    /**
     *购买 日期
     */
    private java.util.Date purchaseDate;

    /**
     *启用 时间
     */
    private java.util.Date enableTime;

    /**
     *购置 地点
     */
    @Length(max = 128)
    private String purchasePlace;

    /**
     *用途
     */
    @Length(max = 128)
    private String application;

    /**
     * 使用 年限
     */
    private Integer serviceLife;

    /**
     *备注
     */
    @Length(max = 128)
    private String remark;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getDeviceTypeCode() {
        return deviceTypeCode;
    }

    public void setDeviceTypeCode(String deviceTypeCode) {
        this.deviceTypeCode = deviceTypeCode;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
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

    public String getResponseMan() {
        return responseMan;
    }

    public void setResponseMan(String responseMan) {
        this.responseMan = responseMan;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setPurchaseDateString(String purchaseDateString) throws ParseException {
        if(StringUtils.isNotBlank(purchaseDateString)){
            this.setPurchaseDate(DateTimeUtils.convertStringToDate(purchaseDateString));
        }
    }

    public Date getEnableTime() {
        return enableTime;
    }

    public void setEnableTime(Date enableTime) {
        this.enableTime = enableTime;
    }

    public void setEnableTimeString(String enableTimeString) throws ParseException {
        if(StringUtils.isNotBlank(enableTimeString)){
            this.setEnableTime(DateTimeUtils.convertStringToDate(enableTimeString));
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
