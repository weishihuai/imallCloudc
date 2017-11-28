package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by frt on 2017/7/14.
 */
public class DrugCheckUpdateVo {
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
    private Long approveManId;

    /**
     * 待检查商品
     */
    @NotEmpty
    private List<DrugCheckItemUpdateVo> drugCheckItemUpdateVoList;

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

    public Long getApproveManId() {
        return approveManId;
    }

    public void setApproveManId(Long approveManId) {
        this.approveManId = approveManId;
    }

    public List<DrugCheckItemUpdateVo> getDrugCheckItemUpdateVoList() {
        return drugCheckItemUpdateVoList;
    }

    public void setDrugCheckItemUpdateVoList(List<DrugCheckItemUpdateVo> drugCheckItemUpdateVoList) {
        this.drugCheckItemUpdateVoList = drugCheckItemUpdateVoList;
    }
}
