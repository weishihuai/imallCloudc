package com.imall.iportal.frontend.wechat.order.service;

import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.OrderStateCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.Order;
import com.imall.iportal.core.shop.entity.OrderItem;
import com.imall.iportal.core.shop.entity.WeShop;
import com.imall.iportal.core.weshop.vo.OrderDetailVo;
import com.imall.iportal.core.weshop.vo.OrderItemDetailVo;
import com.imall.iportal.core.weshop.vo.OrderPageVo;
import com.imall.iportal.frontend.wechat.common.web.WebContextFactory;
import com.imall.iportal.frontend.wechat.member.proxy.WeChatUserProxy;
import com.imall.iportal.frontend.wechat.order.proxy.OrderDetailProxy;
import com.imall.iportal.frontend.wechat.order.proxy.OrderItemProxy;
import com.imall.iportal.frontend.wechat.order.proxy.OrderPageProxy;
import com.imall.iportal.frontend.wechat.order.vo.OrderStateUpdateVo;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderProxyService {
    Logger logger = Logger.getLogger(OrderProxyService.class);

    /**
     * 分页查询订单信息
     * @param pageable 分页对象
     * @return
     * @throws ParseException
     */
    public Page<OrderPageProxy> query(Pageable pageable) throws ParseException {
        WeChatUserProxy weChatUserProxy = WebContextFactory.getWebContext().getFrontEndUser();
        Page<OrderPageVo> voPage = ServiceManager.weShopOrderService.query(pageable, weChatUserProxy.getId());

        List<OrderPageProxy> proxyList = new ArrayList<>();
        for(OrderPageVo vo : voPage.getContent()){
            OrderPageProxy proxy = new OrderPageProxy();
            CommonUtil.copyProperties(vo, proxy);

            WeShop weShop = ServiceManager.weShopService.findOne(vo.getWeShopId());
            proxy.setShopNm(weShop.getShopNm());

            List<OrderItemDetailVo> itemDetailVoList = vo.getOrderItemDetailVoList();
            List<OrderItemProxy> itemProxyList = new ArrayList<>();
            Long totalQuantity = 0L;
            for(OrderItemDetailVo itemVo : itemDetailVoList){
                OrderItemProxy itemProxy = new OrderItemProxy();
                CommonUtil.copyProperties(itemVo, itemProxy);
                itemProxyList.add(itemProxy);
                totalQuantity = totalQuantity + itemVo.getQuantity();
            }
            proxy.setTotalQuantity(totalQuantity);
            proxy.setItemList(itemProxyList);
            proxy.setCreateDateString(vo.getCreateDateString());
            proxyList.add(proxy);
        }

        return new PageImpl<OrderPageProxy>(proxyList, pageable, proxyList.size());
    }

    /**
     * 订单详情
     * @param id 订单 ID
     * @return
     */
    public OrderDetailProxy findByIdAndWeChatUserId(Long id){
        OrderDetailVo vo = ServiceManager.weShopOrderService.findByIdAndWeChatUserId(id, WebContextFactory.getWebContext().getFrontEndUserId());
        OrderDetailProxy proxy = new OrderDetailProxy();
        CommonUtil.copyProperties(vo, proxy);

        StringBuilder sb = new StringBuilder();
        if(vo.getBookDeliveryTimeStart()==null){
            sb.append("立即送达");
        }else{
            Calendar todayCalendar = Calendar.getInstance();
            todayCalendar.setTime(new Date());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(vo.getBookDeliveryTimeStart());
            switch (calendar.get(Calendar.DAY_OF_YEAR) - todayCalendar.get(Calendar.DAY_OF_YEAR)){
                case 0:
                    sb.append("今天");
                    break;
                case 1:
                    sb.append("明天");
                    break;
                case 2:
                    sb.append("后天");
                    break;
                default:
                    sb.append(DateTimeUtils.convertDateToString(vo.getBookDeliveryTimeStart()));
                    break;
            }
            sb.append("[");
            sb.append(DateTimeUtils.getDayOfWeekStrExt(vo.getBookDeliveryTimeStart()));
            sb.append("]");
            proxy.setArrivedTime(DateTimeUtils.convertMmTimeToString(vo.getBookDeliveryTimeStart()) + "-" + DateTimeUtils.convertMmTimeToString(vo.getBookDeliveryTimeEnd()));
        }
        proxy.setArrivedDay(sb.toString());

        WeShop weShop = ServiceManager.weShopService.findByShopId(vo.getShopId());
        proxy.setShopNm(weShop.getShopNm());
        proxy.setShopContactTel(weShop.getContactTel());

        List<OrderItemProxy> itemList = new ArrayList<>();
        for(OrderItemDetailVo itemVo : vo.getOrderItemDetailVoList()){
            OrderItemProxy itemProxy = new OrderItemProxy();
            CommonUtil.copyProperties(itemVo, itemProxy);
            itemList.add(itemProxy);
        }
        proxy.setItemList(itemList);

        return proxy;
    }

    /**
     * 更新订单状态
     * @param orderStateUpdateVo
     */
    @Transactional
    public void updateOrderState(OrderStateUpdateVo orderStateUpdateVo){
        Order order = ServiceManager.orderService.findByIdAndWeChatUserId(orderStateUpdateVo.getId(), WebContextFactory.getWebContext().getFrontEndUserId());
        if (order == null){
            throw new BusinessException(ResGlobal.COMMON_ERROR, "订单不存在");
        }

        switch (OrderStateCodeEnum.fromCode(orderStateUpdateVo.getOrderStateCode())){
            case FINISH:
                //待发货、已关闭、已完成订单无法执行完成操作，只有已发货订单才能执行
                if(!OrderStateCodeEnum.ALREADY_SENDED.toCode().equals(order.getOrderStateCode())){
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.ORDER_STATE_ERROR);
                    throw new BusinessException(ResGlobalExt.ORDER_STATE_ERROR, message);
                }

                order.setOrderStateCode(orderStateUpdateVo.getOrderStateCode());
                order.setFinishTime(new Date());
                break;
            case CLOSE:
                //只有待发货才能执行关闭操作
                if(!OrderStateCodeEnum.WAIT_SEND.toCode().equals(order.getOrderStateCode())){
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.ORDER_STATE_ERROR);
                    throw new BusinessException(ResGlobalExt.ORDER_STATE_ERROR, message);
                }

                if(StringUtils.isBlank(orderStateUpdateVo.getCancelOrderReason())){
                    logger.error("关闭原因不能为空");
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_NOT_EMPTY);
                    throw new BusinessException(ResGlobalExt.COMMON_NOT_EMPTY, message);
                }
                //解锁库存
                ServiceManager.skuLockStockService.unLockStock(order.getOrderNum());
                order.setOrderStateCode(orderStateUpdateVo.getOrderStateCode());
                order.setCancelOrderReason(orderStateUpdateVo.getCancelOrderReason());
                order.setOrderCloseTime(new Date());
                break;
        }
        //更新订单状态
        ServiceManager.orderService.save(order);
    }

    /**
     * 再次购买
     */
    @Transactional
    public void buyAgain(Long id){
        //订单信息
        Order order = ServiceManager.orderService.findByIdAndWeChatUserId(id, WebContextFactory.getWebContext().getFrontEndUserId());
        if(order == null){
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"订单"}));
        }

        //订单项信息
        List<OrderItem> orderItemList = ServiceManager.orderItemService.findByOrderId(order.getId());
        WeChatUserProxy weChatUserProxy = WebContextFactory.getWebContext().getFrontEndUser();
        for(OrderItem orderItem : orderItemList){
            ServiceManager.normalShoppingFlowService.addCartItem(order.getShopId(), weChatUserProxy.getId(), orderItem.getSkuId(), orderItem.getQuantity(), false);
        }
    }
}
