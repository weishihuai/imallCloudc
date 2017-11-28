package com.imall.iportal.frontend.wechat.member.service;

import com.imall.commons.base.util.CommonUtil;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.entity.ReceiverAddr;
import com.imall.iportal.core.shop.entity.WeShop;
import com.imall.iportal.core.shop.vo.ReceiverAddrSaveVo;
import com.imall.iportal.core.shop.vo.ReceiverAddrUpdateVo;
import com.imall.iportal.frontend.wechat.common.utils.MapDistanceUtils;
import com.imall.iportal.frontend.wechat.common.web.WebContextFactory;
import com.imall.iportal.frontend.wechat.member.proxy.ReceiverAddrProxy;
import com.imall.iportal.frontend.wechat.member.proxy.WeChatUserProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxh on 2017/8/14.
 */
@Service
@Transactional(readOnly = true)
public class ReceiveAddrProxyService {

    @Transactional
    public void saveReceiveAddr(ReceiverAddrSaveVo saveVo){
        WeChatUserProxy weChatUserProxy = WebContextFactory.getWebContext().getFrontEndUser();
        saveVo.setWeChatUserId(weChatUserProxy.getId());
        saveVo.setOpenId(weChatUserProxy.getOpenId());
        ServiceManager.receiverAddrService.saveAddr(saveVo);
    }

    @Transactional
    public void updateReceiveAddr(ReceiverAddrUpdateVo updateVo){
        Long weChatUserId = WebContextFactory.getWebContext().getFrontEndUserId();
        updateVo.setWeChatUserId(weChatUserId);
        ServiceManager.receiverAddrService.updateAddr(updateVo);
    }

    @Transactional
    public void deleteReceiveAddr(Long id){
        Long weChatUserId = WebContextFactory.getWebContext().getFrontEndUserId();
        ServiceManager.receiverAddrService.deleteByIdAndWeChatUserId(id, weChatUserId);
    }

    public List<ReceiverAddrProxy> findByWeChatUserId(){
        Long weChatUserId = WebContextFactory.getWebContext().getFrontEndUserId();
        List<ReceiverAddr> receiverAddrList = ServiceManager.receiverAddrService.findByWeChatUserId(weChatUserId);
        List<ReceiverAddrProxy> proxyList = new ArrayList<>();
        for (ReceiverAddr receiverAddr : receiverAddrList){
            proxyList.add(CommonUtil.copyProperties(receiverAddr, new ReceiverAddrProxy()));
        }
        return proxyList;
    }

    public ReceiverAddrProxy findOne(Long id){
        Long weChatUserId = WebContextFactory.getWebContext().getFrontEndUserId();
        return CommonUtil.copyProperties(ServiceManager.receiverAddrService.findByIdAndWeChatUserId(id, weChatUserId), new ReceiverAddrProxy());
    }

    public List<ReceiverAddrProxy> cartListAddr(){
        Long shopId = WebContextFactory.getWebContext().getShopId();
        WeShop weShop = ServiceManager.weShopService.findByShopId(shopId);
        List<ReceiverAddr> list = ServiceManager.receiverAddrService.findByWeChatUserId(WebContextFactory.getWebContext().getFrontEndUserId());
        List<ReceiverAddrProxy> proxyList = new ArrayList<>();
        for (ReceiverAddr addr : list){
            ReceiverAddrProxy proxy = CommonUtil.copyProperties(addr, new ReceiverAddrProxy());
            Double distinct = MapDistanceUtils.distance(addr.getAddrLng(), addr.getAddrLat(), weShop.getShopLng(), weShop.getShopLat());
            proxy.setInDeliveryRange(distinct <= weShop.getDeliveryRange());
            proxyList.add(proxy);
        }
        return proxyList;
    }
}
