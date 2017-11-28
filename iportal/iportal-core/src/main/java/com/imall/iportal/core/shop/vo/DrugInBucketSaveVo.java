package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by wsh on 2017/7/12.
 * 商品装斗保存 VO对象
 */
public class DrugInBucketSaveVo  {
    /**
     * 装斗 人 姓名
     */
    @NotBlank
    @Length(max = 32)
    private String inBucketManName;
    /**
     * 门店 ID
     */
    @NotNull
    private Long shopId;

    /**
     * 装斗 时间
     */
    private String inBucketTimeString;
    /**
     *  审核 人 ID
     */
    @NotNull
    private Long approveManId;

    /**
     *  保存 商品
     */
    @NotEmpty
    @Valid
    private List<DrugInBucketGoodsSaveVo> drugInBucketGoodsSaveVoList;


    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getInBucketManName() {
        return inBucketManName;
    }

    public void setInBucketManName(String inBucketManName) {
        this.inBucketManName = inBucketManName;
    }

    public String getInBucketTimeString() {
        return inBucketTimeString;
    }

    public void setInBucketTimeString(String inBucketTimeString) {
        this.inBucketTimeString = inBucketTimeString;
    }

    public Long getApproveManId() {
        return approveManId;
    }

    public void setApproveManId(Long approveManId) {
        this.approveManId = approveManId;
    }

    public List<DrugInBucketGoodsSaveVo> getDrugInBucketGoodsSaveVoList() {
        return drugInBucketGoodsSaveVoList;
    }

    public void setDrugInBucketGoodsSaveVoList(List<DrugInBucketGoodsSaveVo> drugInBucketGoodsSaveVoList) {
        this.drugInBucketGoodsSaveVoList = drugInBucketGoodsSaveVoList;
    }
}
