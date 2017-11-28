package com.imall.iportal.shiro.vo;

import java.util.List;

/**
 * Created by ygw on 2017/3/20.
 */
public class PermissionContextVo {

    public List<String> rolesSet;

    public List<String> permissionsSet;

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
}
