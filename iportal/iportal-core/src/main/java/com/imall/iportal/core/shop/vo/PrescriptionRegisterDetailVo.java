package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.CertTypeCodeEnum;
import com.imall.iportal.dicts.UserSexCodeEnum;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by frt on 2017/8/1.
 */
public class PrescriptionRegisterDetailVo {
    private Long id;

    /**
     * 订单 ID
     */
    private Long orderId;

    /**
     * 处方 销售 订单 编码
     */
    private String prescriptionSellOrderCode;

    /**
     * 处方 日期
     */
    private Date prescriptionDate;

    /**
     * 医疗 机构
     */
    private String medicalOrg;

    /**
     * 医师 姓名
     */
    private String doctorName;

    /**
     * 临床 诊断
     */
    private String clinicDiagnosis;

    /**
     * 医嘱
     */
    private String doctorsAdvice;

    /**
     * 会员 卡 号码
     */
    private String memberCardNum;

    /**
     * 患者 名称
     */
    private String patientNm;

    /**
     * 性别 类型 代码
     */
    private String sexTypeCode;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 证件 类型 代码
     */
    private String certTypeCode;

    /**
     * 证件 号码
     */
    private String certNum;

    /**
     * 地址
     */
    private String addr;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 备注
     */
    private String remark;

    /**
     * 审核 人
     */
    private java.lang.String approveManRealName;

    /**
     * 处方 登记 状态
     */
    private String prescriptionRegisterState;

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
     * 创建时间
     */
    private Date createDate;

    /**
     * 创建用户名称
     */
    private String createBy;

    /**
     * 处方订单项
     */
    List<PrescriptionRegisterItemVo> itemList;

    /**
     * 附件
     */
    private List<FileVo> fileVoList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getPrescriptionSellOrderCode() {
        return prescriptionSellOrderCode;
    }

    public void setPrescriptionSellOrderCode(String prescriptionSellOrderCode) {
        this.prescriptionSellOrderCode = prescriptionSellOrderCode;
    }

    public Date getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(Date prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public String getMedicalOrg() {
        return medicalOrg;
    }

    public void setMedicalOrg(String medicalOrg) {
        this.medicalOrg = medicalOrg;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getClinicDiagnosis() {
        return clinicDiagnosis;
    }

    public void setClinicDiagnosis(String clinicDiagnosis) {
        this.clinicDiagnosis = clinicDiagnosis;
    }

    public String getDoctorsAdvice() {
        return doctorsAdvice;
    }

    public void setDoctorsAdvice(String doctorsAdvice) {
        this.doctorsAdvice = doctorsAdvice;
    }

    public String getMemberCardNum() {
        return memberCardNum;
    }

    public void setMemberCardNum(String memberCardNum) {
        this.memberCardNum = memberCardNum;
    }

    public String getPatientNm() {
        return patientNm;
    }

    public void setPatientNm(String patientNm) {
        this.patientNm = patientNm;
    }

    public String getSexTypeCode() {
        return sexTypeCode;
    }

    public void setSexTypeCode(String sexTypeCode) {
        this.sexTypeCode = sexTypeCode;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCertTypeCode() {
        return certTypeCode;
    }

    public void setCertTypeCode(String certTypeCode) {
        this.certTypeCode = certTypeCode;
    }

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getApproveManRealName() {
        return approveManRealName;
    }

    public void setApproveManRealName(String approveManRealName) {
        this.approveManRealName = approveManRealName;
    }

    public String getPrescriptionRegisterState() {
        return prescriptionRegisterState;
    }

    public void setPrescriptionRegisterState(String prescriptionRegisterState) {
        this.prescriptionRegisterState = prescriptionRegisterState;
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

    public List<PrescriptionRegisterItemVo> getItemList() {
        return itemList;
    }

    public void setItemList(List<PrescriptionRegisterItemVo> itemList) {
        this.itemList = itemList;
    }

    public String getPrescriptionDateString(){
        return DateTimeUtils.convertDateToString(this.getPrescriptionDate());
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

    public String getSexTypeName(){
        if(StringUtils.isBlank(this.getSexTypeCode())){
            return "";
        }else{
            return UserSexCodeEnum.fromCode(this.getSexTypeCode()).toName();
        }
    }

    public String getCertTypeName(){
        if(StringUtils.isBlank(this.getCertTypeCode())){
            return "";
        }else{
            return CertTypeCodeEnum.fromCode(this.getCertTypeCode()).toName();
        }
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateDateString(){
        if(this.getCreateDate()==null){
            return "";
        }else{
            return DateTimeUtils.convertDateToString(this.getCreateDate());
        }
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public List<FileVo> getFileVoList() {
        return fileVoList;
    }

    public void setFileVoList(List<FileVo> fileVoList) {
        this.fileVoList = fileVoList;
    }
}
