package com.imall.iportal.core.shop.vo;

import com.imall.iportal.core.shop.entity.DecorationRecommendGroup;

import java.util.List;

/**
 * Created by lxh on 2017/8/19.
 */
public class DecorationRecommendGroupVo extends DecorationRecommendGroup {
    //商品数量
    private Long goodsCount;
    //推荐商品列表
    private List<DecorationRecommendVo> recommendList;

    public Long getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Long goodsCount) {
        this.goodsCount = goodsCount;
    }

    public List<DecorationRecommendVo> getRecommendList() {
        return recommendList;
    }

    public void setRecommendList(List<DecorationRecommendVo> recommendList) {
        this.recommendList = recommendList;
    }
}
