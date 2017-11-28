package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.shop.entity.BadReactionRep;

import java.util.List;

/**
 * Created by wsh on 2017/8/10.
 * 不良反应报告详情信息
 */
public class BadReactionRepDetailVo extends BadReactionRep {
    /**
     * 生日
     */
    private String birthDateString;
    /**
     * 发生时间
     */
    private String badReactionOccurTimeString;
    /**
     * 报告日期
     */
    private String repDateString;
    /**
     * 死亡时间
     */
    private String deadTimeString;
    /**
     * 不良反应报告中的药品信息 (怀疑药品集合)
     */
    private List<SuspectDrugDetailVo> suspectDrugInfList;
    /**
     * 不良反应报告中的药品信息 (并用药品集合)
     */
    private List<BlendDrugDetailVo> blendDrugInfList;

    public List<SuspectDrugDetailVo> getSuspectDrugInfList() {
        return suspectDrugInfList;
    }

    public void setSuspectDrugInfList(List<SuspectDrugDetailVo> suspectDrugInfList) {
        this.suspectDrugInfList = suspectDrugInfList;
    }

    public List<BlendDrugDetailVo> getBlendDrugInfList() {
        return blendDrugInfList;
    }

    public void setBlendDrugInfList(List<BlendDrugDetailVo> blendDrugInfList) {
        this.blendDrugInfList = blendDrugInfList;
    }

    public String getBirthDateString() {
        return birthDateString;
    }

    public void setBirthDateString(String birthDateString) {
        this.birthDateString = birthDateString;
    }

    @Override
    public String getBadReactionOccurTimeString() {
        return badReactionOccurTimeString;
    }

    @Override
    public void setBadReactionOccurTimeString(String badReactionOccurTimeString) {
        this.badReactionOccurTimeString = badReactionOccurTimeString;
    }

    @Override
    public String getRepDateString() {
        return repDateString;
    }

    @Override
    public void setRepDateString(String repDateString) {
        this.repDateString = repDateString;
    }

    public String getDeadTimeString() {
        return deadTimeString;
    }

    public void setDeadTimeString(String deadTimeString) {
        this.deadTimeString = deadTimeString;
    }
}
