package com.imall.iportal.core.shop.vo;

import java.util.Date;
import java.util.List;

/**
 * Created by lxh on 2017/7/11.
 * 购进退出单打印模板VO
 */
public class ReturnedPurchasePrintTemplateVo {
    //主键
    private Long id;
    //验收单编号
    private String returnedPurchaseOrderNum;
    //供应商名称
    private String supplierNm;
    //审核人
    private String approveMan;
    //订单日期
    private Date createDate;
    //星期几
    private String dayOfWeek;
    //出库员
    private String outStorageMan;
    //备注
    private String remark;
    //退货项
    private List<ReturnedPurchasePrintTemplateVo.ReturnedPurchaseOrderItemPrintVo> itemPrintVos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReturnedPurchaseOrderNum() {
        return returnedPurchaseOrderNum;
    }

    public void setReturnedPurchaseOrderNum(String returnedPurchaseOrderNum) {
        this.returnedPurchaseOrderNum = returnedPurchaseOrderNum;
    }

    public String getSupplierNm() {
        return supplierNm;
    }

    public void setSupplierNm(String supplierNm) {
        this.supplierNm = supplierNm;
    }

    public String getApproveMan() {
        return approveMan;
    }

    public void setApproveMan(String approveMan) {
        this.approveMan = approveMan;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getOutStorageMan() {
        return outStorageMan;
    }

    public void setOutStorageMan(String outStorageMan) {
        this.outStorageMan = outStorageMan;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<ReturnedPurchaseOrderItemPrintVo> getItemPrintVos() {
        return itemPrintVos;
    }

    public void setItemPrintVos(List<ReturnedPurchaseOrderItemPrintVo> itemPrintVos) {
        this.itemPrintVos = itemPrintVos;
    }

    public static class ReturnedPurchaseOrderItemPrintVo {
        //商品编码
        private String goodsCode;
        //商品名称
        private String goodsNm;
        //商品规格
        private String spec;
        //批号
        private String goodsBatch;
        //生产日期
        private String productionDateString;
        //有效期至
        private String validityString;
        //退货数量
        private Long returnedPurchaseQuantity;
        //采购单价
        private Double purchaseUnitPrice;
        //退货金额
        private Double amount;
        //剂型
        private String dosageForm;
        //单位
        private String unit;
        //生产企业
        private String produceManufacturer;

        public String getGoodsCode() {
            return goodsCode;
        }

        public void setGoodsCode(String goodsCode) {
            this.goodsCode = goodsCode;
        }

        public String getGoodsNm() {
            return goodsNm;
        }

        public void setGoodsNm(String goodsNm) {
            this.goodsNm = goodsNm;
        }

        public String getSpec() {
            return spec;
        }

        public void setSpec(String spec) {
            this.spec = spec;
        }

        public String getGoodsBatch() {
            return goodsBatch;
        }

        public void setGoodsBatch(String goodsBatch) {
            this.goodsBatch = goodsBatch;
        }

        public String getProductionDateString() {
            return productionDateString;
        }

        public void setProductionDateString(String productionDateString) {
            this.productionDateString = productionDateString;
        }

        public String getValidityString() {
            return validityString;
        }

        public void setValidityString(String validityString) {
            this.validityString = validityString;
        }

        public Long getReturnedPurchaseQuantity() {
            return returnedPurchaseQuantity;
        }

        public void setReturnedPurchaseQuantity(Long returnedPurchaseQuantity) {
            this.returnedPurchaseQuantity = returnedPurchaseQuantity;
        }

        public Double getPurchaseUnitPrice() {
            return purchaseUnitPrice;
        }

        public void setPurchaseUnitPrice(Double purchaseUnitPrice) {
            this.purchaseUnitPrice = purchaseUnitPrice;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public String getDosageForm() {
            return dosageForm;
        }

        public void setDosageForm(String dosageForm) {
            this.dosageForm = dosageForm;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getProduceManufacturer() {
            return produceManufacturer;
        }

        public void setProduceManufacturer(String produceManufacturer) {
            this.produceManufacturer = produceManufacturer;
        }
    }
}
