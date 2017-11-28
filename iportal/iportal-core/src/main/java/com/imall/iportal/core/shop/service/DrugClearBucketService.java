package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.vo.DrugClearBucketSaveVo;
import com.imall.iportal.core.shop.vo.DrugClearBucketSearchParam;
import com.imall.iportal.core.shop.vo.DrugClearBucketVo;
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
public interface DrugClearBucketService {
    /**
     * 清斗记录列表
     *
     * @param pageable                   分页对象
     * @param drugClearBucketSearchParam 搜索参数
     * @return
     */
    Page<DrugClearBucketVo> query(@NotNull Pageable pageable, @NotNull @Valid DrugClearBucketSearchParam drugClearBucketSearchParam);

    /**
     * 根据清斗ID查询装斗信息
     *
     * @param shopId 门店ID
     * @param id     药品清斗ID
     * @return
     */
    DrugClearBucketVo findById(@NotNull Long shopId, @NotNull Long id);

    /**
     * 保存清斗信息
     *
     * @param drugClearBucketSaveVo 清斗Vo对象
     */
    void saveDrugClearBucket(@NotNull @Valid DrugClearBucketSaveVo drugClearBucketSaveVo) throws ParseException;
}
