package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by frt on 2017/8/1.
 */
public class PrescriptionRegisterSaveVo {
    /**
     * 门店 ID
     */
    @NotNull
    private Long shopId;

    /**
     * 订单 ID
     */
    @NotNull
    private Long orderId;

    /**
     * 处方 销售 订单 编码
     */
    @NotBlank
    @Length(max = 32)
    private String prescriptionSellOrderCode;

    /**
     * 处方 日期
     */
    @NotBlank
    private String prescriptionDateString;

    /**
     * 医疗 机构
     */
    @NotBlank
    @Length(max = 64)
    private String medicalOrg;

    /**
     * 医师 姓名
     */
    @Length(max = 32)
    private String doctorName;

    /**
     * 临床 诊断
     */
    @Length(max = 128)
    private String clinicDiagnosis;

    /**
     * 医嘱
     */
    @Length(max = 128)
    private String doctorsAdvice;

    /**
     * 会员 卡 号码
     */
    @Length(max = 32)
    private String memberCardNum;

    /**
     * 患者 名称
     */
    @NotBlank
    @Length(max = 32)
    private String patientNm;

    /**
     * 性别 类型 代码
     */
    @Length(max = 32)
    private String sexTypeCode;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 证件 类型 代码
     */
    @Length(max = 32)
    private String certTypeCode;

    /**
     * 证件 号码
     */
    @Length(max = 64)
    private String certNum;

    /**
     * 地址
     */
    @Length(max = 128)
    private String addr;

    /**
     * 手机
     */
    @Length(max = 32)
    private String mobile;

    /**
     * 备注
     */
    @Length(max = 256)
    private String remark;

    /**
     * 审核 人 ID
     */
    @NotNull
    private Long approveManId;

    /**
     * 附件
     */
    private List<FileVo> fileVoList;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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

    public String getPrescriptionDateString() {
        return prescriptionDateString;
    }

    public void setPrescriptionDateString(String prescriptionDateString) {
        this.prescriptionDateString = prescriptionDateString;
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

    public Long getApproveManId() {
        return approveManId;
    }

    public void setApproveManId(Long approveManId) {
        this.approveManId = approveManId;
    }

    public Date getPrescriptionDate() throws ParseException {
        return DateTimeUtils.convertStringToDate(this.getPrescriptionDateString());
    }

    public List<FileVo> getFileVoList() {
        return fileVoList;
    }

    public void setFileVoList(List<FileVo> fileVoList) {
        this.fileVoList = fileVoList;
    }
}
