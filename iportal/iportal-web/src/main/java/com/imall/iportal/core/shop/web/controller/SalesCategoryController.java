package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.SalesCategorySaveVo;
import com.imall.iportal.core.shop.vo.SalesCategorySearchParam;
import com.imall.iportal.core.shop.vo.SalesCategoryUpdateVo;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/salesCategory")
public class SalesCategoryController extends BaseRestSpringController {


	@RequestMapping(value = "/findById",method = RequestMethod.GET)
	@ResponseBody
	public Object findById(Long id, @CurrUser CurrUserVo currUserVo){
		return ServiceManager.salesCategoryService.findById(currUserVo.getShopId(), id);
	}

	@RequestMapping(value = "/query",method = RequestMethod.GET)
	@ResponseBody
	public Object query(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, SalesCategorySearchParam salesCategorySearchParam, @CurrUser CurrUserVo currUserVo){
		salesCategorySearchParam.setShopId(currUserVo.getShopId());
		return ServiceManager.salesCategoryService.query(pageable, salesCategorySearchParam);
	}

	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public Object save(@RequestBody SalesCategorySaveVo salesCategorySaveVo, @CurrUser CurrUserVo currUserVo){
		salesCategorySaveVo.setShopId(currUserVo.getShopId());
		ServiceManager.salesCategoryService.save(salesCategorySaveVo);
		return getSuccess();
	}

	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@ResponseBody
	public Object update(@RequestBody SalesCategoryUpdateVo salesCategoryUpdateVo){
		ServiceManager.salesCategoryService.update(salesCategoryUpdateVo);
		return getSuccess();
	}

	@RequestMapping(value = "/listForGoodsAdd",method = RequestMethod.GET)
	@ResponseBody
	public Object list(ModelMap model, @CurrUser CurrUserVo currUserVo){
		return ServiceManager.salesCategoryService.listForGoodsAdd(currUserVo.getShopId());
	}

	@RequestMapping(value = "/checkSalesCategory", method = RequestMethod.GET)
	@ResponseBody
	public Object checkSalesCategory(Long id, String categoryName, @CurrUser CurrUserVo currUserVo){
		return ServiceManager.salesCategoryService.checkSalesCategory(id, currUserVo.getShopId(), categoryName);
	}
}

