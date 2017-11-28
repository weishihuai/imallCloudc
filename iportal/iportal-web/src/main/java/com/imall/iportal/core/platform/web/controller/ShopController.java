package com.imall.iportal.core.platform.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.platform.vo.ShopSaveVo;
import com.imall.iportal.core.platform.vo.ShopSearchParam;
import com.imall.iportal.core.platform.vo.ShopUpdateVo;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import com.imall.iportal.shiro.service.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;

@Controller
@RequestMapping("/shop")
public class ShopController extends BaseRestSpringController {
	@Autowired
	private PasswordHelper passwordHelper;

	@RequestMapping(value = "/findById", method = RequestMethod.GET)
	@ResponseBody
	public Object findById(Long id,Long orgId) {
		return ServiceManager.shopService.findById(id,orgId);
	}

	@RequestMapping(value = "/findByCurrentShop", method = RequestMethod.GET)
	@ResponseBody
	public Object findByCurrentShop(@CurrUser CurrUserVo currUserVo) {
		return ServiceManager.shopService.findById(currUserVo.getShopId(),currUserVo.getOrgId());
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Object save(@RequestBody ShopSaveVo shopSaveVo,@CurrUser CurrUserVo currUserVo) throws ParseException {
		shopSaveVo.setSalt(passwordHelper.genSalt());
		shopSaveVo.setPassword(passwordHelper.encryptPassword(shopSaveVo.getPassword(), shopSaveVo.getSalt()));
		return ServiceManager.shopService.save(shopSaveVo);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@RequestBody ShopUpdateVo shopUpdateVo) throws ParseException {
		return ServiceManager.shopService.update(shopUpdateVo);
	}


	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseBody
	public Object query(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, ShopSearchParam shopSearchParam) throws ParseException {
		return ServiceManager.shopService.queryShop(pageable,shopSearchParam);
	}

	@RequestMapping(value = "/updateShopState", method = RequestMethod.POST)
	@ResponseBody
	public Object updateShopState( Long id, String state) {
		return ServiceManager.shopService.updateShopState(id, state);
	}

}

