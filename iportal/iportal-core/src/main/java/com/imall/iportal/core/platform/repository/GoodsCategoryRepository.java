
package com.imall.iportal.core.platform.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.platform.entity.GoodsCategory;

import java.util.List;

/**
 * 商品 分类(JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface GoodsCategoryRepository extends IBaseRepository<GoodsCategory, Long>, GoodsCategoryRepositoryCustom {


    List<GoodsCategory> findByParentIdAndIsDeleteOrderByDisplayPositionDesc(Long parentId, String isDelete);

    GoodsCategory findByCategoryNameAndIsDelete(String categoryName, String isDelete);

    List<GoodsCategory> findByNodeCodeLike(String nodeCode);

    List<GoodsCategory> findByIsDeleteAndIdNot(String isDelete, Long goodsCategoryFirstNodeId);

    List<GoodsCategory> findByCategoryName(String categoryName);
}

