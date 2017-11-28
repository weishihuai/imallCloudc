package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by frt on 2017/7/7.
 */
public class TemperatureMoistureMonitorRecordSaveVo {
    /**
     *门店 ID
     */
    @NotNull
    private Long shopId;

    /**
     *货位 ID
     */
    @NotNull
    private Long storageSpaceId;

    /**
     *监控 日期
     */
    @NotBlank
    private String monitorDateString;

    /**
     *记录 人
     */
    @NotBlank
    @Length(max = 32)
    private String recordMan;

    /**
     *监控 时间
     */
    @NotBlank
    @Length(max = 32)
    private String monitorTime;

    /**
     *温度
     */
    @NotNull
    private Double temperature;

    /**
     *湿度
     */
    @NotNull
    private Double moisture;

    /**
     *调控 措施
     */
    @Length(max = 32)
    private String controlMeasure;

    /**
     *调控后 时间
     */
    @Length(max = 32)
    private String timeAfterControl;

    /**
     *调控后 温度
     */
    private Double temperatureAfterControl;

    /**
     *调控后 湿度
     */
    private Double moistureAfterControl;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getStorageSpaceId() {
        return storageSpaceId;
    }

    public void setStorageSpaceId(Long storageSpaceId) {
        this.storageSpaceId = storageSpaceId;
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

    public String getMonitorDateString() {
        return monitorDateString;
    }

    public void setMonitorDateString(String monitorDateString) {
        this.monitorDateString = monitorDateString;
    }

    public Date getMonitorDate() throws ParseException {
        return DateTimeUtils.getDayStartTime(this.getMonitorDateString());
    }
}
