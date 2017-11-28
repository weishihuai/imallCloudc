package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.shop.entity.OtherOutStock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wsh on 2017/7/27.
 * 出库详情信息
 */
public class OtherOutStockDetailVo extends OtherOutStock {
    /**
     * 出库人
     */
    private String operatorName;
    /**
     * 出库时间
     */
    private String outStockTimeTimeString;
    /**
     * 出库类型
     */
    private String typeCode;
    /**
     * 审核人姓名
     */
    private String approveManName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 商品信息集合
     */
    private List<OtherOutStockGoodsVo> otherOutStockGoodsVoList = new ArrayList<>();

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOutStockTimeTimeString() {
        return outStockTimeTimeString;
    }

    public void setOutStockTimeTimeString(String outStockTimeTimeString) {
        this.outStockTimeTimeString = outStockTimeTimeString;
    }

    @Override
    public String getTypeCode() {
        return typeCode;
    }

    @Override
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getApproveManName() {
        return approveManName;
    }

    public void setApproveManName(String approveManName) {
        this.approveManName = approveManName;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<OtherOutStockGoodsVo> getOtherOutStockGoodsVoList() {
        return otherOutStockGoodsVoList;
    }

    public void setOtherOutStockGoodsVoList(List<OtherOutStockGoodsVo> otherOutStockGoodsVoList) {
        this.otherOutStockGoodsVoList = otherOutStockGoodsVoList;
    }
}
