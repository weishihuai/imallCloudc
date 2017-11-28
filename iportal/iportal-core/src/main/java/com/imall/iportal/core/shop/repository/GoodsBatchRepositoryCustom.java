
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.GoodsBatch;
import com.imall.iportal.core.shop.vo.StockWarningSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface GoodsBatchRepositoryCustom {

    /**
     * 商品批号列表
     *
     * @param pageable     分页对象
     * @param searchFields 搜索参数（商品名称/通用名称/商品编码/拼音码）
     * @param batch        商品批号
     * @return
     */
    Page<GoodsBatch> query(Pageable pageable, String searchFields, String batch, Long shopId);

    /**
     * 过期药品预警
     *
     * @param pageable                分页对象
     * @param stockWarningSearchParam 搜索参数
     * @return
     */
    Page<GoodsBatch> queryExpireDrugWarningPage(Pageable pageable, StockWarningSearchParam stockWarningSearchParam);

    /**
     * 近效期催销
     *
     * @param pageable                分页对象
     * @param stockWarningSearchParam 搜索参数
     * @param expireDate              近效期期限
     * @return
     */
    Page<GoodsBatch> queryNearExpireDatePage(Pageable pageable, StockWarningSearchParam stockWarningSearchParam, Integer expireDate);

    /**
     * 查询有库存的商品批次
     * @param goodsId 商品ID
     * @return
     */
    List<GoodsBatch> findHasStockGoodsByGoodsId(Long goodsId);

    /**
     * 查询有库存的商品批次
     * @param goodsId 商品ID
     * @param batch 商品批次
     * @return
     */
    List<GoodsBatch> findHasStockGoodsByGoodsIdAndBatch(Long goodsId, String batch);

    /**
     * 通过商品ID查找当前商品库存
     * @param goodsId 商品ID
     * @return 当前商品库存
     */
    Long findCurrentStockByGoodsId(Long goodsId);

    /**
     * 通过商品ID查找当前商品库存
     * @param goodsId 商品ID
     * @param batch 商品批次
     * @return 当前商品库存
     */
    Long findCurrentStockByGoodsIdAndBatch(Long goodsId, String batch);

    /**
     * 重新创建EntityManager，避免取出对象与要更新保存的对象是同一对象，特殊情况使用，一般直接使用findOne.
     * @param id 主键ID
     * @return
     */
    GoodsBatch findOneByCustom(Long id);

    /**
     * 查找商品批次数据插入到索引队列
     *
     * @return 插入索引队列商品批次数量
     */
    Integer queryGoodsBatchToQueue();

    /**
     * 已过期批次商品状态更新为过期
     */
    void updateOverdueBatch();

}

