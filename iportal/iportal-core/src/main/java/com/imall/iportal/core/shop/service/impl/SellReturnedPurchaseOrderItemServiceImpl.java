package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.GoodsTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.entity.*;
import com.imall.iportal.core.shop.repository.SellReturnedPurchaseOrderItemRepository;
import com.imall.iportal.core.shop.service.SellReturnedPurchaseOrderItemService;
import com.imall.iportal.core.shop.vo.OrderItemVo;
import com.imall.iportal.core.shop.vo.SRPReadyOrderItemVo;
import com.imall.iportal.core.shop.vo.SellReturnedPurchaseOrderItemPageVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 销售 退货 订单 项(服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class SellReturnedPurchaseOrderItemServiceImpl extends AbstractBaseService<SellReturnedPurchaseOrderItem, Long> implements SellReturnedPurchaseOrderItemService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private SellReturnedPurchaseOrderItemRepository getSellReturnedPurchaseOrderItemRepository() {
		return (SellReturnedPurchaseOrderItemRepository) baseRepository;
	}

	@Override
	public Long queryCalcReturnedQuantity(Long orderItemId) {
		Long returnedQuantity = getSellReturnedPurchaseOrderItemRepository().calcReturnedQuantity(orderItemId);
		returnedQuantity = returnedQuantity==null ? 0 : returnedQuantity;
		return returnedQuantity;
	}

	@Override
	public Long findSellReturnPurchaseGoodsQuantity(Long sellReturnPurchaseOrderId) {
		return getSellReturnedPurchaseOrderItemRepository().sumSellReturnPurchaseGoodsQuantity(sellReturnPurchaseOrderId);
	}

	@Override
	public List<SRPReadyOrderItemVo> findVoByOrderId(Long orderId) {
		List<OrderItemVo> list = ServiceManager.orderItemService.findVoByOrderId(orderId);
		List<SRPReadyOrderItemVo> resultList = new ArrayList<>();
		for(OrderItemVo itemVo: list){
			SRPReadyOrderItemVo readyItemVo = new SRPReadyOrderItemVo();
			CommonUtil.copyProperties(itemVo, readyItemVo);
			Long allowReturnedQuantity = itemVo.getQuantity() - queryCalcReturnedQuantity(itemVo.getId());
//			if(!(allowReturnedQuantity<0)){//可能数量只能大于0
//				continue;
//			}
			readyItemVo.setAllowReturnedQuantity(allowReturnedQuantity);
			resultList.add(readyItemVo);
		}
		return resultList;
	}

	@Override
	public List<SellReturnedPurchaseOrderItemPageVo> findItemPageVoByReturnedPurchaseOrderId(Long id) {
		List<SellReturnedPurchaseOrderItem> itemList = getSellReturnedPurchaseOrderItemRepository().findByReturnedPurchaseOrderId(id);
		List<SellReturnedPurchaseOrderItemPageVo> pageVoList = new ArrayList<>();
		for(SellReturnedPurchaseOrderItem item : itemList) {
			pageVoList.add(buildPageVo(item));
		}
		return pageVoList;
	}

	@Override
	public List<SellReturnedPurchaseOrderItem> findByReturnedPurchaseOrderId(Long id) {
		return getSellReturnedPurchaseOrderItemRepository().findByReturnedPurchaseOrderId(id);
	}

	private SellReturnedPurchaseOrderItemPageVo buildPageVo(SellReturnedPurchaseOrderItem item) {
		SellReturnedPurchaseOrderItemPageVo pageVo = new SellReturnedPurchaseOrderItemPageVo();
		OrderItem orderItem = ServiceManager.orderItemService.findOne(item.getOrderItemId());
		Goods goods = ServiceManager.goodsService.findOne(orderItem.getGoodsId());
		GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(item.getGoodsBatchId());
		SellReturnedPurchaseOrder sellReturnedPurchaseOrder = ServiceManager.sellReturnedPurchaseOrderService.findOne(item.getReturnedPurchaseOrderId());
		pageVo.setSellerName(ServiceManager.sysUserService.findOne(sellReturnedPurchaseOrder.getCashierId()).getRealName());
		pageVo.setReturnedPurchaseQuantity(item.getReturnedPurchaseQuantity());
		pageVo.setUnitPrice(item.getUnitPrice());
		pageVo.setTotalAmount(item.getTotalAmount());
		pageVo.setGoodsCode(goods.getGoodsCode());
		pageVo.setGoodsNm(goods.getGoodsNm());
		pageVo.setCommonNm(goods.getCommonNm());
		pageVo.setSpec(goods.getSpec());
		pageVo.setUnit(goods.getUnit());
		pageVo.setProduceManufacturer(goods.getProduceManufacturer());
		pageVo.setBatch(goodsBatch.getBatch());
		pageVo.setValidDateString(goodsBatch.getValidDateString());

		if (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode()) == GoodsTypeCodeEnum.CHINESE_MEDICINE_PIECES) {
			GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goods.getId());
			pageVo.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
		}
		return pageVo;
	}
}