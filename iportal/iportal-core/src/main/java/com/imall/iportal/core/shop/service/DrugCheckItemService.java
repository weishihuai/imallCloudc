package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.DrugCheckItem;
import com.imall.iportal.core.shop.vo.DrugCheckItemDetailVo;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface DrugCheckItemService {
    /**
     * 根据药品检查 ID 查询相关药品信息
     * @param drugCheckId
     * @return
     */
    List<DrugCheckItemDetailVo> listByDrugCheckId(@NotNull Long shopId, @NotNull Long drugCheckId);

    /**
     * 保存
     * @param drugCheckItem
     * @return
     */
    DrugCheckItem save(@NotNull DrugCheckItem drugCheckItem);

    /**
     * 根据 ID 获取检查项信息
     * @param id
     * @return
     */
    DrugCheckItem findOne(@NotNull Long shopId, @NotNull Long id);

    /**
     * 更新
     * @param drugCheckItem
     * @return
     */
    DrugCheckItem update(@NotNull DrugCheckItem drugCheckItem);
}
