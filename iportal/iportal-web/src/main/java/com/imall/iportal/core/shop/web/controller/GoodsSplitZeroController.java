package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.GoodsSplitZeroSaveVo;
import com.imall.iportal.core.shop.vo.GoodsSplitZeroSearchParam;
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
@RequestMapping("/goodsSplitZero")
public class GoodsSplitZeroController extends BaseRestSpringController {

	@RequestMapping(value = "/findById",method = RequestMethod.GET)
	@ResponseBody
	public Object findById(Long id, @CurrUser CurrUserVo currUserVo){
		return ServiceManager.goodsSplitZeroService.findById(currUserVo.getShopId(), id);
	}

	@RequestMapping(value = "/query",method = RequestMethod.GET)
	@ResponseBody
	public Object query(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, GoodsSplitZeroSearchParam goodsSplitZeroSearchParam, @CurrUser CurrUserVo currUserVo){
		goodsSplitZeroSearchParam.setShopId(currUserVo.getShopId());
		return ServiceManager.goodsSplitZeroService.queryGoodsSplitZero(pageable, goodsSplitZeroSearchParam);
	}

	@RequestMapping(value = "/saveGoodsSplitZero", method = RequestMethod.POST)
	@ResponseBody
	public Object saveGoodsSplitZero(@RequestBody GoodsSplitZeroSaveVo goodsSplitZeroSaveVo, @CurrUser CurrUserVo currUserVo) {
		goodsSplitZeroSaveVo.setShopId(currUserVo.getShopId());
		ServiceManager.goodsSplitZeroService.save(goodsSplitZeroSaveVo);
		return success;
	}
}

