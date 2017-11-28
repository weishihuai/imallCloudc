package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.*;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.BadReactionDrugInf;
import com.imall.iportal.core.shop.entity.BadReactionRep;
import com.imall.iportal.core.shop.repository.BadReactionRepRepository;
import com.imall.iportal.core.shop.service.BadReactionRepService;
import com.imall.iportal.core.shop.vo.*;
import com.imall.iportal.dicts.UserSexCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * (服务层实现)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class BadReactionRepServiceImpl extends AbstractBaseService<BadReactionRep, Long> implements BadReactionRepService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private BadReactionRepRepository getBadReactionRepRepository() {
        return (BadReactionRepRepository) baseRepository;
    }

    @Override
    public Page<BadReactionRepPageVo> query(Pageable pageable, BadReactionRegSearchParam badReactionRegSearchParam) throws ParseException {
        Page<BadReactionRep> badReactionRepPage = getBadReactionRepRepository().query(pageable, badReactionRegSearchParam);
        List<BadReactionRepPageVo> badReactionRepPageVoList = new ArrayList<>();
        for (BadReactionRep badReactionRep : badReactionRepPage.getContent()) {
            badReactionRepPageVoList.add(buildBadReactionRepPageVo(badReactionRep));
        }
        return new PageImpl<>(badReactionRepPageVoList, pageable, badReactionRepPage.getTotalElements());
    }

    @Override
    public BadReactionRepDetailVo findById(Long id, Long shopId) {
        BadReactionRep badReactionRep = getBadReactionRepRepository().findByIdAndShopId(id, shopId);
        if (badReactionRep == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"不良反应报告"}));
        }
        List<BadReactionDrugInf> suspectDrugList = ServiceManager.badReactionDrugInfService.listByBadReactionRepId(id, BadReactionDrugInfTypeCodeEnum.SUSPECT_DRUG.toCode());
        List<BadReactionDrugInf> blendDrugList = ServiceManager.badReactionDrugInfService.listByBadReactionRepId(id, BadReactionDrugInfTypeCodeEnum.BLEND_DRUG.toCode());

        //怀疑药品
        List<SuspectDrugDetailVo> suspectDrugInfList = new ArrayList<>();
        for (BadReactionDrugInf badReactionDrugInf : suspectDrugList) {
            SuspectDrugDetailVo suspectDrugDetailVo = new SuspectDrugDetailVo();
            suspectDrugDetailVo.setSuspectDrugInfTypeCode(BadReactionDrugInfTypeCodeEnum.SUSPECT_DRUG.toCode());
            suspectDrugDetailVo.setSuspectApprovalNumber(badReactionDrugInf.getApprovalNumber());
            suspectDrugDetailVo.setSuspectGoodsNm(badReactionDrugInf.getGoodsNm());
            suspectDrugDetailVo.setSuspectCommonNm(badReactionDrugInf.getCommonNm());
            suspectDrugDetailVo.setSuspectProduceManufacturer(badReactionDrugInf.getProduceManufacturer());
            suspectDrugDetailVo.setSuspectBatch(badReactionDrugInf.getBatch());
            suspectDrugDetailVo.setSuspectUsageAndDosage(badReactionDrugInf.getUsageAndDosage());
            suspectDrugDetailVo.setSuspectDrugUseTime(badReactionDrugInf.getDrugUseTime());
            suspectDrugDetailVo.setSuspectDrugUseReason(badReactionDrugInf.getDrugUseReason());
            suspectDrugInfList.add(suspectDrugDetailVo);
        }
        //并用药品
        List<BlendDrugDetailVo> blendDrugInfList = new ArrayList<>();
        for (BadReactionDrugInf badReactionDrugInf : blendDrugList) {
            BlendDrugDetailVo blendDrugDetailVo = new BlendDrugDetailVo();
            blendDrugDetailVo.setBlendDrugInfTypeCode(BadReactionDrugInfTypeCodeEnum.BLEND_DRUG.toCode());
            blendDrugDetailVo.setBlendApprovalNumber(badReactionDrugInf.getApprovalNumber());
            blendDrugDetailVo.setBlendGoodsNm(badReactionDrugInf.getGoodsNm());
            blendDrugDetailVo.setBlendCommonNm(badReactionDrugInf.getCommonNm());
            blendDrugDetailVo.setBlendProduceManufacturer(badReactionDrugInf.getProduceManufacturer());
            blendDrugDetailVo.setBlendBatch(badReactionDrugInf.getBatch());
            blendDrugDetailVo.setBlendUsageAndDosage(badReactionDrugInf.getUsageAndDosage());
            blendDrugDetailVo.setBlendDrugUseTime(badReactionDrugInf.getDrugUseTime());
            blendDrugDetailVo.setBlendDrugUseReason(badReactionDrugInf.getDrugUseReason());
            blendDrugInfList.add(blendDrugDetailVo);
        }
        BadReactionRepDetailVo badReactionRepDetailVo = CommonUtil.copyProperties(badReactionRep, new BadReactionRepDetailVo());
        badReactionRepDetailVo.setBirthDateString(badReactionRep.getBirthDateString());
        badReactionRepDetailVo.setBadReactionOccurTimeString(badReactionRep.getBadReactionOccurTimeString());
        badReactionRepDetailVo.setRepDateString(badReactionRep.getRepDateString());
        badReactionRepDetailVo.setDeadTimeString(badReactionRep.getDeadTimeString());
        badReactionRepDetailVo.setSuspectDrugInfList(suspectDrugInfList);
        badReactionRepDetailVo.setBlendDrugInfList(blendDrugInfList);
        return badReactionRepDetailVo;
    }

    @Override
    @Transactional
    public void saveBadReactionRep(BadReactionRepSaveVo badReactionRepSaveVo) throws ParseException {
        BadReactionRep badReactionRep = new BadReactionRep();
        badReactionRep.setShopId(badReactionRepSaveVo.getShopId());
        badReactionRep.setIsFirstRep(StringUtils.isNotBlank(badReactionRepSaveVo.getIsFirstRep()) ? BadReactionIsFirstRepTypeCodeEnum.fromCode(badReactionRepSaveVo.getIsFirstRep()).toCode() : BadReactionIsFirstRepTypeCodeEnum.FIRST_REG.toCode());
        badReactionRep.setRepType(StringUtils.isNotBlank(badReactionRepSaveVo.getRepType()) ? BadReactionRepTypeCodeEnum.fromCode(badReactionRepSaveVo.getRepType()).toCode() : BadReactionRepTypeCodeEnum.NOVEL.toCode());
        badReactionRep.setRepDepartment(StringUtils.isNotBlank(badReactionRepSaveVo.getRepDepartment()) ? BadReactionRepDepartmentTypeCodeEnum.fromCode(badReactionRepSaveVo.getRepDepartment()).toCode() : BadReactionRepDepartmentTypeCodeEnum.MEDICAL_INSTITUTION.toCode());
        badReactionRep.setCode(badReactionRepSaveVo.getCode());
        badReactionRep.setPatientName(badReactionRepSaveVo.getPatientName());
        badReactionRep.setSexCode(StringUtils.isNotBlank(badReactionRepSaveVo.getSexCode()) ? UserSexCodeEnum.fromCode(badReactionRepSaveVo.getSexCode()).toCode() : UserSexCodeEnum.SECRET.toCode());
        if (StringUtils.isNotBlank(badReactionRepSaveVo.getBirthDateString())) {
            badReactionRep.setBirthDate(DateTimeUtils.convertStringToDate(badReactionRepSaveVo.getBirthDateString()));
        }
        badReactionRep.setAge(badReactionRepSaveVo.getAge());
        badReactionRep.setNation(badReactionRepSaveVo.getNation());
        badReactionRep.setWeight(badReactionRepSaveVo.getWeight());
        badReactionRep.setContactWay(badReactionRepSaveVo.getContactWay());
        badReactionRep.setOriginalDisease(badReactionRepSaveVo.getOriginalDisease());
        badReactionRep.setHospitalNm(badReactionRepSaveVo.getHospitalNm());
        badReactionRep.setMedicalRecordNo(badReactionRepSaveVo.getMedicalRecordNo());
        badReactionRep.setPastDrugBadReaction(StringUtils.isNotBlank(badReactionRepSaveVo.getPastDrugBadReaction()) ? BadReactionDrugBadReactionTypeCodeEnum.fromCode(badReactionRepSaveVo.getPastDrugBadReaction()).toCode() : BadReactionDrugBadReactionTypeCodeEnum.HAS.toCode());
        badReactionRep.setFamilyDrugBadReaction(StringUtils.isNotBlank(badReactionRepSaveVo.getFamilyDrugBadReaction()) ? BadReactionDrugBadReactionTypeCodeEnum.fromCode(badReactionRepSaveVo.getFamilyDrugBadReaction()).toCode() : BadReactionDrugBadReactionTypeCodeEnum.HAS.toCode());
        badReactionRep.setReferImportantInf(StringUtils.isNotBlank(badReactionRepSaveVo.getReferImportantInf()) ? BadReactionReferImportantInfTypeCodeEnum.fromCode(badReactionRepSaveVo.getReferImportantInf()).toCode() : BadReactionReferImportantInfTypeCodeEnum.SMOKE_HISTORY.toCode());
        badReactionRep.setBadReaction(badReactionRepSaveVo.getBadReaction());
        if (StringUtils.isNotBlank(badReactionRepSaveVo.getBadReactionOccurTimeString())) {
            badReactionRep.setBadReactionOccurTime(DateTimeUtils.convertStringToDate(badReactionRepSaveVo.getBadReactionOccurTimeString()));
        }
        badReactionRep.setBadReactionResult(StringUtils.isNotBlank(badReactionRepSaveVo.getBadReactionResult()) ? BadReactionResultCodeEnum.fromCode(badReactionRepSaveVo.getBadReactionResult()).toCode() : BadReactionResultCodeEnum.RECOVERY.toCode());
        if (StringUtils.isNotBlank(badReactionRepSaveVo.getRepDateString())) {
            badReactionRep.setRepDate(DateTimeUtils.convertStringToDate(badReactionRepSaveVo.getRepDateString()));
        }
        badReactionRep.setSequelaPerform(badReactionRepSaveVo.getSequelaPerform());
        badReactionRep.setCauseOfDeath(badReactionRepSaveVo.getCauseOfDeath());
        if (StringUtils.isNotBlank(badReactionRepSaveVo.getDeadTimeString())) {
            badReactionRep.setDeadTime(DateTimeUtils.convertStringToDate(badReactionRepSaveVo.getDeadTimeString()));
        }

        if (StringUtils.isNotBlank(badReactionRepSaveVo.getResponseIsEase())) {
            badReactionRep.setResponseIsEase(BadReactionResponseIsEaseTypeCodeEnum.fromCode(badReactionRepSaveVo.getResponseIsEase()).toCode());
        }
        if (StringUtils.isNotBlank(badReactionRepSaveVo.getIsAppearAgain())) {
            badReactionRep.setIsAppearAgain(BadReactionIsAppearAgainTypeCodeEnum.fromCode(badReactionRepSaveVo.getIsAppearAgain()).toCode());
        }
        if (StringUtils.isNotBlank(badReactionRepSaveVo.getEffectToOriginalDisease())) {
            badReactionRep.setEffectToOriginalDisease(BadReactionEffectToOriginalDiseaseTypeCodeEnum.fromCode(badReactionRepSaveVo.getEffectToOriginalDisease()).toCode());
        }
        if (StringUtils.isNotBlank(badReactionRepSaveVo.getRepManEvaluate())) {
            badReactionRep.setRepManEvaluate(BadReactionRepEvaluateTypeCodeEnum.fromCode(badReactionRepSaveVo.getRepManEvaluate()).toCode());
        }
        badReactionRep.setRepManTel(badReactionRepSaveVo.getRepManTel());
        if (StringUtils.isNotBlank(badReactionRepSaveVo.getRepManProfession())) {
            badReactionRep.setRepManProfession(BadReactionRepManProfessionTypeCodeEnum.fromCode(badReactionRepSaveVo.getRepManProfession()).toCode());
        }
        badReactionRep.setRepManEmail(badReactionRepSaveVo.getRepManEmail());
        badReactionRep.setRepManName(badReactionRepSaveVo.getRepManName());
        if (StringUtils.isNotBlank(badReactionRepSaveVo.getRepDepartmentEvaluate())) {
            badReactionRep.setRepDepartmentEvaluate(BadReactionRepEvaluateTypeCodeEnum.fromCode(badReactionRepSaveVo.getRepDepartmentEvaluate()).toCode());
        }
        badReactionRep.setRepDepartmentNm(badReactionRepSaveVo.getRepDepartmentNm());
        badReactionRep.setRepDepartmentContactMan(badReactionRepSaveVo.getRepDepartmentContactMan());
        badReactionRep.setRepDepartmentTel(badReactionRepSaveVo.getRepDepartmentTel());
        if (StringUtils.isNotBlank(badReactionRepSaveVo.getInfSource())) {
            badReactionRep.setInfSource(BadReactionManufacturingEnterpriseInfSourceTypeCodeEnum.fromCode(badReactionRepSaveVo.getInfSource()).toCode());
        }
        badReactionRep.setRemark(badReactionRepSaveVo.getRemark());
        badReactionRep.setBadReactionProcessDescr(badReactionRepSaveVo.getBadReactionProcessDescr());
        Long badReactionRepId = this.save(badReactionRep).getId();
        //保存怀疑药品信息
        for (SuspectDrugSaveVo suspectDrugSaveVo : badReactionRepSaveVo.getSuspectDrugList()) {
            BadReactionDrugInf badReactionDrugInf = new BadReactionDrugInf();
            badReactionDrugInf.setBadReactionRepId(badReactionRepId);
            badReactionDrugInf.setDrugInfTypeCode(BadReactionDrugInfTypeCodeEnum.SUSPECT_DRUG.toCode());
            badReactionDrugInf.setApprovalNumber(StringUtils.isNotBlank(suspectDrugSaveVo.getSuspectApprovalNumber()) ? suspectDrugSaveVo.getSuspectApprovalNumber().trim() : "");
            badReactionDrugInf.setGoodsNm(StringUtils.isNotBlank(suspectDrugSaveVo.getSuspectGoodsNm()) ? suspectDrugSaveVo.getSuspectGoodsNm().trim() : "");
            badReactionDrugInf.setCommonNm(StringUtils.isNotBlank(suspectDrugSaveVo.getSuspectCommonNm()) ? suspectDrugSaveVo.getSuspectCommonNm().trim() : "");
            badReactionDrugInf.setProduceManufacturer(StringUtils.isNotBlank(suspectDrugSaveVo.getSuspectProduceManufacturer()) ? suspectDrugSaveVo.getSuspectProduceManufacturer().trim() : "");
            badReactionDrugInf.setBatch(StringUtils.isNotBlank(suspectDrugSaveVo.getSuspectBatch()) ? suspectDrugSaveVo.getSuspectBatch().trim() : "");
            badReactionDrugInf.setUsageAndDosage(StringUtils.isNotBlank(suspectDrugSaveVo.getSuspectUsageAndDosage()) ? suspectDrugSaveVo.getSuspectUsageAndDosage().trim() : "");
            badReactionDrugInf.setDrugUseTime(StringUtils.isNotBlank(suspectDrugSaveVo.getSuspectDrugUseTime()) ? suspectDrugSaveVo.getSuspectDrugUseTime().trim() : "");
            badReactionDrugInf.setDrugUseReason(StringUtils.isNotBlank(suspectDrugSaveVo.getSuspectDrugUseReason()) ? suspectDrugSaveVo.getSuspectDrugUseReason().trim() : "");
            ServiceManager.badReactionDrugInfService.save(badReactionDrugInf);
        }

        //保存并用药品信息
        for (BlendDrugSaveVo blendDrugSaveVo : badReactionRepSaveVo.getBlendDrugList()) {
            BadReactionDrugInf badReactionDrugInf = new BadReactionDrugInf();
            badReactionDrugInf.setBadReactionRepId(badReactionRepId);
            badReactionDrugInf.setDrugInfTypeCode(BadReactionDrugInfTypeCodeEnum.BLEND_DRUG.toCode());
            badReactionDrugInf.setApprovalNumber(StringUtils.isNotBlank(blendDrugSaveVo.getBlendApprovalNumber()) ? blendDrugSaveVo.getBlendApprovalNumber().trim() : "");
            badReactionDrugInf.setGoodsNm(StringUtils.isNotBlank(blendDrugSaveVo.getBlendGoodsNm()) ? blendDrugSaveVo.getBlendGoodsNm().trim() : "");
            badReactionDrugInf.setCommonNm(StringUtils.isNotBlank(blendDrugSaveVo.getBlendCommonNm()) ? blendDrugSaveVo.getBlendCommonNm().trim() : "");
            badReactionDrugInf.setProduceManufacturer(StringUtils.isNotBlank(blendDrugSaveVo.getBlendProduceManufacturer()) ? blendDrugSaveVo.getBlendProduceManufacturer().trim() : "");
            badReactionDrugInf.setBatch(StringUtils.isNotBlank(blendDrugSaveVo.getBlendBatch()) ? blendDrugSaveVo.getBlendBatch().trim() : "");
            badReactionDrugInf.setUsageAndDosage(StringUtils.isNotBlank(blendDrugSaveVo.getBlendUsageAndDosage()) ? blendDrugSaveVo.getBlendUsageAndDosage().trim() : "");
            badReactionDrugInf.setDrugUseTime(StringUtils.isNotBlank(blendDrugSaveVo.getBlendDrugUseTime()) ? blendDrugSaveVo.getBlendDrugUseTime().trim() : "");
            badReactionDrugInf.setDrugUseReason(StringUtils.isNotBlank(blendDrugSaveVo.getBlendDrugUseReason()) ? blendDrugSaveVo.getBlendDrugUseReason().trim() : "");
            ServiceManager.badReactionDrugInfService.save(badReactionDrugInf);
        }
    }

    @Override
    @Transactional
    public void updateBadReactionRep(BadReactionRepUpdateVo badReactionRepUpdateVo) throws ParseException {
        BadReactionRep badReactionRep = this.findOne(badReactionRepUpdateVo.getId());
        if (badReactionRep == null || !badReactionRepUpdateVo.getShopId().equals(badReactionRep.getShopId())) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"不良反应报告"}));
        }
        badReactionRep.setIsFirstRep(BadReactionIsFirstRepTypeCodeEnum.fromCode(badReactionRepUpdateVo.getIsFirstRep()).toCode());
        badReactionRep.setRepType(BadReactionRepTypeCodeEnum.fromCode(badReactionRepUpdateVo.getRepType()).toCode());
        badReactionRep.setRepDepartment(BadReactionRepDepartmentTypeCodeEnum.fromCode(badReactionRepUpdateVo.getRepDepartment()).toCode());
        badReactionRep.setCode(badReactionRepUpdateVo.getCode());
        badReactionRep.setPatientName(badReactionRepUpdateVo.getPatientName());
        badReactionRep.setSexCode(UserSexCodeEnum.fromCode(badReactionRepUpdateVo.getSexCode()).toCode());
        if (StringUtils.isNotBlank(badReactionRepUpdateVo.getBirthDateString())) {
            badReactionRep.setBirthDate(DateTimeUtils.convertStringToDate(badReactionRepUpdateVo.getBirthDateString()));
        }
        badReactionRep.setAge(badReactionRepUpdateVo.getAge());
        badReactionRep.setNation(badReactionRepUpdateVo.getNation());
        badReactionRep.setWeight(badReactionRepUpdateVo.getWeight());
        badReactionRep.setContactWay(badReactionRepUpdateVo.getContactWay());
        badReactionRep.setOriginalDisease(badReactionRepUpdateVo.getOriginalDisease());
        badReactionRep.setHospitalNm(badReactionRepUpdateVo.getHospitalNm());
        badReactionRep.setMedicalRecordNo(badReactionRepUpdateVo.getMedicalRecordNo());
        badReactionRep.setPastDrugBadReaction(StringUtils.isNotBlank(badReactionRepUpdateVo.getPastDrugBadReaction()) ? BadReactionDrugBadReactionTypeCodeEnum.fromCode(badReactionRepUpdateVo.getPastDrugBadReaction()).toCode() : "");
        badReactionRep.setFamilyDrugBadReaction(StringUtils.isNotBlank(badReactionRepUpdateVo.getFamilyDrugBadReaction()) ? BadReactionDrugBadReactionTypeCodeEnum.fromCode(badReactionRepUpdateVo.getFamilyDrugBadReaction()).toCode() : "");
        badReactionRep.setReferImportantInf(StringUtils.isNotBlank(badReactionRepUpdateVo.getReferImportantInf()) ? BadReactionReferImportantInfTypeCodeEnum.fromCode(badReactionRepUpdateVo.getReferImportantInf()).toCode() : "");
        badReactionRep.setBadReaction(badReactionRepUpdateVo.getBadReaction());
        if (StringUtils.isNotBlank(badReactionRepUpdateVo.getBadReactionOccurTimeString())) {
            badReactionRep.setBadReactionOccurTime(DateTimeUtils.convertStringToDate(badReactionRepUpdateVo.getBadReactionOccurTimeString()));
        }
        badReactionRep.setBadReactionResult(StringUtils.isNotBlank(badReactionRepUpdateVo.getBadReactionResult()) ? BadReactionResultCodeEnum.fromCode(badReactionRepUpdateVo.getBadReactionResult()).toCode() : "");
        if (StringUtils.isNotBlank(badReactionRepUpdateVo.getRepDateString())) {
            badReactionRep.setRepDate(DateTimeUtils.convertStringToDate(badReactionRepUpdateVo.getRepDateString()));
        }
        badReactionRep.setSequelaPerform(badReactionRepUpdateVo.getSequelaPerform());
        badReactionRep.setCauseOfDeath(badReactionRepUpdateVo.getCauseOfDeath());
        if (StringUtils.isNotBlank(badReactionRepUpdateVo.getDeadTimeString())) {
            badReactionRep.setDeadTime(DateTimeUtils.convertStringToDate(badReactionRepUpdateVo.getDeadTimeString()));
        }
        badReactionRep.setResponseIsEase(StringUtils.isNotBlank(badReactionRepUpdateVo.getResponseIsEase()) ? BadReactionResponseIsEaseTypeCodeEnum.fromCode(badReactionRepUpdateVo.getResponseIsEase()).toCode() : "");
        badReactionRep.setIsAppearAgain(StringUtils.isNotBlank(badReactionRepUpdateVo.getIsAppearAgain()) ? BadReactionIsAppearAgainTypeCodeEnum.fromCode(badReactionRepUpdateVo.getIsAppearAgain()).toCode() : "");
        badReactionRep.setEffectToOriginalDisease(StringUtils.isNotBlank(badReactionRepUpdateVo.getEffectToOriginalDisease()) ? BadReactionEffectToOriginalDiseaseTypeCodeEnum.fromCode(badReactionRepUpdateVo.getEffectToOriginalDisease()).toCode() : "");
        badReactionRep.setRepManEvaluate(StringUtils.isNotBlank(badReactionRepUpdateVo.getRepManEvaluate()) ? BadReactionRepEvaluateTypeCodeEnum.fromCode(badReactionRepUpdateVo.getRepManEvaluate()).toCode():"");
        badReactionRep.setRepManTel(badReactionRepUpdateVo.getRepManTel());
        badReactionRep.setRepManProfession(StringUtils.isNotBlank(badReactionRepUpdateVo.getRepManProfession()) ? BadReactionRepManProfessionTypeCodeEnum.fromCode(badReactionRepUpdateVo.getRepManProfession()).toCode() : "");
        badReactionRep.setRepManEmail(badReactionRepUpdateVo.getRepManEmail());
        badReactionRep.setRepManName(badReactionRepUpdateVo.getRepManName());
        badReactionRep.setRepDepartmentEvaluate(StringUtils.isNotBlank(badReactionRepUpdateVo.getRepDepartmentEvaluate()) ? BadReactionRepEvaluateTypeCodeEnum.fromCode(badReactionRepUpdateVo.getRepDepartmentEvaluate()).toCode() : "");
        badReactionRep.setRepDepartmentNm(badReactionRepUpdateVo.getRepDepartmentNm());
        badReactionRep.setRepDepartmentContactMan(badReactionRepUpdateVo.getRepDepartmentContactMan());
        badReactionRep.setRepDepartmentTel(badReactionRepUpdateVo.getRepDepartmentTel());
        badReactionRep.setInfSource(StringUtils.isNotBlank(badReactionRepUpdateVo.getInfSource()) ? BadReactionManufacturingEnterpriseInfSourceTypeCodeEnum.fromCode(badReactionRepUpdateVo.getInfSource()).toCode() : "");
        badReactionRep.setRemark(badReactionRepUpdateVo.getRemark());
        badReactionRep.setBadReactionProcessDescr(badReactionRepUpdateVo.getBadReactionProcessDescr());
        this.update(badReactionRep);

        //更新怀疑药品信息
        for (SuspectDrugUpdateVo suspectDrugUpdateVo : badReactionRepUpdateVo.getSuspectDrugList()) {
            BadReactionDrugInf badReactionDrugInf = ServiceManager.badReactionDrugInfService.findOne(suspectDrugUpdateVo.getId());
            if (badReactionDrugInf == null) {
                String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"怀疑药品"}));
            }
            badReactionDrugInf.setDrugInfTypeCode(BadReactionDrugInfTypeCodeEnum.SUSPECT_DRUG.toCode());
            badReactionDrugInf.setApprovalNumber(StringUtils.isNotBlank(suspectDrugUpdateVo.getSuspectApprovalNumber()) ? suspectDrugUpdateVo.getSuspectApprovalNumber().trim() : "");
            badReactionDrugInf.setGoodsNm(StringUtils.isNotBlank(suspectDrugUpdateVo.getSuspectGoodsNm()) ? suspectDrugUpdateVo.getSuspectGoodsNm().trim() : "");
            badReactionDrugInf.setCommonNm(StringUtils.isNotBlank(suspectDrugUpdateVo.getSuspectCommonNm()) ? suspectDrugUpdateVo.getSuspectCommonNm().trim() : "");
            badReactionDrugInf.setProduceManufacturer(StringUtils.isNotBlank(suspectDrugUpdateVo.getSuspectProduceManufacturer()) ? suspectDrugUpdateVo.getSuspectProduceManufacturer().trim() : "");
            badReactionDrugInf.setBatch(StringUtils.isNotBlank(suspectDrugUpdateVo.getSuspectBatch()) ? suspectDrugUpdateVo.getSuspectBatch().trim() : "");
            badReactionDrugInf.setUsageAndDosage(StringUtils.isNotBlank(suspectDrugUpdateVo.getSuspectUsageAndDosage()) ? suspectDrugUpdateVo.getSuspectUsageAndDosage().trim() : "");
            badReactionDrugInf.setDrugUseTime(StringUtils.isNotBlank(suspectDrugUpdateVo.getSuspectDrugUseTime()) ? suspectDrugUpdateVo.getSuspectDrugUseTime().trim() : "");
            badReactionDrugInf.setDrugUseReason(StringUtils.isNotBlank(suspectDrugUpdateVo.getSuspectDrugUseReason()) ? suspectDrugUpdateVo.getSuspectDrugUseReason().trim() : "");
            ServiceManager.badReactionDrugInfService.update(badReactionDrugInf);
        }

        //更新并用药品信息
        for (BlendDrugUpdateVo blendDrugUpdateVo : badReactionRepUpdateVo.getBlendDrugList()) {
            BadReactionDrugInf badReactionDrugInf = ServiceManager.badReactionDrugInfService.findOne(blendDrugUpdateVo.getId());
            if (badReactionDrugInf == null) {
                String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
                throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"并用药品"}));
            }
            badReactionDrugInf.setDrugInfTypeCode(BadReactionDrugInfTypeCodeEnum.BLEND_DRUG.toCode());
            badReactionDrugInf.setApprovalNumber(StringUtils.isNotBlank(blendDrugUpdateVo.getBlendApprovalNumber()) ? blendDrugUpdateVo.getBlendApprovalNumber().trim() : "");
            badReactionDrugInf.setGoodsNm(StringUtils.isNotBlank(blendDrugUpdateVo.getBlendGoodsNm()) ? blendDrugUpdateVo.getBlendGoodsNm().trim() : "");
            badReactionDrugInf.setCommonNm(StringUtils.isNotBlank(blendDrugUpdateVo.getBlendCommonNm()) ? blendDrugUpdateVo.getBlendCommonNm().trim() : "");
            badReactionDrugInf.setProduceManufacturer(StringUtils.isNotBlank(blendDrugUpdateVo.getBlendProduceManufacturer()) ? blendDrugUpdateVo.getBlendProduceManufacturer().trim() : "");
            badReactionDrugInf.setBatch(StringUtils.isNotBlank(blendDrugUpdateVo.getBlendBatch()) ? blendDrugUpdateVo.getBlendBatch().trim() : "");
            badReactionDrugInf.setUsageAndDosage(StringUtils.isNotBlank(blendDrugUpdateVo.getBlendUsageAndDosage()) ? blendDrugUpdateVo.getBlendUsageAndDosage().trim() : "");
            badReactionDrugInf.setDrugUseTime(StringUtils.isNotBlank(blendDrugUpdateVo.getBlendDrugUseTime()) ? blendDrugUpdateVo.getBlendDrugUseTime().trim() : "");
            badReactionDrugInf.setDrugUseReason(StringUtils.isNotBlank(blendDrugUpdateVo.getBlendDrugUseReason()) ? blendDrugUpdateVo.getBlendDrugUseReason().trim() : "");
            ServiceManager.badReactionDrugInfService.update(badReactionDrugInf);
        }
    }

    /**
     * 构建列表PageVo对象
     *
     * @param badReactionRep
     * @return
     * @throws ParseException
     */
    private BadReactionRepPageVo buildBadReactionRepPageVo(BadReactionRep badReactionRep) throws ParseException {
        BadReactionRepPageVo badReactionRepPageVo = new BadReactionRepPageVo();
        badReactionRepPageVo.setId(badReactionRep.getId());
        badReactionRepPageVo.setShopId(badReactionRep.getShopId());
        badReactionRepPageVo.setIsFirstRep(BadReactionIsFirstRepTypeCodeEnum.fromCode(badReactionRep.getIsFirstRep()).toCode());
        badReactionRepPageVo.setRepType(BadReactionRepTypeCodeEnum.fromCode(badReactionRep.getRepType()).toCode());
        badReactionRepPageVo.setRepDepartment(BadReactionRepDepartmentTypeCodeEnum.fromCode(badReactionRep.getRepDepartment()).toCode());
        badReactionRepPageVo.setCode(badReactionRep.getCode());
        badReactionRepPageVo.setPatientName(badReactionRep.getPatientName());
        badReactionRepPageVo.setSexCode(UserSexCodeEnum.fromCode(badReactionRep.getSexCode()).toCode());
        if (StringUtils.isNotBlank(badReactionRep.getBirthDateString())) {
            badReactionRepPageVo.setBirthDateString(badReactionRep.getBirthDateString());
        }
        badReactionRepPageVo.setAge(badReactionRep.getAge());
        badReactionRepPageVo.setNation(badReactionRep.getNation());
        badReactionRepPageVo.setWeight(badReactionRep.getWeight());
        badReactionRepPageVo.setContactWay(badReactionRep.getContactWay());
        badReactionRepPageVo.setOriginalDisease(badReactionRep.getOriginalDisease());
        badReactionRepPageVo.setHospitalNm(badReactionRep.getHospitalNm());
        badReactionRepPageVo.setMedicalRecordNo(badReactionRep.getMedicalRecordNo());
        if (StringUtils.isNotBlank(badReactionRep.getPastDrugBadReaction())) {
            badReactionRepPageVo.setPastDrugBadReaction(BadReactionDrugBadReactionTypeCodeEnum.fromCode(badReactionRep.getPastDrugBadReaction()).toCode());
        }
        if (StringUtils.isNotBlank(badReactionRep.getFamilyDrugBadReaction())) {
            badReactionRepPageVo.setFamilyDrugBadReaction(BadReactionDrugBadReactionTypeCodeEnum.fromCode(badReactionRep.getFamilyDrugBadReaction()).toCode());
        }
        if (StringUtils.isNotBlank(badReactionRep.getReferImportantInf())) {
            badReactionRepPageVo.setReferImportantInf(BadReactionReferImportantInfTypeCodeEnum.fromCode(badReactionRep.getReferImportantInf()).toCode());
        }
        badReactionRepPageVo.setBadReaction(badReactionRep.getBadReaction());
        if (StringUtils.isNotBlank(badReactionRep.getBadReactionResult())) {
            badReactionRepPageVo.setBadReactionResult(BadReactionResultCodeEnum.fromCode(badReactionRep.getBadReactionResult()).toCode());
        }
        badReactionRepPageVo.setSequelaPerform(badReactionRep.getSequelaPerform());
        badReactionRepPageVo.setCauseOfDeath(badReactionRep.getCauseOfDeath());
        if (StringUtils.isNotBlank(badReactionRep.getDeadTimeString())) {
            badReactionRepPageVo.setDeadTimeString(badReactionRep.getDeadTimeString());
        }
        if (StringUtils.isNotBlank(badReactionRep.getResponseIsEase())) {
            badReactionRepPageVo.setResponseIsEase(BadReactionResponseIsEaseTypeCodeEnum.fromCode(badReactionRep.getResponseIsEase()).toCode());
        }
        if (StringUtils.isNotBlank(badReactionRep.getIsAppearAgain())) {
            badReactionRepPageVo.setIsAppearAgain(BadReactionIsAppearAgainTypeCodeEnum.fromCode(badReactionRep.getIsAppearAgain()).toCode());
        }
        if (StringUtils.isNotBlank(badReactionRep.getEffectToOriginalDisease())) {
            badReactionRepPageVo.setEffectToOriginalDisease(BadReactionEffectToOriginalDiseaseTypeCodeEnum.fromCode(badReactionRep.getEffectToOriginalDisease()).toCode());
        }
        if (StringUtils.isNotBlank(badReactionRep.getRepManEvaluate())) {
            badReactionRepPageVo.setRepManEvaluate(BadReactionRepEvaluateTypeCodeEnum.fromCode(badReactionRep.getRepManEvaluate()).toCode());
        }
        badReactionRepPageVo.setRepManTel(badReactionRep.getRepManTel());
        if (StringUtils.isNotBlank(badReactionRep.getRepManProfession())) {
            badReactionRepPageVo.setRepManProfession(BadReactionRepManProfessionTypeCodeEnum.fromCode(badReactionRep.getRepManProfession()).toCode());
        }
        badReactionRepPageVo.setRepManEmail(badReactionRep.getRepManEmail());
        badReactionRepPageVo.setRepManName(badReactionRep.getRepManName());
        if (StringUtils.isNotBlank(badReactionRep.getRepDepartmentEvaluate())) {
            badReactionRepPageVo.setRepDepartmentEvaluate(BadReactionRepEvaluateTypeCodeEnum.fromCode(badReactionRep.getRepDepartmentEvaluate()).toCode());
        }
        badReactionRepPageVo.setRepDepartmentNm(badReactionRep.getRepDepartmentNm());
        badReactionRepPageVo.setRepDepartmentContactMan(badReactionRep.getRepDepartmentContactMan());
        badReactionRepPageVo.setRepDepartmentTel(badReactionRep.getRepDepartmentTel());
        if (StringUtils.isNotBlank(badReactionRep.getInfSource())) {
            badReactionRepPageVo.setInfSource(BadReactionManufacturingEnterpriseInfSourceTypeCodeEnum.fromCode(badReactionRep.getInfSource()).toCode());
        }
        badReactionRepPageVo.setRemark(badReactionRep.getRemark());
        badReactionRepPageVo.setBadReactionProcessDescr(badReactionRep.getBadReactionProcessDescr());
        badReactionRepPageVo.setRepDateString(StringUtils.isNotBlank(badReactionRep.getRepDateString()) ? badReactionRep.getRepDateString() : "");
        badReactionRepPageVo.setBadReactionOccurTimeString(StringUtils.isNotBlank(badReactionRep.getBadReactionOccurTimeString()) ? badReactionRep.getBadReactionOccurTimeString() : "");
        return badReactionRepPageVo;
    }

}