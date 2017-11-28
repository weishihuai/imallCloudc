package com.imall.iportal.core.shop.vo;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by wsh on 2017/7/29.
 * 其他入库保存 VO对象
 */
public class OtherInStockSaveVo {
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
     * 入库时间
     */
    @NotBlank
    private String inStockTimeString;
    /**
     * 入库类型代码
     */
    @NotBlank
    private String typeCode;
    /**
     * 审核 人 ID
     */
    @NotNull
    private Long approveManId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 供应商ID
     */
    @NotNull
    private Long supplierId;
    /**
     * 保存 商品
     */
    @NotEmpty
    @Valid
    private List<OtherInStockGoodsSaveVo> otherInStockGoodsSaveVoList;

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

    public String getInStockTimeString() {
        return inStockTimeString;
    }

    public void setInStockTimeString(String inStockTimeString) {
        this.inStockTimeString = inStockTimeString;
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

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public List<OtherInStockGoodsSaveVo> getOtherInStockGoodsSaveVoList() {
        return otherInStockGoodsSaveVoList;
    }

    public void setOtherInStockGoodsSaveVoList(List<OtherInStockGoodsSaveVo> otherInStockGoodsSaveVoList) {
        this.otherInStockGoodsSaveVoList = otherInStockGoodsSaveVoList;
    }
}
