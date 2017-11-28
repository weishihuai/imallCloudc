package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.FacilityAndDeviceAccounts;
import com.imall.iportal.core.shop.entity.MaintainingRecord;
import com.imall.iportal.core.shop.repository.MaintainingRecordRepository;
import com.imall.iportal.core.shop.service.MaintainingRecordService;
import com.imall.iportal.core.shop.vo.MaintainingRecordDetailVo;
import com.imall.iportal.core.shop.vo.MaintainingRecordPageVo;
import com.imall.iportal.core.shop.vo.MaintainingRecordSaveVo;
import com.imall.iportal.core.shop.vo.MaintainingRecordSearchParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class MaintainingRecordServiceImpl extends AbstractBaseService<MaintainingRecord, Long> implements MaintainingRecordService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private MaintainingRecordRepository getMaintainingRecordRepository() {
		return (MaintainingRecordRepository) baseRepository;
	}

	@Override
	@Transactional
	public void save(MaintainingRecordSaveVo saveVo) {
		MaintainingRecord maintainingRecord = new MaintainingRecord();
		BeanUtils.copyProperties(saveVo, maintainingRecord);
		this.save(maintainingRecord);
	}

	@Override
	public Page<MaintainingRecordPageVo> query(Pageable pageable, MaintainingRecordSearchParam maintainingRecordSearchParam) {
		return this.getMaintainingRecordRepository().query(pageable, maintainingRecordSearchParam);
	}

	@Override
	public MaintainingRecordDetailVo findById(Long shopId, Long id) {
		MaintainingRecord maintainingRecord = this.getMaintainingRecordRepository().findOne(shopId, id);
		if(maintainingRecord==null){
			logger.error("对象不存在，shopId:" + shopId + ", id:" + id);
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"设施设备维护记录"}));
		}else{
			FacilityAndDeviceAccounts facilityAndDeviceAccounts = ServiceManager.facilityAndDeviceAccountsService.findOne(shopId, maintainingRecord.getFacilityAndDeviceAccountsId());
			MaintainingRecordDetailVo maintainingRecordDetailVo = new MaintainingRecordDetailVo();
			CommonUtil.copyProperties(facilityAndDeviceAccounts, maintainingRecordDetailVo);
			CommonUtil.copyProperties(maintainingRecord, maintainingRecordDetailVo);
			return maintainingRecordDetailVo;
		}
	}
}