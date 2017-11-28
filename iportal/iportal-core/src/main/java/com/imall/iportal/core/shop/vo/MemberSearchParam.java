package com.imall.iportal.core.shop.vo;

/**
 * Created by wsh on 2017/7/6.
 * 会员列表搜索参数
 */
public class MemberSearchParam {
    /**
     * 门店 ID
     */
    private Long shopId;
    /**
     * 手机号码、会员卡号
     */
    private String searchFields;
    /**
     * 会员姓名
     */
    private String name;
    /**
     * 创建时间(开始)
     */
    private String createDateBeginString;
    /**
     * 创建时间(结束)
     */
    private String createDateEndString;

    public String getSearchFields() {
        return searchFields;
    }

    public void setSearchFields(String searchFields) {
        this.searchFields = searchFields;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateDateBeginString() {
        return createDateBeginString;
    }

    public void setCreateDateBeginString(String createDateBeginString) {
        this.createDateBeginString = createDateBeginString;
    }

    public String getCreateDateEndString() {
        return createDateEndString;
    }

    public void setCreateDateEndString(String createDateEndString) {
        this.createDateEndString = createDateEndString;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}
