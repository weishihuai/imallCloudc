
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.Goods;
import com.imall.iportal.core.shop.vo.GoodsCommonComponentSearchParam;
import com.imall.iportal.core.shop.vo.StockWarningSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 商品(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface GoodsRepositoryCustom{


    /**
     * 缺货预警
     *
     * @param pageable                分页对象
     * @param stockWarningSearchParam 搜索参数
     * @return
     */
    Page<Goods> queryOutOfStockWarningPage(Pageable pageable, StockWarningSearchParam stockWarningSearchParam);

    /**
     * 查找商品
     * @param pageable
     * @param shopId 门店ID
     * @param searchFields 搜索参数（商品名称/通用名称/商品编码/拼音码）
     * @return
     */
    Page<Goods> query(Pageable pageable, Long shopId, String searchFields);

    /**
     * 查找商品数据插入到索引队列
     * @return
     */
    Integer queryGoodsToQueue();
}

