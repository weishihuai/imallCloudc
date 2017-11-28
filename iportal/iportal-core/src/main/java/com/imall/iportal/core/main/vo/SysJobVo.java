package com.imall.iportal.core.main.vo;


import com.imall.iportal.core.main.entity.SysJob;

/**
 * TODO(Vo)
 * @author by imall core generator
 * @version 1.0.0
 */
public class SysJobVo extends SysJob{
    /**
     * 角色名称,多个角色逗号"，"隔开
     */
    private String roleNames;
    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }
}
