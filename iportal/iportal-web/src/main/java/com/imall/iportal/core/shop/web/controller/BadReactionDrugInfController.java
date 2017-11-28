package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/badReactionDrugInf")
public class BadReactionDrugInfController extends BaseRestSpringController {

    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Object saveOrUpdate() {
        return null;
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public Object query(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return null;
    }

    @RequestMapping(value = "/findByBadReactionRepId", method = RequestMethod.GET)
    @ResponseBody
    public Object findByBadReactionRepId(Long id) {
        return ServiceManager.badReactionDrugInfService.findByBadReactionRepId(id);
    }

}

