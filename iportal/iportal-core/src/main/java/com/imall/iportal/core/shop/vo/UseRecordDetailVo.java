package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.DeviceTypeCodeEnum;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * Created by frt on 2017/8/9.
 */
public class UseRecordDetailVo {
    /**
     *设备编号
     */
    private String deviceNum;

    /**
     *设备类型
     */
    private String deviceTypeName;

    /**
     *设备名称
     */
    private String deviceNm;

    /**
     *型号
     */
    private String model;

    /**
     *购置地点
     */
    private String purchasePlace;

    /**
     *启用时间
     */
    private String enableTimeString;

    /**
     * 使用目的
     */
    private String purposes;

    /**
     * 使用 日期
     */
    private String useDateString;

    /**
     * 停止 时间
     */
    private String stopTimeString;

    /**
     * 记录 日期
     */
    private String recordDateString;

    /**
     * 使用 情况
     */
    private String serviceCondition;

    /**
     * 操作 人
     */
    private String operationMan;

    /**
     * 备注
     */
    private String remark;

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    public void setDeviceTypeCode(String deviceTypeCode) {
        if(StringUtils.isBlank(deviceTypeCode)){
            this.deviceTypeName = "";
        }else{
            this.deviceTypeName = DeviceTypeCodeEnum.fromCode(deviceTypeCode).toName();
        }
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

    public String getPurchasePlace() {
        return purchasePlace;
    }

    public void setPurchasePlace(String purchasePlace) {
        this.purchasePlace = purchasePlace;
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

    public String getPurposes() {
        return purposes;
    }

    public void setPurposes(String purposes) {
        this.purposes = purposes;
    }

    public String getUseDateString() {
        return useDateString;
    }

    public void setUseDateString(String useDateString) {
        this.useDateString = useDateString;
    }

    public void setUseDate(Date useDate){
        if(useDate==null){
            this.useDateString = "";
        }else{
            this.useDateString = DateTimeUtils.convertDateToString(useDate);
        }
    }

    public String getStopTimeString() {
        return stopTimeString;
    }

    public void setStopTimeString(String stopTimeString) {
        this.stopTimeString = stopTimeString;
    }

    public void setStopTime(Date stopTime){
        if(stopTime==null){
            this.stopTimeString = "";
        }else{
            this.stopTimeString = DateTimeUtils.convertDateToString(stopTime);
        }
    }

    public String getRecordDateString() {
        return recordDateString;
    }

    public void setRecordDateString(String recordDateString) {
        this.recordDateString = recordDateString;
    }

    public void setRecordDate(Date recordDate){
        if(recordDate==null){
            this.recordDateString = "";
        }else{
            this.recordDateString = DateTimeUtils.convertDateToString(recordDate);
        }
    }

    public String getServiceCondition() {
        return serviceCondition;
    }

    public void setServiceCondition(String serviceCondition) {
        this.serviceCondition = serviceCondition;
    }

    public String getOperationMan() {
        return operationMan;
    }

    public void setOperationMan(String operationMan) {
        this.operationMan = operationMan;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
