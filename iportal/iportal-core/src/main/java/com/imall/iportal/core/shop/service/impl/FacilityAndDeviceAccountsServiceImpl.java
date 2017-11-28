package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.FacilityAndDeviceAccounts;
import com.imall.iportal.core.shop.repository.FacilityAndDeviceAccountsRepository;
import com.imall.iportal.core.shop.service.FacilityAndDeviceAccountsService;
import com.imall.iportal.core.shop.vo.FacilityAndDeviceAccountsPageVo;
import com.imall.iportal.core.shop.vo.FacilityAndDeviceAccountsSaveVo;
import com.imall.iportal.core.shop.vo.FacilityAndDeviceAccountsSearchParam;
import com.imall.iportal.core.shop.vo.FacilityAndDeviceAccountsUpdateVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class FacilityAndDeviceAccountsServiceImpl extends AbstractBaseService<FacilityAndDeviceAccounts, Long> implements FacilityAndDeviceAccountsService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private FacilityAndDeviceAccountsRepository getFacilityAndDeviceAccountsRepository() {
		return (FacilityAndDeviceAccountsRepository) baseRepository;
	}

	@Override
	@Transactional
	public void save(FacilityAndDeviceAccountsSaveVo saveVo) {
		if(this.getFacilityAndDeviceAccountsRepository().findByDeviceNum(saveVo.getShopId(), saveVo.getDeviceNum())!=null){
			logger.error("设备编号已经存在，shopId:" + saveVo.getShopId() + ", deviceNum:" + saveVo.getDeviceNum());
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.DEVICE_NUM_IS_EXIST_ERROR);
			throw new BusinessException(ResGlobalExt.DEVICE_NUM_IS_EXIST_ERROR, message);
		}

		FacilityAndDeviceAccounts facilityAndDeviceAccounts = new FacilityAndDeviceAccounts();
		CommonUtil.copyProperties(saveVo, facilityAndDeviceAccounts);
		this.save(facilityAndDeviceAccounts);
	}

	@Override
	public Page<FacilityAndDeviceAccountsPageVo> query(Pageable pageable, FacilityAndDeviceAccountsSearchParam searchParam) {
		return this.getFacilityAndDeviceAccountsRepository().query(pageable, searchParam);
	}

	@Override
	@Transactional
	public void update(FacilityAndDeviceAccountsUpdateVo updateVo) {
		FacilityAndDeviceAccounts history = this.getFacilityAndDeviceAccountsRepository().findByDeviceNum(updateVo.getShopId(), updateVo.getDeviceNum());
		if(history!=null && !history.getId().equals(updateVo.getId())){
			logger.error("设备编号已经存在，shopId:" + updateVo.getShopId() + ", deviceNum:" + updateVo.getDeviceNum());
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.DEVICE_NUM_IS_EXIST_ERROR);
			throw new BusinessException(ResGlobalExt.DEVICE_NUM_IS_EXIST_ERROR, message);
		}

		FacilityAndDeviceAccounts facilityAndDeviceAccounts = this.getFacilityAndDeviceAccountsRepository().findOne(updateVo.getShopId(), updateVo.getId());
		if(facilityAndDeviceAccounts==null){
			logger.error("对象不存在，shopId:" + updateVo.getShopId() + ", id:" + updateVo.getId());
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"设施设备台账"}));
		}

		CommonUtil.copyProperties(updateVo, facilityAndDeviceAccounts);
		this.update(facilityAndDeviceAccounts);
	}

	@Override
	public Boolean checkDeviceNum(Long shopId, Long id, String deviceNum) {
		FacilityAndDeviceAccounts history = this.getFacilityAndDeviceAccountsRepository().findByDeviceNum(shopId, deviceNum);
		if(history==null || history.getId().equals(id)){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public FacilityAndDeviceAccounts findOne(Long shopId, Long id) {
		return this.getFacilityAndDeviceAccountsRepository().findOne(shopId, id);
	}
}