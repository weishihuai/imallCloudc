package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by wsh on 2017/8/4.
 * 计量器具 - 保存Vo对象
 */
public class MeasuringDeviceAccountsSaveVo {
    /**
     * 门店ID
     */
    @NotNull
    private Long shopId;
    /**
     * 计量 器具 编号
     */
    @NotBlank
    @Length(max = 32)
    private String measuringDeviceNum;
    /**
     * 出厂 编号
     */
    private String manufacturingNum;
    /**
     * 器具 名称
     */
    @NotBlank
    @Length(max = 128)
    private String deviceNm;
    /**
     * 型号
     */
    private String model;
    /**
     * 生产 厂商
     */
    private String produceManufacturer;
    /**
     * 分类 编号
     */
    private String categoryNum;
    /**
     * 测量 范围
     */
    private String measureRange;
    /**
     * 精度 等级
     */
    private String precisionLevel;
    /**
     * 负责人
     */
    @NotBlank
    @Length(max = 32)
    private String responseMan;
    /**
     * 检测 周期
     */
    @NotNull
    private Integer measurePeriod;
    /**
     * 购置 价格
     */
    private Double purchasePrice;
    /**
     * 购买 日期
     */
    private String purchaseDateString;
    /**
     * 启用 时间
     */
    private String enableTimeString;
    /**
     * 购置 地点
     */
    private String purchasePlace;
    /**
     * 用途
     */
    private String application;
    /**
     * 使用 年限
     */
    private Integer serviceLife;
    /**
     * 使用 状态 代码
     */
    private String useStateCode;
    /**
     * 备注
     */
    private String remark;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getMeasuringDeviceNum() {
        return measuringDeviceNum;
    }

    public void setMeasuringDeviceNum(String measuringDeviceNum) {
        this.measuringDeviceNum = measuringDeviceNum;
    }

    public String getManufacturingNum() {
        return manufacturingNum;
    }

    public void setManufacturingNum(String manufacturingNum) {
        this.manufacturingNum = manufacturingNum;
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

    public String getCategoryNum() {
        return categoryNum;
    }

    public void setCategoryNum(String categoryNum) {
        this.categoryNum = categoryNum;
    }

    public String getMeasureRange() {
        return measureRange;
    }

    public void setMeasureRange(String measureRange) {
        this.measureRange = measureRange;
    }

    public String getPrecisionLevel() {
        return precisionLevel;
    }

    public void setPrecisionLevel(String precisionLevel) {
        this.precisionLevel = precisionLevel;
    }

    public String getResponseMan() {
        return responseMan;
    }

    public void setResponseMan(String responseMan) {
        this.responseMan = responseMan;
    }

    public Integer getMeasurePeriod() {
        return measurePeriod;
    }

    public void setMeasurePeriod(Integer measurePeriod) {
        this.measurePeriod = measurePeriod;
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

    public String getEnableTimeString() {
        return enableTimeString;
    }

    public void setEnableTimeString(String enableTimeString) {
        this.enableTimeString = enableTimeString;
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

    public String getUseStateCode() {
        return useStateCode;
    }

    public void setUseStateCode(String useStateCode) {
        this.useStateCode = useStateCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
