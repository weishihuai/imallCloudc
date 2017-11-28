package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.DeviceTypeCodeEnum;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * Created by frt on 2017/8/7.
 */
public class MaintainingRecordPageVo {
    private Long id;

    /**
     *设备 编号
     */
    private String deviceNum;

    /**
     *设备 类型
     */
    private String deviceTypeName;

    /**
     *设备 名称
     */
    private String deviceNm;

    /**
     *型号
     */
    private String model;

    /**
     *购置 地点
     */
    private String purchasePlace;

    /**
     * 检修 负责 人
     */
    private String maintainResponseMan;

    /**
     * 维护 日期
     */
    private String maintainDateString;

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

    public String getMaintainResponseMan() {
        return maintainResponseMan;
    }

    public void setMaintainResponseMan(String maintainResponseMan) {
        this.maintainResponseMan = maintainResponseMan;
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
}
