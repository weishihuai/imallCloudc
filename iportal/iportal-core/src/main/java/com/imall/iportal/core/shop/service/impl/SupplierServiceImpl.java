package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.base.util.Pinyin4jUtil;
import com.imall.commons.base.util.excel.BaseExcelReader;
import com.imall.commons.base.util.excel.ErrorLog;
import com.imall.commons.base.util.excel.ListExcelWriter;
import com.imall.commons.dicts.*;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.FileMng;
import com.imall.iportal.core.main.entity.SysFileLib;
import com.imall.iportal.core.main.vo.FileMngVo;
import com.imall.iportal.core.platform.vo.SupplierExcelVo;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.Supplier;
import com.imall.iportal.core.shop.entity.SupplierCertificatesFile;
import com.imall.iportal.core.shop.repository.SupplierRepository;
import com.imall.iportal.core.shop.service.SupplierService;
import com.imall.iportal.core.shop.vo.*;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

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
public class SupplierServiceImpl extends AbstractBaseService<Supplier, Long> implements SupplierService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private SupplierRepository getSupplierRepository() {
        return (SupplierRepository) baseRepository;
    }

    @Override
    public Page<SupplierPageVo> querySupplier(Pageable pageable, SupplierSearchParam supplierSearchParam) throws ParseException {
        Page<Supplier> supplierPage = getSupplierRepository().query(pageable, supplierSearchParam);
        List<SupplierPageVo> supplierPageVos = new ArrayList<>();
        for (Supplier supplier : supplierPage.getContent()) {
            SupplierPageVo supplierPageVo = CommonUtil.copyProperties(supplier, new SupplierPageVo());
            supplierPageVo.setCreateTimeString(DateTimeUtils.convertDateToString(supplier.getCreateDate()));
            supplierPageVo.setApproveStateName(StringUtils.isNotBlank(supplier.getApproveStateCode())?ApproveStateCodeEnum.fromCode(supplier.getApproveStateCode()).toName():null);
            supplierPageVo.setUnitNatureName(StringUtils.isNotBlank(supplier.getUnitNature()) ? UnitNatureCodeEnum.fromCode(supplier.getUnitNature()).toName() : "");
            supplierPageVos.add(supplierPageVo);
        }
        return new PageImpl<SupplierPageVo>(supplierPageVos, new PageRequest(supplierPage.getNumber(), supplierPage.getSize()), supplierPage.getTotalElements());
    }

    @Override
    @Transactional
    public Long update(SupplierUpdateVo supplierUpdateVo) throws ParseException {
        Supplier supplier = findOne(supplierUpdateVo.getId());
        if (supplier == null||!supplierUpdateVo.getShopId().equals(supplier.getShopId())) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"供应商"}));
        }
        Supplier newSupplier = CommonUtil.copyProperties(supplierUpdateVo, new Supplier());
        newSupplier.setSupplierCode(supplier.getSupplierCode());
        newSupplier.setShopId(supplier.getShopId());
        newSupplier.setApproveStateCode(supplier.getApproveStateCode());
        newSupplier.setIsFirstCheck(supplier.getIsFirstCheck());
        newSupplier.setSupplierDocId(supplier.getSupplierDocId());
        newSupplier.setPinyin(Pinyin4jUtil.getPinYinHeadChar(supplierUpdateVo.getSupplierNm()));
        newSupplier.setUnitNature(StringUtils.isNotBlank(supplierUpdateVo.getUnitNature()) ? UnitNatureCodeEnum.fromCode(supplierUpdateVo.getUnitNature()).toCode() : null);
        newSupplier.setState(StringUtils.isNotBlank(supplierUpdateVo.getState())?BoolCodeEnum.fromCode(supplierUpdateVo.getState()).toCode():"");
        //保存资质文件
        for (SupplierCertificatesFileUpdateVo supCerFileUpdVo : supplierUpdateVo.getSupplierCertificatesFileList()) {
            supCerFileUpdVo.setSupplierId(supplierUpdateVo.getId());
            ServiceManager.supplierCertificatesFileService.updateBySupplierIdAndCertificatesType(supCerFileUpdVo);
        }
        //保存图片
        updatePict(supplierUpdateVo, supplierUpdateVo.getId());
        return save(newSupplier).getId();
    }

    @Override
    public Boolean checkNameIsExist(String name, Long id, Long shopId) {
        List<Supplier> supplier = getSupplierRepository().findBySupplierNmAndShopId(name,shopId);
        return CollectionUtils.isEmpty(supplier) || supplier.get(0).getId().equals(id);
    }

    @Override
    @Transactional
    public Long save(SupplierSaveVo supplierSaveVo) throws ParseException {
        List<Supplier> supplierList = getSupplierRepository().findBySupplierNmAndShopId(supplierSaveVo.getSupplierNm(),supplierSaveVo.getShopId());
        if(!CollectionUtils.isEmpty(supplierList)){
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_IS_EXIST_ERROR);
            throw new BusinessException(ResGlobalExt.COMMON_IS_EXIST_ERROR, String.format(message, new Object[]{supplierSaveVo.getSupplierNm()}));
        }
        Supplier supplier = CommonUtil.copyProperties(supplierSaveVo, new Supplier());
        supplier.setSupplierCode(DateSerialGenerator.genSerialCode(DateSerialGenerator.SUPPLIER_PREFIX));
        supplier.setPinyin(Pinyin4jUtil.getPinYinHeadChar(supplierSaveVo.getSupplierNm()));
        supplier.setUnitNature(StringUtils.isNotBlank(supplierSaveVo.getUnitNature()) ? UnitNatureCodeEnum.fromCode(supplierSaveVo.getUnitNature()).toCode() : null);
        supplier.setState(StringUtils.isNotBlank(supplierSaveVo.getState()) ? BoolCodeEnum.fromCode(supplierSaveVo.getState()).toCode() : null);
        supplier.setApproveStateCode(BoolCodeEnum.YES.toCode().equals(supplierSaveVo.getIsFirstCheck())?ApproveStateCodeEnum.WAIT_APPROVE.toCode():ApproveStateCodeEnum.PASS_APPROVE.toCode());
        Long supplierId = save(supplier).getId();
        //供应商 首营 审核 (首营且申请人不为空)
        if (BoolCodeEnum.fromCode(supplierSaveVo.getIsFirstCheck()).toCode().equals(BoolCodeEnum.YES.toCode()) && StringUtils.isNotBlank(supplierSaveVo.getApplyMan())) {
            FirstManageSupplierQualityApproveSaveVo firstManageSupplierQualityApprove = new FirstManageSupplierQualityApproveSaveVo();
            firstManageSupplierQualityApprove.setShopId(supplierSaveVo.getShopId());
            firstManageSupplierQualityApprove.setSupplierId(supplierId);
            firstManageSupplierQualityApprove.setApplyManName(supplierSaveVo.getApplyMan());
            firstManageSupplierQualityApprove.setSubmitAdvice(supplierSaveVo.getSubmitOpinion());
            firstManageSupplierQualityApprove.setEntRemark(supplierSaveVo.getRemark());
            ServiceManager.firstManageSupplierQualityApproveService.saveFirstManageSupplierQualityApprove(firstManageSupplierQualityApprove);
        }

        //保存资质文件
        for (SupplierCertificatesFileSaveVo supCerFilSaveVo : supplierSaveVo.getSupplierCertificatesFileList()) {
            supCerFilSaveVo.setSupplierId(supplierId);
            ServiceManager.supplierCertificatesFileService.saveSupplierCertificatesFile(supCerFilSaveVo);
        }
        //保存图片
        if(!CollectionUtils.isEmpty(supplierSaveVo.getFileMngs())){
            savePict(supplierSaveVo, supplierId);
        }
        return supplierId;
    }

    /**
     * 保存导入对象
     * @param supplier 供应商
     * @return
     */
    @Transactional
    private Long saveImportObject(Supplier supplier){
        List<Supplier> supplierList = getSupplierRepository().findBySupplierNmAndShopId(supplier.getSupplierNm(),supplier.getShopId());
        //如果重名则更新该对象
        if(!CollectionUtils.isEmpty(supplierList)){
            supplier.setId(supplierList.get(0).getId());
            supplier.setSupplierCode(supplierList.get(0).getSupplierCode());
        }
        supplier.setSupplierCode(StringUtils.isNotBlank(supplier.getSupplierCode())?supplier.getSupplierCode():DateSerialGenerator.genSerialCode(DateSerialGenerator.SUPPLIER_PREFIX));
        supplier.setPinyin(StringUtils.isNotBlank(supplier.getSupplierNm())?Pinyin4jUtil.getPinYinHeadChar(supplier.getSupplierNm()):"");
        supplier.setUnitNature(StringUtils.isNotBlank(supplier.getUnitNature()) ? UnitNatureCodeEnum.fromCode(supplier.getUnitNature()).toCode() : null);
        supplier.setState(StringUtils.isNotBlank(supplier.getState()) ? BoolCodeEnum.fromCode(supplier.getState()).toCode() : null);
        supplier.setApproveStateCode(BoolCodeEnum.fromCode(supplier.getIsFirstCheck()) == BoolCodeEnum.YES ? ApproveStateCodeEnum.WAIT_APPROVE.toCode():ApproveStateCodeEnum.PASS_APPROVE.toCode());
        save(supplier);
        //供应商 首营 审核 (首营且申请人不为空)
        if (BoolCodeEnum.fromCode(supplier.getIsFirstCheck()) == BoolCodeEnum.YES && StringUtils.isNotBlank(supplier.getApplyMan().trim())) {
            FirstManageSupplierQualityApproveSaveVo firstManageSupplierQualityApprove = new FirstManageSupplierQualityApproveSaveVo();
            firstManageSupplierQualityApprove.setShopId(supplier.getShopId());
            firstManageSupplierQualityApprove.setSupplierId(supplier.getId());
            firstManageSupplierQualityApprove.setApplyManName(supplier.getApplyMan());
            firstManageSupplierQualityApprove.setSubmitAdvice(supplier.getSubmitOpinion());
            firstManageSupplierQualityApprove.setEntRemark(supplier.getRemark());
            ServiceManager.firstManageSupplierQualityApproveService.saveFirstManageSupplierQualityApprove(firstManageSupplierQualityApprove);
        }

        return supplier.getId();
    }


    @Override
    @Transactional
    public Long updateIsFirstCheck(Long supplierId, String isFirstCheck) {
        Supplier supplier = findOne(supplierId);
        if (supplier == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }
        supplier.setIsFirstCheck(BoolCodeEnum.fromCode(isFirstCheck).toCode());
        return save(supplier).getId();
    }

    @Override
    @Transactional
    public Long updateApproveStateCode(Long supplierId, String approveStateCode) {
        Supplier supplier = findOne(supplierId);
        if (supplier == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }
        supplier.setApproveStateCode(ApproveStateCodeEnum.fromCode(approveStateCode).toCode());
        return save(supplier).getId();
    }

    @Override
    public SupplierDetailVo findById(Long id, Long shopId) {
        Supplier supplier = findOne(id);
        if (supplier == null || !supplier.getShopId().equals(shopId)) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }
        SupplierDetailVo supplierDetailVo = CommonUtil.copyProperties(supplier, new SupplierDetailVo());
        supplierDetailVo.setSupplierCertificatesFileList(ServiceManager.supplierCertificatesFileService.listSupplierCertificatesFilesList(supplier.getId()));
        supplierDetailVo.setApplyDateString(DateTimeUtils.convertDateToString(supplier.getApplyDate()));
        supplierDetailVo.setUnitNatureName(StringUtils.isNotBlank(supplier.getUnitNature()) ? UnitNatureCodeEnum.fromCode(supplier.getUnitNature()).toName() : "");
        supplierDetailVo.setIsFirstCheckName(StringUtils.isNotBlank(supplier.getIsFirstCheck()) ? BoolCodeEnum.fromCode(supplier.getIsFirstCheck()).toName() : "");

        //获取文件
        List<FileMng> fileMngMsgList = ServiceManager.fileMngService.getList(FileObjectTypeCodeEnum.SUPPLIER_PICT, id);
        List<FileMngVo> fileMngVos = new ArrayList<>();
        for (FileMng pictMng : fileMngMsgList) {
            FileMngVo fileMngVo= CommonUtil.copyProperties(pictMng,new FileMngVo());
            SysFileLib fileLib = ServiceManager.sysFileLibService.findOne(pictMng.getSysFileLibId());
            fileMngVo.setFileNm(fileLib != null ? fileLib.getFileNm() : null);
            fileMngVos.add(fileMngVo);
        }
        supplierDetailVo.setPictMngVos(fileMngVos);
        return supplierDetailVo;
    }

    @Override
    @Transactional
    public Long updateSupplierState(Long id, String state, Long shopId) {
        Supplier supplier = findOne(id);
        if (supplier == null||!shopId.equals(supplier.getShopId())) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }
        supplier.setState(BoolCodeEnum.fromCode(state).toCode());
        return save(supplier).getId();
    }

    @Override
    public Page<SupplierComponentPageVo> queryPage(Pageable pageable, String supplierNm, String certificatesType, String certificatesNum, Long shopId) {
        Page<Supplier> supplierPage = getSupplierRepository().queryPage(pageable, supplierNm, certificatesType, certificatesNum,shopId);
        List<SupplierComponentPageVo> supplierComponentPageVos = new ArrayList<>();
        for (Supplier supplier : supplierPage.getContent()) {
            SupplierComponentPageVo supplierComponentPageVo = CommonUtil.copyProperties(supplier, new SupplierComponentPageVo());
            String businessLicense = ServiceManager.supplierCertificatesFileService.findBySupplierIdAndCertificatesType(supplier.getId(), "BUSINESS_LICENSE").getCertificatesNum();
            String commodityLicense = ServiceManager.supplierCertificatesFileService.findBySupplierIdAndCertificatesType(supplier.getId(), "COMMODITY_LICENSE").getCertificatesNum();
            supplierComponentPageVo.setBusinessLicense(businessLicense);
            supplierComponentPageVo.setCommodityLicense(commodityLicense);
            supplierComponentPageVos.add(supplierComponentPageVo);
        }
        return new PageImpl<>(supplierComponentPageVos, pageable, supplierPage.getTotalElements());
    }


    private void savePict(SupplierSaveVo supplierSaveVo, Long id) {
        List<FileMng> fileMngMsgList = new ArrayList<>();
        for (FileMng fileMng : supplierSaveVo.getFileMngs()) {
            if (StringUtils.isNotBlank(fileMng.getFileId()) && fileMng.getSysFileLibId() != null) {
                fileMngMsgList.add(fileMng);
            }
        }
        ServiceManager.fileMngService.saveListAndDeleteOld(FileObjectTypeCodeEnum.SUPPLIER_PICT, id, fileMngMsgList);

    }

    private void updatePict(SupplierUpdateVo supplierUpdateVo, Long id) {
        List<FileMng> fileMngMsgList = new ArrayList<>();
        for (FileMng fileMng : supplierUpdateVo.getFileMngs()) {
            if (StringUtils.isNotBlank(fileMng.getFileId()) && fileMng.getSysFileLibId() != null) {
                fileMngMsgList.add(fileMng);
            }
        }
        ServiceManager.fileMngService.saveListAndDeleteOld(FileObjectTypeCodeEnum.SUPPLIER_PICT, id, fileMngMsgList);
    }

    @Override
    public Boolean exportExcel(String xlsTemplatePath, String xlsOutputPath, Pageable pageable, SupplierSearchParam supplierSearchParam) {
        Page<Supplier> supplierPage = null;
        try {
            supplierPage = getSupplierRepository().query(pageable, supplierSearchParam);
            List<Supplier> resultList = supplierPage.getTotalElements() > 65000 ? supplierPage.getContent().subList(0, 65000-1) : supplierPage.getContent();
            List<SupplierExcelVo> outResultList = new ArrayList<SupplierExcelVo>();
            List<SysDictItemSimpleVo> businessCategoryList = ServiceManager.sysDictItemService.findByAvailableAndDictType("BUSINESS_CATEGORY");
            Map<String, String> businessCategoryMap = new HashMap<>();
            for(SysDictItemSimpleVo simpleVo: businessCategoryList){
                businessCategoryMap.put(simpleVo.getDictItemCode(), simpleVo.getDictItemNm());
            }

            List<SysDictItemSimpleVo> businessRangeList = ServiceManager.sysDictItemService.findByAvailableAndDictType("BUSINESS_RANGE");
            Map<String, String> businessRangeMap = new HashMap<>();
            for(SysDictItemSimpleVo simpleVo: businessRangeList){
                businessRangeMap.put(simpleVo.getDictItemCode(), simpleVo.getDictItemNm());
            }
            for(Supplier supplier : resultList){
                SupplierExcelVo exportVo = CommonUtil.copyProperties(supplier, new SupplierExcelVo());
                if(StringUtils.isNotBlank(supplier.getBusinessCategory())){
                    exportVo.setBusinessCategoryName(convertToNames(businessCategoryMap, supplier.getBusinessCategory()));
                }
                if(StringUtils.isNotBlank(supplier.getBusinessRange())){
                    exportVo.setBusinessRangeName(convertToNames(businessRangeMap, supplier.getBusinessRange()));
                }
                if(StringUtils.isNotBlank(supplier.getUnitNature())){
                    exportVo.setUnitNatureName(UnitNatureCodeEnum.fromCode(supplier.getUnitNature()).toName());
                }
                if(StringUtils.isNotBlank(supplier.getState())){
                    exportVo.setStateName(BoolCodeEnum.fromCode(supplier.getState()) == BoolCodeEnum.YES ? "启用" : "禁用");
                }
                if(StringUtils.isNotBlank(supplier.getIsFirstCheck())){
                    exportVo.setIsFirstCheckName(BoolCodeEnum.fromCode(supplier.getIsFirstCheck()).toName());
                }
                if(StringUtils.isNotBlank(supplier.getApproveStateCode())){
                    exportVo.setApproveStateName(ApproveStateCodeEnum.fromCode(supplier.getApproveStateCode()).toName());
                }
                setSupplierExportInfo(exportVo);

                outResultList.add(exportVo);
            }
            ListExcelWriter listExcelWriter = new ListExcelWriter(xlsTemplatePath);
            listExcelWriter.fillToFile(outResultList, xlsOutputPath);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void setSupplierExportInfo(SupplierExcelVo exportVo) {
        List<SupplierCertificatesFile> certificatesFileList = ServiceManager.supplierCertificatesFileService.findBySupplierId(exportVo.getId());
        for(SupplierCertificatesFile certificates : certificatesFileList) {
            switch(CertificatesFileCodeEnum.fromCode(certificates.getCertificatesType())) {
                case BUSINESS_LICENSE:
                    exportVo.setBusinessName(certificates.getCertificatesNum());
                    exportVo.setBusinessDate(certificates.getCertificatesValidityString());
                    break;
                case ORGANIZATION_CERTIFICATE:
                    exportVo.setOrganizationName(certificates.getCertificatesNum());
                    exportVo.setOrganizationDate(certificates.getCertificatesValidityString());
                    break;
                case GSP_CERTIFICATE:
                    exportVo.setGspName(certificates.getCertificatesNum());
                    exportVo.setGspDate(certificates.getCertificatesValidityString());
                    break;
                case COMMODITY_LICENSE:
                    exportVo.setCommodityName(certificates.getCertificatesNum());
                    exportVo.setCommodityDate(certificates.getCertificatesValidityString());
                    break;
                case QUALITY_AGREEMENT:
                    exportVo.setQualityName(certificates.getCertificatesNum());
                    exportVo.setQualityDate(certificates.getCertificatesValidityString());
                    break;
                case FOOD_CIRCULATION_LICENSE:
                    exportVo.setFoodCirculationName(certificates.getCertificatesNum());
                    exportVo.setFoodCirculationDate(certificates.getCertificatesValidityString());
                    break;
                case FOOD_HYGIENE_LICENSE:
                    exportVo.setFoodHygieneName(certificates.getCertificatesNum());
                    exportVo.setFoodHygieneDate(certificates.getCertificatesValidityString());
                    break;
                case HEALTH_PRODUCT_HYGIENE_LICENSE:
                    exportVo.setHealthProductHygieneName(certificates.getCertificatesNum());
                    exportVo.setHealthProductHygieneDate(certificates.getCertificatesValidityString());
                    break;
                case MEDIC_DEVICE_MANUFACTURE_PERMISS:
                    exportVo.setMedicDeviceManufactureName(certificates.getCertificatesNum());
                    exportVo.setMedicDeviceManufactureDate(certificates.getCertificatesValidityString());
                    break;
                case COSMETICS_BUSINESS_LICENSE:
                    exportVo.setCosmeticsBusinessName(certificates.getCertificatesNum());
                    exportVo.setCosmeticsBusinessDate(certificates.getCertificatesValidityString());
                    break;
                case COSMETICS_HYGIENIC_LICENSE:
                    exportVo.setCosmeticsHygienicName(certificates.getCertificatesNum());
                    exportVo.setCosmeticsHygienicDate(certificates.getCertificatesValidityString());
                    break;
            }
        }
    }

    private String convertToNames(Map<String, String> codeNameMap, String codesStr){
        StringBuilder builder = new StringBuilder();
        String[] codes = codesStr.split(",");
        for(int i=0; i<codes.length; i++){
            String code = codes[i];
            String name = codeNameMap.get(code);
            if(StringUtils.isNotBlank(name)){
                builder.append(name);
                if(i<codes.length-1){
                    builder.append(",");
                }
            }
        }
        return builder.toString();
    }

    private String convertToCodes(Map<String, String> nameCodeMap, String namesStr){
        StringBuilder builder = new StringBuilder();
        String[] names = namesStr.split(",");
        for(int i=0; i<names.length; i++){
            String name = names[i];
            String code = nameCodeMap.get(name);
            if(StringUtils.isNotBlank(code)){
                builder.append(code);
                if(i<names.length-1){
                    builder.append(",");
                }
            }
        }
        return builder.toString();
    }

    @Autowired
    private JpaTransactionManager jpaTransactionManager;

    @Transactional(propagation = Propagation.NOT_SUPPORTED) //以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
    public List<ErrorLog> importData(String localFileId, Long shopId) {
        BaseExcelReader<SupplierExcelVo> excelReader = new BaseExcelReader<SupplierExcelVo>() {

            private Map<String, String> businessCategoryMap;

            private Map<String, String> businessRangeMap;

            @Override
            protected Map<String, Object> convert(Map<String, Object> valueMap) {
                if(businessCategoryMap==null){
                    List<SysDictItemSimpleVo> businessCategoryList = ServiceManager.sysDictItemService.findByAvailableAndDictType("BUSINESS_CATEGORY");
                    businessCategoryMap = new HashMap<>();
                    for(SysDictItemSimpleVo simpleVo: businessCategoryList){
                        businessCategoryMap.put(simpleVo.getDictItemNm(), simpleVo.getDictItemCode());
                    }
                }

                if(businessRangeMap==null){
                    List<SysDictItemSimpleVo> businessRangeList = ServiceManager.sysDictItemService.findByAvailableAndDictType("BUSINESS_RANGE");
                    businessRangeMap = new HashMap<>();
                    for(SysDictItemSimpleVo simpleVo: businessRangeList){
                        businessRangeMap.put(simpleVo.getDictItemNm(), simpleVo.getDictItemCode());
                    }
                }

                if(valueMap.get(Supplier.STATE+"Name")!=null){
                    valueMap.put(Supplier.STATE, BoolCodeEnum.fromValue("启用".equals(valueMap.get(Supplier.STATE+"Name"))).toCode());
                }

                if(valueMap.get(Supplier.UNIT_NATURE+"Name")!=null){
                    valueMap.put(Supplier.UNIT_NATURE, valueMap.get(Supplier.UNIT_NATURE+"Name").equals(UnitNatureCodeEnum.WHOLESALE_ENTERPRISE.toName()) ? UnitNatureCodeEnum.WHOLESALE_ENTERPRISE.toCode() : UnitNatureCodeEnum.MANUFACTURER.toCode());
                }

                if(valueMap.get(Supplier.BUSINESS_CATEGORY+"Name")!=null){
                    valueMap.put(Supplier.BUSINESS_CATEGORY, convertToCodes(businessCategoryMap, (String) valueMap.get(Supplier.BUSINESS_CATEGORY+"Name")));
                }
                if(valueMap.get(Supplier.BUSINESS_RANGE+"Name")!=null){
                    valueMap.put(Supplier.BUSINESS_RANGE, convertToCodes(businessRangeMap, (String) valueMap.get(Supplier.BUSINESS_RANGE+"Name")));
                }

                if(valueMap.get(Supplier.IS_FIRST_CHECK+"Name")!=null){
                    valueMap.put(Supplier.IS_FIRST_CHECK, valueMap.get(Supplier.IS_FIRST_CHECK+"Name").equals(BoolCodeEnum.YES.toName()) ? BoolCodeEnum.YES.toCode() : BoolCodeEnum.NO.toCode());
                }

                String approveStateName = (String) valueMap.get("approveStateName");
                if(approveStateName!=null){
                    if(ApproveStateCodeEnum.WAIT_APPROVE.toName().equals(approveStateName)){
                        valueMap.put(Supplier.APPROVE_STATE_CODE, ApproveStateCodeEnum.WAIT_APPROVE.toCode());
                    }
                    if(ApproveStateCodeEnum.PASS_APPROVE.toName().equals(approveStateName)){
                        valueMap.put(Supplier.APPROVE_STATE_CODE, ApproveStateCodeEnum.PASS_APPROVE.toCode());
                    }
                    if(ApproveStateCodeEnum.REJECTED.toName().equals(approveStateName)){
                        valueMap.put(Supplier.APPROVE_STATE_CODE, ApproveStateCodeEnum.REJECTED.toCode());
                    }
                }

                return valueMap;
            }

            @Override
            public boolean verifyExt(int lineNumber, Collection<String> protocolList, Map<String, Object> valueMap, List<ErrorLog> errorLogList) {
                return true;
            }

            @Override
            protected Class getBeanClass() {
                return SupplierExcelVo.class;
            }
        };

        List<ErrorLog> errorLogs = new ArrayList<ErrorLog>();
        List<SupplierExcelVo> inputList = excelReader.importData(localFileId, errorLogs);
        int index = 1;
        for (SupplierExcelVo bean : inputList) {
            DefaultTransactionDefinition td = new DefaultTransactionDefinition();
            td.setName("Process Import Supplier" + index);
            index++;
            //使用底层数据库默认隔离级别。
            td.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
            //支持现有的事务，如果没有则新建一个事务。
            td.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
            TransactionStatus txs = jpaTransactionManager.getTransaction(td);

            try {
                //提交事务
                Supplier supplier = new Supplier();
                bean.setShopId(shopId);
                CommonUtil.copyProperties(bean, supplier);
                supplier.setCreateDate(new Date());
                Long supplierId = saveImportObject(supplier);
                setSupplierImportInfo(bean, supplierId);
                jpaTransactionManager.commit(txs);
            } catch(DataIntegrityViolationException dive) {
                if (!txs.isCompleted()) {
                    //回滚事务
                    jpaTransactionManager.rollback(txs);
                }
                throw new BusinessException(ResGlobal.COMMON_ERROR, dive);
            } catch (Exception e){
                if (!txs.isCompleted()) {
                    //回滚事务
                    jpaTransactionManager.rollback(txs);
                }
                throw new BusinessException(ResGlobal.COMMON_ERROR, e);
            }

        }
        return errorLogs;
    }

    private void setSupplierImportInfo(SupplierExcelVo importVo, Long supplierId) {
        List<SupplierCertificatesFile> certificatesFileList = ServiceManager.supplierCertificatesFileService.findBySupplierId(supplierId);
        for(SupplierCertificatesFile certificates : certificatesFileList) {
            ServiceManager.supplierCertificatesFileService.delete(certificates);
        }

        if(StringUtils.isNotBlank(importVo.getBusinessName()) || StringUtils.isNotBlank(importVo.getBusinessDate())) {
            SupplierCertificatesFile certificates = new SupplierCertificatesFile();
            certificates.setCertificatesNum(importVo.getBusinessName());
            certificates.setCertificatesValidityString(importVo.getBusinessDate());
            certificates.setSupplierId(supplierId);
            certificates.setCertificatesType(CertificatesFileCodeEnum.BUSINESS_LICENSE.toCode());
            ServiceManager.supplierCertificatesFileService.save(certificates);
        }
        if(StringUtils.isNotBlank(importVo.getOrganizationName()) || StringUtils.isNotBlank(importVo.getOrganizationDate())) {
            SupplierCertificatesFile certificates = new SupplierCertificatesFile();
            certificates.setCertificatesNum(importVo.getOrganizationName());
            certificates.setCertificatesValidityString(importVo.getOrganizationDate());
            certificates.setSupplierId(supplierId);
            certificates.setCertificatesType(CertificatesFileCodeEnum.ORGANIZATION_CERTIFICATE.toCode());
            ServiceManager.supplierCertificatesFileService.save(certificates);
        }

        if(StringUtils.isNotBlank(importVo.getGspName()) || StringUtils.isNotBlank(importVo.getGspDate())) {
            SupplierCertificatesFile certificates = new SupplierCertificatesFile();
            certificates.setCertificatesNum(importVo.getGspName());
            certificates.setCertificatesValidityString(importVo.getGspDate());
            certificates.setSupplierId(supplierId);
            certificates.setCertificatesType(CertificatesFileCodeEnum.GSP_CERTIFICATE.toCode());
            ServiceManager.supplierCertificatesFileService.save(certificates);
        }

        if(StringUtils.isNotBlank(importVo.getCommodityName()) || StringUtils.isNotBlank(importVo.getCommodityDate())) {
            SupplierCertificatesFile certificates = new SupplierCertificatesFile();
            certificates.setCertificatesNum(importVo.getCommodityName());
            certificates.setCertificatesValidityString(importVo.getCommodityDate());
            certificates.setSupplierId(supplierId);
            certificates.setCertificatesType(CertificatesFileCodeEnum.COMMODITY_LICENSE.toCode());
            ServiceManager.supplierCertificatesFileService.save(certificates);
        }

        if(StringUtils.isNotBlank(importVo.getQualityName()) || StringUtils.isNotBlank(importVo.getQualityDate())) {
            SupplierCertificatesFile certificates = new SupplierCertificatesFile();
            certificates.setCertificatesNum(importVo.getQualityName());
            certificates.setCertificatesValidityString(importVo.getQualityDate());
            certificates.setSupplierId(supplierId);
            certificates.setCertificatesType(CertificatesFileCodeEnum.QUALITY_AGREEMENT.toCode());
            ServiceManager.supplierCertificatesFileService.save(certificates);
        }
        if(StringUtils.isNotBlank(importVo.getFoodCirculationName()) || StringUtils.isNotBlank(importVo.getFoodCirculationDate())) {
            SupplierCertificatesFile certificates = new SupplierCertificatesFile();
            certificates.setCertificatesNum(importVo.getFoodCirculationName());
            certificates.setCertificatesValidityString(importVo.getFoodCirculationDate());
            certificates.setSupplierId(supplierId);
            certificates.setCertificatesType(CertificatesFileCodeEnum.FOOD_CIRCULATION_LICENSE.toCode());
            ServiceManager.supplierCertificatesFileService.save(certificates);
        }
        if(StringUtils.isNotBlank(importVo.getFoodHygieneName()) || StringUtils.isNotBlank(importVo.getFoodHygieneDate())) {
            SupplierCertificatesFile certificates = new SupplierCertificatesFile();
            certificates.setCertificatesNum(importVo.getFoodHygieneName());
            certificates.setCertificatesValidityString(importVo.getFoodHygieneDate());
            certificates.setSupplierId(supplierId);
            certificates.setCertificatesType(CertificatesFileCodeEnum.FOOD_HYGIENE_LICENSE.toCode());
            ServiceManager.supplierCertificatesFileService.save(certificates);
        }
        if(StringUtils.isNotBlank(importVo.getHealthProductHygieneName()) || StringUtils.isNotBlank(importVo.getHealthProductHygieneDate())) {
            SupplierCertificatesFile certificates = new SupplierCertificatesFile();
            certificates.setCertificatesNum(importVo.getHealthProductHygieneName());
            certificates.setCertificatesValidityString(importVo.getHealthProductHygieneDate());
            certificates.setSupplierId(supplierId);
            certificates.setCertificatesType(CertificatesFileCodeEnum.HEALTH_PRODUCT_HYGIENE_LICENSE.toCode());
            ServiceManager.supplierCertificatesFileService.save(certificates);
        }
        if(StringUtils.isNotBlank(importVo.getMedicDeviceManufactureName()) || StringUtils.isNotBlank(importVo.getMedicDeviceManufactureDate())) {
            SupplierCertificatesFile certificates = new SupplierCertificatesFile();
            certificates.setCertificatesNum(importVo.getMedicDeviceManufactureName());
            certificates.setCertificatesValidityString(importVo.getMedicDeviceManufactureDate());
            certificates.setSupplierId(supplierId);
            certificates.setCertificatesType(CertificatesFileCodeEnum.MEDIC_DEVICE_MANUFACTURE_PERMISS.toCode());
            ServiceManager.supplierCertificatesFileService.save(certificates);
        }
        if(StringUtils.isNotBlank(importVo.getCosmeticsBusinessName()) || StringUtils.isNotBlank(importVo.getCosmeticsBusinessDate())) {
            SupplierCertificatesFile certificates = new SupplierCertificatesFile();
            certificates.setCertificatesNum(importVo.getCosmeticsBusinessName());
            certificates.setCertificatesValidityString(importVo.getCosmeticsBusinessDate());
            certificates.setSupplierId(supplierId);
            certificates.setCertificatesType(CertificatesFileCodeEnum.COSMETICS_BUSINESS_LICENSE.toCode());
            ServiceManager.supplierCertificatesFileService.save(certificates);
        }
        if(StringUtils.isNotBlank(importVo.getCosmeticsHygienicName()) || StringUtils.isNotBlank(importVo.getCosmeticsHygienicDate())) {
            SupplierCertificatesFile certificates = new SupplierCertificatesFile();
            certificates.setCertificatesNum(importVo.getCosmeticsHygienicName());
            certificates.setCertificatesValidityString(importVo.getCosmeticsHygienicDate());
            certificates.setSupplierId(supplierId);
            certificates.setCertificatesType(CertificatesFileCodeEnum.COSMETICS_HYGIENIC_LICENSE.toCode());
            ServiceManager.supplierCertificatesFileService.save(certificates);
        }

    }

}