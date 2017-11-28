
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.StockCheck;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface StockCheckRepository extends IBaseRepository<StockCheck, Long>, StockCheckRepositoryCustom {

    /**
     * 查询盘点信息
     *
     * @param checkOrderNum 盘点单号
     * @param shopId        门店ID
     * @return
     */
    @Query("SELECT a FROM StockCheck a where a.checkOrderNum =?1 and a.shopId = ?2")
    List<StockCheck> listByCheckOrderNum(String checkOrderNum, Long shopId);

    /**
     * 根据盘点单号删除盘点信息
     *
     * @param checkedStateCode 盘点状态代码
     * @param checkOrderNum    盘点单号
     */
    @Modifying
    @Query("update StockCheck sc set sc.checkedStateCode =?1 , sc.operationTime =?2  where sc.checkOrderNum =?3 and sc.shopId =?4")
    void updateCheckedStateAndOperationTimeByCheckOrderNum(String checkedStateCode, Date operationTime, String checkOrderNum, Long shopId);

}

