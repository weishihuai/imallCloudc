package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/decorationRecommendGroup")
public class DecorationRecommendGroupController extends BaseRestSpringController {

	@RequestMapping(value = "/findOne",method = RequestMethod.GET)
	@ResponseBody
	public Object findOne(@CurrUser CurrUserVo currUserVo, Long id){
		return ServiceManager.decorationRecommendGroupService.findByIdAndShopId(id, currUserVo.getShopId());
	}

	@RequestMapping(value = "/findByShopId",method = RequestMethod.GET)
	@ResponseBody
	public Object findByShopId(@CurrUser CurrUserVo currUserVo){
		return ServiceManager.decorationRecommendGroupService.findByShopId(currUserVo.getShopId());
	}

	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	@ResponseBody
	public Object delete(@CurrUser CurrUserVo currUserVo, Long id){
		ServiceManager.decorationRecommendGroupService.deleteByIdAndShopId(id, currUserVo.getShopId());
		return getSuccess();
	}

	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@ResponseBody
	public Object update(@CurrUser CurrUserVo currUserVo, Long id, String groupNm){
		ServiceManager.decorationRecommendGroupService.updateDecorationRecommendGroup(currUserVo.getShopId(), id, groupNm);
		return getSuccess();
	}

	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public Object save(@CurrUser CurrUserVo currUserVo, String groupNm){
		ServiceManager.decorationRecommendGroupService.saveDecorationRecommendGroup(currUserVo.getShopId(), groupNm);
		return getSuccess();
	}
}

