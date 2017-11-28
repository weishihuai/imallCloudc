package com.imall.iportal.core.main.vo;


/**
 * TODO(Vo)
 * @author by imall core generator
 * @version 1.0.0
 */
public class SysUserLoginVo {
    private Long id;
    /**
     * 门店 ID
     */
    private Long shopId;
    /**
     * 用户
     */
    private String realName;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
