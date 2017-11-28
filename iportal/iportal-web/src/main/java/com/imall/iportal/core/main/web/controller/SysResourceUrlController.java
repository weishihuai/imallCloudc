package com.imall.iportal.core.main.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.valid.SysResourceUrlSaveValid;
import com.imall.iportal.core.main.valid.SysResourceUrlUpdateValid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2015/9/25.
 */
@Controller
@RequestMapping("/sysResourceUrl")
public class SysResourceUrlController extends BaseRestSpringController {


    /**
     * 删除资源URL
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Object delete(ModelMap model,@RequestBody List<Long> ids) {
        ServiceManager.sysResourceUrlService.delete(ids);
        return getSuccess();
    }

    /**
     * 获取资源的所有Url
     * @param model
     * @param pageable
     * @param url
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list(ModelMap model,@PageableDefault(page = 0, size = 10) Pageable pageable,Long resourceId , String url) {
        return ServiceManager.sysResourceUrlService.query(pageable,resourceId,url);
    }

    /**
     * 保存资源Url
     * @param model
     * @param sysResourceUrlSaveValid
     * @return
     */
    @RequestMapping(value = "/saveResourceUrl",method = RequestMethod.POST)
    @ResponseBody
    public Object saveResourceUrl(ModelMap model, @RequestBody SysResourceUrlSaveValid sysResourceUrlSaveValid) {
        ServiceManager.sysResourceUrlService.save(sysResourceUrlSaveValid);
        return getSuccess();
    }

    /**
     * 更新资源Url
     * @param model
     * @param sysResourceUrlUpdateValid
     * @return
     */
    @RequestMapping(value = "/updateResourceUrl",method = RequestMethod.POST)
    @ResponseBody
    public Object updateResourceUrl(ModelMap model, @RequestBody SysResourceUrlUpdateValid sysResourceUrlUpdateValid) {
        ServiceManager.sysResourceUrlService.update(sysResourceUrlUpdateValid);
        return getSuccess();
    }
}
