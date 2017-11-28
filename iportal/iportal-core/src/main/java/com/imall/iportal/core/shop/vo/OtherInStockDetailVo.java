package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.shop.entity.OtherInStock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wsh on 2017/7/29.
 * 入库详情信息
 */
public class OtherInStockDetailVo extends OtherInStock {
    /**
     * 入库人
     */
    private String operatorName;
    /**
     * 入库时间
     */
    private String inStockTimeString;
    /**
     * 入库类型
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
     * 供应商名称
     */
    private String supplierNm;
    /**
     * 商品信息集合
     */
    private List<OtherInStockGoodsVo> otherInStockGoodsVoList = new ArrayList<>();

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    @Override
    public String getInStockTimeString() {
        return inStockTimeString;
    }

    public void setInStockTimeString(String inStockTimeString) {
        this.inStockTimeString = inStockTimeString;
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

    public String getSupplierNm() {
        return supplierNm;
    }

    public void setSupplierNm(String supplierNm) {
        this.supplierNm = supplierNm;
    }

    public List<OtherInStockGoodsVo> getOtherInStockGoodsVoList() {
        return otherInStockGoodsVoList;
    }

    public void setOtherInStockGoodsVoList(List<OtherInStockGoodsVo> otherInStockGoodsVoList) {
        this.otherInStockGoodsVoList = otherInStockGoodsVoList;
    }
}
