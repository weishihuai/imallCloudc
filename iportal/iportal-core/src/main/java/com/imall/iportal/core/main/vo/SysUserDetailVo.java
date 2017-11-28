package com.imall.iportal.core.main.vo;


import java.util.List;

/**
 * TODO(Vo)
 * @author by imall core generator
 * @version 1.0.0
 */
public class SysUserDetailVo {

    /**
     * id
     */
    private Long id;

    /**
     * 姓名
     */
    private java.lang.String realName;
    /**
     * 手机号
     */
    private java.lang.String mobile;
    /**
     * 身份证
     */
    private java.lang.String identityCard;
    /**
     * 性别
     */
    private java.lang.String sexName;
    /**
     * 性别
     */
    private java.lang.String sex;
    /**
     * 籍贯
     */
    private java.lang.String nativePlace;
    /**
     * 婚姻状况
     */
    private java.lang.String marriageStatName;
    /**
     * 婚姻状况
     */
    private java.lang.String marriageStatCode;
    /**
     * 出生日期
     */
    private java.lang.String birthday;

    /**
     * 邮箱
     */
    private java.lang.String email;
    /**
     * 备注
     */
    private java.lang.String mark;
    /**
     * 住址
     */
    private java.lang.String homeAddr;
    //教育经历
    /**
     * 学校名称
     */
    private java.lang.String schoolNm;
    /**
     * 专业
     */
    private java.lang.String major;
    /**
     * 学历
     */
    private java.lang.String academicQualificati;

    /**
     * 毕业时间
     */
    private java.lang.String graduationTime;

    /**
     * 参加工作时间
     */
    private java.lang.String joinWorkTime;
    /**
     * 技术职称
     */
    private java.lang.String technologyPostTitle;
    //岗位
    /**
     * 岗位
     */
    private List<Long> jobSelectList;

    //行政信息

    /**
     * 入职时间
     */
    private java.lang.String entryJobTime;
    /**
     * 上岗证号
     */
    private java.lang.String employeeCode;
    /**
     * 上岗培训
     */
    private java.lang.String isPostsTrain;

    /**
     * 离职时间
     */
    private java.lang.String leaveTime;
    /**
     * 离职原因
     */
    private java.lang.String leaveReason;

    //账号与密码

    /**
     * 账号
     */
    private java.lang.String userName;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMarriageStatCode() {
        return marriageStatCode;
    }

    public void setMarriageStatCode(String marriageStatCode) {
        this.marriageStatCode = marriageStatCode;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(String graduationTime) {
        this.graduationTime = graduationTime;
    }

    public String getJoinWorkTime() {
        return joinWorkTime;
    }

    public void setJoinWorkTime(String joinWorkTime) {
        this.joinWorkTime = joinWorkTime;
    }

    public String getEntryJobTime() {
        return entryJobTime;
    }

    public void setEntryJobTime(String entryJobTime) {
        this.entryJobTime = entryJobTime;
    }

    public String getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getMarriageStatName() {
        return marriageStatName;
    }

    public void setMarriageStatName(String marriageStatName) {
        this.marriageStatName = marriageStatName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
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

    public String getTechnologyPostTitle() {
        return technologyPostTitle;
    }

    public void setTechnologyPostTitle(String technologyPostTitle) {
        this.technologyPostTitle = technologyPostTitle;
    }

    public List<Long> getJobSelectList() {
        return jobSelectList;
    }

    public void setJobSelectList(List<Long> jobSelectList) {
        this.jobSelectList = jobSelectList;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getIsPostsTrain() {
        return isPostsTrain;
    }

    public void setIsPostsTrain(String isPostsTrain) {
        this.isPostsTrain = isPostsTrain;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
