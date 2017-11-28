package com.imall.iportal.frontend.wechat.order.vo;

/**
 * 再次购买
 */
public class OrderBuyAgainVo {
    private Long id;

    /**
     * 微门店 ID
     */
    private Long weShopId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWeShopId() {
        return weShopId;
    }

    public void setWeShopId(Long weShopId) {
        this.weShopId = weShopId;
    }
}
