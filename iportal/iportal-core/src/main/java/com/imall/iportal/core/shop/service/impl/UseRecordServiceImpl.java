package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.FacilityAndDeviceAccounts;
import com.imall.iportal.core.shop.entity.UseRecord;
import com.imall.iportal.core.shop.repository.UseRecordRepository;
import com.imall.iportal.core.shop.service.UseRecordService;
import com.imall.iportal.core.shop.vo.UseRecordDetailVo;
import com.imall.iportal.core.shop.vo.UseRecordPageVo;
import com.imall.iportal.core.shop.vo.UseRecordSaveVo;
import com.imall.iportal.core.shop.vo.UseRecordSearchParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class UseRecordServiceImpl extends AbstractBaseService<UseRecord, Long> implements UseRecordService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private UseRecordRepository getUseRecordRepository() {
		return (UseRecordRepository) baseRepository;
	}

	@Override
	@Transactional
	public void save(UseRecordSaveVo saveVo) {
		UseRecord useRecord = new UseRecord();
		BeanUtils.copyProperties(saveVo, useRecord);
		useRecord.setRecordDate(new Date());
		this.save(useRecord);
	}

	@Override
	public Page<UseRecordPageVo> query(Pageable pageable, UseRecordSearchParam useRecordSearchParam) {
		return this.getUseRecordRepository().query(pageable, useRecordSearchParam);
	}

	@Override
	public UseRecordDetailVo findById(Long shopId, Long id) {
		UseRecord useRecord = this.getUseRecordRepository().findOne(shopId, id);
		if(useRecord==null){
			logger.error("对象不存在，shopId:" + shopId + ", id:" + id);
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"设施设备使用记录"}));
		}else{
			FacilityAndDeviceAccounts facilityAndDeviceAccounts = ServiceManager.facilityAndDeviceAccountsService.findOne(shopId, useRecord.getFacilityAndDeviceAccountsId());
			UseRecordDetailVo useRecordDetailVo = new UseRecordDetailVo();
			CommonUtil.copyProperties(facilityAndDeviceAccounts, useRecordDetailVo);
			CommonUtil.copyProperties(useRecord, useRecordDetailVo);
			return useRecordDetailVo;
		}
	}
}