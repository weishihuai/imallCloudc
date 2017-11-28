package com.imall.iportal.sso.service.impl;

import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.util.holder.SpringContextHolder;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysApp;
import com.imall.iportal.shiro.sso.utils.SsoUtil;
import com.imall.iportal.sso.service.SsoService;
import com.imall.iportal.sso.vo.SsoAuthVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by yang on 2015-10-19.
 */
@Service
public class SsoServiceImpl implements SsoService {

    private Cache ticketCache;

    public Cache getTicketCache() {
        if(ticketCache==null){
            ticketCache = ((CacheManager) SpringContextHolder.getBean("ehCacheManager")).getCache("ticket-cache");
        }
        return ticketCache;
    }

    @Override
    public void addAuth(String ticket, SsoAuthVo ssoAuthVo) {
        getTicketCache().put(ticket, ssoAuthVo);
    }

    @Override
    public boolean checkTicketCanUse(String ticket) {
        SsoAuthVo ssoAuthVo = (SsoAuthVo)getTicketCache().get(ticket);
        if(ssoAuthVo==null){   //5分钟过期  || DateUtils.addMinutes(ssoAuthVo.getCreateDate(), 5).before(new Date())
            return false;
        }
        return true;
    }

    @Override
    public SsoAuthVo getByTicket(String ticket) {
        return (SsoAuthVo)getTicketCache().get(ticket);
    }

    @Override
    public boolean checkAppKey(String appKey) {
        SysApp sysApp =  ServiceManager.sysAppService.findByAppKey(appKey);
        if(sysApp!=null && BoolCodeEnum.YES==BoolCodeEnum.fromCode(sysApp.getIsAvailable())){
            return true;
        }
        return false;
    }

    @Override
    public long getExpireIn() {
        return 300000L; //5分钟
    }

    @Override
    public void removeByTicket(String ticket) {
        getTicketCache().remove(ticket);
    }

    @Override
    public boolean checkSign(String appKey, String ssoTicket, String redirectUrl, String sign) throws BusinessException {
        //检查传入的客户端id是否正确
        if (org.apache.commons.lang3.StringUtils.isBlank(appKey) || !checkAppKey(appKey)) {
            throw new BusinessException(ResGlobal.CHECK_IS_TRUST_APP_FAILED); //todo 不是有效的AppKey
        }
        SysApp sysApp = ServiceManager.sysAppService.findByAppKey(appKey);
        SortedMap<String, String> paramMap = new TreeMap<String, String>();
        paramMap.put("appKey", appKey);
        paramMap.put("ticket", ssoTicket);
        paramMap.put("redirectUrl", redirectUrl);
        if(!SsoUtil.md5Hash(paramMap, sysApp.getAppSecret()).equals(sign)){
            return false;  // 参数校验失败
        }
        return true;
    }

    @Override
    public boolean checkSign(String appKey, String ssoTicket, String redirectUrl, String sign, String pos) throws BusinessException {
        //检查传入的客户端id是否正确
        if (org.apache.commons.lang3.StringUtils.isBlank(appKey) || !checkAppKey(appKey)) {
            throw new BusinessException(ResGlobal.CHECK_IS_TRUST_APP_FAILED); //todo 不是有效的AppKey
        }
        SysApp sysApp = ServiceManager.sysAppService.findByAppKey(appKey);
        SortedMap<String, String> paramMap = new TreeMap<String, String>();
        paramMap.put("appKey", appKey);
        paramMap.put("ticket", ssoTicket);
        paramMap.put("redirectUrl", redirectUrl);
        paramMap.put("pos", pos);
        if(!SsoUtil.md5Hash(paramMap, sysApp.getAppSecret()).equals(sign)){
            return false;  // 参数校验失败
        }
        return true;
    }

    @Override
    public boolean checkTicket(String ssoTicket) throws BusinessException{
        if(!StringUtils.hasLength(ssoTicket) || !checkTicketCanUse(ssoTicket)){
            //登录状态，没有ticket或ticket过期，登出
            System.out.println("登录状态，没有ticket或ticket过期，登出");
                /*try {
                    subject.logout(); //todo
                }
                catch (UnknownSessionException unknownSessionException){
                }*/
            return false;
        }
        return true;
    }

    @Override
    public String refreshTicket(String ssoTicket){
        String ticket = ssoTicket;
        Subject subject = SecurityUtils.getSubject();
        SsoAuthVo ssoAuthVo = null;
        if(StringUtils.hasLength(ticket)){
            ssoAuthVo = getByTicket(ticket);
        }
        if(ssoAuthVo==null){
            ticket = UUID.randomUUID().toString();
            ssoAuthVo = new SsoAuthVo();
            ssoAuthVo.setNewTicket(ticket);
            ssoAuthVo.setCreateTime(new Date());
            ssoAuthVo.setUserName((String)subject.getPrincipal());
            ssoAuthVo.setSessionId(subject.getSession().getId().toString());
            addAuth(ticket, ssoAuthVo);
        }
        else {
           /* if(DateUtils.addMinutes(ssoAuthVo.getCreateDate(), 5).before(new Date())){  //5分钟过期
                if(StringUtils.hasLength(ssoAuthVo.getOldTicket())){
                    removeByTicket(ssoAuthVo.getOldTicket());
                }
                ticket = UUID.randomUUID().toString();
                ssoAuthVo.setOldTicket(ssoAuthVo.getNewTicket());
                ssoAuthVo.setNewTicket(ticket);
                ssoAuthVo.setCreateDate(new Date());
                ssoAuthVo.setUserName((String)subject.getPrincipal());
                ssoAuthVo.setSessionId(subject.getSession().getId().toString());
                addAuth(ssoAuthVo.getOldTicket(), ssoAuthVo);
                //新的
                SsoAuthVo newSsoAuthVo = new SsoAuthVo();
                CommonUtil.copyProperties(ssoAuthVo, newSsoAuthVo);
                addAuth(newSsoAuthVo.getNewTicket(), newSsoAuthVo);
            }
            else {*/
                //更新用户信息
                ssoAuthVo.setUserName((String)subject.getPrincipal());
                ssoAuthVo.setSessionId(subject.getSession().getId().toString());
                addAuth(ticket, ssoAuthVo);
           /* }*/
        }
        return ticket;
    }

    @Override
    public List<SsoAuthVo> getAllSsoAuth() {
        Cache cache = getTicketCache();
        return new ArrayList<SsoAuthVo>(cache.values());
    }

}
