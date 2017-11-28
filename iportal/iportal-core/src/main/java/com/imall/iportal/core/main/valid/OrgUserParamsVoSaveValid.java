package com.imall.iportal.core.main.valid;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * Created by ygw on 2016/11/23.
 */
public class OrgUserParamsVoSaveValid implements Cloneable, java.io.Serializable{

    public static final long serialVersionUID = -15263788L;

    @NotBlank
    @Length(max = 32)
    private String orgCode; //组织编码

    @NotBlank
    @Length(max = 128)
    private String orgName; //组织名称
    private String zoneCode; //地域编码
    private String address;
    private String phone;
    private String fax;

    @NotBlank
    @Length(max = 128)
    private String realName; //法人姓名

    @NotBlank
    @Length(max = 128)
    private String userName; //登录帐号

    @NotBlank
    @Length(max = 32)
    private String salt; //加密的盐

    @NotBlank
    @Length(max = 128)
    private String password; //加密后的密码
    private String email; //法人E-mail, 可以为null
    private String mobile; //法人电话
    private Long shopId; //
    private Long orgId; //


    private List<Long> sysMenuIdList; //菜单ids

    private List<Long> sysResourceIdList; //资源

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public List<Long> getSysMenuIdList() {
        return sysMenuIdList;
    }

    public void setSysMenuIdList(List<Long> sysMenuIdList) {
        this.sysMenuIdList = sysMenuIdList;
    }

    public List<Long> getSysResourceIdList() {
        return sysResourceIdList;
    }

    public void setSysResourceIdList(List<Long> sysResourceIdList) {
        this.sysResourceIdList = sysResourceIdList;
    }
}
