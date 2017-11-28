package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.GoodsBatch;
import com.imall.iportal.core.shop.vo.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.List;

/**
 * (服务层类)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface GoodsBatchService {

    /**
     * 保存对象
     *
     * @param goodsBatch
     * @return
     */
    GoodsBatch save(@NotNull GoodsBatch goodsBatch);

    /**
     * 根据ID查询相应的对象
     *
     * @param id 主键ID
     * @return
     */
    GoodsBatch findOne(@NotNull Long id);

    /**
     * 商品批号列表
     *
     * @param pageable     分页对象
     * @param searchFields 搜索参数（商品名称/通用名称/商品编码/拼音码）
     * @param batch        商品批号
     * @param shopId       门店ID
     * @return
     */
    Page<GoodsBatchVo> query(@NotNull Pageable pageable, String searchFields, String batch, @NotNull Long shopId);

    /**
     * 过期药品预警
     *
     * @param pageable                分页对象
     * @param stockWarningSearchParam 搜索参数
     * @return
     */
    Page<ExpireDrugWarningGoodsVo> queryExpireDrugWarning(@NotNull Pageable pageable, @NotNull @Valid StockWarningSearchParam stockWarningSearchParam);

    /**
     * 近效期预警
     *
     * @param pageable                分页对象
     * @param stockWarningSearchParam 搜索参数
     * @return
     */
    Page<NearExpireDateGoodsVo> queryNearExpireDatePage(@NotNull Pageable pageable, @NotNull @Valid StockWarningSearchParam stockWarningSearchParam);

    /**
     * 查找批次信息
     *
     * @param goodsId 商品ID
     * @return
     */
    List<GoodsBatch> findByGoodsId(@NotNull Long goodsId);

    /**
     * 根据商品批次ID更新批次信息
     *
     * @param goodsBatchUpdateVo 批次更新Vo对象
     * @return
     */
    void updateBatch(@NotNull @Valid GoodsBatchUpdateVo goodsBatchUpdateVo, Long shopId) throws ParseException;

    /**
     * 查找商品可销售库存信息（金额，数量）
     *
     * @param goodsId 商品ID
     */
    GoodsBatchStockInfo findCurStockInfo(@NotNull Long goodsId);


    /**
     * 商品批次选择组件
     *
     * @param pageable
     * @param goodsCommonComponentSearchParam
     * @param shopId
     * @return
     */
    Page<GoodsBatchCommonComponentPageVo> queryCommonGoodsBatchList(Pageable pageable, GoodsCommonComponentSearchParam goodsCommonComponentSearchParam, Long shopId);

    /**
     * 查询有库存的商品批次
     *
     * @param goodsId 商品ID
     * @return
     */
    List<GoodsBatch> findHasStockGoodsByGoodsId(Long goodsId);

    /**
     * 查询有库存的商品批次
     *
     * @param goodsId 商品ID
     * @param batch 商品批次
     * @return
     */
    List<GoodsBatch> findHasStockGoodsByGoodsIdAndBatch(Long goodsId, String batch);

    /**
     * 根据商品查找
     *
     * @param goodsId
     * @return
     */
    List<GoodsBatchSimpleVo> findGoodsBatchByGoodsId(@NotNull Long goodsId);

    /**
     * 根据商品ID&门店ID查找
     *
     * @param goodsId
     * @param shopId
     * @return
     */
    List<GoodsBatchSimpleVo> findByGoodsIdAndShopId(@NotNull Long goodsId, @NotNull Long shopId);

    GoodsBatch update(GoodsBatch goodsBatch);

    /**
     * 查找商品批次实时库存
     *
     * @param pageable
     * @param shopId       门店ID
     * @param searchFields 搜索参数（商品名称/通用名称/商品编码/拼音码）
     * @param batch        批号
     * @return
     */
    Page<BatchActualStockPageVo> queryBatchActualStock(Pageable pageable, @NotNull Long shopId, String searchFields, String batch);

    /**
     * 查找商品批次数据插入到索引队列
     *
     * @return 插入索引队列商品批次数量
     */
    Integer queryGoodsBatchToQueue();


    /**
     * 根据不同的批次状态查找
     *
     * @param goodsId
     * @param shopId
     * @param batchState
     * @return
     */
    List<GoodsBatch> findByGoodsIdAndShopIdAndBatchState(Long goodsId, Long shopId, String batchState);

    /**
     * 通过商品ID查找当前商品库存
     *
     * @param goodsId 商品ID
     * @return 当前商品库存
     */
    Long findCurrentStockByGoodsId(@NotNull Long goodsId);

    /**
     * 通过商品ID和批次查找当前商品库存
     *
     * @param goodsId 商品ID
     * @param batch 商品批次
     * @return 当前商品库存
     */
    Long findCurrentStockByGoodsIdAndBatch(@NotNull Long goodsId, @NotBlank String batch);


    /**
     * 商品批次减少库存
     *
     * @param goodsBatchId 商品批次ID
     * @param quantity     减少的数量
     */
    void updateSubtractCurrentStock(@NotNull Long goodsBatchId, @NotNull Long quantity);

    /**
     * 查找商品总金额
     *
     * @param goodsId 商品ID
     */
    Double findTotalGoodsAmountByGoodsId(@NotNull Long goodsId);

    /**
     * 重新创建EntityManager，避免取出对象与要更新保存的对象是同一对象，特殊情况使用，一般直接使用findOne.
     *
     * @param id 主键ID
     * @return
     */
    GoodsBatch findOneByCustom(Long id);

    /**
     * 根据 商品ID、批号、货位ID、供应商ID、采购价格 检查批次是否存在
     *
     * @param goodsBatchSimpleSearchVo
     * @return
     */
    GoodsBatch fingByGoodsBatch(GoodsBatchSimpleSearchVo goodsBatchSimpleSearchVo);

    /**
     * 根据 商品ID、批号、货位ID、供应商ID、采购价格、批次状态 检查批次是否存在
     *
     * @param goodsBatchSimpleSearchVo 商品ID、批号、货位ID、供应商ID、采购价格
     * @param batchState               批次状态
     * @return
     */
    GoodsBatch findByGoodsIdAndBatchAndStorageSpaceIdAndSupplierIdAndPurchasePriceAndBatchState(GoodsBatchSimpleSearchVo goodsBatchSimpleSearchVo, String batchState);

    /**
     * 删除批次信息
     *
     * @param goodsBatch 批次信息
     */
    void deleteBatch(@NotNull GoodsBatch goodsBatch);

    /**
     * 检查批次状态
     * @param batchId 商品批次ID
     */
    void checkGoodsBatchState(Long batchId);

    /**
     * 检查批次状态
     * @param goodsId 商品ID
     * @param batch 商品批次
     */
    void checkGoodsBatchState(Long goodsId, String batch);

    /**
     * 已过期批次商品状态更新为过期
     */
    void updateOverdueBatch();
}
