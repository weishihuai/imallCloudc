package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.commons.dicts.GoodsTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysDictItem;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.*;
import com.imall.iportal.core.shop.repository.LimitBuyRegisterRepository;
import com.imall.iportal.core.shop.service.LimitBuyRegisterService;
import com.imall.iportal.core.shop.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * (服务层实现)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class LimitBuyRegisterServiceImpl extends AbstractBaseService<LimitBuyRegister, Long> implements LimitBuyRegisterService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private LimitBuyRegisterRepository getLimitBuyRegisterRepository() {
        return (LimitBuyRegisterRepository) baseRepository;
    }

    @Override
    public Page<LimitBuyRegisterPageVo> query(Pageable pageable, LimitBuyRegisterSearchParam limitBuyRegisterSearchParam) {
        Page<LimitBuyRegister> storageSpacePage = getLimitBuyRegisterRepository().query(pageable, limitBuyRegisterSearchParam);
        List<LimitBuyRegisterPageVo> limitBuyRegisterPageVoList = new ArrayList<>();
        for(LimitBuyRegister limitBuyRegister : storageSpacePage.getContent()){
            LimitBuyRegisterPageVo limitBuyRegisterPageVo = new LimitBuyRegisterPageVo();
            CommonUtil.copyProperties(limitBuyRegister, limitBuyRegisterPageVo);
            limitBuyRegisterPageVoList.add(limitBuyRegisterPageVo);
        }

        return new PageImpl<>(limitBuyRegisterPageVoList,  new PageRequest(storageSpacePage.getNumber(),storageSpacePage.getSize()), storageSpacePage.getTotalElements());
    }

    @Override
    @Transactional
    public Long save(LimitBuyRegisterSaveVo limitBuyRegisterSaveVo){
        LimitBuyRegister limitBuyRegister = new LimitBuyRegister();
        CommonUtil.copyProperties(limitBuyRegisterSaveVo, limitBuyRegister);
        return save(limitBuyRegister).getId();
    }

    @Override
    @Transactional
    public Long update(LimitBuyRegisterUpdateVo limitBuyRegisterUpdateVo){
        LimitBuyRegister limitBuyRegister = this.findOne(limitBuyRegisterUpdateVo.getId());
        if (limitBuyRegister == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"限购登记"}));
        }

        limitBuyRegister.setPatientNm(limitBuyRegisterUpdateVo.getPatientNm());
        limitBuyRegister.setIdentityCard(limitBuyRegisterUpdateVo.getIdentityCard());
        limitBuyRegister.setSexCode(limitBuyRegisterUpdateVo.getSexCode());
        limitBuyRegister.setMobile(limitBuyRegisterUpdateVo.getMobile());
        limitBuyRegister.setRemark(limitBuyRegisterUpdateVo.getRemark());
        limitBuyRegister.setAddr(limitBuyRegisterUpdateVo.getAddr());
        limitBuyRegister.setRegisterDate(limitBuyRegisterUpdateVo.getRegisterDate());
        return update(limitBuyRegister).getId();
    }

    @Override
    public LimitBuyRegisterDetailVo findById(Long shopId, Long id) {
        LimitBuyRegister limitBuyRegister = this.getLimitBuyRegisterRepository().findOne(id, shopId);
        if(limitBuyRegister==null){
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, String.format(message, new Object[]{"限购登记"}));
        }

        return buildLimitBuyRegisterDetailVo(limitBuyRegister);
    }

    /**
     * 构建LimitBuyRegisterVo对象
     *
     * @param limitBuyRegister 限购登记对象
     * @return
     */
    private LimitBuyRegisterDetailVo buildLimitBuyRegisterDetailVo(LimitBuyRegister limitBuyRegister) {
        LimitBuyRegisterDetailVo limitBuyRegisterDetailVo = CommonUtil.copyProperties(limitBuyRegister, new LimitBuyRegisterDetailVo());
        limitBuyRegisterDetailVo.setId(limitBuyRegister.getId());
        limitBuyRegisterDetailVo.setLimitBuyRegisterItemVoList(buildLimitBuyRegisterItem(limitBuyRegister.getOrderId()));
        return limitBuyRegisterDetailVo;
    }

    private List<LimitBuyRegisterItemVo> buildLimitBuyRegisterItem(Long orderId){
        List<LimitBuyRegisterItemVo> itemVoList = new ArrayList<>();
        List<OrderItem> orderItemList = ServiceManager.orderItemService.findByOrderId(orderId);
        for(OrderItem orderItem : orderItemList){
            Goods goods = ServiceManager.goodsService.findOne(orderItem.getGoodsId());
            Long goodId = goods.getId();
            switch (GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode())){
                case DRUG:	//药品
                    GoodsDrug goodsDrug = ServiceManager.goodsDrugService.findByGoodsId(goodId);
                    if(BoolCodeEnum.YES==BoolCodeEnum.fromCode(goodsDrug.getIsEphedrine())){
                        LimitBuyRegisterItemVo limitBuyRegisterItemVo = new LimitBuyRegisterItemVo();
                        CommonUtil.copyProperties(orderItem, limitBuyRegisterItemVo);
                        SysDictItem drugSysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsDrug.getDosageForm());
                        limitBuyRegisterItemVo.setDosageForm(drugSysDictItem.getDictItemNm());
                        limitBuyRegisterItemVo.setApprovalNumber(goodsDrug.getApprovalNumber());
                        itemVoList.add(limitBuyRegisterItemVo);
                    }
                    break;
                case OTHER:	//其他
                    break;
                case CHINESE_MEDICINE_PIECES:	//中药饮片
                    GoodsChineseMedicinePieces goodsChineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(goodId);
                    if(BoolCodeEnum.YES==BoolCodeEnum.fromCode(goodsChineseMedicinePieces.getIsEphedrine())){
                        LimitBuyRegisterItemVo limitBuyRegisterItemVo = new LimitBuyRegisterItemVo();
                        CommonUtil.copyProperties(orderItem, limitBuyRegisterItemVo);
                        SysDictItem cmpSysDictItem = ServiceManager.sysDictItemService.findByDictItemCode(goodsChineseMedicinePieces.getDosageForm());
                        limitBuyRegisterItemVo.setDosageForm(cmpSysDictItem.getDictItemNm());
                        limitBuyRegisterItemVo.setProductionPlace(goodsChineseMedicinePieces.getProductionPlace());
                        limitBuyRegisterItemVo.setApprovalNumber(goodsChineseMedicinePieces.getApprovalNumber());
                        itemVoList.add(limitBuyRegisterItemVo);
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

        return itemVoList;
    }

    @Override
    public List<LimitBuyRegisterItemVo> listItemByOrderId(Long shopId, Long orderId) {
        return buildLimitBuyRegisterItem(orderId);
    }
}