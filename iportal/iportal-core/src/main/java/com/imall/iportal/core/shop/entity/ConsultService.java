
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
@Table(name = "t_shp_consult_service" )
public class ConsultService extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String MEMBER_CARD_NUM = "memberCardNum";
	public static final String PATIENT_NAME = "patientName";
	public static final String AGE = "age";
	public static final String SEX = "sex";
	public static final String MOBILE = "mobile";
	public static final String IDENTITY_CARD = "identityCard";
	public static final String HEIGHT = "height";
	public static final String WEIGHT = "weight";
	public static final String ADDR = "addr";
	public static final String REBAK_FUNCTION = "rebakFunction";
	public static final String IS_PREGNANT = "isPregnant";
	public static final String PREV_MEDICAL_HISTORY = "prevMedicalHistory";
	public static final String CONSULT_PHARMACIST = "consultPharmacist";
	public static final String CONSULT_TIME = "consultTime";
	public static final String QUESTION_DESCR = "questionDescr";
	public static final String EXPERT_ANSWER = "expertAnswer";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private java.lang.Long shopId;
    /** MEMBER_CARD_NUM - 会员 卡号 */
    @Column(name = "MEMBER_CARD_NUM", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String memberCardNum;
    /** PATIENT_NAME - 患者 姓名 */
    @Column(name = "PATIENT_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private java.lang.String patientName;
    /** AGE - 年龄 */
    @Column(name = "AGE", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
    private java.lang.Integer age;
    /** SEX - 性别 */
    @Column(name = "SEX", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String sex;
    /** MOBILE - 手机 */
    @Column(name = "MOBILE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String mobile;
    /** IDENTITY_CARD - 身份证 */
    @Column(name = "IDENTITY_CARD", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String identityCard;
    /** HEIGHT - 身高 */
    @Column(name = "HEIGHT", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private java.lang.Integer height;
    /** WEIGHT - 体重 */
    @Column(name = "WEIGHT", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    private java.lang.Double weight;
    /** ADDR - 地址 */
    @Column(name = "ADDR", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private java.lang.String addr;
    /** REBAK_FUNCTION - 肾功能 */
    @Column(name = "REBAK_FUNCTION", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
    private java.lang.String rebakFunction;
    /** IS_PREGNANT - 是否 怀孕 */
    @Column(name = "IS_PREGNANT", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
    private java.lang.String isPregnant;
    /** PREV_MEDICAL_HISTORY - 过往 病史 */
    @Column(name = "PREV_MEDICAL_HISTORY", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private java.lang.String prevMedicalHistory;
    /** CONSULT_PHARMACIST - 咨询 药师 */
    @Column(name = "CONSULT_PHARMACIST", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private java.lang.String consultPharmacist;
    /** CONSULT_TIME - 咨询 时间 */
    @Column(name = "CONSULT_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
    private java.util.Date consultTime;
    /** QUESTION_DESCR - 问题 描述 */
    @Column(name = "QUESTION_DESCR", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private java.lang.String questionDescr;
    /** EXPERT_ANSWER - 药师 解答 */
    @Column(name = "EXPERT_ANSWER", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
    private java.lang.String expertAnswer;

    public void setShopId(java.lang.Long value) {
		this.shopId = value;
	}

    public java.lang.Long getShopId() {
		return this.shopId;
	}

    public void setMemberCardNum(java.lang.String value) {
		this.memberCardNum = value;
	}

    public java.lang.String getMemberCardNum() {
		return this.memberCardNum;
	}

    public void setPatientName(java.lang.String value) {
		this.patientName = value;
	}

    public java.lang.String getPatientName() {
		return this.patientName;
	}

    public void setAge(java.lang.Integer value) {
		this.age = value;
	}

    public java.lang.Integer getAge() {
		return this.age;
	}

    public void setSex(java.lang.String value) {
		this.sex = value;
	}

    public java.lang.String getSex() {
		return this.sex;
	}

    public void setMobile(java.lang.String value) {
		this.mobile = value;
	}

    public java.lang.String getMobile() {
		return this.mobile;
	}

    public void setIdentityCard(java.lang.String value) {
		this.identityCard = value;
	}

    public java.lang.String getIdentityCard() {
		return this.identityCard;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public void setWeight(java.lang.Double value) {
		this.weight = value;
	}

    public java.lang.Double getWeight() {
		return this.weight;
	}

    public void setAddr(java.lang.String value) {
		this.addr = value;
	}

    public java.lang.String getAddr() {
		return this.addr;
	}

    public void setRebakFunction(java.lang.String value) {
		this.rebakFunction = value;
	}

    public java.lang.String getRebakFunction() {
		return this.rebakFunction;
	}

    public void setIsPregnant(java.lang.String value) {
		this.isPregnant = value;
	}

    public java.lang.String getIsPregnant() {
		return this.isPregnant;
	}

    public void setPrevMedicalHistory(java.lang.String value) {
		this.prevMedicalHistory = value;
	}

    public java.lang.String getPrevMedicalHistory() {
		return this.prevMedicalHistory;
	}

    public void setConsultPharmacist(java.lang.String value) {
		this.consultPharmacist = value;
	}

    public java.lang.String getConsultPharmacist() {
		return this.consultPharmacist;
	}

    public void setConsultTime(java.util.Date value) {
		this.consultTime = value;
	}

    public java.util.Date getConsultTime() {
		return this.consultTime;
	}

    public void setQuestionDescr(java.lang.String value) {
		this.questionDescr = value;
	}

    public java.lang.String getQuestionDescr() {
		return this.questionDescr;
	}

    public void setExpertAnswer(java.lang.String value) {
		this.expertAnswer = value;
	}

    public java.lang.String getExpertAnswer() {
		return this.expertAnswer;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("MEMBER_CARD_NUM",getMemberCardNum())
			.append("PATIENT_NAME",getPatientName())
			.append("AGE",getAge())
			.append("SEX",getSex())
			.append("MOBILE",getMobile())
			.append("IDENTITY_CARD",getIdentityCard())
			.append("HEIGHT",getHeight())
			.append("WEIGHT",getWeight())
			.append("ADDR",getAddr())
			.append("REBAK_FUNCTION",getRebakFunction())
			.append("IS_PREGNANT",getIsPregnant())
			.append("PREV_MEDICAL_HISTORY",getPrevMedicalHistory())
			.append("CONSULT_PHARMACIST",getConsultPharmacist())
			.append("CONSULT_TIME",getConsultTime())
			.append("QUESTION_DESCR",getQuestionDescr())
			.append("EXPERT_ANSWER",getExpertAnswer())
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
		if(!(obj instanceof ConsultService)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		ConsultService other = (ConsultService)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public String getConsultTimeString() {
		return DateTimeUtils.convertDateToString(getConsultTime());
	}

	public void setConsultTimeString(String value) throws ParseException {
		setConsultTime(DateTimeUtils.convertStringToDate(value));
	}

}

