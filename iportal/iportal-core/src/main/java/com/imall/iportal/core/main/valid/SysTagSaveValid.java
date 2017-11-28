package com.imall.iportal.core.main.valid;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by ygw on 2016/5/17.
 */
public class SysTagSaveValid implements Cloneable, java.io.Serializable {

    public static final long serialVersionUID = -15263788L;

    @NotBlank
    @Length(max = 32)
    private String tagTypeCode;

    @NotBlank
    @Length(max = 128)
    private String tagValue;

    @NotNull
    private Long displayPosition;

    @NotNull
    private Long sysOrgId;
    private String createBy;
    private Date createDate;
    private String lastModifiedBy;
    private Date lastModifiedDate;
    private Long version;

    public Long getSysOrgId() {
        return sysOrgId;
    }

    public void setSysOrgId(Long sysOrgId) {
        this.sysOrgId = sysOrgId;
    }

    public String getTagTypeCode() {
        return tagTypeCode;
    }

    public void setTagTypeCode(String tagTypeCode) {
        this.tagTypeCode = tagTypeCode;
    }

    public String getTagValue() {
        return tagValue;
    }

    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }

    public Long getDisplayPosition() {
        return displayPosition;
    }

    public void setDisplayPosition(Long displayPosition) {
        this.displayPosition = displayPosition;
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
