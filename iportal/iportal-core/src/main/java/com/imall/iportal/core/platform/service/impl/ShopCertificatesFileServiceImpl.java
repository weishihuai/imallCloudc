package com.imall.iportal.core.platform.service.impl;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.CertificatesFileCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.platform.entity.ShopCertificatesFile;
import com.imall.iportal.core.platform.repository.ShopCertificatesFileRepository;
import com.imall.iportal.core.platform.service.ShopCertificatesFileService;
import com.imall.iportal.core.platform.vo.ShopCertificatesFileSaveVo;
import com.imall.iportal.core.platform.vo.ShopCertificatesFileUpdateVo;
import com.imall.iportal.core.platform.vo.ShopCertificatesFileVo;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class ShopCertificatesFileServiceImpl extends AbstractBaseService<ShopCertificatesFile, Long> implements ShopCertificatesFileService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private ShopCertificatesFileRepository getShopCertificatesFileRepository() {
		return (ShopCertificatesFileRepository) baseRepository;
	}

	@Override
	public List<ShopCertificatesFileVo> ListShopCertificatesFilesList(Long shopId) {
		List<ShopCertificatesFile> shopCertificatesFileServiceList = getShopCertificatesFileRepository().findByShopId(shopId);
		List<ShopCertificatesFileVo> shopCertificatesFileVoList = new ArrayList<>();
		if (CollectionUtils.isEmpty(shopCertificatesFileServiceList)) {
			return shopCertificatesFileVoList;
		}
		for (ShopCertificatesFile shopCertificatesFile : shopCertificatesFileServiceList) {
			ShopCertificatesFileVo shopCertificatesFileVo = CommonUtil.copyProperties(shopCertificatesFile, new ShopCertificatesFileVo());
			shopCertificatesFileVo.setCertificatesValidityString(DateTimeUtils.convertDateToString(shopCertificatesFile.getCertificatesValidity()));
			shopCertificatesFileVoList.add(shopCertificatesFileVo);
		}
		return shopCertificatesFileVoList;
	}

	@Override
	@Transactional
	public Long updateByShopIdAndCertificatesType(ShopCertificatesFileUpdateVo shopCertificatesFileUpdateVo) throws ParseException {
		ShopCertificatesFile shopCertificatesFile = CommonUtil.copyProperties(shopCertificatesFileUpdateVo, new ShopCertificatesFile());
		shopCertificatesFile.setCertificatesValidity(StringUtils.isNotBlank(shopCertificatesFileUpdateVo.getCertificatesValidityString())?DateTimeUtils.convertStringToDate(shopCertificatesFileUpdateVo.getCertificatesValidityString()):null);
		shopCertificatesFile.setCertificatesType(CertificatesFileCodeEnum.fromCode(shopCertificatesFileUpdateVo.getCertificatesType()).toCode());
		return save(shopCertificatesFile).getId();
	}

	@Override
	@Transactional
	public Long saveShopCertificatesFile(ShopCertificatesFileSaveVo shopCertificatesFileSaveVo) throws ParseException {
		ShopCertificatesFile shopCertificatesFile = CommonUtil.copyProperties(shopCertificatesFileSaveVo, new ShopCertificatesFile());
		if(StringUtils.isNotBlank(shopCertificatesFileSaveVo.getCertificatesValidityString())){
				shopCertificatesFile.setCertificatesValidity(DateTimeUtils.convertStringToDate(shopCertificatesFileSaveVo.getCertificatesValidityString()));
		}
		shopCertificatesFile.setCertificatesType(StringUtils.isNotBlank(shopCertificatesFileSaveVo.getCertificatesType())?CertificatesFileCodeEnum.fromCode(shopCertificatesFileSaveVo.getCertificatesType()).toCode():null);
		return save(shopCertificatesFile).getId();
	}

	@Override
	public ShopCertificatesFile findByShopIdAndCertificatesType(Long shopId, String certificatesType) {
		List<ShopCertificatesFile> byShopIdAndCertificatesType = getShopCertificatesFileRepository().findByShopIdAndCertificatesType(shopId, certificatesType);
		if (CollectionUtils.isEmpty(byShopIdAndCertificatesType)) {
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
		}
		return byShopIdAndCertificatesType.get(0);
	}

}