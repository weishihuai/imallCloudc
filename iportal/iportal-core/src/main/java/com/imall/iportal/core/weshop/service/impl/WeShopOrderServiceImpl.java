package com.imall.iportal.core.weshop.service.impl;

import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.idfs.FileSystemEngine;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.util.BigDecimalUtil;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.PrintUtil;
import com.imall.commons.dicts.*;
import com.imall.iportal.core.elasticsearch.ESUtils;
import com.imall.iportal.core.elasticsearch.entity.OrderEntity;
import com.imall.iportal.core.elasticsearch.entity.OrderItemEntity;
import com.imall.iportal.core.elasticsearch.utils.SearchUtils;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.FileMng;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.*;
import com.imall.iportal.core.shop.vo.OrderStatisticsVo;
import com.imall.iportal.core.weshop.service.WeShopOrderService;
import com.imall.iportal.core.weshop.vo.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.PrinterException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class WeShopOrderServiceImpl implements WeShopOrderService {
    Logger logger = Logger.getLogger(WeShopOrderServiceImpl.class);

    private Integer paperWidth;

    @Value("${app.order.paperWidth:140}")
    public void setPaperWidth(Integer paperWidth) {
        this.paperWidth = paperWidth;
    }

    @Override
    public Page<OrderPageVo> query(Pageable pageable, OrderSearchParam orderSearchParam) throws ParseException {
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

    private QueryBuilder buildQuery(OrderSearchParam orderSearchParam) throws ParseException {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        //微门店ID
        queryBuilder.must(QueryBuilders.termQuery(OrderEntity.WE_SHOP_ID, orderSearchParam.getWeShopId()));

        //订单 来源 代码
        if(StringUtils.isNotBlank(orderSearchParam.getOrderSourceCode())){
            queryBuilder.must(QueryBuilders.termQuery(OrderEntity.ORDER_SOURCE_CODE, orderSearchParam.getOrderSourceCode()));
        }

        //订单 状态 代码
        if(StringUtils.isNotBlank(orderSearchParam.getOrderStateCode())){
            queryBuilder.must(QueryBuilders.termQuery(OrderEntity.ORDER_STATE_CODE, orderSearchParam.getOrderStateCode()));
        }

        //收货人 姓名
        if(StringUtils.isNotBlank(orderSearchParam.getReceiverName())){
            BoolQueryBuilder subQueryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.termQuery(OrderEntity.RECEIVER_NAME, orderSearchParam.getReceiverName()));
            queryBuilder.must(subQueryBuilder);
        }

        //联系 电话
        if(StringUtils.isNotBlank(orderSearchParam.getContactTel())){
            queryBuilder.must(QueryBuilders.termQuery(OrderEntity.CONTACT_TEL, orderSearchParam.getContactTel()));
        }

        //订单 编号
        if(StringUtils.isNotBlank(orderSearchParam.getOrderNum())){
            queryBuilder.must(QueryBuilders.termQuery(OrderEntity.ORDER_NUM, orderSearchParam.getOrderNum()));
        }

        //下单开始时间
        if(StringUtils.isNotBlank(orderSearchParam.getStartCreateDateString())){
            queryBuilder.must(QueryBuilders.rangeQuery(OrderEntity.CREATE_DATE).gte(orderSearchParam.getStartCreateDate().getTime()));
        }

        //下单结束时间
        if(StringUtils.isNotBlank(orderSearchParam.getEndCreateDateString())){
            queryBuilder.must(QueryBuilders.rangeQuery(OrderEntity.CREATE_DATE).lte(orderSearchParam.getEndCreateDate().getTime()));
        }

        //预约 送达 时间 开始
        if(StringUtils.isNotBlank(orderSearchParam.getBookDeliveryTimeStartString())){
            queryBuilder.must(QueryBuilders.rangeQuery(OrderEntity.BOOK_DELIVERY_TIME_START).gte(orderSearchParam.getBookDeliveryTimeStart().getTime()));
        }

        //预约 送达 时间 结束
        if(StringUtils.isNotBlank(orderSearchParam.getBookDeliveryTimeEndString())){
            queryBuilder.must(QueryBuilders.rangeQuery(OrderEntity.BOOK_DELIVERY_TIME_END).lte(orderSearchParam.getBookDeliveryTimeEnd().getTime()));
        }

        //支付 方式 代码
        if(StringUtils.isNotBlank(orderSearchParam.getPaymentWayCode())){
            queryBuilder.must(QueryBuilders.termQuery(OrderEntity.PAYMENT_WAY_CODE, orderSearchParam.getPaymentWayCode()));
        }

        //微信 用户 ID
        if(orderSearchParam.getWechatUserId()!=null){
            queryBuilder.must(QueryBuilders.termQuery(OrderEntity.WECHAT_USER_ID, orderSearchParam.getWechatUserId()));
        }

        //商品编码/通用名称/通用名称首字母/商品名称
        if(StringUtils.isNotBlank(orderSearchParam.getKeyword())){
            String keyword = orderSearchParam.getKeyword();
            BoolQueryBuilder subQueryBuilder = QueryBuilders.boolQuery()
                    .should(QueryBuilders.termQuery(OrderItemEntity.GOODS_CODING, keyword))
                    .should(QueryBuilders.termQuery(OrderItemEntity.COMMON_NM, keyword))
                    .should(QueryBuilders.termQuery(OrderItemEntity.GOODS_NM, keyword))
                    .should(QueryBuilders.termQuery(OrderItemEntity.GOODS_PINYIN, keyword));
            queryBuilder.must(subQueryBuilder);
        }

        return queryBuilder;
    }

    private OrderPageVo buildOrderPageVo(Long orderId){
        OrderPageVo orderPageVo = new OrderPageVo();
        Order order = ServiceManager.orderService.findOne(orderId);
        CommonUtil.copyProperties(order, orderPageVo);
        orderPageVo.setOrderItemDetailVoList(buildOrderItemDetailVo(orderId));
        return orderPageVo;
    }

    private List<OrderItemDetailVo> buildOrderItemDetailVo(Long orderId){
        List<OrderItemDetailVo> orderItemVoList = new ArrayList<>();
        List<OrderItem> orderItemList = ServiceManager.orderItemService.findByOrderId(orderId);
        for(OrderItem orderItem : orderItemList){
            OrderItemDetailVo orderItemDetailVo = new OrderItemDetailVo();
            CommonUtil.copyProperties(orderItem, orderItemDetailVo);
            Goods goods = ServiceManager.goodsService.findOne(orderItem.getGoodsId());
            orderItemDetailVo.setGoodsNm(goods.getGoodsNm());
            Long goodsId = goods.getId();

            //商品图片
            List<FileMng> fileMngList = ServiceManager.fileMngService.getList(FileObjectTypeCodeEnum.GOODS_PICT, goodsId);
            if(fileMngList!=null && fileMngList.size()>0){
                orderItemDetailVo.setGoodsPicUrl(FileSystemEngine.getFileSystem().getUrl(fileMngList.get(0).getFileId()));
            }

            switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())){
                case DRUG:	//药品
                    GoodsDrug goodsDrug = ServiceManager.goodsDrugService.findByGoodsId(goodsId);
                    orderItemDetailVo.setPrescriptionDrugsTypeCode(goodsDrug.getPrescriptionDrugsTypeCode());
                    orderItemDetailVo.setIsEphedrine(goodsDrug.getIsEphedrine());
                    break;
                case OTHER:	//其他
                    break;
                case CHINESE_MEDICINE_PIECES:	//中药饮片
                    GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goodsId);
                    orderItemDetailVo.setPrescriptionDrugsTypeCode(goodsChineseMedicinePieces.getPrescriptionDrugsTypeCode());
                    orderItemDetailVo.setIsEphedrine(goodsChineseMedicinePieces.getIsEphedrine());
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

            orderItemVoList.add(orderItemDetailVo);
        }

        return orderItemVoList;
    }

    @Override
    public OrderStatisticsVo getOrderStatistics(Long weShopId) {
        return ServiceManager.orderService.getOrderStatistics(OrderSourceCodeEnum.WEIXIN.toCode(), weShopId);
    }

    @Override
    public OrderDetailVo findById(Long weShopId, Long id) {
        //订单信息
        Order order = ServiceManager.orderService.findOne(weShopId, id);
        checkOrder(order, weShopId, id);
        OrderDetailVo orderDetailVo = new OrderDetailVo();
        CommonUtil.copyProperties(order, orderDetailVo);
        //订单项信息
        List<OrderItem> orderItemList = ServiceManager.orderItemService.findByOrderId(order.getId());
        List<OrderItemDetailVo> orderItemDetailVoList = new ArrayList<>();
        for(OrderItem orderItem : orderItemList){
            OrderItemDetailVo orderItemDetailVo = this.buildOrderItemDetailVo(orderItem);
            //发货批次信息
            List<OrderSendOutBatchDetailVo> orderSendOutBatchDetailVoList = new ArrayList<>();
            if(!OrderStateCodeEnum.WAIT_SEND.toCode().equals(order.getOrderStateCode())){
                List<OrderSendOutBatch> orderSendOutBatchList = ServiceManager.orderSendOutBatchService.findByOrderIdAndOrderItemId(order.getId(), orderItem.getId());

                for(OrderSendOutBatch orderSendOutBatch : orderSendOutBatchList){
                    OrderSendOutBatchDetailVo orderSendOutBatchDetailVo = new OrderSendOutBatchDetailVo();
                    CommonUtil.copyProperties(orderSendOutBatch, orderSendOutBatchDetailVo);
                    orderSendOutBatchDetailVoList.add(orderSendOutBatchDetailVo);
                }
            }
            orderItemDetailVo.setOrderSendOutBatchDetailVoList(orderSendOutBatchDetailVoList);
            orderItemDetailVoList.add(orderItemDetailVo);
        }
        orderDetailVo.setOrderItemDetailVoList(orderItemDetailVoList);
        return orderDetailVo;
    }

    private OrderItemDetailVo buildOrderItemDetailVo(OrderItem orderItem){
        OrderItemDetailVo orderItemDetailVo = new OrderItemDetailVo();
        CommonUtil.copyProperties(orderItem, orderItemDetailVo);
        //商品图片
        Goods goods = ServiceManager.goodsService.findOne(orderItem.getGoodsId());
        List<FileMng> fileMngList = ServiceManager.fileMngService.getList(FileObjectTypeCodeEnum.GOODS_PICT, goods.getId());
        if(fileMngList!=null && fileMngList.size()>0){
            orderItemDetailVo.setGoodsPicUrl(FileSystemEngine.getFileSystem().getUrl(fileMngList.get(0).getFileId()));
        }
        Long goodsId = goods.getId();
        switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())){
            case DRUG:	//药品
                GoodsDrug goodsDrug = ServiceManager.goodsDrugService.findByGoodsId(goodsId);
                orderItemDetailVo.setPrescriptionDrugsTypeCode(goodsDrug.getPrescriptionDrugsTypeCode());
                orderItemDetailVo.setIsEphedrine(goodsDrug.getIsEphedrine());
                break;
            case OTHER:	//其他
                break;
            case CHINESE_MEDICINE_PIECES:	//中药饮片
                GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goodsId);
                orderItemDetailVo.setPrescriptionDrugsTypeCode(goodsChineseMedicinePieces.getPrescriptionDrugsTypeCode());
                orderItemDetailVo.setIsEphedrine(goodsChineseMedicinePieces.getIsEphedrine());
                break;
            case FOOD_HEALTH:	//食品保健品
            case DAILY_NECESSITIES:	//日用品
            case MEDICAL_INSTRUMENTS:	//医疗器械
            case COSMETIC:	//化妆品
                break;
        }
        return orderItemDetailVo;
    }

    @Transactional
    @Override
    public void updateOrderState(OrderStateSaveVo orderStateSaveVo) {
        //订单信息
        Order order = ServiceManager.orderService.findOne(orderStateSaveVo.getWeShopId(), orderStateSaveVo.getId());
        checkOrder(order, orderStateSaveVo.getWeShopId(), orderStateSaveVo.getId());
        switch (OrderStateCodeEnum.fromCode(orderStateSaveVo.getOrderStateCode())){
            case CLOSE:
                //已关闭、已完成订单无法执行关闭操作
                if(OrderStateCodeEnum.CLOSE.toCode().equals(order.getOrderStateCode())
                        || OrderStateCodeEnum.FINISH.toCode().equals(order.getOrderStateCode())){
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.ORDER_STATE_ERROR);
                    throw new BusinessException(ResGlobalExt.ORDER_STATE_ERROR, message);
                }

                if(StringUtils.isBlank(orderStateSaveVo.getCancelOrderReason())){
                    logger.error("订单关闭原因不能为空");
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_NOT_EMPTY);
                    throw new BusinessException(ResGlobalExt.COMMON_NOT_EMPTY, String.format(message, new Object[]{"订单关闭原因不能为空"}));
                }

                switch (OrderStateCodeEnum.fromCode(order.getOrderStateCode())) {
                    //未发货解锁库存
                    case WAIT_SEND:
                        ServiceManager.skuLockStockService.unLockStock(order.getOrderNum());
                        break;
                    //已发货还原库存
                    case ALREADY_SENDED:
                        ServiceManager.orderService.updateRejectOrder(order);
                        break;
                }

                order.setOrderStateCode(orderStateSaveVo.getOrderStateCode());
                order.setCancelOrderReason(orderStateSaveVo.getCancelOrderReason());
                order.setOrderCloseTime(new Date());
                break;
            case FINISH:
                //待发货、已关闭、已完成订单无法执行完成操作，只有已发货订单才能执行
                if(!OrderStateCodeEnum.ALREADY_SENDED.toCode().equals(order.getOrderStateCode())){
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.ORDER_STATE_ERROR);
                    throw new BusinessException(ResGlobalExt.ORDER_STATE_ERROR, message);
                }

                order.setOrderStateCode(orderStateSaveVo.getOrderStateCode());
                order.setFinishTime(new Date());
                break;
        }

        //更新订单状态
        ServiceManager.orderService.update(order);
    }

    @Override
    public void updateOrderRemark(OrderRemarkSaveVo orderRemarkSaveVo) {
        //订单信息
        Order order = ServiceManager.orderService.findOne(orderRemarkSaveVo.getWeShopId(), orderRemarkSaveVo.getId());
        checkOrder(order, orderRemarkSaveVo.getWeShopId(), orderRemarkSaveVo.getId());
        order.setAdminRemark(orderRemarkSaveVo.getAdminRemark());
        ServiceManager.orderService.update(order);
    }

    @Override
    public OrderSendDetailVo findOrderItemSendDetail(Long weShopId, Long orderId) {
        //订单信息
        Order order = ServiceManager.orderService.findOne(weShopId, orderId);
        checkOrder(order, weShopId, orderId);

        //订单信息
        OrderSendDetailVo orderSendDetailVo = new OrderSendDetailVo();
        orderSendDetailVo.setId(order.getId());
        orderSendDetailVo.setCanSend(true);

        //订单项信息
        List<OrderItemSendDetailVo> orderItemVoList = new ArrayList<>();
        List<OrderItem> orderItemList = ServiceManager.orderItemService.findByOrderId(orderId);
        for(OrderItem orderItem : orderItemList){
            OrderItemSendDetailVo orderItemVo = new OrderItemSendDetailVo();
            CommonUtil.copyProperties(orderItem, orderItemVo);
            orderItemVo.setAdd(false);

            Sku sku = ServiceManager.skuService.findOne(orderItem.getSkuId());
            orderItemVo.setCurrentStock(sku.getCurrentStock());
            orderItemVo.setSecurityStock(sku.getSecurityStock());
            List<GoodsBatchDetailVo> goodsBatchVoList = new ArrayList<>();
            if(sku.getCurrentStock() - sku.getSecurityStock() < orderItem.getQuantity()){//总库存不足，不能发货
                orderSendDetailVo.setCanSend(false);
            }else{
                //批次信息
                List<GoodsBatch> goodsBatchList = ServiceManager.goodsBatchService.findHasStockGoodsByGoodsId(orderItem.getGoodsId());
                if(goodsBatchList.size() == 0){//没有可以发货的批次，不能发货
                    orderSendDetailVo.setCanSend(false);
                }else{
                    Long totalCurrentStock = 0L;
                    for(GoodsBatch goodsBatch : goodsBatchList){
                        GoodsBatchDetailVo goodsBatchVo = new GoodsBatchDetailVo();
                        CommonUtil.copyProperties(goodsBatch, goodsBatchVo);
                        goodsBatchVoList.add(goodsBatchVo);
                        totalCurrentStock = totalCurrentStock + goodsBatch.getCurrentStock();
                    }

                    if(totalCurrentStock<orderItem.getQuantity()){//所有可以发货的批次，总库存不足，不能发货
                        orderSendDetailVo.setCanSend(false);
                        goodsBatchVoList.clear();
                    }
                }
            }
            orderItemVo.setBatchList(goodsBatchVoList);

            orderItemVoList.add(orderItemVo);
        }

        orderSendDetailVo.setItemList(orderItemVoList);
        return orderSendDetailVo;
    }

    private void checkOrder(Order order, Long weShopId, Long orderId){
        if(order==null){
            logger.error("订单不存在，weShopId:" + weShopId + ", orderId:" + orderId);
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"订单"}));
        }
    }

    @Override
    @Transactional
    public void updateOrderToSend(OrderSendVo orderSendVo) {
        synchronized (this){
            //订单信息
            Order order = ServiceManager.orderService.findOne(orderSendVo.getWeShopId(), orderSendVo.getId());
            checkOrder(order, orderSendVo.getWeShopId(), orderSendVo.getId());
            if(!OrderStateCodeEnum.WAIT_SEND.toCode().equals(order.getOrderStateCode())){
                String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.ORDER_STATE_ERROR);
                throw new BusinessException(ResGlobalExt.ORDER_STATE_ERROR, message);
            }

            //设置发货批次信息
            for(OrderItemSendVo itemSendVo : orderSendVo.getItemList()){
                //检查库存
                OrderItem orderItem = ServiceManager.orderItemService.findOne(itemSendVo.getId());
                Sku sku = ServiceManager.skuService.findOne(orderItem.getSkuId());
                GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(itemSendVo.getBatchId());
                if(sku.getCurrentStock() - sku.getSecurityStock() < orderItem.getQuantity() //总库存不小于需要发货商品数量，才可以发货
                        || !(GoodsBatchStateCodeEnum.NORMAL.toCode().equals(goodsBatch.getBatchState()) //批次状态正常，且批次库存大于0，且批次库存不小于批次发货数量，才可以发货
                                && goodsBatch.getCurrentStock()>0
                                && goodsBatch.getCurrentStock()>=itemSendVo.getSendQuantity())) {
                    String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.GOODS_LOW_STOCK_ERROR);
                    throw new BusinessException(ResGlobalExt.GOODS_LOW_STOCK_ERROR, message);
                }

                OrderSendOutBatch orderSendOutBatch = new OrderSendOutBatch();
                orderSendOutBatch.setShopId(order.getShopId());
                orderSendOutBatch.setWeShopId(order.getWeShopId());
                orderSendOutBatch.setOrderId(order.getId());
                orderSendOutBatch.setOrderItemId(itemSendVo.getId());
                orderSendOutBatch.setGoodsBatchId(itemSendVo.getBatchId());
                orderSendOutBatch.setBatch(goodsBatch.getBatch());
                orderSendOutBatch.setQuantity(itemSendVo.getSendQuantity());
                ServiceManager.orderSendOutBatchService.save(orderSendOutBatch);
                //扣减库存
                ServiceManager.goodsBatchService.updateSubtractCurrentStock(orderSendOutBatch.getGoodsBatchId(), itemSendVo.getSendQuantity());
                //更新訂單項的成本價
                orderItem.setGoodsCostTotalAmount(BigDecimalUtil.add(orderItem.getGoodsCostTotalAmount(), BigDecimalUtil.multiply(goodsBatch.getPurchasePrice(), itemSendVo.getSendQuantity())));
                ServiceManager.orderItemService.save(orderItem);
            }

            //更新锁状态为用锁
            ServiceManager.skuLockStockService.useLock(order.getOrderNum());

            //更新订单状态
            order.setOrderStateCode(OrderStateCodeEnum.ALREADY_SENDED.toCode());
            order.setOrderConfirmTime(new Date());
            order.setOpenOrderManId(orderSendVo.getOpenOrderManId());
            ServiceManager.orderService.update(order);
        }
    }

    @Override
    public void printOrderSmallTicket(Long orderId, String userName) {
        if(paperWidth==null){
            logger.error("请在配置文件中配置小票纸张大小");
            return;
        }

        OrderPrintable orderPrintable = new OrderPrintable(orderId, paperWidth, userName);
        try {
            Boolean result = PrintUtil.printSmallTicket(orderPrintable, paperWidth, orderPrintable.getPaperHeight());
            if(!result){
                logger.error("没有发现打印机服务，请确认已经安装了打印机驱动，且设置为默认打印机");
            }
        } catch (PrinterException e) {
            e.printStackTrace();
            logger.error("打印异常");
        }
    }

    @Override
    public Page<OrderPageVo> query(Pageable pageable, Long wechatUserId) {
        Page<Order> orderPage = ServiceManager.orderService.query(pageable, wechatUserId);
        List<OrderPageVo> orderPageVoList = new ArrayList<>();
        for (Order order : orderPage.getContent()) {
            orderPageVoList.add(this.buildOrderPageVo(order.getId()));
        }
        return new PageImpl<OrderPageVo>(orderPageVoList, pageable, orderPageVoList.size());
    }

    @Override
    public OrderDetailVo findByIdAndWeChatUserId(Long id, Long weChatUserId) {
        //订单信息
        Order order = ServiceManager.orderService.findByIdAndWeChatUserId(id, weChatUserId);
        if (order == null){
            throw new BusinessException(ResGlobal.COMMON_ERROR, "订单不存在");
        }
        OrderDetailVo orderDetailVo = new OrderDetailVo();
        CommonUtil.copyProperties(order, orderDetailVo);
        //订单项信息
        List<OrderItem> orderItemList = ServiceManager.orderItemService.findByOrderId(order.getId());
        List<OrderItemDetailVo> orderItemDetailVoList = new ArrayList<>();
        for(OrderItem orderItem : orderItemList){
            orderItemDetailVoList.add(this.buildOrderItemDetailVo(orderItem));
        }
        orderDetailVo.setOrderItemDetailVoList(orderItemDetailVoList);
        return orderDetailVo;
    }
}
