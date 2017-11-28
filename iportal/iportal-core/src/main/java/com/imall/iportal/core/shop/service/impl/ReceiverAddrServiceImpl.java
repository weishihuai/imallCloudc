package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.entity.ReceiverAddr;
import com.imall.iportal.core.shop.repository.ReceiverAddrRepository;
import com.imall.iportal.core.shop.service.ReceiverAddrService;
import com.imall.iportal.core.shop.vo.ReceiverAddrSaveVo;
import com.imall.iportal.core.shop.vo.ReceiverAddrUpdateVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 收货 地址(服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class ReceiverAddrServiceImpl extends AbstractBaseService<ReceiverAddr, Long> implements ReceiverAddrService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private ReceiverAddrRepository getReceiverAddrRepository() {
		return (ReceiverAddrRepository) baseRepository;
	}

	@Override
	@Transactional
	public Long saveAddr(ReceiverAddrSaveVo saveVo) {
		ReceiverAddr receiverAddr = CommonUtil.copyProperties(saveVo, new ReceiverAddr());
		receiverAddr.setIsDefault(BoolCodeEnum.NO.toCode());
		return this.save(receiverAddr).getId();
	}

	@Override
	@Transactional
	public Long updateAddr(ReceiverAddrUpdateVo updateVo) {
		ReceiverAddr receiverAddr = getReceiverAddrRepository().findByIdAndWeChatUserId(updateVo.getId(), updateVo.getWeChatUserId());
		if (receiverAddr == null) {
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobal.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobal.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"收货地址"}));
		}
		receiverAddr.setAddrLat(updateVo.getAddrLat());
		receiverAddr.setAddrLng(updateVo.getAddrLng());
		receiverAddr.setContactTel(updateVo.getContactTel());
		receiverAddr.setDeliveryAddr(updateVo.getDeliveryAddr());
		receiverAddr.setDetailAddr(updateVo.getDetailAddr());
		receiverAddr.setReceiverName(updateVo.getReceiverName());
		receiverAddr.setPositionName(updateVo.getPositionName());
		receiverAddr.setCityName(updateVo.getCityName());
		this.save(receiverAddr);
		return updateVo.getId();
	}

	@Override
	public List<ReceiverAddr> findByWeChatUserId(Long weChatUserId) {
		return getReceiverAddrRepository().findByWeChatUserId(weChatUserId);
	}

	@Override
	@Transactional
	public void deleteByIdAndWeChatUserId(Long id, Long weChatUserId) {
		getReceiverAddrRepository().deleteByIdAndWeChatUserId(id, weChatUserId);
	}

	@Override
	public ReceiverAddr findByIdAndWeChatUserId(Long id, Long weChatUserId) {
		return getReceiverAddrRepository().findByIdAndWeChatUserId(id, weChatUserId);
	}

	@Override
	public ReceiverAddr findDefaultByWeChatUserId(Long weChatUserId) {
		return getReceiverAddrRepository().findByWeChatUserIdAndIsDefault(weChatUserId);
	}
}