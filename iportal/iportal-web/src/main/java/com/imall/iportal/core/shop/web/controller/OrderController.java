package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.commons.dicts.IndexTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.service.ISalesPosShoppingFlowService;
import com.imall.iportal.core.shop.vo.OrderSearchParam;
import com.imall.iportal.core.shop.vo.SellSummarySearchParams;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseRestSpringController {

	@Autowired
	private ISalesPosShoppingFlowService salesPosShoppingFlowService;

	//todo: yang test 生成测试数据，到时要删除
	@RequestMapping(value = "/initEs",method = RequestMethod.GET)
	@ResponseBody
	public Object initEs(Long id){
		/*for(int i=0; i<100; i++){
			SalesPosShoppingCart shoppingCart = salesPosShoppingFlowService.getCartOrNewCart(1L);
			salesPosShoppingFlowService.addCartItem(shoppingCart.getUniqueKey(), 1L, i%3 + 9L, true);
			SalesPosOrderSaveValid orderSaveValid = new SalesPosOrderSaveValid();
			orderSaveValid.setMedicalInsurancePaymentAmount(10D);
			orderSaveValid.setPaymentWayCode(PayWayTypeCodeEnum.CASH_PAY.toCode());
			orderSaveValid.setRealGeveCashAmount(100D);
			ServiceManager.orderService.saveSalesPosOrder(1L, shoppingCart, orderSaveValid);
		}*/



		/*SellReturnedPurchaseOrder order = new SellReturnedPurchaseOrder();
		order.setOrderId(2L);
		order.setShopId(1L);
		order.setRefundTotalAmount(4D);
		order.setRealReturnCashAmount(10D);
		order.setReturnSmall(6D);
		order.setCashierId(1L);
		order.setIsOverallReturnedPurchase(BoolCodeEnum.NO.toCode());
		order.setApproveManId(2L);
		order.setRemark("");
		order.setReturnedPurchaseTime(new Date());

		SellReturnedPurchaseOrderItem orderItem = new SellReturnedPurchaseOrderItem();
		orderItem.setOrderItemId(1L);
		orderItem.setReturnedPurchaseOrderId(1L);
		orderItem.setReturnedPurchaseQuantity(2L);
		orderItem.setUnitPrice(2D);
		orderItem.setTotalAmount(4D);
		orderItem.setBatchNum("10001");
		orderItem.setModPriceReason("");
		orderItem.setModQuantityReason("");

		ServiceManager.sellReturnedPurchaseOrderService.save(order);
		ServiceManager.sellReturnedPurchaseOrderItemService.save(orderItem);*/

		//ServiceManager.esIndexQueueService.rebuildIndex(IndexTypeCodeEnum.ORDER);
		ServiceManager.esIndexQueueService.rebuildIndex(IndexTypeCodeEnum.SELL_RETURNED_PURCHASE_ORDER);
		return true;
	}

	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(){
		return null;
	}

	@RequestMapping(value = "/commonOrderQuery",method = RequestMethod.GET)
	@ResponseBody
	public Object commonOrderQuery(ModelMap model,@PageableDefault(page = 0, size = 10) Pageable pageable, OrderSearchParam orderSearchParam, @CurrUser CurrUserVo currUserVo){
		orderSearchParam.setShopId(currUserVo.getShopId());
		return ServiceManager.orderService.commonOrderQuery(pageable,  orderSearchParam);
	}

	@RequestMapping(value = "/sumOrderTotalAmount",method = RequestMethod.GET)
	@ResponseBody
	public Object sumOrderTotalAmount(ModelMap model, @CurrUser CurrUserVo currUserVo, OrderSearchParam orderSearchParam){
		orderSearchParam.setShopId(currUserVo.getShopId());
		return ServiceManager.orderService.sumOrderTotalAmountByShopId(orderSearchParam);
	}

	@RequestMapping(value = "/querySellSummary",method = RequestMethod.GET)
	@ResponseBody
	public Object querySellSummary(@PageableDefault(page = 0, size = 10) Pageable pageable, @CurrUser CurrUserVo currUserVo, SellSummarySearchParams sellSummarySearchParams){
		sellSummarySearchParams.setShopId(currUserVo.getShopId());
		return ServiceManager.orderService.querySellSummary(pageable, sellSummarySearchParams);
	}

	@RequestMapping(value = "/statOrder",method = RequestMethod.GET)
	@ResponseBody
	public Object statOrder(@CurrUser CurrUserVo currUserVo, SellSummarySearchParams searchParams){
	    searchParams.setShopId(currUserVo.getShopId());
		return ServiceManager.orderService.getOrderStatVo(searchParams);
	}

	@RequestMapping(value = "/findShiftRangeOrder",method = RequestMethod.GET)
	@ResponseBody
	public Object findShiftRangeOrder(@PageableDefault(page = 0, size = 10) Pageable pageable, @CurrUser CurrUserVo currUserVo, Long openOrderManId, String formCreateDateString, String toCreateDateString) throws ParseException {
		return ServiceManager.orderService.findShiftRangeOrder(pageable, currUserVo.getShopId(), openOrderManId, formCreateDateString, toCreateDateString);
	}
}

