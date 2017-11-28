package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.shop.entity.MeasureRecord;

/**
 * Created by wsh on 2017/8/5.
 * 计量器具 检测记录 列表Vo对象
 */
public class MeasureRecordPageVo extends MeasureRecord {
    /**
     * 计量 器具 编号
     */
    private String measuringDeviceNum;
    /**
     * 出厂 编号
     */
    private String manufacturingNum;
    /**
     * 器具 名称
     */
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
    private String responseMan;
    /**
     * 检测 周期
     */
    private Integer measurePeriod;
    /**
     * 使用 状态 代码
     */
    private String useStateCode;
    /**
     * 检测日期
     */
    private String measureDateString;

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

    public String getUseStateCode() {
        return useStateCode;
    }

    public void setUseStateCode(String useStateCode) {
        this.useStateCode = useStateCode;
    }

    @Override
    public String getMeasureDateString() {
        return measureDateString;
    }

    @Override
    public void setMeasureDateString(String measureDateString) {
        this.measureDateString = measureDateString;
    }
}
