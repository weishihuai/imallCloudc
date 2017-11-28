package com.imall.iportal.core.shop.service.impl;

import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.BigDecimalUtil;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.commons.dicts.IndexTypeCodeEnum;
import com.imall.commons.dicts.OrderSourceCodeEnum;
import com.imall.commons.dicts.OrderStateCodeEnum;
import com.imall.iportal.core.elasticsearch.ESUtils;
import com.imall.iportal.core.elasticsearch.entity.OrderEntity;
import com.imall.iportal.core.elasticsearch.entity.SellReturnedPurchaseOrderEntity;
import com.imall.iportal.core.elasticsearch.entity.SellReturnedPurchaseOrderItemEntity;
import com.imall.iportal.core.elasticsearch.utils.SearchUtils;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.shop.entity.*;
import com.imall.iportal.core.shop.repository.SellReturnedPurchaseOrderRepository;
import com.imall.iportal.core.shop.service.SellReturnedPurchaseOrderService;
import com.imall.iportal.core.shop.valid.SellReturnedPurchaseOrderSaveValid;
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
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.InternalSum;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 销售 退货 订单(服务层实现)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class SellReturnedPurchaseOrderServiceImpl extends AbstractBaseService<SellReturnedPurchaseOrder, Long> implements SellReturnedPurchaseOrderService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private SellReturnedPurchaseOrderRepository getSellReturnedPurchaseOrderRepository() {
        return (SellReturnedPurchaseOrderRepository) baseRepository;
    }

    @Override
    public SellReturnedPurchaseOrderDetailVo findDetail(Long id, Long shopId) {
        SellReturnedPurchaseOrder returnOrder = findOne(id);
        if (returnOrder == null || !returnOrder.getShopId().equals(shopId)) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobal.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobal.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"退货单"}));
        }

        return buildDetailVo(returnOrder);
    }

    private SellReturnedPurchaseOrderDetailVo buildDetailVo(SellReturnedPurchaseOrder returnOrder) {
        SellReturnedPurchaseOrderDetailVo detailVo = new SellReturnedPurchaseOrderDetailVo();
        SysUser cashier = ServiceManager.sysUserService.findOne(returnOrder.getCashierId());
        SysUser approveMan = ServiceManager.sysUserService.findOne(returnOrder.getApproveManId());
        detailVo.setSellReturnedPurchaseOrderNum(returnOrder.getSellReturnedPurchaseOrderNum());
        detailVo.setCreateDate(returnOrder.getCreateDate());
        detailVo.setSellPurchaseOrderNum(returnOrder.getSellReturnedPurchaseOrderNum());
        detailVo.setRemark(returnOrder.getRemark());
        detailVo.setCashierNm(cashier.getUserName());
        detailVo.setApproveManNm(approveMan.getUserName());
        detailVo.setRefundTotalAmount(returnOrder.getRefundTotalAmount());
        detailVo.setItemList(ServiceManager.sellReturnedPurchaseOrderItemService.findItemPageVoByReturnedPurchaseOrderId(returnOrder.getId()));
        return detailVo;
    }

    @Override
    public Page<SellReturnedPurchaseOrderPageVo> query(Pageable pageable, SellReturnedPurchaseOrderSearchParam searchParam) {
        Page<SellReturnedPurchaseOrder> orderPage = queryByEs(pageable, searchParam);
        List<SellReturnedPurchaseOrderPageVo> pageVoList = new ArrayList<>();
        for (SellReturnedPurchaseOrder order : orderPage.getContent()) {
            pageVoList.add(buildPageVo(order));
        }

        return new PageImpl<>(pageVoList, pageable, orderPage.getTotalElements());
    }

    private Page<SellReturnedPurchaseOrder> queryByEs(Pageable pageable, SellReturnedPurchaseOrderSearchParam searchParam) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        queryBuilder.must(QueryBuilders.termQuery(SellReturnedPurchaseOrderEntity.SHOP_ID, searchParam.getShopId()));

        if (searchParam.getCashierId() != null) {
            queryBuilder.must(QueryBuilders.termQuery(SellReturnedPurchaseOrderEntity.CASHIER_ID, searchParam.getCashierId()));
        }

        //销售单号
        if (StringUtils.isNotBlank(searchParam.getOrderNum())) {
            queryBuilder.must(QueryBuilders.termQuery(SellReturnedPurchaseOrderEntity.SELL_RETURNED_PURCHASE_ORDER_NUM, searchParam.getOrderNum()));
        }

        //销售开始时间
        if (searchParam.getFromDate() != null) {
            queryBuilder.must(QueryBuilders.rangeQuery(SellReturnedPurchaseOrderEntity.RETURNED_PURCHASE_TIME).gte(searchParam.getFromDate().getTime()));
        }

        //销售结束时间
        if (searchParam.getToDate() != null) {
            queryBuilder.must(QueryBuilders.rangeQuery(SellReturnedPurchaseOrderEntity.RETURNED_PURCHASE_TIME).lte(searchParam.getToDate().getTime()));
        }

        //拼音/商品编码/商品名称/条形码/通用名称
        if (StringUtils.isNotBlank(searchParam.getGoodsSearchFields())) {
            BoolQueryBuilder keywordBuilder = QueryBuilders.boolQuery()
                    .should(QueryBuilders.termQuery(SellReturnedPurchaseOrderItemEntity.GOODS_CODING, searchParam.getGoodsSearchFields()))
                    .should(QueryBuilders.termQuery(SellReturnedPurchaseOrderItemEntity.GOODS_NM, searchParam.getGoodsSearchFields()))
                    .should(QueryBuilders.termQuery(SellReturnedPurchaseOrderItemEntity.COMMON_NM, searchParam.getGoodsSearchFields()))
                    .should(QueryBuilders.termQuery(SellReturnedPurchaseOrderItemEntity.BAR_CODE, searchParam.getGoodsSearchFields()))
                    .should(QueryBuilders.termQuery(SellReturnedPurchaseOrderItemEntity.GOODS_PINYIN, searchParam.getGoodsSearchFields()));
            queryBuilder.must(keywordBuilder);
        }

        //会员:手机号/卡号/会员姓名
        if (StringUtils.isNotBlank(searchParam.getMemberSearchFields())) {
            BoolQueryBuilder keywordsBuilder = QueryBuilders.boolQuery()
                    .should(QueryBuilders.termQuery(OrderEntity.MEMBER_CARD_NUM, searchParam.getMemberSearchFields()))
                    .should(QueryBuilders.termQuery(OrderEntity.NAME, searchParam.getMemberSearchFields()));
            queryBuilder.must(keywordsBuilder);
        }


        SearchRequestBuilder searchRequestBuilder = ESUtils.getClient()
                .prepareSearch(IndexTypeCodeEnum.SELL_RETURNED_PURCHASE_ORDER.toCode())
                .setTypes(IndexTypeCodeEnum.SELL_RETURNED_PURCHASE_ORDER.toCode())
                .setSearchType(SearchType.DEFAULT)
                .setQuery(queryBuilder)
                .addStoredField(SellReturnedPurchaseOrderEntity.ID)
                .addSort(SellReturnedPurchaseOrderEntity.ID, SortOrder.DESC)
                .setFrom(pageable.getPageNumber() * pageable.getPageSize())
                .setSize(pageable.getPageSize())
                .setExplain(false);
        SearchResponse sr = searchRequestBuilder.get();
        List<Map<String, Object>> list = SearchUtils.getSearchResult(sr);
        List<SellReturnedPurchaseOrder> result = new ArrayList<>();
        for (Map<String, Object> map : list) {
            Long id = (Long) map.get(SellReturnedPurchaseOrderEntity.ID);
            result.add((findOne(id)));
        }
        return new PageImpl<>(result, pageable, sr.getHits().getTotalHits());
    }

    private SellReturnedPurchaseOrderPageVo buildPageVo(SellReturnedPurchaseOrder returnOrder) {
        SellReturnedPurchaseOrderPageVo pageVo = new SellReturnedPurchaseOrderPageVo();
        SysUser cashier = ServiceManager.sysUserService.findOne(returnOrder.getCashierId());
        SysUser approveMan = ServiceManager.sysUserService.findOne(returnOrder.getApproveManId());
        Order order = ServiceManager.orderService.findOne(returnOrder.getOrderId());
        if(order.getMemberId()!=null){
            Member member = ServiceManager.memberService.findOne(order.getMemberId());
            pageVo.setName(member.getName());
            pageVo.setMemberCardNum(member.getMemberCardNum());
        }
        pageVo.setId(returnOrder.getId());
        pageVo.setCreateDateString(returnOrder.getCreateDateString());
        pageVo.setSellReturnedPurchaseOrderNum(returnOrder.getSellReturnedPurchaseOrderNum());
        pageVo.setSellPurchaseOrderNum(order.getOrderNum());
        pageVo.setRealReturnCashAmount(returnOrder.getRealReturnCashAmount());
        pageVo.setRefundTotalAmount(returnOrder.getRefundTotalAmount());
        pageVo.setCashierNm(cashier.getRealName());
        pageVo.setApproveManNm(approveMan.getUserName());
        pageVo.setRemark(returnOrder.getRemark());
        return pageVo;
    }

    @Override
    public Integer queryOrderToQueue() {
        return getSellReturnedPurchaseOrderRepository().queryOrderToQueue();
    }

    @Override
    public Double sumReturnedAmountByShopIdAndCashierId(Long shopId, Long cashierId, Date fromReturnedPurchaseTime, Date toReturnedPurchaseTime) {
        SRPOrderSearchParam searchParam = new SRPOrderSearchParam();
        searchParam.setShopId(shopId);
        searchParam.setCashierId(cashierId);
        searchParam.setFromReturnedPurchaseTimeString(DateTimeUtils.convertDateTimeToMilString(fromReturnedPurchaseTime));
        searchParam.setToReturnedPurchaseTimeString(DateTimeUtils.convertDateTimeToMilString(toReturnedPurchaseTime));

        SearchRequestBuilder searchRequestBuilder = ESUtils.getClient()
                .prepareSearch(IndexTypeCodeEnum.SELL_RETURNED_PURCHASE_ORDER.toCode())
                .setTypes(IndexTypeCodeEnum.SELL_RETURNED_PURCHASE_ORDER.toCode())
                .setSearchType(SearchType.DEFAULT) //??? SearchType.QUERY_THEN_FETCH
                .setQuery(buildQuery(searchParam)) //查询条件设置
                .addStoredField(SellReturnedPurchaseOrderEntity.SHOP_ID)            //设置返回字段
                .addStoredField(SellReturnedPurchaseOrderEntity.REFUND_TOTAL_AMOUNT)            //设置返回字段
                .setExplain(false);

        TermsAggregationBuilder tb = AggregationBuilders.terms(SellReturnedPurchaseOrderEntity.SHOP_ID).field(SellReturnedPurchaseOrderEntity.SHOP_ID);
        SumAggregationBuilder sb = AggregationBuilders.sum(SellReturnedPurchaseOrderEntity.REFUND_TOTAL_AMOUNT).field(SellReturnedPurchaseOrderEntity.REFUND_TOTAL_AMOUNT);
        tb.subAggregation(sb);
        searchRequestBuilder.addAggregation(tb);
        LongTerms tms = searchRequestBuilder.get().getAggregations().get(SellReturnedPurchaseOrderEntity.SHOP_ID);
        Double sumTotalAmount = 0d;
        for (Terms.Bucket bucket : tms.getBuckets()) {
            LongTerms.Bucket longBucket = (LongTerms.Bucket) bucket;
            Aggregations aggregations = longBucket.getAggregations();
            List<Aggregation> aggregationList = aggregations.asList();
            for (Aggregation aggregation : aggregationList) {
                Double sum = (Double) ((InternalSum) aggregation).getValue();
                sumTotalAmount = BigDecimalUtil.add(sumTotalAmount, sum);
            }
        }
        return sumTotalAmount;
    }

    @Override
    public Page<SRPReadyOrderPageVo> commonOrderQuery(Pageable pageable, OrderSearchParam orderSearchParam) {
        orderSearchParam.setOrderStateCode(OrderStateCodeEnum.FINISH.toCode());
        Page<OrderPageVo> page = ServiceManager.orderService.commonOrderQuery(pageable, orderSearchParam);
        List<SRPReadyOrderPageVo> resultList = new ArrayList<>();
        for (OrderPageVo pageVo : page) {
            SRPReadyOrderPageVo readyPageVo = new SRPReadyOrderPageVo();
            CommonUtil.copyProperties(pageVo, readyPageVo);
            readyPageVo.setReturnedTime(new Date());
            resultList.add(readyPageVo);
        }
        return new PageImpl<SRPReadyOrderPageVo>(resultList, pageable, page.getTotalElements());
    }

    @Transactional
    @Override
    public void saveReturnedCalc(SellReturnedPurchaseOrderSaveValid returnedPurchaseOrderSaveValid) {
        //验证：可退数量
        Map<Long, Long> map = new HashMap<>();
        for (SellReturnedPurchaseOrderSaveValid.ReturnedOrderItem returnedOrderItem : returnedPurchaseOrderSaveValid.getReturnedOrderItems()) {
            map.put(returnedOrderItem.getOrderItemId(), returnedOrderItem.getReturnedQuantity());
        }
        List<OrderItem> orderItems = ServiceManager.orderItemService.findByOrderId(returnedPurchaseOrderSaveValid.getOrderId());
        for (OrderItem orderItem : orderItems) {
            Long returnedQuantity = ServiceManager.sellReturnedPurchaseOrderItemService.queryCalcReturnedQuantity(orderItem.getId());
            Long allowReturnedQuantity = orderItem.getQuantity() - returnedQuantity;
            if (map.get(orderItem.getId()) !=null&&map.get(orderItem.getId()) > allowReturnedQuantity) {
                throw new BusinessException(ResGlobal.COMMON_ERROR, "商品\"" + orderItem.getGoodsNm() + "\"超出可退数量");
            }
        }
        //验证：应退金额 = 实退金额 - 找零
        if (returnedPurchaseOrderSaveValid.getRefundTotalAmount().doubleValue() != BigDecimalUtil.subtract(returnedPurchaseOrderSaveValid.getRealReturnCashAmount(), returnedPurchaseOrderSaveValid.getReturnSmall()).doubleValue()) {
            throw new BusinessException(ResGlobal.COMMON_ERROR, "应退金额、实退金额、找零，数据不正确，找零 要等于 实退金额 - 应退金额");
        }
        //验证：应退金额
        Double refundTotalAmount = 0D;
        for (SellReturnedPurchaseOrderSaveValid.ReturnedOrderItem returnedOrderItem : returnedPurchaseOrderSaveValid.getReturnedOrderItems()) {
            OrderItem orderItem = ServiceManager.orderItemService.findOne(returnedOrderItem.getOrderItemId());
            refundTotalAmount = BigDecimalUtil.add(refundTotalAmount, BigDecimalUtil.multiply(orderItem.getGoodsUnitPrice(), returnedOrderItem.getReturnedQuantity()));
        }
        if (returnedPurchaseOrderSaveValid.getRefundTotalAmount().doubleValue() != refundTotalAmount.doubleValue()) {
            throw new BusinessException(ResGlobal.COMMON_ERROR, "应退金额数据不正确");
        }

        //保存退货订单
        Order order = ServiceManager.orderService.findOne(returnedPurchaseOrderSaveValid.getOrderId());
        SellReturnedPurchaseOrder returnedPurchaseOrder = new SellReturnedPurchaseOrder();
        CommonUtil.copyProperties(returnedPurchaseOrderSaveValid, returnedPurchaseOrder);
        if(BoolCodeEnum.fromCode(returnedPurchaseOrderSaveValid.getIsOverallReturnedPurchase()) == BoolCodeEnum.YES){
            order.setIsHasReturnedPurchase(BoolCodeEnum.YES.toCode());
            ServiceManager.orderService.save(order);
        }else {
            //订单商品的数量
            Long orderTotalGoodsQuantity = ServiceManager.orderItemService.findOrderTotalGoodsQuantity(order.getId());

            //本次退货退货订单的商品总数量
            Long returnTotalQuantity =  0L;
            for(SellReturnedPurchaseOrderSaveValid.ReturnedOrderItem returnedOrderItem : returnedPurchaseOrderSaveValid.getReturnedOrderItems()){
                returnTotalQuantity += returnedOrderItem.getReturnedQuantity();
            }
            //查找订单 之前所有的退货订单
            List<SellReturnedPurchaseOrder> sellReturnedPurchaseOrders = getSellReturnedPurchaseOrderRepository().findByOrderId(order.getId());
            //查找订单 之前所有退货订单的商品总数量
            Long sellPurchaseGoodsTotalQuantity = 0L;
            for(SellReturnedPurchaseOrder sellReturnedPurchaseOrder:sellReturnedPurchaseOrders){
                Long sellPurchaseGoodsQuantity = ServiceManager.sellReturnedPurchaseOrderItemService.findSellReturnPurchaseGoodsQuantity(sellReturnedPurchaseOrder.getId());
                sellPurchaseGoodsTotalQuantity += sellPurchaseGoodsQuantity;
            }
            if(orderTotalGoodsQuantity.equals(returnTotalQuantity+sellPurchaseGoodsTotalQuantity)){//订单商品总数量=本次退货订单商品总数量+之前退货订单的商品总数量  为整单退货
                order.setIsHasReturnedPurchase(BoolCodeEnum.YES.toCode());
                ServiceManager.orderService.save(order);
            }
        }
        returnedPurchaseOrder.setSellReturnedPurchaseOrderNum(DateSerialGenerator.genSerialCode(DateSerialGenerator.SELL_RETURNED_PURCHASE_ORDER_PREFIX));
        returnedPurchaseOrder.setShopId(order.getShopId());
        returnedPurchaseOrder.setReturnedPurchaseTime(new Date()); //使用当前时间？还是回传的时间？
        SellReturnedPurchaseOrder dbReturnedPurchaseOrder = save(returnedPurchaseOrder);
        //保存退货订单项
        for (SellReturnedPurchaseOrderSaveValid.ReturnedOrderItem returnedOrderItem : returnedPurchaseOrderSaveValid.getReturnedOrderItems()) {
            OrderItem orderItem = ServiceManager.orderItemService.findOne(returnedOrderItem.getOrderItemId());
            if (OrderSourceCodeEnum.fromCode(order.getOrderSourceCode()) == OrderSourceCodeEnum.SALES_POS) {
                List<OrderSendOutBatch> batchList = ServiceManager.orderSendOutBatchService.findByOrderIdAndOrderItemId(orderItem.getOrderId(), orderItem.getId());
                for (OrderSendOutBatch sendOutBatch : batchList) {
                    saveSellReturnOrderItem(sendOutBatch, dbReturnedPurchaseOrder.getId(), returnedOrderItem.getOrderItemId(), returnedOrderItem.getReturnedQuantity(), orderItem.getGoodsUnitPrice());
                }
            } else { //微信
                OrderSendOutBatch sendOutBatch = ServiceManager.orderSendOutBatchService.findOne(returnedOrderItem.getOrderSendOutBatchId());
                if (sendOutBatch != null) {
                    saveSellReturnOrderItem(sendOutBatch, dbReturnedPurchaseOrder.getId(), returnedOrderItem.getOrderItemId(), returnedOrderItem.getReturnedQuantity(), orderItem.getGoodsUnitPrice());
                }
            }
        }
    }

    @Transactional
    private void saveSellReturnOrderItem(OrderSendOutBatch sendOutBatch, Long returnOrderId, Long orderItemId, Long returnedQuantity, Double goodsUnitPrice) {
        SellReturnedPurchaseOrderItem returnedPurchaseOrderItem = new SellReturnedPurchaseOrderItem();
        returnedPurchaseOrderItem.setReturnedPurchaseOrderId(returnOrderId);
        returnedPurchaseOrderItem.setBatchNum(sendOutBatch.getBatch());
        returnedPurchaseOrderItem.setGoodsBatchId(sendOutBatch.getGoodsBatchId());
        returnedPurchaseOrderItem.setOrderItemId(orderItemId);
        returnedPurchaseOrderItem.setReturnedPurchaseQuantity(returnedQuantity);
        returnedPurchaseOrderItem.setUnitPrice(goodsUnitPrice);
        returnedPurchaseOrderItem.setTotalAmount(BigDecimalUtil.multiply(returnedPurchaseOrderItem.getUnitPrice(), returnedPurchaseOrderItem.getReturnedPurchaseQuantity()));
        ServiceManager.sellReturnedPurchaseOrderItemService.save(returnedPurchaseOrderItem);

        //回退库存
        GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(sendOutBatch.getGoodsBatchId());
        goodsBatch.setCurrentStock(goodsBatch.getCurrentStock() + returnedQuantity);
        ServiceManager.goodsBatchService.save(goodsBatch);
    }

    private QueryBuilder buildQuery(SRPOrderSearchParam orderSearchParam) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        //门店 ID
        queryBuilder.must(QueryBuilders.termQuery(SellReturnedPurchaseOrderEntity.SHOP_ID, orderSearchParam.getShopId()));

        if (orderSearchParam.getCashierId() != null) {
            queryBuilder.must(QueryBuilders.termQuery(SellReturnedPurchaseOrderEntity.CASHIER_ID, orderSearchParam.getCashierId()));
        }

        //销售开始时间
        if (StringUtils.isNotBlank(orderSearchParam.getFromReturnedPurchaseTimeString())) {
            queryBuilder.must(QueryBuilders.rangeQuery(SellReturnedPurchaseOrderEntity.RETURNED_PURCHASE_TIME).gte(orderSearchParam.getFormReturnedPurchaseTime().getTime()));
        }

        //销售结束时间
        if (StringUtils.isNotBlank(orderSearchParam.getToReturnedPurchaseTimeString())) {
            queryBuilder.must(QueryBuilders.rangeQuery(SellReturnedPurchaseOrderEntity.RETURNED_PURCHASE_TIME).lte(orderSearchParam.getToReturnedPurchaseTime().getTime()));
        }

        return queryBuilder;
    }

}