package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.global.Global;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.DrugCombinationCategory;
import com.imall.iportal.core.shop.repository.DrugCombinationCategoryRepository;
import com.imall.iportal.core.shop.service.DrugCombinationCategoryService;
import com.imall.iportal.core.shop.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class DrugCombinationCategoryServiceImpl extends AbstractBaseService<DrugCombinationCategory, Long> implements DrugCombinationCategoryService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private DrugCombinationCategoryRepository getDrugCombinationCategoryRepository() {
		return (DrugCombinationCategoryRepository) baseRepository;
	}

	@Override
	@Transactional
	public DrugCombinationCategory save(DrugCombinationCategorySaveVo drugCombinationCategorySaveVo) {
		String categoryNm = drugCombinationCategorySaveVo.getCategoryNm().trim();
		List<DrugCombinationCategory> drugCombinationCategoryList = this.getDrugCombinationCategoryRepository().listByCategoryNm(drugCombinationCategorySaveVo.getOrgId(), categoryNm);
		if(drugCombinationCategoryList.size()==0){
			DrugCombinationCategory drugCombinationCategory = new DrugCombinationCategory();
			drugCombinationCategory.setCategoryNm(categoryNm);
			drugCombinationCategory.setOrgId(drugCombinationCategorySaveVo.getOrgId());
			this.save(drugCombinationCategory);
			return drugCombinationCategory;
		}else{
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_IS_EXIST_ERROR);
			throw new BusinessException(ResGlobalExt.COMMON_IS_EXIST_ERROR, String.format(message, new Object[]{categoryNm}));
		}
	}

	@Override
	public DrugCombinationCategoryDetailVo findById(Long id) {
		DrugCombinationCategory drugCombinationCategory = this.getDrugCombinationCategoryRepository().findOne(id);
		if(drugCombinationCategory==null){
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"联合用药分类"}));
		}

		DrugCombinationCategoryDetailVo drugCombinationCategoryDetailVo = new DrugCombinationCategoryDetailVo();
		CommonUtil.copyProperties(drugCombinationCategory, drugCombinationCategoryDetailVo);
		return drugCombinationCategoryDetailVo;
	}

	@Override
	@Transactional
	public DrugCombinationCategory update(DrugCombinationCategoryUpdateVo drugCombinationCategoryUpdateVo) {
		String categoryNm = drugCombinationCategoryUpdateVo.getCategoryNm().trim();
		List<DrugCombinationCategory> drugCombinationCategoryList = this.getDrugCombinationCategoryRepository().listByCategoryNm(drugCombinationCategoryUpdateVo.getOrgId(), categoryNm);
		if(drugCombinationCategoryList.size()>1 &&
				!drugCombinationCategoryList.get(0).getId().equals(drugCombinationCategoryUpdateVo.getId())){
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_IS_EXIST_ERROR);
			throw new BusinessException(ResGlobalExt.COMMON_IS_EXIST_ERROR, String.format(message, new Object[]{categoryNm}));
		}else{
			DrugCombinationCategory drugCombinationCategory = this.getDrugCombinationCategoryRepository().findOne(drugCombinationCategoryUpdateVo.getOrgId(), drugCombinationCategoryUpdateVo.getId());
			drugCombinationCategory.setCategoryNm(categoryNm);
			this.update(drugCombinationCategory);
			return drugCombinationCategory;
		}
	}

	@Override
	public Page<DrugCombinationCategoryPageVo> query(Pageable pageable, DrugCombinationCategorySearchParam drugCombinationCategorySearchParam) {
		return this.getDrugCombinationCategoryRepository().query(pageable, drugCombinationCategorySearchParam);
	}

	@Override
	public List<DrugCombinationCategoryDetailVo> listAllByOrgId(Long orgId) {
		List<DrugCombinationCategory> drugCombinationCategoryList = this.getDrugCombinationCategoryRepository().listAllByOrgId(orgId, Global.ADMIN_DEFAULT_ORG_ID);
		List<DrugCombinationCategoryDetailVo> drugCombinationCategoryDetailVoList = new ArrayList<>();
		for(DrugCombinationCategory drugCombinationCategory : drugCombinationCategoryList){
			DrugCombinationCategoryDetailVo drugCombinationCategoryDetailVo = new DrugCombinationCategoryDetailVo();
			CommonUtil.copyProperties(drugCombinationCategory, drugCombinationCategoryDetailVo);
			drugCombinationCategoryDetailVoList.add(drugCombinationCategoryDetailVo);
		}
		return drugCombinationCategoryDetailVoList;
	}

	@Override
	@Transactional
	public void delete(Long orgId, Long id) {
		if(ServiceManager.drugCombinationService.checkDrugCombination(orgId, id)){//联合用药分类已经被使用
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.DRUG_COMBINATION_CATEGORY_IS_USED_ERROR);
			throw new BusinessException(ResGlobalExt.DRUG_COMBINATION_CATEGORY_IS_USED_ERROR, message);
		}else{//联合用药分类没有被使用
			super.delete(id);
		}
	}

	@Override
	public Boolean checkByCategoryNm(Long orgId, Long id, String categoryNm) {
		List<DrugCombinationCategory> drugCombinationCategoryList = this.getDrugCombinationCategoryRepository().listByCategoryNm(orgId, categoryNm.trim());
		if(drugCombinationCategoryList.size()>0) {
			DrugCombinationCategory drugCombinationCategory = drugCombinationCategoryList.get(0);
			return id == null || !id.equals(drugCombinationCategory.getId());
		}else {
			return false;
		}
	}
}