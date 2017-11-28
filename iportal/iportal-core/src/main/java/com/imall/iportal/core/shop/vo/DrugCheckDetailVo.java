package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.DrugCheckTypeCodeEnum;

import java.util.Date;
import java.util.List;

/**
 * Created by frt on 2017/7/14.
 */
public class DrugCheckDetailVo {
    private Long id;

    /**
     * 门店 ID
     */
    private Long shopId;

    /**
     * 计划 检查 时间
     */
    private java.util.Date planCheckTime;

    /**
     * 检查 类型 代码
     */
    private String checkTypeCode;

    /**
     * 检查 状态 代码
     */
    private String checkStateCode;

    /**
     * 商品信息
     */
    private List<DrugCheckItemDetailVo> drugCheckItemDetailVoList;

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

    public Date getPlanCheckTime() {
        return planCheckTime;
    }

    public void setPlanCheckTime(Date planCheckTime) {
        this.planCheckTime = planCheckTime;
    }

    public String getCheckTypeCode() {
        return checkTypeCode;
    }

    public void setCheckTypeCode(String checkTypeCode) {
        this.checkTypeCode = checkTypeCode;
    }

    public List<DrugCheckItemDetailVo> getDrugCheckItemDetailVoList() {
        return drugCheckItemDetailVoList;
    }

    public String getCheckStateCode() {
        return checkStateCode;
    }

    public void setCheckStateCode(String checkStateCode) {
        this.checkStateCode = checkStateCode;
    }

    public void setDrugCheckItemDetailVoList(List<DrugCheckItemDetailVo> drugCheckItemDetailVoList) {
        this.drugCheckItemDetailVoList = drugCheckItemDetailVoList;
    }

    public String getPlanCheckTimeString(){
        if(this.getPlanCheckTime()==null){
            return "";
        }else{
            return DateTimeUtils.convertDateToString(this.getPlanCheckTime());
        }
    }

    public String getCheckTypeName(){
        return DrugCheckTypeCodeEnum.fromCode(this.getCheckTypeCode()).toName();
    }
}
