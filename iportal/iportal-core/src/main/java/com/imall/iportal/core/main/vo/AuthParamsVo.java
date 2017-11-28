package com.imall.iportal.core.main.vo;

/**
 * Created by yang on 2015-09-23.
 */
public class AuthParamsVo {

    private Long jobId;
    private String roleName;
    private String roleDescription;

    public AuthParamsVo() {
    }

    public AuthParamsVo(Long jobId, String roleName, String roleDescription) {
        this.jobId = jobId;
        this.roleName = roleName;
        this.roleDescription = roleDescription;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
}
