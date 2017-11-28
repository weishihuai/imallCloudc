package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.vo.OtherOutStockDetailVo;
import com.imall.iportal.core.shop.vo.OtherOutStockPageVo;
import com.imall.iportal.core.shop.vo.OtherOutStockSaveVo;
import com.imall.iportal.core.shop.vo.OtherOutStockSearchParam;
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
public interface OtherOutStockService {

    /**
     * 其他出库列表
     *
     * @param pageable                 分页对象
     * @param otherOutStockSearchParam 搜索参数
     * @return
     */
    Page<OtherOutStockPageVo> query(@NotNull Pageable pageable, @Valid @NotNull OtherOutStockSearchParam otherOutStockSearchParam);

    /**
     * 根据出库单号查询出库详情
     *
     * @param outStockCode 出库单号
     * @param shopId       门店id
     * @return
     */
    OtherOutStockDetailVo findByOutStockCode(@NotBlank String outStockCode, @NotNull Long shopId);

    /**
     * 保存其他出库信息
     *
     * @param otherOutStockSaveVo
     * @throws ParseException
     */
    void saveOtherOutStock(@NotNull @Valid OtherOutStockSaveVo otherOutStockSaveVo) throws ParseException;

}
