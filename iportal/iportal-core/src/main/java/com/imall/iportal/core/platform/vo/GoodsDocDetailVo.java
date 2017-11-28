package com.imall.iportal.core.platform.vo;

import com.imall.iportal.core.main.valid.PictMngSaveValid;
import com.imall.iportal.core.main.vo.FileMngVo;

import java.util.List;

/**
 * Created by caidapao on 2017/7/10.
 * 商品档案列表
 */
public class GoodsDocDetailVo {

    /**
     * 商品档案id
     */
    private Long id;

    /**
     * 商品编码
     */
    private String goodsCode;

    /**
     * 商品名称
     */
    private String goodsNm;

    /**
     * 商品类型code
     */
    private String goodsTypeCode;

    /**
     * 商品类型名称
     */
    private String goodsTypeName;

    /**
     * 商品分类(id)
     */
    private Long goodsCategoryId;

    /**
     * 商品分类(名称)
     */
    private String pathName;

    /**
     * 生产厂商
     */
    private String produceManufacturer;

    /**
     * 通用名称
     */
    private String commonNm;

    /**
     * 规格
     */
    private String spec;

    /**
     * 单位
     */
    private String unit;

    /**
     * 品牌名称
     */
    private String brandNm;

    /**
     * 条形码
     */
    private String barCode;

    /**
     * 毒理 代码
     */
    private String toxicologyCode;

    /**
     * 毒理 名称
     */
    private String toxicologyName;

    /**
     * 储存 条件 代码
     */
    private String storageCondition;

    /**
     * 储存 条件 名称
     */
    private String storageConditionName;

    /**
     * 说明书
     */
    private String instructions;

    /**
     * 用药 指导
     */
    private String medicationGuide;

    /**
     * 药品状态
     */
    private String isEnable;

    /**
     * 是否 首营
     */
    private String isFirstSell;


    /**
     * 提交 意见
     */
    private String submitIdea;

    /**
     * 商品图片
     */
    private List<FileMngVo> pictFileVoList;

    private List<FileMngVo> otherFileVoList;



    /**
     * 备注
     */
    private String remark;
    //以下为药品 中药饮片表

    /**
     * 批准文号
     */
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
     * 审核 状态 代码
     */
    private String approveStateCode;

    /**
     * 剂型
     */
    private String dosageForm;

    /**
     * 剂型
     */
    private String dosageFormName;

    /**
     * 产地
     */
    private String productionPlace;

    /**
     * 功效
     */
    private String effect;

    /**
     * 注册号
     */
    private String regNum;

    /**
     * 注册登记表号
     */
    private String regRegistrationFormNum;

    /**
     * 厂家地址
     */
    private String manufacturerAddr;

    /**
     * 产品标准号码
     */
    private String productStandardNum;

    /**
     * 适用范围
     */
    private String applyRange;

    /**
     * 食品卫生许可证号
     */
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
    private String healthCareFunc;

    /**
     * 适宜人群
     */
    private String appropriateCrowd;

    /**
     * 不适宜人群
     */
    private String notAppropriateCrowd;

    /**
     * 食用方法及用量
     */
    private String edibleMethodAndDosage;

    /**
     * 贮藏方法
     */
    private String storageMethod;

    /**
     * 执行标准
     */
    private String execStandard;

    /**
     * 功效成分
     */
    private String effectComposition;

    /**
     * 注意事项
     */
    private String notice;


    /**
     * 处方药 类型 代码
     */
    private String prescriptionDrugsTypeCode;

    /**
     * 处方药 类型 名称
     */
    private String prescriptionDrugsTypeName;

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
    private String medicalInsuranceNum;

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getApproveStateCode() {
        return approveStateCode;
    }

    public void setApproveStateCode(String approveStateCode) {
        this.approveStateCode = approveStateCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public String getDosageFormName() {
        return dosageFormName;
    }

    public void setDosageFormName(String dosageFormName) {
        this.dosageFormName = dosageFormName;
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

    public Long getGoodsCategoryId() {
        return goodsCategoryId;
    }

    public void setGoodsCategoryId(Long goodsCategoryId) {
        this.goodsCategoryId = goodsCategoryId;
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

    public List<FileMngVo> getPictFileVoList() {
        return pictFileVoList;
    }

    public void setPictFileVoList(List<FileMngVo> pictFileVoList) {
        this.pictFileVoList = pictFileVoList;
    }

    public List<FileMngVo> getOtherFileVoList() {
        return otherFileVoList;
    }

    public void setOtherFileVoList(List<FileMngVo> otherFileVoList) {
        this.otherFileVoList = otherFileVoList;
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

    public String getStorageConditionName() {
        return storageConditionName;
    }

    public void setStorageConditionName(String storageConditionName) {
        this.storageConditionName = storageConditionName;
    }

    public String getPrescriptionDrugsTypeName() {
        return prescriptionDrugsTypeName;
    }

    public void setPrescriptionDrugsTypeName(String prescriptionDrugsTypeName) {
        this.prescriptionDrugsTypeName = prescriptionDrugsTypeName;
    }
}
