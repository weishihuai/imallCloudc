package com.imall.iportal.core.main.vo;

import com.imall.iportal.core.main.entity.SysUser;

/**
 * Created by ygw on 2017/7/21.
 */
public class CurrUserVo{

    private Long userOrgJobId;

    private Long userId;
    private Long orgId; //当前登录授权的orgId;
    private Long jobId;
    private Long shopId;
    private Long weShopId;

    private String userName;
    private String orgName;
    private String jobName;
    private String shopEntName;
    private String weShopName;

    public Long getUserOrgJobId() {
        return userOrgJobId;
    }

    public void setUserOrgJobId(Long userOrgJobId) {
        this.userOrgJobId = userOrgJobId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getWeShopId() {
        return weShopId;
    }

    public void setWeShopId(Long weShopId) {
        this.weShopId = weShopId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getShopEntName() {
        return shopEntName;
    }

    public void setShopEntName(String shopEntName) {
        this.shopEntName = shopEntName;
    }

    public String getWeShopName() {
        return weShopName;
    }

    public void setWeShopName(String weShopName) {
        this.weShopName = weShopName;
    }
}
