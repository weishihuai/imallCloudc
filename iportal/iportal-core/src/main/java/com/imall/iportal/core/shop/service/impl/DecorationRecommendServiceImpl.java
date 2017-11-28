package com.imall.iportal.core.shop.service.impl;


import com.imall.commons.base.idfs.FileSystemEngine;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.commons.dicts.FileObjectTypeCodeEnum;
import com.imall.commons.dicts.GoodsTypeCodeEnum;
import com.imall.commons.dicts.PrescriptionDrugsTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.FileMng;
import com.imall.iportal.core.shop.entity.*;
import com.imall.iportal.core.shop.repository.DecorationRecommendRepository;
import com.imall.iportal.core.shop.service.DecorationRecommendService;
import com.imall.iportal.core.shop.vo.DecorationRecommendGoodsVo;
import com.imall.iportal.core.shop.vo.DecorationRecommendSaveVo;
import com.imall.iportal.core.shop.vo.DecorationRecommendUpdateVo;
import com.imall.iportal.core.shop.vo.DecorationRecommendVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
public class DecorationRecommendServiceImpl extends AbstractBaseService<DecorationRecommend, Long> implements DecorationRecommendService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private DecorationRecommendRepository getDecorationRecommendRepository() {
        return (DecorationRecommendRepository) baseRepository;
    }

    @Override
    public List<DecorationRecommendVo> findByDecorationGroupIdAndShopId(Long decorationGroupId, Long shopId) {
        List<DecorationRecommend> recommendList = getDecorationRecommendRepository().findByDecorationGroupIdAndShopIdOrderByDisplayPositionDesc(decorationGroupId, shopId);
        List<DecorationRecommendVo> voList = new ArrayList<>();
        for (DecorationRecommend recommend : recommendList) {
            DecorationRecommendVo vo = CommonUtil.copyProperties(recommend, new DecorationRecommendVo());
            Goods goods = ServiceManager.goodsService.findOne(recommend.getGoodsId());
            vo.setGoodsNm(goods.getGoodsNm());
            GoodsDrug goodsDrug = ServiceManager.goodsDrugService.findByGoodsId(recommend.getGoodsId());
            vo.setPrescription(goodsDrug != null && PrescriptionDrugsTypeCodeEnum.RX == PrescriptionDrugsTypeCodeEnum.fromCode(goodsDrug.getPrescriptionDrugsTypeCode()));
            GoodsTypeCodeEnum goodsTypeCodeEnum = GoodsTypeCodeEnum.fromCode(goods.getGoodsTypeCode());
            if (goodsTypeCodeEnum == GoodsTypeCodeEnum.DRUG) {
                vo.setEphedrine(BoolCodeEnum.fromCode(goodsDrug.getIsEphedrine()).boolValue());
            } else if (goodsTypeCodeEnum == GoodsTypeCodeEnum.CHINESE_MEDICINE_PIECES) {
                GoodsChineseMedicinePieces chineseMedicinePieces = ServiceManager.goodsChineseMedicinePiecesService.findByGoodsId(recommend.getGoodsId());
                vo.setEphedrine(BoolCodeEnum.fromCode(chineseMedicinePieces.getIsEphedrine()).boolValue());
            }else {
                vo.setEphedrine(false);
            }
            Sku sku = ServiceManager.skuService.findOne(recommend.getSkuId());
            vo.setRetailPrice(sku.getRetailPrice());
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public Long countByDecorationGroupId(Long decorationGroupId) {
        return getDecorationRecommendRepository().countByDecorationGroupId(decorationGroupId);
    }

    @Override
    @Transactional
    public void deleteBatch(Long shopId, List<Long> ids) {
         for (Long id : ids){
             getDecorationRecommendRepository().deleteByIdAndShopId(id, shopId);
         }
    }

    @Override
    @Transactional
    public void saveBatchRecommend(Long shopId, List<DecorationRecommendSaveVo> saveVoList) {
          for (DecorationRecommendSaveVo saveVo : saveVoList){
              List<DecorationRecommend> list = getDecorationRecommendRepository().findByGoodsIdAndDecorationGroupId(saveVo.getGoodsId(), saveVo.getDecorationGroupId());
              if (list.isEmpty()){
                  DecorationRecommend recommend = CommonUtil.copyProperties(saveVo, new DecorationRecommend());
                  recommend.setShopId(shopId);
                  this.save(recommend);
              }
          }
    }

    @Override
    public Boolean checkIsRecommend(Long goodsId, Long groupId) {
        List<DecorationRecommend> list = getDecorationRecommendRepository().findByGoodsIdAndDecorationGroupId(goodsId, groupId);
        return !list.isEmpty();
    }

    @Override
    @Transactional
    public void updateBatchRecommend(Long shopId, List<DecorationRecommendUpdateVo> updateVoList) {
        for (DecorationRecommendUpdateVo updateVo : updateVoList){
            DecorationRecommend recommend = getDecorationRecommendRepository().findByIdAndShopId(updateVo.getId(), shopId);
            if (recommend == null){
                continue;
            }
            recommend.setDisplayPosition(updateVo.getDisplayPosition());
            this.save(recommend);
        }
    }

    @Override
    public List<DecorationRecommendGoodsVo> listRecommendGoods(Long shopId, Long groupId) {
        List<DecorationRecommend> list = getDecorationRecommendRepository().findByDecorationGroupIdAndShopIdOrderByDisplayPositionDesc(groupId, shopId);
        List<DecorationRecommendGoodsVo> voList = new ArrayList<>();
        for (DecorationRecommend recommend : list){
            Goods goods = ServiceManager.goodsService.findOne(recommend.getGoodsId());
            DecorationRecommendGoodsVo vo = new DecorationRecommendGoodsVo();
            vo.setGoodsNm(goods.getGoodsNm());
            vo.setSpec(goods.getSpec());
            Sku sku = ServiceManager.skuService.findByGoodsId(recommend.getGoodsId());
            vo.setRetailPrice(sku.getRetailPrice());
            List<FileMng> fileMngs = ServiceManager.fileMngService.getList(FileObjectTypeCodeEnum.GOODS_PICT, recommend.getGoodsId());
            if (!CollectionUtils.isEmpty(fileMngs)){
                vo.setPicUrl(FileSystemEngine.getFileSystem().getUrl(fileMngs.get(0).getFileId()));
            }
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public Page<DecorationRecommend> query(Pageable pageable, Long shopId, Long groupId) {
        return getDecorationRecommendRepository().query(pageable, shopId, groupId);
    }

    @Override
    @Transactional
    public void deleteByDecorationGroupId(Long decorationGroupId) {
        getDecorationRecommendRepository().deleteByDecorationGroupId(decorationGroupId);
    }
}