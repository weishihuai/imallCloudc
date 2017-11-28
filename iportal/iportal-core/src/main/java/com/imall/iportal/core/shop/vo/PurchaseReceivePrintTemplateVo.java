package com.imall.iportal.core.shop.vo;

import java.util.Date;
import java.util.List;

/**
 * Created by lxh on 2017/7/11.
 * 采购收货打印模板VO
 */
public class PurchaseReceivePrintTemplateVo {
    //主键
    private Long id;
    //收货单编号
    private String receiveOrderNum;
    //供应商名称
    private String supplierNm;
    //备注
    private String remark;
    //订单日期
    private Date createDate;
    //星期几
    private String dayOfWeek;
    //收货人
    private String receiver;
    //收货项
    private List<PurchaseReceivePrintTemplateVo.PurchaseReceiveItemPrintVo> itemPrintVos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceiveOrderNum() {
        return receiveOrderNum;
    }

    public void setReceiveOrderNum(String receiveOrderNum) {
        this.receiveOrderNum = receiveOrderNum;
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

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public List<PurchaseReceiveItemPrintVo> getItemPrintVos() {
        return itemPrintVos;
    }

    public void setItemPrintVos(List<PurchaseReceiveItemPrintVo> itemPrintVos) {
        this.itemPrintVos = itemPrintVos;
    }

    public static class PurchaseReceiveItemPrintVo {
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
        //采购数量
        private Long purchaseQuantity;
        //收货数量
        private Long receiveQuantity;

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

        public Long getReceiveQuantity() {
            return receiveQuantity;
        }

        public void setReceiveQuantity(Long receiveQuantity) {
            this.receiveQuantity = receiveQuantity;
        }
    }
}
