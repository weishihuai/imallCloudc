package com.imall.iportal.core.platform.vo;


import com.imall.iportal.core.shop.entity.Goods;


/**
 * Created by ygw on 2017/8/24
 */
public class GoodsExcelVo extends Goods {

    private String sellCategoryNames;
    private String goodsTypeName;
    private String toxicologyName;
    private String isDeleteName;
    private String approveStateName;
    private String storageConditionName;
    private String isEnableName;
    private String isFirstSellName;


    /** RETAIL_PRICE - 零售 价格 */
    private Double retailPrice;
    /** MEMBER_PRICE - 会员 价格 */
    private Double memberPrice;
    /** MARKET_PRICE - 市场 价格 */
    private Double marketPrice;
    /** COST_PRICE - 成本 价格 */
    private Double costPrice;


    /** APPROVAL_NUMBER - 批准文号 */
    private String approvalNumber;
    /** APPROVAL_NUMBER_TERM - 批准文号 期限 */
    private String approvalNumberTerm;
    /** IS_IMPORT_GOODS - 是否 进口 商品 */
    private String isImportGoodsName;
    /** IS_CHINESE_MEDICINE_PROTECT - 是否 中药 保护 */
    private String isChineseMedicineProtectName;
    /** APPROVE_DATE - 批准 日期 */
    private String approveDate;
    /** DOSAGE_FORM - 剂型 */
    private String dosageForm;
    /** PRESCRIPTION_DRUGS_TYPE_CODE - 处方药 类型 代码 */
    private String prescriptionDrugsTypeCode;
    /** IS_EPHEDRINE - 是否 麻黄碱 */
    private String isEphedrineName;
    /** IS_KEY_CURING - 是否 重点 养护 */
    private String isKeyCuringName;
    /** IS_MEDICAL_INSURANCE_GOODS - 是否 医保 商品 */
    private String isMedicalInsuranceGoodsName;
    /** MEDICAL_INSURANCE_NUM - 医保 号码 */
    private String medicalInsuranceNum;


    /** PRODUCTION_PLACE - 产地 */
    private String productionPlace;
    /** EFFECT - 功效 */
    private String effect;


    /** FOOD_HYGIENE_LICENCE_NUM - 食品 卫生 许可证 号码 */
    private String foodHygieneLicenceNum;
    /** PRODUCTION_DATE - 生产 日期 */
    private String productionDate;
    /** EXPIRATION_DATE - 保质期 */
    private String expirationDate;
    /** HEALTH_CARE_FUNC - 保健 功能 */
    private String healthCareFunc;
    /** APPROPRIATE_CROWD - 适宜 人群 */
    private String appropriateCrowd;
    /** NOT_APPROPRIATE_CROWD - 不 适宜 人群 */
    private String notAppropriateCrowd;
    /** EDIBLE_METHOD_AND_DOSAGE - 食用 方法 及 用量 */
    private String edibleMethodAndDosage;
    /** STORAGE_METHOD - 贮藏 方法 */
    private String storageMethod;
    /** EXEC_STANDARD - 执行 标准 */
    private String execStandard;
    /** EFFECT_COMPOSITION - 功效 成分 */
    private String effectComposition;
    /** NOTICE - 注意 事项 */
    private String notice;

    /** MANUFACTURER_ADDR - 厂家 地址 */
    private String manufacturerAddr;


    /** REG_NUM - 注册 号码 */
    private String regNum;
    /** REG_REGISTRATION_FORM_NUM - 注册 登记表 号码 */
    private String regRegistrationFormNum;
    /** APPLY_RANGE - 适用 范围 */
    private String applyRange;
    /** PRODUCT_STANDARD_NUM - 产品 标准 号码 */
    private String productStandardNum;


    public String getSellCategoryNames() {
        return sellCategoryNames;
    }

    public void setSellCategoryNames(String sellCategoryNames) {
        this.sellCategoryNames = sellCategoryNames;
    }

    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName;
    }

    public String getToxicologyName() {
        return toxicologyName;
    }

    public void setToxicologyName(String toxicologyName) {
        this.toxicologyName = toxicologyName;
    }

    public String getIsDeleteName() {
        return isDeleteName;
    }

    public void setIsDeleteName(String isDeleteName) {
        this.isDeleteName = isDeleteName;
    }

    public String getApproveStateName() {
        return approveStateName;
    }

    public void setApproveStateName(String approveStateName) {
        this.approveStateName = approveStateName;
    }

    public String getStorageConditionName() {
        return storageConditionName;
    }

    public void setStorageConditionName(String storageConditionName) {
        this.storageConditionName = storageConditionName;
    }

    public String getIsEnableName() {
        return isEnableName;
    }

    public void setIsEnableName(String isEnableName) {
        this.isEnableName = isEnableName;
    }

    public String getIsFirstSellName() {
        return isFirstSellName;
    }

    public void setIsFirstSellName(String isFirstSellName) {
        this.isFirstSellName = isFirstSellName;
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

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public String getApprovalNumberTerm() {
        return approvalNumberTerm;
    }

    public void setApprovalNumberTerm(String approvalNumberTerm) {
        this.approvalNumberTerm = approvalNumberTerm;
    }

    public String getIsImportGoodsName() {
        return isImportGoodsName;
    }

    public void setIsImportGoodsName(String isImportGoodsName) {
        this.isImportGoodsName = isImportGoodsName;
    }

    public String getIsChineseMedicineProtectName() {
        return isChineseMedicineProtectName;
    }

    public void setIsChineseMedicineProtectName(String isChineseMedicineProtectName) {
        this.isChineseMedicineProtectName = isChineseMedicineProtectName;
    }

    public String getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(String approveDate) {
        this.approveDate = approveDate;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getPrescriptionDrugsTypeCode() {
        return prescriptionDrugsTypeCode;
    }

    public void setPrescriptionDrugsTypeCode(String prescriptionDrugsTypeCode) {
        this.prescriptionDrugsTypeCode = prescriptionDrugsTypeCode;
    }

    public String getIsEphedrineName() {
        return isEphedrineName;
    }

    public void setIsEphedrineName(String isEphedrineName) {
        this.isEphedrineName = isEphedrineName;
    }

    public String getIsKeyCuringName() {
        return isKeyCuringName;
    }

    public void setIsKeyCuringName(String isKeyCuringName) {
        this.isKeyCuringName = isKeyCuringName;
    }

    public String getIsMedicalInsuranceGoodsName() {
        return isMedicalInsuranceGoodsName;
    }

    public void setIsMedicalInsuranceGoodsName(String isMedicalInsuranceGoodsName) {
        this.isMedicalInsuranceGoodsName = isMedicalInsuranceGoodsName;
    }

    public String getMedicalInsuranceNum() {
        return medicalInsuranceNum;
    }

    public void setMedicalInsuranceNum(String medicalInsuranceNum) {
        this.medicalInsuranceNum = medicalInsuranceNum;
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

    public String getFoodHygieneLicenceNum() {
        return foodHygieneLicenceNum;
    }

    public void setFoodHygieneLicenceNum(String foodHygieneLicenceNum) {
        this.foodHygieneLicenceNum = foodHygieneLicenceNum;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
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

    public String getManufacturerAddr() {
        return manufacturerAddr;
    }

    public void setManufacturerAddr(String manufacturerAddr) {
        this.manufacturerAddr = manufacturerAddr;
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

    public String getApplyRange() {
        return applyRange;
    }

    public void setApplyRange(String applyRange) {
        this.applyRange = applyRange;
    }

    public String getProductStandardNum() {
        return productStandardNum;
    }

    public void setProductStandardNum(String productStandardNum) {
        this.productStandardNum = productStandardNum;
    }
}
