package com.imall.iportal.core.shop.vo;

import java.util.Date;
import java.util.List;

/**
 * Created by lxh on 2017/7/11.
 * 采购验收单打印模板VO
 */
public class PurchaseAcceptancePrintTemplateVo {
    //主键
    private Long id;
    //验收单编号
    private String acceptanceOrderNum;
    //供应商名称
    private String supplierNm;
    //制单员
    private String docMaker;
    //订单日期
    private Date createDate;
    //星期几
    private String dayOfWeek;
    //验收人
    private String acceptanceMan;
    //备注
    private String remark;
    //验收项
    private List<PurchaseAcceptancePrintTemplateVo.PurchaseAcceptanceItemPrintVo> itemPrintVos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcceptanceOrderNum() {
        return acceptanceOrderNum;
    }

    public void setAcceptanceOrderNum(String acceptanceOrderNum) {
        this.acceptanceOrderNum = acceptanceOrderNum;
    }

    public String getSupplierNm() {
        return supplierNm;
    }

    public void setSupplierNm(String supplierNm) {
        this.supplierNm = supplierNm;
    }

    public String getDocMaker() {
        return docMaker;
    }

    public void setDocMaker(String docMaker) {
        this.docMaker = docMaker;
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

    public String getAcceptanceMan() {
        return acceptanceMan;
    }

    public void setAcceptanceMan(String acceptanceMan) {
        this.acceptanceMan = acceptanceMan;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<PurchaseAcceptanceItemPrintVo> getItemPrintVos() {
        return itemPrintVos;
    }

    public void setItemPrintVos(List<PurchaseAcceptanceItemPrintVo> itemPrintVos) {
        this.itemPrintVos = itemPrintVos;
    }

    public static class PurchaseAcceptanceItemPrintVo {
        //商品编码
        private String goodsCode;
        //通用名称
        private String commonNm;
        //商品规格
        private String spec;
        //单位
        private String unit;
        //生产企业
        private String produceManufacturer;
        //批号
        private String goodsBatch;
        //生产日期
        private String productionDateString;
        //有效期至
        private String validityString;
        //货位
        private String goodsAllocation;
        //入库数量
        private Long inStorageQuantity;
        //采购单价
        private Double purchaseUnitPrice;

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

        public String getGoodsAllocation() {
            return goodsAllocation;
        }

        public void setGoodsAllocation(String goodsAllocation) {
            this.goodsAllocation = goodsAllocation;
        }

        public Long getInStorageQuantity() {
            return inStorageQuantity;
        }

        public void setInStorageQuantity(Long inStorageQuantity) {
            this.inStorageQuantity = inStorageQuantity;
        }

        public Double getPurchaseUnitPrice() {
            return purchaseUnitPrice;
        }

        public void setPurchaseUnitPrice(Double purchaseUnitPrice) {
            this.purchaseUnitPrice = purchaseUnitPrice;
        }
    }
}
