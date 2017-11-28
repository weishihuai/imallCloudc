package com.imall.iportal.core.main.vo;

/**
 * Created by lxh on 2017/7/10.
 */
public class SysUserSimpleVo {
    //主键
    private Long id;
    //用户名
    private String userName;
    //真实姓名
    private String realName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
