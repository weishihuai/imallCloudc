package com.imall.iportal.frontend.wechat.index.controller;

import com.imall.commons.base.util.holder.SpringContextHolder;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysApp;
import com.imall.iportal.frontend.wechat.WechatBaseController;
import com.imall.iportal.frontend.wechat.common.web.WebContextFactory;
import com.imall.iportal.frontend.wechat.index.service.IndexProxyService;
import com.imall.iportal.frontend.wechat.member.proxy.WeChatUserProxy;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lxh on 2017/8/12.
 */
@Controller
@RequestMapping("/wechat")
public class IndexController extends WechatBaseController {

    @RequestMapping(value = "/index.html")
    public String index(ModelMap model){
        /*WeChatUserProxy weChatUserProxy = new WeChatUserProxy();
        weChatUserProxy.setId(1L);
        weChatUserProxy.setOpenId("46541653rfg34");
        weChatUserProxy.setMember(false);
        weChatUserProxy.setMobile("18899737769");
        weChatUserProxy.setIsSubscribe(BoolCodeEnum.NO.toCode());
        weChatUserProxy.setQrCodeTicket(ServiceManager.weChatApiService.getShopPromoteQRCodeTicket(QRCodeTypeCodeEnum.QR_STR_SCENE, "", null));
        WebContextFactory.getWebContext().setFrontEndUser(weChatUserProxy);

        WebContextFactory.getWebContext().setShopId(1L); //todo:选择下单微店*/

        WeChatUserProxy weChatUserProxy = new WeChatUserProxy();
        weChatUserProxy.setId(6L);
        weChatUserProxy.setOpenId("obOSOwoB0Mlo13uMNFE_0rShhsoo");
        weChatUserProxy.setMember(false);
        weChatUserProxy.setMobile("18899737769");
        weChatUserProxy.setIsSubscribe(BoolCodeEnum.NO.toCode());
//        weChatUserProxy.setQrCodeTicket(ServiceManager.weChatApiService.getShopPromoteQRCodeTicket(QRCodeTypeCodeEnum.QR_STR_SCENE, "", null));
        WebContextFactory.getWebContext().setFrontEndUser(weChatUserProxy);

        model.addAttribute("sysAppContexts", initSysAppContexts());
        return "wechat/index";
    }

    @RequestMapping(value = "/agentError.html")
    public String agentError(ModelMap model){
        return "wechat/agentError";
    }

    @RequestMapping(value = "/findShop", method = RequestMethod.GET)
    @ResponseBody
    public Object findShop(Double lat, Double lng){
        return ((IndexProxyService)SpringContextHolder.getBean(IndexProxyService.class)).findShop(lat, lng);
    }

    @RequestMapping(value = "/findSupportDeliveryZone", method = RequestMethod.GET)
    @ResponseBody
    public Object findSupportDeliveryZone(Double lat, Double lng){
        return ((IndexProxyService)SpringContextHolder.getBean(IndexProxyService.class)).findSupportDeliveryZone();
    }

    @RequestMapping(value = "/findByCityName", method = RequestMethod.GET)
    @ResponseBody
    public Object findByCityName(String cityName){
        return ((IndexProxyService)SpringContextHolder.getBean(IndexProxyService.class)).findByCityName(cityName);
    }

    @RequestMapping(value = "/findNearByShop", method = RequestMethod.GET)
    @ResponseBody
    public Object findNearByShop(Double lat, Double lng){
        return ((IndexProxyService)SpringContextHolder.getBean(IndexProxyService.class)).findNearByShop(lat, lng);
    }

    @RequestMapping(value = "/findShopById", method = RequestMethod.GET)
    @ResponseBody
    public Object findShopById(Long weShopId){
        return ((IndexProxyService)SpringContextHolder.getBean(IndexProxyService.class)).findShopById(weShopId);
    }

    @RequestMapping(value = "/findRecommendGroup", method = RequestMethod.GET)
    @ResponseBody
    public Object findRecommendGroup(){
        return ((IndexProxyService)SpringContextHolder.getBean(IndexProxyService.class)).findRecommendGroup();
    }

    @RequestMapping(value = "/queryRecommendGoods", method = RequestMethod.GET)
    @ResponseBody
    public Object queryRecommendGoods(@PageableDefault(page = 0, size = 5) Pageable pageable, Long groupId){
        return ((IndexProxyService)SpringContextHolder.getBean(IndexProxyService.class)).queryRecommendGoods(pageable, groupId);
    }

    @RequestMapping(value = "/findSwitchShop", method = RequestMethod.GET)
    @ResponseBody
    public Object findSwitchShop(Double lat, Double lng){
        return ((IndexProxyService)SpringContextHolder.getBean(IndexProxyService.class)).findSwitchShop(lat, lng);
    }

    private Map<String, String> initSysAppContexts(){
        String cacheKey = "front_sysAppContexts";
        Map<String, String> sysAppContexts = (Map<String, String>) WebContextFactory.getWebContext().getSessionAttr(cacheKey);
        if(sysAppContexts == null){
            sysAppContexts = new HashMap<>();
            List<SysApp> sysAppList = ServiceManager.sysAppService.findAll();
            //注意，不能把appKey和appSecret外泄
            for(SysApp dbApp : sysAppList){
                if (BoolCodeEnum.YES == BoolCodeEnum.fromCode(dbApp.getIsAvailable())){
                    sysAppContexts.put(dbApp.getWebContext(), dbApp.getHostname());
                }
            }
            WebContextFactory.getWebContext().setSessionAttr(cacheKey, sysAppContexts);
        }
        return sysAppContexts;
    }
}
