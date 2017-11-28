package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.DrugCombination;
import com.imall.iportal.core.shop.repository.DrugCombinationRepository;
import com.imall.iportal.core.shop.service.DrugCombinationService;
import com.imall.iportal.core.shop.vo.*;
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
public class DrugCombinationServiceImpl extends AbstractBaseService<DrugCombination, Long> implements DrugCombinationService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private DrugCombinationRepository getDrugCombinationRepository() {
		return (DrugCombinationRepository) baseRepository;
	}

	@Override
	public Boolean checkDrugCombination(Long orgId, Long drugCombinationCategoryId) {
		Integer count = this.getDrugCombinationRepository().countByCategoryId(drugCombinationCategoryId);
		return count > 0;
	}

	@Override
	@Transactional
	public DrugCombination save(DrugCombinationSaveVo drugCombinationSaveVo) {
		DrugCombination drugCombination = new DrugCombination();
		CommonUtil.copyProperties(drugCombinationSaveVo, drugCombination);
		super.save(drugCombination);
		return drugCombination;
	}

	@Override
	public DrugCombinationDetailVo findById(Long id) {
		DrugCombination drugCombination = this.getDrugCombinationRepository().findOne(id);
		if(drugCombination==null){
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"联合用药"}));
		}else{
			DrugCombinationDetailVo drugCombinationDetailVo = new DrugCombinationDetailVo();
			CommonUtil.copyProperties(drugCombination, drugCombinationDetailVo);
			DrugCombinationCategoryDetailVo drugCombinationCategoryDetailVo
					= ServiceManager.drugCombinationCategoryService.findById(drugCombination.getDrugCombinationCategoryId());
			if(drugCombinationCategoryDetailVo!=null){
				drugCombinationDetailVo.setCategoryNm(drugCombinationCategoryDetailVo.getCategoryNm());
			}

			return drugCombinationDetailVo;
		}
	}

	@Override
	public Page<DrugCombinationPageVo> query(Pageable pageable, DrugCombinationSearchParam drugCombinationSearchParam) {
		return this.getDrugCombinationRepository().query(pageable, drugCombinationSearchParam);
	}

	@Override
	@Transactional
	public void delete(Long orgId, Long id) {
		DrugCombination drugCombination = this.getDrugCombinationRepository().findOne(orgId, id);
		if(drugCombination==null){
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"联合用药"}));
		}else{
			this.getDrugCombinationRepository().delete(id);
		}
	}

	@Override
	@Transactional
	public void update(DrugCombinationUpdateVo drugCombinationUpdateVo) {
		DrugCombination drugCombination = this.getDrugCombinationRepository().findOne(drugCombinationUpdateVo.getOrgId(), drugCombinationUpdateVo.getId());
		if(drugCombination==null){
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"联合用药"}));
		}else{
			drugCombination.setDrugCombinationCategoryId(drugCombinationUpdateVo.getDrugCombinationCategoryId());
			drugCombination.setDisease(drugCombinationUpdateVo.getDisease());
			drugCombination.setSymptom(drugCombinationUpdateVo.getSymptom());
			drugCombination.setCommonSense(drugCombinationUpdateVo.getCommonSense());
			drugCombination.setDrugUsePrinciple(drugCombinationUpdateVo.getDrugUsePrinciple());
			drugCombination.setLeadingDrug(drugCombinationUpdateVo.getLeadingDrug());
			drugCombination.setDrugCombination(drugCombinationUpdateVo.getDrugCombination());
			drugCombination.setMajorConcern(drugCombinationUpdateVo.getMajorConcern());
			this.update(drugCombination);
		}
	}
}