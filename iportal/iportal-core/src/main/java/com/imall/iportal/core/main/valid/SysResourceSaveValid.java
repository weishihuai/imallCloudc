package com.imall.iportal.core.main.valid;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by ygw on 2016/5/17.
 */
public class SysResourceSaveValid implements Cloneable, java.io.Serializable {

    public static final long serialVersionUID = -15263788L;

    @NotBlank
    @Length(max = 100)
    private String resourceName;

    @NotBlank
    @Length(max = 100)
    private String permissionCode;

    @NotBlank
    @Length(max = 32)
    private String resourceType;

    @NotNull
    @Max(value = Long.MAX_VALUE)
    private Long appId;

    @NotNull
    private Long orderby;

    @NotBlank
    @Length(max = 1)
    private String isAvailable;

    @NotBlank
    @Length(max = 200)
    private String routerKey;

    private String routerTemplateUrl;

    @NotBlank
    @Length(max = 200)
    private String routerTemplateJs;
    private Long parentId;
    private String nodeCode;
    private String createBy;
    private Date createDate;
    private String lastModifiedBy;
    private Date lastModifiedDate;
    private Long version;


    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getOrderby() {
        return orderby;
    }

    public void setOrderby(Long orderby) {
        this.orderby = orderby;
    }

    public String getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getRouterKey() {
        return routerKey;
    }

    public void setRouterKey(String routerKey) {
        this.routerKey = routerKey;
    }

    public String getRouterTemplateUrl() {
        return routerTemplateUrl;
    }

    public void setRouterTemplateUrl(String routerTemplateUrl) {
        this.routerTemplateUrl = routerTemplateUrl;
    }

    public String getRouterTemplateJs() {
        return routerTemplateJs;
    }

    public void setRouterTemplateJs(String routerTemplateJs) {
        this.routerTemplateJs = routerTemplateJs;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
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
