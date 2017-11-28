package com.imall.iportal.frontend.wechat.goods.service;

import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.vo.WeChatGoodsDetailVo;
import com.imall.iportal.core.shop.vo.WeChatGoodsListSearchParam;
import com.imall.iportal.core.shop.vo.WeChatGoodsPageVo;
import com.imall.iportal.frontend.wechat.common.web.WebContextFactory;
import com.imall.iportal.frontend.wechat.goods.proxy.GoodsDetailProxy;
import com.imall.iportal.frontend.wechat.goods.proxy.GoodsPageProxy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caidapao on 2017/8/12.
 */
@Service
@Transactional(readOnly = true)
public class GoodsProxyService {

    public Page<GoodsPageProxy> query(Pageable pageable, WeChatGoodsListSearchParam weChatGoodsListSearchParam) {
        Long shopId = WebContextFactory.getWebContext().getShopId();
        if (shopId == null){
            return new PageImpl<GoodsPageProxy>(new ArrayList<GoodsPageProxy>(), pageable, 0L);
        }
        Page<WeChatGoodsPageVo> weChatGoodsPageVoList = ServiceManager.goodsService.queryForWeChat(pageable,weChatGoodsListSearchParam,shopId);
        List<GoodsPageProxy> goodsPageProxies = new ArrayList<>();
        for(WeChatGoodsPageVo weChatGoodsPageVo:weChatGoodsPageVoList.getContent()){
            goodsPageProxies.add(buildPageProxy(weChatGoodsPageVo));
        }
        return new PageImpl<GoodsPageProxy>(goodsPageProxies,pageable,weChatGoodsPageVoList.getTotalElements());
    }

    private GoodsPageProxy buildPageProxy(WeChatGoodsPageVo weChatGoodsPageVo) {
        GoodsPageProxy goodsPageProxy = new GoodsPageProxy();
        goodsPageProxy.setImgUrl(weChatGoodsPageVo.getImgUrl());
        goodsPageProxy.setRetailPrice(weChatGoodsPageVo.getRetailPrice());
        goodsPageProxy.setCommonNm(weChatGoodsPageVo.getCommonNm());
        goodsPageProxy.setId(weChatGoodsPageVo.getId());
        goodsPageProxy.setIsPrescriptionDrugs(weChatGoodsPageVo.getIsPrescriptionDrugs());
        goodsPageProxy.setSpec(weChatGoodsPageVo.getSpec());
        goodsPageProxy.setMemberPrice(weChatGoodsPageVo.getMemberPrice());
        goodsPageProxy.setSkuId(weChatGoodsPageVo.getSkuId());
        goodsPageProxy.setIsMember(WebContextFactory.getWebContext().getFrontendUserIsMember()? BoolCodeEnum.YES.toCode():BoolCodeEnum.NO.toCode());
        goodsPageProxy.setSkuId(weChatGoodsPageVo.getSkuId());
        goodsPageProxy.setGoodsNm(weChatGoodsPageVo.getGoodsNm());
        return goodsPageProxy;
    }

    public GoodsDetailProxy findDetail(Long id) {
        WeChatGoodsDetailVo weChatGoodsDetailVo = ServiceManager.goodsService.findDetailForWeChat(id);
        GoodsDetailProxy goodsDetailProxy = new GoodsDetailProxy();
        goodsDetailProxy.setIsSellTime(weChatGoodsDetailVo.getIsSellTime());
        goodsDetailProxy.setIsPrescriptionDrugs(weChatGoodsDetailVo.getIsPrescriptionDrugs());
        goodsDetailProxy.setShopLng(weChatGoodsDetailVo.getShopLng());
        goodsDetailProxy.setShopLat(weChatGoodsDetailVo.getShopLat());
        goodsDetailProxy.setId(weChatGoodsDetailVo.getId());
        goodsDetailProxy.setSpec(weChatGoodsDetailVo.getSpec());
        goodsDetailProxy.setCommonNm(weChatGoodsDetailVo.getCommonNm());
        goodsDetailProxy.setShopNm(weChatGoodsDetailVo.getShopNm());
        goodsDetailProxy.setImgUrlList(weChatGoodsDetailVo.getImgUrlList());
        goodsDetailProxy.setDetailLocation(weChatGoodsDetailVo.getDetailLocation());
        goodsDetailProxy.setRetailPrice(weChatGoodsDetailVo.getRetailPrice());
        goodsDetailProxy.setInstructions(weChatGoodsDetailVo.getInstructions());
        goodsDetailProxy.setContactTel(weChatGoodsDetailVo.getContactTel());
        goodsDetailProxy.setMarketPrice(weChatGoodsDetailVo.getMarketPrice());
        goodsDetailProxy.setSkuId(weChatGoodsDetailVo.getSkuId());
        goodsDetailProxy.setWeShopId(weChatGoodsDetailVo.getWeShopId());
        goodsDetailProxy.setGoodsNm(weChatGoodsDetailVo.getGoodsNm());
        Long shopId = WebContextFactory.getWebContext().getShopId();
        goodsDetailProxy.setShowAddCart(shopId != null && shopId.equals(weChatGoodsDetailVo.getShopId()));
        goodsDetailProxy.setNormalSales(weChatGoodsDetailVo.isNormalSales());
        goodsDetailProxy.setProduceManufacturer(weChatGoodsDetailVo.getProduceManufacturer());
        return goodsDetailProxy;
    }
}
