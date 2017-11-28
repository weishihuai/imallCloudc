package com.imall.iportal.core.elasticsearch.provider;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.commons.dicts.GoodsTypeCodeEnum;
import com.imall.commons.dicts.IndexTypeCodeEnum;
import com.imall.commons.dicts.PrescriptionDrugsTypeCodeEnum;
import com.imall.iportal.core.elasticsearch.IIndexProvider;
import com.imall.iportal.core.elasticsearch.entity.OrderEntity;
import com.imall.iportal.core.elasticsearch.entity.OrderItemEntity;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.shop.entity.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class OrderIndexProvider implements IIndexProvider<OrderEntity> {

    @Override
    public OrderEntity getEntity(Long id) {
        Order order = ServiceManager.orderService.findOne(id);
        if (order == null) {
            return null;
        }

        OrderEntity entity = CommonUtil.copyProperties(order, new OrderEntity());
        setGoodsInfo(entity);
        setMemberInfo(entity);
        return entity;
    }

    /**
     * 设置会员信息 会员名称
     * @param entity
     */
    private void setMemberInfo(OrderEntity entity) {
        if(entity.getMemberId() != null){
            Member member = ServiceManager.memberService.findOne(entity.getMemberId());
            entity.setName(member.getName());
        }
    }

    /**
     * 设置订单商品信息（是否麻黄碱、是否处方药、商品IDS）
     *
     * @param entity 索引
     */
    private void setGoodsInfo(OrderEntity entity) {
        List<OrderItem> itemList = ServiceManager.orderItemService.findByOrderId(entity.getId());
        boolean isEphedrineOrder = false;
        boolean isPrescriptionOrder = false;
        List<OrderItemEntity> orderItemEntityList = new ArrayList<>();
        for (OrderItem item : itemList) {
            Long goodsId = item.getGoodsId();
            Goods goods = ServiceManager.goodsService.findOne(goodsId);
            if(!isEphedrineOrder || !isPrescriptionOrder) {
                switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())) {
                    case DRUG:
                        GoodsDrug drug = ServiceManager.goodsDrugService.findByGoodsId(goodsId);
                        if(drug != null && !isEphedrineOrder) {
                            isEphedrineOrder = BoolCodeEnum.fromCode(drug.getIsEphedrine()).boolValue();
                        }
                        if(drug != null && !isPrescriptionOrder) {
                            isPrescriptionOrder = PrescriptionDrugsTypeCodeEnum.fromCode(drug.getPrescriptionDrugsTypeCode()) == PrescriptionDrugsTypeCodeEnum.RX;
                        }
                        break;
                    case CHINESE_MEDICINE_PIECES:
                        GoodsChineseMedicinePieces pieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goodsId);
                        if(pieces != null && !isEphedrineOrder) {
                            isEphedrineOrder = BoolCodeEnum.fromCode(pieces.getIsEphedrine()).boolValue();
                        }
                        if(pieces != null && !isPrescriptionOrder) {
                            isPrescriptionOrder = PrescriptionDrugsTypeCodeEnum.fromCode(pieces.getPrescriptionDrugsTypeCode()) == PrescriptionDrugsTypeCodeEnum.RX;
                        }
                        break;
                }
            }
            OrderItemEntity orderItemEntity = CommonUtil.copyProperties(item, new OrderItemEntity());
            orderItemEntity.setGoodsCoding(goods.getGoodsCode());
            orderItemEntity.setGoodsNm(goods.getGoodsNm());
            orderItemEntity.setGoodsPinyin(goods.getPinyin());
            orderItemEntity.setBarCode(goods.getBarCode());
            orderItemEntity.setCommonNm(goods.getCommonNm());
            orderItemEntityList.add(orderItemEntity);
        }
        entity.setItemList(orderItemEntityList);
        entity.setIsEphedrineOrder(BoolCodeEnum.toCode(isEphedrineOrder));
        entity.setIsPrescriptionOrder(BoolCodeEnum.toCode(isPrescriptionOrder));
    }

    @Override
    public IndexTypeCodeEnum actionType() {
        return IndexTypeCodeEnum.ORDER;
    }

}
