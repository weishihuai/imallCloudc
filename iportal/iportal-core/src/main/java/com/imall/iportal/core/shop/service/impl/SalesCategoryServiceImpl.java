package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.idfs.FileSystemEngine;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.commons.dicts.FileObjectTypeCodeEnum;
import com.imall.commons.dicts.FileTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.FileMng;
import com.imall.iportal.core.main.valid.FileMngSaveValid;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.SalesCategory;
import com.imall.iportal.core.shop.repository.SalesCategoryRepository;
import com.imall.iportal.core.shop.service.SalesCategoryService;
import com.imall.iportal.core.shop.vo.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 销售 分类(服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class SalesCategoryServiceImpl extends AbstractBaseService<SalesCategory, Long> implements SalesCategoryService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private String categoryName = "";

	@Value("${app.shop.salesCategory.categoryName}")
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@SuppressWarnings("unused")
    private SalesCategoryRepository getSalesCategoryRepository() {
		return (SalesCategoryRepository) baseRepository;
	}

	@Override
	@Transactional
	public SalesCategory save(SalesCategorySaveVo salesCategorySaveVo) {
		String categoryName = salesCategorySaveVo.getCategoryName().trim();
		SalesCategory history = this.getSalesCategoryRepository().findByCategoryName(salesCategorySaveVo.getShopId(), categoryName);
		if(history!=null){
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_IS_EXIST_ERROR);
			throw new BusinessException(ResGlobalExt.COMMON_IS_EXIST_ERROR, String.format(message, new Object[]{categoryName}));
		}

		SalesCategory salesCategory = new SalesCategory();
		CommonUtil.copyProperties(salesCategorySaveVo, salesCategory);
		salesCategory.setCategoryName(categoryName);
		//保存销售分类信息
		this.save(salesCategory);

		//保存图片信息
		if(StringUtils.isNotBlank(salesCategorySaveVo.getPictFileId()) && salesCategorySaveVo.getSysFileLibId()!=null){
			FileMngSaveValid fileMngSaveValid = new FileMngSaveValid();
			fileMngSaveValid.setObjectTypeCode(FileObjectTypeCodeEnum.SALES_CATEGORY_PICT.toCode());
			fileMngSaveValid.setObjectId(salesCategory.getId());
			fileMngSaveValid.setSysFileLibId(salesCategorySaveVo.getSysFileLibId());
			fileMngSaveValid.setFileId(salesCategorySaveVo.getPictFileId());
			ServiceManager.fileMngService.save(FileObjectTypeCodeEnum.SALES_CATEGORY_PICT, salesCategory.getId(), fileMngSaveValid);
		}

		return salesCategory;
	}

	@Override
	@Transactional
	public SalesCategory update(SalesCategoryUpdateVo salesCategoryUpdateVo) {
		String categoryName = salesCategoryUpdateVo.getCategoryName().trim();
		SalesCategory history = this.getSalesCategoryRepository().findByCategoryName(salesCategoryUpdateVo.getShopId(), categoryName);
		if(history!=null && !history.getId().equals(salesCategoryUpdateVo.getId())){
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_IS_EXIST_ERROR);
			throw new BusinessException(ResGlobalExt.COMMON_IS_EXIST_ERROR, String.format(message, new Object[]{categoryName}));
		}

		//更新销售分类信息
		SalesCategory salesCategory = this.getSalesCategoryRepository().findOne(salesCategoryUpdateVo.getShopId(), salesCategoryUpdateVo.getId());
		salesCategory.setCategoryName(salesCategoryUpdateVo.getCategoryName());
		salesCategory.setDispalyPosition(salesCategoryUpdateVo.getDispalyPosition());
		salesCategory.setIsEnable(salesCategoryUpdateVo.getIsEnable());
		salesCategory.setIsFrontendShow(salesCategoryUpdateVo.getIsFrontendShow());
		this.update(salesCategory);

		//更新图片信息
		if(StringUtils.isNotBlank(salesCategoryUpdateVo.getNewPictFileId()) && salesCategoryUpdateVo.getNewSysFileLibId()!=null) {
			FileMng fileMng = new FileMng();
			fileMng.setObjectTypeCode(FileObjectTypeCodeEnum.SALES_CATEGORY_PICT.toCode());
			fileMng.setObjectId(salesCategory.getId());
			fileMng.setSysFileLibId(salesCategoryUpdateVo.getNewSysFileLibId());
			fileMng.setFileId(salesCategoryUpdateVo.getNewPictFileId());
			fileMng.setFileTypeCode(FileTypeCodeEnum.IMAGE.toCode());
			ServiceManager.fileMngService.saveAndDeleteOld(FileObjectTypeCodeEnum.SALES_CATEGORY_PICT, salesCategory.getId(), fileMng);
		}else if(StringUtils.isBlank(salesCategoryUpdateVo.getPictFileId()) && salesCategoryUpdateVo.getSysFileLibId()==null){
			ServiceManager.fileMngService.delete(FileObjectTypeCodeEnum.SALES_CATEGORY_PICT, salesCategory.getId());
		}

		return salesCategory;
	}

	@Override
	public SalesCategoryDetailVo findById(Long shopId, Long id) {
		SalesCategory salesCategory = this.getSalesCategoryRepository().findOne(shopId, id);
		if(salesCategory == null){
			String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
			throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"销售分类"}));
		}

		SalesCategoryDetailVo salesCategoryDetailVo = CommonUtil.copyProperties(salesCategory, new SalesCategoryDetailVo());
		//获取图片信息
		List<FileMng> fileMngList = ServiceManager.fileMngService.getList(FileObjectTypeCodeEnum.SALES_CATEGORY_PICT, salesCategory.getId());
		if(CollectionUtils.isNotEmpty(fileMngList)){
			FileMng fileMng = fileMngList.get(0);
            salesCategoryDetailVo.setPictFileId(fileMng.getFileId());
            salesCategoryDetailVo.setSysFileLibId(fileMng.getSysFileLibId());
			salesCategoryDetailVo.setPictUrl(FileSystemEngine.getFileSystem().getUrl(fileMng.getFileId()));
		}

		return salesCategoryDetailVo;
	}

	@Override
	public Page<SalesCategoryPageVo> query(Pageable pageable, SalesCategorySearchParam salesCategorySearchParam) {
		return getSalesCategoryRepository().query(pageable, salesCategorySearchParam);
	}

	@Override
	@Transactional
	public void saveSalesCategoryForShopCreate(Long shopId) {
		String[] categoryNames = this.categoryName.split(",");
		Long index = 1L;
		for(String categoryName : categoryNames){
			SalesCategory salesCategory = new SalesCategory();
			salesCategory.setIsFrontendShow(BoolCodeEnum.YES.toCode());
			salesCategory.setIsEnable(BoolCodeEnum.YES.toCode());
			salesCategory.setDispalyPosition(index);
			salesCategory.setCategoryName(categoryName);
			salesCategory.setShopId(shopId);
			super.save(salesCategory);

			index++;
		}
	}

	@Override
	public List<SalesCategorySimpleVo> listForGoodsAdd(Long shopId) {
		List<SalesCategory> salesCategories = getSalesCategoryRepository().findByShopIdAndIsEnable(shopId,BoolCodeEnum.YES.toCode());
		List<SalesCategorySimpleVo> salesCategorySimpleVos = new ArrayList<>();
		if(salesCategories!=null){
			for(SalesCategory salesCategory:salesCategories){
				salesCategorySimpleVos.add(CommonUtil.copyProperties(salesCategory,new SalesCategorySimpleVo()));
			}
		}
		return salesCategorySimpleVos;
	}

	@Override
	public Boolean checkSalesCategory(Long id, Long shopId, String categoryName) {
		SalesCategory history = this.getSalesCategoryRepository().findByCategoryName(shopId, categoryName);
		return history != null && (!history.getId().equals(id));
	}

	@Override
	public List<SalesCategorySimpleVo> listSalesCategory(Long shopId, String isEnable, String isFrontendShow) {
		List<SalesCategory> salesCategories = getSalesCategoryRepository().listSalesCategory(shopId, isEnable, isFrontendShow);
		List<SalesCategorySimpleVo> voList = new ArrayList<>();
		for (SalesCategory salesCategory : salesCategories){
			voList.add(CommonUtil.copyProperties(salesCategory, new SalesCategorySimpleVo()));
		}
		return voList;
	}
}