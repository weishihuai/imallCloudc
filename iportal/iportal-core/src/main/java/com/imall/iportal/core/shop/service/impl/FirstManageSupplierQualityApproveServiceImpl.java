package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.ApproveStateCodeEnum;
import com.imall.commons.dicts.UnitNatureCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.platform.entity.Shop;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.FirstManageSupplierQualityApprove;
import com.imall.iportal.core.shop.entity.Supplier;
import com.imall.iportal.core.shop.entity.SupplierCertificatesFile;
import com.imall.iportal.core.shop.repository.FirstManageSupplierQualityApproveRepository;
import com.imall.iportal.core.shop.service.FirstManageSupplierQualityApproveService;
import com.imall.iportal.core.shop.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class FirstManageSupplierQualityApproveServiceImpl extends AbstractBaseService<FirstManageSupplierQualityApprove, Long> implements FirstManageSupplierQualityApproveService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private FirstManageSupplierQualityApproveRepository getFirstManageSupplierQualityApproveRepository() {
		return (FirstManageSupplierQualityApproveRepository) baseRepository;
	}

	@Override
	public Page<FirstManageSupplierQualityApprovePageVo> queryFirstManageSupplierQualityApprove(Pageable pageable, FirstManageSupplierQualityApproveSearchParam firstManageSupplierQualityApproveSearchParam) throws ParseException {
		Page<FirstManageSupplierQualityApprove> firstManageSupplierQualityApprovePage = getFirstManageSupplierQualityApproveRepository().query(pageable, firstManageSupplierQualityApproveSearchParam);
		List<FirstManageSupplierQualityApprovePageVo> firstManageSupplierQualityApprovePageVos = new ArrayList<>();
		for (FirstManageSupplierQualityApprove firstManageSupplierQualityApprove : firstManageSupplierQualityApprovePage.getContent()) {
			firstManageSupplierQualityApprovePageVos.add(lazyBuild(firstManageSupplierQualityApprove));
		}
		return new PageImpl<FirstManageSupplierQualityApprovePageVo>(firstManageSupplierQualityApprovePageVos, new PageRequest(firstManageSupplierQualityApprovePage.getNumber(),firstManageSupplierQualityApprovePage.getSize()), firstManageSupplierQualityApprovePage.getTotalElements());
	}

	@Override
	@Transactional
	public Long saveFirstManageSupplierQualityApprove(FirstManageSupplierQualityApproveSaveVo firstManageSupplierQualityApproveSaveVo) {
		FirstManageSupplierQualityApprove firstManageSupplierQualityApprove1 = CommonUtil.copyProperties(firstManageSupplierQualityApproveSaveVo, new FirstManageSupplierQualityApprove());
		firstManageSupplierQualityApprove1.setApproveStateCode(ApproveStateCodeEnum.WAIT_APPROVE.toCode());
		return save(firstManageSupplierQualityApprove1).getId();
	}

	@Override
	@Transactional
	public Long updateApproveStateCode(Long firstManageSupplierQualityApproveId, String approveStateCode) {
		FirstManageSupplierQualityApprove firstManageSupplierQualityApprove = findOne(firstManageSupplierQualityApproveId);
		if (firstManageSupplierQualityApprove == null) {
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
		}
		firstManageSupplierQualityApprove.setApproveStateCode(org.apache.commons.lang.StringUtils.isNotBlank(approveStateCode) ? ApproveStateCodeEnum.fromCode(approveStateCode).toCode() : null);
		return save(firstManageSupplierQualityApprove).getId();
	}

	@Override
	@Transactional
	public Long updateQualityApproveTime(Long firstManageSupplierQualityApproveId) throws ParseException {
		FirstManageSupplierQualityApprove firstManageSupplierQualityApprove = findOne(firstManageSupplierQualityApproveId);
		if (firstManageSupplierQualityApprove == null) {
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
		}
		firstManageSupplierQualityApprove.setQualityApproveTime(DateTimeUtils.convertDateToString(new Date()));
		return save(firstManageSupplierQualityApprove).getId();

	}

	private FirstManageSupplierQualityApprovePageVo lazyBuild(FirstManageSupplierQualityApprove firstManageSupplierQualityApprove){
		FirstManageSupplierQualityApprovePageVo firstManageSupplierQualityApprovePageVo = CommonUtil.copyProperties(firstManageSupplierQualityApprove, new FirstManageSupplierQualityApprovePageVo());
		Supplier supplier = ServiceManager.supplierService.findOne(firstManageSupplierQualityApprovePageVo.getSupplierId());
		if (supplier == null) {
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
		}
		firstManageSupplierQualityApprovePageVo.setSupplierCode(supplier.getSupplierCode());
		firstManageSupplierQualityApprovePageVo.setSupplierNm(supplier.getSupplierNm());
		firstManageSupplierQualityApprovePageVo.setLegalRepresentative(supplier.getLegalRepresentative());
		firstManageSupplierQualityApprovePageVo.setQualityResponseManName(supplier.getQualityResponseManName());
		firstManageSupplierQualityApprovePageVo.setBusinessResponseManName(supplier.getBusinessResponseManName());
		Shop shop = ServiceManager.shopService.findOne(firstManageSupplierQualityApprovePageVo.getShopId());
		firstManageSupplierQualityApprovePageVo.setTaxRegisterCertNum(shop.getTaxRegisterCertNum());
		SupplierCertificatesFile supplierCertificatesFile= ServiceManager.supplierCertificatesFileService.findBySupplierIdAndCertificatesType(supplier.getId(), "BUSINESS_LICENSE");
		firstManageSupplierQualityApprovePageVo.setBusinessLicense(supplierCertificatesFile.getCertificatesNum());
		firstManageSupplierQualityApprovePageVo.setBusinessLicenseTimeString(DateTimeUtils.convertDateToString(supplierCertificatesFile.getCertificatesValidity()));
		firstManageSupplierQualityApprovePageVo.setApproveStateName(StringUtils.isNotBlank(firstManageSupplierQualityApprove.getApproveStateCode())?ApproveStateCodeEnum.fromCode(firstManageSupplierQualityApprove.getApproveStateCode()).toName():null);
		firstManageSupplierQualityApprovePageVo.setApproveStateCode(firstManageSupplierQualityApprove.getApproveStateCode());
		firstManageSupplierQualityApprovePageVo.setQualityApproveTimeString(firstManageSupplierQualityApprove.getQualityApproveTime());
		return firstManageSupplierQualityApprovePageVo;
	}
	@Override
	public FirstManageSupplierQualityApproveDetailVo findById(Long id,Long shopId) {
		FirstManageSupplierQualityApprove firstManageSupplierQualityApprove = findOne(id);
		if (firstManageSupplierQualityApprove == null||!firstManageSupplierQualityApprove.getShopId().equals(shopId)) {
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
		}
		FirstManageSupplierQualityApproveDetailVo firstManageSupplierQualityApproveDetailVo = CommonUtil.copyProperties(firstManageSupplierQualityApprove, new FirstManageSupplierQualityApproveDetailVo());
		//获取供应商数据
		Supplier supplier = ServiceManager.supplierService.findOne(firstManageSupplierQualityApprove.getSupplierId());
		firstManageSupplierQualityApproveDetailVo.setSupplierNm(supplier.getSupplierNm());
		firstManageSupplierQualityApproveDetailVo.setUnitNatureName(StringUtils.isNotBlank(supplier.getUnitNature())? UnitNatureCodeEnum.fromCode(supplier.getUnitNature()).toName():null);
		firstManageSupplierQualityApproveDetailVo.setQualityResponseManName(supplier.getQualityResponseManName());
		firstManageSupplierQualityApproveDetailVo.setLegalRepresentative(supplier.getLegalRepresentative());
		firstManageSupplierQualityApproveDetailVo.setBusinessResponseManName(supplier.getBusinessResponseManName());
		firstManageSupplierQualityApproveDetailVo.setBusinessResponseManTel(supplier.getBusinessResponseManTel());
		firstManageSupplierQualityApproveDetailVo.setBusinessResponseManTel(supplier.getBusinessResponseManTel());
		firstManageSupplierQualityApproveDetailVo.setBusinessResponseManEmail(supplier.getBusinessResponseManEmail());
		firstManageSupplierQualityApproveDetailVo.setRegCapital(supplier.getRegCapital());
		firstManageSupplierQualityApproveDetailVo.setRegAddr(supplier.getRegAddr());
		firstManageSupplierQualityApproveDetailVo.setFax(supplier.getFax());
		firstManageSupplierQualityApproveDetailVo.setReturnedPurchaseAddr(supplier.getReturnedPurchaseAddr());
		firstManageSupplierQualityApproveDetailVo.setBusinessRange(supplier.getBusinessRange());
		firstManageSupplierQualityApproveDetailVo.setBusinessCategory(supplier.getBusinessCategory());
		//获取供应商 资质文件
		List<SupplierCertificatesFileVo> supplierCertificatesFileVos= ServiceManager.supplierCertificatesFileService.listSupplierCertificatesFilesList(supplier.getId());
		firstManageSupplierQualityApproveDetailVo.setSupplierCertificatesFileList(supplierCertificatesFileVos);
		firstManageSupplierQualityApproveDetailVo.setApplyMan(supplier.getApplyMan());
		firstManageSupplierQualityApproveDetailVo.setApplyDateString(DateTimeUtils.convertDateToString(supplier.getApplyDate()));
		firstManageSupplierQualityApproveDetailVo.setSubmitOpinion(supplier.getSubmitOpinion());
		firstManageSupplierQualityApproveDetailVo.setRemark(supplier.getRemark());
		//获取 供应商 首营审核数据
		List<FirstManageSupplierQualityApproveInfDetailVo> firstManageSupplierQualityApproveInfDetailVos= ServiceManager.firstManageSupplierQualityApproveInfService.findByFirstManageSupplierQualityApproveId(firstManageSupplierQualityApprove.getId());
		firstManageSupplierQualityApproveDetailVo.setFirstManageSupplierQualityApproveInfDetailVos(firstManageSupplierQualityApproveInfDetailVos);
		return firstManageSupplierQualityApproveDetailVo;
	}
}