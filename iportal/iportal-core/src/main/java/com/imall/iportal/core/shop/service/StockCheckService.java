package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.StockCheck;
import com.imall.iportal.core.shop.vo.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.ParseException;

/**
 * (服务层类)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface StockCheckService {

    /**
     * 根据id查询库存盘点信息
     *
     * @param id 库存盘点ID
     * @return
     */
    StockCheck findOne(@NotNull Long id);

    /**
     * 库存盘点列表
     *
     * @param pageable              分页对象
     * @param stockCheckSearchParam 搜索参数
     * @return
     */
    Page<StockCheckPageVo> query(@NotNull Pageable pageable, @Valid @NotNull StockCheckSearchParam stockCheckSearchParam) ;


    /**
     * 盘点过账列表
     *
     * @param pageable                分页对象
     * @param stockPostingSearchParam 搜索参数
     * @return
     */
    Page<StockPostingPageVo> queryStockPosting(@NotNull Pageable pageable, @Valid @NotNull StockPostingSearchParam stockPostingSearchParam);

    /**
     * 根据盘点单号查询库存盘点详情
     *
     * @param checkOrderNum 盘点单号
     * @param shopId        门店id
     * @return
     */
    StockCheckDetailVo findByCheckOrderNum(@NotBlank String checkOrderNum, @NotNull Long shopId);

    /**
     * 根据盘点单号删除盘点信息
     *
     * @param checkOrderNum 盘点单号
     */
    void updateCheckedStateByCheckOrderNum(@NotBlank String checkOrderNum, Long shopId);

    /**
     * 保存库存盘点信息
     *
     * @param shopId           门店ID
     * @param operatorId       操作人ID
     * @param stockCheckSaveVo 盘点信息
     */
    void saveStockCheck(@NotNull Long shopId, Long operatorId, @Valid @NotNull StockCheckSaveVo stockCheckSaveVo) throws ParseException;

    /**
     * 库存盘点更新实盘数量
     *
     * @param stockCheckCheckedVo 实盘数量
     */
    void updateStockCheckRealCheckQuantity(@NotNull @Valid StockCheckCheckedVo stockCheckCheckedVo, @NotNull Long shopId) throws ParseException;

    /**
     * 根据盘点ID过账
     *
     * @param id     盘点ID
     * @param shopId 门店ID
     */
    void updateStockCheckStateToPosting(@NotNull Long id, @NotNull Long shopId);
}
