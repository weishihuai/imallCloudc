package com.imall.iportal.sso.service;

import com.imall.commons.base.interceptor.BusinessException;
import com.imall.iportal.sso.vo.SsoAuthVo;

import java.util.List;

/**
 * Created by yang on 2015-10-19.
 */
public interface SsoService {

    //添加 aticket
    void addAuth(String ticket, SsoAuthVo ssoAuthVo);

    //验证ticket是否有效
    boolean checkTicketCanUse(String ticket);

    SsoAuthVo getByTicket(String ticket);

    //Ticket 过期时间
    long getExpireIn();

    void removeByTicket(String ticket);

    boolean checkAppKey(String appKey);

    boolean checkSign(String appKey, String ssoTicket, String redirectUrl, String sign) throws BusinessException;

    boolean checkSign(String appKey, String ssoTicket, String redirectUrl, String sign, String pos) throws BusinessException;

    boolean checkTicket(String ssoTicket) throws BusinessException;

    String refreshTicket(String ssoTicket);

    List<SsoAuthVo> getAllSsoAuth();
}
