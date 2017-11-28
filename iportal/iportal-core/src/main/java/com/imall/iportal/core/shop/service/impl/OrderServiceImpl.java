package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.*;
import com.imall.commons.dicts.*;
import com.imall.iportal.core.elasticsearch.ESUtils;
import com.imall.iportal.core.elasticsearch.IndexBuilder;
import com.imall.iportal.core.elasticsearch.entity.OrderEntity;
import com.imall.iportal.core.elasticsearch.entity.OrderItemEntity;
import com.imall.iportal.core.elasticsearch.utils.SearchUtils;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.platform.entity.Shop;
import com.imall.iportal.core.shop.entity.*;
import com.imall.iportal.core.shop.repository.OrderRepository;
import com.imall.iportal.core.shop.service.OrderService;
import com.imall.iportal.core.shop.valid.NormalOrderPaySaveValid;
import com.imall.iportal.core.shop.valid.NormalOrderSaveValid;
import com.imall.iportal.core.shop.valid.SalesPosOrderSaveValid;
import com.imall.iportal.core.shop.vo.*;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.InternalSum;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;

/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class OrderServiceImpl extends AbstractBaseService<Order, Long> implements OrderService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private Integer statisticsDays;

	@Value("${app.order.statisticsDays:7}")
	public void setStatisticsDays(Integer statisticsDays) {
		this.statisticsDays = statisticsDays;
	}

	@SuppressWarnings("unused")
    private OrderRepository getOrderRepository() {
		return (OrderRepository) baseRepository;
	}

	@Transactional
	@Override
	public Long saveSalesPosOrder(CurrUserVo currUserVo, ShoppingCart cart, SalesPosOrderSaveValid salesPosOrderSaveValid) {
		SalesPosShoppingCart shoppingCart = (SalesPosShoppingCart)cart;

		if(shoppingCart.getCartItems().isEmpty()){
			throw new BusinessException(ResGlobal.COMMON_ERROR, "购物车没有商品");
		}
		if(cart.getOrderTotalAmount() <= 0){
			throw new BusinessException(ResGlobal.COMMON_ERROR, "订单金额小于或等于0，订单提交失败");
		}

		//锁库存
		List<CartItem> cartItemList = new ArrayList<>();
		for(CartItem cartItem :shoppingCart.getCartItems()){
			if(!cartItem.getIsItemSelected()){ //选中的
				continue;
			}
            cartItemList.add(cartItem);
		}

		//找零计算 = 实收现金 + 医保支付金额 - 订单总金额(应收金额)
		Double returnSmall = 0D;
		if(PayWayTypeCodeEnum.fromCode(salesPosOrderSaveValid.getPaymentWayCode()) == PayWayTypeCodeEnum.CASH_PAY) {
			returnSmall = BigDecimalUtil.subtract(BigDecimalUtil.add(salesPosOrderSaveValid.getRealGeveCashAmount(), salesPosOrderSaveValid.getMedicalInsurancePaymentAmount()), shoppingCart.getOrderTotalAmount());
			if (returnSmall < 0) {
				throw new BusinessException(ResGlobal.COMMON_ERROR, "支付金额小于应付金额!");
			}
		}

		Order order = new Order();
		order.setOpenOrderManId(currUserVo.getUserId());
		order.setMedicalInsurancePaymentAmount(salesPosOrderSaveValid.getMedicalInsurancePaymentAmount());
		order.setPaymentWayCode(salesPosOrderSaveValid.getPaymentWayCode());
		order.setRealGeveCashAmount(salesPosOrderSaveValid.getRealGeveCashAmount());
		order.setReturnSmall(returnSmall);
		order.setFreightAmount(0d);
		order.setIsPayed(BoolCodeEnum.YES.toCode());
		order.setOrderSourceCode(OrderSourceCodeEnum.SALES_POS.toCode());
		order.setRemark("");
		order.setIsCod(BoolCodeEnum.NO.toCode());
		order.setOrderStateCode(OrderStateCodeEnum.FINISH.toCode());
		order.setFinishTime(new Date());
		order.setDeliveryTypeCode(DeliveryTypeCodeEnum.NEVER_PAY.toCode());
		order.setCoordinateX(0d);
		order.setCoordinateY(0d);
        order.setDeliveryMinOrderAmount(0D);
		order.setOrderNum(DateSerialGenerator.genSerialCode(DateSerialGenerator.ORDER_PREFIX));
		Long orderId = addOrder(shoppingCart, order);

		Double orderCostTotalAmount = 0D;
		Double grossMargin = 0D;
		//保存订单项
		for(CartItem cartItem :cartItemList){
			SalesPosCartItem posCartItem = (SalesPosCartItem)cartItem;

			Goods goods = ServiceManager.goodsService.findOne(posCartItem.getGoodsId());
			if(BoolCodeEnum.YES == BoolCodeEnum.fromCode(goods.getIsDelete())
					|| GoodsApproveStateCodeEnum.PASS_APPROVE != GoodsApproveStateCodeEnum.fromCode(goods.getApproveStateCode())
					|| BoolCodeEnum.NO == BoolCodeEnum.fromCode(goods.getIsEnable())) {
				throw new BusinessException(ResGlobal.COMMON_ERROR, "商品[" + goods.getGoodsNm() + "]已下架");
			}

			OrderItem orderItem = new OrderItem();
			orderItem.setOrderId(orderId);
			orderItem.setGoodsId(posCartItem.getGoodsId());
			orderItem.setSkuId(posCartItem.getSkuId());
			orderItem.setGoodsCoding(posCartItem.getGoodsCode());
			orderItem.setGoodsPinyin(Pinyin4jUtil.getPinYinHeadChar(posCartItem.getCommonNm()));
			orderItem.setCommonNm(posCartItem.getCommonNm());
			orderItem.setGoodsNm(posCartItem.getGoodsNm());
			orderItem.setProduceManufacturer(posCartItem.getProduceManufacturer());
			orderItem.setSpec(posCartItem.getSpec());
			orderItem.setUnit(posCartItem.getUnit());
			orderItem.setQuantity(posCartItem.getQuantity());
			orderItem.setGoodsUnitPrice(posCartItem.getUnitPrice());
			orderItem.setGoodsTotalAmount(posCartItem.getTotalAmount());
			orderItem.setRemark("");
			orderItem.setSeller(currUserVo.getUserId());
			orderItem.setGoodsCostTotalAmount(0D);
			ServiceManager.orderItemService.save(orderItem);

			Long dbQuantity = ServiceManager.skuService.getStockQuantity(cartItem.getSkuId());
			if(dbQuantity==0 || orderItem.getQuantity() > dbQuantity){
				throw new BusinessException(ResGlobal.COMMON_ERROR, "商品库存不足");
			}

			//批次
			List<GoodsBatch> goodsBatchList = ServiceManager.goodsBatchService.findHasStockGoodsByGoodsIdAndBatch(posCartItem.getGoodsId(), posCartItem.getBatch());
			Long waitSendOutQuantity = orderItem.getQuantity();
			Double costTotalAmount = 0D;
			Double grossMarginAmount = 0D;
			for(GoodsBatch goodsBatch : goodsBatchList) {
				if(waitSendOutQuantity <= 0) {
					break;
				}

				//保存发货批次
				OrderSendOutBatch orderSendOutBatch = new OrderSendOutBatch();
				orderSendOutBatch.setShopId(order.getShopId());
				orderSendOutBatch.setWeShopId(order.getWeShopId());
				orderSendOutBatch.setOrderId(order.getId());
				orderSendOutBatch.setOrderItemId(orderItem.getId());
				orderSendOutBatch.setGoodsBatchId(goodsBatch.getId());
				orderSendOutBatch.setBatch(posCartItem.getBatch());
				if(goodsBatch.getCurrentStock() >= waitSendOutQuantity) {
					orderSendOutBatch.setQuantity(waitSendOutQuantity);
				} else {
					orderSendOutBatch.setQuantity(goodsBatch.getCurrentStock());
				}
				waitSendOutQuantity -= orderSendOutBatch.getQuantity();

				//计算成本
				costTotalAmount = BigDecimalUtil.add(costTotalAmount, BigDecimalUtil.multiply(orderSendOutBatch.getQuantity(), goodsBatch.getPurchasePrice()));

				//计算毛利
				Double unitgrossMargin = BigDecimalUtil.subtract(orderItem.getGoodsUnitPrice(), goodsBatch.getPurchasePrice());
				grossMarginAmount = BigDecimalUtil.add(grossMarginAmount, BigDecimalUtil.multiply(orderSendOutBatch.getQuantity(), unitgrossMargin));
				ServiceManager.orderSendOutBatchService.save(orderSendOutBatch);
				//扣减库存
				ServiceManager.goodsBatchService.updateSubtractCurrentStock(orderSendOutBatch.getGoodsBatchId(), orderSendOutBatch.getQuantity());
				//保存出入库日志
				saveOutInStockLog(order, orderSendOutBatch);
			}
			if(waitSendOutQuantity > 0) {
				throw new BusinessException(ResGlobal.COMMON_ERROR, "商品批次[" + posCartItem.getBatch() + "]库存不足");
			}
			Sku sku = ServiceManager.skuService.findOne(cartItem.getSkuId());
			sku.setSalesVolume(sku.getSalesVolume() + orderItem.getQuantity());
			ServiceManager.skuService.save(sku);
			ServiceManager.esIndexQueueService.saveQueue(IndexTypeCodeEnum.GOODS.toCode(), sku.getGoodsId(), null);

			orderItem.setGoodsCostTotalAmount(costTotalAmount);
			ServiceManager.orderItemService.save(orderItem);
			orderCostTotalAmount += costTotalAmount;
			grossMargin += grossMarginAmount;
		}

		order.setCostTotalAmount(orderCostTotalAmount);
		order.setGrossMargin(grossMargin);
		ServiceManager.orderService.save(order);

		//移除已选的（已下单的）购物车项
		if(cart.getMemberId()!=null){
			ServiceManager.salesPosShoppingFlowService.clearCartBySelected(shoppingCart.getShopId(), shoppingCart.getMemberId());
		} else {
			ServiceManager.salesPosShoppingFlowService.clearCartBySelected(shoppingCart.getShopId(), shoppingCart.getUniqueKey());
		}
		ServiceManager.salesPosShoppingFlowService.convertWithoutMember(cart.getUniqueKey());

		//打印小票
		ServiceManager.weShopOrderService.printOrderSmallTicket(orderId, currUserVo.getUserName());
		IndexBuilder.commitImmediately(orderId, IndexTypeCodeEnum.ORDER);
		return orderId;
	}

	@Transactional
	@Override
	public Order saveNormalOrder(ShoppingCart cart, NormalOrderSaveValid normalOrderSaveValid) {
        NormalShoppingCart shoppingCart = (NormalShoppingCart)cart;

        if(shoppingCart.getCartItems().isEmpty()){
			throw new BusinessException(ResGlobal.COMMON_ERROR, "您的需求单没有商品");
		}
		WeShop weShop = ServiceManager.weShopService.findByShopId(cart.getShopId());
		if (weShop == null){
			throw new BusinessException(ResGlobal.COMMON_ERROR, "门店不存在");
		}
		Shop shop = ServiceManager.shopService.findOne(weShop.getShopId());
		if (shop == null){
			throw new BusinessException(ResGlobal.COMMON_ERROR, "门店不存在");
		}
		if (BoolCodeEnum.NO == BoolCodeEnum.fromCode(shop.getIsEnable())){
			throw new BusinessException(ResGlobal.COMMON_ERROR, "门店已禁用");
		}
		if (BoolCodeEnum.NO == BoolCodeEnum.fromCode(weShop.getIsNormalSales())){
			throw new BusinessException(ResGlobal.COMMON_ERROR, "门店已暂停营业");
		}
		if(MapDistanceUtils.distance(weShop.getShopLng(), weShop.getShopLat(), shoppingCart.getReceiverAddr().getAddrLng(), shoppingCart.getReceiverAddr().getAddrLat()) > weShop.getDeliveryRange()){
			throw new BusinessException(ResGlobal.COMMON_ERROR, "配送地址超出门店配送范围");
		}

		//锁库存
		List<CartItem> cartItemList = new ArrayList<>();
		List<SkuLockStock> skuLockStocks = new ArrayList<>();
		for(CartItem cartItem :shoppingCart.getCartItems()){
			if(!cartItem.getIsItemSelected()){ //选中的
				continue;
			}

			//检查商品状态
			ServiceManager.skuService.checkGoodsState(cartItem.getObjectId());

			Long stockQuantity = ServiceManager.skuService.getStockQuantity(cartItem.getObjectId());
			if(stockQuantity < cartItem.getQuantity()) {
				throw new BusinessException(ResGlobal.COMMON_ERROR, "商品[" + cartItem.getGoodsNm() + "]库存不足");
			}
			SkuLockStock skuLockStock = new SkuLockStock();
			skuLockStock.setSkuId(cartItem.getSkuId());
			skuLockStock.setQuantity(cartItem.getQuantity());
			skuLockStocks.add(skuLockStock);
			cartItemList.add(cartItem);
		}
		if(cartItemList.isEmpty()){
			throw new BusinessException(ResGlobal.COMMON_ERROR, "请选择下单项");
		}
		if(cart.getOrderTotalAmount() <= 0){
			throw new BusinessException(ResGlobal.COMMON_ERROR, "订单金额小于或等于0，订单提交失败");
		}
		if (shoppingCart.getReceiverAddrId() == null){
			throw new BusinessException(ResGlobal.COMMON_ERROR, "请选择配送地址");
		}
		String orderNum = DateSerialGenerator.genSerialCode(DateSerialGenerator.ORDER_PREFIX);
		ServiceManager.skuLockStockService.lockedStock(shoppingCart.getShopId(), shoppingCart.getMemberId(), orderNum, OrderSourceCodeEnum.WEIXIN, skuLockStocks);

		Order order = new Order();
		//order.setOpenOrderManId(null);
		order.setWeShopId(shoppingCart.getWeShopId());
		order.setMedicalInsurancePaymentAmount(0d);
		order.setPaymentWayCode(null);
		order.setRealGeveCashAmount(0d);
		order.setReturnSmall(0d);
		order.setFreightAmount(shoppingCart.getFreightAmount());
		order.setIsPayed(BoolCodeEnum.NO.toCode());
		order.setOrderSourceCode(OrderSourceCodeEnum.WEIXIN.toCode());
		order.setRemark(normalOrderSaveValid.getRemark());
		order.setIsCod(BoolCodeEnum.YES.toCode());
		order.setOrderStateCode(OrderStateCodeEnum.WAIT_SEND.toCode());
		order.setBookDeliveryTimeStart(normalOrderSaveValid.getBookDeliveryTimeStart());
		order.setBookDeliveryTimeEnd(normalOrderSaveValid.getBookDeliveryTimeEnd());
		order.setDeliveryTypeCode(shoppingCart.getDeliveryTypeCode());
		order.setDeliveryMinOrderAmount(shoppingCart.getDeliveryMinOrderAmount());

		ReceiverAddr receiverAddr = ServiceManager.receiverAddrService.findOne(shoppingCart.getReceiverAddrId());
		order.setReceiverName(receiverAddr.getReceiverName());
		order.setContactTel(receiverAddr.getContactTel());
		order.setDeliveryAddr(receiverAddr.getDeliveryAddr());
		order.setDetailAddr(receiverAddr.getDetailAddr());
		order.setCoordinateX(receiverAddr.getAddrLat());
		order.setCoordinateY(receiverAddr.getAddrLng());
		order.setOrderNum(orderNum);
		order.setWechatUserId(shoppingCart.getWeChatUserId());
		Long orderId = addOrder(shoppingCart, order);
		//保存订单项
		for(CartItem cartItem :cartItemList){
			NormalCartItem normalCartItem = (NormalCartItem)cartItem;
			OrderItem orderItem = new OrderItem();
			orderItem.setOrderId(orderId);
			orderItem.setGoodsId(normalCartItem.getGoodsId());
			orderItem.setSkuId(normalCartItem.getSkuId());
			orderItem.setGoodsCoding(normalCartItem.getGoodsCode());
			orderItem.setGoodsPinyin(Pinyin4jUtil.getPinYinHeadChar(normalCartItem.getCommonNm()));
			orderItem.setCommonNm(normalCartItem.getCommonNm());
			orderItem.setGoodsNm(normalCartItem.getGoodsNm());
			orderItem.setProduceManufacturer(normalCartItem.getProduceManufacturer());
			orderItem.setSpec(normalCartItem.getSpec());
			orderItem.setUnit(normalCartItem.getUnit());
			orderItem.setQuantity(normalCartItem.getQuantity());
			orderItem.setGoodsUnitPrice(normalCartItem.getUnitPrice());
			orderItem.setGoodsCostTotalAmount(0D);
			orderItem.setGoodsTotalAmount(normalCartItem.getTotalAmount());
			orderItem.setRemark("");
			orderItem.setSeller(null);
			ServiceManager.orderItemService.save(orderItem);
			//发货的时候，再 保存出入库日志和扣减库存

			//更新商品销量
			Sku sku = ServiceManager.skuService.findOne(cartItem.getSkuId());
			sku.setSalesVolume(sku.getSalesVolume() + orderItem.getQuantity());
			ServiceManager.skuService.save(sku);
			ServiceManager.esIndexQueueService.saveQueue(IndexTypeCodeEnum.GOODS.toCode(), sku.getGoodsId(), null);
		}
		//移除已选的（已下单的）购物车项
		ServiceManager.normalShoppingFlowService.clearCartBySelected(shoppingCart.getShopId(), shoppingCart.getWeChatUserId());
		return order;
	}

	private void saveOutInStockLog(Order order, OrderSendOutBatch orderSendOutBatch){
		OutInStockLog log = new OutInStockLog();
		GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(orderSendOutBatch.getGoodsBatchId());
		log.setShopId(orderSendOutBatch.getShopId());
		log.setGoodsId(goodsBatch.getGoodsId());
		log.setSkuId(goodsBatch.getSkuId());
		log.setTypeCode(OutInStockTypeCodeEnum.OUT_STOCK.toCode());
		log.setGoodsBatchId(goodsBatch.getId());
		log.setStorageSpaceId(goodsBatch.getStorageSpaceId());
		log.setQuantity(0 - orderSendOutBatch.getQuantity());
		log.setAmount(0 - BigDecimalUtil.multiply(goodsBatch.getPurchasePrice(), orderSendOutBatch.getQuantity()));
		log.setClearingPrevQuantity(goodsBatch.getCurrentStock());
		log.setClearingPrevAmount(BigDecimalUtil.multiply(goodsBatch.getCurrentStock(), goodsBatch.getPurchasePrice()));
		log.setLogSourceTypeCode(OutInStockLogSourceTypeCodeEnum.OUT_STOCK.toCode());
		log.setLogSourceObjectId(orderSendOutBatch.getId());
		log.setObjectOrderNum(order.getOrderNum());
		ServiceManager.outInStockLogService.save(log);
	}

	@Transactional
	@Override
	public Long saveCodPay(Long openOrderManId, NormalOrderPaySaveValid normalOrderPaySaveValid) {
		Order order = ServiceManager.orderService.findOne(normalOrderPaySaveValid.getOrderId());

		//找零计算 = 实收现金 + 医保支付金额 - 订单总金额(应收金额)
		Double returnSmall = BigDecimalUtil.subtract(BigDecimalUtil.add(normalOrderPaySaveValid.getRealGeveCashAmount(), normalOrderPaySaveValid.getMedicalInsurancePaymentAmount()), order.getOrderTotalAmount());
		if(returnSmall < 0){
			throw new BusinessException(ResGlobal.COMMON_ERROR, "支付金额小于应付金额!");
		}
		order.setOpenOrderManId(openOrderManId);
		order.setMedicalInsurancePaymentAmount(normalOrderPaySaveValid.getMedicalInsurancePaymentAmount());
		order.setPaymentWayCode(normalOrderPaySaveValid.getPaymentWayCode());
		order.setRealGeveCashAmount(normalOrderPaySaveValid.getRealGeveCashAmount());
		order.setReturnSmall(returnSmall);
		order.setIsPayed(BoolCodeEnum.YES.toCode());
		order.setOrderStateCode(OrderStateCodeEnum.FINISH.toCode());
		order.setFinishTime(new Date());
		update(order);
		return order.getId();
	}

	private Long addOrder(ShoppingCart shoppingCart, Order order){
		if(shoppingCart.getMemberId()!=null){
			Member member = ServiceManager.memberService.findOne(shoppingCart.getMemberId());
			order.setMemberCardNum(member.getMemberCardNum());
			order.setMemberId(shoppingCart.getMemberId());
		}
		order.setShopId(shoppingCart.getShopId());
		order.setGoodsTotalAmount(shoppingCart.getGoodsTotalAmount());
		order.setCostTotalAmount(0D);
		order.setGrossMargin(0D);
		order.setOrderTotalAmount(shoppingCart.getOrderTotalAmount());
		order.setIsHasReturnedPurchase(BoolCodeEnum.NO.toCode());
		Order dbOrder = save(order);
		return dbOrder.getId();

	}

	@Override
	public Integer queryOrderToQueue(){
		return getOrderRepository().queryOrderToQueue();
	}

	@Override
	public Page<OrderPageVo> commonOrderQuery(Pageable pageable, OrderSearchParam orderSearchParam) {
		SearchRequestBuilder searchRequestBuilder = ESUtils.getClient()
				.prepareSearch(IndexTypeCodeEnum.ORDER.toCode())
				.setTypes(IndexTypeCodeEnum.ORDER.toCode())
				.setSearchType(SearchType.DEFAULT)
				.setQuery(buildQuery(orderSearchParam)) //查询条件设置
				.addSort(OrderEntity.ID, SortOrder.DESC)
				.addStoredField(OrderEntity.ID)			//设置返回字段
				.setFrom(pageable.getPageNumber() * pageable.getPageSize())
				.setSize(pageable.getPageSize())
				.setExplain(false);
		SearchResponse sr = searchRequestBuilder.get();
		List<Map<String, Object>> list = SearchUtils.getSearchResult(sr);
		List<OrderPageVo> orderPageVoList = new ArrayList<>();
		for (Map<String, Object> map : list) {
			Long id = (Long) map.get(OrderEntity.ID);
			orderPageVoList.add(this.buildOrderPageVo(id));
		}

		return new PageImpl<OrderPageVo>(orderPageVoList, pageable, sr.getHits().getTotalHits());
	}

	private QueryBuilder buildQuery(OrderSearchParam orderSearchParam){
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		//门店 ID
		queryBuilder.must(QueryBuilders.termQuery(OrderEntity.SHOP_ID, orderSearchParam.getShopId()));

		if(orderSearchParam.getOpenOrderManId()!=null){
			queryBuilder.must(QueryBuilders.termQuery(OrderEntity.OPEN_ORDER_MAN_ID, orderSearchParam.getOpenOrderManId()));
		}

		if(StringUtils.isNotBlank(orderSearchParam.getOrderStateCode())) {
			queryBuilder.must(QueryBuilders.termQuery(OrderEntity.ORDER_STATE_CODE, orderSearchParam.getOrderStateCode()));
		}

		//拼音/商品编码/商品名称/条形码/通用名称
		if (StringUtils.isNotBlank(orderSearchParam.getGoodsSearchFields())) {
			BoolQueryBuilder keywordBuilder = QueryBuilders.boolQuery()
					.should(QueryBuilders.termQuery(OrderItemEntity.GOODS_CODING, orderSearchParam.getGoodsSearchFields()))
					.should(QueryBuilders.termQuery(OrderItemEntity.GOODS_NM, orderSearchParam.getGoodsSearchFields()))
					.should(QueryBuilders.termQuery(OrderItemEntity.COMMON_NM, orderSearchParam.getGoodsSearchFields()))
					.should(QueryBuilders.termQuery(OrderItemEntity.BAR_CODE, orderSearchParam.getGoodsSearchFields()))
					.should(QueryBuilders.termQuery(OrderItemEntity.GOODS_PINYIN, orderSearchParam.getGoodsSearchFields()));
			queryBuilder.must(keywordBuilder);
		}

		//会员:手机号/卡号/会员姓名
		if (StringUtils.isNotBlank(orderSearchParam.getMemberSearchFields())) {
			BoolQueryBuilder keywordsBuilder = QueryBuilders.boolQuery()
					.should(QueryBuilders.termQuery(OrderEntity.MEMBER_CARD_NUM, orderSearchParam.getMemberSearchFields()))
					.should(QueryBuilders.termQuery(OrderEntity.NAME, orderSearchParam.getMemberSearchFields()));
			queryBuilder.must(keywordsBuilder);
		}

		//是否退货订单
		if(StringUtils.isNotBlank(orderSearchParam.getIsHasReturnedPurchase())){
			queryBuilder.must(QueryBuilders.termQuery(OrderEntity.IS_HAS_RETURNED_PURCHASE, orderSearchParam.getIsHasReturnedPurchase()));
		}

		//订单 来源 代码
		if(StringUtils.isNotBlank(orderSearchParam.getOrderSourceCode())){
			queryBuilder.must(QueryBuilders.termQuery(OrderEntity.ORDER_SOURCE_CODE, orderSearchParam.getOrderSourceCode()));
		}

		//订单 编号
		if(StringUtils.isNotBlank(orderSearchParam.getOrderNum())){
			queryBuilder.must(QueryBuilders.termQuery(OrderEntity.ORDER_NUM, orderSearchParam.getOrderNum()));
		}

		//销售开始时间
		if(orderSearchParam.getFormCreateDate() != null){
			queryBuilder.must(QueryBuilders.rangeQuery(OrderEntity.CREATE_DATE).gt(orderSearchParam.getFormCreateDate().getTime()));
		}

		//销售结束时间
		if(orderSearchParam.getToCreateDate() != null){
			queryBuilder.must(QueryBuilders.rangeQuery(OrderEntity.CREATE_DATE).lt(orderSearchParam.getToCreateDate().getTime()));
		}

		//是否 麻黄碱 订单
		if(StringUtils.isNotBlank(orderSearchParam.getIsEphedrineOrder())){
			queryBuilder.must(QueryBuilders.termQuery(OrderEntity.IS_EPHEDRINE_ORDER, orderSearchParam.getIsEphedrineOrder()));
		}

		//是否 处方药 订单
		if(StringUtils.isNotBlank(orderSearchParam.getIsPrescriptionOrder())){
			queryBuilder.must(QueryBuilders.termQuery(OrderEntity.IS_PRESCRIPTION_ORDER, orderSearchParam.getIsPrescriptionOrder()));
		}

		//是否退货订单
		if(StringUtils.isNotBlank(orderSearchParam.getPayWayType())){
			queryBuilder.must(QueryBuilders.termQuery(OrderEntity.PAYMENT_WAY_CODE, orderSearchParam.getPayWayType()));
		}
		return queryBuilder;
	}

	@Override
	public Double sumOrderTotalAmountByShopId(OrderSearchParam orderSearchParam) {
		SearchRequestBuilder searchRequestBuilder = ESUtils.getClient()
				.prepareSearch(IndexTypeCodeEnum.ORDER.toCode())
				.setTypes(IndexTypeCodeEnum.ORDER.toCode())
				.setSearchType(SearchType.DEFAULT) //??? SearchType.QUERY_THEN_FETCH
				.setQuery(buildQuery(orderSearchParam)) //查询条件设置
				.addStoredField(OrderEntity.SHOP_ID)			//设置返回字段
				.addStoredField(OrderEntity.ORDER_TOTAL_AMOUNT)			//设置返回字段
				.setExplain(false);
		SearchResponse sr = searchRequestBuilder.get();
		Double sumOrderTotalAmount = 0d;
		List<Map<String, Object>> list = SearchUtils.getSearchResult(sr);
		if(list.size() == 0){
			return sumOrderTotalAmount;
		}
		TermsAggregationBuilder tb = AggregationBuilders.terms(OrderEntity.SHOP_ID).field(OrderEntity.SHOP_ID);
		SumAggregationBuilder sb = AggregationBuilders.sum(OrderEntity.ORDER_TOTAL_AMOUNT).field(OrderEntity.ORDER_TOTAL_AMOUNT);
		tb.subAggregation(sb);
		searchRequestBuilder.addAggregation(tb);
		LongTerms tms =  searchRequestBuilder.get().getAggregations().get(OrderEntity.SHOP_ID);
		for(Bucket bucket: tms.getBuckets()){
			LongTerms.Bucket longBucket = (LongTerms.Bucket)bucket;
			Aggregations aggregations = longBucket.getAggregations();
			List<Aggregation> aggregationList = aggregations.asList();
			for(Aggregation aggregation: aggregationList){
				Double sum = (Double)((InternalSum) aggregation).getValue();
				sumOrderTotalAmount = BigDecimalUtil.add(sumOrderTotalAmount, sum);
			}
		}
		return sumOrderTotalAmount;
	}

	@Override
	public Double sumOrderTotalAmountByShopIdAndPosMan(Long shopId, Long openOrderManId, Date formCreateDate, Date toCreateDate) {
		OrderSearchParam searchParam = new OrderSearchParam();
		searchParam.setShopId(shopId);
		searchParam.setOpenOrderManId(openOrderManId);
		searchParam.setFormCreateDate(formCreateDate);
		searchParam.setToCreateDate(toCreateDate);
		return sumOrderTotalAmountByShopId(searchParam);
	}

	@Override
	public Long sumOrderTotalQuantityByShopIdAndPosMan(Long shopId, Long openOrderManId, Date formCreateDate, Date toCreateDate) {
		OrderSearchParam orderSearchParam = new OrderSearchParam();
		orderSearchParam.setShopId(shopId);
		orderSearchParam.setOpenOrderManId(openOrderManId);
		orderSearchParam.setFormCreateDate(formCreateDate);
		orderSearchParam.setToCreateDate(toCreateDate);
		SearchRequestBuilder searchRequestBuilder = ESUtils.getClient()
				.prepareSearch(IndexTypeCodeEnum.ORDER.toCode())
				.setTypes(IndexTypeCodeEnum.ORDER.toCode())
				.setSearchType(SearchType.DEFAULT)
				.setQuery(buildQuery(orderSearchParam)) //查询条件设置
				.addStoredField(OrderEntity.SHOP_ID)			//设置返回字段
				.setFrom(0)
				.setSize(1)
				.setExplain(false);
		SearchResponse sr = searchRequestBuilder.get();
		return sr.getHits().getTotalHits();
	}

	@Override
	public Double sumOrderReturnedPurchaseAmountByShopIdAndPosMan(Long shopId, Long openOrderManId, Date fromReturnedPurchaseTime, Date toReturnedPurchaseTime) {
		return ServiceManager.sellReturnedPurchaseOrderService.sumReturnedAmountByShopIdAndCashierId(shopId, openOrderManId, fromReturnedPurchaseTime, toReturnedPurchaseTime);
	}

	@Override
	public Double sumOrderPayWayAmountByShopIdAndPosMan(Long shopId, Long openOrderManId, Date formCreateDate, Date toCreateDate, PayWayTypeCodeEnum payWayTypeCode) {
		OrderSearchParam searchParam = new OrderSearchParam();
		searchParam.setShopId(shopId);
		searchParam.setOpenOrderManId(openOrderManId);
		searchParam.setFormCreateDate(formCreateDate);
		searchParam.setToCreateDate(toCreateDate);
		searchParam.setPayWayType(payWayTypeCode.toCode());

		SearchRequestBuilder searchRequestBuilder = ESUtils.getClient()
				.prepareSearch(IndexTypeCodeEnum.ORDER.toCode())
				.setTypes(IndexTypeCodeEnum.ORDER.toCode())
				.setSearchType(SearchType.DEFAULT) //??? SearchType.QUERY_THEN_FETCH
				.setQuery(buildQuery(searchParam)) //查询条件设置
				.addStoredField(OrderEntity.SHOP_ID)			//设置返回字段
				.setExplain(false);
		SearchResponse sr = searchRequestBuilder.get();
		Double sumTotalAmount = 0d;
		List<Map<String, Object>> list = SearchUtils.getSearchResult(sr);
		if(list.size() == 0){
			return sumTotalAmount;
		}
		TermsAggregationBuilder tb = AggregationBuilders.terms(OrderEntity.SHOP_ID).field(OrderEntity.SHOP_ID);
		tb.subAggregation(AggregationBuilders.sum(OrderEntity.ORDER_TOTAL_AMOUNT).field(OrderEntity.ORDER_TOTAL_AMOUNT))
				.subAggregation(AggregationBuilders.sum(OrderEntity.MEDICAL_INSURANCE_PAYMENT_AMOUNT).field(OrderEntity.MEDICAL_INSURANCE_PAYMENT_AMOUNT));
		searchRequestBuilder.addAggregation(tb);
		LongTerms tms =  searchRequestBuilder.get().getAggregations().get(OrderEntity.SHOP_ID);
		Double orderTotalAmount = 0D;
		Double totalMedicalInsurancePaymentAmount = 0D;
		for(Bucket bucket: tms.getBuckets()){
			LongTerms.Bucket longBucket = (LongTerms.Bucket)bucket;
			Aggregations aggregations = longBucket.getAggregations();
			List<Aggregation> aggregationList = aggregations.asList();
			for(Aggregation aggregation: aggregationList){
				Double sum = ((InternalSum) aggregation).getValue();
				if (OrderEntity.ORDER_TOTAL_AMOUNT.equals(aggregation.getName())){
					orderTotalAmount = BigDecimalUtil.add(sum, orderTotalAmount);
				}else if (OrderEntity.MEDICAL_INSURANCE_PAYMENT_AMOUNT.equals(aggregation.getName())){
					totalMedicalInsurancePaymentAmount = BigDecimalUtil.add(sum, totalMedicalInsurancePaymentAmount);
				}
			}
		}

		return BigDecimalUtil.subtract(orderTotalAmount, totalMedicalInsurancePaymentAmount);
	}

	@Override
	public Double sumOrderReturnSmallAmountByShopIdAndPosMan(Long shopId, Long openOrderManId, Date formCreateDate, Date toCreateDate) {
		OrderSearchParam searchParam = new OrderSearchParam();
		searchParam.setShopId(shopId);
		searchParam.setOpenOrderManId(openOrderManId);
		searchParam.setFormCreateDate(formCreateDate);
		searchParam.setToCreateDate(toCreateDate);

		SearchRequestBuilder searchRequestBuilder = ESUtils.getClient()
				.prepareSearch(IndexTypeCodeEnum.ORDER.toCode())
				.setTypes(IndexTypeCodeEnum.ORDER.toCode())
				.setSearchType(SearchType.DEFAULT) //??? SearchType.QUERY_THEN_FETCH
				.setQuery(buildQuery(searchParam)) //查询条件设置
				.addStoredField(OrderEntity.SHOP_ID)			//设置返回字段
				.addStoredField(OrderEntity.RETURN_SMALL)			//设置返回字段
				.setExplain(false);
		SearchResponse sr = searchRequestBuilder.get();
		Double sumTotalAmount = 0d;
		List<Map<String, Object>> list = SearchUtils.getSearchResult(sr);
		if(list.size() == 0){
			return sumTotalAmount;
		}
		TermsAggregationBuilder tb = AggregationBuilders.terms(OrderEntity.SHOP_ID).field(OrderEntity.SHOP_ID);
		SumAggregationBuilder sb = AggregationBuilders.sum(OrderEntity.RETURN_SMALL).field(OrderEntity.RETURN_SMALL);
		tb.subAggregation(sb);
		searchRequestBuilder.addAggregation(tb);
		LongTerms tms =  searchRequestBuilder.get().getAggregations().get(OrderEntity.SHOP_ID);
		for(Bucket bucket: tms.getBuckets()){
			LongTerms.Bucket longBucket = (LongTerms.Bucket)bucket;
			Aggregations aggregations = longBucket.getAggregations();
			List<Aggregation> aggregationList = aggregations.asList();
			for(Aggregation aggregation: aggregationList){
				Double sum = (Double)((InternalSum) aggregation).getValue();
				sumTotalAmount = BigDecimalUtil.add(sumTotalAmount, sum);
			}
		}

		return sumTotalAmount;
	}

	@Override
	public Double sumOrderMedicalInsuranceAmountByShopIdAndPosMan(Long shopId, Long openOrderManId, Date formCreateDate, Date toCreateDate) {
		OrderSearchParam searchParam = new OrderSearchParam();
		searchParam.setShopId(shopId);
		searchParam.setOpenOrderManId(openOrderManId);
		searchParam.setFormCreateDate(formCreateDate);
		searchParam.setToCreateDate(toCreateDate);

		SearchRequestBuilder searchRequestBuilder = ESUtils.getClient()
				.prepareSearch(IndexTypeCodeEnum.ORDER.toCode())
				.setTypes(IndexTypeCodeEnum.ORDER.toCode())
				.setSearchType(SearchType.DEFAULT) //??? SearchType.QUERY_THEN_FETCH
				.setQuery(buildQuery(searchParam)) //查询条件设置
				.addStoredField(OrderEntity.SHOP_ID)			//设置返回字段
				.addStoredField(OrderEntity.MEDICAL_INSURANCE_PAYMENT_AMOUNT)			//设置返回字段
				.setExplain(false);
		SearchResponse sr = searchRequestBuilder.get();
		Double sumTotalAmount = 0d;
		List<Map<String, Object>> list = SearchUtils.getSearchResult(sr);
		if(list.size() == 0){
			return sumTotalAmount;
		}
		TermsAggregationBuilder tb = AggregationBuilders.terms(OrderEntity.SHOP_ID).field(OrderEntity.SHOP_ID);
		SumAggregationBuilder sb = AggregationBuilders.sum(OrderEntity.MEDICAL_INSURANCE_PAYMENT_AMOUNT).field(OrderEntity.MEDICAL_INSURANCE_PAYMENT_AMOUNT);
		tb.subAggregation(sb);
		searchRequestBuilder.addAggregation(tb);
		LongTerms tms =  searchRequestBuilder.get().getAggregations().get(OrderEntity.SHOP_ID);
		for(Bucket bucket: tms.getBuckets()){
			LongTerms.Bucket longBucket = (LongTerms.Bucket)bucket;
			Aggregations aggregations = longBucket.getAggregations();
			List<Aggregation> aggregationList = aggregations.asList();
			for(Aggregation aggregation: aggregationList){
				Double sum = (Double)((InternalSum) aggregation).getValue();
				sumTotalAmount = BigDecimalUtil.add(sumTotalAmount, sum);
			}
		}
		return sumTotalAmount;
	}

	@Override
	public OrderStatVo getOrderStatVo(SellSummarySearchParams searchParams) {
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
				.must(QueryBuilders.termQuery(OrderEntity.SHOP_ID, searchParams.getShopId()));
		if (StringUtils.isNotBlank(searchParams.getOrderNum())){
			queryBuilder.must(QueryBuilders.termQuery(OrderEntity.ORDER_NUM, searchParams.getOrderNum()));
		}
		if (StringUtils.isNotBlank(searchParams.getOrderSourceCode())){
			queryBuilder.must(QueryBuilders.termQuery(OrderEntity.ORDER_SOURCE_CODE, searchParams.getOrderSourceCode()));
		}
		if (StringUtils.isNotBlank(searchParams.getOrderStateCode())){
			queryBuilder.must(QueryBuilders.termQuery(OrderEntity.ORDER_STATE_CODE, searchParams.getOrderStateCode()));
		}
		if (StringUtils.isNotBlank(searchParams.getKeyword())){
			String keyword = searchParams.getKeyword();
			BoolQueryBuilder subQueryBuilder = QueryBuilders.boolQuery()
					.should(QueryBuilders.termQuery(OrderItemEntity.GOODS_NM, keyword))
					.should(QueryBuilders.termQuery(OrderItemEntity.GOODS_CODING, keyword))
					.should(QueryBuilders.termQuery(OrderItemEntity.COMMON_NM, keyword))
					.should(QueryBuilders.termQuery(OrderItemEntity.GOODS_PINYIN, keyword));
			queryBuilder.must(subQueryBuilder);
		}
		SearchRequestBuilder requestBuilder =  ESUtils.getClient()
				.prepareSearch(IndexTypeCodeEnum.ORDER.toCode())
				.setTypes(IndexTypeCodeEnum.ORDER.toCode())
				.setSearchType(SearchType.DEFAULT)
				.setQuery(queryBuilder)
				.addAggregation(AggregationBuilders.sum(OrderEntity.ORDER_TOTAL_AMOUNT).field(OrderEntity.ORDER_TOTAL_AMOUNT))
				.addAggregation(AggregationBuilders.sum(OrderItemEntity.GOODS_COST_TOTAL_AMOUNT).field(OrderItemEntity.GOODS_COST_TOTAL_AMOUNT))
				.addAggregation(AggregationBuilders.sum(OrderItemEntity.QUANTITY).field(OrderItemEntity.QUANTITY))
				.setExplain(false);

		Aggregations aggregations = requestBuilder.get().getAggregations();
		List<Aggregation> list = aggregations.asList();
		Double amountReceivable = 0D;//应收金额
		Double costTotal = 0D;//总成本
		Long goodsTotalNum = 0L; //商品数量总计
		for (Aggregation aggregation : list){
			InternalSum internalSum = (InternalSum) aggregation;
			String name = internalSum.getName();
			Double value = internalSum.getValue();
			if (name.equals(OrderItemEntity.QUANTITY)){
				goodsTotalNum += value.longValue();
			}else if (name.equals(OrderEntity.ORDER_TOTAL_AMOUNT)){
				amountReceivable = BigDecimalUtil.add(amountReceivable, value);
			}else if (name.equals(OrderItemEntity.GOODS_COST_TOTAL_AMOUNT)){
				costTotal = BigDecimalUtil.add(costTotal, value);
			}
		}
		OrderStatVo statVo = new OrderStatVo();
		statVo.setAmountReceivable(amountReceivable);
		//TODO lxh 由于现阶段没做优惠相关，所以实收金额=应收金额
		statVo.setAmountReceived(amountReceivable);
		statVo.setGoodsTotalNum(goodsTotalNum);
		statVo.setProfit(BigDecimalUtil.subtract(amountReceivable, costTotal));
		return statVo;
	}

	@Override
	public Page<SellSummaryPageVo> querySellSummary(Pageable pageable, SellSummarySearchParams searchParams) {
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
				.must(QueryBuilders.termQuery(OrderEntity.SHOP_ID, searchParams.getShopId()));
		if (StringUtils.isNotBlank(searchParams.getOrderNum())){
			queryBuilder.must(QueryBuilders.termQuery(OrderEntity.ORDER_NUM, searchParams.getOrderNum()));
		}
		if (StringUtils.isNotBlank(searchParams.getOrderSourceCode())){
			queryBuilder.must(QueryBuilders.termQuery(OrderEntity.ORDER_SOURCE_CODE, searchParams.getOrderSourceCode()));
		}
		if (StringUtils.isNotBlank(searchParams.getOrderStateCode())){
			queryBuilder.must(QueryBuilders.termQuery(OrderEntity.ORDER_STATE_CODE, searchParams.getOrderStateCode()));
		}
		if (StringUtils.isNotBlank(searchParams.getKeyword())){
			String keyword = searchParams.getKeyword();
			BoolQueryBuilder subQueryBuilder = QueryBuilders.boolQuery()
					.should(QueryBuilders.termQuery(OrderItemEntity.GOODS_NM, keyword))
					.should(QueryBuilders.termQuery(OrderItemEntity.GOODS_CODING, keyword))
					.should(QueryBuilders.termQuery(OrderItemEntity.COMMON_NM, keyword))
					.should(QueryBuilders.termQuery(OrderItemEntity.GOODS_PINYIN, keyword));
			queryBuilder.must(subQueryBuilder);
		}

		SearchRequestBuilder requestBuilder =  ESUtils.getClient()
				.prepareSearch(IndexTypeCodeEnum.ORDER.toCode())
				.setTypes(IndexTypeCodeEnum.ORDER.toCode())
				.setSearchType(SearchType.DEFAULT)
				.setQuery(queryBuilder)
				.setFrom(pageable.getPageNumber()*pageable.getPageSize())
				.setSize(pageable.getPageSize())
				.addSort(OrderEntity.CREATE_DATE, SortOrder.DESC)
				.addStoredField(OrderEntity.ID)
				.setExplain(false);

		SearchResponse sr = requestBuilder.get();
		List<Map<String, Object>> list = SearchUtils.getSearchResult(sr);
		List<SellSummaryPageVo> voList = new ArrayList<>();
		for (Map<String, Object> map : list){
			Long orderId = (Long) map.get(OrderEntity.ID);
			voList.add(this.budSellSummaryVo(orderId));
		}
		return new PageImpl<SellSummaryPageVo>(voList, pageable, sr.getHits().getTotalHits());
	}

	private SellSummaryPageVo budSellSummaryVo(Long orderId){
		Order order = this.findOne(orderId);
		SellSummaryPageVo vo = CommonUtil.copyProperties(order, new SellSummaryPageVo());
		if (order.getOpenOrderManId() != null){
			SysUser sysUser = ServiceManager.sysUserService.findOne(order.getOpenOrderManId());
            vo.setOpenOrderMan(sysUser == null ? "" : sysUser.getRealName());
		}
		vo.setOrderId(orderId);
		vo.setSellTimeString(order.getCreateDateString());
		List<OrderItem> orderItemList = ServiceManager.orderItemService.findByOrderId(orderId);
		Long goodsTotalNum = 0L;//商品总数量
		Double costTotalAmount = 0D;//总成本
		for (OrderItem orderItem : orderItemList){
			goodsTotalNum += orderItem.getQuantity();
			costTotalAmount = BigDecimalUtil.add(costTotalAmount, orderItem.getGoodsCostTotalAmount());
		}
		//应收金额=实收金额
		vo.setAmountReceivable(order.getOrderTotalAmount());
		vo.setAmountReceived(order.getOrderTotalAmount());
		vo.setCostTotalAmount(costTotalAmount);
		vo.setGoodsTotalNum(goodsTotalNum);
		return vo;
	}

	private OrderPageVo buildOrderPageVo(Long orderId){
		OrderPageVo orderPageVo = new OrderPageVo();
		Order order = this.findOne(orderId);
		CommonUtil.copyProperties(order, orderPageVo);
		orderPageVo.setIsPrescriptionOrder(BoolCodeEnum.NO.toName());
		orderPageVo.setIsEphedrineOrder(BoolCodeEnum.NO.toName());

		if(order.getOpenOrderManId()!=null){
			SysUser sysUser = ServiceManager.sysUserService.findOne(order.getOpenOrderManId());
			if(sysUser!=null){
				orderPageVo.setOpenOrderMan(sysUser.getRealName());
			}
		}

		if(order.getMemberId()!=null){
			Member member = ServiceManager.memberService.findOne(order.getMemberId());
			if(member!=null){
				orderPageVo.setMemberName(member.getName());
				orderPageVo.setMemberCardNum(member.getMemberCardNum());
			}
		}

		List<OrderItem> orderItemList = ServiceManager.orderItemService.findByOrderId(orderId);
		for(OrderItem orderItem : orderItemList){
			Goods goods = ServiceManager.goodsService.findOne(orderItem.getGoodsId());
			Long goodsId = goods.getId();
			switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())){
				case DRUG:	//药品
					GoodsDrug goodsDrug = ServiceManager.goodsDrugService.findByGoodsId(goodsId);
					if(BoolCodeEnum.YES==BoolCodeEnum.fromCode(goodsDrug.getIsEphedrine())){//麻黄碱
						orderPageVo.setIsEphedrineOrder(BoolCodeEnum.YES.toName());
					}

					if(PrescriptionDrugsTypeCodeEnum.OTC!=PrescriptionDrugsTypeCodeEnum.fromCode(goodsDrug.getPrescriptionDrugsTypeCode())){//处方药
						orderPageVo.setIsPrescriptionOrder(BoolCodeEnum.YES.toName());
					}
					break;
				case OTHER:	//其他
					break;
				case CHINESE_MEDICINE_PIECES:	//中药饮片
					GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goodsId);
					if(BoolCodeEnum.YES==BoolCodeEnum.fromCode(goodsChineseMedicinePieces.getIsEphedrine())){//麻黄碱
						orderPageVo.setIsEphedrineOrder(BoolCodeEnum.YES.toName());
					}

					if(PrescriptionDrugsTypeCodeEnum.OTC!=PrescriptionDrugsTypeCodeEnum.fromCode(goodsChineseMedicinePieces.getPrescriptionDrugsTypeCode())){//处方药
						orderPageVo.setIsPrescriptionOrder(BoolCodeEnum.YES.toName());
					}
					break;
				case FOOD_HEALTH:	//食品保健品
					break;
				case DAILY_NECESSITIES:	//日用品
					break;
				case MEDICAL_INSTRUMENTS:	//医疗器械
					break;
				case COSMETIC:	//化妆品
					break;
			}
		}

		return orderPageVo;
	}

	@Override
	public OrderStatisticsVo getOrderStatistics(String orderSourceCode, Long id) {
		OrderStatisticsVo orderStatisticsVo = new OrderStatisticsVo();
		orderStatisticsVo.setStatisticsDays(this.statisticsDays);

		if(id==null){
			orderStatisticsVo.setOrderQuantity(0);
			orderStatisticsVo.setTotalAmount(0D);
			orderStatisticsVo.setWaitSendQuantity(0);
			orderStatisticsVo.setAlreadySendedQuantity(0);
		}else{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - this.statisticsDays);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);

			orderStatisticsVo.setOrderQuantity(this.getOrderRepository().countOrderQuantity(id, orderSourceCode, calendar.getTime()));
			Double totalAmount = this.getOrderRepository().countTotalAmount(id, orderSourceCode, calendar.getTime());
			orderStatisticsVo.setTotalAmount(totalAmount==null ? 0 : BigDecimalUtil.add(totalAmount, 0));
			orderStatisticsVo.setWaitSendQuantity(this.getOrderRepository().countOrderStateQuantity(id, orderSourceCode, OrderStateCodeEnum.WAIT_SEND.toCode()));
			orderStatisticsVo.setAlreadySendedQuantity(this.getOrderRepository().countOrderStateQuantity(id, orderSourceCode, OrderStateCodeEnum.ALREADY_SENDED.toCode()));
		}

		return orderStatisticsVo;
	}

	@Override
	public Order findOne(Long weShopId, Long id) {
		return this.getOrderRepository().findOne(weShopId, id);
	}

	@Override
	public List<Order> listOrderByWeChatUserId(Long weChatUserId, String orderSourceCode) {
		return getOrderRepository().listOrderByWeChatUserId(weChatUserId,orderSourceCode);
	}

	@Override
	public Page<Order> query(Pageable pageable, Long wechatUserId) {
		return this.getOrderRepository().query(pageable, wechatUserId);
	}

	@Override
	public Order findByIdAndWeChatUserId(Long id, Long weChatUserId) {
		return getOrderRepository().findByIdAndWechatUserId(id, weChatUserId);
	}

	@Override
	public Page<OrderPageVo> findShiftRangeOrder(Pageable pageable, Long shopId, Long openOrderManId, String formCreateDateString, String toCreateDateString) throws ParseException {
		OrderSearchParam param = new OrderSearchParam();
		param.setShopId(shopId);
		param.setFormCreateDate(DateTimeUtils.convertStringToDateTime(formCreateDateString));
		param.setToCreateDate(DateTimeUtils.convertStringToDateTime(toCreateDateString));
		param.setOpenOrderManId(openOrderManId);
		SearchRequestBuilder searchRequestBuilder = ESUtils.getClient()
				.prepareSearch(IndexTypeCodeEnum.ORDER.toCode())
				.setTypes(IndexTypeCodeEnum.ORDER.toCode())
				.setSearchType(SearchType.DEFAULT)
				.setQuery(buildQuery(param)) //查询条件设置
				.addSort(OrderEntity.CREATE_DATE, SortOrder.ASC)
				.addStoredField(OrderEntity.ID)			//设置返回字段
				.setFrom(pageable.getPageNumber() * pageable.getPageSize())
				.setSize(pageable.getPageSize())
				.setExplain(false);
		SearchResponse sr = searchRequestBuilder.get();
		List<Map<String, Object>> list = SearchUtils.getSearchResult(sr);
		List<OrderPageVo> orderPageVoList = new ArrayList<>();
		for (Map<String, Object> map : list) {
			Long id = (Long) map.get(OrderEntity.ID);
			orderPageVoList.add(this.buildOrderPageVo(id));
		}
		return new PageImpl<OrderPageVo>(orderPageVoList, pageable, sr.getHits().getTotalHits());
	}

	@Override
	@Transactional
	public void updateRejectOrder(Order order) {
		List<OrderSendOutBatch> sendOutBatchList = ServiceManager.orderSendOutBatchService.findByOrderId(order.getId());
		for(OrderSendOutBatch sendOutBatch : sendOutBatchList) {
			GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(sendOutBatch.getGoodsBatchId());
			goodsBatch.setCurrentStock(goodsBatch.getCurrentStock() + sendOutBatch.getQuantity());
			ServiceManager.goodsBatchService.update(goodsBatch);
		}
	}
}