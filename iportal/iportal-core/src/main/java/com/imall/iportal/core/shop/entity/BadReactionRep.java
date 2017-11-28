
package com.imall.iportal.core.shop.entity;

import com.imall.commons.base.entity.BaseEntity;
import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.text.ParseException;

/**
 * 实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_shp_bad_reaction_rep" )
public class BadReactionRep extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String IS_FIRST_REP = "isFirstRep";
	public static final String REP_TYPE = "repType";
	public static final String REP_DEPARTMENT = "repDepartment";
	public static final String CODE = "code";
	public static final String PATIENT_NAME = "patientName";
	public static final String SEX_CODE = "sexCode";
	public static final String BIRTH_DATE = "birthDate";
	public static final String AGE = "age";
	public static final String NATION = "nation";
	public static final String WEIGHT = "weight";
	public static final String CONTACT_WAY = "contactWay";
	public static final String ORIGINAL_DISEASE = "originalDisease";
	public static final String HOSPITAL_NM = "hospitalNm";
	public static final String MEDICAL_RECORD_NO = "medicalRecordNo";
	public static final String PAST_DRUG_BAD_REACTION = "pastDrugBadReaction";
	public static final String FAMILY_DRUG_BAD_REACTION = "familyDrugBadReaction";
	public static final String REFER_IMPORTANT_INF = "referImportantInf";
	public static final String BAD_REACTION = "badReaction";
	public static final String BAD_REACTION_OCCUR_TIME = "badReactionOccurTime";
	public static final String BAD_REACTION_RESULT = "badReactionResult";
	public static final String REP_DATE = "repDate";
	public static final String SEQUELA_PERFORM = "sequelaPerform";
	public static final String CAUSE_OF_DEATH = "causeOfDeath";
	public static final String DEAD_TIME = "deadTime";
	public static final String RESPONSE_IS_EASE = "responseIsEase";
	public static final String IS_APPEAR_AGAIN = "isAppearAgain";
	public static final String EFFECT_TO_ORIGINAL_DISEASE = "effectToOriginalDisease";
	public static final String REP_MAN_EVALUATE = "repManEvaluate";
	public static final String REP_MAN_TEL = "repManTel";
	public static final String REP_MAN_PROFESSION = "repManProfession";
	public static final String REP_MAN_EMAIL = "repManEmail";
	public static final String REP_MAN_NAME = "repManName";
	public static final String REP_DEPARTMENT_EVALUATE = "repDepartmentEvaluate";
	public static final String REP_DEPARTMENT_NM = "repDepartmentNm";
	public static final String REP_DEPARTMENT_CONTACT_MAN = "repDepartmentContactMan";
	public static final String REP_DEPARTMENT_TEL = "repDepartmentTel";
	public static final String INF_SOURCE = "infSource";
	public static final String REMARK = "remark";
	public static final String BAD_REACTION_PROCESS_DESCR = "badReactionProcessDescr";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;
    /** IS_FIRST_REP - 是否 首次 报告 */
    @Column(name = "IS_FIRST_REP", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String isFirstRep;
    /** REP_TYPE - 报告 类型 */
    @Column(name = "REP_TYPE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String repType;
    /** REP_DEPARTMENT - 报告 单位 类别 */
    @Column(name = "REP_DEPARTMENT", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String repDepartment;
    /** CODE - 编码 */
    @Column(name = "CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String code;
    /** PATIENT_NAME - 患者 姓名 */
    @Column(name = "PATIENT_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String patientName;
    /** SEX_CODE - 性别 代码 */
    @Column(name = "SEX_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String sexCode;
    /** BIRTH_DATE - 出生 日期 */
    @Column(name = "BIRTH_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date birthDate;
    /** AGE - 年龄 */
    @Column(name = "AGE", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
    private Integer age;
    /** NATION - 民族 */
    @Column(name = "NATION", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String nation;
    /** WEIGHT - 体重 */
    @Column(name = "WEIGHT", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private Double weight;
    /** CONTACT_WAY - 联系 方式 */
    @Column(name = "CONTACT_WAY", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String contactWay;
    /** ORIGINAL_DISEASE - 原患疾病 */
    @Column(name = "ORIGINAL_DISEASE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String originalDisease;
    /** HOSPITAL_NM - 医院 名称 */
    @Column(name = "HOSPITAL_NM", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String hospitalNm;
    /** MEDICAL_RECORD_NO - 病历号 */
    @Column(name = "MEDICAL_RECORD_NO", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String medicalRecordNo;
    /** PAST_DRUG_BAD_REACTION - 既往 药品 不良反应 */
    @Column(name = "PAST_DRUG_BAD_REACTION", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String pastDrugBadReaction;
    /** FAMILY_DRUG_BAD_REACTION - 家族 药品 不良反应 */
    @Column(name = "FAMILY_DRUG_BAD_REACTION", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String familyDrugBadReaction;
    /** REFER_IMPORTANT_INF - 相关 重要 信息 */
    @Column(name = "REFER_IMPORTANT_INF", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String referImportantInf;
    /** BAD_REACTION - 不良反应 */
    @Column(name = "BAD_REACTION", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String badReaction;
    /** BAD_REACTION_OCCUR_TIME - 不良反应 发生 时间 */
    @Column(name = "BAD_REACTION_OCCUR_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date badReactionOccurTime;
    /** BAD_REACTION_RESULT - 不良反应 结果 */
    @Column(name = "BAD_REACTION_RESULT", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String badReactionResult;
    /** REP_DATE - 报告 日期 */
    @Column(name = "REP_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date repDate;
    /** SEQUELA_PERFORM - 后遗症 表现 */
    @Column(name = "SEQUELA_PERFORM", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String sequelaPerform;
    /** CAUSE_OF_DEATH - 直接 死因 */
    @Column(name = "CAUSE_OF_DEATH", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String causeOfDeath;
    /** DEAD_TIME - 死亡 时间 */
    @Column(name = "DEAD_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date deadTime;
    /** RESPONSE_IS_EASE - 反应 是否 减轻 */
    @Column(name = "RESPONSE_IS_EASE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String responseIsEase;
    /** IS_APPEAR_AGAIN - 是否 再次 出现 */
    @Column(name = "IS_APPEAR_AGAIN", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String isAppearAgain;
    /** EFFECT_TO_ORIGINAL_DISEASE - 对原患疾病 影响 */
    @Column(name = "EFFECT_TO_ORIGINAL_DISEASE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String effectToOriginalDisease;
    /** REP_MAN_EVALUATE - 报告 人 评价 */
    @Column(name = "REP_MAN_EVALUATE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String repManEvaluate;
    /** REP_MAN_TEL - 报告 人 电话 */
    @Column(name = "REP_MAN_TEL", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String repManTel;
    /** REP_MAN_PROFESSION - 报告 人 职业 */
    @Column(name = "REP_MAN_PROFESSION", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String repManProfession;
    /** REP_MAN_EMAIL - 报告 人 邮箱 */
    @Column(name = "REP_MAN_EMAIL", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String repManEmail;
    /** REP_MAN_NAME - 报告 人 姓名 */
    @Column(name = "REP_MAN_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String repManName;
    /** REP_DEPARTMENT_EVALUATE - 报告 单位 评价 */
    @Column(name = "REP_DEPARTMENT_EVALUATE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String repDepartmentEvaluate;
    /** REP_DEPARTMENT_NM - 报告 单位 名称 */
    @Column(name = "REP_DEPARTMENT_NM", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String repDepartmentNm;
    /** REP_DEPARTMENT_CONTACT_MAN - 联系 人 */
    @Column(name = "REP_DEPARTMENT_CONTACT_MAN", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String repDepartmentContactMan;
    /** REP_DEPARTMENT_TEL - 报告 单位 电话 */
    @Column(name = "REP_DEPARTMENT_TEL", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String repDepartmentTel;
    /** INF_SOURCE - 信息 来源 */
    @Column(name = "INF_SOURCE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String infSource;
    /** REMARK - 备注 */
    @Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
    private String remark;
    /** BAD_REACTION_PROCESS_DESCR - 不良 反应 过程 描述 */
    @Column(name = "BAD_REACTION_PROCESS_DESCR", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
    private String badReactionProcessDescr;

    public void setShopId(Long value) {
		this.shopId = value;
	}

    public Long getShopId() {
		return this.shopId;
	}

    public void setIsFirstRep(String value) {
		this.isFirstRep = value;
	}

    public String getIsFirstRep() {
		return this.isFirstRep;
	}

    public void setRepType(String value) {
		this.repType = value;
	}

    public String getRepType() {
		return this.repType;
	}

    public void setRepDepartment(String value) {
		this.repDepartment = value;
	}

    public String getRepDepartment() {
		return this.repDepartment;
	}

    public void setCode(String value) {
		this.code = value;
	}

    public String getCode() {
		return this.code;
	}

    public void setPatientName(String value) {
		this.patientName = value;
	}

    public String getPatientName() {
		return this.patientName;
	}

    public void setSexCode(String value) {
		this.sexCode = value;
	}

    public String getSexCode() {
		return this.sexCode;
	}

    public void setBirthDate(java.util.Date value) {
		this.birthDate = value;
	}

    public java.util.Date getBirthDate() {
		return this.birthDate;
	}

    public void setAge(Integer value) {
		this.age = value;
	}

    public Integer getAge() {
		return this.age;
	}

    public void setNation(String value) {
		this.nation = value;
	}

    public String getNation() {
		return this.nation;
	}

    public void setWeight(Double value) {
		this.weight = value;
	}

    public Double getWeight() {
		return this.weight;
	}

    public void setContactWay(String value) {
		this.contactWay = value;
	}

    public String getContactWay() {
		return this.contactWay;
	}

    public void setOriginalDisease(String value) {
		this.originalDisease = value;
	}

    public String getOriginalDisease() {
		return this.originalDisease;
	}

    public void setHospitalNm(String value) {
		this.hospitalNm = value;
	}

    public String getHospitalNm() {
		return this.hospitalNm;
	}

    public void setMedicalRecordNo(String value) {
		this.medicalRecordNo = value;
	}

    public String getMedicalRecordNo() {
		return this.medicalRecordNo;
	}

    public void setPastDrugBadReaction(String value) {
		this.pastDrugBadReaction = value;
	}

    public String getPastDrugBadReaction() {
		return this.pastDrugBadReaction;
	}

    public void setFamilyDrugBadReaction(String value) {
		this.familyDrugBadReaction = value;
	}

    public String getFamilyDrugBadReaction() {
		return this.familyDrugBadReaction;
	}

    public void setReferImportantInf(String value) {
		this.referImportantInf = value;
	}

    public String getReferImportantInf() {
		return this.referImportantInf;
	}

    public void setBadReaction(String value) {
		this.badReaction = value;
	}

    public String getBadReaction() {
		return this.badReaction;
	}

    public void setBadReactionOccurTime(java.util.Date value) {
		this.badReactionOccurTime = value;
	}

    public java.util.Date getBadReactionOccurTime() {
		return this.badReactionOccurTime;
	}

    public void setBadReactionResult(String value) {
		this.badReactionResult = value;
	}

    public String getBadReactionResult() {
		return this.badReactionResult;
	}

    public void setRepDate(java.util.Date value) {
		this.repDate = value;
	}

    public java.util.Date getRepDate() {
		return this.repDate;
	}

    public void setSequelaPerform(String value) {
		this.sequelaPerform = value;
	}

    public String getSequelaPerform() {
		return this.sequelaPerform;
	}

    public void setCauseOfDeath(String value) {
		this.causeOfDeath = value;
	}

    public String getCauseOfDeath() {
		return this.causeOfDeath;
	}

    public void setDeadTime(java.util.Date value) {
		this.deadTime = value;
	}

    public java.util.Date getDeadTime() {
		return this.deadTime;
	}

    public void setResponseIsEase(String value) {
		this.responseIsEase = value;
	}

    public String getResponseIsEase() {
		return this.responseIsEase;
	}

    public void setIsAppearAgain(String value) {
		this.isAppearAgain = value;
	}

    public String getIsAppearAgain() {
		return this.isAppearAgain;
	}

    public void setEffectToOriginalDisease(String value) {
		this.effectToOriginalDisease = value;
	}

    public String getEffectToOriginalDisease() {
		return this.effectToOriginalDisease;
	}

    public void setRepManEvaluate(String value) {
		this.repManEvaluate = value;
	}

    public String getRepManEvaluate() {
		return this.repManEvaluate;
	}

    public void setRepManTel(String value) {
		this.repManTel = value;
	}

    public String getRepManTel() {
		return this.repManTel;
	}

    public void setRepManProfession(String value) {
		this.repManProfession = value;
	}

    public String getRepManProfession() {
		return this.repManProfession;
	}

    public void setRepManEmail(String value) {
		this.repManEmail = value;
	}

    public String getRepManEmail() {
		return this.repManEmail;
	}

	public String getRepManName() {
		return repManName;
	}

	public void setRepManName(String repManName) {
		this.repManName = repManName;
	}

	public void setRepDepartmentEvaluate(String value) {
		this.repDepartmentEvaluate = value;
	}

    public String getRepDepartmentEvaluate() {
		return this.repDepartmentEvaluate;
	}

    public void setRepDepartmentNm(String value) {
		this.repDepartmentNm = value;
	}

    public String getRepDepartmentNm() {
		return this.repDepartmentNm;
	}

    public void setRepDepartmentContactMan(String value) {
		this.repDepartmentContactMan = value;
	}

    public String getRepDepartmentContactMan() {
		return this.repDepartmentContactMan;
	}

    public void setRepDepartmentTel(String value) {
		this.repDepartmentTel = value;
	}

    public String getRepDepartmentTel() {
		return this.repDepartmentTel;
	}

    public void setInfSource(String value) {
		this.infSource = value;
	}

    public String getInfSource() {
		return this.infSource;
	}

    public void setRemark(String value) {
		this.remark = value;
	}

    public String getRemark() {
		return this.remark;
	}

    public void setBadReactionProcessDescr(String value) {
		this.badReactionProcessDescr = value;
	}

    public String getBadReactionProcessDescr() {
		return this.badReactionProcessDescr;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("IS_FIRST_REP",getIsFirstRep())
			.append("REP_TYPE",getRepType())
			.append("REP_DEPARTMENT",getRepDepartment())
			.append("CODE",getCode())
			.append("PATIENT_NAME",getPatientName())
			.append("SEX_CODE",getSexCode())
			.append("BIRTH_DATE",getBirthDate())
			.append("AGE",getAge())
			.append("NATION",getNation())
			.append("WEIGHT",getWeight())
			.append("CONTACT_WAY",getContactWay())
			.append("ORIGINAL_DISEASE",getOriginalDisease())
			.append("HOSPITAL_NM",getHospitalNm())
			.append("MEDICAL_RECORD_NO",getMedicalRecordNo())
			.append("PAST_DRUG_BAD_REACTION",getPastDrugBadReaction())
			.append("FAMILY_DRUG_BAD_REACTION",getFamilyDrugBadReaction())
			.append("REFER_IMPORTANT_INF",getReferImportantInf())
			.append("BAD_REACTION",getBadReaction())
			.append("BAD_REACTION_OCCUR_TIME",getBadReactionOccurTime())
			.append("BAD_REACTION_RESULT",getBadReactionResult())
			.append("REP_DATE",getRepDate())
			.append("SEQUELA_PERFORM",getSequelaPerform())
			.append("CAUSE_OF_DEATH",getCauseOfDeath())
			.append("DEAD_TIME",getDeadTime())
			.append("RESPONSE_IS_EASE",getResponseIsEase())
			.append("IS_APPEAR_AGAIN",getIsAppearAgain())
			.append("EFFECT_TO_ORIGINAL_DISEASE",getEffectToOriginalDisease())
			.append("REP_MAN_EVALUATE",getRepManEvaluate())
			.append("REP_MAN_TEL",getRepManTel())
			.append("REP_MAN_PROFESSION",getRepManProfession())
			.append("REP_MAN_EMAIL",getRepManEmail())
			.append("REP_MAN_NAME",getRepManName())
			.append("REP_DEPARTMENT_EVALUATE",getRepDepartmentEvaluate())
			.append("REP_DEPARTMENT_NM",getRepDepartmentNm())
			.append("REP_DEPARTMENT_CONTACT_MAN",getRepDepartmentContactMan())
			.append("REP_DEPARTMENT_TEL",getRepDepartmentTel())
			.append("INF_SOURCE",getInfSource())
			.append("REMARK",getRemark())
			.append("BAD_REACTION_PROCESS_DESCR",getBadReactionProcessDescr())
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
		if(!(obj instanceof BadReactionRep)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		BadReactionRep other = (BadReactionRep)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public String getRepDateString() {
		return DateTimeUtils.convertDateToString(this.getRepDate());
	}

	public void setRepDateString(String value) throws ParseException {
		this.setRepDate(DateTimeUtils.convertStringToDate(value));
	}

	public String getBadReactionOccurTimeString() {
		return DateTimeUtils.convertDateToString(this.getBadReactionOccurTime());
	}

	public void setBadReactionOccurTimeString(String value) throws ParseException {
		this.setBadReactionOccurTime(DateTimeUtils.convertStringToDate(value));
	}

	public String getDeadTimeString() {
		return DateTimeUtils.convertDateToString(this.getDeadTime());
	}

	public void setDeadTimeString(String value) throws ParseException {
		this.setDeadTime(DateTimeUtils.convertStringToDate(value));
	}

	public String getBirthDateString() {
		return DateTimeUtils.convertDateToString(this.getBirthDate());
	}

	public void setBirthDateString(String value) throws ParseException {
		this.setBirthDate(DateTimeUtils.convertStringToDate(value));
	}

}

