package com.imall.iportal.core.main.valid;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by ygw on 2016/5/17.
 */
public class SysAuthSaveValid implements Cloneable, java.io.Serializable {

    public static final long serialVersionUID = -15263788L;

    @NotNull
    private Long jobId;
    @NotNull
    private Long roleId;
    private String createBy;
    private Date createDate;
    private String lastModifiedBy;
    private Date lastModifiedDate;
    private Long version;


    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
