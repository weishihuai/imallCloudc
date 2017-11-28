package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by frt on 2017/7/15.
 */
public class DrugCuringSaveVo {
    /**
     *门店 ID
     */
    @NotNull
    private Long shopId;

    /**
     * 计划 养护 时间
     */
    @NotBlank
    @Length(max = 19)
    private String planCuringTimeString;

    /**
     * 养护 类型
     */
    @NotBlank
    @Length(max = 32)
    private String curingTypeCode;

    /**
     * 养护项信息
     */
    @NotEmpty
    private List<DrugCuringItemSaveVo> itemList;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getPlanCuringTimeString() {
        return planCuringTimeString;
    }

    public void setPlanCuringTimeString(String planCuringTimeString) {
        this.planCuringTimeString = planCuringTimeString;
    }

    public String getCuringTypeCode() {
        return curingTypeCode;
    }

    public void setCuringTypeCode(String curingTypeCode) {
        this.curingTypeCode = curingTypeCode;
    }

    public List<DrugCuringItemSaveVo> getItemList() {
        return itemList;
    }

    public void setItemList(List<DrugCuringItemSaveVo> itemList) {
        this.itemList = itemList;
    }

    public Date getPlanCuringTime() throws ParseException {
        if(StringUtils.isNotBlank(this.getPlanCuringTimeString())){
            return DateTimeUtils.getDayStartTime(this.getPlanCuringTimeString());
        }else{
            return null;
        }
    }

}
