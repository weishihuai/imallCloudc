package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.GoodsSplitZero;
import com.imall.iportal.core.shop.vo.GoodsSplitZeroDetailVo;
import com.imall.iportal.core.shop.vo.GoodsSplitZeroPageVo;
import com.imall.iportal.core.shop.vo.GoodsSplitZeroSaveVo;
import com.imall.iportal.core.shop.vo.GoodsSplitZeroSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * (服务层类)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface GoodsSplitZeroService {

    /**
     * 保存对象
     *
     * @param goodsSplitZero
     * @return
     */
    GoodsSplitZero save(GoodsSplitZero goodsSplitZero);

    /**
     * 根据ID查询相应的对象
     *
     * @param id 主键ID
     * @return
     */
    GoodsSplitZero findOne(@NotNull Long id);

    /**
     * 获取商品拆零分页列表
     *
     * @param pageable                  分页参数
     * @param goodsSplitZeroSearchParam 搜索参数
     * @return 分页列表对象
     */
    Page<GoodsSplitZeroPageVo> queryGoodsSplitZero(@NotNull Pageable pageable, @NotNull @Valid GoodsSplitZeroSearchParam goodsSplitZeroSearchParam);

    /**
     * 根据商品拆零对象id获取对象详情
     *
     * @param id 商品拆零对象id
     * @return 商品拆零对象
     */
    GoodsSplitZeroDetailVo findById(@NotNull Long shopId, @NotNull Long id);

    /**
     * 新增商品拆零对象
     *
     * @param goodsSplitZeroSaveVo 商品拆零对象
     * @return
     */
    Long save(@NotNull @Valid GoodsSplitZeroSaveVo goodsSplitZeroSaveVo);

    /**
     * 查找商品拆零信息
     *
     * @param goodsId 商品id
     * @return
     */
    List<GoodsSplitZero> findByGoodsId(Long goodsId);

}
