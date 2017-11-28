package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.shop.entity.ConsultService;

import java.util.List;

/**
 * Created by HWJ on 2017/7/18
 */
public class ConsultServiceDetailVo extends ConsultService {

    /**
     * 咨询时间
     */
    private String consultTimeString;

    /**
     * 咨询药品
     */
    private List<ConsultGoodsInfDetailVo> goodsList;

    @Override
    public String getConsultTimeString() {
        return consultTimeString;
    }

    public void setConsultTimeString(String consultTimeString) {
        this.consultTimeString = consultTimeString;
    }

    public List<ConsultGoodsInfDetailVo> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<ConsultGoodsInfDetailVo> goodsList) {
        this.goodsList = goodsList;
    }
}
