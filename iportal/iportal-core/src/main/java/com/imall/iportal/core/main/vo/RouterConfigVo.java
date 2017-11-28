package com.imall.iportal.core.main.vo;

/**
 * 路由配置
 */
public class RouterConfigVo {


    private Long resourceId;
    /*权限编码*/
    private String permissionCode;
    /*路由key*/
    private String routerKey;
    /*路由模版URL*/
    private String routerTemplateUrl;
    /*路由JS*/
    private String routerTemplateJs;

    private String resourceNodeCode;

    //对应菜单的nodeCode
    private String menuNodeCode;

    private Long appId;

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
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

    public String getMenuNodeCode() {
        return menuNodeCode;
    }

    public void setMenuNodeCode(String menuNodeCode) {
        this.menuNodeCode = menuNodeCode;
    }

    public String getResourceNodeCode() {
        return resourceNodeCode;
    }

    public void setResourceNodeCode(String resourceNodeCode) {
        this.resourceNodeCode = resourceNodeCode;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }
}
