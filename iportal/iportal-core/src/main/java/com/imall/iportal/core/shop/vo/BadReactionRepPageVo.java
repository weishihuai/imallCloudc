package com.imall.iportal.core.shop.vo;

/**
 * Created by wsh on 2017/8/10.
 * 不良反应报告 列表Vo
 */
public class BadReactionRepPageVo {
    /**
     * 不良反应报告 主键ID
     */
    private Long id;
    /**
     * SHOP_ID - 门店 ID
     */
    private Long shopId;
    /**
     * IS_FIRST_REP - 是否 首次 报告
     */
    private String isFirstRep;
    /**
     * REP_TYPE - 报告 类型
     */
    private String repType;
    /**
     * REP_DEPARTMENT - 报告 单位 类别
     */
    private String repDepartment;
    /**
     * CODE - 编码
     */
    private String code;
    /**
     * PATIENT_NAME - 患者 姓名
     */
    private String patientName;
    /**
     * SEX_CODE - 性别 代码
     */
    private String sexCode;
    /**
     * 出生日期
     */
    private String birthDateString;
    /**
     * AGE - 年龄
     */
    private Integer age;
    /**
     * NATION - 民族
     */
    private String nation;
    /**
     * WEIGHT - 体重
     */
    private Double weight;
    /**
     * CONTACT_WAY - 联系 方式
     */
    private String contactWay;
    /**
     * ORIGINAL_DISEASE - 原患疾病
     */
    private String originalDisease;
    /**
     * HOSPITAL_NM - 医院 名称
     */
    private String hospitalNm;
    /**
     * MEDICAL_RECORD_NO - 病历号
     */
    private String medicalRecordNo;
    /**
     * PAST_DRUG_BAD_REACTION - 既往 药品 不良反应
     */
    private String pastDrugBadReaction;
    /**
     * FAMILY_DRUG_BAD_REACTION - 家族 药品 不良反应
     */
    private String familyDrugBadReaction;
    /**
     * REFER_IMPORTANT_INF - 相关 重要 信息
     */
    private String referImportantInf;
    /**
     * BAD_REACTION - 不良反应
     */
    private String badReaction;
    /**
     * BAD_REACTION_RESULT - 不良反应 结果
     */
    private String badReactionResult;
    /**
     * SEQUELA_PERFORM - 后遗症 表现
     */
    private String sequelaPerform;
    /**
     * CAUSE_OF_DEATH - 直接 死因
     */
    private String causeOfDeath;
    /**
     * DEAD_TIME - 死亡 时间
     */
    private String deadTimeString;
    /**
     * RESPONSE_IS_EASE - 反应 是否 减轻
     */
    private String responseIsEase;
    /**
     * IS_APPEAR_AGAIN - 是否 再次 出现
     */
    private String isAppearAgain;
    /**
     * EFFECT_TO_ORIGINAL_DISEASE - 对原患疾病 影响
     */
    private String effectToOriginalDisease;
    /**
     * REP_MAN_EVALUATE - 报告 人 评价
     */
    private String repManEvaluate;
    /**
     * REP_MAN_TEL - 报告 人 电话
     */
    private String repManTel;
    /**
     * REP_MAN_PROFESSION - 报告 人 职业
     */
    private String repManProfession;
    /**
     * REP_MAN_EMAIL - 报告 人 邮箱
     */
    private String repManEmail;
    /**
     * REP_MAN_NAME - 报告 人 姓名
     */
    private String repManName;
    /**
     * REP_DEPARTMENT_EVALUATE - 报告 单位 评价
     */
    private String repDepartmentEvaluate;
    /**
     * REP_DEPARTMENT_NM - 报告 单位 名称
     */
    private String repDepartmentNm;
    /**
     * REP_DEPARTMENT_CONTACT_MAN - 联系 人
     */
    private String repDepartmentContactMan;
    /**
     * REP_DEPARTMENT_TEL - 报告 单位 电话
     */
    private String repDepartmentTel;
    /**
     * INF_SOURCE - 信息 来源
     */
    private String infSource;
    /**
     * REMARK - 备注
     */
    private String remark;
    /**
     * BAD_REACTION_PROCESS_DESCR - 不良 反应 过程 描述
     */
    private String badReactionProcessDescr;
    /**
     * 报告日期
     */
    private String repDateString;
    /**
     * 不良反应发生时间
     */
    private String badReactionOccurTimeString;

    public String getRepDateString() {
        return repDateString;
    }

    public void setRepDateString(String repDateString) {
        this.repDateString = repDateString;
    }

    public String getBadReactionOccurTimeString() {
        return badReactionOccurTimeString;
    }

    public void setBadReactionOccurTimeString(String badReactionOccurTimeString) {
        this.badReactionOccurTimeString = badReactionOccurTimeString;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getIsFirstRep() {
        return isFirstRep;
    }

    public void setIsFirstRep(String isFirstRep) {
        this.isFirstRep = isFirstRep;
    }

    public String getRepType() {
        return repType;
    }

    public void setRepType(String repType) {
        this.repType = repType;
    }

    public String getRepDepartment() {
        return repDepartment;
    }

    public void setRepDepartment(String repDepartment) {
        this.repDepartment = repDepartment;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getSexCode() {
        return sexCode;
    }

    public void setSexCode(String sexCode) {
        this.sexCode = sexCode;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public String getOriginalDisease() {
        return originalDisease;
    }

    public void setOriginalDisease(String originalDisease) {
        this.originalDisease = originalDisease;
    }

    public String getHospitalNm() {
        return hospitalNm;
    }

    public void setHospitalNm(String hospitalNm) {
        this.hospitalNm = hospitalNm;
    }

    public String getMedicalRecordNo() {
        return medicalRecordNo;
    }

    public void setMedicalRecordNo(String medicalRecordNo) {
        this.medicalRecordNo = medicalRecordNo;
    }

    public String getPastDrugBadReaction() {
        return pastDrugBadReaction;
    }

    public void setPastDrugBadReaction(String pastDrugBadReaction) {
        this.pastDrugBadReaction = pastDrugBadReaction;
    }

    public String getFamilyDrugBadReaction() {
        return familyDrugBadReaction;
    }

    public void setFamilyDrugBadReaction(String familyDrugBadReaction) {
        this.familyDrugBadReaction = familyDrugBadReaction;
    }

    public String getReferImportantInf() {
        return referImportantInf;
    }

    public void setReferImportantInf(String referImportantInf) {
        this.referImportantInf = referImportantInf;
    }

    public String getBadReaction() {
        return badReaction;
    }

    public void setBadReaction(String badReaction) {
        this.badReaction = badReaction;
    }

    public String getBadReactionResult() {
        return badReactionResult;
    }

    public void setBadReactionResult(String badReactionResult) {
        this.badReactionResult = badReactionResult;
    }

    public String getSequelaPerform() {
        return sequelaPerform;
    }

    public void setSequelaPerform(String sequelaPerform) {
        this.sequelaPerform = sequelaPerform;
    }

    public String getCauseOfDeath() {
        return causeOfDeath;
    }

    public void setCauseOfDeath(String causeOfDeath) {
        this.causeOfDeath = causeOfDeath;
    }

    public String getDeadTimeString() {
        return deadTimeString;
    }

    public void setDeadTimeString(String deadTimeString) {
        this.deadTimeString = deadTimeString;
    }

    public String getResponseIsEase() {
        return responseIsEase;
    }

    public void setResponseIsEase(String responseIsEase) {
        this.responseIsEase = responseIsEase;
    }

    public String getIsAppearAgain() {
        return isAppearAgain;
    }

    public void setIsAppearAgain(String isAppearAgain) {
        this.isAppearAgain = isAppearAgain;
    }

    public String getEffectToOriginalDisease() {
        return effectToOriginalDisease;
    }

    public void setEffectToOriginalDisease(String effectToOriginalDisease) {
        this.effectToOriginalDisease = effectToOriginalDisease;
    }

    public String getRepManEvaluate() {
        return repManEvaluate;
    }

    public void setRepManEvaluate(String repManEvaluate) {
        this.repManEvaluate = repManEvaluate;
    }

    public String getRepManTel() {
        return repManTel;
    }

    public void setRepManTel(String repManTel) {
        this.repManTel = repManTel;
    }

    public String getRepManProfession() {
        return repManProfession;
    }

    public void setRepManProfession(String repManProfession) {
        this.repManProfession = repManProfession;
    }

    public String getRepManEmail() {
        return repManEmail;
    }

    public void setRepManEmail(String repManEmail) {
        this.repManEmail = repManEmail;
    }

    public String getRepManName() {
        return repManName;
    }

    public void setRepManName(String repManName) {
        this.repManName = repManName;
    }

    public String getRepDepartmentEvaluate() {
        return repDepartmentEvaluate;
    }

    public void setRepDepartmentEvaluate(String repDepartmentEvaluate) {
        this.repDepartmentEvaluate = repDepartmentEvaluate;
    }

    public String getRepDepartmentNm() {
        return repDepartmentNm;
    }

    public void setRepDepartmentNm(String repDepartmentNm) {
        this.repDepartmentNm = repDepartmentNm;
    }

    public String getRepDepartmentContactMan() {
        return repDepartmentContactMan;
    }

    public void setRepDepartmentContactMan(String repDepartmentContactMan) {
        this.repDepartmentContactMan = repDepartmentContactMan;
    }

    public String getRepDepartmentTel() {
        return repDepartmentTel;
    }

    public void setRepDepartmentTel(String repDepartmentTel) {
        this.repDepartmentTel = repDepartmentTel;
    }

    public String getInfSource() {
        return infSource;
    }

    public void setInfSource(String infSource) {
        this.infSource = infSource;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBadReactionProcessDescr() {
        return badReactionProcessDescr;
    }

    public void setBadReactionProcessDescr(String badReactionProcessDescr) {
        this.badReactionProcessDescr = badReactionProcessDescr;
    }

    public String getBirthDateString() {
        return birthDateString;
    }

    public void setBirthDateString(String birthDateString) {
        this.birthDateString = birthDateString;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
