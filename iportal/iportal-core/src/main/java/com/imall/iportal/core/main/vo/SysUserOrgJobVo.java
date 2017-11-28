package com.imall.iportal.core.main.vo;


import com.imall.iportal.core.main.entity.SysUserOrgJob;

/**
 * TODO(Vo)
 * @author by imall core generator
 * @version 1.0.0
 */
public class SysUserOrgJobVo extends SysUserOrgJob{

    private String userName;
    private String realName;
    private String orgCode;
    private String orgName;
    private String jobCode;
    private String jobName;


    public String getUser() {
        return this.userName + " / " + this.realName;
    }

    public String getOrg() {
        return this.orgCode + " / " + this.orgName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
}
