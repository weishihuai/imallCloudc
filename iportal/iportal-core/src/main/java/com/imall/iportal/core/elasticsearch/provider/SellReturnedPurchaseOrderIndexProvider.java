package com.imall.iportal.core.elasticsearch.provider;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.IndexTypeCodeEnum;
import com.imall.iportal.core.elasticsearch.IIndexProvider;
import com.imall.iportal.core.elasticsearch.entity.SellReturnedPurchaseOrderEntity;
import com.imall.iportal.core.elasticsearch.entity.SellReturnedPurchaseOrderItemEntity;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.entity.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ygw on 2017/7/31.
 */

@Component
public class SellReturnedPurchaseOrderIndexProvider implements IIndexProvider<SellReturnedPurchaseOrderEntity> {
    @Override
    public SellReturnedPurchaseOrderEntity getEntity(Long id) {
        SellReturnedPurchaseOrder srpOrder = ServiceManager.sellReturnedPurchaseOrderService.findOne(id);
        if (srpOrder == null) {
            return null;
        }

        SellReturnedPurchaseOrderEntity entity = CommonUtil.copyProperties(srpOrder, new SellReturnedPurchaseOrderEntity());

        setGoodsInfo(entity);
        setMemberInfo(entity);
        //

        return entity;
    }

    private void setMemberInfo(SellReturnedPurchaseOrderEntity entity) {
        Order order = ServiceManager.orderService.findOne(entity.getOrderId());
        if(order.getMemberId()!=null){
            Member member = ServiceManager.memberService.findOne(order.getMemberId());
            if(member != null){
                entity.setMemberCardNum(member.getMemberCardNum());
                entity.setName(member.getName());
            }
        }
    }

    private void setGoodsInfo(SellReturnedPurchaseOrderEntity entity) {
        List<SellReturnedPurchaseOrderItem> itemList = ServiceManager.sellReturnedPurchaseOrderItemService.findByReturnedPurchaseOrderId(entity.getId());

        List<SellReturnedPurchaseOrderItemEntity> orderItemEntityList = new ArrayList<>();
        for(SellReturnedPurchaseOrderItem item:itemList){
            GoodsBatch goodsBatch = ServiceManager.goodsBatchService.findOne(item.getGoodsBatchId());
            if(goodsBatch == null){
                continue;
            }
            Goods goods = ServiceManager.goodsService.findOne(goodsBatch.getGoodsId());
            SellReturnedPurchaseOrderItemEntity orderItemEntity = new SellReturnedPurchaseOrderItemEntity();
            orderItemEntity.setGoodsCoding(goods.getGoodsCode());
            orderItemEntity.setGoodsNm(goods.getGoodsNm());
            orderItemEntity.setGoodsPinyin(goods.getPinyin());
            orderItemEntity.setBarCode(goods.getBarCode());
            orderItemEntity.setCommonNm(goods.getCommonNm());
            orderItemEntityList.add(orderItemEntity);
        }
        entity.setItemList(orderItemEntityList);
    }

    @Override
    public IndexTypeCodeEnum actionType() {
        return IndexTypeCodeEnum.SELL_RETURNED_PURCHASE_ORDER;
    }


}
