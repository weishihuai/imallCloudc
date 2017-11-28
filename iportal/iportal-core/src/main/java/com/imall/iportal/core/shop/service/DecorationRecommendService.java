package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.DecorationRecommend;
import com.imall.iportal.core.shop.vo.DecorationRecommendGoodsVo;
import com.imall.iportal.core.shop.vo.DecorationRecommendSaveVo;
import com.imall.iportal.core.shop.vo.DecorationRecommendUpdateVo;
import com.imall.iportal.core.shop.vo.DecorationRecommendVo;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface DecorationRecommendService{

    /**
     * 根据分组ID和门店ID查找推荐列表
     * @param decorationGroupId
     * @param shopId
     * @return
     */
    List<DecorationRecommendVo> findByDecorationGroupIdAndShopId(@NotNull Long decorationGroupId, @NotNull Long shopId);

    /**
     * 根据分组ID统计推荐商品数量
     * @param decorationGroupId
     * @return
     */
    Long countByDecorationGroupId(@NotNull Long decorationGroupId);

    /**
     * 批量删除
     * @param shopId
     * @param ids
     */
    void deleteBatch(@NotNull Long shopId, @NotEmpty List<Long> ids);

    /**
     * 批量推荐
     * @param shopId
     * @param saveVoList
     */
    void saveBatchRecommend(@NotNull Long shopId, @NotEmpty List<DecorationRecommendSaveVo> saveVoList);

    /**
     * 检查是否已推荐
     * @param goodsId
     * @param groupId
     * @return
     */
    Boolean checkIsRecommend(@NotNull Long goodsId, @NotNull Long groupId);

    /**
     * 批量更新
     * @param shopId
     * @param updateVoList
     */
    void updateBatchRecommend(@NotNull Long shopId, @NotEmpty List<DecorationRecommendUpdateVo> updateVoList);

    /**
     * 根据分组ID列出推荐商品
     * @param shopId
     * @param groupId
     * @return
     */
    List<DecorationRecommendGoodsVo> listRecommendGoods(@NotNull Long shopId, @NotNull Long groupId);

    /**
     * 分页查询
     * @param pageable
     * @param shopId
     * @param groupId
     * @return
     */
    Page<DecorationRecommend> query(@NotNull Pageable pageable, @NotNull Long shopId, @NotNull Long groupId);

    /**
     * 根据装修分组删除商品推荐
     * @param decorationGroupId
     */
    void deleteByDecorationGroupId(@NotNull Long decorationGroupId);
}
