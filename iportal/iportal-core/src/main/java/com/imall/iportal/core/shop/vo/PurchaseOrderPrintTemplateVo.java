package com.imall.iportal.core.shop.vo;

import java.util.Date;
import java.util.List;

/**
 * Created by lxh on 2017/7/11.
 * 采购单打印模板VO
 */
public class PurchaseOrderPrintTemplateVo {
    //主键
    private Long id;
    //订单编号
    private String purchaseOrderNum;
    //供应商名称
    private String supplierNm;
    //备注
    private String remark;
    //订单日期
    private Date createDate;
    //星期几
    private String dayOfWeek;
    //采购员
    private String purchaseMan;
    //采购订单项
    private List<PurchaseOrderPrintTemplateVo.PurchaseOrderItemPrintVo> itemPrintVos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPurchaseOrderNum() {
        return purchaseOrderNum;
    }

    public void setPurchaseOrderNum(String purchaseOrderNum) {
        this.purchaseOrderNum = purchaseOrderNum;
    }

    public String getSupplierNm() {
        return supplierNm;
    }

    public void setSupplierNm(String supplierNm) {
        this.supplierNm = supplierNm;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getPurchaseMan() {
        return purchaseMan;
    }

    public void setPurchaseMan(String purchaseMan) {
        this.purchaseMan = purchaseMan;
    }

    public List<PurchaseOrderItemPrintVo> getItemPrintVos() {
        return itemPrintVos;
    }

    public void setItemPrintVos(List<PurchaseOrderItemPrintVo> itemPrintVos) {
        this.itemPrintVos = itemPrintVos;
    }

    public static class PurchaseOrderItemPrintVo {
        //商品编码
        private String goodsCode;
        //通用名称
        private String commonNm;
        //商品规格
        private String spec;
        //剂型
        private String dosageForm;
        //单位
        private String unit;
        //生产企业
        private String produceManufacturer;
        //采购 数量
        private Long purchaseQuantity;
        //采购单价
        private Double purchaseUnitPrice;
        //采购总金额
        private Double purchaseTotalAmount;

        public String getGoodsCode() {
            return goodsCode;
        }

        public void setGoodsCode(String goodsCode) {
            this.goodsCode = goodsCode;
        }

        public String getCommonNm() {
            return commonNm;
        }

        public void setCommonNm(String commonNm) {
            this.commonNm = commonNm;
        }

        public String getSpec() {
            return spec;
        }

        public void setSpec(String spec) {
            this.spec = spec;
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

        public Long getPurchaseQuantity() {
            return purchaseQuantity;
        }

        public void setPurchaseQuantity(Long purchaseQuantity) {
            this.purchaseQuantity = purchaseQuantity;
        }

        public Double getPurchaseUnitPrice() {
            return purchaseUnitPrice;
        }

        public void setPurchaseUnitPrice(Double purchaseUnitPrice) {
            this.purchaseUnitPrice = purchaseUnitPrice;
        }

        public Double getPurchaseTotalAmount() {
            return purchaseTotalAmount;
        }

        public void setPurchaseTotalAmount(Double purchaseTotalAmount) {
            this.purchaseTotalAmount = purchaseTotalAmount;
        }
    }
}
