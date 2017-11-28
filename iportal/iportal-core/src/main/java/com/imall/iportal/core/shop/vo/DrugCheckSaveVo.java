package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by frt on 2017/7/14.
 */
public class DrugCheckSaveVo {
    /**
     *门店 ID
     */
    @NotNull
    private Long shopId;

    /**
     * 计划 检查 时间
     */
    @NotBlank
    @Length(max = 19)
    private String planCheckTimeString;

    /**
     * 检查 类型 代码
     */
    @NotBlank
    @Length(max = 32)
    private String checkTypeCode;

    @NotEmpty
    private List<DrugCheckItemSaveVo> drugCheckItemSaveVoList;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getPlanCheckTimeString() {
        return planCheckTimeString;
    }

    public void setPlanCheckTimeString(String planCheckTimeString) {
        this.planCheckTimeString = planCheckTimeString;
    }

    public String getCheckTypeCode() {
        return checkTypeCode;
    }

    public void setCheckTypeCode(String checkTypeCode) {
        this.checkTypeCode = checkTypeCode;
    }

    public List<DrugCheckItemSaveVo> getDrugCheckItemSaveVoList() {
        return drugCheckItemSaveVoList;
    }

    public void setDrugCheckItemSaveVoList(List<DrugCheckItemSaveVo> drugCheckItemSaveVoList) {
        this.drugCheckItemSaveVoList = drugCheckItemSaveVoList;
    }

    public Date getPlanCheckTime() throws ParseException {
        return DateTimeUtils.getDayStartTime(this.getPlanCheckTimeString());
    }
}
