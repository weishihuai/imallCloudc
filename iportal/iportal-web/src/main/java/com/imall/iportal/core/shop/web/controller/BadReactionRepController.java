package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.vo.BadReactionRegSearchParam;
import com.imall.iportal.core.shop.vo.BadReactionRepSaveVo;
import com.imall.iportal.core.shop.vo.BadReactionRepUpdateVo;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
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
@RequestMapping("/badReactionRep")
public class BadReactionRepController extends BaseRestSpringController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object query(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, BadReactionRegSearchParam badReactionRegSearchParam, @CurrUser CurrUserVo currUserVo) throws ParseException {
        badReactionRegSearchParam.setShopId(currUserVo.getShopId());
        return ServiceManager.badReactionRepService.query(pageable, badReactionRegSearchParam);
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public Object findById(Long id, @CurrUser CurrUserVo currUserVo) {
        return ServiceManager.badReactionRepService.findById(id, currUserVo.getShopId());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Object saveBadReactionRep(@RequestBody BadReactionRepSaveVo badReactionRepSaveVo, @CurrUser CurrUserVo currUserVo) throws ParseException {
        badReactionRepSaveVo.setShopId(currUserVo.getShopId());
        ServiceManager.badReactionRepService.saveBadReactionRep(badReactionRepSaveVo);
        return getSuccess();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object updateBadReactionRep(@RequestBody BadReactionRepUpdateVo badReactionRepUpdateVo, @CurrUser CurrUserVo currUserVo) throws ParseException {
        badReactionRepUpdateVo.setShopId(currUserVo.getShopId());
        ServiceManager.badReactionRepService.updateBadReactionRep(badReactionRepUpdateVo);
        return getSuccess();
    }

}

