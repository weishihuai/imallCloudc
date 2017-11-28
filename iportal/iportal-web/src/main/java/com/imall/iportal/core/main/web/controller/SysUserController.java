package com.imall.iportal.core.main.web.controller;

import com.imall.commons.base.global.Global;
import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.main.log.annotation.LogWriter;
import com.imall.iportal.core.main.log.dicts.LogTypeCodeEnum;
import com.imall.iportal.core.main.valid.SysUserSaveValid;
import com.imall.iportal.core.main.valid.SysUserUpdateValid;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.main.vo.SysUserBatchStateVo;
import com.imall.iportal.core.main.vo.SysUserSimpleVo;
import com.imall.iportal.core.main.vo.UserParamsVo;
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
import java.util.List;

/**
 * Created by Donie on 2015/8/19.
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserController extends BaseRestSpringController {


    @Autowired
    private PasswordHelper passwordHelper;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(ModelMap model, @CurrUser CurrUserVo currUserVo, @PageableDefault(page = 0, size = 10) Pageable pageable, UserParamsVo userParamsVo) throws ParseException {
        userParamsVo.setOrgId(currUserVo.getOrgId());
        return ServiceManager.sysUserService.query(pageable, userParamsVo);
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public Object findById(Long id, @CurrUser CurrUserVo currUserVo) {
        return ServiceManager.sysUserService.findById(id, currUserVo.getOrgId());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(ModelMap model, @CurrUser CurrUserVo currUserVo, @RequestBody SysUserSaveValid sysUserSaveValid) {
        sysUserSaveValid.setOrgId(currUserVo.getOrgId());
        sysUserSaveValid.setShopId(currUserVo.getShopId());
        //加盐 加密
        sysUserSaveValid.setSalt(passwordHelper.genSalt());
        sysUserSaveValid.setPassword(passwordHelper.encryptPassword(sysUserSaveValid.getPassword(), sysUserSaveValid.getSalt()));
        sysUserSaveValid.setIsDefaultAdmin(BoolCodeEnum.NO.toCode());
        ServiceManager.sysUserService.save(sysUserSaveValid);
        return getSuccess();
    }

    @LogWriter(type = LogTypeCodeEnum.SYS_USER, title = "用户新增或修改", nav = "云C平台 >> 系统管理 >> 用户列表 -> 添加 || 云C平台 >> 系统管理 >> 用户列表 >> ... -> 编辑")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(ModelMap model, @RequestBody SysUserUpdateValid sysUserSaveValid, @CurrUser CurrUserVo currUserVo) {
        sysUserSaveValid.setOrgId(currUserVo.getOrgId());
        ServiceManager.sysUserService.update(sysUserSaveValid);
        return getSuccess();
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(ModelMap model, @RequestBody List<Long> ids) {
        ServiceManager.sysUserService.delete(ids);
        return getSuccess();
    }

    @RequestMapping(value = "/findOne", method = RequestMethod.GET)
    @ResponseBody
    public Object findOne(ModelMap model, Long id) {
        if (id != null) {
            return ServiceManager.sysUserService.findOne(id);
        }
        return getSuccess();
    }

    @RequestMapping(value = "/updateIsEnable", method = RequestMethod.POST)
    @ResponseBody
    public Object updateStatus(ModelMap model, @RequestBody SysUserBatchStateVo sysUserBatchStateVo) {

        ServiceManager.sysUserService.updateIsEnable(sysUserBatchStateVo.getIds(), sysUserBatchStateVo.getIsEnable());
        return getSuccess();
    }

    @RequestMapping(value = "/modifyPassword", method = RequestMethod.POST)
    @ResponseBody
    public Object modifyPassword(ModelMap model, @RequestBody List<Long> ids, @RequestBody String password) {
        for (Long id : ids) {
            SysUser user = new SysUser();
            //加盐 加密
            user.setSalt(passwordHelper.genSalt());
            user.setPassword(passwordHelper.encryptPassword(user.getPassword(), user.getSalt()));
            ServiceManager.sysUserService.modifyPassword(id, user.getPassword(), user.getSalt());
        }
        return getSuccess();
    }

    @RequestMapping(value = "/modifyPasswordOne", method = RequestMethod.POST)
    @ResponseBody
    public Object modifyPasswordOne(ModelMap model, Long id, String password) {
        SysUser user = new SysUser();
        //加盐 加密
        user.setPassword(password);
        user.setSalt(passwordHelper.genSalt());
        user.setPassword(passwordHelper.encryptPassword(user.getPassword(), user.getSalt()));
        ServiceManager.sysUserService.modifyPassword(id, user.getPassword(), user.getSalt());
        return getSuccess();
    }

    //修改当前登录用户的密码
    @RequestMapping(value = "/modifyPasswordByCurrUser", method = RequestMethod.POST)
    @ResponseBody
    public Object modifyPassword(ModelMap model, @CurrUser CurrUserVo currUser, String password) {
        SysUser user = new SysUser();
        //加盐 加密
        user.setPassword(password);
        user.setSalt(passwordHelper.genSalt());
        user.setPassword(passwordHelper.encryptPassword(user.getPassword(), user.getSalt()));
        ServiceManager.sysUserService.modifyPassword(currUser.getUserId(), user.getPassword(), user.getSalt());
        return getSuccess();
    }

    @RequestMapping(value = "/modifyPasswordByUser", method = RequestMethod.POST)
    @ResponseBody
    public Object modifyPasswordByUser(ModelMap model, @CurrUser CurrUserVo currUser, String userName, @RequestBody String password) {
        //todo:需要判断当前登录的管理员有没有对这个用户修改设置密码的权限
        SysUser dbUser = ServiceManager.sysUserService.getByLoginId(userName);
        SysUser user = new SysUser();
        //加盐 加密
        user.setPassword(password);
        user.setSalt(passwordHelper.genSalt());
        user.setPassword(passwordHelper.encryptPassword(user.getPassword(), user.getSalt()));
        ServiceManager.sysUserService.modifyPassword(dbUser.getId(), user.getPassword(), user.getSalt());
        return getSuccess();
    }

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    @ResponseBody
    public Object modifyPasswordByUser(@CurrUser CurrUserVo currUser, String userName, String password) {
        SysUser sysUser = ServiceManager.sysUserService.findByUserName(userName);
        if (sysUser == null) {
            throw new BusinessException(ResGlobal.COMMON_OBJECT_NO_FOUND);
        }
        //仅限当前门店的用户方可修改
        if (!currUser.getOrgId().equals(sysUser.getOrgId())) {
            throw new BusinessException(ResGlobal.COMMON_OBJECT_NO_FOUND);
        }
        String sign = passwordHelper.encryptPassword(password, sysUser.getSalt());
        if (!sysUser.getPassword().equals(sign)) {
            throw new BusinessException(ResGlobal.PASSWORD_ERROR);
        }
        SysUserSimpleVo vo = new SysUserSimpleVo();
        vo.setId(sysUser.getId());
        vo.setUserName(sysUser.getUserName());
        vo.setRealName(sysUser.getRealName());
        return vo;
    }

    @RequestMapping(value = "/findCurrentLoginUserMessage", method = RequestMethod.GET)
    @ResponseBody
    public Object findCurrentLoginUserMessage(ModelMap model, @CurrUser CurrUserVo currUser) {
        return ServiceManager.sysUserService.findCurrentLoginUserMessage(currUser);

    }


    @RequestMapping(value = "/getLoginUserInfo", method = RequestMethod.GET)
    @ResponseBody
    public Object getRealName(@CurrUser CurrUserVo currUserVo) {
        SysUserSimpleVo vo = new SysUserSimpleVo();
        SysUser sysUser = ServiceManager.sysUserService.findOne(currUserVo.getUserId());
        vo.setId(sysUser.getId());
        vo.setUserName(sysUser.getUserName());
        vo.setRealName(sysUser.getRealName());
        return vo;
    }

    @RequestMapping(value = "/checkUserNameIsExist", method = RequestMethod.POST)
    @ResponseBody
    public Object checkUserNameIsExist(@RequestBody SysUser sysUser) {
        return ServiceManager.sysUserService.checkUserNameIsExist(sysUser.getUserName(), sysUser.getId());
    }

    @RequestMapping(value = "/findByShopId", method = RequestMethod.GET)
    @ResponseBody
    public Object findByShopId(@CurrUser CurrUserVo currUserVo, Long shopId, Long orgId) {
        if (!Global.ADMIN_DEFAULT_ORG_ID.equals(currUserVo.getOrgId())) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobal.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobal.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"子公司"}));
        }
        return ServiceManager.sysUserService.findByShopIdAndIsDefaultAdminAndOrgId(shopId, BoolCodeEnum.YES.toCode(), orgId);
    }

    @RequestMapping(value = "/findByLoginShop", method = RequestMethod.GET)
    @ResponseBody
    public Object findByLoginShop(@CurrUser CurrUserVo currUserVo) {
        return ServiceManager.sysUserService.findByShopId(currUserVo.getShopId());
    }

    @RequestMapping(value = "/findByLoginCashier", method = RequestMethod.GET)
    @ResponseBody
    public Object findByLoginCashier(@CurrUser CurrUserVo currUserVo) {
        return ServiceManager.sysUserService.findByLoginCashier(currUserVo.getShopId(), currUserVo.getUserId());
    }

}
