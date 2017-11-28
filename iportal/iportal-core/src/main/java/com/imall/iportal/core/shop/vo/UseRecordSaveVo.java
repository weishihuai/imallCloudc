package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by frt on 2017/8/7.
 */
public class UseRecordSaveVo {
    /**
     *门店 ID
     */
    @NotNull
    private Long shopId;

    /**
     *设施 设备 台账 ID
     */
    @NotNull
    private Long facilityAndDeviceAccountsId;

    /**
     *使用 目的
     */
    @NotEmpty
    @Length(max = 32)
    private String purposes;

    /**
     *使用 日期
     */
    @NotNull
    private java.util.Date useDate;

    /**
     * 停止 时间
     */
    private java.util.Date stopTime;

    /**
     *使用 情况
     */
    @Length(max = 32)
    private String serviceCondition;

    /**
     *操作 人
     */
    @Length(max = 32)
    private String operationMan;

    /**
     *备注
     */
    @Length(max = 32)
    private String remark;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getFacilityAndDeviceAccountsId() {
        return facilityAndDeviceAccountsId;
    }

    public void setFacilityAndDeviceAccountsId(Long facilityAndDeviceAccountsId) {
        this.facilityAndDeviceAccountsId = facilityAndDeviceAccountsId;
    }

    public String getPurposes() {
        return purposes;
    }

    public void setPurposes(String purposes) {
        this.purposes = purposes;
    }

    public Date getUseDate() {
        return useDate;
    }

    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }

    public void setUseDateString(String useDateString) throws ParseException {
        if(StringUtils.isNotBlank(useDateString)){
            this.useDate = DateTimeUtils.convertStringToDate(useDateString);
        }
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public void setStopTimeString(String stopTimeString) throws ParseException {
        if(StringUtils.isNotBlank(stopTimeString)){
            this.stopTime = DateTimeUtils.convertStringToDate(stopTimeString);
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
