
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
@Table(name = "t_shp_measure_record" )
public class MeasureRecord extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String MEASURING_DEVICE_ACCOUNTS_ID = "measuringDeviceAccountsId";
	public static final String MEASURE_DATE = "measureDate";
	public static final String START_TIME = "startTime";
	public static final String END_TIME = "endTime";
	public static final String IDENTIFY_PRJ = "identifyPrj";
	public static final String SKILL_REQUIREMENT = "skillRequirement";
	public static final String MEASURE_METHOD = "measureMethod";
	public static final String IDENTIFY_CONCLUSION = "identifyConclusion";
	public static final String MEASURE_MAN = "measureMan";
	public static final String REVIEW_MAN = "reviewMan";
	public static final String REMARK = "remark";

    /** SHOP_ID - 门店 ID */
    @Column(name = "SHOP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long shopId;
    /** MEASURING_DEVICE_ACCOUNTS_ID - 计量 器具 台账 ID */
    @Column(name = "MEASURING_DEVICE_ACCOUNTS_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long measuringDeviceAccountsId;
    /** MEASURE_DATE - 检测 日期 */
    @Column(name = "MEASURE_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date measureDate;
    /** START_TIME - 开始 时间 */
    @Column(name = "START_TIME", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date startTime;
    /** END_TIME - 结束 时间 */
    @Column(name = "END_TIME", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date endTime;
    /** IDENTIFY_PRJ - 鉴定 项目 */
    @Column(name = "IDENTIFY_PRJ", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String identifyPrj;
    /** SKILL_REQUIREMENT - 技术 要求 */
    @Column(name = "SKILL_REQUIREMENT", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String skillRequirement;
    /** MEASURE_METHOD - 检测 方法 */
    @Column(name = "MEASURE_METHOD", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String measureMethod;
    /** IDENTIFY_CONCLUSION - 鉴定 结论 */
    @Column(name = "IDENTIFY_CONCLUSION", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String identifyConclusion;
    /** MEASURE_MAN - 检测 人 */
    @Column(name = "MEASURE_MAN", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String measureMan;
    /** REVIEW_MAN - 复检人 名称 */
    @Column(name = "REVIEW_MAN", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String reviewMan;
    /** REMARK - 备注 */
    @Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String remark;

    public void setShopId(Long value) {
		this.shopId = value;
	}

    public Long getShopId() {
		return this.shopId;
	}

    public void setMeasuringDeviceAccountsId(Long value) {
		this.measuringDeviceAccountsId = value;
	}

    public Long getMeasuringDeviceAccountsId() {
		return this.measuringDeviceAccountsId;
	}

    public void setMeasureDate(java.util.Date value) {
		this.measureDate = value;
	}

    public java.util.Date getMeasureDate() {
		return this.measureDate;
	}

    public void setStartTime(java.util.Date value) {
		this.startTime = value;
	}

    public java.util.Date getStartTime() {
		return this.startTime;
	}

    public void setEndTime(java.util.Date value) {
		this.endTime = value;
	}

    public java.util.Date getEndTime() {
		return this.endTime;
	}

    public void setIdentifyPrj(String value) {
		this.identifyPrj = value;
	}

    public String getIdentifyPrj() {
		return this.identifyPrj;
	}

    public void setSkillRequirement(String value) {
		this.skillRequirement = value;
	}

    public String getSkillRequirement() {
		return this.skillRequirement;
	}

    public void setMeasureMethod(String value) {
		this.measureMethod = value;
	}

    public String getMeasureMethod() {
		return this.measureMethod;
	}

    public void setIdentifyConclusion(String value) {
		this.identifyConclusion = value;
	}

    public String getIdentifyConclusion() {
		return this.identifyConclusion;
	}

    public void setMeasureMan(String value) {
		this.measureMan = value;
	}

    public String getMeasureMan() {
		return this.measureMan;
	}

    public void setReviewMan(String value) {
		this.reviewMan = value;
	}

    public String getReviewMan() {
		return this.reviewMan;
	}

    public void setRemark(String value) {
		this.remark = value;
	}

    public String getRemark() {
		return this.remark;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SHOP_ID",getShopId())
			.append("MEASURING_DEVICE_ACCOUNTS_ID",getMeasuringDeviceAccountsId())
			.append("MEASURE_DATE",getMeasureDate())
			.append("START_TIME",getStartTime())
			.append("END_TIME",getEndTime())
			.append("IDENTIFY_PRJ",getIdentifyPrj())
			.append("SKILL_REQUIREMENT",getSkillRequirement())
			.append("MEASURE_METHOD",getMeasureMethod())
			.append("IDENTIFY_CONCLUSION",getIdentifyConclusion())
			.append("MEASURE_MAN",getMeasureMan())
			.append("REVIEW_MAN",getReviewMan())
			.append("REMARK",getRemark())
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
		if(!(obj instanceof MeasureRecord)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		MeasureRecord other = (MeasureRecord)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public String getStartTimeString() {
		return DateTimeUtils.convertDateToString(this.getStartTime());
	}

	public void setStartTimeString(String value) throws ParseException {
		this.setStartTime(DateTimeUtils.convertStringToDate(value));
	}

	public String getEndTimeString() {
		return DateTimeUtils.convertDateToString(this.getEndTime());
	}

	public void setEndTimeString(String value) throws ParseException {
		this.setEndTime(DateTimeUtils.convertStringToDate(value));
	}

	public String getMeasureDateString() {
		return DateTimeUtils.convertDateToString(this.getMeasureDate());
	}

	public void setMeasureDateString(String value) throws ParseException {
		this.setMeasureDate(DateTimeUtils.convertStringToDate(value));
	}
}

