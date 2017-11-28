package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by frt on 2017/7/15.
 */
public class DrugCuringUpdateVo {
    @NotNull
    private Long id;

    /**
     *门店 ID
     */
    @NotNull
    private Long shopId;

    /**
     * 审核 人 ID
     */
    @NotNull
    private Long curingManId;

    /**
     * 待养护商品
     */
    @NotEmpty
    private List<DrugCuringItemUpdateVo> itemList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getCuringManId() {
        return curingManId;
    }

    public void setCuringManId(Long curingManId) {
        this.curingManId = curingManId;
    }

    public List<DrugCuringItemUpdateVo> getItemList() {
        return itemList;
    }

    public void setItemList(List<DrugCuringItemUpdateVo> itemList) {
        this.itemList = itemList;
    }
}
