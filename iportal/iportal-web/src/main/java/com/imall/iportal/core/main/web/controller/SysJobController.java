package com.imall.iportal.core.main.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysJob;
import com.imall.iportal.core.main.valid.SysJobSaveValid;
import com.imall.iportal.core.main.valid.SysJobUpdateValid;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
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
 * Created by Donie on 2015/10/9.
 */
@Controller
@RequestMapping("/sysJob")
public class SysJobController extends BaseRestSpringController {

    @RequestMapping(value = "/findPage",method = RequestMethod.GET)
    @ResponseBody
    public Object findPage(ModelMap model, @CurrUser CurrUserVo currUserVo, @PageableDefault(page = 0, size = 10) Pageable pageable, String jobName, String jobCode, String isAvailable){
        return  ServiceManager.sysJobService.query(pageable, currUserVo.getOrgId());
    }

    @RequestMapping(value = "/findOne",method = RequestMethod.GET)
    @ResponseBody
    public Object findOne(Long id){
        return ServiceManager.sysJobService.findOne(id);
    }
    @RequestMapping(value = "/findByOrgId",method = RequestMethod.GET)
    @ResponseBody
    public Object findByOrgId(@CurrUser CurrUserVo currUserVo){
        return ServiceManager.sysJobService.findByOrgId(currUserVo.getOrgId());
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public Object save(@CurrUser CurrUserVo currUserVo, @RequestBody SysJobSaveValid sysJobSaveValid){
        sysJobSaveValid.setOrgId(currUserVo.getOrgId());
        sysJobSaveValid.setIsDefaultAdmin(BoolCodeEnum.NO.toCode());
        ServiceManager.sysJobService.save(sysJobSaveValid);
        return getSuccess();
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Object update(@CurrUser CurrUserVo currUserVo,@RequestBody SysJobUpdateValid sysJobUpdateValid){
        sysJobUpdateValid.setOrgId(currUserVo.getOrgId());
        ServiceManager.sysJobService.update(sysJobUpdateValid);
        return getSuccess();
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public  Object delete(@RequestBody List<Long> ids){
        ServiceManager.sysJobService.deleteSysJob(ids);
        return getSuccess();
    }

    @RequestMapping(value = "/checkJobCodeIsExist", method = RequestMethod.POST)
    @ResponseBody
    public Object checkJobCodeIsExist(@RequestBody SysJob sysJob) {
        return ServiceManager.sysJobService.checkJobCodeIsExist(sysJob.getJobCode(), sysJob.getId());
    }

}
