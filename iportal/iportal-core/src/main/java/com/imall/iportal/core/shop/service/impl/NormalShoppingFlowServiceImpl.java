package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.idfs.FileSystemEngine;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.util.BigDecimalUtil;
import com.imall.commons.base.util.MapDistanceUtils;
import com.imall.commons.dicts.*;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.FileMng;
import com.imall.iportal.core.shop.commons.util.ShoppingCalc;
import com.imall.iportal.core.shop.commons.util.ShoppingUtil;
import com.imall.iportal.core.shop.entity.*;
import com.imall.iportal.core.shop.service.INormalShoppingFlowService;
import com.imall.iportal.core.shop.vo.CartItem;
import com.imall.iportal.core.shop.vo.NormalCartItem;
import com.imall.iportal.core.shop.vo.NormalShoppingCart;
import com.imall.iportal.core.weshop.entity.Fans;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by ygw on 2017/3/1.
 * objectId 是 批次ID
 */
@Service
@Transactional(readOnly = true)
public class NormalShoppingFlowServiceImpl implements INormalShoppingFlowService {

    private Map<String, NormalShoppingCart> cartCache = new HashMap<>();
    private static final String CART_TYPE = "NORMAL";

    private NormalShoppingCart createCart(String uniqueKey) {
        NormalShoppingCart shoppingCart = new NormalShoppingCart();
        shoppingCart.setUniqueKey(uniqueKey);
        return shoppingCart;
    }

    private void saveCart(NormalShoppingCart shoppingCart) {
        cartCache.put(shoppingCart.getUniqueKey(), shoppingCart);
    }

    @Override
    public void clearCartCache() {
        cartCache.clear();
    }

    @Override
    public NormalShoppingCart getCartOrNewCart(Long shopId, Long weChatUserId) {
        String uniqueKey = ShoppingUtil.genCartKey(CART_TYPE, shopId, weChatUserId);
        NormalShoppingCart shoppingCart = cartCache.get(uniqueKey);
        WeShop weShop = ServiceManager.weShopService.findByShopId(shopId);
        if (shoppingCart == null) {
            shoppingCart = createCart(uniqueKey);
            shoppingCart.setWeChatUserId(weChatUserId);
            shoppingCart.setMemberId(this.getMemberIdByWeChatUserIdAndShopId(weChatUserId, shopId));
            shoppingCart.setShopId(shopId);
            shoppingCart.setWeShopId(weShop.getId());
            shoppingCart.setWeShopName(weShop.getShopNm());
            shoppingCart.setShopPromiseSendTime(weShop.getShopPromiseSendTime());
            shoppingCart.setDeliveryMinOrderAmount(weShop.getDeliveryMinOrderAmount());
            shoppingCart.setDeliveryTypeCode(weShop.getDeliveryTypeCode());
            //加载持久化的购物车项
            List<ShoppingCartStore> cartStoreList = ServiceManager.shoppingCartStoreService.findByCartUniqueKey(shoppingCart.getUniqueKey());
            for (ShoppingCartStore cartStore : cartStoreList) {
                CartItem cartItem = createCartItem(shoppingCart.getMemberId(), cartStore.getSkuId(), shopId);
                Long dbQuantity = ServiceManager.skuService.getStockQuantity(cartItem.getSkuId());
                if (dbQuantity <= 0) {
                    //初始化购物车的时候，如果库存为零。则不加入购物车里面
                    ServiceManager.shoppingCartStoreService.delete(cartStore.getId());
                    continue;
//                    throw new BusinessException(ResGlobal.COMMON_ERROR, "库存不足");
                }
                //如果库存不足，则按最大库存加入购物车
                cartItem.setQuantity(cartStore.getQuantity() > dbQuantity ? dbQuantity : cartStore.getQuantity());
                //重新计算单品的优惠
                cartItem = ShoppingCalc.calcItemAmount(shoppingCart, cartItem);
                shoppingCart.updateCartItem(cartItem);
            }
            //默认收货地址
            ReceiverAddr receiverAddr = ServiceManager.receiverAddrService.findDefaultByWeChatUserId(weChatUserId);
            if (receiverAddr != null && MapDistanceUtils.distance(receiverAddr.getAddrLng(), receiverAddr.getAddrLng(), weShop.getShopLng(), weShop.getShopLat()) <= weShop.getDeliveryRange()) {
                shoppingCart.setReceiverAddrId(receiverAddr.getId());
                shoppingCart.setReceiverAddr(receiverAddr);
            }
            shoppingCart = (NormalShoppingCart) ShoppingCalc.calcCartAmount(weShop, shoppingCart);
        } else {
            if (shoppingCart.getReceiverAddrId() != null) {
                ReceiverAddr receiverAddr = ServiceManager.receiverAddrService.findOne(shoppingCart.getReceiverAddrId());
                if (receiverAddr == null || MapDistanceUtils.distance(receiverAddr.getAddrLng(), receiverAddr.getAddrLat(), weShop.getShopLng(), weShop.getShopLat()) > weShop.getDeliveryRange()) {
                    shoppingCart.setReceiverAddrId(null);
                    shoppingCart.setReceiverAddr(null);
                }
            }
        }
        //更新到缓存
        cartCache.put(uniqueKey, shoppingCart);
        return shoppingCart;
    }

    @Transactional
    @Override
    public NormalShoppingCart clearCart(Long shopId, Long weChatUserId) {
        NormalShoppingCart shoppingCart = getCartOrNewCart(shopId, weChatUserId);
        shoppingCart.getCartItems().clear();
        WeShop weShop = ServiceManager.weShopService.findOne(shoppingCart.getWeShopId());
        shoppingCart = (NormalShoppingCart) ShoppingCalc.calcAmount(weShop, shoppingCart);
        //更新到缓存
        saveCart(shoppingCart);
        //持久化的购物车项也要删除
        ServiceManager.shoppingCartStoreService.clearCart(shoppingCart.getUniqueKey());
        return shoppingCart;
    }

    @Transactional
    @Override
    public NormalShoppingCart clearCartBySelected(Long shopId, Long weChatUserId) {
        NormalShoppingCart shoppingCart = getCartOrNewCart(shopId, weChatUserId);
        Collection<CartItem> cartItems = shoppingCart.getCartItems();
        Iterator<CartItem> iterator = cartItems.iterator();
        while (iterator.hasNext()) {
            CartItem cartItem = iterator.next();
            if (cartItem.getIsItemSelected()) {
                iterator.remove();
                //持久化的购物车项也要删除
                ServiceManager.shoppingCartStoreService.removeCartItemBySkuId(shoppingCart.getUniqueKey(), cartItem.getObjectId());
            }
        }
        WeShop weShop = ServiceManager.weShopService.findOne(shoppingCart.getWeShopId());
        shoppingCart = (NormalShoppingCart) ShoppingCalc.calcAmount(weShop, shoppingCart);
        //更新到缓存
        saveCart(shoppingCart);
        return shoppingCart;
    }

    @Transactional
    @Override
    public NormalShoppingCart dropCart(Long shopId, Long weChatUserId) {
        //从缓存移除购物车
        String uniqueKey = ShoppingUtil.genCartKey(CART_TYPE, shopId, weChatUserId);
        cartCache.remove(uniqueKey);
        //持久化的购物车项也要删除
        ServiceManager.shoppingCartStoreService.clearCart(uniqueKey);
        //创建一个新的
        return getCartOrNewCart(shopId, weChatUserId);
    }

    //objectId 是 skuID
    @Transactional
    @Override
    public NormalShoppingCart addCartItem(Long shopId, Long weChatUserId, Long objectId, Long quantity, boolean isModify) {
        WeShop weShop = ServiceManager.weShopService.findByShopId(shopId);
        if (weShop == null){
            throw new BusinessException(ResGlobal.COMMON_ERROR, "门店不存在");
        }
        if (BoolCodeEnum.NO == BoolCodeEnum.fromCode(weShop.getIsNormalSales())){
            throw new BusinessException(ResGlobal.COMMON_ERROR, "门店已暂停营业");
        }
        NormalShoppingCart shoppingCart = getCartOrNewCart(shopId, weChatUserId);
        CartItem cartItem = shoppingCart.getCartItem(objectId);
        //检查商品状态
        ServiceManager.skuService.checkGoodsState(objectId);
        if (cartItem == null) {
            cartItem = createCartItem(this.getMemberIdByWeChatUserIdAndShopId(weChatUserId, shopId), objectId, shopId);
        }
        Long sumQuantity = 0L;
        if (isModify) {
            //修改
            sumQuantity = quantity < 0 ? 0 : quantity;
        } else {
            //增加
            sumQuantity = BigDecimalUtil.add(cartItem.getQuantity(), quantity).longValue();
            if (sumQuantity < 0L) {
                sumQuantity = 0L;
            }
        }
        Long dbQuantity = ServiceManager.skuService.getStockQuantity(cartItem.getSkuId());
        if (dbQuantity == 0 || sumQuantity > dbQuantity) {
            throw new BusinessException(ResGlobal.COMMON_ERROR, "库存不足");
        }
        cartItem.setQuantity(sumQuantity);
        shoppingCart.addCartItem(cartItem);
        //重新计算单品的优惠
        cartItem = ShoppingCalc.calcItemAmount(shoppingCart, cartItem);
        shoppingCart.updateCartItem(cartItem);
        //重新计算品类品牌和订单的优惠、商品总金额、订单总金额
        shoppingCart = (NormalShoppingCart) ShoppingCalc.calcCartAmount(weShop, shoppingCart);
        //更新到缓存
        saveCart(shoppingCart);
        //持久化购物车项
        NormalCartItem normalCartItem = (NormalCartItem) cartItem;
        ShoppingCartStore cartStore = new ShoppingCartStore();
        cartStore.setCartTypeCode(shoppingCart.getCartTypeCodeEnum().toCode());
        cartStore.setCartUniqueKey(shoppingCart.getUniqueKey());
        cartStore.setShopId(shoppingCart.getShopId());
        cartStore.setWeChatUserId(weChatUserId);
        cartStore.setGoodsId(normalCartItem.getGoodsId());
        cartStore.setSkuId(normalCartItem.getSkuId());
        cartStore.setQuantity(cartItem.getQuantity());
        cartStore.setSalesManId(null);
        ServiceManager.shoppingCartStoreService.addCartItem(cartStore);
        return shoppingCart;
    }

    private NormalCartItem createCartItem(Long memberId, Long objectId, Long shopId) {
        NormalCartItem cartItem = new NormalCartItem();
        cartItem.setObjectId(objectId);
        //SKU
        Sku sku = ServiceManager.skuService.findOne(objectId);
        //商品
        Goods goods = ServiceManager.goodsService.findOne(sku.getGoodsId());
        if (goods == null || !goods.getShopId().equals(shopId)) {
            throw new BusinessException(ResGlobal.COMMON_ERROR, "商品不存在");
        }

        List<Long> sellCategoryList = new ArrayList<>();
        for (String category : goods.getSellCategoryIds().split(",")) {
            sellCategoryList.add(Long.parseLong(category));
        }

        cartItem.setGoodsCode(goods.getGoodsCode());
        cartItem.setCommonNm(goods.getCommonNm());
        cartItem.setGoodsNm(goods.getGoodsNm());
        cartItem.setProduceManufacturer(goods.getProduceManufacturer());
        cartItem.setSpec(goods.getSpec());
        cartItem.setUnit(goods.getUnit());

        List<FileMng> pictMngMsgList = ServiceManager.fileMngService.getList(FileObjectTypeCodeEnum.GOODS_PICT, goods.getId());
        if (!CollectionUtils.isEmpty(pictMngMsgList)) {
            for (FileMng fileMng : pictMngMsgList) {
                if (FileTypeCodeEnum.fromCode(fileMng.getFileTypeCode()) == FileTypeCodeEnum.IMAGE) {
                    cartItem.setGoodsImgUrl(FileSystemEngine.getFileSystem().getUrl(fileMng.getFileId()));
                    break;
                }
            }
        }

        cartItem.setCategoryId(sellCategoryList);
        cartItem.setGoodsId(goods.getId());
        cartItem.setSkuId(sku.getId());

        switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())) {
            case DRUG:
                GoodsDrug goodsDrug = ServiceManager.goodsDrugService.findByGoodsId(goods.getId());
                cartItem.setIsRx(PrescriptionDrugsTypeCodeEnum.RX == PrescriptionDrugsTypeCodeEnum.fromCode(goodsDrug.getPrescriptionDrugsTypeCode()));
                break;
            case CHINESE_MEDICINE_PIECES:
                GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goods.getId());
                cartItem.setIsRx(PrescriptionDrugsTypeCodeEnum.RX == PrescriptionDrugsTypeCodeEnum.fromCode(goodsChineseMedicinePieces.getPrescriptionDrugsTypeCode()));
                break;
            default:
                break;
        }

        //会员
        double unitPrice = sku.getRetailPrice();
        if (memberId != null) {
            Member member = ServiceManager.memberService.findOne(memberId);
            Date now = new Date();
            if (member != null && MemberStateCodeEnum.fromCode(member.getMemberStateCode()) == MemberStateCodeEnum.NORMAL
                    && StringUtils.isNotBlank(member.getCardUseStateCode()) && MemberCardUseStatCodeEnum.NORMAL == MemberCardUseStatCodeEnum.fromCode(member.getCardUseStateCode())
                    && member.getEffectTime() != null && member.getExpireTime() != null && now.after(member.getEffectTime()) && now.before(member.getExpireTime())) {
                unitPrice = sku.getMemberPrice() <= 0 ? sku.getRetailPrice() : sku.getMemberPrice();
            }
        }
        cartItem.setUnitPrice(unitPrice); //价格要注意：取 零售价？ 会员价？ 有会员卡，用会员价计算，没有会员卡的就用零售价计算。
        return cartItem;
    }

    @Transactional
    @Override
    public NormalShoppingCart removeCartItem(Long shopId, Long weChatUserId, Long objectId) {
        NormalShoppingCart shoppingCart = getCartOrNewCart(shopId, weChatUserId);
        shoppingCart.removeCartItem(objectId);
        //重新计算优惠和商品总金额和订单总金额
        WeShop weShop = ServiceManager.weShopService.findOne(shoppingCart.getWeShopId());
        shoppingCart = (NormalShoppingCart) ShoppingCalc.calcAmount(weShop, shoppingCart);
        //更新到缓存
        saveCart(shoppingCart);
        //持久化购物车项
        ServiceManager.shoppingCartStoreService.removeCartItemBySkuId(shoppingCart.getUniqueKey(), objectId);
        return shoppingCart;
    }

    @Transactional
    @Override
    public NormalShoppingCart batchRemoveCartItem(Long shopId, Long weChatUserId, List<Long> objectIds) {
        NormalShoppingCart shoppingCart = getCartOrNewCart(shopId, weChatUserId);
        for (Long objectId : objectIds) {
            shoppingCart.removeCartItem(objectId);
        }
        //重新计算优惠和商品总金额和订单总金额
        WeShop weShop = ServiceManager.weShopService.findOne(shoppingCart.getWeShopId());
        shoppingCart = (NormalShoppingCart) ShoppingCalc.calcAmount(weShop, shoppingCart);
        //更新到缓存
        saveCart(shoppingCart);
        //持久化购物车项
        ServiceManager.shoppingCartStoreService.batchRemoveCartItemBySkuId(shoppingCart.getUniqueKey(), objectIds);
        return shoppingCart;
    }

    @Override
    public NormalShoppingCart selectAll(Long shopId, Long weChatUserId) {
        NormalShoppingCart shoppingCart = getCartOrNewCart(shopId, weChatUserId);
        for (CartItem cartItem : shoppingCart.getCartItems()) {
            cartItem.setIsItemSelected(true);
        }
        WeShop weShop = ServiceManager.weShopService.findOne(shoppingCart.getWeShopId());
        shoppingCart = (NormalShoppingCart) ShoppingCalc.calcCartAmount(weShop, shoppingCart);
        //保存到缓存
        saveCart(shoppingCart);
        return shoppingCart;
    }

    @Override
    public NormalShoppingCart unselectAll(Long shopId, Long weChatUserId) {
        NormalShoppingCart shoppingCart = getCartOrNewCart(shopId, weChatUserId);
        for (CartItem cartItem : shoppingCart.getCartItems()) {
            cartItem.setIsItemSelected(false);
        }
        WeShop weShop = ServiceManager.weShopService.findOne(shoppingCart.getWeShopId());
        shoppingCart = (NormalShoppingCart) ShoppingCalc.calcCartAmount(weShop, shoppingCart);
        //保存到缓存
        saveCart(shoppingCart);
        return shoppingCart;
    }

    @Override
    @Transactional
    public NormalShoppingCart getCartByNewest(Long shopId, Long weChatUserId) {
        NormalShoppingCart shoppingCart = getCartOrNewCart(shopId, weChatUserId);
        if (shoppingCart.getReceiverAddr() == null) {
            ReceiverAddr receiverAddr = ServiceManager.receiverAddrService.findDefaultByWeChatUserId(weChatUserId);
            if (receiverAddr != null) {
                shoppingCart.setReceiverAddrId(receiverAddr.getId());
                shoppingCart.setReceiverAddr(receiverAddr);
            }
        }
        //保存到缓存
        saveCart(shoppingCart);
        return shoppingCart;
    }

    @Override
    public Long getGoodsCount(Long shopId, Long weChatUserId) {
        NormalShoppingCart shoppingCart = cartCache.get(ShoppingUtil.genCartKey(CART_TYPE, shopId, weChatUserId));
        if (shoppingCart == null) {
            return 0L;
        }
        Collection<CartItem> cartItems = shoppingCart.getCartItems();
        Long total = 0L;
        for (CartItem cartItem : cartItems) {
            total += cartItem.getQuantity();
        }
        return total;
    }

    @Override
    public void updateCartAfterPriceChange(Sku sku) {
        Date now = new Date();
        for (String key : cartCache.keySet()) {
            NormalShoppingCart cart = cartCache.get(key);
            Collection<CartItem> items = cart.getCartItems();
            boolean flag = false;
            for (CartItem item : items) {
                if (item.getSkuId().equals(sku.getId())) {
                    item.setUnitPrice(sku.getRetailPrice());
                    if (cart.getMemberId() != null) {
                        item.setUnitPrice(sku.getRetailPrice());
                        Member member = ServiceManager.memberService.findOne(cart.getMemberId());
                        if (member != null && MemberStateCodeEnum.fromCode(member.getMemberStateCode()) == MemberStateCodeEnum.NORMAL &&
                                StringUtils.isNotBlank(member.getCardUseStateCode()) && MemberCardUseStatCodeEnum.NORMAL == MemberCardUseStatCodeEnum.fromCode(member.getCardUseStateCode())
                                && member.getEffectTime() != null && member.getExpireTime() != null && now.after(member.getEffectTime()) && now.before(member.getExpireTime())) {
                            item.setUnitPrice(sku.getMemberPrice() <= 0 ? sku.getRetailPrice() : sku.getMemberPrice());
                        }
                    }
                    cart.updateCartItem(ShoppingCalc.calcItemAmount(cart, item));
                    flag = true;
                    break;
                }
            }
            if (flag) {
                WeShop weShop = ServiceManager.weShopService.findOne(cart.getWeShopId());
                saveCart((NormalShoppingCart) ShoppingCalc.calcCartAmount(weShop, cart));
            }
        }
    }

    @Override
    public NormalShoppingCart selectedChange(Long shopId, Long weChatUserId, Long skuId) {
        NormalShoppingCart shoppingCart = getCartOrNewCart(shopId, weChatUserId);
        CartItem cartItem = shoppingCart.getCartItem(skuId);
        cartItem.setIsItemSelected(!cartItem.getIsItemSelected());
        WeShop weShop = ServiceManager.weShopService.findOne(shoppingCart.getWeShopId());
        shoppingCart = (NormalShoppingCart) ShoppingCalc.calcCartAmount(weShop, shoppingCart);
        //保存到缓存
        saveCart(shoppingCart);
        return shoppingCart;
    }

    @Override
    public NormalShoppingCart saveReceiverAddr(Long shopId, Long weChatUserId, Long receiverAddrId) {
        NormalShoppingCart shoppingCart = getCartOrNewCart(shopId, weChatUserId);
        shoppingCart.setReceiverAddrId(receiverAddrId);
        ReceiverAddr receiverAddr = ServiceManager.receiverAddrService.findOne(receiverAddrId);
        if (receiverAddr != null) {
            shoppingCart.setReceiverAddr(receiverAddr);
        }
        //保存到缓存
        saveCart(shoppingCart);
        return shoppingCart;
    }

    @Override
    public NormalShoppingCart saveDefaultReceiverAddr(Long shopId, Long weChatUserId) {
        NormalShoppingCart shoppingCart = getCartOrNewCart(shopId, weChatUserId);
        if(shoppingCart.getReceiverAddrId() != null) {
            return shoppingCart;
        }
        WeShop weShop = ServiceManager.weShopService.findByShopId(shopId);
        List<ReceiverAddr> list = ServiceManager.receiverAddrService.findByWeChatUserId(weChatUserId);

        ReceiverAddr defaultReceiverAddr = null;
        Double nearDistinct = 0D;
        for (ReceiverAddr addr : list){
            Double distinct = MapDistanceUtils.distance(addr.getAddrLng(), addr.getAddrLat(), weShop.getShopLng(), weShop.getShopLat());
            //过滤不在配送范围门店和距离大于已选择地址
            if(distinct > weShop.getDeliveryRange() || (defaultReceiverAddr != null && nearDistinct < distinct)) {
                continue;
            }
            defaultReceiverAddr = addr;
            nearDistinct = distinct;
        }

        if(defaultReceiverAddr == null) {
            return shoppingCart;
        }

        shoppingCart.setReceiverAddrId(defaultReceiverAddr.getId());
        shoppingCart.setReceiverAddr(defaultReceiverAddr);
        //保存到缓存
        saveCart(shoppingCart);
        return shoppingCart;
    }


    private Long getMemberIdByWeChatUserIdAndShopId(Long weChatUserId, Long shopId) {
        Fans fans = ServiceManager.fansService.findByWeChatUserIdAndShopId(weChatUserId, shopId);
        return fans == null ? null : fans.getMemberId();
    }
}
