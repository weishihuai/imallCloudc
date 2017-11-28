package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.OtherInStock;
import com.imall.iportal.core.shop.vo.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
public interface OtherInStockService {

    /**
     * 其他入库列表
     *
     * @param pageable                分页对象
     * @param otherInStockSearchParam 搜索参数
     * @return
     */
    Page<OtherInStockPageVo> query(@NotNull Pageable pageable, @NotNull @Valid OtherInStockSearchParam otherInStockSearchParam);

    /**
     * 根据入库单号查询入库详情
     *
     * @param inStockCode 入库单号
     * @param shopId      门店id
     * @return
     */
    OtherInStockDetailVo findByInStockCode(@NotBlank String inStockCode, @NotNull Long shopId);


    /**
     * 保存其他入库信息
     *
     * @param otherInStockSaveVo
     * @throws ParseException
     */
    void saveOtherInStock(@NotNull @Valid OtherInStockSaveVo otherInStockSaveVo) throws ParseException;

    /**
     * 根据输入批号、生产日期、有效期检查/新增商品批次信息,返回批次主键ID
     *
     * @param otherInStockSaveVo
     * @param goodsBatchId
     */
    Long checkGoodsBatch(@NotNull OtherInStockSaveVo otherInStockSaveVo, @NotNull OtherInStockGoodsSaveVo otherInStockGoodsSaveVo, Long goodsBatchId) throws ParseException;

    /**
     * 查询批次入库信息
     * @param shopId    门店 ID
     * @param goodsBatchId  批次 ID
     * @return
     */
    List<OtherInStock> queryByGoodsBatchId(@NotNull Long shopId, @NotNull Long goodsBatchId);
}
