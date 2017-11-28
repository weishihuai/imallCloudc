
package com.imall.iportal.core.shop.repository;

import com.imall.iportal.core.shop.entity.OutInStockLog;

import java.util.Date;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface OutInStockLogRepositoryCustom{

    /**
     * 查询某一时间点后的某一商品第一次出入库记录
     * @param goodsId 商品ID
     * @param date 查询的时间点
     * @return
     */
    OutInStockLog findFirstLogAfterDate(Long goodsId, Long shopId, Date date);

    /**
     * 查找出入库日志数据插入到索引队列
     * @return
     */
    Integer queryOutInStockLogToQueue();

}

