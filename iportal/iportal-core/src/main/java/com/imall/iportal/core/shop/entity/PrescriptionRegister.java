
package com.imall.iportal.core.shop.entity;

import com.imall.commons.base.entity.BaseEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * 实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_shp_prescription_register" )
public class PrescriptionRegister extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String ORDER_ID = "orderId";
	public static final String PRESCRIPTION_SELL_ORDER_CODE = "prescriptionSellOrderCode";
	public static final String PRESCRIPTION_DATE = "prescriptionDate";
	public static final String MEDICAL_ORG = "medicalOrg";
	public static final String DOCTOR_NAME = "doctorName";
	public static final String CLINIC_DIAGNOSIS = "clinicDiagnosis";
	public static final String DOCTORS_ADVICE = "doctorsAdvice";
	public static final String MEMBER_CARD_NUM = "memberCardNum";
	public static final String PATIENT_NM = "patientNm";
	public static final String SEX_TYPE_CODE = "sexTypeCode";
	public static final String AGE = "age";
	public static final String CERT_TYPE_CODE = "certTypeCode";
	public static final String CERT_NUM = "certNum";
	public static final String ADDR = "addr";
	public static final String MOBILE = "mobile";
	public static final String REMARK = "remark";
	public static final String APPROVE_MAN_ID = "approveManId";
	public static final String PRESCRIPTION_REGISTER_STATE = "prescriptionRegisterState";
	public static final String DISPENSING_MAN_NAME = "dispensingManName";
	public static final String REPEAT_APPROVE_MAN_ID = "repeatApproveManId";
	public static final String GRANT_DRUG_MAN_NAME = "grantDrugManName";
	public static final String DISPENSING_DATE = "dispensingDate";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;
    /** ORDER_ID - 订单 ID */
    @Column(name = "ORDER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long orderId;
    /** PRESCRIPTION_SELL_ORDER_CODE - 处方 销售 订单 编码 */
    @Column(name = "PRESCRIPTION_SELL_ORDER_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String prescriptionSellOrderCode;
    /** PRESCRIPTION_DATE - 处方 日期 */
    @Column(name = "PRESCRIPTION_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date prescriptionDate;
    /** MEDICAL_ORG - 医疗 机构 */
    @Column(name = "MEDICAL_ORG", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    private String medicalOrg;
    /** DOCTOR_NAME - 医师 姓名 */
    @Column(name = "DOCTOR_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String doctorName;
    /** CLINIC_DIAGNOSIS - 临床 诊断 */
    @Column(name = "CLINIC_DIAGNOSIS", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private String clinicDiagnosis;
    /** DOCTORS_ADVICE - 医嘱 */
    @Column(name = "DOCTORS_ADVICE", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private String doctorsAdvice;
    /** MEMBER_CARD_NUM - 会员 卡 号码 */
    @Column(name = "MEMBER_CARD_NUM", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String memberCardNum;
    /** PATIENT_NM - 患者 名称 */
    @Column(name = "PATIENT_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String patientNm;
    /** SEX_TYPE_CODE - 性别 类型 代码 */
    @Column(name = "SEX_TYPE_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String sexTypeCode;
    /** AGE - 年龄 */
    @Column(name = "AGE", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
    private Integer age;
    /** CERT_TYPE_CODE - 证件 类型 代码 */
    @Column(name = "CERT_TYPE_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String certTypeCode;
    /** CERT_NUM - 证件 号码 */
    @Column(name = "CERT_NUM", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private String certNum;
    /** ADDR - 地址 */
    @Column(name = "ADDR", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private String addr;
    /** MOBILE - 手机 */
    @Column(name = "MOBILE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String mobile;
    /** REMARK - 备注 */
    @Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
    private String remark;
    /** APPROVE_MAN_ID - 审核 人 ID */
    @Column(name = "APPROVE_MAN_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long approveManId;
    /** PRESCRIPTION_REGISTER_STATE - 处方 登记 状态 */
    @Column(name = "PRESCRIPTION_REGISTER_STATE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String prescriptionRegisterState;
    /** DISPENSING_MAN_NAME - 调剂 员 姓名 */
    @Column(name = "DISPENSING_MAN_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String dispensingManName;
    /** REPEAT_APPROVE_MAN_ID - 复核 人 ID */
    @Column(name = "REPEAT_APPROVE_MAN_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    private Long repeatApproveManId;
    /** GRANT_DRUG_MAN_NAME - 发药 人 姓名 */
    @Column(name = "GRANT_DRUG_MAN_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String grantDrugManName;
    /** DISPENSING_DATE - 调剂 日期 */
    @Column(name = "DISPENSING_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date dispensingDate;

    public void setShopId(Long value) {
		this.shopId = value;
	}

    public Long getShopId() {
		return this.shopId;
	}

    public void setOrderId(Long value) {
		this.orderId = value;
	}

    public Long getOrderId() {
		return this.orderId;
	}

    public void setPrescriptionSellOrderCode(String value) {
		this.prescriptionSellOrderCode = value;
	}

    public String getPrescriptionSellOrderCode() {
		return this.prescriptionSellOrderCode;
	}

    public void setPrescriptionDate(java.util.Date value) {
		this.prescriptionDate = value;
	}

    public java.util.Date getPrescriptionDate() {
		return this.prescriptionDate;
	}

    public void setMedicalOrg(String value) {
		this.medicalOrg = value;
	}

    public String getMedicalOrg() {
		return this.medicalOrg;
	}

    public void setDoctorName(String value) {
		this.doctorName = value;
	}

    public String getDoctorName() {
		return this.doctorName;
	}

    public void setClinicDiagnosis(String value) {
		this.clinicDiagnosis = value;
	}

    public String getClinicDiagnosis() {
		return this.clinicDiagnosis;
	}

    public void setDoctorsAdvice(String value) {
		this.doctorsAdvice = value;
	}

    public String getDoctorsAdvice() {
		return this.doctorsAdvice;
	}

    public void setMemberCardNum(String value) {
		this.memberCardNum = value;
	}

    public String getMemberCardNum() {
		return this.memberCardNum;
	}

    public void setPatientNm(String value) {
		this.patientNm = value;
	}

    public String getPatientNm() {
		return this.patientNm;
	}

    public void setSexTypeCode(String value) {
		this.sexTypeCode = value;
	}

    public String getSexTypeCode() {
		return this.sexTypeCode;
	}

    public void setAge(Integer value) {
		this.age = value;
	}

    public Integer getAge() {
		return this.age;
	}

    public void setCertTypeCode(String value) {
		this.certTypeCode = value;
	}

    public String getCertTypeCode() {
		return this.certTypeCode;
	}

    public void setCertNum(String value) {
		this.certNum = value;
	}

    public String getCertNum() {
		return this.certNum;
	}

    public void setAddr(String value) {
		this.addr = value;
	}

    public String getAddr() {
		return this.addr;
	}

    public void setMobile(String value) {
		this.mobile = value;
	}

    public String getMobile() {
		return this.mobile;
	}

    public void setRemark(String value) {
		this.remark = value;
	}

    public String getRemark() {
		return this.remark;
	}

    public void setApproveManId(Long value) {
		this.approveManId = value;
	}

    public Long getApproveManId() {
		return this.approveManId;
	}

    public void setPrescriptionRegisterState(String value) {
		this.prescriptionRegisterState = value;
	}

    public String getPrescriptionRegisterState() {
		return this.prescriptionRegisterState;
	}

    public void setDispensingManName(String value) {
		this.dispensingManName = value;
	}

    public String getDispensingManName() {
		return this.dispensingManName;
	}

    public void setRepeatApproveManId(Long value) {
		this.repeatApproveManId = value;
	}

    public Long getRepeatApproveManId() {
		return this.repeatApproveManId;
	}

    public void setGrantDrugManName(String value) {
		this.grantDrugManName = value;
	}

    public String getGrantDrugManName() {
		return this.grantDrugManName;
	}

    public void setDispensingDate(java.util.Date value) {
		this.dispensingDate = value;
	}

    public java.util.Date getDispensingDate() {
		return this.dispensingDate;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("ORDER_ID",getOrderId())
			.append("PRESCRIPTION_SELL_ORDER_CODE",getPrescriptionSellOrderCode())
			.append("PRESCRIPTION_DATE",getPrescriptionDate())
			.append("MEDICAL_ORG",getMedicalOrg())
			.append("DOCTOR_NAME",getDoctorName())
			.append("CLINIC_DIAGNOSIS",getClinicDiagnosis())
			.append("DOCTORS_ADVICE",getDoctorsAdvice())
			.append("MEMBER_CARD_NUM",getMemberCardNum())
			.append("PATIENT_NM",getPatientNm())
			.append("SEX_TYPE_CODE",getSexTypeCode())
			.append("AGE",getAge())
			.append("CERT_TYPE_CODE",getCertTypeCode())
			.append("CERT_NUM",getCertNum())
			.append("ADDR",getAddr())
			.append("MOBILE",getMobile())
			.append("REMARK",getRemark())
			.append("APPROVE_MAN_ID",getApproveManId())
			.append("PRESCRIPTION_REGISTER_STATE",getPrescriptionRegisterState())
			.append("DISPENSING_MAN_NAME",getDispensingManName())
			.append("REPEAT_APPROVE_MAN_ID",getRepeatApproveManId())
			.append("GRANT_DRUG_MAN_NAME",getGrantDrugManName())
			.append("DISPENSING_DATE",getDispensingDate())
			.append("CREATE_DATE",getCreateDate())
			.append("CREATE_BY",getCreateBy())
			.append("LAST_MODIFIED_DATE",getLastModifiedDate())
			.append("LAST_MODIFIED_BY",getLastModifiedBy())
			.append("VERSION",getVersion())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(this.getId() == null){
			return false;
		}
		if(!(obj instanceof PrescriptionRegister)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		PrescriptionRegister other = (PrescriptionRegister)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

