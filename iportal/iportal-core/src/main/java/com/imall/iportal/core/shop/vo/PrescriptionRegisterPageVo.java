package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysUser;

import java.util.Date;

/**
 * Created by frt on 2017/8/1.
 */
public class PrescriptionRegisterPageVo {
    private Long id;

    /**
     * 处方 销售 订单 编码
     */
    private String prescriptionSellOrderCode;

    /**
     * 患者 名称
     */
    private String patientNm;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 医疗 机构
     */
    private String medicalOrg;

    /**
     * 证件 号码
     */
    private String certNum;

    /**
     * 医师 姓名
     */
    private String doctorName;

    /**
     * 处方 日期
     */
    private Date prescriptionDate;

    /**
     * 创建 日期
     */
    private Date createDate;

    /**
     * 审核 人
     */
    private java.lang.String approveManRealName;

    /**
     * 调剂 员 姓名
     */
    private String dispensingManName;

    /**
     * 复核 人
     */
    private java.lang.String repeatApproveManRealName;

    /**
     * 发药 人 姓名
     */
    private String grantDrugManName;

    /**
     * 调剂 日期
     */
    private java.util.Date dispensingDate;

    /**
     * 处方 登记 状态
     */
    private String prescriptionRegisterState;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrescriptionSellOrderCode() {
        return prescriptionSellOrderCode;
    }

    public void setPrescriptionSellOrderCode(String prescriptionSellOrderCode) {
        this.prescriptionSellOrderCode = prescriptionSellOrderCode;
    }

    public String getPatientNm() {
        return patientNm;
    }

    public void setPatientNm(String patientNm) {
        this.patientNm = patientNm;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMedicalOrg() {
        return medicalOrg;
    }

    public void setMedicalOrg(String medicalOrg) {
        this.medicalOrg = medicalOrg;
    }

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Date getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(Date prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getPrescriptionDateString(){
        if(this.getPrescriptionDate()==null){
            return "";
        }else{
            return DateTimeUtils.convertDateToString(this.getPrescriptionDate());
        }
    }

    public String getCreateDateString(){
        if(this.getCreateDate()==null){
            return "";
        }else{
            return DateTimeUtils.convertDateToString(this.getCreateDate());
        }
    }

    public String getApproveManRealName() {
        return approveManRealName;
    }

    public void setApproveManRealName(String approveManRealName) {
        this.approveManRealName = approveManRealName;
    }

    public String getDispensingManName() {
        return dispensingManName;
    }

    public void setDispensingManName(String dispensingManName) {
        this.dispensingManName = dispensingManName;
    }

    public String getRepeatApproveManRealName() {
        return repeatApproveManRealName;
    }

    public void setRepeatApproveManRealName(String repeatApproveManRealName) {
        this.repeatApproveManRealName = repeatApproveManRealName;
    }

    public String getGrantDrugManName() {
        return grantDrugManName;
    }

    public void setGrantDrugManName(String grantDrugManName) {
        this.grantDrugManName = grantDrugManName;
    }

    public Date getDispensingDate() {
        return dispensingDate;
    }

    public void setDispensingDate(Date dispensingDate) {
        this.dispensingDate = dispensingDate;
    }

    public String getDispensingDateString(){
        if(this.getDispensingDate()==null){
            return "";
        }else{
            return DateTimeUtils.convertDateToString(this.getDispensingDate());
        }
    }

    public String getPrescriptionRegisterState() {
        return prescriptionRegisterState;
    }

    public void setPrescriptionRegisterState(String prescriptionRegisterState) {
        this.prescriptionRegisterState = prescriptionRegisterState;
    }

    public void setApproveManId(Long value) {
        if(value!=null){
            SysUser approveMan = ServiceManager.sysUserService.findOne(value);
            if(approveMan!=null){
                setApproveManRealName(approveMan.getRealName());
            }
        }
    }

    public void setRepeatApproveManId(Long value) {
        if(value!=null){
            SysUser repeatApproveMan = ServiceManager.sysUserService.findOne(value);
            if(repeatApproveMan!=null){
                setRepeatApproveManRealName(repeatApproveMan.getRealName());
            }
        }
    }
}
