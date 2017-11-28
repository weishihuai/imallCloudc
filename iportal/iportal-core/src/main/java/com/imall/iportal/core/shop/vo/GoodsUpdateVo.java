package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.main.entity.FileMng;
import com.imall.iportal.core.main.valid.PictMngSaveValid;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by caidapao on 2017/7/7.
 * 商品 - 保存Vo对象
 */
public class GoodsUpdateVo {

    /**
     * 商品id
     */
    private Long id;
    /**
     * 门店id
     */
    @NotNull
    private Long shopId;

    /**
     * 商品编码
     */
    @NotBlank
    @Length(max = 128)
    private String goodsCode;

    /**
     * 商品名称
     */
    @NotBlank
    @Length(max = 128)
    private String goodsNm;

    /**
     * 商品类型
     */
    @NotBlank
    @Length(max = 32)
    private String goodsTypeCode;

    /**
     * 销售分类
     */
    private List<Long> sellCategoryIds;

    /**
     * 生产厂商
     */
    @Length(max = 64)
    private String produceManufacturer;

    /**
     * 通用名称
     */
    @NotBlank
    @Length(max = 64)
    private String commonNm;

    /**
     * 规格
     */
    @NotBlank
    @Length(max = 32)
    private String spec;

    /**
     * 单位
     */
    @NotBlank
    @Length(max = 32)
    private String unit;

    /**
     * 品牌名称
     */
    @Length(max = 32)
    private String brandNm;

    /**
     * 条形码
     */
    @Length(max = 32)
    private String barCode;

    /**
     * 毒理 代码
     */
    @NotBlank
    @Length(max = 32)
    private String toxicologyCode;

    /**
     * 储存 条件 代码
     */
    @NotBlank
    @Length(max = 32)
    private String storageCondition;

    /**
     * 说明书
     */
    @Length(max = 16777215)
    private String instructions;

    /**
     * 用药 指导
     */
    @Length(max = 16777215)
    private String medicationGuide;

    /**
     * 药品状态
     */
    @NotBlank
    private String isEnable;

    /**
     * 是否 首营
     */
    @NotBlank
    private String isFirstSell;

    /**
     * 提交 意见
     */
    @Length(max = 64)
    private String submitIdea;

    /**
     * 商品图片
     */
    private List<FileMng> pictFileList;

    /**
     * 商品附件
     */
    private List<FileMng> otherFileList;


    /**
     * 备注
     */
    @Length(max = 64)
    private String remark;
    //以下为药品 中药饮片表

    /**
     * 批准文号
     */
    @Length(max = 64)
    private String approvalNumber;

    /**
     * 批准文号 期限
     */
    private String approvalNumberTermString;

    /**
     * 是否 进口 商品
     */
    private String isImportGoods;

    /**
     * 是否 中药 保护
     */
    private String isChineseMedicineProtect;

    /**
     * 批准日期
     */
    private String approveDateString;

    /**
     * 剂型
     */
    @Length(max = 64)
    private String dosageForm;

    /**
     * 产地
     */
    @Length(max = 32)
    private String productionPlace;

    /**
     * 功效
     */
    @Length(max = 512)
    private String effect;

    /**
     * 注册号
     */
    @Length(max = 64)
    private String regNum;

    /**
     * 注册登记表号
     */
    @Length(max = 64)
    private String regRegistrationFormNum;

    /**
     * 厂家地址
     */
    @Length(max = 32)
    private String manufacturerAddr;

    /**
     * 产品标准号码
     */
    @Length(max = 32)
    private String productStandardNum;

    /**
     * 适用范围
     */
    @Length(max = 512)
    private String applyRange;

    /**
     * 食品卫生许可证号
     */
    @Length(max = 64)
    private String foodHygieneLicenceNum;

    /**
     * 生产日期
     */
    private String productionDateString;

    /**
     * 保质期
     */
    private String expirationDateString;

    /**
     * 保健功能
     */
    @Length(max = 128)
    private String healthCareFunc;

    /**
     * 适宜人群
     */
    @Length(max = 64)
    private String appropriateCrowd;

    /**
     * 不适宜人群
     */
    @Length(max = 64)
    private String notAppropriateCrowd;

    /**
     * 食用方法及用量
     */
    @Length(max = 64)
    private String edibleMethodAndDosage;

    /**
     * 贮藏方法
     */
    @Length(max = 64)
    private String storageMethod;

    /**
     * 执行标准
     */
    @Length(max = 64)
    private String execStandard;

    /**
     * 功效成分
     */
    @Length(max = 64)
    private String effectComposition;

    /**
     * 注意事项
     */
    @Length(max = 512)
    private String notice;


    /**
     * 处方药 类型 代码
     */
    @Length(max = 32)
    private String prescriptionDrugsTypeCode;

    /**
     * 是否 麻黄碱
     */
    private String isEphedrine;

    /**
     * 是否 重点 养护
     */
    private String isKeyCuring;

    /**
     * 是否医保
     */
    private String isMedicalInsuranceGoods;

    /**
     * 医保 号码
     */
    @Length(max = 32)
    private String medicalInsuranceNum;


    //以下为 sku
    /**
     * 零售 价格
     */
    @NotNull
    private Double retailPrice;

    /**
     * 会员价格
     */
    private Double memberPrice;

    /**
     * 市场 价格
     */
    private Double marketPrice;

    /**
     * 成本 价格
     */
    private Double costPrice;

    /**
     * 当前库存
     */
    private Long currentStock;

    /**
     * 安全库存
     */
    private Long securityStock;

    /**
     * 采购 税率
     */
    private Double purchaseTaxRate;

    /**
     * 销售 税率
     */
    private Double sellTaxRate;

    /**
     * 是否 特价 商品
     */
    private String isSpecialPriceGoods;

    /**
     * 是否 拆零
     */
    private String isSplitZero;

    /**
     * 拆零单位
     */
    private String splitZeroUnit;

    /**
     * 拆零 数量
     */
    private Long splitZeroQuantity;

    /**
     * 拆零 规格
     */
    @Length(max = 32)
    private String splitZeroSpec;

    /**
     * 拆零零售价
     */
    private Double splitZeroRetailPrice;

    /**
     * 拆零会员价
     */
    private Double splitZeroMemberPrice;

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Double getSplitZeroRetailPrice() {
        return splitZeroRetailPrice;
    }

    public void setSplitZeroRetailPrice(Double splitZeroRetailPrice) {
        this.splitZeroRetailPrice = splitZeroRetailPrice;
    }

    public Double getSplitZeroMemberPrice() {
        return splitZeroMemberPrice;
    }

    public void setSplitZeroMemberPrice(Double splitZeroMemberPrice) {
        this.splitZeroMemberPrice = splitZeroMemberPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

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

    public String getGoodsTypeCode() {
        return goodsTypeCode;
    }

    public void setGoodsTypeCode(String goodsTypeCode) {
        this.goodsTypeCode = goodsTypeCode;
    }

    public List<Long> getSellCategoryIds() {
        return sellCategoryIds;
    }

    public void setSellCategoryIds(List<Long> sellCategoryIds) {
        this.sellCategoryIds = sellCategoryIds;
    }

    public String getProduceManufacturer() {
        return produceManufacturer;
    }

    public void setProduceManufacturer(String produceManufacturer) {
        this.produceManufacturer = produceManufacturer;
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

    public String getBrandNm() {
        return brandNm;
    }

    public void setBrandNm(String brandNm) {
        this.brandNm = brandNm;
    }

    public String getToxicologyCode() {
        return toxicologyCode;
    }

    public void setToxicologyCode(String toxicologyCode) {
        this.toxicologyCode = toxicologyCode;
    }

    public String getStorageCondition() {
        return storageCondition;
    }

    public void setStorageCondition(String storageCondition) {
        this.storageCondition = storageCondition;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getMedicationGuide() {
        return medicationGuide;
    }

    public void setMedicationGuide(String medicationGuide) {
        this.medicationGuide = medicationGuide;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public String getIsFirstSell() {
        return isFirstSell;
    }

    public void setIsFirstSell(String isFirstSell) {
        this.isFirstSell = isFirstSell;
    }

    public String getSubmitIdea() {
        return submitIdea;
    }

    public void setSubmitIdea(String submitIdea) {
        this.submitIdea = submitIdea;
    }

    public List<FileMng> getPictFileList() {
        return pictFileList;
    }

    public void setPictFileList(List<FileMng> pictFileList) {
        this.pictFileList = pictFileList;
    }

    public List<FileMng> getOtherFileList() {
        return otherFileList;
    }

    public void setOtherFileList(List<FileMng> otherFileList) {
        this.otherFileList = otherFileList;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public String getApprovalNumberTermString() {
        return approvalNumberTermString;
    }

    public void setApprovalNumberTermString(String approvalNumberTermString) {
        this.approvalNumberTermString = approvalNumberTermString;
    }

    public String getIsImportGoods() {
        return isImportGoods;
    }

    public void setIsImportGoods(String isImportGoods) {
        this.isImportGoods = isImportGoods;
    }

    public String getIsChineseMedicineProtect() {
        return isChineseMedicineProtect;
    }

    public void setIsChineseMedicineProtect(String isChineseMedicineProtect) {
        this.isChineseMedicineProtect = isChineseMedicineProtect;
    }

    public String getApproveDateString() {
        return approveDateString;
    }

    public void setApproveDateString(String approveDateString) {
        this.approveDateString = approveDateString;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getProductionPlace() {
        return productionPlace;
    }

    public void setProductionPlace(String productionPlace) {
        this.productionPlace = productionPlace;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    public String getRegRegistrationFormNum() {
        return regRegistrationFormNum;
    }

    public void setRegRegistrationFormNum(String regRegistrationFormNum) {
        this.regRegistrationFormNum = regRegistrationFormNum;
    }

    public String getManufacturerAddr() {
        return manufacturerAddr;
    }

    public void setManufacturerAddr(String manufacturerAddr) {
        this.manufacturerAddr = manufacturerAddr;
    }

    public String getProductStandardNum() {
        return productStandardNum;
    }

    public void setProductStandardNum(String productStandardNum) {
        this.productStandardNum = productStandardNum;
    }

    public String getApplyRange() {
        return applyRange;
    }

    public void setApplyRange(String applyRange) {
        this.applyRange = applyRange;
    }

    public String getFoodHygieneLicenceNum() {
        return foodHygieneLicenceNum;
    }

    public void setFoodHygieneLicenceNum(String foodHygieneLicenceNum) {
        this.foodHygieneLicenceNum = foodHygieneLicenceNum;
    }

    public String getProductionDateString() {
        return productionDateString;
    }

    public void setProductionDateString(String productionDateString) {
        this.productionDateString = productionDateString;
    }

    public String getExpirationDateString() {
        return expirationDateString;
    }

    public void setExpirationDateString(String expirationDateString) {
        this.expirationDateString = expirationDateString;
    }

    public String getHealthCareFunc() {
        return healthCareFunc;
    }

    public void setHealthCareFunc(String healthCareFunc) {
        this.healthCareFunc = healthCareFunc;
    }

    public String getAppropriateCrowd() {
        return appropriateCrowd;
    }

    public void setAppropriateCrowd(String appropriateCrowd) {
        this.appropriateCrowd = appropriateCrowd;
    }

    public String getNotAppropriateCrowd() {
        return notAppropriateCrowd;
    }

    public void setNotAppropriateCrowd(String notAppropriateCrowd) {
        this.notAppropriateCrowd = notAppropriateCrowd;
    }

    public String getEdibleMethodAndDosage() {
        return edibleMethodAndDosage;
    }

    public void setEdibleMethodAndDosage(String edibleMethodAndDosage) {
        this.edibleMethodAndDosage = edibleMethodAndDosage;
    }

    public String getStorageMethod() {
        return storageMethod;
    }

    public void setStorageMethod(String storageMethod) {
        this.storageMethod = storageMethod;
    }

    public String getExecStandard() {
        return execStandard;
    }

    public void setExecStandard(String execStandard) {
        this.execStandard = execStandard;
    }

    public String getEffectComposition() {
        return effectComposition;
    }

    public void setEffectComposition(String effectComposition) {
        this.effectComposition = effectComposition;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getPrescriptionDrugsTypeCode() {
        return prescriptionDrugsTypeCode;
    }

    public void setPrescriptionDrugsTypeCode(String prescriptionDrugsTypeCode) {
        this.prescriptionDrugsTypeCode = prescriptionDrugsTypeCode;
    }

    public String getIsEphedrine() {
        return isEphedrine;
    }

    public void setIsEphedrine(String isEphedrine) {
        this.isEphedrine = isEphedrine;
    }

    public String getIsKeyCuring() {
        return isKeyCuring;
    }

    public void setIsKeyCuring(String isKeyCuring) {
        this.isKeyCuring = isKeyCuring;
    }

    public String getIsMedicalInsuranceGoods() {
        return isMedicalInsuranceGoods;
    }

    public void setIsMedicalInsuranceGoods(String isMedicalInsuranceGoods) {
        this.isMedicalInsuranceGoods = isMedicalInsuranceGoods;
    }

    public String getMedicalInsuranceNum() {
        return medicalInsuranceNum;
    }

    public void setMedicalInsuranceNum(String medicalInsuranceNum) {
        this.medicalInsuranceNum = medicalInsuranceNum;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Double getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(Double memberPrice) {
        this.memberPrice = memberPrice;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Long getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Long currentStock) {
        this.currentStock = currentStock;
    }

    public Long getSecurityStock() {
        return securityStock;
    }

    public void setSecurityStock(Long securityStock) {
        this.securityStock = securityStock;
    }

    public Double getPurchaseTaxRate() {
        return purchaseTaxRate;
    }

    public void setPurchaseTaxRate(Double purchaseTaxRate) {
        this.purchaseTaxRate = purchaseTaxRate;
    }

    public Double getSellTaxRate() {
        return sellTaxRate;
    }

    public void setSellTaxRate(Double sellTaxRate) {
        this.sellTaxRate = sellTaxRate;
    }

    public String getIsSpecialPriceGoods() {
        return isSpecialPriceGoods;
    }

    public void setIsSpecialPriceGoods(String isSpecialPriceGoods) {
        this.isSpecialPriceGoods = isSpecialPriceGoods;
    }

    public String getIsSplitZero() {
        return isSplitZero;
    }

    public void setIsSplitZero(String isSplitZero) {
        this.isSplitZero = isSplitZero;
    }

    public String getSplitZeroUnit() {
        return splitZeroUnit;
    }

    public void setSplitZeroUnit(String splitZeroUnit) {
        this.splitZeroUnit = splitZeroUnit;
    }

    public Long getSplitZeroQuantity() {
        return splitZeroQuantity;
    }

    public void setSplitZeroQuantity(Long splitZeroQuantity) {
        this.splitZeroQuantity = splitZeroQuantity;
    }

    public String getSplitZeroSpec() {
        return splitZeroSpec;
    }

    public void setSplitZeroSpec(String splitZeroSpec) {
        this.splitZeroSpec = splitZeroSpec;
    }

}
