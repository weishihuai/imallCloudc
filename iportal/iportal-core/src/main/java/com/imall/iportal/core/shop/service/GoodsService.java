package com.imall.iportal.core.shop.service;


import com.imall.commons.base.util.excel.ErrorLog;
import com.imall.iportal.core.shop.entity.Goods;
import com.imall.iportal.core.shop.vo.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.List;

/**
 * 商品(服务层类)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface GoodsService {

    /**
     * 根据ID查询相应的对象
     *
     * @param id 主键ID
     * @return
     */
    Goods findOne(@NotNull Long id);

    /**
     * 商品公共组件列表
     *
     * @param pageable
     * @param goodsCommonComponentSearchParam
     * @return
     */
    Page<GoodsCommonComponentPageVo> queryGoodsCommonComponent(Pageable pageable, GoodsCommonComponentSearchParam goodsCommonComponentSearchParam, @NotNull Long shopId);

    /**
     * pos查询商品列表
     *
     * @param searchFields 商品拼音/名称/编码（拼音、名称支持模糊搜索）
     * @return List<GoodsPostPageVo>
     */
    List<GoodsPostPageVo> listGoodsByPos(@NotNull Long shopId, String searchFields);

    /**
     * 添加商品
     *
     * @param goodsSaveVo 商品
     * @return
     */
    Long saveGoods(@NotNull @Valid GoodsSaveVo goodsSaveVo);

    /**
     * 商品管理列表
     *
     * @param pageable
     * @param goodsListSearchParam
     * @return
     */
    Page<GoodsListPageVo> query(Pageable pageable, GoodsListSearchParam goodsListSearchParam, @NotNull Long shopId);


    /**
     * 查找商品数据插入到索引队列
     *
     * @return
     */
    Integer queryGoodsToQueue();

    /**
     * 查找商品详情
     *
     * @param id
     * @return
     */
    GoodsDetailVo findDetail(@NotNull Long id,@NotNull Long shopId);

    /**
     * 更新商品
     *
     * @param goodsUpdateVo
     * @return
     */
    Long updateGoods(@NotNull @Valid GoodsUpdateVo goodsUpdateVo) throws ParseException;

    /**
     * 根据商品ID与商品类型获取剂型
     *
     * @param goodsId
     * @param goodsTypeCode
     * @return
     */
    String getDosageFormByGoodsIdAndGoodsTypeCode(@NotNull Long goodsId, @NotNull String goodsTypeCode);

    /**
     * 根据主键查询
     *
     * @param goodsId
     * @return
     */
    PurchaseOrderGoodsVo findPurchaseOrderGoodsVoById(@NotNull Long goodsId);


    /**
     * 检查该商品编码是否已经存在
     *
     * @param goodsCode
     * @param id
     * @return
     */
    Boolean findGoodsCodeExist(@NotBlank @Length(max = 32) String goodsCode, Long id,@NotNull Long shopId);


    /**
     * 启用
     * @param goodsUpdateIsEnableParam
     * @return
     */
    Boolean updateStartUsing(@NotNull @Valid GoodsUpdateIsEnableParam goodsUpdateIsEnableParam);

    /**
     * 停用
     * @param goodsUpdateIsEnableParam
     * @return
     */
    Boolean updateBlockUp(@NotNull @Valid GoodsUpdateIsEnableParam goodsUpdateIsEnableParam);
    /**
     * 缺货预警
     *
     * @param pageable                分页对象
     * @param stockWarningSearchParam 搜索参数
     * @return
     */
    Page<OutOfStockWarningGoodsVo> queryOutOfStockWarning(@NotNull Pageable pageable, @Valid @NotNull StockWarningSearchParam stockWarningSearchParam);

    /**
     * 查找商品实时库存
     * @param pageable
     * @param shopId 门店ID
     * @param searchFields 搜索参数（商品名称/通用名称/商品编码/拼音码）
     * @return
     */
    Page<GoodsActualStockPageVo> queryGoodsActualStock(Pageable pageable, @NotNull Long shopId, String searchFields);

    Goods save(Goods goods);

    /**
     * 微信商品列表
     * @param pageable
     * @param weChatGoodsListSearchParam
     * @param shopId
     * @return
     */
    Page<WeChatGoodsPageVo> queryForWeChat(Pageable pageable, WeChatGoodsListSearchParam weChatGoodsListSearchParam, @NotNull Long shopId);

    /**
     * 微信页面 商品详情
     * @return
     */
    WeChatGoodsDetailVo findDetailForWeChat(@NotNull Long id);

    /**
     * 对可以拆零的商品进行拆零,若已经拆零过,找到直接返回,否则拆零后返回
     * @param goodsId
     * @return
     */
    Goods saveSpiltGoods(@NotNull Long goodsId,@NotNull Long shopId);

    /**
     * 查询装修推荐商品
     * @param pageable
     * @param groupId
     * @param shopId
     * @param salesCategoryId
     * @param keyword
     * @return
     */
    Page<DecorationRecommendGoodsVo> queryDecorationRecommendGoods(@NotNull Pageable pageable, @NotNull Long groupId, @NotNull Long shopId, Long salesCategoryId, String keyword);

    /**
     * 导出所有符合搜索条件的数据
     * @param goodsListSearchParam
     * @return
     */
    Boolean exportExcel(@NotNull String webRealPath, @NotNull String xlsTemplatePath, @NotNull String xlsOutputPath, @NotNull GoodsListSearchParam goodsListSearchParam, @NotNull Long shopId);

    /**
     * 导入
     * @param localFileId 物理文件地址
     * @return
     */
    List<ErrorLog> importData(String localFileId, @NotNull Long shopId);
}
