package com.imall.iportal.core.main.valid;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by ygw on 2016/5/17.
 */
public class SysUserSaveValid implements Cloneable, java.io.Serializable{

    public static final long serialVersionUID = -15263788L;


    @NotBlank
    @Length(max = 128)
    private String userName;

    @NotBlank
    @Length(max = 128)
    private String realName;

    private String email;

    private String mobile;

    @NotBlank
    @Length(max = 128)
    private String password;

    @NotBlank
    @Length(max = 32)
    private String salt;

    private String sex;
    /** isDefaultAdmin - 是否主岗位 */
    private String isDefaultAdmin;


    private List<Long> jobSelectList;

    @NotNull
    private Long orgId;

    @Length(max = 512)
    private String mark;

    /** IDENTITY_CARD - 身份证 */
    @Length(max = 32)
    private String identityCard;

    /** NATIVE_PLACE - 籍贯 */
    @Length(max = 32)
    private String nativePlace;

    /** MARRIAGE_STAT_CODE - 婚姻 状况 代码 */
    @Length(max = 32)
    private String marriageStatCode;

    /** BIRTHDAY - 生日 */
    private Date birthday;

    /** HOME_ADDR - 住址 */
    @Length(max = 128)
    private String homeAddr;

    /** SCHOOL_NM - 学校 名称 */
    @Length(max = 32)
    private String schoolNm;

    /** MAJOR - 专业 */
    @Length(max = 32)
    private String major;

    /** ACADEMIC_QUALIFICATI - 学历 */
    @Length(max = 32)
    private String academicQualificati;

    /** GRADUATION_TIME - 毕业 时间 */
    private Date graduationTime;

    /** JOIN_WORK_TIME - 参加 工作 时间 */
    private Date joinWorkTime;

    /** TECHNOLOGY_POST_TITLE - 技术 职称 */
    @Length(max = 32)
    private String technologyPostTitle;

    /** ENTRY_JOB_TIME - 入职 时间 */
    private Date entryJobTime;

    /** IS_POSTS_TRAIN - 是否 上岗 培训 */
    @NotBlank
    @Length(max = 1)
    private String isPostsTrain;

    /** LEAVE_TIME - 离职 时间 */
    private Date leaveTime;

    /** LEAVE_REASON - 离职 原因 */
    @Length(max = 128)
    private String leaveReason;

    /** SHOP_ID - 门店 ID */
    private Long shopId;

    public String getIsDefaultAdmin() {
        return isDefaultAdmin;
    }

    public void setIsDefaultAdmin(String isDefaultAdmin) {
        this.isDefaultAdmin = isDefaultAdmin;
    }

    public List<Long> getJobSelectList() {
        return jobSelectList;
    }

    public void setJobSelectList(List<Long> jobSelectList) {
        this.jobSelectList = jobSelectList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getMarriageStatCode() {
        return marriageStatCode;
    }

    public void setMarriageStatCode(String marriageStatCode) {
        this.marriageStatCode = marriageStatCode;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getHomeAddr() {
        return homeAddr;
    }

    public void setHomeAddr(String homeAddr) {
        this.homeAddr = homeAddr;
    }

    public String getSchoolNm() {
        return schoolNm;
    }

    public void setSchoolNm(String schoolNm) {
        this.schoolNm = schoolNm;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getAcademicQualificati() {
        return academicQualificati;
    }

    public void setAcademicQualificati(String academicQualificati) {
        this.academicQualificati = academicQualificati;
    }

    public Date getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(Date graduationTime) {
        this.graduationTime = graduationTime;
    }

    public Date getJoinWorkTime() {
        return joinWorkTime;
    }

    public void setJoinWorkTime(Date joinWorkTime) {
        this.joinWorkTime = joinWorkTime;
    }

    public String getTechnologyPostTitle() {
        return technologyPostTitle;
    }

    public void setTechnologyPostTitle(String technologyPostTitle) {
        this.technologyPostTitle = technologyPostTitle;
    }

    public Date getEntryJobTime() {
        return entryJobTime;
    }

    public void setEntryJobTime(Date entryJobTime) {
        this.entryJobTime = entryJobTime;
    }

    public String getIsPostsTrain() {
        return isPostsTrain;
    }

    public void setIsPostsTrain(String isPostsTrain) {
        this.isPostsTrain = isPostsTrain;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }


}
