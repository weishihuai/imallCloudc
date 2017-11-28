package com.imall.commons.demo.entity;

import com.imall.commons.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * Created by lxd on 2015/7/14.
 */
@Entity
@Table(name = "T_DEMO")
public class Demo extends BaseEntity<Long> {

    public final static  String  LOGIN_NAME = "loginName";
    public final static  String  NAME = "name";
    public final static  String  PASSWORD = "password";
    public final static  String  EMAIL = "email";

    @Column(updatable = false, name = "login_name",nullable = true,length = 255)
    private String loginName;

    @Column(updatable = false, name = "name",nullable = true,length = 64)
    private String name;

    @Column(updatable = false, name = "password",nullable = true,length = 255)
    private String password;

    @Column(updatable = false, name = "email",nullable = true,length = 128)
    private String email;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append("loginName = ").append(loginName).append(" name=").append(name).append(" password=").append(password).append(" email=").append(email);
        return builder.toString();
    }
}
