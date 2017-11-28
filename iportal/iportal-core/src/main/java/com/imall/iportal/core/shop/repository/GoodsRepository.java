
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.Goods;
/**
 * 商品(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface GoodsRepository extends  IBaseRepository<Goods, Long>,GoodsRepositoryCustom {


    /**
     * 通过商品编码查找goods
     * @param goodsCode
     * @return
     */
    Goods findByGoodsCodeAndShopId(String goodsCode,Long shopId);

    /**
     * 查看拆零后的商品
     * @param goodsId
     * @return
     */
    Goods findBySplitZeroSourceGoodsId(Long goodsId);

}

