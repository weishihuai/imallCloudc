package com.imall.iportal.core.main.valid;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by ygw on 2016/5/17.
 */
public class SysParamInfUpdateValid implements Cloneable, java.io.Serializable  {

    public static final long serialVersionUID = -15263788L;

    @NotNull
    private Long id;

    @NotBlank
    @Length(max = 32)
    private String paramGroupTypeCode;

    @NotBlank
    @Length(max = 32)
    private String paramTypeCode;

    @NotBlank
    @Length(max = 128)
    private String innerCode;

    @NotBlank
    @Length(max = 64)
    private String paramNm;

    @NotBlank
    @Length(max = 512)
    private String paramDescr;

    @NotBlank
    private String paramValue;

    @NotNull
    private Long sysOrgId;
    private String createBy;
    private Date createDate;
    private String lastModifiedBy;
    private Date lastModifiedDate;
    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParamGroupTypeCode() {
        return paramGroupTypeCode;
    }

    public void setParamGroupTypeCode(String paramGroupTypeCode) {
        this.paramGroupTypeCode = paramGroupTypeCode;
    }

    public String getParamTypeCode() {
        return paramTypeCode;
    }

    public void setParamTypeCode(String paramTypeCode) {
        this.paramTypeCode = paramTypeCode;
    }

    public String getInnerCode() {
        return innerCode;
    }

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode;
    }

    public String getParamNm() {
        return paramNm;
    }

    public void setParamNm(String paramNm) {
        this.paramNm = paramNm;
    }

    public String getParamDescr() {
        return paramDescr;
    }

    public void setParamDescr(String paramDescr) {
        this.paramDescr = paramDescr;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public Long getSysOrgId() {
        return sysOrgId;
    }

    public void setSysOrgId(Long sysOrgId) {
        this.sysOrgId = sysOrgId;
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
