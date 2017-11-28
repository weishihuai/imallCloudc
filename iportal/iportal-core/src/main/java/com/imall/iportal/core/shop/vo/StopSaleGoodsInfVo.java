package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.shop.entity.StopSaleGoodsInf;

/**
 * Created by caidapao on 2017/8/9.
 */
public class StopSaleGoodsInfVo  extends StopSaleGoodsInf {

    /**
     * 剂型名称
     */
    private String dosageFormName;

    public String getDosageFormName() {
        return dosageFormName;
    }

    public void setDosageFormName(String dosageFormName) {
        this.dosageFormName = dosageFormName;
    }
}
