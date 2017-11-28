package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.DrugCuringItem;
import com.imall.iportal.core.shop.vo.DrugCuringItemDetailVo;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface DrugCuringItemService {
    /**
     * 保存养护项
     * @param drugCuringItem
     * @return
     */
    DrugCuringItem save(@NotNull DrugCuringItem drugCuringItem);

    /**
     * 根据药品养护 ID 查询相关药品信息
     * @param drugCuringId
     * @return
     */
    List<DrugCuringItemDetailVo> listByDrugCuringId(@NotNull Long shopId, @NotNull Long drugCuringId);

    /**
     * 根据 ID 获取养护项信息
     * @param id
     * @return
     */
    DrugCuringItem findOne(@NotNull Long shopId, @NotNull Long id);

    /**
     * 更新
     * @param drugCuringItem
     * @return
     */
    DrugCuringItem update(@NotNull DrugCuringItem drugCuringItem);
}
