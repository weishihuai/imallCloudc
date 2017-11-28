package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by wsh on 2017/8/1.
 * 药品锁定 保存VO
 */
public class DrugLockSaveVo {
    /**
     * 门店 ID
     */
    @NotNull
    private Long shopId;
    /**
     * 锁定人姓名
     */
    @NotBlank
    @Length(max = 32)
    private String lockManName;
    /**
     * 锁定时间
     */
    @NotBlank
    private String lockTimeString;
    /**
     *  保存 商品
     */
    @NotEmpty
    @Valid
    private List<DrugLockGoodsSaveVo> drugLockGoodsSaveVoList;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getLockManName() {
        return lockManName;
    }

    public void setLockManName(String lockManName) {
        this.lockManName = lockManName;
    }

    public String getLockTimeString() {
        return lockTimeString;
    }

    public void setLockTimeString(String lockTimeString) {
        this.lockTimeString = lockTimeString;
    }

    public List<DrugLockGoodsSaveVo> getDrugLockGoodsSaveVoList() {
        return drugLockGoodsSaveVoList;
    }

    public void setDrugLockGoodsSaveVoList(List<DrugLockGoodsSaveVo> drugLockGoodsSaveVoList) {
        this.drugLockGoodsSaveVoList = drugLockGoodsSaveVoList;
    }
}
