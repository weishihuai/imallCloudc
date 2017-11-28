package com.imall.iportal.core.platform.service.impl;


import com.imall.commons.base.global.Global;
import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.Pinyin4jUtil;
import com.imall.commons.base.util.SevenZipUtils;
import com.imall.commons.base.util.excel.BaseExcelReader;
import com.imall.commons.base.util.excel.ErrorLog;
import com.imall.commons.base.util.excel.ListExcelWriter;
import com.imall.commons.dicts.*;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.FileMng;
import com.imall.iportal.core.main.entity.SysDictItem;
import com.imall.iportal.core.main.entity.SysFileLib;
import com.imall.iportal.core.main.vo.FileMngVo;
import com.imall.iportal.core.platform.entity.*;
import com.imall.iportal.core.platform.repository.GoodsDocRepository;
import com.imall.iportal.core.platform.service.GoodsDocService;
import com.imall.iportal.core.platform.vo.*;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.text.ParseException;
import java.util.*;

/**
 * (服务层实现)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class GoodsDocServiceImpl extends AbstractBaseService<GoodsDoc, Long> implements GoodsDocService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private GoodsDocRepository getGoodsDocRepository() {
        return (GoodsDocRepository) baseRepository;
    }

    @Override
    public Page<GoodsDocListPageVo> query(Pageable pageable, GoodsDocListSearchParam goodsDocListSearchParam) throws ParseException {
        Page<GoodsDoc> goodsDocs = getGoodsDocRepository().query(pageable, goodsDocListSearchParam);
        List<GoodsDocListPageVo> goodsDocListPageVoList = new ArrayList<>();
        for (GoodsDoc goodsDoc : goodsDocs.getContent()) {
            goodsDocListPageVoList.add(buildGoodsDocListPageVo(goodsDoc));
        }
        return new PageImpl<GoodsDocListPageVo>(goodsDocListPageVoList, pageable, goodsDocs.getTotalElements());
    }

    private GoodsDocListPageVo buildGoodsDocListPageVo(GoodsDoc goodsDoc) {
        GoodsDocListPageVo goodsDocListPageVo = CommonUtil.copyProperties(goodsDoc, new GoodsDocListPageVo());

        if (GoodsTypeCodeEnum.fromCode(goodsDoc.getGoodsTypeCode()) == GoodsTypeCodeEnum.DRUG) {
            GoodsDocDrug goodsDocDrug = ServiceManager.goodsDocDrugService.findByGoodsDocId(goodsDoc.getId());
            if(goodsDocDrug != null) {
                SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsDocDrug.getDosageForm());
                goodsDocListPageVo.setDosageForm(sysDictItem.getDictItemNm());
            }
        }

        if (GoodsTypeCodeEnum.fromCode(goodsDoc.getGoodsTypeCode()) == GoodsTypeCodeEnum.CHINESE_MEDICINE_PIECES) {
            GoodsDocChineseMedicinePieces goodsDocChineseMedicinePieces = ServiceManager.goodsDocChineseMedicinePiecesService.findByGoodsDocId(goodsDoc.getId());
            if(goodsDocChineseMedicinePieces != null){
                goodsDocListPageVo.setProductionPlace(goodsDocChineseMedicinePieces.getProductionPlace());
                SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsDocChineseMedicinePieces.getDosageForm());
                goodsDocListPageVo.setDosageForm(sysDictItem.getDictItemNm());
            }
        }

        return goodsDocListPageVo;
    }

    @Transactional
    @Override
    public Long saveGoodsDoc(GoodsDocSaveVo goodsDocSaveVo) {
        GoodsDoc goodsDoc = CommonUtil.copyProperties(goodsDocSaveVo, new GoodsDoc());
        goodsDoc.setApproveStateCode(GoodsApproveStateCodeEnum.WAIT_APPROVE.toCode());
        goodsDoc.setGoodsTypeCode(GoodsTypeCodeEnum.fromCode(goodsDocSaveVo.getGoodsTypeCode()).toCode());
        goodsDoc.setIsDelete(BoolCodeEnum.NO.toCode());
        goodsDoc.setToxicologyCode(ToxicologyTypeCodeEnum.fromCode(goodsDocSaveVo.getToxicologyCode()).toCode());
        goodsDoc.setStorageCondition(StorageConditionTypeCodeEnum.fromCode(goodsDocSaveVo.getStorageCondition()).toCode());
        goodsDoc.setDisplayPosition(1L);
        goodsDoc.setPinyin(Pinyin4jUtil.getPinYinHeadChar(goodsDocSaveVo.getCommonNm()));
        goodsDoc.setInstructionsStr(goodsDocSaveVo.getInstructions());
        goodsDoc.setMedicationGuideStr(goodsDocSaveVo.getMedicationGuide());
        save(goodsDoc);

        saveGoodsDocType(goodsDocSaveVo, goodsDoc.getId());

        //保存图片
        if (!CollectionUtils.isEmpty(goodsDocSaveVo.getPictFileList())) {
            savePict(FileObjectTypeCodeEnum.GOODS_DOC_PICT, goodsDoc.getId(), goodsDocSaveVo.getPictFileList());
        }
        //保存附件
        if (!CollectionUtils.isEmpty(goodsDocSaveVo.getOtherFileList())) {
            savePict(FileObjectTypeCodeEnum.GOODS_DOC_ATTACHMENT, goodsDoc.getId(), goodsDocSaveVo.getOtherFileList());
        }
        return goodsDoc.getId();
    }

    private void saveGoodsDocType(GoodsDocSaveVo goodsDocSaveVo, Long goodsId) {
        switch (GoodsTypeCodeEnum.fromCode(goodsDocSaveVo.getGoodsTypeCode())) {
            case DRUG:
                if (StringUtils.isBlank(goodsDocSaveVo.getApprovalNumber()) || StringUtils.isBlank(goodsDocSaveVo.getApprovalNumberTermString()) || StringUtils.isBlank(goodsDocSaveVo.getDosageForm())) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_NOT_EMPTY);
                    throw new BusinessException(ResGlobalExt.COMMON_NOT_EMPTY, message);
                }
                GoodsDocDrug goodsDocDrug = CommonUtil.copyProperties(goodsDocSaveVo, new GoodsDocDrug());
                goodsDocDrug.setPrescriptionDrugsTypeCode(PrescriptionDrugsTypeCodeEnum.fromCode(goodsDocSaveVo.getPrescriptionDrugsTypeCode()).toCode());
                goodsDocDrug.setGoodsDocId(goodsId);
                ServiceManager.goodsDocDrugService.save(goodsDocDrug);
                break;
            case OTHER:
                if (StringUtils.isBlank(goodsDocSaveVo.getApprovalNumber())) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_NOT_EMPTY);
                    throw new BusinessException(ResGlobalExt.COMMON_NOT_EMPTY, message);
                }
                GoodsDocOther goodsDocOther = CommonUtil.copyProperties(goodsDocSaveVo, new GoodsDocOther());
                goodsDocOther.setGoodsDocId(goodsId);
                ServiceManager.goodsDocOtherService.save(goodsDocOther);
                break;
            case CHINESE_MEDICINE_PIECES:
                GoodsDocChineseMedicinePieces goodsDocChineseMedicinePieces = CommonUtil.copyProperties(goodsDocSaveVo, new GoodsDocChineseMedicinePieces());
                goodsDocChineseMedicinePieces.setPrescriptionDrugsTypeCode(PrescriptionDrugsTypeCodeEnum.fromCode(goodsDocSaveVo.getPrescriptionDrugsTypeCode()).toCode());
                goodsDocChineseMedicinePieces.setGoodsDocId(goodsId);
                if (BoolCodeEnum.fromCode(goodsDocSaveVo.getIsMedicalInsuranceGoods()) == BoolCodeEnum.YES
                        && goodsDocSaveVo.getMedicalInsuranceNum() != null) {
                    goodsDocChineseMedicinePieces.setMedicalInsuranceNum(goodsDocSaveVo.getMedicalInsuranceNum());
                }
                ServiceManager.goodsDocChineseMedicinePiecesService.save(goodsDocChineseMedicinePieces);
                break;
            case FOOD_HEALTH:
                if (StringUtils.isBlank(goodsDocSaveVo.getFoodHygieneLicenceNum()) || StringUtils.isBlank(goodsDocSaveVo.getProductionDateString()) || StringUtils.isBlank(goodsDocSaveVo.getExpirationDateString()) || StringUtils.isBlank(goodsDocSaveVo.getHealthCareFunc())) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_NOT_EMPTY);
                    throw new BusinessException(ResGlobalExt.COMMON_NOT_EMPTY, message);
                }
                GoodsDocFoodHealth goodsDocFoodHealth = CommonUtil.copyProperties(goodsDocSaveVo, new GoodsDocFoodHealth());
                goodsDocFoodHealth.setGoodsDocId(goodsId);
                ServiceManager.goodsDocFoodHealthService.save(goodsDocFoodHealth);
                break;
            case DAILY_NECESSITIES:
                if (StringUtils.isBlank(goodsDocSaveVo.getApprovalNumber())) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_NOT_EMPTY);
                    throw new BusinessException(ResGlobalExt.COMMON_NOT_EMPTY, message);
                }
                GoodsDocDailyNecessities goodsDailyNecessities = CommonUtil.copyProperties(goodsDocSaveVo, new GoodsDocDailyNecessities());
                goodsDailyNecessities.setGoodsDocId(goodsId);
                ServiceManager.goodsDocDailyNecessitiesService.save(goodsDailyNecessities);
                break;
            case MEDICAL_INSTRUMENTS:
                if (StringUtils.isBlank(goodsDocSaveVo.getRegNum()) || StringUtils.isBlank(goodsDocSaveVo.getRegRegistrationFormNum())) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_NOT_EMPTY);
                    throw new BusinessException(ResGlobalExt.COMMON_NOT_EMPTY, message);
                }
                GoodsDocMedicalInstruments goodsDocMedicalInstruments = CommonUtil.copyProperties(goodsDocSaveVo, new GoodsDocMedicalInstruments());
                goodsDocMedicalInstruments.setGoodsDocId(goodsId);
                ServiceManager.goodsDocMedicalInstrumentsService.save(goodsDocMedicalInstruments);
                break;
            case COSMETIC:
                if (StringUtils.isBlank(goodsDocSaveVo.getApprovalNumber())) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_NOT_EMPTY);
                    throw new BusinessException(ResGlobalExt.COMMON_NOT_EMPTY, message);
                }
                GoodsDocCosmetic goodsDocCosmetic = CommonUtil.copyProperties(goodsDocSaveVo, new GoodsDocCosmetic());
                goodsDocCosmetic.setGoodsDocId(goodsId);
                ServiceManager.goodsDocCosmeticService.save(goodsDocCosmetic);
                break;
        }
    }

    private void savePict(FileObjectTypeCodeEnum fileObjectTypeCodeEnum, Long id, List<FileMng> fileMngs) {
        List<FileMng> fileMnglist = new ArrayList<>();
        for (FileMng fileMng : fileMngs) {
            if (StringUtils.isNotBlank(fileMng.getFileId()) && fileMng.getSysFileLibId() != null) {
                fileMnglist.add(fileMng);
            }
        }
        ServiceManager.fileMngService.saveList(fileObjectTypeCodeEnum, id, fileMnglist);
    }

    private void updatePict(FileObjectTypeCodeEnum fileObjectTypeCodeEnum, Long id, List<FileMng> fileMngs) {
        List<FileMng> fileMnglist = new ArrayList<>();
        for (FileMng fileMng : fileMngs) {
            if (StringUtils.isNotBlank(fileMng.getFileId()) && fileMng.getSysFileLibId() != null) {
                fileMnglist.add(fileMng);
            }
        }
        ServiceManager.fileMngService.saveListAndDeleteOld(fileObjectTypeCodeEnum, id, fileMnglist);
    }

    @Override
    public GoodsDocDetailVo findDetail(Long id) {
        GoodsDoc goodsDoc = findOne(id);
        if (goodsDoc == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }
        GoodsDocDetailVo goodsDocDetailVo = CommonUtil.copyProperties(goodsDoc, new GoodsDocDetailVo());
        goodsDocDetailVo.setInstructions(goodsDoc.getInstructionsStr());
        goodsDocDetailVo.setMedicationGuide(goodsDoc.getMedicationGuideStr());
        goodsDocDetailVo.setGoodsTypeName(GoodsTypeCodeEnum.fromCode(goodsDoc.getGoodsTypeCode()).toName());
        goodsDocDetailVo.setToxicologyName(ToxicologyTypeCodeEnum.fromCode(goodsDoc.getToxicologyCode()).toName());
        goodsDocDetailVo.setStorageConditionName(StorageConditionTypeCodeEnum.fromCode(goodsDoc.getStorageCondition()).toName());

        GoodsCategory goodsCategory = ServiceManager.goodsCategoryService.findOne(goodsDoc.getGoodsCategoryId());
        if (goodsCategory != null) {
            goodsDocDetailVo.setPathName(goodsCategory.getCategoryName());
        }
        switch (GoodsTypeCodeEnum.fromCode(goodsDoc.getGoodsTypeCode())) {
            case DRUG:
                GoodsDocDrug goodsDocDrug = ServiceManager.goodsDocDrugService.findByGoodsDocId(goodsDoc.getId());
                goodsDocDetailVo.setApprovalNumber(goodsDocDrug.getApprovalNumber());
                goodsDocDetailVo.setApproveDateString(goodsDocDrug.getApproveDateString());
                goodsDocDetailVo.setIsImportGoods(goodsDocDrug.getIsImportGoods());
                goodsDocDetailVo.setIsChineseMedicineProtect(goodsDocDrug.getIsChineseMedicineProtect());
                goodsDocDetailVo.setApprovalNumberTermString(goodsDocDrug.getApprovalNumberTermString());
                SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsDocDrug.getDosageForm());
                goodsDocDetailVo.setDosageForm(goodsDocDrug.getDosageForm());
                goodsDocDetailVo.setDosageFormName(sysDictItem.getDictItemNm());
                goodsDocDetailVo.setPrescriptionDrugsTypeCode(goodsDocDrug.getPrescriptionDrugsTypeCode());
                goodsDocDetailVo.setIsEphedrine(goodsDocDrug.getIsEphedrine());
                goodsDocDetailVo.setIsKeyCuring(goodsDocDrug.getIsKeyCuring());
                goodsDocDetailVo.setIsMedicalInsuranceGoods(goodsDocDrug.getIsMedicalInsuranceGoods());
                goodsDocDetailVo.setMedicalInsuranceNum(goodsDocDrug.getMedicalInsuranceNum());
                goodsDocDetailVo.setPrescriptionDrugsTypeName(PrescriptionDrugsTypeCodeEnum.fromCode(goodsDocDrug.getPrescriptionDrugsTypeCode()).toName());
                break;
            case OTHER:
                GoodsDocOther goodsDocOther = ServiceManager.goodsDocOtherService.findByGoodsDocId(goodsDoc.getId());
                goodsDocDetailVo.setApprovalNumber(goodsDocOther.getApprovalNumber());
                goodsDocDetailVo.setManufacturerAddr(goodsDocOther.getManufacturerAddr());
                break;
            case CHINESE_MEDICINE_PIECES:
                GoodsDocChineseMedicinePieces goodsDocChineseMedicinePieces = ServiceManager.goodsDocChineseMedicinePiecesService.findByGoodsDocId(goodsDoc.getId());
                goodsDocDetailVo.setApprovalNumber(goodsDocChineseMedicinePieces.getApprovalNumber());
                goodsDocDetailVo.setApproveDateString(goodsDocChineseMedicinePieces.getApproveDateString());
                goodsDocDetailVo.setIsImportGoods(goodsDocChineseMedicinePieces.getIsImportGoods());
                goodsDocDetailVo.setIsChineseMedicineProtect(goodsDocChineseMedicinePieces.getIsChineseMedicineProtect());
                goodsDocDetailVo.setApprovalNumberTermString(goodsDocChineseMedicinePieces.getApprovalNumberTermString());
                SysDictItem sysDictItem2 = ServiceManager.sysDictItemService.findByDictItemCode(goodsDocChineseMedicinePieces.getDosageForm());
                goodsDocDetailVo.setDosageFormName(sysDictItem2.getDictItemNm());
                goodsDocDetailVo.setDosageForm(goodsDocChineseMedicinePieces.getDosageForm());
                goodsDocDetailVo.setPrescriptionDrugsTypeCode(goodsDocChineseMedicinePieces.getPrescriptionDrugsTypeCode());
                goodsDocDetailVo.setIsEphedrine(goodsDocChineseMedicinePieces.getIsEphedrine());
                goodsDocDetailVo.setIsKeyCuring(goodsDocChineseMedicinePieces.getIsKeyCuring());
                goodsDocDetailVo.setIsMedicalInsuranceGoods(goodsDocChineseMedicinePieces.getIsMedicalInsuranceGoods());
                goodsDocDetailVo.setMedicalInsuranceNum(goodsDocChineseMedicinePieces.getMedicalInsuranceNum());
                goodsDocDetailVo.setProductionPlace(goodsDocChineseMedicinePieces.getProductionPlace());
                goodsDocDetailVo.setEffect(goodsDocChineseMedicinePieces.getEffect());
                goodsDocDetailVo.setPrescriptionDrugsTypeName(PrescriptionDrugsTypeCodeEnum.fromCode(goodsDocChineseMedicinePieces.getPrescriptionDrugsTypeCode()).toName());
                break;
            case FOOD_HEALTH:
                GoodsDocFoodHealth goodsDocFoodHealth = ServiceManager.goodsDocFoodHealthService.findByGoodsDocId(goodsDoc.getId());
                goodsDocDetailVo.setFoodHygieneLicenceNum(goodsDocFoodHealth.getFoodHygieneLicenceNum());
                goodsDocDetailVo.setProductionDateString(goodsDocFoodHealth.getProductionDateString());
                goodsDocDetailVo.setExpirationDateString(goodsDocFoodHealth.getExpirationDateString());
                goodsDocDetailVo.setHealthCareFunc(goodsDocFoodHealth.getHealthCareFunc());
                goodsDocDetailVo.setAppropriateCrowd(goodsDocFoodHealth.getAppropriateCrowd());
                goodsDocDetailVo.setNotAppropriateCrowd(goodsDocFoodHealth.getNotAppropriateCrowd());
                goodsDocDetailVo.setEdibleMethodAndDosage(goodsDocFoodHealth.getEdibleMethodAndDosage());
                goodsDocDetailVo.setStorageMethod(goodsDocFoodHealth.getStorageMethod());
                goodsDocDetailVo.setExecStandard(goodsDocFoodHealth.getExecStandard());
                goodsDocDetailVo.setEffectComposition(goodsDocFoodHealth.getEffectComposition());
                goodsDocDetailVo.setNotice(goodsDocFoodHealth.getNotice());
                break;
            case DAILY_NECESSITIES:
                GoodsDocDailyNecessities goodsDocDailyNecessities = ServiceManager.goodsDocDailyNecessitiesService.findByGoodsDocId(goodsDoc.getId());
                goodsDocDetailVo.setApprovalNumber(goodsDocDailyNecessities.getApprovalNumber());
                goodsDocDetailVo.setManufacturerAddr(goodsDocDailyNecessities.getManufacturerAddr());
                break;
            case MEDICAL_INSTRUMENTS:
                GoodsDocMedicalInstruments goodsDocMedicalInstruments = ServiceManager.goodsDocMedicalInstrumentsService.findByGoodsDocId(goodsDoc.getId());
                goodsDocDetailVo.setRegNum(goodsDocMedicalInstruments.getRegNum());
                goodsDocDetailVo.setRegRegistrationFormNum(goodsDocMedicalInstruments.getRegRegistrationFormNum());
                goodsDocDetailVo.setManufacturerAddr(goodsDocMedicalInstruments.getManufacturerAddr());
                goodsDocDetailVo.setApplyRange(goodsDocMedicalInstruments.getApplyRange());
                goodsDocDetailVo.setProductStandardNum(goodsDocMedicalInstruments.getProductStandardNum());
                break;
            case COSMETIC:
                GoodsDocCosmetic goodsDocCosmetic = ServiceManager.goodsDocCosmeticService.findByGoodsDocId(goodsDoc.getId());
                goodsDocDetailVo.setApprovalNumber(goodsDocCosmetic.getApprovalNumber());
                goodsDocDetailVo.setManufacturerAddr(goodsDocCosmetic.getManufacturerAddr());
                break;
        }

        //获取附件
        List<FileMng> fileMngMsgList = ServiceManager.fileMngService.getList(FileObjectTypeCodeEnum.GOODS_DOC_ATTACHMENT, id);
        List<FileMngVo> fileMngVos = new ArrayList<>();
        for (FileMng fileMng : fileMngMsgList) {
            FileMngVo fileMngVo= CommonUtil.copyProperties(fileMng,new FileMngVo());
            SysFileLib fileLib = ServiceManager.sysFileLibService.findOne(fileMng.getSysFileLibId());
            fileMngVo.setFileNm(fileLib != null?fileLib.getFileNm():"");
            fileMngVos.add(fileMngVo);
        }
        goodsDocDetailVo.setOtherFileVoList(fileMngVos);

        //获取图片
        List<FileMng> pictMngMsgList = ServiceManager.fileMngService.getList(FileObjectTypeCodeEnum.GOODS_DOC_PICT, id);
        List<FileMngVo> pictMngVos = new ArrayList<>();
        for (FileMng pictMng : pictMngMsgList) {
            FileMngVo fileMngVo= CommonUtil.copyProperties(pictMng,new FileMngVo());
            SysFileLib fileLib = ServiceManager.sysFileLibService.findOne(pictMng.getSysFileLibId());
            fileMngVo.setFileNm(fileLib != null?fileLib.getFileNm():"");
            pictMngVos.add(fileMngVo);
        }
        goodsDocDetailVo.setPictFileVoList(pictMngVos);
        return goodsDocDetailVo;
    }

    @Transactional
    @Override
    public Long updateGoodsDoc(GoodsDocUpdateVo goodsDocUpdateVo) throws ParseException {
        GoodsDoc goodsDoc = findOne(goodsDocUpdateVo.getId());
        if (goodsDoc == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }
        goodsDoc.setGoodsCategoryId(goodsDocUpdateVo.getGoodsCategoryId());
        if (BoolCodeEnum.fromCode(goodsDocUpdateVo.getIsUpdateToWaitApprove()) == BoolCodeEnum.YES) {
            goodsDoc.setApproveStateCode(GoodsApproveStateCodeEnum.WAIT_APPROVE.toCode());
        }
        goodsDoc.setGoodsCode(goodsDocUpdateVo.getGoodsCode());
        goodsDoc.setGoodsNm(goodsDocUpdateVo.getGoodsNm());
        goodsDoc.setBrandNm(goodsDocUpdateVo.getBrandNm());
        goodsDoc.setBarCode(goodsDocUpdateVo.getBarCode());
        goodsDoc.setCommonNm(goodsDocUpdateVo.getCommonNm());
        goodsDoc.setSpec(goodsDocUpdateVo.getSpec());
        goodsDoc.setUnit(goodsDocUpdateVo.getUnit());
        goodsDoc.setToxicologyCode(ToxicologyTypeCodeEnum.fromCode(goodsDocUpdateVo.getToxicologyCode()).toCode());
        goodsDoc.setStorageCondition(StorageConditionTypeCodeEnum.fromCode(goodsDocUpdateVo.getStorageCondition()).toCode());
        goodsDoc.setProduceManufacturer(goodsDocUpdateVo.getProduceManufacturer());
        goodsDoc.setInstructionsStr(goodsDocUpdateVo.getInstructions());
        goodsDoc.setMedicationGuideStr(goodsDocUpdateVo.getMedicationGuide());
        goodsDoc.setPinyin(Pinyin4jUtil.getPinYinHeadChar(goodsDocUpdateVo.getCommonNm()));

        //类型一样,直接更新
        if (GoodsTypeCodeEnum.fromCode(goodsDoc.getGoodsTypeCode()) == GoodsTypeCodeEnum.fromCode(goodsDocUpdateVo.getGoodsTypeCode())) {
            updateGoodsDocType(goodsDocUpdateVo, goodsDoc.getGoodsTypeCode(),goodsDoc.getId());
        } else {
            //先删除旧的类型
            deleteOldGoodsType(goodsDoc.getGoodsTypeCode(),goodsDoc.getId());
            //创建新的商品档案关联表记录并更新
            createNewGoodsDocTypeAndUpdate(goodsDocUpdateVo);
            goodsDoc.setGoodsTypeCode(GoodsTypeCodeEnum.fromCode(goodsDocUpdateVo.getGoodsTypeCode()).toCode());
        }
        //编辑图片
        updatePict(FileObjectTypeCodeEnum.GOODS_DOC_PICT, goodsDoc.getId(), goodsDocUpdateVo.getPictFileList());
        //编辑附件
        updatePict(FileObjectTypeCodeEnum.GOODS_DOC_ATTACHMENT, goodsDoc.getId(), goodsDocUpdateVo.getOtherFileList());
        return save(goodsDoc).getId();
    }

    private void createNewGoodsDocTypeAndUpdate(GoodsDocUpdateVo goodsDocUpdateVo) throws ParseException {
        switch (GoodsTypeCodeEnum.fromCode(goodsDocUpdateVo.getGoodsTypeCode())) {
            case DRUG:
                GoodsDocDrug goodsDocDrug = new GoodsDocDrug();
                goodsDocDrug.setApprovalNumber(goodsDocUpdateVo.getApprovalNumber());
                goodsDocDrug.setApproveDateString(goodsDocUpdateVo.getApproveDateString());
                goodsDocDrug.setApprovalNumberTermString(goodsDocUpdateVo.getApprovalNumberTermString());
                goodsDocDrug.setIsImportGoods(goodsDocUpdateVo.getIsImportGoods());
                goodsDocDrug.setIsChineseMedicineProtect(goodsDocUpdateVo.getIsChineseMedicineProtect());
                goodsDocDrug.setDosageForm(goodsDocUpdateVo.getDosageForm());
                goodsDocDrug.setPrescriptionDrugsTypeCode(goodsDocUpdateVo.getPrescriptionDrugsTypeCode());
                goodsDocDrug.setIsEphedrine(goodsDocUpdateVo.getIsEphedrine());
                goodsDocDrug.setIsKeyCuring(goodsDocUpdateVo.getIsKeyCuring());
                goodsDocDrug.setIsMedicalInsuranceGoods(goodsDocUpdateVo.getIsMedicalInsuranceGoods());
                goodsDocDrug.setMedicalInsuranceNum(goodsDocUpdateVo.getMedicalInsuranceNum());
                goodsDocDrug.setGoodsDocId(goodsDocUpdateVo.getId());
                ServiceManager.goodsDocDrugService.save(goodsDocDrug);
                break;
            case OTHER:
                GoodsDocOther goodsDocOther = new GoodsDocOther();
                goodsDocOther.setApprovalNumber(goodsDocUpdateVo.getApprovalNumber());
                goodsDocOther.setManufacturerAddr(goodsDocUpdateVo.getManufacturerAddr());
                goodsDocOther.setGoodsDocId(goodsDocUpdateVo.getId());
                ServiceManager.goodsDocOtherService.save(goodsDocOther);
                break;
            case CHINESE_MEDICINE_PIECES:
                GoodsDocChineseMedicinePieces goodsChineseMedicinePiecesNew = new GoodsDocChineseMedicinePieces();
                goodsChineseMedicinePiecesNew.setApprovalNumber(goodsDocUpdateVo.getApprovalNumber());
                goodsChineseMedicinePiecesNew.setApproveDateString(goodsDocUpdateVo.getApproveDateString());
                goodsChineseMedicinePiecesNew.setIsImportGoods(goodsDocUpdateVo.getIsImportGoods());
                goodsChineseMedicinePiecesNew.setIsChineseMedicineProtect(goodsDocUpdateVo.getIsChineseMedicineProtect());
                goodsChineseMedicinePiecesNew.setApproveDateString(goodsDocUpdateVo.getApproveDateString());
                goodsChineseMedicinePiecesNew.setDosageForm(goodsDocUpdateVo.getDosageForm());
                goodsChineseMedicinePiecesNew.setPrescriptionDrugsTypeCode(goodsDocUpdateVo.getPrescriptionDrugsTypeCode());
                goodsChineseMedicinePiecesNew.setIsEphedrine(goodsDocUpdateVo.getIsEphedrine());
                goodsChineseMedicinePiecesNew.setIsKeyCuring(goodsDocUpdateVo.getIsKeyCuring());
                goodsChineseMedicinePiecesNew.setIsMedicalInsuranceGoods(goodsDocUpdateVo.getIsMedicalInsuranceGoods());
                goodsChineseMedicinePiecesNew.setMedicalInsuranceNum(goodsDocUpdateVo.getMedicalInsuranceNum());
                goodsChineseMedicinePiecesNew.setProductionPlace(goodsDocUpdateVo.getProductionPlace());
                goodsChineseMedicinePiecesNew.setEffect(goodsDocUpdateVo.getEffect());
                goodsChineseMedicinePiecesNew.setGoodsDocId(goodsDocUpdateVo.getId());
                ServiceManager.goodsDocChineseMedicinePiecesService.save(goodsChineseMedicinePiecesNew);
                break;
            case FOOD_HEALTH:
                GoodsDocFoodHealth goodsFoodHealthNew = new GoodsDocFoodHealth();
                goodsFoodHealthNew.setFoodHygieneLicenceNum(goodsDocUpdateVo.getFoodHygieneLicenceNum());
                goodsFoodHealthNew.setProductionDateString(goodsDocUpdateVo.getProductionDateString());
                goodsFoodHealthNew.setExpirationDateString(goodsDocUpdateVo.getExpirationDateString());
                goodsFoodHealthNew.setHealthCareFunc(goodsDocUpdateVo.getHealthCareFunc());
                goodsFoodHealthNew.setAppropriateCrowd(goodsDocUpdateVo.getAppropriateCrowd());
                goodsFoodHealthNew.setNotAppropriateCrowd(goodsDocUpdateVo.getNotAppropriateCrowd());
                goodsFoodHealthNew.setEdibleMethodAndDosage(goodsDocUpdateVo.getEdibleMethodAndDosage());
                goodsFoodHealthNew.setStorageMethod(goodsDocUpdateVo.getStorageMethod());
                goodsFoodHealthNew.setExecStandard(goodsDocUpdateVo.getExecStandard());
                goodsFoodHealthNew.setEffectComposition(goodsDocUpdateVo.getEffectComposition());
                goodsFoodHealthNew.setNotice(goodsDocUpdateVo.getNotice());
                goodsFoodHealthNew.setGoodsDocId(goodsDocUpdateVo.getId());
                ServiceManager.goodsDocFoodHealthService.save(goodsFoodHealthNew);
                break;
            case DAILY_NECESSITIES:
                GoodsDocDailyNecessities goodsDailyNecessitiesNew = new GoodsDocDailyNecessities();
                goodsDailyNecessitiesNew.setApprovalNumber(goodsDocUpdateVo.getApprovalNumber());
                goodsDailyNecessitiesNew.setManufacturerAddr(goodsDocUpdateVo.getManufacturerAddr());
                goodsDailyNecessitiesNew.setGoodsDocId(goodsDocUpdateVo.getId());
                ServiceManager.goodsDocDailyNecessitiesService.save(goodsDailyNecessitiesNew);
                break;
            case MEDICAL_INSTRUMENTS:
                GoodsDocMedicalInstruments goodsMedicalInstrumentsNew = new GoodsDocMedicalInstruments();
                goodsMedicalInstrumentsNew.setRegRegistrationFormNum(goodsDocUpdateVo.getRegRegistrationFormNum());
                goodsMedicalInstrumentsNew.setRegNum(goodsDocUpdateVo.getRegNum());
                goodsMedicalInstrumentsNew.setManufacturerAddr(goodsDocUpdateVo.getManufacturerAddr());
                goodsMedicalInstrumentsNew.setApplyRange(goodsDocUpdateVo.getApplyRange());
                goodsMedicalInstrumentsNew.setProductStandardNum(goodsDocUpdateVo.getProductStandardNum());
                goodsMedicalInstrumentsNew.setGoodsDocId(goodsDocUpdateVo.getId());
                ServiceManager.goodsDocMedicalInstrumentsService.save(goodsMedicalInstrumentsNew);
                break;
            case COSMETIC:
                GoodsDocCosmetic goodsCosmeticNew = new GoodsDocCosmetic();
                goodsCosmeticNew.setApprovalNumber(goodsDocUpdateVo.getApprovalNumber());
                goodsCosmeticNew.setManufacturerAddr(goodsDocUpdateVo.getManufacturerAddr());
                goodsCosmeticNew.setGoodsDocId(goodsDocUpdateVo.getId());
                ServiceManager.goodsDocCosmeticService.save(goodsCosmeticNew);
                break;
        }
    }

    private void deleteOldGoodsType(String goodsTypeCode,Long goodsDocId) {
        switch (GoodsTypeCodeEnum.fromCode(goodsTypeCode)) {
            case DRUG:
                GoodsDocDrug goodsDocDrug = ServiceManager.goodsDocDrugService.findByGoodsDocId(goodsDocId);
                ServiceManager.goodsDocDrugService.delete(goodsDocDrug.getId());
                break;
            case OTHER:
                GoodsDocOther goodsDocOther = ServiceManager.goodsDocOtherService.findByGoodsDocId(goodsDocId);
                ServiceManager.goodsDocOtherService.delete(goodsDocOther.getId());
                break;
            case CHINESE_MEDICINE_PIECES:
                GoodsDocChineseMedicinePieces goodsDocChineseMedicinePieces = ServiceManager.goodsDocChineseMedicinePiecesService.findByGoodsDocId(goodsDocId);
                ServiceManager.goodsDocChineseMedicinePiecesService.delete(goodsDocChineseMedicinePieces.getId());
                break;
            case FOOD_HEALTH:
                GoodsDocFoodHealth goodsDocFoodHealth = ServiceManager.goodsDocFoodHealthService.findByGoodsDocId(goodsDocId);
                ServiceManager.goodsDocFoodHealthService.delete(goodsDocFoodHealth.getId());
                break;
            case DAILY_NECESSITIES:
                GoodsDocDailyNecessities goodsDocDailyNecessities = ServiceManager.goodsDocDailyNecessitiesService.findByGoodsDocId(goodsDocId);
                ServiceManager.goodsDocDailyNecessitiesService.delete(goodsDocDailyNecessities.getId());
                break;
            case MEDICAL_INSTRUMENTS:
                GoodsDocMedicalInstruments goodsDocMedicalInstruments = ServiceManager.goodsDocMedicalInstrumentsService.findByGoodsDocId(goodsDocId);
                ServiceManager.goodsDocMedicalInstrumentsService.delete(goodsDocMedicalInstruments.getId());
                break;
            case COSMETIC:
                GoodsDocCosmetic goodsDocCosmetic = ServiceManager.goodsDocCosmeticService.findByGoodsDocId(goodsDocId);
                ServiceManager.goodsDocCosmeticService.delete(goodsDocCosmetic.getId());
                break;
        }
    }

    private void updateGoodsDocType(GoodsDocUpdateVo goodsDocUpdateVo, String goodsTypeCode, Long goodsDocId) throws ParseException {
        switch (GoodsTypeCodeEnum.fromCode(goodsTypeCode)) {
            case DRUG:
                GoodsDocDrug goodsDocDrug = ServiceManager.goodsDocDrugService.findByGoodsDocId(goodsDocId);
                goodsDocDrug.setApprovalNumber(goodsDocUpdateVo.getApprovalNumber());
                goodsDocDrug.setApproveDateString(goodsDocUpdateVo.getApproveDateString());
                goodsDocDrug.setApprovalNumberTermString(goodsDocUpdateVo.getApprovalNumberTermString());
                goodsDocDrug.setIsImportGoods(goodsDocUpdateVo.getIsImportGoods());
                goodsDocDrug.setIsChineseMedicineProtect(goodsDocUpdateVo.getIsChineseMedicineProtect());
                goodsDocDrug.setDosageForm(goodsDocUpdateVo.getDosageForm());
                goodsDocDrug.setPrescriptionDrugsTypeCode(goodsDocUpdateVo.getPrescriptionDrugsTypeCode());
                goodsDocDrug.setIsEphedrine(goodsDocUpdateVo.getIsEphedrine());
                goodsDocDrug.setIsKeyCuring(goodsDocUpdateVo.getIsKeyCuring());
                goodsDocDrug.setIsMedicalInsuranceGoods(goodsDocUpdateVo.getIsMedicalInsuranceGoods());
                goodsDocDrug.setMedicalInsuranceNum(BoolCodeEnum.fromCode(goodsDocUpdateVo.getIsMedicalInsuranceGoods())==BoolCodeEnum.YES?goodsDocUpdateVo.getMedicalInsuranceNum():"");
                ServiceManager.goodsDocDrugService.save(goodsDocDrug);
                break;
            case OTHER:
                GoodsDocOther goodsDocOther = ServiceManager.goodsDocOtherService.findByGoodsDocId(goodsDocId);
                goodsDocOther.setApprovalNumber(goodsDocUpdateVo.getApprovalNumber());
                goodsDocOther.setManufacturerAddr(goodsDocUpdateVo.getManufacturerAddr());
                ServiceManager.goodsDocOtherService.save(goodsDocOther);
                break;
            case CHINESE_MEDICINE_PIECES:
                GoodsDocChineseMedicinePieces goodsDocChineseMedicinePieces = ServiceManager.goodsDocChineseMedicinePiecesService.findByGoodsDocId(goodsDocId);
                goodsDocChineseMedicinePieces.setApprovalNumber(goodsDocUpdateVo.getApprovalNumber());
                goodsDocChineseMedicinePieces.setApproveDateString(goodsDocUpdateVo.getApproveDateString());
                goodsDocChineseMedicinePieces.setIsImportGoods(goodsDocUpdateVo.getIsImportGoods());
                goodsDocChineseMedicinePieces.setIsChineseMedicineProtect(goodsDocUpdateVo.getIsChineseMedicineProtect());
                goodsDocChineseMedicinePieces.setApprovalNumberTermString(goodsDocUpdateVo.getApprovalNumberTermString());
                goodsDocChineseMedicinePieces.setDosageForm(goodsDocUpdateVo.getDosageForm());
                goodsDocChineseMedicinePieces.setPrescriptionDrugsTypeCode(goodsDocUpdateVo.getPrescriptionDrugsTypeCode());
                goodsDocChineseMedicinePieces.setIsEphedrine(goodsDocUpdateVo.getIsEphedrine());
                goodsDocChineseMedicinePieces.setIsKeyCuring(goodsDocUpdateVo.getIsKeyCuring());
                goodsDocChineseMedicinePieces.setIsMedicalInsuranceGoods(goodsDocUpdateVo.getIsMedicalInsuranceGoods());
                goodsDocChineseMedicinePieces.setMedicalInsuranceNum(BoolCodeEnum.fromCode(goodsDocUpdateVo.getIsMedicalInsuranceGoods())==BoolCodeEnum.YES?goodsDocUpdateVo.getMedicalInsuranceNum():"");
                goodsDocChineseMedicinePieces.setProductionPlace(goodsDocUpdateVo.getProductionPlace());
                goodsDocChineseMedicinePieces.setEffect(goodsDocUpdateVo.getEffect());
                ServiceManager.goodsDocChineseMedicinePiecesService.save(goodsDocChineseMedicinePieces);
                break;
            case FOOD_HEALTH:
                GoodsDocFoodHealth goodsDocFoodHealth = ServiceManager.goodsDocFoodHealthService.findByGoodsDocId(goodsDocId);
                goodsDocFoodHealth.setFoodHygieneLicenceNum(goodsDocUpdateVo.getFoodHygieneLicenceNum());
                goodsDocFoodHealth.setProductionDateString(goodsDocUpdateVo.getProductionDateString());
                goodsDocFoodHealth.setExpirationDateString(goodsDocUpdateVo.getExpirationDateString());
                goodsDocFoodHealth.setHealthCareFunc(goodsDocUpdateVo.getHealthCareFunc());
                goodsDocFoodHealth.setAppropriateCrowd(goodsDocUpdateVo.getAppropriateCrowd());
                goodsDocFoodHealth.setNotAppropriateCrowd(goodsDocUpdateVo.getNotAppropriateCrowd());
                goodsDocFoodHealth.setEdibleMethodAndDosage(goodsDocUpdateVo.getEdibleMethodAndDosage());
                goodsDocFoodHealth.setStorageMethod(goodsDocUpdateVo.getStorageMethod());
                goodsDocFoodHealth.setExecStandard(goodsDocUpdateVo.getExecStandard());
                goodsDocFoodHealth.setEffectComposition(goodsDocUpdateVo.getEffectComposition());
                goodsDocFoodHealth.setNotice(goodsDocUpdateVo.getNotice());
                ServiceManager.goodsDocFoodHealthService.save(goodsDocFoodHealth);
                break;
            case DAILY_NECESSITIES:
                GoodsDocDailyNecessities goodsDocDailyNecessities = ServiceManager.goodsDocDailyNecessitiesService.findByGoodsDocId(goodsDocId);
                goodsDocDailyNecessities.setApprovalNumber(goodsDocUpdateVo.getApprovalNumber());
                goodsDocDailyNecessities.setManufacturerAddr(goodsDocUpdateVo.getManufacturerAddr());
                ServiceManager.goodsDocDailyNecessitiesService.save(goodsDocDailyNecessities);
                break;
            case MEDICAL_INSTRUMENTS:
                GoodsDocMedicalInstruments goodsDocMedicalInstruments = ServiceManager.goodsDocMedicalInstrumentsService.findByGoodsDocId(goodsDocId);
                goodsDocMedicalInstruments.setRegNum(goodsDocUpdateVo.getRegNum());
                goodsDocMedicalInstruments.setRegRegistrationFormNum(goodsDocUpdateVo.getRegRegistrationFormNum());
                goodsDocMedicalInstruments.setManufacturerAddr(goodsDocUpdateVo.getManufacturerAddr());
                goodsDocMedicalInstruments.setApplyRange(goodsDocUpdateVo.getApplyRange());
                goodsDocMedicalInstruments.setProductStandardNum(goodsDocUpdateVo.getProductStandardNum());
                ServiceManager.goodsDocMedicalInstrumentsService.save(goodsDocMedicalInstruments);
                break;
            case COSMETIC:
                GoodsDocCosmetic goodsDocCosmetic = ServiceManager.goodsDocCosmeticService.findByGoodsDocId(goodsDocId);
                goodsDocCosmetic.setApprovalNumber(goodsDocUpdateVo.getApprovalNumber());
                goodsDocCosmetic.setManufacturerAddr(goodsDocUpdateVo.getManufacturerAddr());
                ServiceManager.goodsDocCosmeticService.save(goodsDocCosmetic);
                break;
        }
    }

    @Override
    public Boolean findGoodsCodeExist(String goodsCode, Long id) {
        GoodsDoc goodsDoc = getGoodsDocRepository().findByGoodsCodeAndIsDelete(goodsCode,BoolCodeEnum.NO.toCode());
        return (goodsDoc == null && id == null) || goodsDoc == null || goodsDoc.getId().equals(id);
    }

    @Override
    public GoodsDoc findByGoodsCode(String goodsCode) {
        return getGoodsDocRepository().findByGoodsCodeAndIsDelete(goodsCode,BoolCodeEnum.NO.toCode());
    }

    @Transactional
    @Override
    public Boolean updateDenyApprove(Long id) {
        GoodsDoc goodsDoc = findOne(id);
        if (goodsDoc == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }
        goodsDoc.setApproveStateCode(GoodsApproveStateCodeEnum.DENY_APPROVE.toCode());
        save(goodsDoc);
        return true;
    }

    @Transactional
    @Override
    public Boolean updatePassApprove(Long id) {
        GoodsDoc goodsDoc = findOne(id);
        if (goodsDoc == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }
        goodsDoc.setApproveStateCode(GoodsApproveStateCodeEnum.PASS_APPROVE.toCode());
        save(goodsDoc);
        return true;
    }

    @Transactional
    @Override
    public Boolean updateDelete(Long id) {
        GoodsDoc goodsDoc = findOne(id);
        if (goodsDoc == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }
        goodsDoc.setIsDelete(BoolCodeEnum.YES.toCode());
        save(goodsDoc);
        return true;
    }

    @Override
    public Boolean exportExcel(String webRealPath, String xlsTemplatePath, String xlsOutputPath, Pageable pageable, GoodsDocListSearchParam goodsDocListSearchParam) {

        List<GoodsDocExcelVo> drugResultList = new ArrayList<>();
        List<GoodsDocExcelVo> otherResultList = new ArrayList<>();
        List<GoodsDocExcelVo> cmpResultList = new ArrayList<>();
        List<GoodsDocExcelVo> healthResultList = new ArrayList<>();
        List<GoodsDocExcelVo> dailyResultList = new ArrayList<>();
        List<GoodsDocExcelVo> miResultList = new ArrayList<>();
        List<GoodsDocExcelVo> cosmeticResultList = new ArrayList<>();

        Page<GoodsDoc> goodsDocPage = getGoodsDocRepository().query(pageable, goodsDocListSearchParam);
        List<GoodsDoc> resultList = goodsDocPage.getContent();
        for(GoodsDoc goodsDoc : resultList){
            GoodsDocExcelVo docExportVo = buildGoodsDocExcelVo(goodsDoc);
            switch (GoodsTypeCodeEnum.fromCode(docExportVo.getGoodsTypeCode())) {
                case DRUG:
                    setGoodsDocDrugExportInfo(docExportVo);
                    drugResultList.add(docExportVo);
                    break;
                case OTHER:
                    setGoodsDocOtherExportInfo(docExportVo);
                    otherResultList.add(docExportVo);
                    break;
                case CHINESE_MEDICINE_PIECES:
                    setGoodsDocChineseMedicinePiecesExportInfo(docExportVo);
                    cmpResultList.add(docExportVo);
                    break;
                case FOOD_HEALTH:
                    setGoodsDocFoodHealthExportInfo(docExportVo);
                    healthResultList.add(docExportVo);
                    break;
                case DAILY_NECESSITIES:
                    setGoodsDocDailyNecessitiesExportInfo(docExportVo);
                    dailyResultList.add(docExportVo);
                    break;
                case MEDICAL_INSTRUMENTS:
                    setGoodsDocMedicalInstrumentsExportInfo(docExportVo);
                    miResultList.add(docExportVo);
                    break;
                case COSMETIC:
                    setGoodsDocCosmeticExportInfo(docExportVo);
                    cosmeticResultList.add(docExportVo);
                    break;
                default:
                    break;
            }

        }

        String fileMngTempPath = webRealPath + "xlsoutput/Goods/" + getUUIDPath();
        File file = new File(fileMngTempPath);
        file.mkdir();
        try {
            if(!drugResultList.isEmpty()) {
                String xlsOutputExcelPath = fileMngTempPath + "/drug.xls";
                ListExcelWriter listExcelWriter = new ListExcelWriter(xlsTemplatePath + "/GoodsDocDrug.xls");
                listExcelWriter.fillToFile(drugResultList, xlsOutputExcelPath);
            }
            if(!otherResultList.isEmpty()) {
                String xlsOutputExcelPath = fileMngTempPath + "/other.xls";
                ListExcelWriter listExcelWriter = new ListExcelWriter(xlsTemplatePath + "/GoodsDocOther.xls");
                listExcelWriter.fillToFile(otherResultList, xlsOutputExcelPath);
            }
            if(!cmpResultList.isEmpty()) {
                String xlsOutputExcelPath = fileMngTempPath + "/cmp.xls";
                ListExcelWriter listExcelWriter = new ListExcelWriter(xlsTemplatePath + "/GoodsDocChineseMedicinePieces.xls");
                listExcelWriter.fillToFile(cmpResultList, xlsOutputExcelPath);
            }
            if(!healthResultList.isEmpty()) {
                String xlsOutputExcelPath = fileMngTempPath + "/health.xls";
                ListExcelWriter listExcelWriter = new ListExcelWriter(xlsTemplatePath + "/GoodsDocFoodHealth.xls");
                listExcelWriter.fillToFile(healthResultList, xlsOutputExcelPath);
            }
            if(!dailyResultList.isEmpty()) {
                String xlsOutputExcelPath = fileMngTempPath + "/daily.xls";
                ListExcelWriter listExcelWriter = new ListExcelWriter(xlsTemplatePath + "/GoodsDocDailyNecessities.xls");
                listExcelWriter.fillToFile(dailyResultList, xlsOutputExcelPath);
            }
            if(!miResultList.isEmpty()) {
                String xlsOutputExcelPath = fileMngTempPath + "/instruments.xls";
                ListExcelWriter listExcelWriter = new ListExcelWriter(xlsTemplatePath + "/GoodsDocMedicalInstruments.xls");
                listExcelWriter.fillToFile(miResultList, xlsOutputExcelPath);
            }
            if(!cosmeticResultList.isEmpty()) {
                String xlsOutputExcelPath = fileMngTempPath + "/cosmetic.xls";
                ListExcelWriter listExcelWriter = new ListExcelWriter(xlsTemplatePath + "/GoodsDocCosmetic.xls");
                listExcelWriter.fillToFile(cosmeticResultList, xlsOutputExcelPath);
            }
            zipFiles(fileMngTempPath, xlsOutputPath);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private GoodsDocExcelVo buildGoodsDocExcelVo(GoodsDoc goodsDoc) {
        GoodsDocExcelVo docExportVo = CommonUtil.copyProperties(goodsDoc, new GoodsDocExcelVo());
        if(goodsDoc.getGoodsCategoryId()!=null){
            GoodsCategory goodsCategory = ServiceManager.goodsCategoryService.findOne(goodsDoc.getGoodsCategoryId());
            if(goodsCategory!=null){
                docExportVo.setGoodsCategoryName(goodsCategory.getCategoryName());
            }
        }
        if(StringUtils.isNotBlank(goodsDoc.getGoodsTypeCode())){
            docExportVo.setGoodsTypeName(GoodsTypeCodeEnum.fromCode(goodsDoc.getGoodsTypeCode()).toName());
        }
        if(StringUtils.isNotBlank(goodsDoc.getToxicologyCode())){
            docExportVo.setToxicologyName(ToxicologyTypeCodeEnum.fromCode(goodsDoc.getToxicologyCode()).toName());
        }
        if(StringUtils.isNotBlank(goodsDoc.getStorageCondition())){
            docExportVo.setStorageConditionName(StorageConditionTypeCodeEnum.fromCode(goodsDoc.getStorageCondition()).toName());
        }
        if(StringUtils.isNotBlank(goodsDoc.getApproveStateCode())){
            docExportVo.setApproveStateName(GoodsApproveStateCodeEnum.fromCode(goodsDoc.getApproveStateCode()).toName());
        }
        return docExportVo;
    }

    private void setGoodsDocDrugExportInfo(GoodsDocExcelVo docExportVo) {
        GoodsDocDrug goodsDrug = ServiceManager.goodsDocDrugService.findByGoodsDocId(docExportVo.getId());

        if(goodsDrug == null) {
            return;
        }
        docExportVo.setApprovalNumber(goodsDrug.getApprovalNumber());
        docExportVo.setApprovalNumberTerm(goodsDrug.getApprovalNumberTermString());
        docExportVo.setIsImportGoodsName(BoolCodeEnum.fromCode(goodsDrug.getIsImportGoods()).toName());
        docExportVo.setIsChineseMedicineProtectName(BoolCodeEnum.fromCode(goodsDrug.getIsImportGoods()).toName());
        docExportVo.setApproveDate(goodsDrug.getApproveDateString());
        SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsDrug.getDosageForm());
        if(sysDictItem != null) {
            docExportVo.setDosageForm(sysDictItem.getDictItemNm());
        }
        docExportVo.setPrescriptionDrugsTypeCode(PrescriptionDrugsTypeCodeEnum.fromCode(goodsDrug.getPrescriptionDrugsTypeCode()).toName());
        docExportVo.setIsEphedrineName(BoolCodeEnum.fromCode(goodsDrug.getIsEphedrine()).toName());
        docExportVo.setIsKeyCuringName(BoolCodeEnum.fromCode(goodsDrug.getIsKeyCuring()).toName());
        docExportVo.setIsMedicalInsuranceGoodsName(BoolCodeEnum.fromCode(goodsDrug.getIsMedicalInsuranceGoods()).toName());
        if(StringUtils.isNotBlank(goodsDrug.getMedicalInsuranceNum())) {
            docExportVo.setMedicalInsuranceNum(goodsDrug.getMedicalInsuranceNum());
        }
    }

    private void setGoodsDocOtherExportInfo(GoodsDocExcelVo docExportVo) {
        GoodsDocOther goodsOther = ServiceManager.goodsDocOtherService.findByGoodsDocId(docExportVo.getId());
        if(goodsOther == null) {
            return;
        }

        docExportVo.setApprovalNumber(goodsOther.getApprovalNumber());
        docExportVo.setManufacturerAddr(goodsOther.getManufacturerAddr());
    }

    private void setGoodsDocChineseMedicinePiecesExportInfo(GoodsDocExcelVo docExportVo) {
        GoodsDocChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsDocChineseMedicinePiecesService.findByGoodsDocId(docExportVo.getId());
        if(goodsChineseMedicinePieces == null) {
            return;
        }
        docExportVo.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
        docExportVo.setApprovalNumberTerm(goodsChineseMedicinePieces.getApprovalNumberTermString());
        docExportVo.setIsImportGoodsName(BoolCodeEnum.fromCode(goodsChineseMedicinePieces.getIsImportGoods()).toName());
        docExportVo.setIsChineseMedicineProtectName(BoolCodeEnum.fromCode(goodsChineseMedicinePieces.getIsImportGoods()).toName());
        docExportVo.setApproveDate(goodsChineseMedicinePieces.getApproveDateString());
        SysDictItem sysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
        if(sysDictItem != null) {
            docExportVo.setDosageForm(sysDictItem.getDictItemNm());
        }
        docExportVo.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
        docExportVo.setEffect(goodsChineseMedicinePieces.getEffect());
        docExportVo.setPrescriptionDrugsTypeCode(PrescriptionDrugsTypeCodeEnum.fromCode(goodsChineseMedicinePieces.getPrescriptionDrugsTypeCode()).toName());
        docExportVo.setIsEphedrineName(BoolCodeEnum.fromCode(goodsChineseMedicinePieces.getIsEphedrine()).toName());
        docExportVo.setIsKeyCuringName(BoolCodeEnum.fromCode(goodsChineseMedicinePieces.getIsKeyCuring()).toName());
        docExportVo.setIsMedicalInsuranceGoodsName(BoolCodeEnum.fromCode(goodsChineseMedicinePieces.getIsMedicalInsuranceGoods()).toName());
        docExportVo.setMedicalInsuranceNum(goodsChineseMedicinePieces.getMedicalInsuranceNum());
    }

    private void setGoodsDocFoodHealthExportInfo(GoodsDocExcelVo docExportVo) {
        GoodsDocFoodHealth goodsFoodHealth = ServiceManager.goodsDocFoodHealthService.findByGoodsDocId(docExportVo.getId());
        if(goodsFoodHealth == null) {
            return;
        }

        docExportVo.setFoodHygieneLicenceNum(goodsFoodHealth.getFoodHygieneLicenceNum());
        docExportVo.setProductionDate(goodsFoodHealth.getProductionDateString());
        docExportVo.setExpirationDate(goodsFoodHealth.getExpirationDateString());
        docExportVo.setHealthCareFunc(goodsFoodHealth.getHealthCareFunc());
        docExportVo.setAppropriateCrowd(goodsFoodHealth.getAppropriateCrowd());
        docExportVo.setEdibleMethodAndDosage(goodsFoodHealth.getEdibleMethodAndDosage());
        docExportVo.setStorageMethod(goodsFoodHealth.getStorageMethod());
        docExportVo.setExecStandard(goodsFoodHealth.getExecStandard());
        docExportVo.setEffectComposition(goodsFoodHealth.getEffectComposition());
        docExportVo.setNotice(goodsFoodHealth.getNotice());
        docExportVo.setNotAppropriateCrowd(goodsFoodHealth.getNotAppropriateCrowd());
    }

    private void setGoodsDocDailyNecessitiesExportInfo(GoodsDocExcelVo docExportVo) {
        GoodsDocDailyNecessities goodsDailyNecessities = ServiceManager.goodsDocDailyNecessitiesService.findByGoodsDocId(docExportVo.getId());
        if(goodsDailyNecessities == null) {
            return;
        }
        docExportVo.setApprovalNumber(goodsDailyNecessities.getApprovalNumber());
        docExportVo.setManufacturerAddr(goodsDailyNecessities.getManufacturerAddr());

    }

    private void setGoodsDocMedicalInstrumentsExportInfo(GoodsDocExcelVo docExportVo) {
        GoodsDocMedicalInstruments medicalInstruments = ServiceManager.goodsDocMedicalInstrumentsService.findByGoodsDocId(docExportVo.getId());
        if(medicalInstruments == null) {
            return;
        }

        docExportVo.setRegNum(medicalInstruments.getRegNum());
        docExportVo.setRegRegistrationFormNum(medicalInstruments.getRegRegistrationFormNum());
        docExportVo.setManufacturerAddr(medicalInstruments.getManufacturerAddr());
        docExportVo.setApplyRange(medicalInstruments.getApplyRange());
        docExportVo.setProductStandardNum(medicalInstruments.getProductStandardNum());
    }

    private void setGoodsDocCosmeticExportInfo(GoodsDocExcelVo docExportVo) {
        GoodsDocCosmetic cosmetic = ServiceManager.goodsDocCosmeticService.findByGoodsDocId(docExportVo.getId());
        if(cosmetic == null) {
            return;
        }
        docExportVo.setApprovalNumber(cosmetic.getApprovalNumber());
        docExportVo.setManufacturerAddr(cosmetic.getManufacturerAddr());
    }

    /**
     * 压缩文件
     * @param zipFile 需要压缩的文件
     * @param zipFilePath 压缩文件保存路径
     *
     */
    private void zipFiles(String zipFile, String zipFilePath){
        SevenZipUtils.zipFiles(new String[]{zipFile}, zipFilePath, new SevenZipUtils.ZipInterceptor(){

            @Override
            //过滤压缩文件return false
            public boolean beforeEachZip(File file) {
                return true;
            }

            @Override
            public void afterEachZip(SevenZArchiveEntry zipEntry) {
            }
        });
    }
    @Autowired
    private JpaTransactionManager jpaTransactionManager;

    @Transactional(propagation = Propagation.NOT_SUPPORTED) //以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
    public List<ErrorLog> importData(String localFileId) {
        List<SysDictItem> sysDictItemList = ServiceManager.sysDictItemService.findByDataDictId(Global.DICT_DOSAGE_FORM_ID);
        final Map<String, String> sysDictItemMap = new HashedMap();
        for(SysDictItem sysDictItem : sysDictItemList) {
            sysDictItemMap.put(sysDictItem.getDictItemNm(), sysDictItem.getDictItemCode());
        }

        BaseExcelReader<GoodsDocExcelVo> excelReader = new BaseExcelReader<GoodsDocExcelVo>() {

            @Override
            protected Map<String, Object> convert(Map<String, Object> valueMap) {

                return valueMap;
            }

            @Override
            public boolean verifyExt(int lineNumber, Collection<String> protocolList, Map<String, Object> valueMap, List<ErrorLog> errorLogList) {
                String goodsCategoryName = (String) valueMap.get("goodsCategoryName");
                if(goodsCategoryName!=null){
                    List<GoodsCategory> list = ServiceManager.goodsCategoryService.findByCategoryName(goodsCategoryName);
                    if(list.isEmpty()){
                        errorLogList.add(new ErrorLog(lineNumber, "商品分类[" + goodsCategoryName + "]不存在..."));
                        return false;
                    } else {
                        valueMap.put(GoodsDoc.GOODS_CATEGORY_ID, list.get(0).getId());
                    }
                }

                String goodsTypeName = (String) valueMap.get("goodsTypeName");
                if(goodsTypeName!=null){
                    boolean isFalse = true;
                    for (GoodsTypeCodeEnum codeEnum : GoodsTypeCodeEnum.values()) {
                        if (codeEnum.toName().equals(goodsTypeName)) {
                            valueMap.put(GoodsDoc.GOODS_TYPE_CODE, codeEnum.toCode());
                            isFalse = false;
                        }
                    }
                    if(isFalse) {
                        errorLogList.add(new ErrorLog(lineNumber, "商品类型[" + goodsTypeName + "]不存在..."));
                        return false;
                    }
                }

                String toxicologyName = (String) valueMap.get("toxicologyName");
                if(toxicologyName!=null){
                    boolean isFalse = true;
                    for (ToxicologyTypeCodeEnum codeEnum : ToxicologyTypeCodeEnum.values()) {
                        if (codeEnum.toName().equals(toxicologyName)) {
                            valueMap.put(GoodsDoc.TOXICOLOGY_CODE, codeEnum.toCode());
                            isFalse = false;
                        }
                    }
                    if(isFalse) {
                        errorLogList.add(new ErrorLog(lineNumber, "毒理[" + toxicologyName + "]不存在..."));
                        return false;
                    }
                }

                String storageConditionName = (String) valueMap.get("storageConditionName");
                if(storageConditionName!=null){
                    boolean isFalse = true;
                    for (StorageConditionTypeCodeEnum codeEnum : StorageConditionTypeCodeEnum.values()) {
                        if (codeEnum.toName().equals(storageConditionName)) {
                            valueMap.put(GoodsDoc.STORAGE_CONDITION, codeEnum.toCode());
                            isFalse = false;
                        }
                    }
                    if(isFalse) {
                        errorLogList.add(new ErrorLog(lineNumber, "储存条件[" + storageConditionName + "]不存在..."));
                        return false;
                    }
                }

                String isImportGoodsName = (String) valueMap.get("isImportGoodsName");
                if(isImportGoodsName!=null){
                    boolean isFalse = true;
                    for (BoolCodeEnum codeEnum : BoolCodeEnum.values()) {
                        if (codeEnum.toName().equals(isImportGoodsName)) {
                            valueMap.put("isImportGoodsName", codeEnum.toCode());
                            isFalse = false;
                        }
                    }
                    if(isFalse) {
                        errorLogList.add(new ErrorLog(lineNumber, "是否进口药品[" + isImportGoodsName + "]不存在..."));
                        return false;
                    }
                }

                String isChineseMedicineProtectName = (String) valueMap.get("isChineseMedicineProtectName");
                if(isChineseMedicineProtectName!=null){
                    boolean isFalse = true;
                    for (BoolCodeEnum codeEnum : BoolCodeEnum.values()) {
                        if (codeEnum.toName().equals(isChineseMedicineProtectName)) {
                            valueMap.put("isChineseMedicineProtectName", codeEnum.toCode());
                            isFalse = false;
                        }
                    }
                    if(isFalse) {
                        errorLogList.add(new ErrorLog(lineNumber, "是否中药保护[" + isChineseMedicineProtectName + "]不存在..."));
                        return false;
                    }
                }


                String dosageForm = (String) valueMap.get("dosageForm");
                if(StringUtils.isNotBlank(dosageForm)) {
                    String dictItemCode = sysDictItemMap.get(dosageForm.trim());
                    if (StringUtils.isBlank(dictItemCode)) {
                        errorLogList.add(new ErrorLog(lineNumber, "剂型[" + dosageForm + "]不存在..."));
                        return false;
                    } else {
                        valueMap.put("dosageForm", dictItemCode);
                    }
                }

                String prescriptionDrugsTypeCode = (String) valueMap.get("prescriptionDrugsTypeCode");
                if(prescriptionDrugsTypeCode!=null){
                    boolean isFalse = true;
                    for (PrescriptionDrugsTypeCodeEnum codeEnum : PrescriptionDrugsTypeCodeEnum.values()) {
                        if (codeEnum.toName().equals(prescriptionDrugsTypeCode)) {
                            valueMap.put("prescriptionDrugsTypeCode", codeEnum.toCode());
                            isFalse = false;
                            break;
                        }
                    }
                    if(isFalse) {
                        errorLogList.add(new ErrorLog(lineNumber, "处方药类型[" + prescriptionDrugsTypeCode + "]不存在..."));
                        return false;
                    }
                }

                String isEphedrineName = (String) valueMap.get("isEphedrineName");
                if(isEphedrineName!=null){
                    boolean isFalse = true;
                    for (BoolCodeEnum codeEnum : BoolCodeEnum.values()) {
                        if (codeEnum.toName().equals(isEphedrineName)) {
                            valueMap.put("isEphedrineName", codeEnum.toCode());
                            isFalse = false;
                        }
                    }
                    if(isFalse) {
                        errorLogList.add(new ErrorLog(lineNumber, "是否麻黄碱[" + isEphedrineName + "]不存在..."));
                        return false;
                    }

                }

                String isKeyCuringName = (String) valueMap.get("isKeyCuringName");
                if(isKeyCuringName!=null){
                    boolean isFalse = true;
                    for (BoolCodeEnum codeEnum : BoolCodeEnum.values()) {
                        if (codeEnum.toName().equals(isKeyCuringName)) {
                            valueMap.put("isKeyCuringName", codeEnum.toCode());
                            isFalse = false;
                        }
                    }
                    if(isFalse) {
                        errorLogList.add(new ErrorLog(lineNumber, "是否重点养护[" + isKeyCuringName + "]不存在..."));
                        return false;
                    }

                }

                String isMedicalInsuranceGoodsName = (String) valueMap.get("isMedicalInsuranceGoodsName");
                if(isMedicalInsuranceGoodsName!=null){
                    boolean isFalse = true;
                    for (BoolCodeEnum codeEnum : BoolCodeEnum.values()) {
                        if (codeEnum.toName().equals(isMedicalInsuranceGoodsName)) {
                            valueMap.put("isMedicalInsuranceGoodsName", codeEnum.toCode());
                            isFalse = false;
                        }
                    }
                    if(isFalse) {
                        errorLogList.add(new ErrorLog(lineNumber, "是否医保商品[" + isMedicalInsuranceGoodsName + "]不存在..."));
                        return false;
                    }
                }

                return true;
            }

            @Override
            protected Class getBeanClass() {
                return GoodsDocExcelVo.class;
            }
        };

        List<ErrorLog> errorLogs = new ArrayList<>();
        List<GoodsDocExcelVo> inputList = excelReader.importData(localFileId, errorLogs);
        int index = 1;
        for (GoodsDocExcelVo bean : inputList) {
            DefaultTransactionDefinition td = new DefaultTransactionDefinition();
            td.setName("Process Import GoodsDoc" + index);
            index++;
            //使用底层数据库默认隔离级别。
            td.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
            //支持现有的事务，如果没有则新建一个事务。
            td.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
            TransactionStatus txs = jpaTransactionManager.getTransaction(td);

            try {
                //提交事务
                GoodsDoc dbGoodsDoc = getGoodsDocRepository().findByGoodsCodeAndIsDelete(bean.getGoodsCode(),BoolCodeEnum.NO.toCode()); //todo:目前只支持编辑，不支持新增

                if(dbGoodsDoc == null) {
                    dbGoodsDoc = new GoodsDoc();
                }

                dbGoodsDoc.setGoodsCode(bean.getGoodsCode());
                dbGoodsDoc.setGoodsNm(bean.getGoodsNm());
                dbGoodsDoc.setBrandNm(bean.getBrandNm());
                dbGoodsDoc.setGoodsCategoryId(bean.getGoodsCategoryId());
                dbGoodsDoc.setBarCode(bean.getBarCode());
                dbGoodsDoc.setGoodsTypeCode(bean.getGoodsTypeCode());
                dbGoodsDoc.setCommonNm(bean.getCommonNm());
                dbGoodsDoc.setSpec(bean.getSpec());
                dbGoodsDoc.setUnit(bean.getUnit());
                dbGoodsDoc.setToxicologyCode(bean.getToxicologyCode());
                dbGoodsDoc.setStorageCondition(bean.getStorageCondition());
                dbGoodsDoc.setProduceManufacturer(bean.getProduceManufacturer());
                dbGoodsDoc.setIsDelete(BoolCodeEnum.NO.toCode());
                dbGoodsDoc.setDisplayPosition(1L);
                dbGoodsDoc.setApproveStateCode(GoodsApproveStateCodeEnum.PASS_APPROVE.toCode());
                save(dbGoodsDoc);

                switch (GoodsTypeCodeEnum.fromCode(bean.getGoodsTypeCode())) {
                    case DRUG:
                        setGoodsDocDrugImportInfo(dbGoodsDoc.getId(), bean);
                        break;
                    case OTHER:
                        setGoodsDocOtherImportInfo(dbGoodsDoc.getId(), bean);
                        break;
                    case CHINESE_MEDICINE_PIECES:
                        setGoodsDocChineseMedicinePiecesImportInfo(dbGoodsDoc.getId(), bean);
                        break;
                    case FOOD_HEALTH:
                        setGoodsDocFoodHealthImportInfo(dbGoodsDoc.getId(), bean);
                        break;
                    case DAILY_NECESSITIES:
                        setGoodsDocDailyNecessitiesImportInfo(dbGoodsDoc.getId(), bean);
                        break;
                    case MEDICAL_INSTRUMENTS:
                        setGoodsDocMedicalInstrumentsImportInfo(dbGoodsDoc.getId(), bean);
                        break;
                    case COSMETIC:
                        setGoodsDocCosmeticImportInfo(dbGoodsDoc.getId(), bean);
                        break;
                    default:
                        break;
                }

                jpaTransactionManager.commit(txs);
            } catch(DataIntegrityViolationException dive) {
                if (!txs.isCompleted()) {
                    //回滚事务
                    jpaTransactionManager.rollback(txs);
                }
                throw new BusinessException(ResGlobal.COMMON_ERROR, dive);
            }
        }
        return errorLogs;
    }

    @Transactional
    private void setGoodsDocDrugImportInfo(Long goodsDocId, GoodsDocExcelVo docExportVo) {
        GoodsDocDrug goodsDrug = ServiceManager.goodsDocDrugService.findByGoodsDocId(goodsDocId);

        if(goodsDrug == null) {
            goodsDrug = new GoodsDocDrug();
        }
        goodsDrug.setApprovalNumber(docExportVo.getApprovalNumber());
        try {
            if(StringUtils.isNotBlank(docExportVo.getApprovalNumberTerm())) {
                goodsDrug.setApprovalNumberTermString(docExportVo.getApprovalNumberTerm());
            }
            if(StringUtils.isNotBlank(docExportVo.getApproveDate())) {
                goodsDrug.setApproveDateString(docExportVo.getApproveDate());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        goodsDrug.setIsImportGoods(docExportVo.getIsImportGoodsName());
        goodsDrug.setIsChineseMedicineProtect(docExportVo.getIsImportGoodsName());
        goodsDrug.setDosageForm(docExportVo.getDosageForm());
        goodsDrug.setPrescriptionDrugsTypeCode(docExportVo.getPrescriptionDrugsTypeCode());
        goodsDrug.setIsEphedrine(docExportVo.getIsEphedrineName());
        goodsDrug.setIsKeyCuring(docExportVo.getIsKeyCuringName());
        goodsDrug.setIsMedicalInsuranceGoods(docExportVo.getIsMedicalInsuranceGoodsName());
        goodsDrug.setMedicalInsuranceNum(docExportVo.getMedicalInsuranceNum());
        goodsDrug.setGoodsDocId(goodsDocId);
        ServiceManager.goodsDocDrugService.save(goodsDrug);
    }

    @Transactional
    private void setGoodsDocOtherImportInfo(Long goodsDocId, GoodsDocExcelVo docExportVo) {
        GoodsDocOther goodsOther = ServiceManager.goodsDocOtherService.findByGoodsDocId(goodsDocId);
        if(goodsOther == null) {
            goodsOther = new GoodsDocOther();
        }

        goodsOther.setApprovalNumber(docExportVo.getApprovalNumber());
        goodsOther.setManufacturerAddr(docExportVo.getManufacturerAddr());
        goodsOther.setGoodsDocId(goodsDocId);
        ServiceManager.goodsDocOtherService.save(goodsOther);
    }

    @Transactional
    private void setGoodsDocChineseMedicinePiecesImportInfo(Long goodsDocId, GoodsDocExcelVo docExportVo) {
        GoodsDocChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsDocChineseMedicinePiecesService.findByGoodsDocId(goodsDocId);
        if(goodsChineseMedicinePieces == null) {
            goodsChineseMedicinePieces = new GoodsDocChineseMedicinePieces();
        }
        goodsChineseMedicinePieces.setApprovalNumber(docExportVo.getApprovalNumber());
        try {
            if(StringUtils.isNotBlank(docExportVo.getApprovalNumberTerm())) {
                goodsChineseMedicinePieces.setApprovalNumberTermString(docExportVo.getApprovalNumberTerm());
            }
            if(StringUtils.isNotBlank(docExportVo.getApproveDate())) {
                goodsChineseMedicinePieces.setApproveDateString(docExportVo.getApproveDate());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        goodsChineseMedicinePieces.setIsImportGoods(docExportVo.getIsImportGoodsName());
        goodsChineseMedicinePieces.setIsChineseMedicineProtect(docExportVo.getIsImportGoodsName());
        goodsChineseMedicinePieces.setDosageForm(docExportVo.getDosageForm());
        goodsChineseMedicinePieces.setProductionPlace(docExportVo.getProductionPlace());
        goodsChineseMedicinePieces.setEffect(docExportVo.getEffect());
        goodsChineseMedicinePieces.setPrescriptionDrugsTypeCode(docExportVo.getPrescriptionDrugsTypeCode());
        goodsChineseMedicinePieces.setIsEphedrine(docExportVo.getIsEphedrineName());
        goodsChineseMedicinePieces.setIsKeyCuring(docExportVo.getIsKeyCuringName());
        goodsChineseMedicinePieces.setIsMedicalInsuranceGoods(docExportVo.getIsMedicalInsuranceGoodsName());
        goodsChineseMedicinePieces.setMedicalInsuranceNum(docExportVo.getMedicalInsuranceNum());
        goodsChineseMedicinePieces.setGoodsDocId(goodsDocId);
        ServiceManager.goodsDocChineseMedicinePiecesService.save(goodsChineseMedicinePieces);
    }

    @Transactional
    private void setGoodsDocFoodHealthImportInfo(Long goodsDocId, GoodsDocExcelVo docExportVo) {
        GoodsDocFoodHealth goodsFoodHealth = ServiceManager.goodsDocFoodHealthService.findByGoodsDocId(goodsDocId);
        if(goodsFoodHealth == null) {
            goodsFoodHealth = new GoodsDocFoodHealth();
        }

        try {
            goodsFoodHealth.setProductionDateString(docExportVo.getProductionDate());
            goodsFoodHealth.setExpirationDateString(docExportVo.getExpirationDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        goodsFoodHealth.setFoodHygieneLicenceNum(docExportVo.getFoodHygieneLicenceNum());
        goodsFoodHealth.setHealthCareFunc(docExportVo.getHealthCareFunc());
        goodsFoodHealth.setAppropriateCrowd(docExportVo.getAppropriateCrowd());
        goodsFoodHealth.setEdibleMethodAndDosage(docExportVo.getEdibleMethodAndDosage());
        goodsFoodHealth.setStorageMethod(docExportVo.getStorageMethod());
        goodsFoodHealth.setExecStandard(docExportVo.getExecStandard());
        goodsFoodHealth.setEffectComposition(docExportVo.getEffectComposition());
        goodsFoodHealth.setNotice(docExportVo.getNotice());
        goodsFoodHealth.setNotAppropriateCrowd(docExportVo.getNotAppropriateCrowd());
        goodsFoodHealth.setGoodsDocId(goodsDocId);
        ServiceManager.goodsDocFoodHealthService.save(goodsFoodHealth);
    }

    @Transactional
    private void setGoodsDocDailyNecessitiesImportInfo(Long goodsDocId, GoodsDocExcelVo docExportVo) {
        GoodsDocDailyNecessities goodsDailyNecessities = ServiceManager.goodsDocDailyNecessitiesService.findByGoodsDocId(goodsDocId);
        if(goodsDailyNecessities == null) {
            goodsDailyNecessities = new GoodsDocDailyNecessities();
        }
        goodsDailyNecessities.setApprovalNumber(docExportVo.getApprovalNumber());
        goodsDailyNecessities.setManufacturerAddr(docExportVo.getManufacturerAddr());
        goodsDailyNecessities.setGoodsDocId(goodsDocId);
        ServiceManager.goodsDocDailyNecessitiesService.save(goodsDailyNecessities);
    }

    @Transactional
    private void setGoodsDocMedicalInstrumentsImportInfo(Long goodsDocId, GoodsDocExcelVo docExportVo) {
        GoodsDocMedicalInstruments medicalInstruments = ServiceManager.goodsDocMedicalInstrumentsService.findByGoodsDocId(goodsDocId);
        if(medicalInstruments == null) {
            medicalInstruments = new GoodsDocMedicalInstruments();
        }

        medicalInstruments.setRegNum(docExportVo.getRegNum());
        medicalInstruments.setRegRegistrationFormNum(docExportVo.getRegRegistrationFormNum());
        medicalInstruments.setManufacturerAddr(docExportVo.getManufacturerAddr());
        medicalInstruments.setApplyRange(docExportVo.getApplyRange());
        medicalInstruments.setProductStandardNum(docExportVo.getProductStandardNum());
        medicalInstruments.setGoodsDocId(goodsDocId);
        ServiceManager.goodsDocMedicalInstrumentsService.save(medicalInstruments);
    }

    @Transactional
    private void setGoodsDocCosmeticImportInfo(Long goodsDocId, GoodsDocExcelVo docExportVo) {
        GoodsDocCosmetic cosmetic = ServiceManager.goodsDocCosmeticService.findByGoodsDocId(goodsDocId);
        if(cosmetic == null) {
            cosmetic = new GoodsDocCosmetic();
        }
        cosmetic.setApprovalNumber(docExportVo.getApprovalNumber());
        cosmetic.setManufacturerAddr(docExportVo.getManufacturerAddr());
        cosmetic.setGoodsDocId(goodsDocId);
        ServiceManager.goodsDocCosmeticService.save(cosmetic);
    }

    private String getUUIDPath() {
        StringBuilder builder = new StringBuilder();
        builder.append("goods").append("_");
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        String uuid = UUID.randomUUID().toString();
        builder.append(cal.get(Calendar.YEAR))
                .append("_").append(cal.get(Calendar.MONTH) + 1)
                .append("_").append(cal.get(Calendar.DAY_OF_MONTH))
                .append("_").append(uuid);
        return builder.toString();
    }
}