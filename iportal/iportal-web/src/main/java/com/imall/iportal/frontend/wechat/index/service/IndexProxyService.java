package com.imall.iportal.frontend.wechat.index.service;

import com.imall.commons.base.idfs.FileSystemEngine;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.commons.dicts.FileObjectTypeCodeEnum;
import com.imall.commons.dicts.GoodsTypeCodeEnum;
import com.imall.commons.dicts.PrescriptionDrugsTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.FileMng;
import com.imall.iportal.core.platform.entity.Shop;
import com.imall.iportal.core.shop.entity.*;
import com.imall.iportal.core.shop.vo.SalesCategorySimpleVo;
import com.imall.iportal.core.weshop.entity.Fans;
import com.imall.iportal.frontend.wechat.common.utils.MapDistanceUtils;
import com.imall.iportal.frontend.wechat.common.web.WebContextFactory;
import com.imall.iportal.frontend.wechat.goods.proxy.GoodsPageProxy;
import com.imall.iportal.frontend.wechat.index.proxy.DecorationRecommendGroupProxy;
import com.imall.iportal.frontend.wechat.index.proxy.IndexShopProxy;
import com.imall.iportal.frontend.wechat.index.proxy.SalesCategoryProxy;
import com.imall.iportal.frontend.wechat.index.proxy.ZoneProxy;
import com.imall.iportal.frontend.wechat.member.proxy.WeChatUserProxy;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxh on 2017/8/12.
 */
@Service
@Transactional(readOnly = true)
public class IndexProxyService {

    /**
     * 定位门店
     * @param lat
     * @param lng
     * @return
     */
    public IndexShopProxy findShop(Double lat, Double lng) {
        WebContextFactory.getWebContext().setShopId(null);
        WeChatUserProxy weChatUserProxy = WebContextFactory.getWebContext().getFrontEndUser();
        List<Fans> fansList = ServiceManager.fansService.findByOpenId(weChatUserProxy.getOpenId());
        IndexShopProxy indexShopProxy = null;
        if (fansList.isEmpty()) {
            //不是门店粉丝，则找出距离最近的门店
            indexShopProxy = this.findMinDistanceShop(lat, lng, true);
        } else {
            for (Fans fans : fansList) {
                Long shopId = fans.getShopId();
                WeShop weShop = ServiceManager.weShopService.findByShopId(shopId);
                if(BoolCodeEnum.NO == BoolCodeEnum.fromCode(weShop.getIsNormalSales())){
                    continue;
                }
                Shop shop = ServiceManager.shopService.findOne(shopId);
                if (shop == null || BoolCodeEnum.NO == BoolCodeEnum.fromCode(shop.getIsEnable())){
                    continue;
                }
                Double distance = MapDistanceUtils.distance(lng, lat, weShop.getShopLng(), weShop.getShopLat());
                //如果在配送范围内
                if (distance < weShop.getDeliveryRange()) {
                    indexShopProxy = this.buildIndexShopProxy(weShop, false);
                    indexShopProxy.setSalesCategoryList(this.getSalesCategory(weShop.getShopId()));
                    WebContextFactory.getWebContext().setShopId(weShop.getShopId());
                    break;
                }
            }
            if (indexShopProxy == null){
                indexShopProxy = this.findMinDistanceShop(lat, lng, true);
            }
        }
        return indexShopProxy == null ? new IndexShopProxy(true) : indexShopProxy;
    }

    private IndexShopProxy findMinDistanceShop(Double lat, Double lng, Boolean enableSwitchShop) {
        IndexShopProxy indexShopProxy = null;
        List<WeShop> list = ServiceManager.weShopService.findByDistance(10D, lat, lng);
        if (CollectionUtils.isNotEmpty(list)) {
            Double minDistance = Double.MAX_VALUE;
            WeShop minDistanceShop = null;
            for (WeShop weShop : list) {
                if(BoolCodeEnum.NO == BoolCodeEnum.fromCode(weShop.getIsNormalSales())){
                    continue;
                }
                Shop shop = ServiceManager.shopService.findOne(weShop.getShopId());
                if (shop == null || BoolCodeEnum.NO == BoolCodeEnum.fromCode(shop.getIsEnable())){
                    continue;
                }
                Double distance = MapDistanceUtils.distance(lng, lat, weShop.getShopLng(), weShop.getShopLat());
                //不在配送范围
                if (distance > weShop.getDeliveryRange()) {
                    continue;
                }
                if (minDistance > distance) {
                    minDistance = distance;
                    minDistanceShop = weShop;
                }
            }
            if (minDistanceShop != null) {
                indexShopProxy = this.buildIndexShopProxy(minDistanceShop, enableSwitchShop);
                indexShopProxy.setSalesCategoryList(this.getSalesCategory(minDistanceShop.getShopId()));
                WebContextFactory.getWebContext().setShopId(minDistanceShop.getShopId());
            }
        }
        return indexShopProxy;
    }

    private IndexShopProxy buildIndexShopProxy(WeShop weShop, Boolean enableSwitchShop) {
        IndexShopProxy indexShopProxy = CommonUtil.copyProperties(weShop, new IndexShopProxy());
        indexShopProxy.setWeShopId(weShop.getId());
        indexShopProxy.setEnableSwitchShop(enableSwitchShop);
        List<FileMng> list = ServiceManager.fileMngService.getList(FileObjectTypeCodeEnum.SHOP_MGR_WE_CHAT_PICT, weShop.getId());
        if (CollectionUtils.isNotEmpty(list)) {
            indexShopProxy.setShopMgrWeChatUrl(FileSystemEngine.getFileSystem().getUrl(list.get(0).getFileId(), "160X160"));
        }
        list = ServiceManager.fileMngService.getList(FileObjectTypeCodeEnum.SHOP_LOGO, weShop.getId());
        if (CollectionUtils.isNotEmpty(list)) {
            indexShopProxy.setLogoUrl(FileSystemEngine.getFileSystem().getUrl(list.get(0).getFileId(), "160X160"));
        }
        return indexShopProxy;
    }

    /**
     * 查询支持配送的城市
     * @return
     */
    public List<ZoneProxy> findSupportDeliveryZone() {
        List<WeShop> list = ServiceManager.weShopService.findDistinctWeShop();
        List<ZoneProxy> zoneProxyList = new ArrayList<>();
        for (WeShop weShop : list) {
            Shop shop = ServiceManager.shopService.findOne(weShop.getShopId());
            if (shop == null || BoolCodeEnum.NO == BoolCodeEnum.fromCode(shop.getIsEnable())){
                continue;
            }
            ZoneProxy zoneProxy = new ZoneProxy();
            zoneProxy.setZoneId(weShop.getShopZoneParent());
            zoneProxy.setZoneName(weShop.getShopZoneParentName());
            zoneProxyList.add(zoneProxy);
        }
        return zoneProxyList;
    }

    /**
     * 根据城市名称查找属于城市的门店
     * @param cityName
     * @return
     */
    public List<IndexShopProxy> findByCityName(String cityName) {
        List<WeShop> list = ServiceManager.weShopService.findByCityName(cityName);
        List<IndexShopProxy> proxyList = new ArrayList<>();
        for (WeShop weShop : list) {
            IndexShopProxy proxy = CommonUtil.copyProperties(weShop, new IndexShopProxy());
            proxy.setWeShopId(weShop.getId());
            List<FileMng> fileMngList = ServiceManager.fileMngService.getList(FileObjectTypeCodeEnum.SHOP_LOGO, weShop.getId());
            if (CollectionUtils.isNotEmpty(fileMngList)) {
                proxy.setLogoUrl(FileSystemEngine.getFileSystem().getUrl(fileMngList.get(0).getFileId(), "160X160"));
            }
            proxyList.add(proxy);
        }
        return proxyList;
    }

    private List<SalesCategoryProxy> getSalesCategory(Long shopId) {
        List<SalesCategorySimpleVo> voList = ServiceManager.salesCategoryService.listSalesCategory(shopId, BoolCodeEnum.YES.toCode(), BoolCodeEnum.YES.toCode());
        List<SalesCategoryProxy> proxyList = new ArrayList<>();
        for (SalesCategorySimpleVo vo : voList) {
            SalesCategoryProxy proxy = CommonUtil.copyProperties(vo, new SalesCategoryProxy());
            List<FileMng> fileMngList = ServiceManager.fileMngService.getList(FileObjectTypeCodeEnum.SALES_CATEGORY_PICT, vo.getId());
            if (CollectionUtils.isNotEmpty(fileMngList)){
                proxy.setLogoUrl(FileSystemEngine.getFileSystem().getUrl(fileMngList.get(0).getFileId(), "100X100"));
            }
            proxyList.add(proxy);
        }
        return proxyList;
    }

    /**
     * 查询附近门店
     * @param lat
     * @param lng
     * @return
     */
    public List<IndexShopProxy> findNearByShop(Double lat, Double lng) {
        List<WeShop> list = ServiceManager.weShopService.findByDistance(10D, lat, lng);
        List<IndexShopProxy> proxyList = new ArrayList<>();
        for (WeShop weShop : list) {
            Shop shop = ServiceManager.shopService.findOne(weShop.getShopId());
            if (shop == null || BoolCodeEnum.NO == BoolCodeEnum.fromCode(shop.getIsEnable())){
                continue;
            }
            Double distance = MapDistanceUtils.distance(lng, lat, weShop.getShopLng(), weShop.getShopLat());
            //不在配送范围
            if (distance > weShop.getDeliveryRange()) {
                continue;
            }
            IndexShopProxy proxy = CommonUtil.copyProperties(weShop, new IndexShopProxy());
            proxy.setWeShopId(weShop.getId());
            List<FileMng> fileMngList = ServiceManager.fileMngService.getList(FileObjectTypeCodeEnum.SHOP_LOGO, weShop.getId());
            if (CollectionUtils.isNotEmpty(fileMngList)) {
                proxy.setLogoUrl(FileSystemEngine.getFileSystem().getUrl(fileMngList.get(0).getFileId(), "160X160"));
            }
            proxyList.add(proxy);
        }
        return proxyList;
    }

    /**
     * 根据主键查询门店
     * @param weShopId
     * @return
     */
    public IndexShopProxy findShopById(Long weShopId) {
        WebContextFactory.getWebContext().setShopId(null);
        if (weShopId == null){
            return new IndexShopProxy();
        }
        WeChatUserProxy weChatUserProxy = WebContextFactory.getWebContext().getFrontEndUser();
        if (weChatUserProxy == null){
            return new IndexShopProxy();
        }
        WeShop weShop = ServiceManager.weShopService.findOne(weShopId);
        if(weShop == null || BoolCodeEnum.NO == BoolCodeEnum.fromCode(weShop.getIsNormalSales())){
            return new IndexShopProxy();
        }
        Shop shop = ServiceManager.shopService.findOne(weShop.getShopId());
        if (shop == null || BoolCodeEnum.NO == BoolCodeEnum.fromCode(shop.getIsEnable())){
            return new IndexShopProxy();
        }
        Fans fans = ServiceManager.fansService.findByOpenIdAndShopId(weChatUserProxy.getOpenId(), weShop.getShopId());
        boolean enableSwitchShop = fans == null || !weShop.getShopId().equals(fans.getShopId());
        IndexShopProxy proxy = this.buildIndexShopProxy(weShop, enableSwitchShop);
        proxy.setSalesCategoryList(this.getSalesCategory(weShop.getShopId()));
        WebContextFactory.getWebContext().setShopId(weShop.getShopId());
        return proxy;
    }

    /**
     * 查询推荐分组
     * @return
     */
    public List<DecorationRecommendGroupProxy> findRecommendGroup() {
        Long shopId = WebContextFactory.getWebContext().getShopId();
        List<DecorationRecommendGroupProxy> proxyList = new ArrayList<>();
        if (shopId == null) {
            return proxyList;
        }
        List<DecorationRecommendGroup> list = ServiceManager.decorationRecommendGroupService.getByShopId(shopId);
        for (DecorationRecommendGroup group : list) {
            proxyList.add(CommonUtil.copyProperties(group, new DecorationRecommendGroupProxy()));
        }
        return proxyList;
    }

    /**
     * 根据分组查询推荐商品
     * @param pageable
     * @param groupId
     * @return
     */
    public Page<GoodsPageProxy> queryRecommendGoods(Pageable pageable, Long groupId) {
        Long shopId = WebContextFactory.getWebContext().getShopId();
        List<GoodsPageProxy> proxyList = new ArrayList<>();
        if (shopId == null || groupId == null) {
            return new PageImpl<GoodsPageProxy>(proxyList, pageable, 0L);
        }
        Page<DecorationRecommend> resultPage = ServiceManager.decorationRecommendService.query(pageable, shopId, groupId);
        Boolean isMember = WebContextFactory.getWebContext().getFrontendUserIsMember();
        for (DecorationRecommend recommend : resultPage.getContent()) {
            GoodsPageProxy proxy = new GoodsPageProxy();
            Goods goods = ServiceManager.goodsService.findOne(recommend.getGoodsId());
            proxy.setCommonNm(goods.getCommonNm());
            proxy.setGoodsNm(goods.getGoodsNm());
            proxy.setSpec(goods.getSpec());
            proxy.setId(recommend.getGoodsId());
            switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())) {
                case DRUG:
                    GoodsDrug drug = ServiceManager.goodsDrugService.findByGoodsId(goods.getId());
                    if (drug != null) {
                        boolean isPrescription = PrescriptionDrugsTypeCodeEnum.fromCode(drug.getPrescriptionDrugsTypeCode()) == PrescriptionDrugsTypeCodeEnum.RX;
                        proxy.setIsPrescriptionDrugs(BoolCodeEnum.toCode(isPrescription));
                    }
                    break;
                case CHINESE_MEDICINE_PIECES:
                    GoodsChineseMedicinePieces pieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goods.getId());
                    if(pieces != null) {
                        boolean isPrescription = PrescriptionDrugsTypeCodeEnum.fromCode(pieces.getPrescriptionDrugsTypeCode()) == PrescriptionDrugsTypeCodeEnum.RX;
                        proxy.setIsPrescriptionDrugs(BoolCodeEnum.toCode(isPrescription));
                    }
                    break;
            }

            Sku sku = ServiceManager.skuService.findOne(recommend.getSkuId());
            proxy.setSkuId(sku.getId());
            proxy.setRetailPrice(sku.getRetailPrice());
            proxy.setMemberPrice(isMember ? (sku.getMemberPrice()<=0?sku.getRetailPrice():sku.getMemberPrice()) : sku.getRetailPrice());
            List<FileMng> pictMngMsgList = ServiceManager.fileMngService.getList(FileObjectTypeCodeEnum.GOODS_PICT, goods.getId());
            proxy.setImgUrl(CollectionUtils.isEmpty(pictMngMsgList) ? "" : FileSystemEngine.getFileSystem().getUrl(pictMngMsgList.get(0).getFileId()));
            proxyList.add(proxy);
        }
        return new PageImpl<GoodsPageProxy>(proxyList, pageable, resultPage.getTotalElements());
    }

    /**
     * 查询可切换的门店列表
     * @param lat
     * @param lng
     * @return
     */
    public List<IndexShopProxy> findSwitchShop(Double lat, Double lng){
        List<IndexShopProxy> proxyList = new ArrayList<>();
        if(lat == null || lng == null){
            return proxyList;
        }
        List<Fans> fansList = ServiceManager.fansService.findByOpenId(WebContextFactory.getWebContext().getFrontEndUser().getOpenId());
        for (Fans fans : fansList){
            Shop shop = ServiceManager.shopService.findOne(fans.getShopId());
            if (shop == null || BoolCodeEnum.NO == BoolCodeEnum.fromCode(shop.getIsEnable())){
                continue;
            }
            WeShop weShop = ServiceManager.weShopService.findByShopId(fans.getShopId());
            Double distance = MapDistanceUtils.distance(lng, lat, weShop.getShopLng(), weShop.getShopLat());
            //不在配送范围
            if (distance > weShop.getDeliveryRange()) {
                continue;
            }
            IndexShopProxy proxy = CommonUtil.copyProperties(weShop, new IndexShopProxy());
            proxy.setWeShopId(weShop.getId());
            List<FileMng> fileMngList = ServiceManager.fileMngService.getList(FileObjectTypeCodeEnum.SHOP_LOGO, weShop.getId());
            if (CollectionUtils.isNotEmpty(fileMngList)) {
                proxy.setLogoUrl(FileSystemEngine.getFileSystem().getUrl(fileMngList.get(0).getFileId(), "160X160"));
            }
            proxyList.add(proxy);
        }
        return proxyList;
    }
}
