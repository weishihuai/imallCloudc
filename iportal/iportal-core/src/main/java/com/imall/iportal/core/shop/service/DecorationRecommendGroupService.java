package com.imall.iportal.core.shop.service;


import com.imall.iportal.core.shop.entity.DecorationRecommendGroup;
import com.imall.iportal.core.shop.vo.DecorationRecommendGroupVo;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
@Validated
public interface DecorationRecommendGroupService{

    /**
     * 根据门店ID查找
     * @param shopId
     * @return
     */
    List<DecorationRecommendGroupVo> findByShopId(@NotNull Long shopId);

    /**
     * 根据主键和门店ID删除
     * @param id
     * @param shopId
     */
    void deleteByIdAndShopId(@NotNull Long id, @NotNull Long shopId);

    /**
     * 根据主键ID和门店ID查找
     * @param id
     * @param shopId
     * @return
     */
    DecorationRecommendGroupVo findByIdAndShopId(@NotNull Long id, @NotNull Long shopId);

    /**
     * 保存分组
     * @param shopId
     * @param groupNm
     * @return
     */
    Long saveDecorationRecommendGroup(@NotNull Long shopId, @NotBlank String groupNm);

    /**
     * 更新分组
     * @param shopId
     * @param id
     * @param groupNm
     * @return
     */
    Long updateDecorationRecommendGroup(@NotNull Long shopId, @NotNull Long id, @NotBlank String groupNm);

    /**
     *
     * @param shopId
     * @return
     */
    List<DecorationRecommendGroup> getByShopId(@NotNull Long shopId);
}
