package com.imall.iportal.core.platform.service.impl;


import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.base.util.Pinyin4jUtil;
import com.imall.commons.base.util.excel.BaseExcelReader;
import com.imall.commons.base.util.excel.ErrorLog;
import com.imall.commons.base.util.excel.ListExcelWriter;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.commons.dicts.UnitNatureCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.platform.entity.SupplierDoc;
import com.imall.iportal.core.platform.entity.SupplierDocCertificatesFile;
import com.imall.iportal.core.platform.repository.SupplierDocRepository;
import com.imall.iportal.core.platform.service.SupplierDocService;
import com.imall.iportal.core.platform.vo.*;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.service.impl.DateSerialGenerator;
import com.imall.iportal.core.shop.vo.SysDictItemSimpleVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
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

import java.text.ParseException;
import java.util.*;

/**
 * 供应商 档案(服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class SupplierDocServiceImpl extends AbstractBaseService<SupplierDoc, Long> implements SupplierDocService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private SupplierDocRepository getSupplierDocRepository() {
		return (SupplierDocRepository) baseRepository;
	}

	@Override
	public Page<SupplierDocPageVo> querySupplierDoc(Pageable pageable, SupplierDocSearchParam supplierDocSearchParam) throws ParseException {
		Page<SupplierDoc> supplierDocPage = getSupplierDocRepository().query(pageable, supplierDocSearchParam);
		List<SupplierDocPageVo> supplierDocPageVos = new ArrayList<>();
		for (SupplierDoc supplierDoc : supplierDocPage.getContent()) {
			SupplierDocPageVo supplierDocPageVo = CommonUtil.copyProperties(supplierDoc, new SupplierDocPageVo());
			supplierDocPageVo.setCreateTimeString(DateTimeUtils.convertDateTimeToString(supplierDoc.getCreateDate()));
			supplierDocPageVo.setUnitNatureName(StringUtils.isNotBlank(supplierDoc.getUnitNature()) ? UnitNatureCodeEnum.fromCode(supplierDoc.getUnitNature()).toName() : "");
			supplierDocPageVos.add(supplierDocPageVo);
		}
		return new PageImpl<SupplierDocPageVo>(supplierDocPageVos, pageable, supplierDocPage.getTotalElements());
	}

	@Override
	@Transactional
	public Long update(SupplierDocUpdateVo supplierDocUpdateVo) throws ParseException {

		List<SupplierDoc> supplierDocs = getSupplierDocRepository().findBySupplierNm(supplierDocUpdateVo.getSupplierNm());
		if(CollectionUtils.isNotEmpty(supplierDocs) && !supplierDocs.get(0).getId().equals(supplierDocUpdateVo.getId())){
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_IS_EXIST_ERROR);
			throw new BusinessException(ResGlobalExt.COMMON_IS_EXIST_ERROR, String.format(message, new Object[]{supplierDocUpdateVo.getSupplierNm()}));
		}

		SupplierDoc supplierDoc = findOne(supplierDocUpdateVo.getId());
		if ( supplierDoc== null) {
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND,  String.format(message, new Object[]{supplierDocUpdateVo.getSupplierNm()}));
		}
		SupplierDoc newSupplierDoc = CommonUtil.copyProperties(supplierDocUpdateVo,supplierDoc);
		supplierDoc.setPinyin(Pinyin4jUtil.getPinYinHeadChar(supplierDoc.getSupplierNm()));
		save(newSupplierDoc);
		//保存资质文件
		List<SupplierDocCertificatesFile> certificatesFileList = ServiceManager.supplierDocCertificatesFileService.findBySupplierDocId(supplierDocUpdateVo.getId());
		for(SupplierDocCertificatesFile certificates : certificatesFileList) {
			ServiceManager.supplierDocCertificatesFileService.delete(certificates);
		}
		for (SupplierDocCertificatesFileSaveVo supCerFileUpdVo : supplierDocUpdateVo.getSupplierDocCertificatesFileList()) {
			supCerFileUpdVo.setSupplierDocId(supplierDocUpdateVo.getId());
			ServiceManager.supplierDocCertificatesFileService.saveSupplierDocCertificatesFile(supCerFileUpdVo);
		}
		return save(supplierDoc).getId();
	}

	@Override
	public Boolean checkNameIsExist(String name, Long userId) {
		List<SupplierDoc> supplier = getSupplierDocRepository().findBySupplierNm(name);
		return CollectionUtils.isEmpty(supplier) || supplier.get(0).getId().equals(userId);
	}

	@Override
	@Transactional
		public Long save(SupplierDocSaveVo supplierDocSaveVo) throws ParseException {

		List<SupplierDoc> supplierDocs = getSupplierDocRepository().findBySupplierNm(supplierDocSaveVo.getSupplierNm());
		if(!CollectionUtils.isEmpty(supplierDocs)){
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_IS_EXIST_ERROR);
			throw new BusinessException(ResGlobalExt.COMMON_IS_EXIST_ERROR, String.format(message, new Object[]{supplierDocSaveVo.getSupplierNm()}));
		}
		SupplierDoc supplierDoc = CommonUtil.copyProperties(supplierDocSaveVo, new SupplierDoc());
		supplierDoc.setSupplierCode(DateSerialGenerator.genSerialCode(DateSerialGenerator.SUPPLIER_PREFIX));
		supplierDoc.setPinyin(Pinyin4jUtil.getPinYinHeadChar(supplierDoc.getSupplierNm()));
		supplierDoc.setUnitNature(StringUtils.isNotBlank(supplierDocSaveVo.getUnitNature())?UnitNatureCodeEnum.fromCode(supplierDocSaveVo.getUnitNature()).toCode():null);
		supplierDoc.setState(BoolCodeEnum.YES.toCode());
		Long supplierDocId = save(supplierDoc).getId();
		//保存资质文件
		for (SupplierDocCertificatesFileSaveVo supCerFilSaveVo : supplierDocSaveVo.getSupplierDocCertificatesFileList()) {
			supCerFilSaveVo.setSupplierDocId(supplierDocId);
			ServiceManager.supplierDocCertificatesFileService.saveSupplierDocCertificatesFile(supCerFilSaveVo);
		}
		return supplierDocId;
	}

	@Override
	public SupplierDocDetailVo findById(Long id) {
		SupplierDoc supplierDoc = findOne(id);
		if (supplierDoc == null) {
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
		}
		SupplierDocDetailVo supplierDocDetailVo = CommonUtil.copyProperties(supplierDoc, new SupplierDocDetailVo());
		supplierDocDetailVo.setUnitNatureName(StringUtils.isNotBlank(supplierDoc.getUnitNature()) ? UnitNatureCodeEnum.fromCode(supplierDoc.getUnitNature()).toName() : "");
		supplierDocDetailVo.setSupplierDocCertificatesFileList(ServiceManager.supplierDocCertificatesFileService.listSupplierDocCertificatesFilesList(supplierDoc.getId()));
		return supplierDocDetailVo;
	}

	@Override
	@Transactional
	public Long updateSupplierDocState(Long id, String state) {
		SupplierDoc supplierDoc = findOne(id);
		if (supplierDoc == null) {
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
		}
		supplierDoc.setState(BoolCodeEnum.fromCode(state).toCode());
		return save(supplierDoc).getId();
	}

	@Override
	public Page<SupplierDocComponentPageVo> queryPage(Pageable pageable, String supplierNm, String certificatesNum, String certificatesType) {
		Page<SupplierDoc> supplierDocPage = getSupplierDocRepository().queryPage(pageable, supplierNm,certificatesType, certificatesNum);
		List<SupplierDocComponentPageVo> supplierComponentPageVos = new ArrayList<>();
		for (SupplierDoc supplierDoc:supplierDocPage.getContent()) {
			SupplierDocComponentPageVo supplierDocComponentPageVo = CommonUtil.copyProperties(supplierDoc, new SupplierDocComponentPageVo());
            String businessLicense= ServiceManager.supplierDocCertificatesFileService.findBySupplierDocIdAndCertificatesType(supplierDoc.getId(), "BUSINESS_LICENSE").getCertificatesNum();
            String commodityLicense= ServiceManager.supplierDocCertificatesFileService.findBySupplierDocIdAndCertificatesType(supplierDoc.getId(), "COMMODITY_LICENSE").getCertificatesNum();
            supplierDocComponentPageVo.setBusinessLicense(businessLicense);
            supplierDocComponentPageVo.setCommodityLicense(commodityLicense);
			List<SupplierDocCertificatesFileVo> listSupplierDocCertificatesFilesList = ServiceManager.supplierDocCertificatesFileService.listSupplierDocCertificatesFilesList(supplierDoc.getId());
			supplierDocComponentPageVo.setSupplierCertificatesFileList(listSupplierDocCertificatesFilesList);
			supplierComponentPageVos.add(supplierDocComponentPageVo);
		}
		return new PageImpl<SupplierDocComponentPageVo>(supplierComponentPageVos, pageable, supplierDocPage.getTotalElements());
	}

	@Override
	public Boolean exportExcel(String xlsTemplatePath, String xlsOutputPath, Pageable pageable, SupplierDocSearchParam supplierSearchParam) {
		Page<SupplierDoc> supplierDocPage = null;
		try {
			supplierDocPage = getSupplierDocRepository().query(pageable, supplierSearchParam);
			List<SupplierDoc> resultList = supplierDocPage.getTotalElements() > 65000 ? supplierDocPage.getContent().subList(0, 65000-1) : supplierDocPage.getContent();
			List<SupplierDocExcelVo> outResultList = new ArrayList<SupplierDocExcelVo>();
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
			for(SupplierDoc supplierDoc : resultList){
				SupplierDocExcelVo docExportVo = CommonUtil.copyProperties(supplierDoc, new SupplierDocExcelVo());
				if(StringUtils.isNotBlank(supplierDoc.getBusinessCategory())){
					docExportVo.setBusinessCategoryName(convertToNames(businessCategoryMap, supplierDoc.getBusinessCategory()));
				}
				if(StringUtils.isNotBlank(supplierDoc.getBusinessRange())){
					docExportVo.setBusinessRangeName(convertToNames(businessRangeMap, supplierDoc.getBusinessRange()));
				}
				if(StringUtils.isNotBlank(supplierDoc.getUnitNature())){
					docExportVo.setUnitNatureName(UnitNatureCodeEnum.fromCode(supplierDoc.getUnitNature()).toName());
				}
				if(StringUtils.isNotBlank(supplierDoc.getState())){
					docExportVo.setStateName(BoolCodeEnum.fromCode(supplierDoc.getState()) == BoolCodeEnum.YES ? "启用" : "禁用");
				}
				outResultList.add(docExportVo);
			}
			ListExcelWriter listExcelWriter = new ListExcelWriter(xlsTemplatePath);
			listExcelWriter.fillToFile(outResultList, xlsOutputPath);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
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
	public List<ErrorLog> importData(String localFileId) {
		BaseExcelReader<SupplierDocExcelVo> excelReader = new BaseExcelReader<SupplierDocExcelVo>() {

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

				if(valueMap.get(SupplierDoc.STATE+"Name")!=null){
					valueMap.put(SupplierDoc.STATE, BoolCodeEnum.fromValue("启用".equals(valueMap.get(SupplierDoc.STATE+"Name"))).toCode());
				}

				if(valueMap.get(SupplierDoc.UNIT_NATURE+"Name")!=null){
					valueMap.put(SupplierDoc.UNIT_NATURE, valueMap.get(SupplierDoc.UNIT_NATURE+"Name").equals(UnitNatureCodeEnum.WHOLESALE_ENTERPRISE.toName()) ? UnitNatureCodeEnum.WHOLESALE_ENTERPRISE.toCode() : UnitNatureCodeEnum.MANUFACTURER.toCode());
				}

				if(valueMap.get(SupplierDoc.BUSINESS_CATEGORY+"Name")!=null){
					valueMap.put(SupplierDoc.BUSINESS_CATEGORY, convertToCodes(businessCategoryMap, (String) valueMap.get(SupplierDoc.BUSINESS_CATEGORY+"Name")));
				}
				if(valueMap.get(SupplierDoc.BUSINESS_RANGE+"Name")!=null){
					valueMap.put(SupplierDoc.BUSINESS_RANGE, convertToCodes(businessRangeMap, (String) valueMap.get(SupplierDoc.BUSINESS_RANGE+"Name")));
				}
				return valueMap;
			}

			@Override
			public boolean verifyExt(int lineNumber, Collection<String> protocolList, Map<String, Object> valueMap, List<ErrorLog> errorLogList) {
				return true;
			}

			@Override
			protected Class getBeanClass() {
				return SupplierDocExcelVo.class;
			}
		};

		List<ErrorLog> errorLogs = new ArrayList<ErrorLog>();
		List<SupplierDocExcelVo> inputList = excelReader.importData(localFileId, errorLogs);
		int index = 1;
		for (SupplierDocExcelVo bean : inputList) {
			DefaultTransactionDefinition td = new DefaultTransactionDefinition();
			td.setName("Process Import SupplierDoc" + index);
			index++;
			//使用底层数据库默认隔离级别。
			td.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
			//支持现有的事务，如果没有则新建一个事务。
			td.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			TransactionStatus txs = jpaTransactionManager.getTransaction(td);
			try {
				//提交事务
				SupplierDoc supplierDoc = new SupplierDoc();
				CommonUtil.copyProperties(bean, supplierDoc);
				supplierDoc.setCreateDate(new Date());
				List<SupplierDoc> dbSupplierDoc = getSupplierDocRepository().findBySupplierNm(supplierDoc.getSupplierNm());
				//如果导入的编码为空，则判断对象是否存在。优先使用已存在对象编码
				if(StringUtils.isBlank(supplierDoc.getSupplierCode())){
					supplierDoc.setSupplierCode(CollectionUtils.isEmpty(dbSupplierDoc)?DateSerialGenerator.genSerialCode(DateSerialGenerator.SUPPLIER_PREFIX):dbSupplierDoc.get(0).getSupplierCode());
				}
				if(CollectionUtils.isNotEmpty(dbSupplierDoc)){
					supplierDoc.setId(dbSupplierDoc.get(0).getId());
				}
				supplierDoc.setPinyin(Pinyin4jUtil.getPinYinHeadChar(supplierDoc.getSupplierNm()));

				save(supplierDoc);
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




}