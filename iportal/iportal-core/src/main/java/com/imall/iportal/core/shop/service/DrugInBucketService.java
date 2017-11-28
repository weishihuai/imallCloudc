package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.vo.DrugInBucketSaveVo;
import com.imall.iportal.core.shop.vo.DrugInBucketSearchParam;
import com.imall.iportal.core.shop.vo.DrugInBucketVo;
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
public interface DrugInBucketService {
    /**
     * 装斗记录列表
     *
     * @param pageable                分页对象
     * @param drugInBucketSearchParam 搜索参数
     * @return
     */
    Page<DrugInBucketVo> query(@NotNull Pageable pageable, @Valid @NotNull DrugInBucketSearchParam drugInBucketSearchParam);

    /**
     * 根据装斗ID查询装斗信息
     *
     * @param shopId 门店ID
     * @param id     药品装斗ID
     * @return
     */
    DrugInBucketVo findById(@NotNull Long shopId, @NotNull Long id);

    /**
     * 保存装斗信息
     *
     * @param drugInBucketSaveVo 装斗Vo对象
     */
    void saveDrugInBucket(@NotNull @Valid DrugInBucketSaveVo drugInBucketSaveVo) throws ParseException;
}
