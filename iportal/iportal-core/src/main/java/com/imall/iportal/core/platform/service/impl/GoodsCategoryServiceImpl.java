package com.imall.iportal.core.platform.service.impl;


import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.Node;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.commons.dicts.GlobalExt;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.platform.entity.GoodsCategory;
import com.imall.iportal.core.platform.repository.GoodsCategoryRepository;
import com.imall.iportal.core.platform.service.GoodsCategoryService;
import com.imall.iportal.core.platform.vo.GoodsCategorySaveVo;
import com.imall.iportal.core.platform.vo.GoodsCategoryUpdateVo;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品 分类(服务层实现)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class GoodsCategoryServiceImpl extends AbstractBaseService<GoodsCategory, Long> implements GoodsCategoryService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unused")
    private GoodsCategoryRepository getGoodsCategoryRepository() {
        return (GoodsCategoryRepository) baseRepository;
    }


    @Override
    public List<Node> findByParentId(Long parentId, Integer subMaxLayer) {
        if (parentId == null) {
            parentId = GlobalExt.GOODS_CATEGORY_FIRST_NODE_ID;
        }
        List<GoodsCategory> list = getGoodsCategoryRepository().findByParentIdAndIsDeleteOrderByDisplayPositionDesc(parentId,BoolCodeEnum.NO.toCode());
        return build(parentId, subMaxLayer, list, 0);
    }

    private List<Node> build(Long parentId, Integer subMaxLayer, List<GoodsCategory> list, int currSubLayer) {
        List<Node> nodes = new ArrayList<>();
        for (GoodsCategory category : list) {
            Node node = new Node();
            node.setId(category.getId());
            node.setName(category.getCategoryName());
            node.setNodeCode(category.getNodeCode());
            node.setPid(parentId);
            node.setOpen(false);
            node.setChecked(false);
            node.setNodeObject(category);
            if (currSubLayer < subMaxLayer) {
                List<GoodsCategory> subList = getGoodsCategoryRepository().findByParentIdAndIsDeleteOrderByDisplayPositionDesc(category.getId(),BoolCodeEnum.NO.toCode());
                node.setIsParent(subList.size() > 0);
                node.setChildren(build(category.getId(), subMaxLayer, subList, currSubLayer + 1));
            } else {
                node.setIsParent(false);
                node.setChildren(new ArrayList<Node>());
            }
            nodes.add(node);
        }
        return nodes;
    }


    @Override
    public List<Node> listRoots() {
        List<GoodsCategory> list = getGoodsCategoryRepository().findByParentIdAndIsDeleteOrderByDisplayPositionDesc(GlobalExt.GOODS_CATEGORY_FIRST_NODE_ID,BoolCodeEnum.NO.toCode());
        return build(GlobalExt.GOODS_CATEGORY_FIRST_NODE_ID, GlobalExt.PRODUCT_SALES_CATEGORY_SUB_MAX_LAYERS, list, 0);
    }

    @Transactional
    @Override
    public void updateDelete(Long id) {
        GoodsCategory salesCategory = findOne(id);
        List<GoodsCategory> goodsCategories = getGoodsCategoryRepository().findByNodeCodeLike(salesCategory.getNodeCode());
        for (GoodsCategory ac : goodsCategories) {
            ac.setIsDelete(BoolCodeEnum.YES.toCode());
            save(ac);
        }
    }

    @Transactional
    @Override
    public Long saveGoodsCategory(GoodsCategorySaveVo goodsCategorySaveVo) {
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setParentId(goodsCategorySaveVo.getParentId());
        goodsCategory.setDisplayPosition(goodsCategorySaveVo.getDisplayPosition());
        goodsCategory.setCategoryName(goodsCategorySaveVo.getCategoryName());
        goodsCategory.setIsDelete(BoolCodeEnum.NO.toCode());
        goodsCategory.setNodeCode(ServiceManager.treeCodeGenerateBuilder.generate8Code(GoodsCategory.class, goodsCategory.getParentId()));
        return save(goodsCategory).getId();
    }

    @Transactional
    @Override
    public void updateGoodsCategory(GoodsCategoryUpdateVo goodsCategoryUpdateVo) {
        GoodsCategory goodsCategory = findOne(goodsCategoryUpdateVo.getId());
        if (goodsCategory == null) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }
        goodsCategory.setCategoryName(goodsCategoryUpdateVo.getCategoryName());
        goodsCategory.setDisplayPosition(goodsCategoryUpdateVo.getDisplayPosition());
        save(goodsCategory);
    }

    @Override
    public Boolean findGoodsCategoryExist(String categoryName, Long id) {
        GoodsCategory goodsCategory = getGoodsCategoryRepository().findByCategoryNameAndIsDelete(categoryName,BoolCodeEnum.NO.toCode());
        return (goodsCategory == null && id == null) || goodsCategory == null || goodsCategory.getId().equals(id);
    }

    @Override
    public List<GoodsCategory> findAllCategories() {
        return getGoodsCategoryRepository().findByIsDeleteAndIdNot(BoolCodeEnum.NO.toCode(),GlobalExt.GOODS_CATEGORY_FIRST_NODE_ID);
    }

    @Override
    public List<GoodsCategory> findByCategoryName(String categoryName) {
        return getGoodsCategoryRepository().findByCategoryName(categoryName);
    }
}