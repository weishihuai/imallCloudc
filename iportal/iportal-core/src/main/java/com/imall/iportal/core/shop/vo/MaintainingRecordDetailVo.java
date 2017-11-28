package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.DeviceTypeCodeEnum;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * Created by frt on 2017/8/9.
 */
public class MaintainingRecordDetailVo {
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
     * 维护 日期
     */
    private String maintainDateString;

    /**
     * 维护 内容
     */
    private String maintainCont;

    /**
     * 维护 结果
     */
    private String maintainResult;

    /**
     * 工作 状况
     */
    private String workState;

    /**
     * 检修 负责 人
     */
    private String maintainResponseMan;

    /**
     * 审批 人 名称
     */
    private String approverNm;

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

    public String getMaintainDateString() {
        return maintainDateString;
    }

    public void setMaintainDateString(String maintainDateString) {
        this.maintainDateString = maintainDateString;
    }

    public void setMaintainDate(Date maintainDate){
        if(maintainDate==null){
            this.maintainDateString = "";
        }else{
            this.maintainDateString = DateTimeUtils.convertDateToString(maintainDate);
        }
    }

    public String getMaintainCont() {
        return maintainCont;
    }

    public void setMaintainCont(String maintainCont) {
        this.maintainCont = maintainCont;
    }

    public String getMaintainResult() {
        return maintainResult;
    }

    public void setMaintainResult(String maintainResult) {
        this.maintainResult = maintainResult;
    }

    public String getWorkState() {
        return workState;
    }

    public void setWorkState(String workState) {
        this.workState = workState;
    }

    public String getMaintainResponseMan() {
        return maintainResponseMan;
    }

    public void setMaintainResponseMan(String maintainResponseMan) {
        this.maintainResponseMan = maintainResponseMan;
    }

    public String getApproverNm() {
        return approverNm;
    }

    public void setApproverNm(String approverNm) {
        this.approverNm = approverNm;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
