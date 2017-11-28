
package com.imall.iportal.core.shop.repository;

import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.shop.entity.StorageSpaceMove;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public interface StorageSpaceMoveRepository extends IBaseRepository<StorageSpaceMove, Long>, StorageSpaceMoveRepositoryCustom {

    /**
     * 查询货位移动信息
     *
     * @param moveOrderNum 移动单号
     * @param shopId       门店ID
     * @return
     */
    @Query("SELECT s FROM StorageSpaceMove s where s.moveOrderNum =?1 and s.shopId = ?2")
    List<StorageSpaceMove> listByMoveOrderNum(String moveOrderNum, Long shopId);

}

