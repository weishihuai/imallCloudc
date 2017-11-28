package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.DeviceTypeCodeEnum;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * Created by frt on 2017/8/9.
 */
public class UseRecordPageVo {
    private Long id;

    /**
     *设备编号
     */
    private String deviceNum;

    /**
     *设备名称
     */
    private String deviceNm;

    /**
     *设备类型
     */
    private String deviceTypeName;

    /**
     *型号
     */
    private String model;

    /**
     *购置地点
     */
    private String purchasePlace;

    /**
     * 操作 人
     */
    private String operationMan;

    /**
     * 使用 日期
     */
    private String useDateString;

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

    public String getDeviceNm() {
        return deviceNm;
    }

    public void setDeviceNm(String deviceNm) {
        this.deviceNm = deviceNm;
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

    public String getOperationMan() {
        return operationMan;
    }

    public void setOperationMan(String operationMan) {
        this.operationMan = operationMan;
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
}
