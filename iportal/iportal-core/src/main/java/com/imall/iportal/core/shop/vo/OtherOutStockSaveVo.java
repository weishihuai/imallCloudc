package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by wsh on 2017/7/27.
 * 其他出库保存 VO对象
 */
public class OtherOutStockSaveVo {
    /**
     * 操作人ID
     */
    @NotNull
    private Long operationManId;
    /**
     * 门店 ID
     */
    @NotNull
    private Long shopId;
    /**
     * 出库时间
     */
    @NotBlank
    private String outStockTimeString;
    /**
     * 出库类型代码
     */
    @NotBlank
    private String typeCode;
    /**
     *  审核 人 ID
     */
    @NotNull
    private Long approveManId;
    /**
     * 备注
     */
    private String remark;
    /**
     *  保存 商品
     */
    @NotEmpty
    private List<OtherOutStockGoodsSaveVo> otherOutStockGoodsSaveVoList;

    public Long getOperationManId() {
        return operationManId;
    }

    public void setOperationManId(Long operationManId) {
        this.operationManId = operationManId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getOutStockTimeString() {
        return outStockTimeString;
    }

    public void setOutStockTimeString(String outStockTimeString) {
        this.outStockTimeString = outStockTimeString;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public Long getApproveManId() {
        return approveManId;
    }

    public void setApproveManId(Long approveManId) {
        this.approveManId = approveManId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<OtherOutStockGoodsSaveVo> getOtherOutStockGoodsSaveVoList() {
        return otherOutStockGoodsSaveVoList;
    }

    public void setOtherOutStockGoodsSaveVoList(List<OtherOutStockGoodsSaveVo> otherOutStockGoodsSaveVoList) {
        this.otherOutStockGoodsSaveVoList = otherOutStockGoodsSaveVoList;
    }
}
