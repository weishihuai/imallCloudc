package com.imall.iportal.core.platform.service;


import com.imall.commons.base.util.Node;
import com.imall.iportal.core.platform.entity.GoodsCategory;
import com.imall.iportal.core.platform.vo.GoodsCategorySaveVo;
import com.imall.iportal.core.platform.vo.GoodsCategoryUpdateVo;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 商品 分类(服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface GoodsCategoryService{

    /**
     * 查找子节点
     * @param id
     * @param subMaxLayer
     * @return
     */
    List<Node> findByParentId(Long id, Integer subMaxLayer);


    GoodsCategory findOne(@NotNull Long goodsCategoryId);

    /**
     * 商品分类列表
     * @return
     */
    List<Node> listRoots();

    /**
     * 软删除
     * @param id
     */
    void updateDelete(@NotNull Long id);

    /**
     * 新增商品分类
     * @param goodsCategorySaveVo
     * @return
     */
    Long saveGoodsCategory(@NotNull @Valid GoodsCategorySaveVo goodsCategorySaveVo);

    /**
     * 编辑商品分类
     * @param goodsCategoryUpdateVo
     */
    void updateGoodsCategory(@NotNull @Valid GoodsCategoryUpdateVo goodsCategoryUpdateVo);

    /**
     * 检查该商品分类名称是否可用
     * @param categoryName
     * @param id
     * @return
     */
    Boolean findGoodsCategoryExist(@NotBlank @Length(max = 32)String categoryName, Long id);

    /**
     * 查找所有的商品分类
     * @return
     */
    List<GoodsCategory> findAllCategories();

    /**
     *
     * @param categoryName
     * @return
     */
    List<GoodsCategory> findByCategoryName(String categoryName);
}
