package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.DecorationRecommendSaveVo;
import com.imall.iportal.core.shop.vo.DecorationRecommendUpdateVo;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/decorationRecommend")
public class DecorationRecommendController extends BaseRestSpringController {

	@RequestMapping(value = "/saveBatchRecommend",method = RequestMethod.POST)
	@ResponseBody
	public Object saveBatchRecommend(@CurrUser CurrUserVo currUserVo, @RequestBody List<DecorationRecommendSaveVo> saveVoList){
		ServiceManager.decorationRecommendService.saveBatchRecommend(currUserVo.getShopId(), saveVoList);
		return getSuccess();
	}

	@RequestMapping(value = "/query",method = RequestMethod.GET)
	@ResponseBody
	public Object query(@CurrUser CurrUserVo currUserVo, Long decorationGroupId){
		return ServiceManager.decorationRecommendService.findByDecorationGroupIdAndShopId(decorationGroupId, currUserVo.getShopId());
	}

	@RequestMapping(value = "/deleteBatch",method = RequestMethod.POST)
	@ResponseBody
	public Object deleteBatch(@CurrUser CurrUserVo currUserVo, @RequestBody List<Long> ids){
		ServiceManager.decorationRecommendService.deleteBatch(currUserVo.getShopId(), ids);
		return getSuccess();
	}

	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@ResponseBody
	public Object update(@CurrUser CurrUserVo currUserVo, @RequestBody List<DecorationRecommendUpdateVo> updateVoList){
		ServiceManager.decorationRecommendService.updateBatchRecommend(currUserVo.getShopId(), updateVoList);
		return getSuccess();
	}

	@RequestMapping(value = "/listRecommendGoods",method = RequestMethod.GET)
	@ResponseBody
	public Object listRecommendGoods(@CurrUser CurrUserVo currUserVo, Long groupId){
		return ServiceManager.decorationRecommendService.listRecommendGoods(currUserVo.getShopId(), groupId);
	}

}

