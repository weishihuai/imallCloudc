package com.imall.iportal.core.main.vo;

import java.util.List;

/**
 * Created by yang on 2015-10-28.
 */
public class TagAuthVo {

    private Long userId;

    private Long jobId;

    private List<String> rolesSet;
    //权限
    private List<String> permissionsSet;

    private Long prevJobId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public List<String> getRolesSet() {
        return rolesSet;
    }

    public void setRolesSet(List<String> rolesSet) {
        this.rolesSet = rolesSet;
    }

    public List<String> getPermissionsSet() {
        return permissionsSet;
    }

    public void setPermissionsSet(List<String> permissionsSet) {
        this.permissionsSet = permissionsSet;
    }

    public Long getPrevJobId() {
        return prevJobId;
    }

    public void setPrevJobId(Long prevJobId) {
        this.prevJobId = prevJobId;
    }
}
