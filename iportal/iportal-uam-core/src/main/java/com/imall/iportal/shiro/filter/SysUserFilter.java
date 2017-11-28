package com.imall.iportal.shiro.filter;

import com.imall.commons.base.global.Global;
import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysJob;
import com.imall.iportal.core.main.entity.SysOrg;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.main.entity.SysUserOrgJob;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.platform.entity.Shop;
import com.imall.iportal.core.shop.entity.WeShop;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by yang on 2015-10-28.
 */
public class SysUserFilter extends PathMatchingFilter {


    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Session session = SecurityUtils.getSubject().getSession();
        CurrUserVo currUserVo = (CurrUserVo)session.getAttribute(Global.CURR_USER);
        //当前登录的用户
        if(currUserVo==null){
            String username = (String)SecurityUtils.getSubject().getPrincipal();
            SysUser sysUser = ServiceManager.sysUserService.getByLoginId(username);
            SysUserOrgJob userOrgJob = ServiceManager.sysUserOrgJobService.getIsmainByUserId(sysUser.getId());
            currUserVo = ServiceManager.sysUserService.getCurrUserVo(sysUser.getId(), userOrgJob.getId());
            session.setAttribute(Global.CURR_USER, currUserVo);
        }
        request.setAttribute(Global.CURR_USER, currUserVo);
        return true;
    }
}