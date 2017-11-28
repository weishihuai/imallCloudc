package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.ConsultGoodsInf;
import com.imall.iportal.core.shop.entity.ConsultService;
import com.imall.iportal.core.shop.repository.ConsultServiceRepository;
import com.imall.iportal.core.shop.service.ConsultServiceService;
import com.imall.iportal.core.shop.vo.ConsultGoodsInfDetailVo;
import com.imall.iportal.core.shop.vo.ConsultServiceDetailVo;
import com.imall.iportal.core.shop.vo.ConsultServiceSaveVo;
import com.imall.iportal.core.shop.vo.ConsultServiceSearchParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class ConsultServiceServiceImpl extends AbstractBaseService<ConsultService, Long> implements ConsultServiceService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private ConsultServiceRepository getConsultServiceRepository() {
		return (ConsultServiceRepository) baseRepository;
	}

	@Override
	@Transactional
	public Long save(ConsultServiceSaveVo saveVo) {
		ConsultService consultService = CommonUtil.copyProperties(saveVo, new ConsultService());
		Long consultServiceId = save(consultService).getId();
		for(Long goodsId : saveVo.getGoodsIdList()) {
			ConsultGoodsInf goodsInf = new ConsultGoodsInf();
			goodsInf.setGoodsId(goodsId);
			goodsInf.setConsultServiceId(consultServiceId);
			ServiceManager.consultGoodsInfService.save(goodsInf);
		}
		return consultServiceId;
	}

	@Override
	public Page<ConsultService> query(Pageable pageable, ConsultServiceSearchParam searchParam){
		return getConsultServiceRepository().query(pageable, searchParam);
	}

	@Override
	public ConsultServiceDetailVo findById(Long shopId, Long id) {
		ConsultService consultService = findOne(id);
		if(consultService == null || !consultService.getShopId().equals(shopId)) {
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
		}

		List<ConsultGoodsInfDetailVo> detailVoList = ServiceManager.consultGoodsInfService.findDetailByConsultServiceId(id);
		ConsultServiceDetailVo detailVo = CommonUtil.copyProperties(consultService, new ConsultServiceDetailVo());
		detailVo.setGoodsList(detailVoList);
		return detailVo;
	}
}