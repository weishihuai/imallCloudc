package com.imall.iportal.core.platform.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.platform.vo.GoodsCategorySaveVo;
import com.imall.iportal.core.platform.vo.GoodsCategoryUpdateVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/goodsCategory")
public class GoodsCategoryController extends BaseRestSpringController {

    @RequestMapping(value = "/findOne", method = RequestMethod.GET)
    @ResponseBody
    public Object findOne(Long id) {
        return ServiceManager.goodsCategoryService.findOne(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody GoodsCategorySaveVo goodsCategorySaveVo) {
        return ServiceManager.goodsCategoryService.saveGoodsCategory(goodsCategorySaveVo);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@RequestBody GoodsCategoryUpdateVo goodsCategoryUpdateVo) {
        ServiceManager.goodsCategoryService.updateGoodsCategory(goodsCategoryUpdateVo);
        return getSuccess();
    }

    @RequestMapping(value = "/listRoots", method = RequestMethod.GET)
    @ResponseBody
    public Object query() {
        return ServiceManager.goodsCategoryService.listRoots();
    }

    @RequestMapping(value = "/updateDelete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@RequestBody Long id) {
        ServiceManager.goodsCategoryService.updateDelete(id);
        return getSuccess();
    }

    @RequestMapping(value = "/findAllCategories", method = RequestMethod.GET)
    @ResponseBody
    public Object findAllCategories() {
        return ServiceManager.goodsCategoryService.findAllCategories();
    }

    @RequestMapping(value = "/findByParentId", method = RequestMethod.GET)
    @ResponseBody
    public Object findByParentId(Long id, Integer subMaxLayer) {
        return ServiceManager.goodsCategoryService.findByParentId(id, subMaxLayer);
    }

    @RequestMapping(value = "/isGoodsCategoryExist", method = RequestMethod.GET)
    @ResponseBody
    public Object isGoodsCategoryExist(String categoryName,Long id) {
        return ServiceManager.goodsCategoryService.findGoodsCategoryExist(categoryName, id);
    }


}

