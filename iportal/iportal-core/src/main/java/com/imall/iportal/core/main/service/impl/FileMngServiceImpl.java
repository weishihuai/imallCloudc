package com.imall.iportal.core.main.service.impl;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.FileObjectTypeCodeEnum;
import com.imall.commons.dicts.FileTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.FileMng;
import com.imall.iportal.core.main.entity.SysFileLib;
import com.imall.iportal.core.main.repository.FileMngRepository;
import com.imall.iportal.core.main.service.FileMngService;
import com.imall.iportal.core.main.valid.FileMngSaveValid;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class FileMngServiceImpl extends AbstractBaseService<FileMng, Long> implements FileMngService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private FileMngRepository getFileMngRepository() {
		return (FileMngRepository) baseRepository;
	}

	@Transactional
	@Override
	public void saveList(FileObjectTypeCodeEnum fileObjectTypeCode, Long objectId, List<FileMng> fileMngList) {
		for(FileMng fileMng: fileMngList){
			SysFileLib fileLib = ServiceManager.sysFileLibService.findOne(fileMng.getSysFileLibId());
			//保存的时候附件已经不存在了
			if(fileLib == null){
				String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
				throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"附件"}));
			}
			if(StringUtils.isBlank(fileMng.getFileTypeCode())) {
				fileMng.setFileTypeCode(fileLib.getFileTypeCode());
			}
			fileMng.setFileTypeCode(FileTypeCodeEnum.fromCode(fileMng.getFileTypeCode()).toCode());
			fileMng.setObjectTypeCode(fileObjectTypeCode.toCode());
			fileMng.setObjectId(objectId);
			save(fileMng);
		}
	}

	@Transactional
	@Override
	public FileMng save(FileObjectTypeCodeEnum fileObjectTypeCode, Long objectId, FileMngSaveValid fileMngSaveValid) {
		FileMng fileMng = CommonUtil.copyProperties(fileMngSaveValid, new FileMng());
		if(StringUtils.isBlank(fileMng.getFileTypeCode())) {
			SysFileLib fileLib = ServiceManager.sysFileLibService.findOne(fileMng.getSysFileLibId());
			fileMng.setFileTypeCode(fileLib.getFileTypeCode());
		}
		fileMng.setFileTypeCode(FileTypeCodeEnum.fromCode(fileMng.getFileTypeCode()).toCode());
		fileMng.setObjectTypeCode(fileObjectTypeCode.toCode());
		fileMng.setObjectId(objectId);
		return save(fileMng);
	}

	@Transactional
	@Override
	public void saveListAndDeleteOld(FileObjectTypeCodeEnum fileObjectTypeCode, Long objectId, List<FileMng> fileMngList) {
		delete(fileObjectTypeCode, objectId);
		saveList(fileObjectTypeCode, objectId, fileMngList);
	}

	@Override
	@Transactional
	public void saveAndDeleteOld(FileObjectTypeCodeEnum fileObjectTypeCode, Long objectId, FileMng fileMng) {
		delete(fileObjectTypeCode, objectId);
		fileMng.setObjectId(objectId);
		fileMng.setObjectTypeCode(fileObjectTypeCode.toCode());
		save(fileMng);
	}

	@Override
	@Transactional
	public void saveNewFileAndDeleteOld(FileObjectTypeCodeEnum fileObjectTypeCode, Long objectId, FileMng fileMng) {
		if (!CollectionUtils.isEmpty(this.getList(fileObjectTypeCode,objectId))) {
			delete(fileObjectTypeCode, objectId);
		}
		fileMng.setObjectId(objectId);
		fileMng.setObjectTypeCode(fileObjectTypeCode.toCode());
		save(fileMng);
	}

	@Transactional
	@Override
	public void delete(FileObjectTypeCodeEnum fileObjectTypeCode, Long objectId) {
		getFileMngRepository().delete(fileObjectTypeCode.toCode(), objectId);
	}

	@Override
	public List<FileMng> getList(FileObjectTypeCodeEnum fileObjectTypeCode, Long objectId) {
		return getFileMngRepository().getList(fileObjectTypeCode.toCode(), objectId);
	}

	@Override
	public void deleteBySysFileLibId(FileObjectTypeCodeEnum fileObjectTypeCode, Long sysFileLibId) {
		getFileMngRepository().deleteBySysFileLibId(fileObjectTypeCode.toCode(), sysFileLibId);
	}
}