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
public class MaintainingRecordSaveVo {
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
     *维护 日期
     */
    @NotNull
    private java.util.Date maintainDate;

    /**
     *维护 内容
     */
    @NotEmpty
    @Length(max = 32)
    private String maintainCont;

    /**
     *维护 结果
     */
    @Length(max = 32)
    private String maintainResult;

    /**
     *工作 状况
     */
    @Length(max = 32)
    private String workState;

    /**
     *检修 负责 人
     */
    @Length(max = 32)
    private String maintainResponseMan;

    /**
     *审批 人 名称
     */
    @Length(max = 32)
    private String approverNm;

    /**
     *备注
     */
    @Length(max = 32)
    private String remark;

    /**
     *审核 人 ID
     */
    @NotNull
    private Long approveManId;

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

    public Date getMaintainDate() {
        return maintainDate;
    }

    public void setMaintainDate(Date maintainDate) {
        this.maintainDate = maintainDate;
    }

    public void setMaintainDateString(String maintainDateString) throws ParseException {
        if(StringUtils.isNotBlank(maintainDateString)){
            this.maintainDate = DateTimeUtils.convertStringToDate(maintainDateString);
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

    public Long getApproveManId() {
        return approveManId;
    }

    public void setApproveManId(Long approveManId) {
        this.approveManId = approveManId;
    }
}
