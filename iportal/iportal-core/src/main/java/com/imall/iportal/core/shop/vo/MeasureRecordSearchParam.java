package com.imall.iportal.core.shop.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by wsh on 2017/8/5
 * 计量器具 检查记录
 */
public class MeasureRecordSearchParam {
    /**
     * 门店ID
     */
    @NotNull
    private Long shopId;
    /**
     * 计量器具编号
     */
    private String measuringDeviceNum;
    /**
     * 出厂编号
     */
    private String manufacturingNum;
    /**
     * 检测时间 开始时间
     */
    private String measureDateStartString;
    /**
     * 检测时间 结束时间
     */
    private String measureDateEndString;

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

    public String getMeasureDateStartString() {
        return measureDateStartString;
    }

    public void setMeasureDateStartString(String measureDateStartString) {
        this.measureDateStartString = measureDateStartString;
    }

    public String getMeasureDateEndString() {
        return measureDateEndString;
    }

    public void setMeasureDateEndString(String measureDateEndString) {
        this.measureDateEndString = measureDateEndString;
    }
}
