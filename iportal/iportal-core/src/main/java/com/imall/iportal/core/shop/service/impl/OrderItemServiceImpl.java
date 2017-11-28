package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.commons.dicts.PrescriptionDrugsTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.shop.entity.*;
import com.imall.iportal.core.shop.repository.OrderItemRepository;
import com.imall.iportal.core.shop.service.OrderItemService;
import com.imall.iportal.core.shop.vo.OrderItemVo;
import com.imall.iportal.core.shop.vo.PurchaseOrderGoodsVo;
import com.imall.iportal.core.shop.vo.SellDetailPageVo;
import com.imall.iportal.core.shop.vo.SellSummarySearchParams;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class OrderItemServiceImpl extends AbstractBaseService<OrderItem, Long> implements OrderItemService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private OrderItemRepository getOrderItemRepository() {
		return (OrderItemRepository) baseRepository;
	}

	@Override
	public List<OrderItem> findByOrderId(Long orderId) {
		return getOrderItemRepository().findByOrderId(orderId);
	}

	@Override
	public Long findOrderTotalGoodsQuantity(Long orderId) {
		return getOrderItemRepository().sumQuantityByOrderId(orderId);
	}

	@Override
	public List<OrderItemVo> findVoByOrderId(Long orderId) {
		List<OrderItemVo> resultList = new ArrayList<>();
		List<OrderItem> list = findByOrderId(orderId);
		for(OrderItem item: list){
			OrderItemVo itemVo = new OrderItemVo();
			CommonUtil.copyProperties(item, itemVo);
			List<OrderSendOutBatch> orderSendOutBatch = ServiceManager.orderSendOutBatchService.findByOrderIdAndOrderItemId(orderId,item.getId());
			if(CollectionUtils.isNotEmpty(orderSendOutBatch)){
				itemVo.setBatch(orderSendOutBatch.get(0).getBatch());
			}
			if(item.getSeller()!=null){
				SysUser sysUser = ServiceManager.sysUserService.findOne(item.getSeller());
				if(sysUser!=null){
					itemVo.setSellerName(sysUser.getRealName());
				}
			}
			resultList.add(itemVo);
		}
		return resultList;
	}

	@Override
	public Page<SellDetailPageVo> querySellDetailPageVo(Pageable pageable, SellSummarySearchParams searchParams) {
		//TODO lxh 暂无建立索引
		Page<OrderItem> resultPage = getOrderItemRepository().querySellDetailPageVo(pageable, searchParams);
		List<SellDetailPageVo> voList = new ArrayList<>();
		for (OrderItem orderItem : resultPage.getContent()){
			SellDetailPageVo vo = CommonUtil.copyProperties(orderItem, new SellDetailPageVo());
			//商品信息
			PurchaseOrderGoodsVo goodsVo = ServiceManager.goodsService.findPurchaseOrderGoodsVoById(orderItem.getGoodsId());
			vo.setDosageForm(goodsVo.getDosageForm());
			vo.setApprovalNumber(goodsVo.getApprovalNumber());
			vo.setIsEphedrine(StringUtils.isBlank(goodsVo.getIsEphedrine()) ? BoolCodeEnum.NO.toCode() : goodsVo.getIsEphedrine());
			if (StringUtils.isNotBlank(goodsVo.getPrescriptionDrugsTypeCode()) && PrescriptionDrugsTypeCodeEnum.RX == PrescriptionDrugsTypeCodeEnum.fromCode(goodsVo.getPrescriptionDrugsTypeCode())){
				vo.setIsPrescription(BoolCodeEnum.YES.toCode());
			}else {
				vo.setIsPrescription(BoolCodeEnum.NO.toCode());
			}
			//营业员
			if (orderItem.getSeller() != null){
				SysUser sysUser = ServiceManager.sysUserService.findOne(orderItem.getSeller());
				vo.setSellerNm(sysUser == null ? "" : sysUser.getRealName());
			}
			//订单信息
			Order order = ServiceManager.orderService.findOne(orderItem.getOrderId());
			vo.setOrderNum(order.getOrderNum());
			vo.setMemberCardNum(order.getMemberCardNum());
			vo.setSellTimeString(order.getCreateDateString());
			vo.setOrderSourceCode(order.getOrderSourceCode());
			vo.setOrderStateCode(order.getOrderStateCode());
			if (order.getMemberId() != null){
				Member member = ServiceManager.memberService.findOne(order.getMemberId());
				vo.setMobile(member.getMobile());
			}
			//货位
			List<OrderSendOutBatch> batchList = ServiceManager.orderSendOutBatchService.findByOrderIdAndOrderItemId(orderItem.getOrderId(), orderItem.getId());
			List<String> storageSpaceNmList = new ArrayList<>();
			for(OrderSendOutBatch batch: batchList){
				GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(batch.getGoodsBatchId());
				StorageSpace storageSpace = ServiceManager.storageSpaceService.findOne(goodsBatch.getStorageSpaceId());
				if(!storageSpaceNmList.contains(storageSpace.getStorageSpaceNm())){
					storageSpaceNmList.add(storageSpace.getStorageSpaceNm());
				}
			}
			vo.setStorageSpaceNm(StringUtils.join(storageSpaceNmList.toArray(), ","));
			voList.add(vo);
		}
		return new PageImpl<SellDetailPageVo>(voList, pageable, resultPage.getTotalElements());
	}

}