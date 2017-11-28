package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.shop.entity.PurchaseReceiveRecord;

import java.util.List;

/**
 * Created by lxh on 2017/7/20.
 * 采购收货详细
 */
public class PurchaseReceiveRecordVo extends PurchaseReceiveRecord {
    //供应商编码
    private String supplierCode;
    //供应商名称
    private String supplierNm;
    //验收项
    private List<PurchaseReceiveRecordItemVo> itemList;
    //货位列表
    private List<StorageSpaceSimpleVo> storageSpaceList;

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierNm() {
        return supplierNm;
    }

    public void setSupplierNm(String supplierNm) {
        this.supplierNm = supplierNm;
    }

    public List<PurchaseReceiveRecordItemVo> getItemList() {
        return itemList;
    }

    public void setItemList(List<PurchaseReceiveRecordItemVo> itemList) {
        this.itemList = itemList;
    }

    public List<StorageSpaceSimpleVo> getStorageSpaceList() {
        return storageSpaceList;
    }

    public void setStorageSpaceList(List<StorageSpaceSimpleVo> storageSpaceList) {
        this.storageSpaceList = storageSpaceList;
    }
}
