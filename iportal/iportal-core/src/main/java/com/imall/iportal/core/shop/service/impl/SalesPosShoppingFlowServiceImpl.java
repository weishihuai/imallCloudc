package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.idfs.FileSystemEngine;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.util.BigDecimalUtil;
import com.imall.commons.dicts.FileObjectTypeCodeEnum;
import com.imall.commons.dicts.FileTypeCodeEnum;
import com.imall.commons.dicts.MemberStateCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.FileMng;
import com.imall.iportal.core.shop.commons.util.ShoppingCalc;
import com.imall.iportal.core.shop.commons.util.ShoppingUtil;
import com.imall.iportal.core.shop.entity.Goods;
import com.imall.iportal.core.shop.entity.Member;
import com.imall.iportal.core.shop.entity.Sku;
import com.imall.iportal.core.shop.service.ISalesPosShoppingFlowService;
import com.imall.iportal.core.shop.vo.CartItem;
import com.imall.iportal.core.shop.vo.SalesPosCartItem;
import com.imall.iportal.core.shop.vo.SalesPosShoppingCart;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by ygw on 2017/3/1.
 */
@Service
@Transactional(readOnly = true)
public class SalesPosShoppingFlowServiceImpl implements ISalesPosShoppingFlowService {


    private Map<String, SalesPosShoppingCart> cartCache = new HashMap<>();
    private static final String CART_TYPE = "SALES_POS";

    @Override
    public void clearCartCache() {
        cartCache.clear();
    }

    private SalesPosShoppingCart createCart(String uniqueKey){
        SalesPosShoppingCart shoppingCart = new SalesPosShoppingCart();
        shoppingCart.setUniqueKey(uniqueKey);
        return shoppingCart;
    }

    private void saveCart(SalesPosShoppingCart shoppingCart){
        cartCache.put(shoppingCart.getUniqueKey(), shoppingCart);
    }

    @Override
    public SalesPosShoppingCart getCartOrNewCart(Long shopId, Long memberId) {
        String uniqueKey = ShoppingUtil.genCartKey(CART_TYPE, shopId, memberId);
        SalesPosShoppingCart shoppingCart = cartCache.get(uniqueKey);
        if(shoppingCart==null){
            shoppingCart = createCart(uniqueKey);
            shoppingCart.setShopId(shopId);
            shoppingCart.setMemberId(memberId);
            shoppingCart = (SalesPosShoppingCart)ShoppingCalc.calcCartAmount(null, shoppingCart);
            //更新到缓存
            cartCache.put(uniqueKey, shoppingCart);
        }
        return shoppingCart;
    }

    @Override
    public SalesPosShoppingCart getCartOrNewCart(Long shopId) {
        String uniqueKey = ShoppingUtil.genCartKey(CART_TYPE, shopId);
        SalesPosShoppingCart shoppingCart = cartCache.get(uniqueKey);
        if(shoppingCart==null){
            shoppingCart = createCart(uniqueKey);
            shoppingCart.setShopId(shopId);
            shoppingCart = (SalesPosShoppingCart)ShoppingCalc.calcCartAmount(null, shoppingCart);
            //更新到缓存
            cartCache.put(uniqueKey, shoppingCart);
        }
        return shoppingCart;
    }

    @Override
    public SalesPosShoppingCart getCart(String uniqueKey) {
        if(!cartCache.containsKey(uniqueKey)){
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobal.DEFAULT_ERROR_OBJECT_NOT_EXIST);
            throw new BusinessException(ResGlobal.APP_RELATE_DELETE_ERROR,String.format(message,new Object[]{uniqueKey + "的购物车"})); //异常处理
        }
        return cartCache.get(uniqueKey);
    }

    @Transactional
    @Override
    public SalesPosShoppingCart clearCart(Long shopId, Long memberId) {
        SalesPosShoppingCart shoppingCart = getCartOrNewCart(shopId, memberId);
        return clearCart(shoppingCart.getUniqueKey());
    }

    @Transactional
    @Override
    public SalesPosShoppingCart clearCart(Long shopId, String uniqueKey) {
        SalesPosShoppingCart shoppingCart = cartCache.get(uniqueKey);
        if(shoppingCart==null){
            shoppingCart = getCartOrNewCart(shopId);
        }
        return clearCart(shoppingCart.getUniqueKey());
    }

    private SalesPosShoppingCart clearCart( String uniqueKey) {
        SalesPosShoppingCart shoppingCart = getCart(uniqueKey);
        shoppingCart.getCartItems().clear();
        shoppingCart = (SalesPosShoppingCart)ShoppingCalc.calcAmount(null, shoppingCart);
        //更新到缓存
        saveCart(shoppingCart);
        return shoppingCart;
    }

    @Transactional
    @Override
    public SalesPosShoppingCart clearCartBySelected(Long shopId, String uniqueKey) {
        SalesPosShoppingCart shoppingCart = cartCache.get(uniqueKey);
        if(shoppingCart==null){
            shoppingCart = getCartOrNewCart(shopId);
        }
        return clearCartBySelected(shoppingCart.getUniqueKey());
    }

    @Transactional
    @Override
    public SalesPosShoppingCart clearCartBySelected(Long shopId, Long memberId) {
        SalesPosShoppingCart shoppingCart = getCartOrNewCart(shopId, memberId);
        return clearCartBySelected(shoppingCart.getUniqueKey());
    }

    private SalesPosShoppingCart clearCartBySelected(String uniqueKey) {
        SalesPosShoppingCart shoppingCart = getCart(uniqueKey);
        Collection<CartItem> cartItems = shoppingCart.getCartItems();
        Iterator<CartItem> iterator = cartItems.iterator();
        while (iterator.hasNext()){
            CartItem cartItem = iterator.next();
            if(cartItem.getIsItemSelected()){
                iterator.remove();
            }
        }
        shoppingCart = (SalesPosShoppingCart)ShoppingCalc.calcAmount(null, shoppingCart);
        //更新到缓存
        saveCart(shoppingCart);
        return shoppingCart;
    }

    @Transactional
    @Override
    public SalesPosShoppingCart dropCart(Long shopId, String uniqueKey) {
        //从缓存移除购物车
        cartCache.remove(uniqueKey);
        //创建一个新的
        return getCartOrNewCart(shopId);
    }

    @Transactional
    @Override
    public SalesPosShoppingCart dropCart(Long shopId, Long memberId) {
        //从缓存移除购物车
        String uniqueKey = ShoppingUtil.genCartKey(CART_TYPE, shopId, memberId);
        cartCache.remove(uniqueKey);
        //创建一个新的
        return getCartOrNewCart(shopId, memberId);
    }


    //objectId 是 批次ID
    @Transactional
    @Override
    public SalesPosShoppingCart addCartItem(Long shopId, Long memberId, Long skuId, String batch, Long quantity, boolean isModify) {
        SalesPosShoppingCart shoppingCart = getCartOrNewCart(shopId, memberId);
        return addCartItem(shoppingCart.getUniqueKey(), memberId, skuId, batch, quantity, isModify);
    }

    @Transactional
    @Override
    public SalesPosShoppingCart addCartItem(String uniqueKey, Long skuId, String batch, Long quantity, boolean isModify) {
        return addCartItem(uniqueKey, null, skuId, batch, quantity, isModify);
    }

    private SalesPosShoppingCart addCartItem(String uniqueKey, Long memberId, Long skuId, String batch, Long quantity, boolean isModify) {
        SalesPosShoppingCart shoppingCart = getCart(uniqueKey);
        CartItem cartItem = shoppingCart.getCartItem(skuId, batch);
        Sku sku = ServiceManager.skuService.findOne(skuId);
        //检查批次状态
        ServiceManager.goodsBatchService.checkGoodsBatchState(sku.getGoodsId(), batch);
        if(cartItem==null){
            cartItem = createCartItem(memberId, sku, batch);
        }
        Long sumQuantity = 0L;
        if(isModify){
            //修改
            sumQuantity = quantity < 0 ? 0 : quantity;
        } else {
            //增加
            sumQuantity = BigDecimalUtil.add(cartItem.getQuantity(), quantity).longValue();
            if(sumQuantity < 0L){
                sumQuantity = 0L;
            }
        }
        Long dbQuantity = ServiceManager.skuService.getStockQuantity(cartItem.getSkuId());
        if(dbQuantity==0 || sumQuantity > dbQuantity){
            throw new BusinessException(ResGlobal.COMMON_ERROR, "商品库存不足");
        }
        Long batchQuantity = ServiceManager.goodsBatchService.findCurrentStockByGoodsIdAndBatch(sku.getGoodsId(), batch);
        if(batchQuantity == 0 || sumQuantity > batchQuantity){
            throw new BusinessException(ResGlobal.COMMON_ERROR, "商品批次库存不足,剩"+batchQuantity+"件");
        }
        shoppingCart.addCartItem(cartItem);
        cartItem.setQuantity(sumQuantity);
        //重新计算单品的优惠
        cartItem = ShoppingCalc.calcItemAmount(shoppingCart, cartItem);
        shoppingCart.updateCartItem(cartItem);
        //重新计算品类品牌和订单的优惠、商品总金额、订单总金额
        shoppingCart = (SalesPosShoppingCart)ShoppingCalc.calcCartAmount(null, shoppingCart);
        //更新到缓存
        saveCart(shoppingCart);
        //持久化购物车项
        return shoppingCart;
    }

    private SalesPosCartItem createCartItem(Long memberId, Sku sku, String batch){
        SalesPosCartItem cartItem = new SalesPosCartItem();
        cartItem.setObjectId(sku.getId());
        //商品
        Goods goods = ServiceManager.goodsService.findOne(sku.getGoodsId());
        //SKU
        cartItem.setGoodsCode(goods.getGoodsCode());
        cartItem.setCommonNm(goods.getCommonNm());
        cartItem.setGoodsNm(goods.getGoodsNm());
        cartItem.setProduceManufacturer(goods.getProduceManufacturer());
        cartItem.setSpec(goods.getSpec());
        cartItem.setUnit(goods.getUnit());
        cartItem.setBatch(batch);

        List<FileMng> pictMngMsgList = ServiceManager.fileMngService.getList(FileObjectTypeCodeEnum.GOODS_PICT, goods.getId());
        if (!CollectionUtils.isEmpty(pictMngMsgList)) {
            for (FileMng fileMng : pictMngMsgList) {
                if (FileTypeCodeEnum.fromCode(fileMng.getFileTypeCode()) == FileTypeCodeEnum.IMAGE) {
                    cartItem.setGoodsImgUrl(FileSystemEngine.getFileSystem().getUrl(fileMng.getFileId()));
                    break;
                }
            }
        }

        List<Long> sellCategoryList = new ArrayList<>();
        for (String category : goods.getSellCategoryIds().split(",")) {
            sellCategoryList.add(Long.parseLong(category));
        }
        cartItem.setCurrentQuantity(ServiceManager.goodsBatchService.findCurrentStockByGoodsIdAndBatch(sku.getGoodsId(), batch));
        cartItem.setCategoryId(sellCategoryList);
        cartItem.setGoodsId(goods.getId());
        cartItem.setSkuId(sku.getId());
        //会员
        double unitPrice = sku.getRetailPrice();
        if(memberId!=null){
            Member member = ServiceManager.memberService.findOne(memberId);
            if(member!=null && MemberStateCodeEnum.fromCode(member.getMemberStateCode()) == MemberStateCodeEnum.NORMAL){ //非禁用会员
                unitPrice = sku.getMemberPrice() <= 0 ? sku.getRetailPrice() : sku.getMemberPrice();
            }
        }
        cartItem.setUnitPrice(unitPrice); //价格要注意：取 零售价？ 会员价？ 有会员卡，用会员价计算，没有会员卡的就用零售价计算。
        return cartItem;
    }

    @Transactional
    @Override
    public SalesPosShoppingCart removeCartItem(Long shopId, Long memberId, Long skuId, String batch) {
        SalesPosShoppingCart shoppingCart = getCartOrNewCart(shopId, memberId);
        return removeCartItem(shoppingCart.getUniqueKey(), skuId, batch);
    }

    @Transactional
    @Override
    public SalesPosShoppingCart removeCartItem(String uniqueKey, Long skuId, String batch) {
        SalesPosShoppingCart shoppingCart = getCart(uniqueKey);
        shoppingCart.removeCartItem(skuId, batch);
        //重新计算优惠和商品总金额和订单总金额
        shoppingCart = (SalesPosShoppingCart)ShoppingCalc.calcAmount(null, shoppingCart);
        //更新到缓存
        saveCart(shoppingCart);
        return shoppingCart;
    }

    //todo:uniqueKey是否要重新生成？？？
    @Override
    public SalesPosShoppingCart convert(String uniqueKey, Long memberId) {
        SalesPosShoppingCart shoppingCart = getCart(uniqueKey);
        shoppingCart.setMemberId(memberId);
        //更新单价为会员价
        for(CartItem cartItem : shoppingCart.getCartItems()){
            SalesPosCartItem salesPosCartItem = (SalesPosCartItem)cartItem;
            Sku sku = ServiceManager.skuService.findOne(salesPosCartItem.getSkuId());
            Member member = ServiceManager.memberService.findOne(memberId);
            if(member!=null && MemberStateCodeEnum.fromCode(member.getMemberStateCode()) == MemberStateCodeEnum.NORMAL){ //非禁用会员
                cartItem.setUnitPrice(sku.getMemberPrice() <= 0 ? sku.getRetailPrice() : sku.getMemberPrice());
                shoppingCart.updateCartItem(cartItem);
            }
        }

        //重新计算品类品牌和订单的优惠、商品总金额、订单总金额
        shoppingCart = (SalesPosShoppingCart)ShoppingCalc.calcAmount(null, shoppingCart);
        //更新到缓存
        saveCart(shoppingCart);
        return shoppingCart;
    }


    @Override
    public SalesPosShoppingCart convertWithoutMember(String uniqueKey) {
        SalesPosShoppingCart shoppingCart = getCart(uniqueKey);
        shoppingCart.setMemberId(null);
        //更新单价为会员价
        for(CartItem cartItem : shoppingCart.getCartItems()){
            SalesPosCartItem salesPosCartItem = (SalesPosCartItem)cartItem;
            Sku sku = ServiceManager.skuService.findOne(salesPosCartItem.getSkuId());
            cartItem.setUnitPrice(sku.getRetailPrice());
            shoppingCart.updateCartItem(cartItem);
        }

        //重新计算品类品牌和订单的优惠、商品总金额、订单总金额
        shoppingCart = (SalesPosShoppingCart)ShoppingCalc.calcAmount(null, shoppingCart);
        //更新到缓存
        saveCart(shoppingCart);
        return shoppingCart;
    }
}
