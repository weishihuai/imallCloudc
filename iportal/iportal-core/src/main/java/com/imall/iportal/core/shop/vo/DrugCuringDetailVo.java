package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.DrugCuringTypeCodeEnum;

import java.util.Date;
import java.util.List;

/**
 * Created by frt on 2017/7/15.
 */
public class DrugCuringDetailVo {
    private Long id;

    /**
     * 门店 ID
     */
    private Long shopId;

    /**
     * 计划 养护 时间
     */
    private java.util.Date planCuringTime;

    /**
     * 养护 类型 代码
     */
    private String curingTypeCode;

    /**
     * 养护 状态 代码
     */
    private String curingStateCode;

    /**
     * 养护 状态 代码
     */
    private List<DrugCuringItemDetailVo> itemList;

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

    public Date getPlanCuringTime() {
        return planCuringTime;
    }

    public void setPlanCuringTime(Date planCuringTime) {
        this.planCuringTime = planCuringTime;
    }

    public String getCuringTypeCode() {
        return curingTypeCode;
    }

    public void setCuringTypeCode(String curingTypeCode) {
        this.curingTypeCode = curingTypeCode;
    }

    public List<DrugCuringItemDetailVo> getItemList() {
        return itemList;
    }

    public void setItemList(List<DrugCuringItemDetailVo> itemList) {
        this.itemList = itemList;
    }

    public String getCuringStateCode() {
        return curingStateCode;
    }

    public void setCuringStateCode(String curingStateCode) {
        this.curingStateCode = curingStateCode;
    }

    public String getPlanCuringTimeString(){
        if(this.getPlanCuringTime()==null){
            return "";
        }else{
            return DateTimeUtils.convertDateToString(this.getPlanCuringTime());
        }
    }

    public String getCuringTypeName(){
        return DrugCuringTypeCodeEnum.fromCode(this.getCuringTypeCode()).toName();
    }
}
