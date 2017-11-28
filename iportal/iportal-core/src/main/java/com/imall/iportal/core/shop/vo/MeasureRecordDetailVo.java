package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.shop.entity.MeasureRecord;

/**
 * Created by wsh on 2017/8/5.
 * 计量器具 检测记录 详情对象
 */
public class MeasureRecordDetailVo extends MeasureRecord {
    /**
     * 计量器具编号
     */
    private String measuringDeviceNum;
    /**
     * 出厂编号
     */
    private String manufacturingNum;
    /**
     * 器具名称
     */
    private String deviceNm;
    /**
     * 型号
     */
    private String model;
    /**
     * 生产厂家
     */
    private String produceManufacturer;
    /**
     * 分类编号
     */
    private String categoryNum;
    /**
     * 测量范围
     */
    private String measureRange;
    /**
     * 精度等级
     */
    private String precisionLevel;
    /**
     * 负责人
     */
    private String responseMan;
    /**
     * 检测周期
     */
    private Integer measurePeriod;
    /**
     * 检测日期
     */
    private String measureDateString;
    /**
     * 开始时间
     */
    private String startTimeString;
    /**
     * 结束时间
     */
    private String endTimeString;

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

    @Override
    public String getMeasureDateString() {
        return measureDateString;
    }

    @Override
    public void setMeasureDateString(String measureDateString) {
        this.measureDateString = measureDateString;
    }

    @Override
    public String getStartTimeString() {
        return startTimeString;
    }

    @Override
    public void setStartTimeString(String startTimeString) {
        this.startTimeString = startTimeString;
    }

    @Override
    public String getEndTimeString() {
        return endTimeString;
    }

    @Override
    public void setEndTimeString(String endTimeString) {
        this.endTimeString = endTimeString;
    }
}
