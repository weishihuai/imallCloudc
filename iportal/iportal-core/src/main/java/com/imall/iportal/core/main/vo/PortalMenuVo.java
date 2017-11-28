package com.imall.iportal.core.main.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 平台菜单
 * Created by Administrator on 2015/10/20.
 */
public class PortalMenuVo implements Serializable {

    private Long id;

    /*菜单名称*/
    private String menuNm;
    /*路由key*/
    private String routerKey;
    /*权限编码*/
    private String permissionCode;
    /*图标*/
    private String iconClass;
    /*菜单类型*/
    private String menuType;

    private String nodeCode;

    /*子菜单*/
    List<PortalMenuVo> subChildList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuNm() {
        return menuNm;
    }

    public void setMenuNm(String menuNm) {
        this.menuNm = menuNm;
    }

    public String getRouterKey() {
        return routerKey;
    }

    public void setRouterKey(String routerKey) {
        this.routerKey = routerKey;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public List<PortalMenuVo> getSubChildList() {
        return subChildList;
    }

    public void setSubChildList(List<PortalMenuVo> subChildList) {
        this.subChildList = subChildList;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }
}
