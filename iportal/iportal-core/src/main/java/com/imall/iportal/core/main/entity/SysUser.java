
package com.imall.iportal.core.main.entity;

import com.imall.commons.base.entity.BaseEntity;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.iportal.core.main.log.annotation.LogColumnField;
import com.imall.iportal.core.main.log.annotation.LogTableName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * T_SYS_USER【员工】实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "t_pt_sys_user" )
@LogTableName("员工")
public class SysUser extends BaseEntity<Long>{

	public static final String SHOP_ID = "shopId";
	public static final String ORG_ID = "orgId";
	public static final String IS_DEFAULT_ADMIN = "isDefaultAdmin";
	public static final String EMPLOYEE_CODE = "employeeCode";
	public static final String USER_NAME = "userName";
	public static final String REAL_NAME = "realName";
	public static final String EMAIL = "email";
	public static final String MOBILE = "mobile";
	public static final String PASSWORD = "password";
	public static final String SEX = "sex";
	public static final String SALT = "salt";
	public static final String IS_ENABLE = "isEnable";
	public static final String IS_DELETED = "isDeleted";
	public static final String IDENTITY_CARD = "identityCard";
	public static final String NATIVE_PLACE = "nativePlace";
	public static final String MARRIAGE_STAT_CODE = "marriageStatCode";
	public static final String BIRTHDAY = "birthday";
	public static final String HOME_ADDR = "homeAddr";
	public static final String SCHOOL_NM = "schoolNm";
	public static final String MAJOR = "major";
	public static final String ACADEMIC_QUALIFICATI = "academicQualificati";
	public static final String GRADUATION_TIME = "graduationTime";
	public static final String JOIN_WORK_TIME = "joinWorkTime";
	public static final String TECHNOLOGY_POST_TITLE = "technologyPostTitle";
	public static final String ENTRY_JOB_TIME = "entryJobTime";
	public static final String IS_POSTS_TRAIN = "isPostsTrain";
	public static final String LEAVE_TIME = "leaveTime";
	public static final String LEAVE_REASON = "leaveReason";
	public static final String MARK = "mark";

	/** SHOP_ID - 门店 ID */
	@Column(name = "SHOP_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	private java.lang.Long shopId;
	/** ORG_ID - 所属组织机构 ID */
	@Column(name = "ORG_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
	private java.lang.Long orgId;
	/** IS_DEFAULT_ADMIN - 是否 默认 管理员 */
	@Column(name = "IS_DEFAULT_ADMIN", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
	private java.lang.String isDefaultAdmin;
	/** EMPLOYEE_CODE - 上岗 证号 */
	@LogColumnField("上岗证号")
	@Column(name = "EMPLOYEE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	private java.lang.String employeeCode;
	/** USER_NAME - 用户名称，用户登陆名 */
	@LogColumnField("用户名称")
	@Column(name = "USER_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
	private java.lang.String userName;
	/** REAL_NAME - 真实姓名 */
	@LogColumnField("真实姓名")
	@Column(name = "REAL_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 64)
	private java.lang.String realName;
	/** EMAIL - 邮箱 */
	@LogColumnField("邮箱")
	@Column(name = "EMAIL", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	private java.lang.String email;
	/** MOBILE - 手机号 */
	@LogColumnField("手机号")
	@Column(name = "MOBILE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private java.lang.String mobile;
	/** PASSWORD - 密码 */
	@Column(name = "PASSWORD", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
	private java.lang.String password;
	/** SEX - 性别 */
	@LogColumnField("性别")
	@Column(name = "SEX", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private java.lang.String sex;
	/** SALT - 加密密码时使用的种子 */
	@Column(name = "SALT", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	private java.lang.String salt;
	/** IS_ENABLE - 状态：0-锁定  1-激活 */
	@LogColumnField("状态")
	@Column(name = "IS_ENABLE", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
	private java.lang.String isEnable;
	/** IS_DELETED - 是否删除 */
	@LogColumnField("是否删除")
	@Column(name = "IS_DELETED", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
	private java.lang.String isDeleted;
	/** IDENTITY_CARD - 身份证 */
	@LogColumnField("身份证")
	@Column(name = "IDENTITY_CARD", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private java.lang.String identityCard;
	/** NATIVE_PLACE - 籍贯 */
	@LogColumnField("籍贯")
	@Column(name = "NATIVE_PLACE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private java.lang.String nativePlace;
	@LogColumnField("婚姻状况代码")
	/** MARRIAGE_STAT_CODE - 婚姻 状况 代码 */
	@Column(name = "MARRIAGE_STAT_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private java.lang.String marriageStatCode;
	/** BIRTHDAY - 生日 */
	@LogColumnField("生日")
	@Column(name = "BIRTHDAY", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	private java.util.Date birthday;
	/** HOME_ADDR - 住址 */
	@LogColumnField("住址")
	@Column(name = "HOME_ADDR", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	private java.lang.String homeAddr;
	/** SCHOOL_NM - 学校 名称 */
	@LogColumnField("学校名称")
	@Column(name = "SCHOOL_NM", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private java.lang.String schoolNm;
	/** MAJOR - 专业 */
	@LogColumnField("专业")
	@Column(name = "MAJOR", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private java.lang.String major;
	/** ACADEMIC_QUALIFICATI - 学历 */
	@LogColumnField("学历")
	@Column(name = "ACADEMIC_QUALIFICATI", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private java.lang.String academicQualificati;
	/** GRADUATION_TIME - 毕业 时间 */
	@LogColumnField("毕业时间")
	@Column(name = "GRADUATION_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	private java.util.Date graduationTime;
	/** JOIN_WORK_TIME - 参加 工作 时间 */
	@LogColumnField("参加工作时间")
	@Column(name = "JOIN_WORK_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	private java.util.Date joinWorkTime;
	/** TECHNOLOGY_POST_TITLE - 技术 职称 */
	@LogColumnField("技术职称")
	@Column(name = "TECHNOLOGY_POST_TITLE", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	private java.lang.String technologyPostTitle;
	/** ENTRY_JOB_TIME - 入职 时间 */
	@LogColumnField("入职时间")
	@Column(name = "ENTRY_JOB_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	private java.util.Date entryJobTime;
	/** IS_POSTS_TRAIN - 是否 上岗 培训 */
	@LogColumnField("是否上岗培训")
	@Column(name = "IS_POSTS_TRAIN", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
	private java.lang.String isPostsTrain;
	/** LEAVE_TIME - 离职 时间 */
	@LogColumnField("离职时间")
	@Column(name = "LEAVE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 0)
	private java.util.Date leaveTime;
	/** LEAVE_REASON - 离职 原因 */
	@LogColumnField("离职原因")
	@Column(name = "LEAVE_REASON", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	private java.lang.String leaveReason;
	/** MARK - 备注 */
	@LogColumnField("备注")
	@Column(name = "MARK", unique = false, nullable = true, insertable = true, updatable = true, length = 512)
	private java.lang.String mark;

	public void setShopId(java.lang.Long value) {
		this.shopId = value;
	}

	public java.lang.Long getShopId() {
		return this.shopId;
	}

	public void setOrgId(java.lang.Long value) {
		this.orgId = value;
	}

	public java.lang.Long getOrgId() {
		return this.orgId;
	}

	public void setIsDefaultAdmin(java.lang.String value) {
		this.isDefaultAdmin = value;
	}

	public java.lang.String getIsDefaultAdmin() {
		return this.isDefaultAdmin;
	}

	public void setEmployeeCode(java.lang.String value) {
		this.employeeCode = value;
	}

	public java.lang.String getEmployeeCode() {
		return this.employeeCode;
	}

	public void setUserName(java.lang.String value) {
		this.userName = value;
	}

	public java.lang.String getUserName() {
		return this.userName;
	}

	public void setRealName(java.lang.String value) {
		this.realName = value;
	}

	public java.lang.String getRealName() {
		return this.realName;
	}

	public void setEmail(java.lang.String value) {
		this.email = value;
	}

	public java.lang.String getEmail() {
		return this.email;
	}

	public void setMobile(java.lang.String value) {
		this.mobile = value;
	}

	public java.lang.String getMobile() {
		return this.mobile;
	}

	public void setPassword(java.lang.String value) {
		this.password = value;
	}

	public java.lang.String getPassword() {
		return this.password;
	}

	public void setSex(java.lang.String value) {
		this.sex = value;
	}

	public java.lang.String getSex() {
		return this.sex;
	}

	public void setSalt(java.lang.String value) {
		this.salt = value;
	}

	public java.lang.String getSalt() {
		return this.salt;
	}

	public void setIsEnable(java.lang.String value) {
		this.isEnable = value;
	}

	public java.lang.String getIsEnable() {
		return this.isEnable;
	}

	public void setIsDeleted(java.lang.String value) {
		this.isDeleted = value;
	}

	public java.lang.String getIsDeleted() {
		return this.isDeleted;
	}

	public void setIdentityCard(java.lang.String value) {
		this.identityCard = value;
	}

	public java.lang.String getIdentityCard() {
		return this.identityCard;
	}

	public void setNativePlace(java.lang.String value) {
		this.nativePlace = value;
	}

	public java.lang.String getNativePlace() {
		return this.nativePlace;
	}

	public void setMarriageStatCode(java.lang.String value) {
		this.marriageStatCode = value;
	}

	public java.lang.String getMarriageStatCode() {
		return this.marriageStatCode;
	}

	public void setBirthday(java.util.Date value) {
		this.birthday = value;
	}

	public java.util.Date getBirthday() {
		return this.birthday;
	}
	public String getBirthdayString() {
		return this.birthday!=null? DateTimeUtils.convertDateToString(this.birthday):null;
	}

	public void setHomeAddr(java.lang.String value) {
		this.homeAddr = value;
	}

	public java.lang.String getHomeAddr() {
		return this.homeAddr;
	}

	public void setSchoolNm(java.lang.String value) {
		this.schoolNm = value;
	}

	public java.lang.String getSchoolNm() {
		return this.schoolNm;
	}

	public void setMajor(java.lang.String value) {
		this.major = value;
	}

	public java.lang.String getMajor() {
		return this.major;
	}

	public void setAcademicQualificati(java.lang.String value) {
		this.academicQualificati = value;
	}

	public java.lang.String getAcademicQualificati() {
		return this.academicQualificati;
	}

	public void setGraduationTime(java.util.Date value) {
		this.graduationTime = value;
	}

	public java.util.Date getGraduationTime() {
		return this.graduationTime;
	}
	public String getGraduationTimeString() {
		return this.graduationTime!=null? DateTimeUtils.convertDateToString(this.graduationTime):null;
	}

	public void setJoinWorkTime(java.util.Date value) {
		this.joinWorkTime = value;
	}

	public java.util.Date getJoinWorkTime() {
		return this.joinWorkTime;
	}
	public String getJoinWorkTimeString() {
		return this.joinWorkTime!=null? DateTimeUtils.convertDateToString(this.joinWorkTime):null;
	}

	public void setTechnologyPostTitle(java.lang.String value) {
		this.technologyPostTitle = value;
	}

	public java.lang.String getTechnologyPostTitle() {
		return this.technologyPostTitle;
	}

	public void setEntryJobTime(java.util.Date value) {
		this.entryJobTime = value;
	}

	public java.util.Date getEntryJobTime() {
		return this.entryJobTime;
	}
	public String getEntryJobTimeString() {
		return this.entryJobTime!=null? DateTimeUtils.convertDateToString(this.entryJobTime):null;
	}

	public void setIsPostsTrain(java.lang.String value) {
		this.isPostsTrain = value;
	}

	public java.lang.String getIsPostsTrain() {
		return this.isPostsTrain;
	}

	public void setLeaveTime(java.util.Date value) {
		this.leaveTime = value;
	}

	public java.util.Date getLeaveTime() {
		return this.leaveTime;
	}
	public String getLeaveTimeString() {
		return this.leaveTime!=null? DateTimeUtils.convertDateToString(this.leaveTime):null;
	}

	public void setLeaveReason(java.lang.String value) {
		this.leaveReason = value;
	}

	public java.lang.String getLeaveReason() {
		return this.leaveReason;
	}

	public void setMark(java.lang.String value) {
		this.mark = value;
	}

	public java.lang.String getMark() {
		return this.mark;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("ID",getId())
				.append("SHOP_ID",getShopId())
				.append("ORG_ID",getOrgId())
				.append("IS_DEFAULT_ADMIN",getIsDefaultAdmin())
				.append("EMPLOYEE_CODE",getEmployeeCode())
				.append("USER_NAME",getUserName())
				.append("REAL_NAME",getRealName())
				.append("EMAIL",getEmail())
				.append("MOBILE",getMobile())
				.append("PASSWORD",getPassword())
				.append("SEX",getSex())
				.append("SALT",getSalt())
				.append("IS_ENABLE",getIsEnable())
				.append("IS_DELETED",getIsDeleted())
				.append("IDENTITY_CARD",getIdentityCard())
				.append("NATIVE_PLACE",getNativePlace())
				.append("MARRIAGE_STAT_CODE",getMarriageStatCode())
				.append("BIRTHDAY",getBirthday())
				.append("HOME_ADDR",getHomeAddr())
				.append("SCHOOL_NM",getSchoolNm())
				.append("MAJOR",getMajor())
				.append("ACADEMIC_QUALIFICATI",getAcademicQualificati())
				.append("GRADUATION_TIME",getGraduationTime())
				.append("JOIN_WORK_TIME",getJoinWorkTime())
				.append("TECHNOLOGY_POST_TITLE",getTechnologyPostTitle())
				.append("ENTRY_JOB_TIME",getEntryJobTime())
				.append("IS_POSTS_TRAIN",getIsPostsTrain())
				.append("LEAVE_TIME",getLeaveTime())
				.append("LEAVE_REASON",getLeaveReason())
				.append("MARK",getMark())
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
		if(!(obj instanceof SysUser)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		SysUser other = (SysUser)obj;
		return new EqualsBuilder()
				.append(getId(),other.getId())
				.isEquals();
	}
}

