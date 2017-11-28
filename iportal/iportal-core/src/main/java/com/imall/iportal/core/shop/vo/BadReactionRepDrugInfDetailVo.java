package com.imall.iportal.core.shop.vo;

import java.util.List;

/**
 * Created by wsh on 2017/8/15.
 */
public class BadReactionRepDrugInfDetailVo {
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
}
