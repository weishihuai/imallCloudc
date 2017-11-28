package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.entity.StorageSpace;

/**
 * 温度湿度监控分页对象
 */
public class TemperatureMoistureMonitorRecordPageVo{
    private Long id;

    /**
     * 货位
     */
    private String storageSpaceNm;

    /**
     * 监控 日期
     */
    private String monitorDateString;

    /**
     * 记录 人
     */
    private String recordMan;

    /**
     * 监控 时间
     */
    private String monitorTime;

    /**
     * 温度
     */
    private Double temperature;

    /**
     * 湿度
     */
    private Double moisture;

    /**
     * 调控 措施
     */
    private String controlMeasure;

    /**
     * 调控后 时间
     */
    private String timeAfterControl;

    /**
     * 调控后 温度
     */
    private Double temperatureAfterControl;

    /**
     * 调控后 湿度
     */
    private Double moistureAfterControl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStorageSpaceNm() {
        return storageSpaceNm;
    }

    public void setStorageSpaceNm(String storageSpaceNm) {
        this.storageSpaceNm = storageSpaceNm;
    }

    public String getMonitorDateString() {
        return monitorDateString;
    }

    public void setMonitorDateString(String monitorDateString) {
        this.monitorDateString = monitorDateString;
    }

    public void setMonitorDate(java.util.Date value) {
        if(value!=null){
            this.setMonitorDateString(DateTimeUtils.convertDateToString(value));
        }
    }

    public String getRecordMan() {
        return recordMan;
    }

    public void setRecordMan(String recordMan) {
        this.recordMan = recordMan;
    }

    public String getMonitorTime() {
        return monitorTime;
    }

    public void setMonitorTime(String monitorTime) {
        this.monitorTime = monitorTime;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getMoisture() {
        return moisture;
    }

    public void setMoisture(Double moisture) {
        this.moisture = moisture;
    }

    public String getControlMeasure() {
        return controlMeasure;
    }

    public void setControlMeasure(String controlMeasure) {
        this.controlMeasure = controlMeasure;
    }

    public String getTimeAfterControl() {
        return timeAfterControl;
    }

    public void setTimeAfterControl(String timeAfterControl) {
        this.timeAfterControl = timeAfterControl;
    }

    public Double getTemperatureAfterControl() {
        return temperatureAfterControl;
    }

    public void setTemperatureAfterControl(Double temperatureAfterControl) {
        this.temperatureAfterControl = temperatureAfterControl;
    }

    public Double getMoistureAfterControl() {
        return moistureAfterControl;
    }

    public void setMoistureAfterControl(Double moistureAfterControl) {
        this.moistureAfterControl = moistureAfterControl;
    }

    public void setStorageSpaceId(Long value) {
        if(value!=null){
            StorageSpace storageSpace = ServiceManager.storageSpaceService.findOne(value);
            if(storageSpace!=null){
                this.setStorageSpaceNm(storageSpace.getStorageSpaceNm());
            }
        }
    }
}
